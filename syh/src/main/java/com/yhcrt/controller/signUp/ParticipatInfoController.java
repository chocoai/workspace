package com.yhcrt.controller.signUp;

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
import com.yhcrt.entity.signUp.ParticipatDetail;
import com.yhcrt.entity.signUp.ParticipatInfo;
import com.yhcrt.entity.signUp.UnitInfo;
import com.yhcrt.service.project.ClassManagerService;
import com.yhcrt.service.project.ProjectManagerService;
import com.yhcrt.service.signUp.ParticipatDetailService;
import com.yhcrt.service.signUp.ParticipatInfoService;
import com.yhcrt.service.signUp.UnitInfoService;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.StringUtils;
import com.yhcrt.utils.page.PageBean;

/**
 * 
 * TODO
 * @author 陈伟
 * 2017年7月11日 下午2:09:43
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/participatInfo")
public class ParticipatInfoController extends BaseController{
	
	@Resource(name="unitInfoService")
	private UnitInfoService unitInfoService;
	@Resource(name="classManagerService")
	private ClassManagerService classManagerService;
	@Resource(name="projectManagerService")
	private ProjectManagerService projectManagerService;
	@Resource(name="participatInfoService")
	private ParticipatInfoService participatInfoService;
	@Resource(name="participatDetailService")
	private ParticipatDetailService participatDetailService;
	/**
	 * 查询运动员参赛列表
	 * @param 
	 * @return 
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView listAthleteParticipat(Integer pCid){
		model.clear();
		model.put("pCid", pCid);
		return new ModelAndView("participatInfo/index",model);
	}
	/**
	 * AJAX查询运动员参赛列表分页显示
	 * @param 
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(Integer currentPage,Integer pCid,String param){
		model.clear();
		model.put("pCid", pCid);
		model.put("param", param);
		try {
			 PageHelper.startPage(currentPage, pageSize);
			 List<ParticipatInfo> list = participatInfoService.quertByParam(model);
			 PageBean<ParticipatInfo> pageInfo=new PageBean<ParticipatInfo>(list);
			 model.put("pageInfo",pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 查询参赛详情/运动员信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/queryDetail")
	@ResponseBody
	public Map<String,Object> queryDetail(Integer cid){
		model.clear();
		if(cid==null){
			model.put("state", "nodata");
		}else{
			try {
				List<ParticipatDetail> list = participatDetailService.queryDetail(cid);
				model.put("list", list);
				model.put("state", "success");
			} catch (Exception e) {
				model.put("state", "failed");
				e.printStackTrace();
			}
		}
		return model;
	}
	
	/**
	 * @Title: updateInfo
	 * @Description: 获取参赛单位信息
	 * @return: ModelAndView
	 */
	@RequestMapping("/updateInfo")
	public ModelAndView get(HttpServletRequest request,Integer cid){
		model.clear();
		ParticipatInfo info = null;
		try {
			if(cid != null){
				info = participatInfoService.getById(cid);
			}else {
				info = new ParticipatInfo();
				String pCid = request.getParameter("projectCid");
				if(!StringUtils.isBlank(pCid)){
					ProjectManager bean = projectManagerService.queryProjectByCid(Integer.parseInt(pCid));
					info.setProjectManager(bean);
				}
			}
			Integer classCid = info.getProjectManager().getpCid();
			ClassManager classManager =classManagerService.searchObjectByCid(classCid);
			info.setBackup(classManager.getBackup());
			
			 model.put("info", info);
			 
			 //获取单位
			 List<UnitInfo> unitList = unitInfoService.quertByUnitContent(null);
			 model.put("list", unitList);
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView("participatInfo/addInfo", model);
	}
	/**
	 * 删除参赛信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/deleteInfo")
	@ResponseBody
	public Map<String, Object> deleteInfo(@RequestParam Integer[] cids){
		model.clear(); 
		if (cids==null||cids.length==0) {
			model.put("state", "nodata");
		}else {
			try {
				participatDetailService.deleteByCids(cids);
				model.put("state", "success");
			} catch (Exception e) {
				model.put("state", "failed");
				e.printStackTrace();
			}
		}
		return model;
	}
	/**
	 * 
	 * @Title: isVilidataAthlete
	 * @Description: 验证单个运动员
	 * @return: String
	 */
	@RequestMapping("/isVilidataAthlete")
	@ResponseBody
	public Map<String, Object> isVilidataAthlete(Integer cid,Integer projectCid,Integer isTeam,Integer athleteCid){
		model.clear(); 
		try {
			if(athleteCid==null){
				model.put("states", "noathlete");
			}else{
				model = participatDetailService.isVilidataAthlete(cid,projectCid, isTeam, athleteCid);
			}
		} catch (Exception e) {
			model.put("states", "failed");
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 
	 * @Title: isVilidataAthlete
	 * @Description: 验证多个运动员综合限制
	 * @return: String
	 */
	@RequestMapping("/isVilidataTeam")
	@ResponseBody
	public Map<String, Object> isVilidataTeam(Integer cid,Integer projectCid,Integer[] athleteCids){
		model.clear(); 
		try {
			if(athleteCids==null||athleteCids.length==0){
				model.put("states", "noathlete");
			}else{
				model = participatDetailService.isVilidataTeam(cid, projectCid, athleteCids);
			}
		} catch (Exception e) {
			model.put("states", "failed");
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 
	 * @Title: doAddInfo
	 * @Description: 新增/编辑数据保存
	 * @return: String
	 */
	@RequestMapping("/saveToUpdate")
	@ResponseBody
	public String saveToUpdate(Integer isperUnit,Integer projectCid,Integer[] unitCids ,Integer isTeam,Integer[] athleteCids,Integer cid){
		String result="";
		if(unitCids==null || unitCids.length==0){
			result = "nounit";
		}else if(athleteCids==null || athleteCids.length==0){
			result = "noathlete";
		}else{
			try {
				if(cid!=null){
					participatDetailService.updateInfo(isperUnit,unitCids,athleteCids,cid);
				}else{
					ParticipatInfo info = new ParticipatInfo();
					info.setPid(projectCid);
					info.setIsperUnit(0);
					if(unitCids.length==1){
						info.setIscombinationTeam(Constants.Middle);
					}else{
						info.setIscombinationTeam(Constants.Middle_1);
					}
					info.setIsperUnit(isperUnit);
					info.setIsindividual(isTeam);
					info.setCreaTime(DateUtil.getTime());
					info.setCreaRen(getUserCode());
					
					participatDetailService.saveInfo(info ,athleteCids);
				}
				result="success";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
}
