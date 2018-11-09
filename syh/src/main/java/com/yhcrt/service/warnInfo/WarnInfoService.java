package com.yhcrt.service.warnInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.dao.DaoSupport;
import com.yhcrt.service.CommonService;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.page.PageBean;

/**
 * 预警提示信息的service
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("warnInfoService")
public class WarnInfoService {

	@Resource
	private DaoSupport dao;
	
	@Resource
	private CommonService commonService;
	/**
	 * 查询参赛年龄预警信息
	 * @param comRecord
	 * @return
	 * @throws Exception
	 */
/*	public HashMap<String,Object> selectAgeWarnAll(HashMap<String, Object> map) {
		List<Object> athlist = new ArrayList<Object>();
		try {
			List<AthleteParticipat> aplist = (List<AthleteParticipat>) dao.findForList("AthleteParticipatMapper.selectAgeWarnAll", map);
			List<AthleteDetail> detaillist = (List<AthleteDetail>) dao.findForList("AthleteDetailMapper.selectAgeWarnAll", map);
			for (AthleteParticipat athleteParticipat : aplist) {
				HashMap<String,Object> hashMap = commonService.getName(athleteParticipat.getProjectManager().getpCid());
				athleteParticipat.setProjectName(athleteParticipat.getProjectManager().getProjectName());
				String ageStart =(String)hashMap.get("ageStart");
				if(ageStart == null){
					ageStart = "";
				}
				String ageEnd =(String)hashMap.get("ageEnd");
				if(ageEnd == null){
					ageEnd = "";
				}
				athleteParticipat.setAge(ageStart+"至"+ageEnd);
				athleteParticipat.setClassName((String)hashMap.get("className"));
				athlist.add(athleteParticipat);
			}
			for (AthleteDetail athleteDetail : detaillist) {
				HashMap<String,Object> hashMap = commonService.getName(athleteDetail.getTeamParticipat().getProjectManager().getpCid());
				athleteDetail.setProjectName(athleteDetail.getTeamParticipat().getProjectManager().getProjectName());
				String ageStart =(String)hashMap.get("ageStart");
				if(ageStart == null){
					ageStart = "";
				}
				String ageEnd =(String)hashMap.get("ageEnd");
				if(ageEnd == null){
					ageEnd = "";
				}
				athleteDetail.setAge(ageStart+"至"+ageEnd);
				athleteDetail.setClassName((String)hashMap.get("className"));
				athlist.add(athleteDetail);
			}
			//分页后，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>，
			PageInfo<Object> pageInfo =new PageInfo<Object>(athlist);
			map.put("warnInfoList", athlist);
			map.put("pageInfo", pageInfo);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	@SuppressWarnings("all")
	public HashMap<String,Object> selectAgeSumWarn(HashMap<String, Object> map) {
		try {
			PageHelper.startPage((Integer)map.get("currentPage"), (Integer)map.get("pageSize"));
			List<Map<String, Object>> aplist = (List<Map<String, Object>>) dao.findForList("ParticipatDetailMapper.selectAgeSumWarn", map);
			for(Map<String, Object> maptemp : aplist){
				Integer classId  = (Integer) maptemp.get("classId");
				String className = "";
				className = commonService.getFullName(classId, Constants.Middle_2,className);
				maptemp.put("className",className );
			}
			PageInfo<Map<String, Object>> pageInfo =new PageInfo<Map<String, Object>>(aplist);
			map.put("warnInfoList", aplist);
			map.put("pageInfo", pageInfo);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("all")
	public HashMap<String,Object> selectAgeWarn(HashMap<String, Object> map) {
		try {
			PageHelper.startPage((Integer)map.get("currentPage"), (Integer)map.get("pageSize"));
			List<Map<String, Object>> aplist = (List<Map<String, Object>>) dao.findForList("ParticipatDetailMapper.selectAgeWarn", map);
			for(Map<String, Object> maptemp : aplist){
				Integer classId  = (Integer) maptemp.get("classId");
				String className = "";
				className = commonService.getFullName(classId, Constants.Middle_2,className);
				maptemp.put("className",className+"|"+ maptemp.get("projectName"));
			}
			PageBean<Map<String, Object>> pageInfo =new PageBean<Map<String, Object>>(aplist);
			map.put("pageInfo", pageInfo);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("all")
	public HashMap<String,Object> selectUnitNumWarn(HashMap<String, Object> map) {
		try {
			PageHelper.startPage((Integer)map.get("currentPage"), (Integer)map.get("pageSize"));
			List<Map<String, Object>> aplist = (List<Map<String, Object>>) dao.findForList("ParticipatDetailMapper.selectUnitNumWarn", map);
			for(Map<String, Object> maptemp : aplist){
				Integer classId  = (Integer) maptemp.get("classId");
				String className = "";
				className = commonService.getFullName(classId, Constants.Middle_2,className);
				maptemp.put("className",className );
			}
			PageBean<Map<String, Object>> pageInfo =new PageBean<Map<String, Object>>(aplist);
			map.put("pageInfo", pageInfo);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("all")
	public HashMap<String,Object> selectPersonNumWarn(HashMap<String, Object> map) {
		try {
			PageHelper.startPage((Integer)map.get("currentPage"), (Integer)map.get("pageSize"));
			List<Map<String, Object>> aplist = (List<Map<String, Object>>) dao.findForList("ParticipatDetailMapper.selectPersonNumWarn", map);
			for(Map<String, Object> maptemp : aplist){
				Integer classId  = (Integer) maptemp.get("classId");
				String className = "";
				className = commonService.getFullName(classId, Constants.Middle_2,className);
				maptemp.put("className",className );
			}
			PageBean<Map<String, Object>> pageInfo =new PageBean<Map<String, Object>>(aplist);
			map.put("pageInfo", pageInfo);
			return map;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("all")
	public HashMap<String,Object> selectNuptialWarn(HashMap<String, Object> map) {
		try {
			PageHelper.startPage((Integer)map.get("currentPage"), (Integer)map.get("pageSize"));
			List<Map<String, Object>> aplist = (List<Map<String, Object>>) dao.findForList("ParticipatDetailMapper.selectNuptialWarn", map);
			for(Map<String, Object> maptemp : aplist){
				Integer classId  = (Integer) maptemp.get("classId");
				String className = "";
				className = commonService.getFullName(classId, Constants.Middle_2,className);
				maptemp.put("className",className );
			}
			PageBean<Map<String, Object>> pageInfo =new PageBean<Map<String, Object>>(aplist);
			map.put("pageInfo", pageInfo);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("all")
	public HashMap<String,Object> selectAcrossUnitWarn(HashMap<String, Object> map) {
		try {
			PageHelper.startPage((Integer)map.get("currentPage"), (Integer)map.get("pageSize"));
			List<Map<String, Object>> aplist = (List<Map<String, Object>>) dao.findForList("ParticipatDetailMapper.selectAcrossUnitWarn", map);
			for(Map<String, Object> maptemp : aplist){
				Integer classId  = (Integer) maptemp.get("classId");
				String className = "";
				className = commonService.getFullName(classId, Constants.Middle_2,className);
				maptemp.put("className",className );
			}
			PageBean<Map<String, Object>> pageInfo =new PageBean<Map<String, Object>>(aplist);
			map.put("pageInfo", pageInfo);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 项目个数
	 * @param map
	 * @return
	 */
/*	public HashMap<String,Object> selectProjectNumWarnAll(HashMap<String, Object> map) {
		List<Object> athlist = new ArrayList<Object>();
		List<Object> teamplist = new ArrayList<Object>();
		try {
			List<AthleteParticipat> aplist = (List<AthleteParticipat>) dao.findForList("AthleteParticipatMapper.selectAgeWarnAll", map);
			List<AthleteDetail> detaillist = (List<AthleteDetail>) dao.findForList("AthleteDetailMapper.selectAgeWarnAll", map);
			for (AthleteParticipat athleteParticipat : aplist) {
				HashMap<String,Object> hashMap = commonService.getName(athleteParticipat.getProjectManager().getpCid());
				Integer personSinglenum=0;
				Integer personTeamnum = 0;
				Integer personTotnum = 0;
				Integer unitSinglenum = 0;
				Integer unitTeamnum = 0;
				Integer unitTotnum = 0;
				if(hashMap.get("personSinglenum")!= null && hashMap.get("personSinglenum") != ""){
					personSinglenum = (Integer)hashMap.get("personSinglenum");
				}
				if(hashMap.get("personTeamnum")!= null && hashMap.get("personTeamnum") != ""){
					personTeamnum = (Integer)hashMap.get("personTeamnum");
				}
				if(hashMap.get("personTotnum")!= null && hashMap.get("personTotnum") != ""){
					personTotnum = (Integer)hashMap.get("personTotnum");
				}
				if(hashMap.get("unitSinglenum")!= null && hashMap.get("unitSinglenum") != ""){
					unitSinglenum = (Integer)hashMap.get("unitSinglenum");
				}
				if(hashMap.get("unitTeamnum")!= null && hashMap.get("unitTeamnum") != ""){
					unitTeamnum = (Integer)hashMap.get("unitTeamnum");
				}
				if(hashMap.get("unitTotnum")!= null && hashMap.get("unitTotnum") != ""){
					unitTotnum = (Integer)hashMap.get("unitTotnum");
				}
				athleteParticipat.setProjectName(athleteParticipat.getProjectManager().getProjectName());
				athleteParticipat.setClassName((String)hashMap.get("className"));
				athleteParticipat.setPersonTotnum((Integer)hashMap.get("personTotnum"));
				athleteParticipat.setUnitTotnum((Integer)hashMap.get("unitTotnum"));
				athlist.add(athleteParticipat);
			}
			for (AthleteDetail athleteDetail : detaillist) {
				HashMap<String,Object> hashMap = commonService.getName(athleteDetail.getTeamParticipat().getProjectManager().getpCid());
				Integer personSinglenum=0;
				Integer personTeamnum = 0;
				Integer personTotnum = 0;
				Integer unitSinglenum = 0;
				Integer unitTeamnum = 0;
				Integer unitTotnum = 0;
				if(hashMap.get("personSinglenum")!= null && hashMap.get("personSinglenum") != ""){
					personSinglenum = (Integer)hashMap.get("personSinglenum");
				}
				if(hashMap.get("personTeamnum")!= null && hashMap.get("personTeamnum") != ""){
					personTeamnum = (Integer)hashMap.get("personTeamnum");
				}
				if(hashMap.get("personTotnum")!= null && hashMap.get("personTotnum") != ""){
					personTotnum = (Integer)hashMap.get("personTotnum");
				}
				if(hashMap.get("unitSinglenum")!= null && hashMap.get("unitSinglenum") != ""){
					unitSinglenum = (Integer)hashMap.get("unitSinglenum");
				}
				if(hashMap.get("unitTeamnum")!= null && hashMap.get("unitTeamnum") != ""){
					unitTeamnum = (Integer)hashMap.get("unitTeamnum");
				}
				if(hashMap.get("unitTotnum")!= null && hashMap.get("unitTotnum") != ""){
					unitTotnum = (Integer)hashMap.get("unitTotnum");
				}
				athleteDetail.setProjectName(athleteDetail.getTeamParticipat().getProjectManager().getProjectName());
				athleteDetail.setClassName((String)hashMap.get("className"));
				athleteDetail.setPersonTotnum((Integer)hashMap.get("personTotnum"));
				athleteDetail.setUnitTotnum((Integer)hashMap.get("unitTotnum"));
				athlist.add(athleteDetail);
			}
			//查询单位项目个数预警
			map.put("warnState", "4");
			List<TeamParticipat> tplist = (List<TeamParticipat>) dao.findForList("TeamParticipatMapper.selectUnitWarnAll", map);
			for (TeamParticipat teamParticipat : tplist) {
				HashMap<String,Object> hashMap = commonService.getName(teamParticipat.getProjectManager().getpCid());
				Integer unitSinglenum = 0;
				Integer unitTeamnum = 0;
				Integer unitTotnum = 0;
				if(hashMap.get("unitSinglenum")!= null && hashMap.get("unitSinglenum") != ""){
					unitSinglenum = (Integer)hashMap.get("unitSinglenum");
				}
				if(hashMap.get("unitTeamnum")!= null && hashMap.get("unitTeamnum") != ""){
					unitTeamnum = (Integer)hashMap.get("unitTeamnum");
				}
				if(hashMap.get("unitTotnum")!= null && hashMap.get("unitTotnum") != ""){
					unitTotnum = (Integer)hashMap.get("unitTotnum");
				}
				teamParticipat.setProjectName(teamParticipat.getProjectManager().getProjectName());
				teamParticipat.setClassName((String)hashMap.get("className"));
				teamParticipat.setUnitTotnum((Integer)hashMap.get("unitTotnum"));
				teamplist.add(teamParticipat);
			}
			//分页后，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>，
			map.put("warnInfoList", athlist);
			map.put("warnTeamInfoList", teamplist);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	/**
	 * 重名参赛
	 * @param map
	 * @return
	 */
/*	public HashMap<String,Object> selectNuptialWarnAll(HashMap<String, Object> map) {
		List<Object> athlist = new ArrayList<Object>();
		try {
			List<AthleteParticipat> aplist = (List<AthleteParticipat>) dao.findForList("AthleteParticipatMapper.selectAgeWarnAll", map);
			List<AthleteDetail> detaillist = (List<AthleteDetail>) dao.findForList("AthleteDetailMapper.selectAgeWarnAll", map);
			for (AthleteParticipat athleteParticipat : aplist) {
				HashMap<String,Object> hashMap = commonService.getName(athleteParticipat.getProjectManager().getpCid());
				athleteParticipat.setProjectName(athleteParticipat.getProjectManager().getProjectName());
				athleteParticipat.setClassName((String)hashMap.get("className"));
				athlist.add(athleteParticipat);
			}
			for (AthleteDetail athleteDetail : detaillist) {
				HashMap<String,Object> hashMap = commonService.getName(athleteDetail.getTeamParticipat().getProjectManager().getpCid());
				athleteDetail.setProjectName(athleteDetail.getTeamParticipat().getProjectManager().getProjectName());
				athleteDetail.setClassName((String)hashMap.get("className"));
				athlist.add(athleteDetail);
			}
			//分页后，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>，
			PageInfo<Object> pageInfo =new PageInfo<Object>(athlist);
			map.put("warnInfoList", athlist);
			map.put("pageInfo", pageInfo);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	/**
	 * 跨单位参赛
	 * @param map
	 * @return
	 */
/*	public HashMap<String,Object> selectAcrossUnitWarnAll(HashMap<String, Object> map) {
		List<Object> athlist = new ArrayList<Object>();
		try {
			List<AthleteParticipat> aplist = (List<AthleteParticipat>) dao.findForList("AthleteParticipatMapper.selectAgeWarnAll", map);
			List<AthleteDetail> detaillist = (List<AthleteDetail>) dao.findForList("AthleteDetailMapper.selectAgeWarnAll", map);
			for (AthleteParticipat athleteParticipat : aplist) {
				HashMap<String,Object> hashMap = commonService.getName(athleteParticipat.getProjectManager().getpCid());
				athleteParticipat.setProjectName(athleteParticipat.getProjectManager().getProjectName());
				athleteParticipat.setClassName((String)hashMap.get("className"));
				athlist.add(athleteParticipat);
			}
			for (AthleteDetail athleteDetail : detaillist) {
				HashMap<String,Object> hashMap = commonService.getName(athleteDetail.getTeamParticipat().getProjectManager().getpCid());
				athleteDetail.setProjectName(athleteDetail.getTeamParticipat().getProjectManager().getProjectName());
				athleteDetail.setClassName((String)hashMap.get("className"));
				athlist.add(athleteDetail);
			}
			//分页后，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>，
			PageInfo<Object> pageInfo =new PageInfo<Object>(athlist);
			map.put("warnInfoList", athlist);
			map.put("pageInfo", pageInfo);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
}