package com.yhcrt.healthcloud.health.service;

import com.yhcrt.healthcloud.health.entity.HealthRecord;

/**
 * 健康档案服务接口
 * @author huzelin
 *
 */
public interface  HealthRecordService{
	
	// zhengjiadong
	public int insertSelective(HealthRecord record);

	public HealthRecord selectByPrimaryKey(Integer memberId);

	public int updateByPrimaryKeySelective(HealthRecord record);
	
	public int saveRecord(HealthRecord record);
	
	public int updateRecord(HealthRecord record);
	
	
	/**
	 * 根据recordId获取健康档案
	 * @param recordId 档案编号
	 * @return 健康档案对象
	 */
	public HealthRecord getRecordByRecordId(int recordId);
	
	/**
	 * 根据memberId获取健康档案
	 * @param memberId 档案编号
	 * @return 健康档案对象
	 */
	public HealthRecord getRecordByMemberId(int memberId);

}
