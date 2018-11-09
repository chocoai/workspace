package com.yhcrt.controller.project;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.project.ClassManager;
import com.yhcrt.entity.project.ProjectManager;
import com.yhcrt.service.CommonService;
import com.yhcrt.service.project.ProjectManagerService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.ConstantsLog;
import com.yhcrt.utils.ReflectionUtil;
import com.yhcrt.utils.StringUtils;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONObject;

/**
 * 比赛项目管理的控制层
 * @author heyun
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/projectManager")
public class ProjectManagerController extends BaseController{
	
	@Resource
	private CommonService commonService;
	@Resource(name="projectManagerService")
	private ProjectManagerService projectManagerService;
	
	/**
	 * 查询比赛项目
	 * @param projectManager
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/index")
	public ModelAndView listProjectManager(Integer pCid,String pName){
		model.clear();
		model.put("pCid", pCid);
		model.put("pName", pName);
		return new ModelAndView("project/projectManagerList",model);
	}
	
	/**
	 * 查询比赛项目列表分页显示
	 * @param jsonString
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(Integer currentPage,Integer pCid){
		model.clear();
		try {
			 PageHelper.startPage(currentPage, getPageSize());
			 if(pCid == null){
				pCid = TokenManager.getErsionNum(); 
			 }
			 List<ProjectManager> list = projectManagerService.queryList(pCid);
			 PageBean<ProjectManager> pageInfo=new PageBean<ProjectManager>(list);
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
		ProjectManager projectManager = null;
		try {
			if(cid != null){
				projectManager = projectManagerService.searchObjectByCid(cid);
			}else {
				projectManager = new ProjectManager();
				String pCid = request.getParameter("pCid");
				if(!StringUtils.isBlank(pCid)){
					projectManager.setpCid(Integer.parseInt(pCid));
					
					ClassManager classManager = new ClassManager();
					classManager.setCid(Integer.parseInt(pCid));
					classManager.setClassifyName(request.getParameter("projectName"));
					
					projectManager.setClassManager(classManager);
				}
			}
			 model.put("projectManager", projectManager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("project/addProjectManager" ,model);
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
			JSONObject projectManagerJson= JSONObject.fromObject(jsonString);
			ProjectManager projectManager= (ProjectManager) JSONObject.toBean(projectManagerJson,ProjectManager.class);
			Integer cid = projectManager.getCid();
			try {
				if(cid==null){
					result= projectManagerService.insertProjectManager(projectManager);
					sysStsyemLogService.saveSelective(ConstantsLog.PROJECT_MANAGER_SAVE , projectManager.getProjectName(), ConstantsLog.LOG_0);
				}else{
					ProjectManager bean = projectManagerService.searchObjectByCid(cid);
					Integer pCid = bean.getpCid();
					
					ReflectionUtil.bean2Bean(bean,projectManager,"creaRen,creaTime");
					if(!pCid.equals(projectManager.getpCid())){
						ClassManager classManager = commonService.getByClassCid(projectManager.getpCid(),Constants.Middle_2);
						bean.setProjectType(classManager.getCid());	
					}
					
					result= projectManagerService.updateProjectManager(bean);
					sysStsyemLogService.saveSelective(ConstantsLog.PROJECT_MANAGER_EDIT, projectManager.getProjectName(), ConstantsLog.LOG_0);
				}
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	};
	
	/**
	 * 删除比赛项目
	 * @param cid
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> deleteProjectManager(Integer cid){
		model.clear();
		if(cid==null){
			model.put("status", "nodata");
			return model;
		}
		try {
			ProjectManager projectManager = projectManagerService.searchObjectByCid(cid);
			model =  projectManagerService.deleteProjectManager(cid);
			
			sysStsyemLogService.saveSelective(ConstantsLog.PROJECT_MANAGER_DELETE, projectManager.getProjectName(), ConstantsLog.LOG_0);
		} catch (Exception e) {
			e.printStackTrace();
			model.put("status", "failed");
		}
		return model;
	}
	/**
	 * 新增时根据pcid以及名称去查询是否重复输入
	 * @param cid
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getObjByNameAndPCid")
	public String getObjByNameAndPCid(String name,String pCid){
		String str = projectManagerService.getObjByNameAndPCid(name,pCid);
		return str;
	}
}
