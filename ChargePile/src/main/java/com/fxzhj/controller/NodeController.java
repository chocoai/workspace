package com.fxzhj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxzhj.model.NodeGroup;
import com.fxzhj.model.Node;
import com.fxzhj.service.NodeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.util.StringUtil;

/**
 * 节点tree
 * @author jifei
 *
 */
@Controller
@RequestMapping(value = "/node")
public class NodeController {
	
	@Autowired
	private NodeService nodeService;
	
	/**
	 * 查询tree
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "nodeTree")
	@ResponseBody
	public List<Map<String, Object>> nodeTree(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id","");
		root.put("text","全部");
		List<Map<String, Object>> childs = insertTree();
		if(childs != null && childs.size()>0){
			root.put("state", "closed");
			root.put("children", childs);
        }
		root.put("attributes", null);
		result.add(root);
		return result;
	}

	//查询一级节点
	private List<Map<String, Object>> insertTree() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();  
		List<Node> nodeList=nodeService.queryNodeTree();
		if(nodeList!=null && nodeList.size()>0){
			for(Node bean:nodeList){
				Map<String, Object> nodeMap = new HashMap<String, Object>();
				nodeMap.put("id", bean.getNodeId());  
				nodeMap.put("text", bean.getNodeName());
				//返回的是childs
		        List<Map<String, Object>> childs = insertChildTree(bean.getNodeId(),bean.getNodeId());
		        if(childs != null && childs.size()>0){
		        	nodeMap.put("state", "closed");
		        	nodeMap.put("children", childs);
		        }
		        nodeMap.put("attributes", bean.getNodeId());
		        result.add(nodeMap);
			}
		}
		return result;
	}
	
	//查询子节点
	private List<Map<String, Object>> insertChildTree(String parentId ,String attr) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Node> nodeList=nodeService.queryChildTree(parentId);
		if(nodeList!=null && nodeList.size()>0){
			for(Node bean:nodeList){
				Map<String, Object> nodeMap = new HashMap<String, Object>();
				nodeMap.put("id", bean.getNodeId());  
				nodeMap.put("text", bean.getNodeName());
				//返回的是childs
				String attrs = attr+"->"+bean.getNodeId();
				if(bean.getIsNode() == 0){
					nodeMap.put("state", "closed");
					List<Map<String, Object>> childs = insertChildTree(bean.getNodeId(),attrs);
					 if(childs != null && childs.size()>0){
				        nodeMap.put("children", childs);
					 }
				}
		        nodeMap.put("attributes", attrs);
		        result.add(nodeMap);
			}
		}
		return result;
	}
	
	/**
	 * 根据节点id查询节点信息
	 * @param nodeId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryByNid")
	@ResponseBody
	public Map<String, Object> queryByNid(String nodeId, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(request.getParameter("page")) && StringUtil.isNotEmpty(request.getParameter("rows"))){
			int pageNo = Integer.parseInt((String) request.getParameter("page"));
			// 当前检索的条数
			int pageSize = Integer.parseInt((String) request.getParameter("rows"));
			PageHelper.startPage(pageNo, pageSize);
		}
		if(StringUtil.isNotEmpty(nodeId)){
			List<Node> list =nodeService.queryByNid(nodeId);
			PageInfo<Node> pageList = null;
			if(list!=null&&list.size()>0){
				pageList = new PageInfo<Node>(list);
			}
			
			if (pageList != null && pageList.getList() != null && pageList.getList().size()>0) {
				map.put("total", pageList.getTotal());
				map.put("rows", pageList.getList());
			}else{
				map.put("total", 0);
				map.put("rows", new ArrayList<Node>());
			}
		} else {
			map.put("total", 0);
			map.put("rows", new ArrayList<Node>());
		}
		
		return map;
	}
	
	/**
	 * 节点分组类型
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public List<NodeGroup> queryAll(){
		NodeGroup group = new NodeGroup();
		group.setGroupName("--请选择--");
		List<NodeGroup> list = nodeService.showCombobox();
		list.add(0, group);
		return list;
	}

	/**
	 * 根据节点id修改节点数据
	 * @param nodeId
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "/updateNode")
	@ResponseBody
	public Map<String, Object> updateNode(String nodeId, String groupId){
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(nodeId) && StringUtil.isNotEmpty(groupId)){
			try {
				nodeService.updateNode(nodeId,Long.parseLong(groupId));
				map.put("state", "SUCCESS");
			} catch (Exception e) {
				map.put("state", "ERROR");
			}
		} else {
			map.put("state", "FALSE");
		}
		return map;
	}
}
