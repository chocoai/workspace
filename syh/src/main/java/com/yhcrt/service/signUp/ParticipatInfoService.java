package com.yhcrt.service.signUp;
import static com.yhcrt.utils.Constants.ATHLETE_BASE_INFO;
import static com.yhcrt.utils.Constants.PARTICIPAT_DETAIL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.queryStatistical.ScoreInfo;
import com.yhcrt.entity.queryStatistical.ThreeList;
import com.yhcrt.entity.queryStatistical.ThreeListDetail;
import com.yhcrt.entity.queryStatistical.ThreeListProject;
import com.yhcrt.entity.queryStatistical.ThreeListUnit;
import com.yhcrt.entity.signUp.AthleteBaseInfo;
import com.yhcrt.entity.signUp.ParticipatDetail;
import com.yhcrt.entity.signUp.ParticipatInfo;
import com.yhcrt.service.CommonService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.GetPinyin;
import com.yhcrt.utils.GetSequence;

/***
 * 
 * 参赛信息业务层
 * @author 陈伟
 * 2017年7月10日 下午2:03:21
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("participatInfoService")
public class ParticipatInfoService {
	@Resource
	private DaoSupport dao;
	@Resource(name="CommonService")
	private CommonService commonService;
	 
	
	/**
	 * 
	 * @Title: getById
	 * @Description: TODO
	 * @return: ParticipatDetail
	 */
	public ParticipatInfo getById(Integer cid) throws Exception {
		return (ParticipatInfo) dao.findForObject("ParticipatInfoMapper.getById",cid);
	}
	
	public void update(ParticipatInfo participatInfo) throws Exception {
		  dao.update("ParticipatInfoMapper.updateByPrimaryKeySelective", participatInfo);
	}
	
	
	/**
	 * 查询参赛信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ParticipatInfo> quertByParam(Map<String, Object> model) throws Exception{
		List<ParticipatInfo> list = (ArrayList<ParticipatInfo>)dao.findForList("ParticipatInfoMapper.quertByParam", model);
		if(list.size()>0){
			String projectNames = "";
			if(model.get("pCid")!=null){
				Integer pCid = list.get(0).getProjectManager().getpCid();
				String className = "";
				className = commonService.getFullName(pCid, Constants.Middle_2,className);
				projectNames = list.get(0).getProjectManager().getProjectName()+"("+className+")";
			}
			for (ParticipatInfo participatInfo : list) {
				if(model.get("pCid")!=null){
					participatInfo.setProjectNames(projectNames);
					continue;
				}else{
					Integer pCid = participatInfo.getProjectManager().getpCid();
					String className = "";
					className = commonService.getFullName(pCid, Constants.Middle_2,className);
					projectNames = list.get(0).getProjectManager().getProjectName()+"("+className+")";
					participatInfo.setProjectNames(projectNames);
				}
			}
		}
		return list;
	}
	
	/**
	 * 查询已经录入成绩的信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ParticipatInfo> quertByParamDoneScore(Map<String, Object> model) throws Exception{
		List<ParticipatInfo> list = (ArrayList<ParticipatInfo>)dao.findForList("ParticipatInfoMapper.quertByParamDoneScore", model);
		if(list.size()>0){
			String projectNames = "";
			if(model.get("pCid")!=null){
				Integer pCid = list.get(0).getProjectManager().getpCid();
				String className = "";
				className = commonService.getFullName(pCid, Constants.Middle_2,className);
				projectNames = list.get(0).getProjectManager().getProjectName()+"("+className+")";
			}
			for (ParticipatInfo participatInfo : list) {
				if(model.get("pCid")!=null){
					participatInfo.setProjectNames(projectNames);
					continue;
				}else{
					Integer pCid = participatInfo.getProjectManager().getpCid();
					String className = "";
					className = commonService.getFullName(pCid, Constants.Middle_2,className);
					projectNames = list.get(0).getProjectManager().getProjectName()+"("+className+")";
					participatInfo.setProjectNames(projectNames);
				}
			}
		}
		return list;
	}
	
	/**
	 * 查询还未录入成绩的参赛信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ParticipatInfo> quertByParamNoScore(Map<String, Object> model) throws Exception{
		List<ParticipatInfo> list = (ArrayList<ParticipatInfo>)dao.findForList("ParticipatInfoMapper.quertByParamNoScore", model);
		if(list.size()>0){
			String projectNames = "";
			if(model.get("pCid")!=null){
				Integer pCid = list.get(0).getProjectManager().getpCid();
				String className = "";
				className = commonService.getFullName(pCid, Constants.Middle_2,className);
				projectNames = list.get(0).getProjectManager().getProjectName()+"("+className+")";
			}
			for (ParticipatInfo participatInfo : list) {
				if(model.get("pCid")!=null){
					participatInfo.setProjectNames(projectNames);
					continue;
				}else{
					Integer pCid = participatInfo.getProjectManager().getpCid();
					String className = "";
					className = commonService.getFullName(pCid, Constants.Middle_2,className);
					projectNames = list.get(0).getProjectManager().getProjectName()+"("+className+")";
					participatInfo.setProjectNames(projectNames);
				}
			}
		}
		return list;
	}
	
	/**
	 * 修改团队成员
	 * @param teamCid
	 * @param oldAthleteCid
	 * @param oldUnitCid
	 * @param newAthleteName
	 * @param newUnitCid
	 * @throws Exception 
	 */
	public void updateTeamAthlete(Integer teamCid,Integer projectCid,Integer oldAthleteCid,Integer oldUnitCid,String newAthleteName,Integer newUnitCid) throws Exception{
		if (oldUnitCid!=newUnitCid) {//如果单位变了
			//添加新成员的基础信息
			AthleteBaseInfo athleteBaseInfo=new AthleteBaseInfo();
			int athleteCid=GetSequence.getSequenceByName(dao, ATHLETE_BASE_INFO);
			athleteBaseInfo.setCid(athleteCid);
			athleteBaseInfo.setAthleteName(newAthleteName);
			athleteBaseInfo.setSex(0);
			athleteBaseInfo.setBackup("");
			athleteBaseInfo.setUnitCid(newUnitCid);
			athleteBaseInfo.setAbbreviation(GetPinyin.getPinYinHeadChar(newAthleteName));
			dao.save("AthleteBaseInfoMapper.insert", athleteBaseInfo); //保存个人信息
			//删除原有的成员
			ParticipatInfo participatInfo = (ParticipatInfo) dao.findForObject("ParticipatInfoMapper.getById", teamCid);
			List<ParticipatDetail> participatDetailList = participatInfo.getParticipatDetails();
			Set<Integer> units = new HashSet<Integer>();
			units.add(newUnitCid);
			for(ParticipatDetail participatDetailTemp : participatDetailList){
				if(!participatDetailTemp.getAthleteId().equals(oldAthleteCid)){ //排除本参赛员，修改之后是否混合团队
					units.add(participatDetailTemp.getAthleteBaseInfo().getUnitCid());
				}
			}
			if(units.size()>1){  ////混合队
				participatInfo.setIscombinationTeam(1);
			}else{   //非混合队
				participatInfo.setIscombinationTeam(0);
			}
			dao.update("ParticipatInfoMapper.updateByPrimaryKeySelective", participatInfo);
			for(ParticipatDetail participatDetailTemp : participatDetailList){
				if(participatDetailTemp.getAthleteId().equals(oldAthleteCid)){
					dao.delete("ParticipatDetailMapper.deleteByCid", participatDetailTemp.getCid());
				}
			}
			//将个人信息保存到团队中去
			ParticipatDetail participatDetail=new ParticipatDetail();
			participatDetail.setCid(GetSequence.getSequenceByName(dao, PARTICIPAT_DETAIL));
			participatDetail.setParticipatId(teamCid);
			participatDetail.setAthleteId(athleteCid);
			participatDetail.setBackup("变更成员");
			dao.save("ParticipatDetailMapper.insert", participatDetail);
		}else{  //只是修改姓名
			AthleteBaseInfo athleteBaseInfo = (AthleteBaseInfo) dao.findForObject("AthleteBaseInfoMapper.getById", oldAthleteCid);
			athleteBaseInfo.setAthleteName(newAthleteName);
			dao.update("AthleteBaseInfoMapper.updateByPrimaryKey", athleteBaseInfo);
		}
	}
	/**
	 * @Title: queryTeenageThree
	 * @Description: 三榜信息
	 * @return: List<ThreeList>
	 */
	@SuppressWarnings("unchecked")
	public List<ThreeList> queryTeenageThree(Map<String, Object> model) {
		List<ThreeList> list = null;
		try {
			model.put("ersionNum", TokenManager.getErsionNum());
			list =  (List<ThreeList>) dao.findForList("ThreeListMapper.queryThreeList", model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @Title: queryTeenageThreeDetail
	 * @Description: 三榜明细信息
	 * @return: List<ThreeList>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryTeenageThreeDetail(Map<String, Object> model) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			model.put("ersionNum", TokenManager.getErsionNum());
			List<ThreeListProject> projectList =  (List<ThreeListProject>) dao.findForList("ThreeListProjectMapper.queryThreeProjectList", model);
			if(projectList ==null || projectList.size()==0){
				map.put("project", null);
				map.put("unit", null);
				map.put("rows", null);
			}else{
				List<ThreeListUnit> unitList =  (List<ThreeListUnit>) dao.findForList("ThreeListMapper.queryThreeUnitList", model);
				List<ThreeListDetail> list =  (List<ThreeListDetail>) dao.findForList("ThreeListDetailMapper.queryThreeListDetail", model);
				map.put("project",projectList);
				map.put("unit", unitList);
				map.put("rows", list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * @Title: queryScoreInfo
	 * @Description: 成绩信息
	 * @return: List<ThreeList>
	 */
	@SuppressWarnings("unchecked")
	public List<ScoreInfo> queryScoreInfo(Map<String, Object> model) {
		List<ScoreInfo> list = null;
		try {
			model.put("ersionNum", TokenManager.getErsionNum());
			list =  (List<ScoreInfo>) dao.findForList("ScoreInfoMapper.queryScoreInfo", model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @Title: queryRanking
	 * @Description: 验证名次是否被录入
	 * @return: List<ThreeList>
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public boolean countRanking(Map<String, Object> model) throws Exception {
	    Integer countRanking =  (Integer) dao.findForObject("ParticipatDetailMapper.countRanking", model);
		return countRanking>0?true:false;
	}
}
