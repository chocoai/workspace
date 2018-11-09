package com.smart.web.action.res;

import java.util.ArrayList;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.smart.model.Res;
import com.smart.service.ResService;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class ResAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ResService resService;

	private String id;

	private Integer roleId;

	private String resIdList;

	// @Action("list")
	// public String list() {
	// List<Res> list = resService.getList(roleId); //用于显示打勾的权限
	// put("list", list);
	// return "list";
	// }

	@Action("list")
	public String list() {
		List<Res> resList = resService.getList(); // 所有权限
		List<?> treelist = this.getTree("0", resList);
		JSONArray jsong = JSONArray.fromObject(treelist);
		put("jsong", jsong);
		List<Res> list = resService.getList(roleId);
		put("list", list);

		return "list";
	}

	public List<?> getTree(String pid, List<Res> reslist) {
		List<JSONObject> resultlist = new ArrayList<JSONObject>();
		// 当前级菜单集合
		List<?> list = this.getChildrens(reslist, pid);
		for (int i = 0; i < list.size(); i++) {
			Res pojo = (Res) list.get(i);
			reslist.remove(pojo);
			JSONObject umenu = new JSONObject();
			umenu.put("id", pojo.getId());
			umenu.put("pid", pojo.getPid());
			umenu.put("name", pojo.getName());
			umenu.put("url", pojo.getUrl());
			// 子菜单
			// List<?> children = this.getChildrens(reslist, pojo.getId());
			umenu.put("children", getTree(pojo.getId(), reslist));
			resultlist.add(umenu);
		}
		return resultlist;
	}

	public List<Res> getChildrens(List<Res> menus, String pid) {// 主要是加载下级节点
		List<Res> resultList = new ArrayList<Res>();
		Res pojo = null;
		for (Object obj : menus) {
			pojo = (Res) obj;
			String parentid = pojo.getPid();// 获取节点的父id
			if (parentid.equals(pid)) {// 父节点id
				resultList.add(pojo);// 加载下级节点
			}
		}
		return resultList;
	}

	// @Action(value = "save", results = { @Result(name = "list", location =
	// "list", type = "redirectAction", params={"roleId", "${roleId}"}) })
	// public String save() {
	// resService.save(roleId, resIdList);
	// return "list";
	// }

	// @Action(value = "save", results = { @Result(name = "workbench", location
	// = "/WEB-INF/content/index/workbench.ftl") })
	@Action(value = "save", results = {
			@Result(name = "roleList", location = "../role/list", type = "redirectAction") })
	public String save() {
		resService.save(roleId, resIdList);
		return "roleList";
	}

	@Action("show")
	public String show() {
		List<Res> resList = resService.getList(); // 所有权限
		List<?> treelist = this.getTree("0", resList);
		JSONArray jsong = JSONArray.fromObject(treelist);
		put("jsong", jsong);
		return "show";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getResIdList() {
		return resIdList;
	}

	public void setResIdList(String resIdList) {
		this.resIdList = resIdList;
	}

}
