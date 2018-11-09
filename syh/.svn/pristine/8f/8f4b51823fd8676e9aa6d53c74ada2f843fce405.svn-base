package com.yhcrt.controller.pubRewinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.rewardAndPunishment.PubRewInfo;
import com.yhcrt.entity.signUp.ParticipatDetail;
import com.yhcrt.entity.signUp.ParticipatInfo;
import com.yhcrt.entity.signUp.UnitInfo;
import com.yhcrt.service.project.ClassManagerService;
import com.yhcrt.service.rewardAndPunishment.PubRewInfoService;
import com.yhcrt.service.signUp.ParticipatDetailService;
import com.yhcrt.service.signUp.ParticipatInfoService;
import com.yhcrt.service.signUp.UnitInfoService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.GetPinyin;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 奖惩信息的控制层
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/pubRewInfo")
public class PubRewInfoController extends BaseController{
	@Resource(name="pubRewInfoService")
	private PubRewInfoService pubRewInfoService;
	@Resource
	private UnitInfoService unitInfoService;
	@Resource
	private ParticipatInfoService participatInfoService;
	@Resource
	private ParticipatDetailService participatDetailService;
	@Resource
	private ClassManagerService classManagerService;
	
	/**
	 * 进入奖惩主页面
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView getIndex(){
		return new ModelAndView("pubRewInfo/index");
	}
	
	/**
	 * AJAX分页查询
	 * @param jsonString
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(Integer currentPage,String unitName,String projectName){
		model.clear();
		//获得查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("unitName", unitName);
		paramMap.put("projectName", projectName);
		paramMap.put("ersionNum", TokenManager.getErsionNum());
		try {
			 PageHelper.startPage(currentPage, pageSize);
			 List<PubRewInfo> list=pubRewInfoService.queryByProperties(paramMap);
			 PageBean<PubRewInfo> pageInfo=new PageBean<PubRewInfo>(list);
			 model.put("pageInfo", pageInfo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * 批量删除奖惩信息
	 * @param cids
	 * @return
	 */
	@RequestMapping("/deleteInfo")
	@ResponseBody
	public String deleteInfo(@RequestParam Integer[] cids){
		String result="";
		if (cids==null||cids.length==0) {//当前台传过来的数组为空时
			result="nodata";
		}else {
			try {
				pubRewInfoService.deleteInfoByCid(cids);  //调用service执行删除操作
				result="success";		//删除成功后返回成功提示
			} catch (Exception e) {
				result="failed";		//出现异常，返回失败提示
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 进入新增奖励页面
	 * @return
	 */
	@RequestMapping("/addRew")
	public ModelAndView addRews(){
		model.clear();
		return new ModelAndView("pubRewInfo/addRew",model);
	}
	
	/**
	 * 进入新增惩罚页面
	 * @return
	 */
	@RequestMapping("/addPunish")
	public ModelAndView addPunish(){
		model.clear();
		return new ModelAndView("pubRewInfo/addPunish",model);
	}
	
	/**
	 * 处理新增奖励信息
	 * @param request
	 * @param jsonString
	 * @return
	 */
	@RequestMapping("/doAddRewInfo")
	@ResponseBody
	public String doAddRew(HttpServletRequest request, @RequestParam String jsonString){
		String result="";
		if (jsonString==null||jsonString.equals("")) { 
			result="nodata";
		}else{
			//得到前台传过来的奖惩对象
			JSONObject pubRewInfoJson=JSONObject.fromObject(jsonString);
			PubRewInfo pubRewInfo=(PubRewInfo) pubRewInfoJson.toBean(pubRewInfoJson,PubRewInfo.class);
			pubRewInfo.setCreaRen(getUserCode());   //设置操作人
			pubRewInfo.setCreaTime(DateUtil.getTime());	//操作时间
			try {
				pubRewInfoService.insertRewInfo(pubRewInfo);
				result="success";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 处理新增惩罚信息
	 * @param request
	 * @param jsonString
	 * @return
	 */
	@RequestMapping("/doAddPunishInfo")
	@ResponseBody
	public String doAddPunish(HttpServletRequest request, @RequestParam String jsonString){
		String result="";
		if (jsonString==null||jsonString.equals("")) { 
			result="nodata";
		}else{
			try{
				JSONObject pubRewInfoJson=JSONObject.fromObject(jsonString);
				Integer participatId = pubRewInfoJson.getInt("participatId");
				boolean isMedal = pubRewInfoJson.getBoolean("isMedal");//是否取消奖牌
				boolean isIntrgral = pubRewInfoJson.getBoolean("isIntrgral");//是否取消分数
				boolean isScores = pubRewInfoJson.getBoolean("isScores");//是否取消成绩
				boolean isRanking = pubRewInfoJson.getBoolean("isRanking");//是否取消名次
				ParticipatInfo participatInfo = participatInfoService.getById(participatId);
				List<ParticipatDetail> participatDetailList = participatInfo.getParticipatDetails();
				Set<Integer> units = new HashSet<Integer>();
				Integer RANKING = null;
				for(ParticipatDetail participatDetail : participatDetailList){
					if(RANKING==null){
						RANKING = participatDetail.getRanking();
					}
					units.add(participatDetail.getAthleteBaseInfo().getUnitCid());
				}
			
				for(ParticipatDetail participatDetail : participatDetailList){
					PubRewInfo pubRewInfo = new PubRewInfo();
					pubRewInfo.setCreaRen(getUserCode());   //设置操作人
					pubRewInfo.setCreaTime(DateUtil.getTime());	//操作时间
					pubRewInfo.setInfoType(1);
					pubRewInfo.setParticipatId(participatInfo.getCid());
					pubRewInfo.setProjectCid(participatInfo.getPid());
					pubRewInfo.setCompanyCid(participatDetail.getAthleteBaseInfo().getUnitCid());
					pubRewInfo.setBackup(pubRewInfoJson.getString("backup"));
					pubRewInfo.setReason(pubRewInfoJson.getString("reason"));
					if(isMedal){   //取消奖牌
						pubRewInfo.setMedalType(participatDetail.getMedal());
						participatDetail.setMedal(null);
						pubRewInfo.setMedalCount(participatDetail.getMedalNum());
						participatDetail.setMedalNum(null);
					}
					if(isIntrgral){  //取消积分
						pubRewInfo.setIntrgralCount(participatDetail.getIntrgral());
						participatDetail.setIntrgral(null);
					}
					if(isScores){  //取消成绩
						pubRewInfo.setScoresCount(participatDetail.getScores());
						participatDetail.setScores(null);
					}
					if(isRanking){ //取消排名
						pubRewInfo.setRankingCount(participatDetail.getRanking());
						participatDetail.setRanking(null);
					}
					participatDetailService.updateByPunishSelective(participatDetail);  //先操作
					if(units.contains(participatDetail.getAthleteBaseInfo().getUnitCid())){
						pubRewInfoService.insertPunishInfo(pubRewInfo);
						units.remove(participatDetail.getAthleteBaseInfo().getUnitCid());
					}
				}
				//惩罚后，下面的成员名次和积分往上移动
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("isMedal", isMedal);
				paramMap.put("isIntrgral", isIntrgral);
				paramMap.put("isRanking", isRanking);
				paramMap.put("projectId", participatInfo.getPid());
				paramMap.put("ranking", RANKING);
				participatDetailService.updateByPunishForOther(paramMap);  //后操作
				result = "success";
			}catch(Exception e){
				result = "failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 获取公司信息
	 * @param str
	 * @return
	 */
	@RequestMapping("/getUnit")
	@ResponseBody
	public JSONArray getUnit(@RequestParam String str){
		List<UnitInfo> list=new ArrayList<UnitInfo>();
		if (!str.equals("")&&str!=null) {
			try {
				model.put("ersionNum", TokenManager.getErsionNum());
				if (GetPinyin.isChinese(str)) {//如果输入的是汉字
					model.put("unitName", str);
					list=unitInfoService.quertByUnitName(model); //通过单位名称获取  
				}else{//如果输入的是首字母
					model.put("unitAbbreviation", str);
					list=unitInfoService.quertByAbbreviation(model);//通过单位简称获取
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONArray json = JSONArray.fromObject( list );
		return json;
	}
	
	/**
	 * 查询报名信息选择下拉框
	 * @param projectCid
	 * @param unitCid
	 * @return
	 */
	@RequestMapping("/getParticipatInfo")
	@ResponseBody
	public Map<String, Object> getParticipatInfo(Integer projectCid,Integer unitCid){
		model.clear();
		//获得查询条件
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("projectCid", projectCid);
		paramMap.put("unitCid", unitCid);
		try {
			 List<Map<String, Object>> list=pubRewInfoService.getParticipatInfo(paramMap);
			 model.put("participatInfo", list);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return model;
	}
}
