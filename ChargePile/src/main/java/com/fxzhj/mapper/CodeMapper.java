package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Code;

public interface CodeMapper {
	
	//查询绑定信息
	List<Code> queryCodeByUrl(Code code);

	//查询未绑定的二维码
	List<Code> queryCodeUnbounded();

	//根据二维码id修改状态
	void updateCode(Code code);

	//批量生成1000个二维码
	void batchCode(List<Code> list);

	//根据codeNum判断是否重复
	int countByCodeNum(String codeNum);

	//新增二维码编号
	void addCode(Code code);

	//根据二维码修改二维码编号
	void updateByCodeNum(String codeNum, String regCode);

	//批量删除二维码信息
	void batchDelete(String[] ids);

	//随机查询一条空闲的二维码
	Code queryRandom();
	
}