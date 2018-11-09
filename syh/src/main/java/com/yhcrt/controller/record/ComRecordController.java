package com.yhcrt.controller.record;

import java.util.HashMap;
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
import com.yhcrt.entity.project.ProjectManager;
import com.yhcrt.entity.record.ComRecord;
import com.yhcrt.service.CommonService;
import com.yhcrt.service.project.ClassManagerService;
import com.yhcrt.service.project.ProjectManagerService;
import com.yhcrt.service.record.ComRecordService;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONObject;

/**
 * 竞赛项目记录库的控制层
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/comRecord")
public class ComRecordController extends BaseController{
	@Resource(name="comRecordService")
	private ComRecordService comRecordService;
	@Resource
	private ClassManagerService classManagerService;
	@Resource
	private ProjectManagerService projectManagerService;
	@Resource
	private CommonService commonService;
	
	/**
	 * 进入主页面
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Integer pCid){
		model.clear();
		model.put("pCid", pCid);
		return new ModelAndView("comRecord/index",model);
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping("/addInfo")
	public ModelAndView addInfo(Integer projectCid,String projectName){
		model.clear();
		model.put("projectCid", projectCid);
		model.put("projectName", projectName);
		return new ModelAndView("comRecord/addInfo",model);
	}
	
	/**
	 * 修改页面
	 * @return
	 */
	@RequestMapping("/updateInfo")
	public ModelAndView updateInfo(@RequestParam Integer cid){
		model.clear();
		Map<String, Object> model=new HashMap<String, Object>();
		try {
			ComRecord comRecord=comRecordService.queryByCid(cid);
			model.put("comRecord", comRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("comRecord/updateInfo",model);
	}
	
	/**
	 * AJAX分页查询
	 * @param jsonString
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(Integer currentPage,@RequestParam String jsonString){
		model.clear();
		Map<String,Object> model = new HashMap<String, Object>();
		JSONObject comRecordJson= JSONObject.fromObject(jsonString);
		ComRecord comRecord= (ComRecord) JSONObject.toBean(comRecordJson,ComRecord.class);
		try {
			 PageHelper.startPage(currentPage, 10);
			 comRecord.setVersionNum(0);//获取有效版本 
			 List<ComRecord> list=comRecordService.queryAll(comRecord);
			 for (ComRecord comRecord2 : list) {  //拼接比赛项目名称
				 String pName = "";
				 pName = commonService.getFullName(comRecord2.getProjectManager().getpCid(), Constants.Middle_2,pName);
				 String projectName=comRecord2.getProjectManager().getProjectName();
				 comRecord2.getProjectManager().setProjectName(projectName+"("+pName+")");
			 }
			 PageBean<ComRecord> pageInfo=new PageBean<ComRecord>(list);
			 model.put("pageInfo", pageInfo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return model;
	}
	
	
	
	/**
	 * 获取项目信息
	 * @param cid
	 * @return
	 */
	@RequestMapping("/getProjectInfo")
	@ResponseBody
	public Map<String,Object> getProjectInfo(@RequestParam Integer cid){
		model.clear();
		Map<String,Object> model = new HashMap<String, Object>();
		ProjectManager projectManager=new ProjectManager();
		projectManager=projectManagerService.queryProjectByCid(cid);
		model.put("projectManager",projectManager);
		boolean isOnly=true;
		try {
			isOnly=comRecordService.queryCountByProjectCid(cid)==0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("isOnly", isOnly);
		return model;
	}
	
	/**
	 * 处理新增记录
	 * @param cid
	 * @return
	 */
	@RequestMapping("/doAddInfo")
	@ResponseBody
	public String doAddInfo(@RequestParam String jsonString){
		String result="";
		if (jsonString==null||jsonString.equals("")) {
			result="nodata";
		}else {
			JSONObject comrecordJson=JSONObject.fromObject(jsonString);
			ComRecord comRecord=(ComRecord) comrecordJson.toBean(comrecordJson, ComRecord.class);
			comRecord.setCreaRen(getUserCode());//设置操作人
			comRecord.setCreaTime(DateUtil.getTime());//设置操作时间
			try {
				comRecordService.insertNewInfo(comRecord);
				result="success";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	};
	
	/**
	 * 处理刷新记录
	 * @param cid
	 * @return
	 */
	@RequestMapping("/refreshInfo")
	@ResponseBody
	public String refreshInfo(HttpServletRequest request,@RequestParam String jsonString){
		String result="";
		JSONObject comrecordJson=JSONObject.fromObject(jsonString);
		ComRecord newComRecord=(ComRecord) comrecordJson.toBean(comrecordJson, ComRecord.class);
		try {
			comRecordService.updateOldInfo(newComRecord);
			result="success";
		} catch (Exception e) {
			result="failed";
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 记录执行删除
	 * @param cids
	 * @return
	 */
	@RequestMapping("/deleteInfo")
	@ResponseBody
	public String deleteInfo(@RequestParam Integer[] cids){
		String result="";
		if (cids==null||cids.length==0) {
			result="nodata";
		}else {
			try {
				comRecordService.deleteInfoByCid(cids);
				result="success";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 处理更新操作
	 * @param cids
	 * @return
	 */
	@RequestMapping("/doUpdateInfo")
	@ResponseBody
	public String doUpdateInfo(@RequestParam String jsonString){
		String result="";
		JSONObject comrecordJson=JSONObject.fromObject(jsonString);
		ComRecord comRecord=(ComRecord) comrecordJson.toBean(comrecordJson, ComRecord.class);
		try {
			comRecordService.updateInfo(comRecord);
			result="success";
		} catch (Exception e) {
			result="failed";
			e.printStackTrace();
		}
		return result;
	}
}
