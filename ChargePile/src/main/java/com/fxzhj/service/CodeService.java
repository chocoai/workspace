package com.fxzhj.service;

import java.util.List;

import com.fxzhj.model.Code;

public interface CodeService {

	//根据节点查询是否绑定
	List<Code> queryCodeByUrl(Code code);

	//查询未绑定二维码
	List<Code> queryCodeUnbounded();

	//根据二维码id修改状态
	void updateCode(Code code);

	//根据二维码id解绑
	void unbundlingCode(Long id);

	//批量生成二维码
	void batchCode();

}
