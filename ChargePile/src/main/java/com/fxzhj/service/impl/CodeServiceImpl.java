package com.fxzhj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxzhj.mapper.CodeMapper;
import com.fxzhj.mapper.NodeCodeMapper;
import com.fxzhj.mapper.NodeMapper;
import com.fxzhj.mapper.UrlDescribeMapper;
import com.fxzhj.model.Code;
import com.fxzhj.model.NodeCode;
import com.fxzhj.model.UrlDescribe;
import com.fxzhj.service.CodeService;
import com.fxzhj.util.DateUtil;

import tk.mybatis.mapper.util.StringUtil;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeMapper CMapper;
	
	@Autowired
	private NodeMapper NMapper;
	
	@Autowired
	private NodeCodeMapper NCMapper;
	
	@Autowired
	private UrlDescribeMapper UDMapper;
	
	//根据节点查询是否绑定
	@Override
	public List<Code> queryCodeByUrl(Code code) {
		//查询绑定信息
		List<Code> codeList = CMapper.queryCodeByUrl(code);
		for(Code bean : codeList){
			if(StringUtil.isNotEmpty(bean.getNodeUrl())){
				String[] arr= bean.getNodeUrl().split("->");
				String name="";
				for(int i=0;i<arr.length;i++){
					//查询节点名称并拼接路径
					name += NMapper.queryNodeName(arr[i]) +"->";
				}
				name=name.substring(0,name.length()-2);
				bean.setNodeUrl(name);
			}
		}
		return codeList;
	}

	//查询未绑定二维码
	@Override
	public List<Code> queryCodeUnbounded() {
		return CMapper.queryCodeUnbounded();
	}

	//根据二维码id修改状态
	@Override
	@Transactional
	public void updateCode(Code code) {
		Date date = new Date();
		UrlDescribe urlDescribe=new UrlDescribe();
		urlDescribe.setDescpt(code.getDescpt());
		urlDescribe.setCreateTime(date);
		//增到绑定的路径描述
		UDMapper.addUrlDescribe(urlDescribe);
		
		NodeCode nodeCode=new NodeCode();
		nodeCode.setCodeId(code.getId());
		nodeCode.setNodeUrl(code.getNodeUrl());
		nodeCode.setDescribeId(urlDescribe.getId());
		nodeCode.setCreateTime(date);
		//新增节点路径绑定二维码信息表
		NCMapper.addNodeCode(nodeCode);
		
		//修改二维码状态
		code.setStatus(1);
		CMapper.updateCode(code);
	}

	//根据二维码id解绑
	@Override
	@Transactional
	public void unbundlingCode(Long id) {
		Code code=new Code();
		code.setId(id);
		code.setStatus(0);
		//修改二维码状态
		CMapper.updateCode(code);
		
		//首先根据二维码id删除路径描述信息
		int count = UDMapper.deleteByCid(id);
		if(count>0){
			//然后根据二维码id删除二维码绑定节点信息
			NCMapper.deleteByCid(id);
		}
	}

	//批量生成1000个二维码
	@Override
	public void batchCode() {
		List<Code> list=new ArrayList<Code>();
		Date date = new Date();
		for(int i=0;i<1000;i++){
			Code code= new Code();
			code.setCodeNum(DateUtil.codeNum(i));
			code.setCreateTime(date);
			code.setStatus(0);
			list.add(code);
		}
		CMapper.batchCode(list);
	}

}
