/**
 * 
 */
package com.smart.dao;

import java.util.List;

import com.smart.model.ProjectProcessState;
import com.smart.util.Page;

/**
 * @Description:
 * @author raopanfeng
 * @date 2017年1月20日 下午4:31:49
 */
public interface ProjectProcessStateDao
		extends BaseDao<ProjectProcessState, Integer> {

	/**
	 * 根据项目ID查询当前项目的状态
	 * 
	 * @param projectId
	 * @return
	 */
	public List<ProjectProcessState> getListByProjectId(Integer projectId);
	
	/**
	 * 根据项目信息ID查询当前项目的状态
	 * 
	 * @param projectId
	 * @return
	 */
	public List<ProjectProcessState> getListByProjectInfoId(Integer ProjectInfoId);

	/**
	 * 根据项目ID和stepCode查询项目的当前状态
	 * 
	 * @param projectId
	 * @param stepCode
	 * @return
	 */
	public ProjectProcessState getOneByProjectIdAndStepCode(Integer projectId,
			String stepCode);

	/**
	 * 查询项目处于经营管理或项目管理的哪个环节
	 * 
	 * @param projectId
	 * @param businessType
	 * @return
	 */
	public ProjectProcessState getOneByProjectIdAndBusinesstype(
			Integer projectId, Integer businessType);

	/**
	 * 查询当前用户的待办项目
	 */

	public Page<ProjectProcessState> getPageState(Integer pageNo,
			Integer pageSize, String no, String name, Integer userId,
			String businessType);
	
	public Integer countBacklog(Integer userId);

	/**
	 * @param id
	 * @param stepCode
	 * @return
	 */
	public ProjectProcessState getOneByProjectInfoIdAndStepCode(Integer id,
			String stepCode);

	/**
	 * @param id
	 * @return
	 */
	public ProjectProcessState getOneByProjectInfoId(Integer id);

}
