/**
 * 
 */
package com.smart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.ProjectProcessStateDao;
import com.smart.model.ProjectProcessState;
import com.smart.util.Page;

/**
 * @Description:
 * @author raopanfeng
 * @date 2017年1月20日 下午5:07:11
 */
@Service
public class ProjectProcessStateService {

	@Autowired
	private ProjectProcessStateDao projectProcessStateDao;

	@Autowired
	private UserService userService;

	public void save(ProjectProcessState entity) {
		projectProcessStateDao.save(entity);
	}

	public void delete(Integer id) {
		projectProcessStateDao.delete(id);
	}

	public void update(ProjectProcessState entity) {
		projectProcessStateDao.update(entity);
	}

	public ProjectProcessState get(Integer id) {
		return projectProcessStateDao.get(id);
	}

	public List<ProjectProcessState> getAll() {
		return projectProcessStateDao.getAll();
	}

	public List<ProjectProcessState> getListByProjectId(Integer projectId) {
		List<ProjectProcessState> ppsList = projectProcessStateDao
				.getListByProjectId(projectId);
		for (ProjectProcessState pps : ppsList) {
			pps.setCurrentUserName(
					userService.getUserNamesByUserIds(pps.getCurrentUsers()));
		}
		return ppsList;
	}
	
	public List<ProjectProcessState> getListByProjectInfoId(Integer ProjectInfoId) {
		List<ProjectProcessState> ppsList = projectProcessStateDao
				.getListByProjectInfoId(ProjectInfoId);
		for (ProjectProcessState pps : ppsList) {
			pps.setCurrentUserName(
					userService.getUserNamesByUserIds(pps.getCurrentUsers()));
		}
		return ppsList;
	}

	public ProjectProcessState getOneByProjectIdAndStepCode(Integer projectId,
			String stepCode) {
		ProjectProcessState pps = projectProcessStateDao
				.getOneByProjectIdAndStepCode(projectId, stepCode);
		return pps;
	}

	public ProjectProcessState getOneByProjectIdAndBusinesstype(
			Integer projectId, Integer type) {
		return projectProcessStateDao
				.getOneByProjectIdAndBusinesstype(projectId, type);
	}

	/**
	 * 查询当前用户待办的项目
	 * 
	 * @param businessType
	 *            类型 0：经营管理 1:项目管理
	 * @return: Page<ProjectProcessState>
	 */
	public Page<ProjectProcessState> getPageState(Integer pageNo,
			Integer pageSize, String no, String name, Integer userId,
			String businessType) {
		return projectProcessStateDao.getPageState(pageNo, pageSize, no, name,
				userId, businessType);
	}
	
	public Integer countBacklog(Integer userId){
		return projectProcessStateDao.countBacklog(userId);
	}

	/**
	 * @param id
	 * @param bidPlan
	 * @return
	 */
	public ProjectProcessState getOneByProjectInfoIdAndStepCode(Integer id,
			String stepCode) {
		ProjectProcessState pps = projectProcessStateDao
				.getOneByProjectInfoIdAndStepCode(id, stepCode);
		return pps;
	}

	/**
	 * @param id
	 * @return
	 */
	public ProjectProcessState getOneByProjectInfoId(Integer id) {
		ProjectProcessState pps = projectProcessStateDao
				.getOneByProjectInfoId(id);
		return pps;
	}

}
