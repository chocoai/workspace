package com.yhcrt.controller.scoreManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.project.ProjectManager;
import com.yhcrt.entity.record.ComRecord;
import com.yhcrt.entity.signUp.AthleteBaseInfo;
import com.yhcrt.entity.signUp.ParticipatDetail;
import com.yhcrt.entity.signUp.ParticipatInfo;
import com.yhcrt.service.CommonService;
import com.yhcrt.service.project.ProjectManagerService;
import com.yhcrt.service.record.ComRecordService;
import com.yhcrt.service.signUp.AthleteBaseInfoService;
import com.yhcrt.service.signUp.ParticipatDetailService;
import com.yhcrt.service.signUp.ParticipatInfoService;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.StringUtils;
import com.yhcrt.utils.Tools;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 成绩录入控制类
 * @author kejunzhong
 * 2017年5月25日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/inputScore")
public class InputScoreController extends BaseController{
	
	@Resource
	private ParticipatInfoService participatInfoService;
	@Resource
	private ParticipatDetailService participatDetailService;
	@Resource
	private AthleteBaseInfoService athleteBaseInfoService;
	@Resource
	private ProjectManagerService projectManagerService;
	@Resource
	private ComRecordService comRecordService;
	@Resource
	private CommonService commonService;

	// 进入查询主页
	@RequestMapping("/index")
	public ModelAndView getIndex() {
		return new ModelAndView("scoreManager/inputScore/index");
	}
	
	// 新增参赛信息
	@RequestMapping("/addAthleteParticipat")
	public ModelAndView addAthleteParticipat() {
		return new ModelAndView("scoreManager/inputScore/addAthleteParticipat");
	}

	// 进入录入成绩页面(个人)
	@RequestMapping("/inputAthleteScore")
	public ModelAndView getInputAthleteScore(@RequestParam Integer projectCid, @RequestParam Integer athleteCid) {
		model.clear();
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			AthleteBaseInfo athleteBaseInfo = athleteBaseInfoService.getById(athleteCid);
			ProjectManager projectManager = projectManagerService.queryProjectByCid(projectCid);
			model.put("athleteInfo", athleteBaseInfo);
			model.put("projectManager", projectManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("scoreManager/inputScore/inputAthleteScore", model);
	}

	/**
	 * AJAX刷新列表
	 * 
	 * @param jsonString
	 * @return
	 */
/*	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(Integer projectCid,Integer currentPage,String queryUnitName,String queryAthleteName,Integer queryRange) {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> queryCondition=new HashMap<String, Object>();
		//设置查询条件
		queryCondition.put("projectCid", projectCid);
		queryCondition.put("athleteName", queryAthleteName);
		queryCondition.put("unitName",queryUnitName);
		queryCondition.put("queryRange",queryRange);
		try {
			PageHelper.startPage(currentPage, 10);
			String pName=null;
			String projectName=null;
			List<AthleteParticipat> list = athleteParticipatService.queryScores(queryCondition);
			for (AthleteParticipat athleteParticipat2 : list) {
				if(projectCid==null){
					pName=(String)commonService.getName(athleteParticipat2.getProjectManager().getpCid()).get("className");
					projectName=athleteParticipat2.getProjectManager().getProjectName();
					athleteParticipat2.getProjectManager().setProjectName(projectName+"("+pName+")");
				}
				if (athleteParticipat2.getProjectManager().getIsTeam()==1) {//如果是团队赛的时候
					//拼接单位名称
					athleteParticipat2.setTeamUnitName(commonService.getTeamInfoByTeamCid(athleteParticipat2.getAthleteCid()).get("unitName"));
					//拼接运动员姓名
					athleteParticipat2.setTeamAthleteName(commonService.getTeamInfoByTeamCid(athleteParticipat2.getAthleteCid()).get("athleteName"));
				}
			}
			//查询项目
			ProjectManager projectBean = null;
			if(projectCid!=null){
				projectBean = projectManagerService.queryProjectByCid(projectCid);
				pName=(String)commonService.getName(projectBean.getpCid()).get("className");
				projectName=projectBean.getProjectName();
				projectBean.setProjectName(projectName+"("+pName+")");
			}
			model.put("projectManager", projectBean);
			
			PageBean<AthleteParticipat> pageInfo = new PageBean<AthleteParticipat>(list);
			model.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}*/
	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(Integer currentPage,Integer pCid,String queryParam) {
		model.clear();
		model.put("pCid", pCid);
		model.put("param", queryParam);
		try {
			 PageHelper.startPage(currentPage, pageSize);
			 List<ParticipatInfo> list = participatInfoService.quertByParamNoScore(model);	
			 PageBean<ParticipatInfo> pageInfo=new PageBean<ParticipatInfo>(list);
			 model.put("pageInfo",pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 *    查询成绩基本信息列表  关联detail成绩 奖牌等
	 * @param currentPage
	 * @param pCid
	 * @param queryParam
	 * @return
	 */
	@RequestMapping("/queryPageReport")
	@ResponseBody
	public Map<String, Object> queryPageReport(Integer currentPage,Integer pCid,String queryParam) {
		model.clear();
		model.put("pCid", pCid);
		model.put("param", queryParam);
		try {
			 PageHelper.startPage(currentPage, pageSize);
			 List<ParticipatInfo> list = participatInfoService.quertByParamDoneScore(model);	
			 for(ParticipatInfo participatInfo : list){
				    Set<Integer> units= new HashSet<Integer>();
					for(ParticipatDetail participatDetail : participatInfo.getParticipatDetails()){
						units.add(participatDetail.getAthleteBaseInfo().getUnitCid());
					}
			    	Map<String, String> scoreInfo = new HashMap<String, String>();
			    	Integer rakingTotal=0,medalTotal=0;
			    	Double medalNumTotal=0.0,intrgralTotal=0.0;
			    	String scoresTotal=null,viewIntrgral="",viewMedalNum="";
			    	for(ParticipatDetail participatDetail : participatInfo.getParticipatDetails()){
			    		Integer unitCid = participatDetail.getAthleteBaseInfo().getUnitCid();
			    		String unitName = participatDetail.getAthleteBaseInfo().getUnitInfo().getUnitName();
			    		Double medalNum = participatDetail.getMedalNum()==null?0:participatDetail.getMedalNum();
			    		Double intrgral = participatDetail.getIntrgral()==null?0:participatDetail.getIntrgral();
			    		if(units.contains(unitCid)){
				    		String scores = participatDetail.getScores();
				    		viewIntrgral += "("+unitName+":"+Tools.formatDouble(intrgral, 2) +")";
				    		viewMedalNum += "("+unitName+":"+Tools.formatDouble(medalNum,2)+")";
				    		Integer ranking = participatDetail.getRanking()==null?0:participatDetail.getRanking();
				    		Integer medal = participatDetail.getMedal()==null?null:participatDetail.getMedal();
				    		scoresTotal = scores;
				    		rakingTotal = ranking;
				    		medalTotal = medal;
				    		medalNumTotal += medalNum;
			    			intrgralTotal += intrgral;
			    			units.remove(unitCid);
			    		}
			    	}
			    	scoreInfo.put("scores", scoresTotal+"");
			    	scoreInfo.put("ranking", rakingTotal+"");
			    	scoreInfo.put("medal", medalTotal+"");
			    	if(participatInfo.getIscombinationTeam()==1){ //组合队查看成绩详情
				    	scoreInfo.put("viewIntrgral", viewIntrgral);
				    	scoreInfo.put("viewMedalNum", viewMedalNum);
			    	}else{
			    		scoreInfo.put("viewIntrgral", Tools.formatDouble(intrgralTotal, 2) +"");
				    	scoreInfo.put("viewMedalNum", Tools.formatDouble(medalNumTotal, 0) +"");
			    	}
			    	scoreInfo.put("medalNum", Tools.formatDouble(medalNumTotal, 0) +"");
			    	scoreInfo.put("intrgral", Tools.formatDouble(intrgralTotal, 2) +"");
			    	participatInfo.setScoreInfo(scoreInfo);
			 }		
			 PageBean<ParticipatInfo> pageInfo=new PageBean<ParticipatInfo>(list);
			 model.put("pageInfo",pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * 处理更新成绩
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
			athleteParticipat.setGold(0+"");//默认奖牌数量都为0
			athleteParticipat.setSilver(0+"");
			athleteParticipat.setCopper(0+"");
			athleteParticipat.setCreaRen(getUserCode());
			athleteParticipat.setCreaTime(DateUtil.getTime());
			Integer ranking= athleteParticipat.getRanking();//运动员名次
			try {
				//得到计分计牌方式
				ProjectManager projectManager=projectManagerService.queryScoreRecordByProjectCid(athleteParticipat.getProjectCid());
				//获得金牌
				Integer goldMedal=projectManager.getScoreRecord().getGoldMedal();
				//获得银牌
				Integer silverMedal=projectManager.getScoreRecord().getSilverMedal();
				//获得铜牌
				Integer bronzeMedal= projectManager.getScoreRecord().getBronzeMedal();
				if (goldMedal>=ranking) {     //如果金牌数大于名次则获得金牌，否则获得银牌、以此类推
					athleteParticipat.setGold("1");
				}else if ((silverMedal+goldMedal)>=ranking) {  
					athleteParticipat.setSilver("1");
				}else if ((bronzeMedal+silverMedal+goldMedal)>=ranking) {
					athleteParticipat.setCopper("1");
				}
				if (ranking==1) {//第一名
					athleteParticipat.setIntrgral(projectManager.getScoreRecord().getFirstScore()+"");
				}else if(ranking==2){//第二名
					athleteParticipat.setIntrgral(projectManager.getScoreRecord().getSecondScore()+"");
				}else if(ranking==3){//第三名
					athleteParticipat.setIntrgral(projectManager.getScoreRecord().getThirdScore()+"");
				}else if(ranking==4){//第四名
					athleteParticipat.setIntrgral(projectManager.getScoreRecord().getFourthScore()+"");
				}else if(ranking==5){//第五名
					athleteParticipat.setIntrgral(projectManager.getScoreRecord().getFifthScore()+"");
				}else if(ranking==6){//第六名
					athleteParticipat.setIntrgral(projectManager.getScoreRecord().getSixthScore()+"");
				}else if(ranking==7){//第七名
					athleteParticipat.setIntrgral(projectManager.getScoreRecord().getSeventhScore()+"");
				}else if(ranking==8){//第八名
					athleteParticipat.setIntrgral(projectManager.getScoreRecord().getEightScore()+"");
				}else {//其他
					athleteParticipat.setIntrgral(0+"");//没有积分
				}
				athleteParticipatService.updateScores(athleteParticipat);
				result = "success";
			} catch (Exception e) {
				result = "failed";
				// TODO: handle exception
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
	            ParticipatInfo participatInfo = participatInfoService.getById(participatId);
	            participatInfo.setExp1(1);
	            participatInfoService.update(participatInfo);
				List<ParticipatDetail> participatDetailList = participatInfo.getParticipatDetails();
				//得到计分计牌方式
				ProjectManager projectManager=projectManagerService.queryScoreRecordByProjectCid(participatInfo.getPid());
				Set<Integer> units = new HashSet<Integer>();
				for(ParticipatDetail participatDetail: participatDetailList){
					Integer unitId = participatDetail.getAthleteBaseInfo().getUnitCid();
					units.add(unitId);
				}
				boolean isTeam = projectManager.getIsTeam()==1?true:false;
				boolean saveFlag = true; //如果是团队，则是保存一次破纪录信息
				Double size = Double.valueOf(units.size());
				for(ParticipatDetail participatDetail: participatDetailList){
					participatDetail.setParticipatInfo(participatInfo);
					participatDetail.setCreaRen(getUserCode());
					participatDetail.setCreaTime(DateUtil.getTime());
					if(participatDetailJson.has("scores")){
						participatDetail.setScores(participatDetailJson.getString("scores"));
					}
					participatDetail.setJudgeLevel(participatDetailJson.getInt("judgeLevel"));
					participatDetail.setJudgeRecord(participatDetailJson.getInt("judgeRecord"));
					participatDetail.setBackup(participatDetailJson.getString("backup"));
					Integer ranking = participatDetailJson.getInt("ranking");
					participatDetail.setRanking(ranking);
					//获得金牌
					Integer goldMedal=projectManager.getScoreRecord().getGoldMedal();
					//获得银牌
					Integer silverMedal=projectManager.getScoreRecord().getSilverMedal();
					//获得铜牌
					Integer bronzeMedal= projectManager.getScoreRecord().getBronzeMedal();
					if (goldMedal>=ranking) {     //如果金牌数大于名次则获得金牌，否则获得银牌、以此类推
						participatDetail.setMedal(0);    //奖牌(0:金；1：银；2：铜)
						participatDetail.setMedalNum(1.0/size);
					}else if ((silverMedal+goldMedal)>=ranking) {  
						participatDetail.setMedal(1);
						participatDetail.setMedalNum(1.0/size);
					}else if ((bronzeMedal+silverMedal+goldMedal)>=ranking) {
						participatDetail.setMedal(2);
						participatDetail.setMedalNum(1.0/size);
					}
					if (ranking==1) {//第一名
						participatDetail.setIntrgral(projectManager.getScoreRecord().getFirstScore()/size);
					}else if(ranking==2){//第二名
						participatDetail.setIntrgral(projectManager.getScoreRecord().getSecondScore()/size);
					}else if(ranking==3){//第三名
						participatDetail.setIntrgral(projectManager.getScoreRecord().getThirdScore()/size);
					}else if(ranking==4){//第四名
						participatDetail.setIntrgral(projectManager.getScoreRecord().getFourthScore()/size);
					}else if(ranking==5){//第五名
						participatDetail.setIntrgral(projectManager.getScoreRecord().getFifthScore()/size);
					}else if(ranking==6){//第六名
						participatDetail.setIntrgral(projectManager.getScoreRecord().getSixthScore()/size);
					}else if(ranking==7){//第七名
						participatDetail.setIntrgral(projectManager.getScoreRecord().getSeventhScore()/size);
					}else if(ranking==8){//第八名
						participatDetail.setIntrgral(projectManager.getScoreRecord().getEightScore()/size);
					}else {//其他
						participatDetail.setIntrgral(0.0);//没有积分
					}
					participatDetailService.updateScores(participatDetail,isTeam,saveFlag,participatInfo.getUnitNames());
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
	
	
	
	/**
	 * 判断组合队伍信息(如果是组合队伍则需要分配奖励)
	 * @param teamCid
	 * @return
	 */
/*	@RequestMapping("/judgeAllotReward")
	@ResponseBody
	public Map<String, Object> judgeAllotReward(@RequestParam Integer teamCid,@RequestParam Integer projectCid){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			List<TeamParticipat> teamParticipats=teamParticipatService.queryByTeamCid(teamCid);
			if (teamParticipats.size()<2) {//独立单位的队伍不需要分配奖励
				AthleteParticipat athleteParticipat=athleteParticipatService.getAthleteParticipat(teamCid, projectCid);
				teamParticipats.get(0).setGold(athleteParticipat.getGold());
				teamParticipats.get(0).setSilver(athleteParticipat.getSilver());
				teamParticipats.get(0).setCopper(athleteParticipat.getCopper());
				teamParticipats.get(0).setIntrgral(athleteParticipat.getIntrgral());
				teamParticipats.get(0).setProjectCid(projectCid);
				teamParticipatService.updateTeamScores(teamParticipats.get(0));
				resultMap.put("result", "oneUnit");
			}else {//组合单位参赛，需要分配奖励
				AthleteParticipat athleteParticipat=athleteParticipatService.getAthleteParticipat(teamCid, projectCid);
				DecimalFormat    df   = new DecimalFormat("######0.0");  //保留一位小数
				Double goldNum=Double.valueOf(athleteParticipat.getGold())/teamParticipats.size();
				Double silverNum=Double.valueOf(athleteParticipat.getSilver())/teamParticipats.size();
				Double copperNum=Double.valueOf(athleteParticipat.getCopper())/teamParticipats.size();
				Double intrgral=Double.valueOf(athleteParticipat.getIntrgral())/teamParticipats.size();
				for (int i = 0; i < teamParticipats.size(); i++) { //初始默认分配
					teamParticipats.get(i).setGold(df.format(goldNum).toString());
					teamParticipats.get(i).setSilver(df.format(silverNum).toString());
					teamParticipats.get(i).setCopper(df.format(copperNum).toString());
					teamParticipats.get(i).setIntrgral(df.format(intrgral).toString());
					teamParticipats.get(i).setProjectCid(projectCid);
					teamParticipatService.updateTeamScores(teamParticipats.get(0));
				}
				resultMap.put("result", "groupUnit");
				resultMap.put("athleteParticipat", athleteParticipat);//待分配
				resultMap.put("teamParticipats", teamParticipats);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", "failed");
		}
		return resultMap;
	}*/
	@RequestMapping("/judgeAllotReward")
	@ResponseBody
	public Map<String, Object> judgeAllotReward(@RequestParam Integer participatId){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			ParticipatInfo participatInfo = participatInfoService.getById(participatId);
			List<ParticipatDetail> detailList = participatInfo.getParticipatDetails();
            Set<Integer> units = new HashSet<Integer>();
            JSONArray unitsArray = new JSONArray();
			for(ParticipatDetail participatDetail : detailList){
				units.add(participatDetail.getAthleteBaseInfo().getUnitCid());
			}
			
			if(units.size()>1){  //组合单位参赛，需要分配奖励
		    	Map<String, String> scoreInfo = new HashMap<String, String>();
		    	Integer rakingTotal=0,medalTotal=0;
		    	Double medalNumTotal=0.0,intrgralTotal=0.0;
		    	String scoresTotal=null;
				for(ParticipatDetail participatDetail : detailList){
		    		Integer unitCid = participatDetail.getAthleteBaseInfo().getUnitCid();
		    		Double medalNum = participatDetail.getMedalNum()==null?0:participatDetail.getMedalNum();
		    		Double intrgral = participatDetail.getIntrgral()==null?0:participatDetail.getIntrgral();
		    		if(units.contains(unitCid)){
						JSONObject unit = new JSONObject();
						unit.put("unitName", participatDetail.getAthleteBaseInfo().getUnitInfo().getUnitName());
						unit.put("unitCid", participatDetail.getAthleteBaseInfo().getUnitCid());
						unit.put("intrgral", Tools.formatDouble(participatDetail.getIntrgral(), 2));
						unit.put("medalNum", Tools.formatDouble(participatDetail.getMedalNum(),2));
						unitsArray.add(unit);
			    		String scores = participatDetail.getScores();
			    		Integer ranking = participatDetail.getRanking()==null?0:participatDetail.getRanking();
			    		Integer medal = participatDetail.getMedal()==null?null:participatDetail.getMedal();
			    		scoresTotal = scores;
			    		rakingTotal = ranking;
			    		medalTotal = medal;
			    		medalNumTotal += medalNum;
		    			intrgralTotal += intrgral;
		    			units.remove(unitCid);
		    		}
		    	}
				scoreInfo.put("participatId", participatInfo.getCid()+"");
		    	scoreInfo.put("scores", scoresTotal+"");
		    	scoreInfo.put("ranking", rakingTotal+"");
		    	scoreInfo.put("medal", medalTotal+"");
		    	scoreInfo.put("medalNum", Tools.formatDouble(medalNumTotal, 0) +"");  //精确到整数
		    	scoreInfo.put("intrgral", Tools.formatDouble(intrgralTotal,2)+"");
		    	resultMap.put("scoreInfo", scoreInfo);
		    	resultMap.put("units", unitsArray);
		    	if(Tools.formatDouble(medalNumTotal, 0)>0 || Tools.formatDouble(intrgralTotal,2)>0 ){
		    		resultMap.put("result", "groupUnit");
		    	}else{
		    		resultMap.put("result", "oneUnit"); //
		    	}
			}else{
				resultMap.put("result", "oneUnit");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", "failed");
		}
		return resultMap;
	}
	
	
	
	
	
	
	
	
	/**
	 * 处理组合队成绩得分分配
	 * @param jsonString
	 * @return
	 */
/*	@RequestMapping("/doAllotReward")
	@ResponseBody
	public String doAllotReward(@RequestParam String jsonString){
		JSONArray jsonArray=JSONArray.fromObject(jsonString);
		List<TeamParticipat> teamParticipats= jsonArray.toList(jsonArray, TeamParticipat.class);
		String result="";
		for (TeamParticipat teamParticipat : teamParticipats) {
			teamParticipat.setCreaRen(getUserCode());
			teamParticipat.setCreaTime(DateUtil.getTime());
			try {
				teamParticipatService.updateTeamScores(teamParticipat);
				result="success";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}*/
	@RequestMapping("/doAllotReward")
	@ResponseBody
	public String doAllotReward(@RequestParam String jsonString){	
		JSONArray infojsonArray = JSONArray.fromObject(jsonString);
		String result="";
		try{
			Integer participatInfoId = infojsonArray.getJSONObject(0).getInt("participatInfoId");
			for (int i=0;i<infojsonArray.size();i++) {
				JSONObject info = infojsonArray.getJSONObject(i);
				Integer unitId = Integer.valueOf(info.getString("unitCid"));
				Integer medal = null;
				Double medalNum = null ;
				Double intrgral = Double.valueOf(info.getString("intrgral"));
                if(!"0".equals(info.getString("gold"))){
                	medal = 0;
                	medalNum = Double.valueOf(info.getString("gold"));
                }else if(!"0".equals(info.getString("silver"))){
                	medal = 1;
                	medalNum = Double.valueOf(info.getString("silver"));
                }else if(!"0".equals(info.getString("copper"))){
                	medal = 2;
                	medalNum = Double.valueOf(info.getString("copper"));
                }
                participatDetailService.AllotReward(medal,medalNum,unitId,participatInfoId,intrgral);
			}
			result = "success";
		}catch(Exception e){
			result ="failed";
           e.printStackTrace();
		}
		return result;
	}

	/**
	 * 成绩校验
	 * @param jsonString
	 * @return
	 */
/*	@RequestMapping("/doScoreJudge")
	@ResponseBody
	public Map<String, Object> doScoreJudge(@RequestParam String jsonString) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("recordJudge", 0);
		JSONObject athleteParticiparJson = JSONObject.fromObject(jsonString);
		AthleteParticipat athleteParticipat = (AthleteParticipat) athleteParticiparJson.toBean(athleteParticiparJson,AthleteParticipat.class);
		try {
			ComRecord comRecord = comRecordService.queryByProjectCid(athleteParticipat.getProjectCid());
			// 获得项目信息
			ProjectManager projectManager = projectManagerService.searchObjectByCid(athleteParticipat.getProjectCid());
			// 运动员成绩处理
			Integer athleteScore = Integer.valueOf(athleteParticipat.getScores().replace(".", "").replace("/", ""));
			Integer mastersLevel=0,firstLevel=0,secondLevel=0,thirdLevel=0;
			// 健将成绩标准
			if (projectManager.getMastersLevel()!=null&&projectManager.getMastersLevel().replace(".", "").replace("/", "")!="") {
				mastersLevel = Integer.valueOf(projectManager.getMastersLevel().replace(".", "").replace("/", ""));
			}
			// 一级运动员标准
			if (projectManager.getFirstLevel()!=null&&projectManager.getFirstLevel().replace(".", "").replace("/", "")!="") {
				firstLevel = Integer.valueOf(projectManager.getFirstLevel().replace(".", "").replace("/", ""));
			}
			// 二级运动员标准
			if (projectManager.getSecondLevel()!=null&&projectManager.getSecondLevel().replace(".", "").replace("/", "")!="") {
				secondLevel = Integer.valueOf(projectManager.getSecondLevel().replace(".", "").replace("/", ""));
			}
			// 三级运动员标准
			if (projectManager.getThreeLevel()!=null&&projectManager.getThreeLevel().replace(".", "").replace("/", "")!="") {
				thirdLevel = Integer.valueOf(projectManager.getThreeLevel().replace(".", "").replace("/", ""));
			}
			if (projectManager.getResultType() == 1 || projectManager.getResultType() == 4) { // 时间下行或数值下行
				if (comRecord!=null) {
					// 获取该项目的最高成绩
					Integer recordScore = Integer.valueOf(comRecord.getRecordScore().replace(".", "").replace("/", ""));
					if (athleteScore < recordScore) { // 破纪录的时候
						resultMap.put("recordJudge", 1);
					}
				}
				if (mastersLevel!=0&&athleteScore <= mastersLevel) {// 达到健将级别了
					resultMap.put("levelJudge", 1);
				} else if (firstLevel!=0&&athleteScore <= firstLevel) {// 达到一级运动员级别
					resultMap.put("levelJudge", 2);
				} else if (secondLevel!=0&&athleteScore <= secondLevel) {// 达到二级运动员级别
					resultMap.put("levelJudge", 3);
				} else if (thirdLevel!=0&&athleteScore <= thirdLevel) {// 达到三级运动员级别
					resultMap.put("levelJudge", 4);
				} else {
					resultMap.put("levelJudge", 0); // 没有达等级
				}
			} else if (projectManager.getResultType() == 2 || projectManager.getResultType() == 3) { // 时间上行
				if (comRecord!=null) {
					// 获取该项目的最高成绩
					Integer recordScore = Integer.valueOf(comRecord.getRecordScore().replace(".", "").replace("/", ""));
					if (athleteScore > recordScore) { // 破纪录的时候
						resultMap.put("recordJudge", 1);
					} 
				}
				if (athleteScore >= mastersLevel) {// 达到健将级别了
					resultMap.put("levelJudge", 1);
				} else if (athleteScore >= firstLevel) {// 达到一级运动员级别
					resultMap.put("levelJudge", 2);
				} else if (athleteScore >= secondLevel) {// 达到二级运动员级别
					resultMap.put("levelJudge", 3);
				} else if (athleteScore >= thirdLevel) {// 达到三级运动员级别
					resultMap.put("levelJudge", 4);
				} else { // 没有达等级
					resultMap.put("levelJudge", 0);
				}
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return resultMap;
	}*/
	@RequestMapping("/doScoreJudge")
	@ResponseBody
	public Map<String, Object> doScoreJudge(@RequestParam String jsonString) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("recordJudge", 0);
		JSONObject participatDetailJson = JSONObject.fromObject(jsonString);
		try {
			if(!participatDetailJson.has("participatId")){
				resultMap.put("states", "participatId");
				return resultMap;
			}
			Integer participatId = participatDetailJson.getInt("participatId");
			ParticipatInfo participatInfo = participatInfoService.getById(participatId);
			ComRecord comRecord = comRecordService.queryByProjectCid(participatInfo.getPid());
			// 获得项目信息
			ProjectManager projectManager = participatInfo.getProjectManager();
			
			// 运动员成绩处理
			Double athleteScore = participatDetailJson.getDouble("scores");
			Double mastersLevel=0.0,firstLevel=0.0,secondLevel=0.0,thirdLevel=0.0;
			// 健将成绩标准
			if (!StringUtils.isBlank(projectManager.getMastersLevel())) {
				mastersLevel = Double.parseDouble(projectManager.getMastersLevel());
			}
			// 一级运动员标准
			if (!StringUtils.isBlank(projectManager.getFirstLevel())) {
				mastersLevel = Double.parseDouble(projectManager.getFirstLevel());
			}
			// 二级运动员标准
			if (!StringUtils.isBlank(projectManager.getSecondLevel())) {
				mastersLevel = Double.parseDouble(projectManager.getSecondLevel());
			}
			// 三级运动员标准
			if (!StringUtils.isBlank(projectManager.getThreeLevel())) {
				mastersLevel = Double.parseDouble(projectManager.getThreeLevel());
			}
			if (projectManager.getResultType() == 1 || projectManager.getResultType() == 4) { // 时间下行或数值下行
				if (comRecord!=null) {
					// 获取该项目的最高成绩
					Integer recordScore = Integer.valueOf(comRecord.getRecordScore().replace(".", "").replace("/", ""));
					if (athleteScore < recordScore) { // 破纪录的时候
						resultMap.put("recordJudge", 1);
					}
				}
				if (mastersLevel!=0&&athleteScore <= mastersLevel) {// 达到健将级别了
					resultMap.put("levelJudge", 1);
				} else if (firstLevel!=0&&athleteScore <= firstLevel) {// 达到一级运动员级别
					resultMap.put("levelJudge", 2);
				} else if (secondLevel!=0&&athleteScore <= secondLevel) {// 达到二级运动员级别
					resultMap.put("levelJudge", 3);
				} else if (thirdLevel!=0&&athleteScore <= thirdLevel) {// 达到三级运动员级别
					resultMap.put("levelJudge", 4);
				} else {
					resultMap.put("levelJudge", 0); // 没有达等级
				}
			} else if (projectManager.getResultType() == 2 || projectManager.getResultType() == 3) { // 时间上行
				if (comRecord!=null) {
					// 获取该项目的最高成绩
					Integer recordScore = Integer.valueOf(comRecord.getRecordScore().replace(".", "").replace("/", ""));
					if (athleteScore > recordScore) { // 破纪录的时候
						resultMap.put("recordJudge", 1);
					} 
				}
				if (athleteScore >= mastersLevel) {// 达到健将级别了
					resultMap.put("levelJudge", 1);
				} else if (athleteScore >= firstLevel) {// 达到一级运动员级别
					resultMap.put("levelJudge", 2);
				} else if (athleteScore >= secondLevel) {// 达到二级运动员级别
					resultMap.put("levelJudge", 3);
				} else if (athleteScore >= thirdLevel) {// 达到三级运动员级别
					resultMap.put("levelJudge", 4);
				} else { // 没有达等级
					resultMap.put("levelJudge", 0);
				}
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 获取团队运动员信息
	 * @param jsonString
	 * @return
	 */
/*	@RequestMapping("/getAthletes")
	@ResponseBody
	public Map<String, Object> getAthletes(Integer teamCid){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<AthleteDetail> list=new ArrayList<AthleteDetail>();
		try {
			list=teamParticipatService.getAthleteDetailByTeamCid(teamCid);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		resultMap.put("list", list);
		return resultMap;
	}*/
	@RequestMapping("/getAthletes")
	@ResponseBody
	public Map<String, Object> getAthletes(Integer teamCid){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ParticipatDetail> list=new ArrayList<ParticipatDetail>();
		try {
			list=participatDetailService.queryDetail(teamCid);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		resultMap.put("list", list);
		return resultMap;
	}
	
	/**
	 * 新增参赛信息并录入成绩
	 * @param 运动员
	 * @param 项目CID
	 * @return
	 */
/*	@RequestMapping("/doAddAthlete")
	@ResponseBody
	public Map<String, Object> doAddAthlete(@RequestParam String JSONAtletes,@RequestParam String JSONathleteParticipat) {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		JSONArray athletes=JSONArray.fromObject(JSONAtletes);//获得录入的运动员
		JSONObject athleteParticipatjson=JSONObject.fromObject(JSONathleteParticipat);
		List<AthleteBaseInfo> athleteBaseInfos=athletes.toList(athletes, AthleteBaseInfo.class);
		AthleteParticipat athleteParticipat=(AthleteParticipat) athleteParticipatjson.toBean(athleteParticipatjson,AthleteParticipat.class);
		athleteParticipat.setCreaRen(getUserCode());
		athleteParticipat.setCreaTime(DateUtil.getTime());
		resultMap= athleteParticipatService.saveAthleteParticipat(athleteBaseInfos, athleteParticipat);
		return resultMap;
	}*/
	@RequestMapping("/doAddAthlete")
	@ResponseBody
	public Map<String, Object> doAddAthlete(@RequestParam String JSONAtletes,@RequestParam String JSONparticipatDetail) {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		JSONArray athletes=JSONArray.fromObject(JSONAtletes);//获得录入的运动员
		JSONObject participatDetailJson =JSONObject.fromObject(JSONparticipatDetail);
		participatDetailJson.put("creaRen", getUserCode());
		participatDetailJson.put("creaTime",DateUtil.getTime());
		List<AthleteBaseInfo> athleteBaseInfos=athletes.toList(athletes, AthleteBaseInfo.class);	
		resultMap= participatDetailService.saveParticipatDetail(athleteBaseInfos, participatDetailJson);
		return resultMap;
	}
	
	
	/**
	 * 变更运动员单位或者名称
	 * @param teamCid
	 * @param projectCid
	 * @param oldAthleteCid
	 * @param oldUnitCid
	 * @param newAthleteName
	 * @param newUnitCid
	 * @return
	 */
	@RequestMapping("/updateTeamAthlete")
	@ResponseBody
	public String updateTeamAthlete(@RequestParam Integer teamCid,@RequestParam Integer projectCid,@RequestParam Integer oldAthleteCid,
						@RequestParam Integer oldUnitCid,@RequestParam String newAthleteName,@RequestParam Integer newUnitCid){
		String result="";
		try {
		//	athleteParticipatService.updateTeamAthlete(1, projectCid,oldAthleteCid, oldUnitCid, newAthleteName, newUnitCid);
			participatInfoService.updateTeamAthlete(teamCid, projectCid, oldAthleteCid, oldUnitCid, newAthleteName, newUnitCid);
			result="success";
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			result="failed";
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @Title: isRanking
	 * @Description: 判断名次是否被录入
	 * @return: Map<String,Object>
	 */
	@RequestMapping("/isRanking")
	@ResponseBody
	public Map<String, Object> isRanking(Integer pCid,Integer participatId,String rangking) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		model.put("pCid",pCid);
		model.put("participatId",participatId);
		model.put("ranking",rangking);
		try {
		
			boolean isInput = participatInfoService.countRanking(model);
			if(isInput){
				resultMap.put("states", "rankingInput");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}


