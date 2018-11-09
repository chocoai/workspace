package com.yhcrt.iHealthCloud.service;	
	
import java.util.List;	

import com.yhcrt.iHealthCloud.entity.ServiceLog;

	
	
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
    /**@Title: getByOrderId
     * @Description: 通过工单查询出日志
     * @param orderId
     * @return    
    */
    ServiceLog getByOrderId(Integer orderId);	
    	
}	
