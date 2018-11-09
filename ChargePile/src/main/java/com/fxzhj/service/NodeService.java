package com.fxzhj.service;

import java.util.List;

import com.fxzhj.model.NodeGroup;
import com.fxzhj.model.Node;

public interface NodeService {

	//查询一级节点
	List<Node> queryNodeTree();

	//查询子节点
	List<Node> queryChildTree(String parentId);

	//根据节点id查询节点信息
	List<Node> queryByNid(String nodeId);

	//节点分组类型
	List<NodeGroup> showCombobox();

	//根据节点id修改节点数据
	void updateNode(String nodeId, Long groupId);

}
