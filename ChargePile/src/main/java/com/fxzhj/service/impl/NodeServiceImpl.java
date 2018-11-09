package com.fxzhj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxzhj.mapper.NodeGroupMapper;
import com.fxzhj.mapper.NodeMapper;
import com.fxzhj.model.NodeGroup;
import com.fxzhj.model.Node;
import com.fxzhj.service.NodeService;

@Service
public class NodeServiceImpl implements NodeService {

	@Autowired
	private NodeMapper NMapper;
	
	@Autowired
	private NodeGroupMapper CGMapper;

	//查询一级节点
	@Override
	public List<Node> queryNodeTree() {
		return NMapper.queryNodeTree();
	}

	//查询子节点
	@Override
	public List<Node> queryChildTree(String parentId) {
		return NMapper.queryChildTree(parentId);
	}

	//根据节点id查询节点信息
	@Override
	public List<Node> queryByNid(String nodeId) {
		return NMapper.queryByNid(nodeId);
	}

	//节点分组类型
	@Override
	public List<NodeGroup> showCombobox() {
		return CGMapper.showCombobox();
	}

	//根据节点id修改节点数据
	@Override
	public void updateNode(String nodeId, Long groupId) {
		NMapper.updateNode(nodeId, groupId);
	}

}
