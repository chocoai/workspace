/**
 * 
 */
package com.smart.dao;

import java.util.List;

import com.smart.model.ProjectProcessHistory;

/**
 * @Description:
 * @author raopanfeng
 * @date 2017年1月20日 下午5:57:16
 */
public interface ProjectProcessHistoryDao
		extends BaseDao<ProjectProcessHistory, Integer> {

	/**
	 * 根据项目ID查询该项目的流程处理历史记录
	 * 
	 * @param projectId
	 * @return
	 */
	public List<ProjectProcessHistory> getListByProjectId(Integer projectId);
	
	/**
	 * 根据项目信息ID查询该项目的流程处理历史记录
	 * 
	 * @param projectId
	 * @return
	 */
	public List<ProjectProcessHistory> getListByProjectInfoId(Integer projectInfoId);

}
