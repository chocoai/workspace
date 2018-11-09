package com.yhcrt.healthcloud.mall.service;

import java.util.List;

import com.yhcrt.healthcloud.mall.entity.ServiceLog;

/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
 */
public interface ServiceLogService {

	Integer add(ServiceLog serviceLog);

	Integer del(Integer cid);

	Integer upd(ServiceLog serviceLog);

	ServiceLog get(Integer cid);

	List<ServiceLog> getAll();

	// 查询服务工单日志
	ServiceLog getByOrderId(Integer orderId);

}
