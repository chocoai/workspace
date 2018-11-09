package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.MedicalExaminationReport;

/**
 * 健康档案数据服务接口
 * 
 * @author huzelin
 *
 */
public interface MerService {

	/**
	 * 根据memberId获取体检报告列表
	 * 
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getMerListByMemberId(JSONObject pdataObj);

	public List<MedicalExaminationReport> getMerListByMemberId(String memberId);

	/**
	 * 根据merId获取体检报告
	 * 
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getMerByMerId(JSONObject pdataObj);

	/**
	 * 根据memberId获取最近一次体检
	 * 
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getLatestMerByMemberId(JSONObject pdataObj);
}
