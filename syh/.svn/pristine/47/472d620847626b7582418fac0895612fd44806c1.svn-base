package com.yhcrt.service.signUp;

import static com.yhcrt.utils.Constants.ATHLETE_BASE_INFO;
import static com.yhcrt.utils.Constants.PARTICIPAT_DETAIL;
import static com.yhcrt.utils.Constants.PARTICIPAT_INFO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.StringUtil;
import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.project.ClassManager;
import com.yhcrt.entity.project.ProjectManager;
import com.yhcrt.entity.record.ComRecord;
import com.yhcrt.entity.signUp.AthleteBaseInfo;
import com.yhcrt.entity.signUp.ParticipatDetail;
import com.yhcrt.entity.signUp.ParticipatInfo;
import com.yhcrt.entity.signUp.UnitInfo;
import com.yhcrt.service.CommonService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.GetPinyin;
import com.yhcrt.utils.GetSequence;
import com.yhcrt.utils.StringUtils;
import com.yhcrt.utils.Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/***
 * 
 * 参赛明细信息业务层
 * @author 陈伟
 * 2017年7月10日 下午2:03:21
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("participatDetailService")
public class ParticipatDetailService {
	@Resource
	private DaoSupport dao;
	@Resource(name="CommonService")
	private CommonService commonService;
	
	/**
	 * @Title: queryDetail
	 * @Description: TODO
	 * @return: void
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<ParticipatDetail> queryDetail(Integer participatCid) throws Exception {
		return (List<ParticipatDetail>) dao.findForList("ParticipatDetailMapper.getByParticipatCid", participatCid);
	}
	
	/**
	 * 更新参赛成绩
	 * @param athleteParticipat
	 * @throws Exception 
	 */
	public void updateScores(ParticipatDetail participatDetail ,boolean isTeam,boolean saveFlag,String unitNames) throws Exception{
		if (participatDetail.getJudgeRecord()!=null && participatDetail.getJudgeRecord()==1 && saveFlag) {  //如果刷新记录
			//获取旧记录信息
			ComRecord newComRecord=new ComRecord();
			newComRecord.setVersionNum(0);		//设置新纪录生效
			newComRecord.setProjectCid(participatDetail.getParticipatInfo().getPid());
			ComRecord oldComRecord=(ComRecord)dao.findForObject("ComRecordMapper.queryAll", newComRecord);
			oldComRecord.setVersionNum(1);//将版本设为历史版本
			newComRecord.setCid(GetSequence.getSequenceByName(dao, "com_record"));
			newComRecord.setRecordScore(participatDetail.getScores());
			newComRecord.setPreCid(oldComRecord.getCid());
			if(isTeam){
				newComRecord.setRecordHolder(unitNames);
			}else{
				newComRecord.setRecordHolder(participatDetail.getAthleteBaseInfo().getAthleteName());
			}
			newComRecord.setBackup(participatDetail.getBackup());
			newComRecord.setRecordCreatetime(DateUtil.getDay());
			dao.update("ComRecordMapper.updateInfo", oldComRecord);
			dao.save("ComRecordMapper.insertNewInfo", newComRecord);
		}
		dao.update("ParticipatDetailMapper.updateByPrimaryKeySelective", participatDetail);
	}
	
	
	public void AllotReward(Integer medal,Double medalNum,Integer unitId, Integer participatInfoId,Double intrgral) throws Exception{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("medal", medal);
        paramMap.put("unitId", unitId);
        paramMap.put("medalNum", medalNum);
        paramMap.put("participatId", participatInfoId);
        paramMap.put("intrgral", intrgral);
		dao.update("ParticipatDetailMapper.allotReward", paramMap);
	}
	
	
	
	/**
	 * 新增参赛运动员并录入成绩
	 * @param list
	 * @param projectCid
	 */
	public Map<String, Object> saveParticipatDetail(List<AthleteBaseInfo> list,JSONObject participatDetailJson) {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		Set<Integer> units=new HashSet<Integer>();//保存单位的CID
		Integer projectCid= Integer.valueOf(participatDetailJson.getString("projectCid"));
		resultMap.put("result", "success");
		ParticipatDetail participatDetail = null;
		Integer participatInfoId = 0,totalScore = 0;
		//报名开始
		if (list.size()==1) { //个人赛的时候
			try {
				//得到计分计牌方式
				ProjectManager projectManager=(ProjectManager) dao.findForObject("ProjectManagerMapper.queryScoreRecordByProjectCid", projectCid);
				participatDetailJson.remove("projectCid");
				participatDetail = (ParticipatDetail) participatDetailJson.toBean(participatDetailJson, ParticipatDetail.class);
				ParticipatInfo  participatInfo = new ParticipatInfo();
				participatInfoId = GetSequence.getSequenceByName(dao, "participat_info");
				participatInfo.setCid(participatInfoId);
				participatInfo.setPid(projectCid);
				participatInfo.setBackup(participatDetailJson.getString("backup"));
				participatInfo.setIsperUnit(1);
				participatInfo.setIscombinationTeam(0);
				participatInfo.setIsindividual(1);
				participatInfo.setExp1(1);  //已经录入成绩
				participatInfo.setCreaTime(DateUtil.getTime());
				dao.save("ParticipatInfoMapper.save", participatInfo); 
				
				int athleteCid=GetSequence.getSequenceByName(dao, "athlete_base_info");	
				list.get(0).setCid(athleteCid);
				list.get(0).setSex(0);
				list.get(0).setBackup("");
				list.get(0).setAbbreviation(GetPinyin.getPinYinHeadChar(list.get(0).getAthleteName()));
				dao.save("AthleteBaseInfoMapper.insert", list.get(0)); //保存个人信息
	
				participatDetail.setParticipatInfo(participatInfo);
				participatDetail.setAthleteId(athleteCid);
				participatDetail.setParticipatId(participatInfoId);
				participatDetail.setCid(GetSequence.getSequenceByName(dao, "participat_detail"));
				dao.save("ParticipatDetailMapper.insert", participatDetail);//报名详情
				resultMap.put("type", "0");  //个人赛
				totalScore = insertRecord(participatDetail, projectManager,1.0,list.get(0).getAthleteName(),true);
			} catch (Exception e) {
				resultMap.put("result", "failed");
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}else if (list.size()>1) {  //团队赛的时候
			try {  
				Set<String> unitNames = new HashSet<String>();
				//得到计分计牌方式
				ProjectManager projectManager=(ProjectManager) dao.findForObject("ProjectManagerMapper.queryScoreRecordByProjectCid", projectCid);
				participatDetailJson.remove("projectCid");
				participatDetail = (ParticipatDetail) participatDetailJson.toBean(participatDetailJson, ParticipatDetail.class);
				
				resultMap.put("type", 1);
				participatInfoId = GetSequence.getSequenceByName(dao, "participat_info");
				boolean flag = false;
				int unitId = -1;
				for (AthleteBaseInfo athleteBaseInfo : list) {  //统计单位个数
					units.add(athleteBaseInfo.getUnitCid());
					UnitInfo unitInfo = (UnitInfo) dao.findForObject("UnitInfoMapper.getById", athleteBaseInfo.getUnitCid());
					unitNames.add(unitInfo.getUnitName());
					if(unitId!=-1 && unitId!=athleteBaseInfo.getUnitCid()){
						flag = true; 
					}else{
						unitId = athleteBaseInfo.getUnitCid();
					}
				}
				String unitNamesStr = "";
				for(String name : unitNames){
					unitNamesStr += name+" ";
				}
				ParticipatInfo  participatInfo = new ParticipatInfo();
				participatInfo.setCid(participatInfoId);
				participatInfo.setPid(projectCid);
				participatInfo.setBackup(participatDetailJson.getString("backup"));
				participatInfo.setIsperUnit(1);  //是代表单位
//				if(flag){   //是否组合队
//					participatInfo.setIscombinationTeam(1);
//				}else{
//					participatInfo.setIscombinationTeam(0);
//				}
				participatInfo.setIscombinationTeam(flag?1:0); //是否组合队
				participatInfo.setIsindividual(0);  //是否个人赛
				participatInfo.setCreaTime(DateUtil.getTime());
				participatInfo.setExp1(1);  //已经录入成绩
				boolean saveFlag = true;
				for (AthleteBaseInfo athleteBaseInfo : list) {//保存运动员的基本信息
					int cid = GetSequence.getSequenceByName(dao, ATHLETE_BASE_INFO);
					athleteBaseInfo.setCid(cid);
					dao.save("AthleteBaseInfoMapper.insert", athleteBaseInfo);
					participatDetail.setParticipatInfo(participatInfo);
					participatDetail.setAthleteId(cid);
					participatDetail.setParticipatId(participatInfoId);
					participatDetail.setCid(GetSequence.getSequenceByName(dao, "participat_detail"));
					dao.save("ParticipatDetailMapper.insert", participatDetail);//报名详情
					totalScore = insertRecord(participatDetail, projectManager,Double.valueOf(units.size()),unitNamesStr,saveFlag);
					saveFlag = false;
				}

				dao.save("ParticipatInfoMapper.save", participatInfo); 
			} catch (Exception e) {
				resultMap.put("result", "failed");
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
		//报名结束
		if (units.size()>1) { //多个单位组合的时候
			try{
				if((participatDetail.getMedalNum()!=null && participatDetail.getMedalNum()!=0d) ||
						(participatDetail.getIntrgral()!=null && participatDetail.getIntrgral()!=0)){
					JSONArray unitjJsonArray = new JSONArray();
					for(Integer unitId : units){
						UnitInfo unitinfo = (UnitInfo) dao.findForObject("UnitInfoMapper.getById", unitId); 
						JSONObject jsonUnit = new JSONObject();
						jsonUnit.put("unitName", unitinfo.getUnitName());
						jsonUnit.put("unitCid", unitinfo.getCid());
						jsonUnit.put("medalNum", Tools.formatDouble(participatDetail.getMedalNum()==null?0d:participatDetail.getMedalNum(), 2));
						jsonUnit.put("intrgral", Tools.formatDouble(participatDetail.getIntrgral()==null?0d:participatDetail.getIntrgral(),2));
						jsonUnit.put("participatInfoId", participatInfoId);
						unitjJsonArray.add(jsonUnit);
					}
	
						resultMap.put("isGroup", 1);  //组合队
						resultMap.put("participatDetail", participatDetail);//待分配
						resultMap.put("totalScore", totalScore);//待分配分数
						resultMap.put("unitList", unitjJsonArray);
				}else{
					resultMap.put("isGroup", 0);
				}
			}catch(Exception e){
				resultMap.put("result", "failed");
				e.printStackTrace();
			}
		}else{
			resultMap.put("isGroup", 0);  //独立队
		}
		return resultMap;
	}
	
	public int insertRecord(ParticipatDetail participatDetail,ProjectManager projectManager,Double unitSize,String unitNames,boolean saveFlag) throws Exception{
			int returnScore = 0;
			//成绩录入开始
			Integer ranking= participatDetail.getRanking();//运动员名次
			//获得金牌
			Integer goldMedal=projectManager.getScoreRecord().getGoldMedal();
			//获得银牌
			Integer silverMedal=projectManager.getScoreRecord().getSilverMedal();
			//获得铜牌
			Integer bronzeMedal= projectManager.getScoreRecord().getBronzeMedal();
			boolean isTeam = projectManager.getIsTeam()==1?true:false;

		//	DecimalFormat    df   = new DecimalFormat("######0.0");  //保留一位小数
			if (goldMedal>=ranking) {     //如果金牌数大于名次则获得金牌，否则获得银牌、以此类推
				participatDetail.setMedal(0);    //奖牌(0:金；1：银；2：铜)
				participatDetail.setMedalNum(1/unitSize);
			}else if ((silverMedal+goldMedal)>=ranking) {  
				participatDetail.setMedal(1);
				participatDetail.setMedalNum(1/unitSize);
			}else if ((bronzeMedal+silverMedal+goldMedal)>=ranking) {
				participatDetail.setMedal(2);
				participatDetail.setMedalNum(1/unitSize);
			}
			if (ranking==1) {//第一名
				returnScore =  projectManager.getScoreRecord().getFirstScore();
				participatDetail.setIntrgral(returnScore/unitSize);
			}else if(ranking==2){//第二名
				returnScore =  projectManager.getScoreRecord().getSecondScore();
				participatDetail.setIntrgral(returnScore/unitSize);
			}else if(ranking==3){//第三名
				returnScore =  projectManager.getScoreRecord().getThirdScore();
				participatDetail.setIntrgral(returnScore/unitSize);
			}else if(ranking==4){//第四名
				returnScore =  projectManager.getScoreRecord().getFourthScore();
				participatDetail.setIntrgral(returnScore/unitSize);
			}else if(ranking==5){//第五名
				returnScore =  projectManager.getScoreRecord().getFifthScore();
				participatDetail.setIntrgral(returnScore/unitSize);
			}else if(ranking==6){//第六名
				returnScore =  projectManager.getScoreRecord().getSixthScore();
				participatDetail.setIntrgral(returnScore/unitSize);
			}else if(ranking==7){//第七名
				returnScore =  projectManager.getScoreRecord().getSeventhScore();
				participatDetail.setIntrgral(returnScore/unitSize);
			}else if(ranking==8){//第八名
				returnScore =  projectManager.getScoreRecord().getEightScore();
				participatDetail.setIntrgral(returnScore/unitSize);
			}else {//其他
				participatDetail.setIntrgral(0.0);//没有积分
			}
			updateScores(participatDetail,isTeam,saveFlag,StringUtil.isEmpty(unitNames)?"":unitNames.trim());
			return returnScore;
		//成绩录入结束
	}

	/**
	 * @Title: 删除参赛信息
	 * @Description: TODO
	 * @return: void
	 * @throws Exception 
	 */
	public void deleteByCids(Integer[] cids) throws Exception {
		int count = (Integer) dao.delete("ParticipatInfoMapper.deleteByCids",cids);
		if(count>0){
			dao.delete("ParticipatDetailMapper.deleteByParticipatCids",cids);
		} 
	}

	/**
	 * @Title: isVilidataAthlete
	 * @Description: 验证运动员参赛限制
	 * @return: String
	 * @throws Exception 
	 */
	public Map<String, Object> isVilidataAthlete(Integer cid,Integer projectCid, Integer isTeam, Integer athleteCid) throws Exception {

		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();//返回map
		
		if(cid!=null){
			ParticipatInfo info = (ParticipatInfo) dao.findForObject("ParticipatInfoMapper.getById",cid);
			List<ParticipatDetail> detailes = info.getParticipatDetails();
			
			for (ParticipatDetail bean : detailes) {
				if(bean.getAthleteId().equals(athleteCid)){//运动员不改变时，验证通过
					returnMap.put("states", "success");
					return returnMap;
				}
			}
		}
		
		ProjectManager projectManager = (ProjectManager)dao.findForObject("ProjectManagerMapper.searchObjectByCid", projectCid);
		AthleteBaseInfo athleteBaseInfo = (AthleteBaseInfo) dao.findForObject("AthleteBaseInfoMapper.getById", athleteCid);
		
		//判断该运动员是否已经参赛该项目
		Map<String, Object> paramMap = new LinkedHashMap<String, Object>();//参数map
		paramMap.put("pid", projectCid);
		paramMap.put("athlete", athleteCid);
		int counIsProject = (Integer) dao.findForObject("ParticipatDetailMapper.countIsProject", paramMap);
		if(counIsProject>0){
			returnMap.put("states", "projectError");
			return returnMap;
		}
		
		//判断项目性别和运动员性别
		if (projectManager.getGender()!=Constants.Middle_2&&!projectManager.getGender().equals(athleteBaseInfo.getSex())) {
			returnMap.put("states", "sexError");
			return returnMap;
		}
		//判断运动员年龄是否录入
		String birthday = athleteBaseInfo.getBirthday();
		if(StringUtils.isBlank(birthday)){
			returnMap.put("states", "nobirthday");
			return returnMap;
		}
		
		//判断是否可以跨组参赛
		ClassManager classManager = projectManager.getClassManager();
		Integer isCrossGroup = classManager.getIsCrossGroup();
		if(isCrossGroup.equals(Constants.Middle_1)){
			
		}
		
		
		
		
		String [] s = new String[8];
		if(athleteBaseInfo.getSex()==Constants.Middle){//男女一起比赛时 男性运动员年龄限制 
			s[0] = "groupManAgeStart";s[1] = "groupManAgeEnd";s[2] = "teamManAgeStart";s[3] = "teamManAgeEnd";
		}else if(athleteBaseInfo.getSex()==Constants.Middle_1){
			s[0] = "groupWomanAgeStart";s[1] = "groupWomanAgeEnd";s[2] = "teamWomanAgeStart";s[3] = "teamWomanAgeEnd";
		}	
			
		if(isTeam.equals(Constants.Middle)){
			s[4] = "personSinglenum";s[5] = "unitSinglenum";s[6] = "personTotnum";s[7] = "unitTotnum";
		}else{
			s[4] = "personTeamnum";s[5] = "unitTeamnum";s[6] = "unitTotnum";s[7] = "personTotnum";
		}
		
		Map<String, String> map = new HashMap<String, String>();
		//获得项目限制条件
		map = commonService.getLimits(map,projectManager.getpCid(),Constants.Middle_3,s);
		
		
		//验证男性年龄
		if(athleteBaseInfo.getSex()==Constants.Middle){
			//验证团体
			String teamManAgeStart = map.containsKey("teamManAgeStart")?(String)map.get("teamManAgeStart"):"";
			String teamManAgeEnd = map.containsKey("teamManAgeEnd")?(String)map.get("teamManAgeEnd"):"";
			if(!StringUtils.isBlank(teamManAgeStart)&&!StringUtils.isBlank(teamManAgeEnd)){
				int a = birthday.compareTo(teamManAgeStart);
				int b = birthday.compareTo(teamManAgeEnd);
				if(a<0||b>0){
					returnMap.put("states", "teamManAgeStart");
					returnMap.put("param", "【"+teamManAgeStart+"】-【"+teamManAgeEnd+"】");
					return returnMap;
				}
			}
			//验证组内
			String start = map.containsKey("groupManAgeStart")?(String)map.get("groupManAgeStart"):"";
			String end = map.containsKey("groupManAgeEnd")?(String)map.get("groupManAgeEnd"):"";
			if(!StringUtils.isBlank(start)&&!StringUtils.isBlank(end)){
				int a = birthday.compareTo(start);
				int b = birthday.compareTo(end);
				
				if(a<0||b>0){
					returnMap.put("states", "groupManAgeStart");
					returnMap.put("param", "【"+start+"】-【"+end+"】");
					return returnMap;
				}
			}
			
			
		}else if(athleteBaseInfo.getSex()==Constants.Middle_1){
			String teamWomanAgeStart = map.containsKey("teamWomanAgeStart")?(String)map.get("teamWomanAgeStart"):"";
			String teamWomanAgeEnd = map.containsKey("teamWomanAgeEnd")?(String)map.get("teamWomanAgeEnd"):"";
			if(!StringUtils.isBlank(teamWomanAgeStart)&&!StringUtils.isBlank(teamWomanAgeEnd)){
				int a = birthday.compareTo(teamWomanAgeStart);
				int b = birthday.compareTo(teamWomanAgeEnd);
				if(a<0||b>0){
					returnMap.put("states", "teamWomanAgeStart");
					returnMap.put("param", "【"+teamWomanAgeStart+"】-【"+teamWomanAgeEnd+"】");
					return returnMap;
				}
			}
			
			//验证组内男性年龄
			String start = map.containsKey("groupWomanAgeStart")?(String)map.get("groupWomanAgeStart"):"";
			String end = map.containsKey("groupWomanAgeEnd")?(String)map.get("groupWomanAgeEnd"):"";
			if(!StringUtils.isBlank(start)&&!StringUtils.isBlank(end)){
				int a = birthday.compareTo(start);
				int b = birthday.compareTo(end);
				
				if(a<0||b>0){
					returnMap.put("states", "groupWomanAgeStart");
					returnMap.put("param", "【"+start+"】-【"+end+"】");
					return returnMap;
				}
			}
		}
		
	
		//验证个人参赛数限制
		paramMap.clear(); 
		paramMap.put("athlete", athleteCid);
		paramMap.put("projectType", projectManager.getProjectType());	
		
		Integer personTotnum = map.containsKey("personTotnum")?Integer.parseInt(map.get("personTotnum")):0;
		int countNum = (Integer) dao.findForObject("ParticipatDetailMapper.counIsUnit", paramMap);//获取个人参赛总数
		if(personTotnum>0&&countNum>0&&countNum>=personTotnum){
			returnMap.put("states", "personToError");//个人参赛总数超出
			return returnMap;
		}
		
		
		paramMap.put("isTeam", isTeam);	
		int counIsAthlete = (Integer) dao.findForObject("ParticipatDetailMapper.counIsAthlete", paramMap);//获取个人单项/团体参赛数
		Integer personNum = 0;
		if(isTeam.equals(Constants.Middle)){
			personNum = map.containsKey("personSinglenum")?Integer.parseInt(map.get("personSinglenum")):0;
		}else{
			personNum = map.containsKey("personTeamnum")?Integer.parseInt(map.get("personTeamnum")):0;
		}
		if(personNum>0&&counIsAthlete>0&&counIsAthlete>=personNum){
			returnMap.put("states", "personError");//个人单项/团体参赛数超出
			return returnMap;
		}
		//验证单位参赛数限制
		paramMap.clear(); 
		paramMap.put("unitCid", athleteBaseInfo.getUnitCid());
		paramMap.put("projectType", projectManager.getProjectType());
		
		Integer unitTotnum = map.containsKey("unitTotnum")?Integer.parseInt(map.get("unitTotnum")):0;
		int countUnit = (Integer) dao.findForObject("ParticipatDetailMapper.counIsUnit", paramMap);//获取单位参赛总数
		if(unitTotnum>0&&countUnit>0&&countUnit>=unitTotnum){//单位参赛总数超出
			returnMap.put("states", "unitNumError");
			return returnMap;
		}
		
		paramMap.put("isTeam", isTeam);	
		int counIsUnit = (Integer) dao.findForObject("ParticipatDetailMapper.counIsUnit", paramMap);//获取单位单项/团体参赛数
		Integer unitNum = 0;
		if(isTeam.equals(Constants.Middle)){
			unitNum = map.containsKey("unitSinglenum")?Integer.parseInt(map.get("unitSinglenum")):0;
		}else{
			unitNum = map.containsKey("unitTeamnum")?Integer.parseInt(map.get("unitTeamnum")):0;
		}
		
		if(unitNum>0&&counIsUnit>0&&counIsUnit>=unitNum){//单位单项/团体参赛数超出
			returnMap.put("states", "unitError");
			return returnMap;
		}
		returnMap.put("states", "success");
		return returnMap;
	}
	

	/**
	 * @Title: isVilidataTeam
	 * @Description: 验证多个运动员综合限制
	 * @return: Map<String,Object>
	 * @throws Exception 
	 */
	public Map<String, Object> isVilidataTeam(Integer cid, Integer projectCid, Integer[] athleteCids) throws Exception {
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();//返回map
		
		if(cid!=null){
			ParticipatInfo info = (ParticipatInfo) dao.findForObject("ParticipatInfoMapper.getById",cid);
			List<ParticipatDetail> detailes = info.getParticipatDetails();
			
			List<Integer> list = new ArrayList<Integer>();
			
			if(athleteCids.length==detailes.size()){
				for (ParticipatDetail bean : detailes) {
					boolean isContain = Arrays.asList(athleteCids).contains(bean.getAthleteId());
					if(isContain) list.add(bean.getAthleteId());
				}
				if(list.size()==detailes.size()){
					returnMap.put("states", "success");
					return returnMap;
				}
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		//获得项目限制条件
		ProjectManager projectManager = (ProjectManager)dao.findForObject("ProjectManagerMapper.searchObjectByCid", projectCid);
		String [] s = {"teamSum","teamManSum","teamWomanSum","ageSum"};
		map = commonService.getLimits(map,projectManager.getpCid(),Constants.Middle_3,s);
		
		//团体项目人数
		Integer teamSum = map.containsKey("teamSum")?Integer.parseInt(map.get("teamSum")):0;
		if(teamSum>0&&athleteCids.length!=teamSum){
			returnMap.put("states", "teamSumError");
			returnMap.put("param", teamSum);
			return returnMap;
		}
		
		//团体项目男运动员人数(至少)
		Integer teamManSum = map.containsKey("teamManSum")?Integer.parseInt(map.get("teamManSum")):0;
		if(teamManSum>0){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("cids",athleteCids);
			paramMap.put("sex",Constants.Middle);
			paramMap.put("ersionNum", TokenManager.getErsionNum());
			Integer countSex =  (Integer) dao.findForObject("AthleteBaseInfoMapper.countByCidsAndSex", paramMap);
			if(teamManSum>countSex){
				returnMap.put("states", "teamManSumError");
				returnMap.put("param", teamManSum);
				return returnMap;
			}
		}
		//团体项目女运动员人数(至少)
		Integer teamWomanSum = map.containsKey("teamWomanSum")?Integer.parseInt(map.get("teamWomanSum")):0;
		if(teamWomanSum>0){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("cids",athleteCids);
			paramMap.put("sex",Constants.Middle_1);
			paramMap.put("ersionNum", TokenManager.getErsionNum());
			Integer countSex =  (Integer) dao.findForObject("AthleteBaseInfoMapper.countByCidsAndSex", paramMap);
			if(teamWomanSum>countSex){
				returnMap.put("states", "teamWomanSumError");
				returnMap.put("param", teamWomanSum);
				return returnMap;
			}
		}
		
		Integer ageSum = map.containsKey("ageSum")?Integer.parseInt(map.get("ageSum")):0;
		if(ageSum<=0){
			returnMap.put("states", "success");
			return returnMap;
		}
		Integer ageCount = 0;
		for (int i = 0; i < athleteCids.length; i++) {
			AthleteBaseInfo athleteBaseInfo = (AthleteBaseInfo) dao.findForObject("AthleteBaseInfoMapper.getById", athleteCids[i]);
			String birthday = athleteBaseInfo.getBirthday();
			if(StringUtils.isBlank(birthday)){
				returnMap.put("states", "nobirthday");
				return returnMap;
			}
			ageCount += DateUtil.getAge(birthday);
			if(ageCount>ageSum){
				returnMap.put("states", "ageSumError");
				return returnMap;
			}
		}
		returnMap.put("states", "success");
		return returnMap;
	}
	
	
	/**
	 * @Title: saveInfo
	 * @Description: TODO
	 * @return: void
	 * @throws Exception 
	 */
	public void saveInfo(ParticipatInfo info, Integer[] athleteCids) throws Exception {
		int infoCid = GetSequence.getSequenceByName(dao, PARTICIPAT_INFO);
		info.setCid(infoCid);
		
		
		List<ParticipatDetail> list = new ArrayList<ParticipatDetail>();
		for (int i = 0; i < athleteCids.length; i++) {
			int detailCid = GetSequence.getSequenceByName(dao, PARTICIPAT_DETAIL);
			ParticipatDetail detail = new ParticipatDetail();
			detail.setCid(detailCid);
			detail.setParticipatId(infoCid);
			detail.setAthleteId(athleteCids[i]);
			detail.setCreaTime(info.getCreaTime());
			detail.setCreaRen(info.getCreaRen());
			list.add(detail);
		}
		if(list.size()>0){
			int count = (Integer) dao.save("ParticipatDetailMapper.saveSelectives", list);
			if(count==list.size()){
				dao.save("ParticipatInfoMapper.save", info);
			}
		}
	}
	
	
	/**
	 * 新增惩罚信息的时候取消成绩 名次 等信息
	 * @param participatDetail
	 * @throws Exception
	 */
	public void updateByPunishSelective(ParticipatDetail participatDetail) throws Exception {
		dao.update("ParticipatDetailMapper.updateByPunishSelective", participatDetail);
	}
	
	/**
	 * 新增惩罚信息的时候取消成绩 名次 后，其它人重新排名，计分
	 * @param map
	 * @throws Exception
	 */
	public void updateByPunishForOther(Map<String, Object> paramMap) throws Exception {
		dao.update("ParticipatDetailMapper.updateByPunishForOther", paramMap);
	}

	/**
	 * @Title: updateInfo
	 * @Description: TODO
	 * @return: void
	 * @throws Exception 
	 */
	public void updateInfo(Integer isperUnit,Integer[] unitCids,Integer[] athleteCids, Integer cid) throws Exception {
		ParticipatInfo info = (ParticipatInfo) dao.findForObject("ParticipatInfoMapper.getById",cid);
		if(unitCids.length==1){
			info.setIscombinationTeam(Constants.Middle);
		}else{
			info.setIscombinationTeam(Constants.Middle_1);
		}
		info.setIsperUnit(isperUnit);
		dao.findForObject("ParticipatInfoMapper.updateByPrimaryKeySelective",info);//更新参赛信息
		
		String participatCid = info.getParticipatCids();
		String [] arr = StringUtils.str2Array(participatCid, ",");
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			list1.add(Integer.parseInt(arr[i]));
		}
		for (int i = 0; i < athleteCids.length; i++) {
			list2.add(athleteCids[i]);
			if(!list1.contains(athleteCids[i])){
				int detailCid = GetSequence.getSequenceByName(dao, PARTICIPAT_DETAIL);
				ParticipatDetail detail = new ParticipatDetail();
				detail.setCid(detailCid);
				detail.setParticipatId(info.getCid());
				detail.setAthleteId(athleteCids[i]);
				detail.setCreaTime(info.getCreaTime());
				detail.setCreaRen(info.getCreaRen());
				dao.save("ParticipatDetailMapper.insert", detail);//新增的插入
				System.out.println("增加："+athleteCids[i]);
			}
		}
		for (int i = 0; i <list1.size(); i++) {
			if(!list2.contains(list1.get(i))){
				Map<String, Object> map = new LinkedHashMap<String, Object>();//参数map
				map.put("participatId", info.getCid());
				map.put("athlete",list1.get(i));
				System.out.println("删除："+list1.get(i));
				dao.delete("ParticipatDetailMapper.deleteByParticipatCidAndAthlete", map);//没有的删除
			}
		}
	}

}
