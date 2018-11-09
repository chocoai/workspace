package com.yhcrt.service.project;

import static com.yhcrt.utils.Constants.PROJECT_MANAGER;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.controller.BaseController;
import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.project.ClassManager;
import com.yhcrt.entity.project.ProjectManager;
import com.yhcrt.service.CommonService;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.GetPinyin;
import com.yhcrt.utils.GetSequence;
import com.yhcrt.utils.StringUtils;

/**
 * 比赛项目管理的service
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("projectManagerService")
public class ProjectManagerService {
	@Resource
	private DaoSupport dao;
	
	@Resource
	private CommonService commonService;
	/**
	 * 查询比赛项目列表
	 * @param projectManager
	 * @return
	 * @throws Exception
	 */
	public List<ProjectManager> queryList(Integer pCid) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		ProjectManager projectManager  = new ProjectManager();
		projectManager.setpCid(pCid);
		List<ProjectManager> list = (List<ProjectManager>) dao.findForList("ProjectManagerMapper.selectByExample", projectManager);
		/*
		 * 通过循环根据性别分组的值去判断
		 * 0：男；1：女;2:混合
		 */
		for (ProjectManager pm : list) {
			String pName = "";
			pName = commonService.getFullName(pm.getpCid(), Constants.Middle_2,pName);
			pm.setpName(pName);
			if(pm.getIsTeam() == 0){
				pm.setGameType("个人赛");
			}else if(pm.getIsTeam() == 1){
				pm.setGameType("团体赛");
			}
			if(pm.getGender()==0){
				pm.setSex("男");
			}else if(pm.getGender()==1){
				pm.setSex("女");
			}else{
				pm.setSex("混合");
			}
		}
		return list;
	}
	
	/**
	 * 添加比赛项目
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public String insertProjectManager(ProjectManager projectManager) {
		String result="";
			try {
				projectManager.setCid(GetSequence.getSequenceByName(dao, PROJECT_MANAGER));
				projectManager.setCreaRen(BaseController.getUserCode());
				projectManager.setCreaTime(DateUtil.getTime());


				ClassManager classManager = commonService.getByClassCid(projectManager.getpCid(),Constants.Middle_3);
				projectManager.setProjectType(classManager.getCid());
				/*
				 * 当简称为空串或者null时，根据项目名称首字母去取值
				 */
				if(StringUtils.isBlank(projectManager.getAbbreviation())){
					projectManager.setAbbreviation(GetPinyin.getPinYinHeadChar(projectManager.getProjectName()));
				}
				dao.save("ProjectManagerMapper.insert", projectManager);
				result="success";
			} catch (Exception e) {
				e.printStackTrace();
				result="failed";
			}
		return result;
	}
	
	/**
	 * 通过CID获取项目信息
	 * @return
	 * @throws Exception 
	 */
	public ProjectManager searchObjectByCid(Integer cid) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		try {
			ProjectManager projectManager = (ProjectManager)dao.findForObject("ProjectManagerMapper.searchObjectByCid", cid);
			return projectManager;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 修改比赛项目
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public String updateProjectManager(ProjectManager projectManager) {
		String result="";
			try {
				dao.save("ProjectManagerMapper.update", projectManager);
				result="success";
			} catch (Exception e) {
				e.printStackTrace();
				result="failed";
			}
		return result;
	}
	/**
	 * 删除比赛项目
	 * @param strs
	 * @return 
	 * @throws Exception
	 */
	public HashMap<String, Object> deleteProjectManager(Integer cid) throws Exception {
		HashMap<String,Object> map=new HashMap<String,Object>();
		Integer countParticipat = (Integer) dao.findForObject("ParticipatInfoMapper.countByProjectCid", cid);
		if(countParticipat>0){
			map.put("status", "noparticipat");
		}else{
			dao.delete("ProjectManagerMapper.delete", cid);
			map.put("status", "success");
		}
		return map;
	}
	/**
	 * 根据项目CID查询比赛项目
	 * @param projectManager
	 * @return
	 * @throws Exception
	 */
	public ProjectManager queryProjectByCid(Integer cid) {
		try {
			ProjectManager projectManager = (ProjectManager)dao.findForObject("ProjectManagerMapper.queryProjectByCid", cid);
			return projectManager;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 通过项目CID查询该项目的计分记牌方式
	 * @param cid
	 * @return
	 * @throws Exception 
	 */
	public ProjectManager queryScoreRecordByProjectCid(Integer cid) throws Exception{
		return (ProjectManager) dao.findForObject("ProjectManagerMapper.queryScoreRecordByProjectCid", cid);
	}

	/**
	 * 新增时根据上级CID以及分类名称去查询是否重复输入
	 * @param cid
	 * @return 
	 * @throws Exception
	 */
	public String getObjByNameAndPCid(String name, String pCid) {
		String result="";
		if (name==null||name.equals("")) {
			result="nodata";
		}else {
			try {
				if(pCid != null && !pCid.equals("")){
					HashMap<String,Object> map=new HashMap<String,Object>();
					map.put("pCid", pCid);
					map.put("projectName", name);
					Integer count = (Integer) dao.findForObject("ProjectManagerMapper.countByNameAndPCid",map);
					if(count >0){
						result = "failed";
					}else{
						result="success";
					}
				}else{
					result="success";
				}
			} catch (Exception e) {
				e.printStackTrace();
				result="failed";
			}
		}
		return result;
	}
}