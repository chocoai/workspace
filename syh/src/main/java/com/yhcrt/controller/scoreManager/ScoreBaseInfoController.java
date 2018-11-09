package com.yhcrt.controller.scoreManager;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhcrt.entity.signUp.ParticipatDetail;
import com.yhcrt.entity.signUp.ParticipatInfo;
import com.yhcrt.service.project.ProjectManagerService;
import com.yhcrt.service.signUp.AthleteBaseInfoService;
import com.yhcrt.service.signUp.ParticipatDetailService;
import com.yhcrt.service.signUp.ParticipatInfoService;

import net.sf.json.JSONObject;

/**
 * 成绩基本信息管理的控制层
 * @author kejunzhong
 * 2017年5月22日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/scoreBaseInfo")
public class ScoreBaseInfoController {
	@Resource
	private ParticipatInfoService participatInfoService;
	@Resource
	private ParticipatDetailService participatDetailService;
	@Resource
	private AthleteBaseInfoService athleteBaseInfoService;
	@Resource
	private ProjectManagerService projectManagerService;
	
	//进入成绩基本信息管理主页面
	@RequestMapping("/index")
	public ModelAndView getIndex(){
		return new ModelAndView("scoreManager/scoreBaseInfo/index");
	}
	
	/**
	 * 处理修改成绩
	 * @param jsonString
	 * @return
	 */
/*	@RequestMapping("/doUpdateInfo")
	@ResponseBody
	public String doUpdateInfo(@RequestParam String jsonString) {
		String result = "";
		if (jsonString == null || jsonString.equals("")) {
			result = "nodata";
		} else {
			JSONObject athleteParticiparJson = JSONObject.fromObject(jsonString);
			AthleteParticipat athleteParticipat = (AthleteParticipat) athleteParticiparJson.toBean(athleteParticiparJson, AthleteParticipat.class);
			try {
				athleteParticipatService.updateScores(athleteParticipat);
				result = "success";
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}*/
	@RequestMapping("/doUpdateInfo")
	@ResponseBody
	public String doUpdateInfo(@RequestParam String jsonString) {
		String result = "";
		if (jsonString == null || jsonString.equals("")) {
			result = "nodata";
		} else {
			try {
				JSONObject participatDetailJson = JSONObject.fromObject(jsonString);
	            Integer participatId = Integer.valueOf(participatDetailJson.getString("participatId"));
	            ParticipatDetail updateParticipatDetail = (ParticipatDetail) participatDetailJson.toBean(participatDetailJson, ParticipatDetail.class);
	            ParticipatInfo participatInfo = participatInfoService.getById(participatId);
	            participatInfo.setBackup(updateParticipatDetail.getBackup());
	            participatInfoService.update(participatInfo);
				boolean isTeam = participatInfo.getProjectManager().getIsTeam()==1?true:false;
				boolean saveFlag = true; //如果是团队，则是保存一次破纪录信息
				List<ParticipatDetail> participatDetailList = participatInfo.getParticipatDetails();
				Set<Integer> units = new HashSet<Integer>();
//				DecimalFormat    df   = new DecimalFormat("######0.0");  //保留一位小数
				for(ParticipatDetail participatDetail: participatDetailList){
					Integer unitId = participatDetail.getAthleteBaseInfo().getUnitCid();
					units.add(unitId);
				}
				Double size = Double.valueOf(units.size());
				for(ParticipatDetail participatDetail: participatDetailList){
					participatDetail.setParticipatInfo(participatInfo);
					participatDetail.setJudgeLevel(updateParticipatDetail.getJudgeLevel());
					participatDetail.setJudgeRecord(updateParticipatDetail.getJudgeRecord());
					participatDetail.setScores(updateParticipatDetail.getScores()); //成绩
					participatDetail.setIntrgral(updateParticipatDetail.getIntrgral()/size);
					Double medalNum = updateParticipatDetail.getMedalNum()==null?0d:updateParticipatDetail.getMedalNum();
					participatDetail.setMedalNum(medalNum/size);
					participatDetail.setRanking(updateParticipatDetail.getRanking());
					participatDetail.setMedal(updateParticipatDetail.getMedal());
					participatDetailService.updateScores(participatDetail , isTeam, saveFlag, participatInfo.getUnitNames());
                    if(isTeam){
                    	saveFlag = false;
                    }
				}
				result = "success";
			} catch (Exception e) {
				result = "failed";
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return result;
	}
}
