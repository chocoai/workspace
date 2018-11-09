package com.fxzhj.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxzhj.mapper.CodeMapper;
import com.fxzhj.mapper.ElevatorMapper;
import com.fxzhj.mapper.NodeCodeMapper;
import com.fxzhj.mapper.NodeMapper;
import com.fxzhj.mapper.TypeMapper;
import com.fxzhj.mapper.UrlDescribeMapper;
import com.fxzhj.model.Code;
import com.fxzhj.model.Elevator;
import com.fxzhj.model.Node;
import com.fxzhj.model.NodeCode;
import com.fxzhj.model.UrlDescribe;
import com.fxzhj.service.ElevatorService;
import com.fxzhj.util.DateUtil;

import tk.mybatis.mapper.util.StringUtil;

@Service
public class ElevatorServiceImpl implements ElevatorService {
	
	@Autowired
	private ElevatorMapper EMapper;
	
	@Autowired
	private NodeMapper NMapper;
	
	@Autowired
	private CodeMapper CMapper;
	
	@Autowired
	private NodeCodeMapper NCMapper;
	
	@Autowired
	private UrlDescribeMapper UDMapper;
	
	@Autowired
	private TypeMapper TMapper;

	//根据条件查询小区电梯
	@Override
	public List<Elevator> queryElevator(Elevator elevator) {
		return EMapper.queryElevator(elevator);
	}

	//新增小区电梯
	@Override
	@Transactional
	public void add(Elevator elevator) {
		Date date=new Date();
		String nodeId = DateUtil.DTstr();
		Code code = new Code();
		code.setCodeNum(elevator.getRegCode());
		code.setStatus(1);
		code.setCreateTime(date);
		code.setCreateUser(null);
		int count1=CMapper.countByCodeNum(elevator.getRegCode());
		if(count1==0){
			//新增code中的二维码编号
			CMapper.addCode(code);
			
			Node node = new Node();
			node.setNodeId(nodeId);
			node.setNodeName(elevator.getEleName());
			node.setIsNode(1);
			node.setNodeStatus(1);
			node.setCreateTime(date);
			//需要修改为查询
			node.setParentId("DT201706224564");
			//新增node中的节点
			NMapper.addNode(node);
			
			//查询电梯类型所对应的内容
			UrlDescribe uDescribe = new UrlDescribe();
			String content = TMapper.queryByType(2);
			uDescribe.setDescpt(content);
			uDescribe.setCreateTime(date);
			uDescribe.setCreateUser(null);
			//新增url描述表
			UDMapper.addUrlDescribe(uDescribe);
			
			//新增节点路径绑定二维码
			NodeCode nCode = new NodeCode();
			nCode.setNodeUrl(node.getParentId()+"->"+nodeId);
			nCode.setCodeId(code.getId());
			nCode.setDescribeId(uDescribe.getId());
			nCode.setCreateTime(date);
			nCode.setCreateUser(null);
			NCMapper.addNodeCode(nCode);
			
			elevator.setNodeId(nodeId);
			//新增电梯信息
			EMapper.add(elevator);
		}
		
		
	}

	//根据id修改电梯
	@Override
	@Transactional
	public void update(Elevator elevator) {
		//根据id查询二维码
		Elevator ele = EMapper.queryById(elevator.getId());
		if(StringUtil.isNotEmpty(ele.getRegCode())){
			int count1=CMapper.countByCodeNum(elevator.getRegCode());
			if(count1==0){
				//修改二维码编号
				CMapper.updateByCodeNum(ele.getRegCode(),elevator.getRegCode());
				//根据nodeId修改节点名称中的节点
				NMapper.updateByNid(ele.getNodeId(),elevator.getEleName());
				//根据id修改电梯
				EMapper.update(elevator);
			}
		}
		
	}

	//批量删除
	@Override
	@Transactional
	public void batchDelete(int[] ids) {
		//批量查询电梯信息
		List<Elevator> list = EMapper.queryByArrIds(ids);
		if(list!=null && list.size()>0){
			String[] nodeIds = new String[list.size()];
			String[] codeNums = new String[list.size()];
			for(int i=0;i<list.size();i++){
				nodeIds[i]=list.get(i).getNodeId();
				codeNums[i]=list.get(i).getRegCode();
			} 
			if(nodeIds.length>0 && codeNums.length>0){
				//批量删除节点信息
				NMapper.batchDelete(nodeIds);
				
				//批量删除路径描述信息
				UDMapper.batchDelete(codeNums);
				
				//批量删除节点绑定二维码信息
				NCMapper.batchDelete(codeNums);
				
				//批量删除二维码信息
				CMapper.batchDelete(codeNums);
				
				//批量删除电梯信息
				EMapper.batchDelete(ids);
			}
		}
	}

	//根据名称和二维码判断是否重复
	@Override
	public int countByNameOrCode(Elevator elevator) {
		return EMapper.countByNameOrCode(elevator);
	}
	
	//根据名称和二维码并根据id排除本条数据 进行重复判断
	@Override
	public int countExId(Elevator elevator) {
		return EMapper.countExId(elevator);
	}

}
