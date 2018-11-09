package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Node;

public interface NodeMapper {

	//查询一级节点
	List<Node> queryNodeTree();

	//查询子节点
	List<Node> queryChildTree(String parentId);

	//查询节点名称
	String queryNodeName(String nodeId);

	//根据节点id查询节点信息
	List<Node> queryByNid(String nodeId);

	//根据节点id修改节点数据
	void updateNode(String nodeId, Long groupId);

	//批量新增节点信息
	void batchNode(List<Node> list);

	//根据设备id修改节点信息
	void updateByDid(Long id, Integer status);

	//根据端口id修改节点信息
	void updateByPid(Long id, Integer status);

	//新增节点信息
	void addNode(Node node);

	//根据nodeId修改节点名称中的节点
	void updateByNid(String nodeId,String name);

	//批量删除节点信息
	void batchDelete(String[] ids);
	
}