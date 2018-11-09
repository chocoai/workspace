package com.fxzhj.mapper;

import com.fxzhj.model.NodeCode;

public interface NodeCodeMapper {

	//新增节点路径绑定二维码信息表
	void addNodeCode(NodeCode nodeCode);

	//根据二维码id删除二维码绑定节点信息
	void deleteByCid(Long Cid);

	//批量删除节点绑定二维码信息
	void batchDelete(String[] ids);

	//批量模糊匹配
	int queryBatchByNid(String[] ids);

	//根据扩展字段查询商城的二维码是否已经绑定
	String queryByFiled2(String storeUrl);

}