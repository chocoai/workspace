package com.yhcrt.controller.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.Tree;
import com.yhcrt.entity.project.ClassManager;
import com.yhcrt.service.project.ClassManagerService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.ConstantsLog;
import com.yhcrt.utils.ReflectionUtil;
import com.yhcrt.utils.StringUtils;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ClassManagerController.java
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/classManager")
public class ClassManagerController extends BaseController{
	@Autowired
	private ClassManagerService classManagerService;
	
	/**
	 * 查询项目分类列表
	 * @param 
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView listClassManager(Integer pCid){
		model.clear();
		model.put("pCid", pCid);
		return new ModelAndView("project/classManagerList",model);
	}
	/**
	 * 查询项目分类列表分页显示
	 * @param jsonString
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(Integer currentPage,Integer classCid){
		model.clear();
		try {
			 if(classCid == null){
				 classCid = TokenManager.getErsionNum(); 
			 }
			 PageHelper.startPage(currentPage, getPageSize());
			 List<ClassManager> list = classManagerService.queryList(classCid);
			 PageBean<ClassManager> pageInfo=new PageBean<ClassManager>(list);
			 model.put("pageInfo",pageInfo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * @Title: addToEdit
	 * @Description: 新增/更新跳转页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/updateInfo")
	public ModelAndView addToEdit(HttpServletRequest request,Integer cid){
		model.clear();
		ClassManager classManager= null;
		try {
			if(cid != null){
				classManager = classManagerService.searchObjectByCid(cid);
			}else {
				classManager = new ClassManager();
				String classCid = request.getParameter("classCid");
				if(!StringUtils.isBlank(classCid)){
					classManager.setClassCid(Integer.parseInt(classCid));
					ClassManager bean = classManagerService.searchObjectByCid(Integer.parseInt(classCid));
					classManager.setManager(bean);
				}
			}
			 model.put("classManager", classManager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("project/addClassManager" ,model);
	}
	/**
	 * 
	 * @Title: saveToUpdate
	 * @Description: 新增/更新数据
	 * @return: String
	 */
	@RequestMapping("/saveToUpdate")
	@ResponseBody
	public String saveToUpdate(HttpServletRequest request,@RequestParam String jsonString){
		String result="";
		if (jsonString==null||jsonString.equals("")) {
			result="nodata";
		}else {
			JSONObject classManagerJson= JSONObject.fromObject(jsonString);
			ClassManager classManager= (ClassManager) JSONObject.toBean(classManagerJson,ClassManager.class);
			Integer cid = classManager.getCid();
			try {
				if(cid==null){
					result= classManagerService.insertClassManager(classManager);
					sysStsyemLogService.saveSelective(ConstantsLog.CLASS_MANAGER_SAVE, classManager.getClassifyName(), ConstantsLog.LOG_0);
				}else{
					ClassManager bean = classManagerService.searchObjectByCid(cid);
					ReflectionUtil.bean2Bean(bean,classManager,"creaRen,creaTime");
					result= classManagerService.updateClassManager(bean);
					sysStsyemLogService.saveSelective(ConstantsLog.CLASS_MANAGER_EDIT, classManager.getClassifyName(), ConstantsLog.LOG_0);
				}
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	};
	
	/**
	 * 删除项目分类
	 * @param cid
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> deleteClassManager(Integer cid){
		model.clear();
		if(cid==null){
			model.put("status", "nodata");
			return model;
		}
		try {
			ClassManager bean = classManagerService.searchObjectByCid(cid);
			model = classManagerService.deleteClassManager(cid);
			sysStsyemLogService.saveSelective(ConstantsLog.CLASS_MANAGER_DELETE, bean.getClassifyName(), ConstantsLog.LOG_0);
		} catch (Exception e) {
			e.printStackTrace();
			model.put("status", "failed");
		}
		return model;
	}
	
	/**
	 * 获取分类树结构
	 * @return
	 */
	@RequestMapping("/queryClassToTree")
	@ResponseBody
	public JSONArray queryClassToTree(){
		List<Tree> trees=new ArrayList<Tree>();
		List<ClassManager> classList= classManagerService.queryClassToTree();
		for (ClassManager classManager : classList) {
			Tree tree=new Tree();
			tree.setId(classManager.getCid());
			tree.setParentId(classManager.getClassCid());
			tree.setNodeValue(classManager.getClassifyName());
			tree.setClassType(classManager.getClassType());
			trees.add(tree);
		}
		JSONArray json = JSONArray.fromObject( trees );
		return json;
	}
	
	/**
	 * 根据项目分类cid查询该项目
	 * @param cid
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/searchObjectByCid")
	public ClassManager searchObjectByCid(String cid){
		return classManagerService.searchObjectByCid(Integer.valueOf(cid));
	}
	/**
	 * 新增时根据上级CID以及分类名称去查询是否重复输入
	 * @param cid
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getObjByNameAndClassCid")
	public String getObjByNameAndClassCid(String classifyName,String classCid){
		String str = classManagerService.getObjByNameAndClassCid(classifyName,classCid);
		return str;
	}
	/**
	 * 选择节点时判断是否为末级分类节点
	 * @param classCidNode
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/queryIsNode")
	public Map<String, Object> queryIsNode(Integer classCidNode){
		return classManagerService.queryIsNode(classCidNode);
	}
}
