package com.yhcrt.iHealthCloud.service.impl;	
	
import java.util.List;	

	




import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	

import com.yhcrt.iHealthCloud.entity.ServiceLog;
import com.yhcrt.iHealthCloud.entity.ServiceLogExample;
import com.yhcrt.iHealthCloud.mapper.ServiceLogMapper;
import com.yhcrt.iHealthCloud.service.ServiceLogService;
	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
@Service	
public class ServiceLogServiceImpl implements ServiceLogService{	
	
    @Autowired	
    private ServiceLogMapper serviceLogMapper;	
    	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.ServiceLogService#add(com.yhcrt.healthcloud.mall.entity.ServiceLog)	
     */	
    @Override	
    public Integer add(ServiceLog serviceLog) {	
        return serviceLogMapper.insert(serviceLog);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.ServiceLogService#del(java.lang.String)	
     */	
    @Override	
    public Integer del(Integer cid) {	
        return serviceLogMapper.deleteByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.ServiceLogService#upd(com.yhcrt.healthcloud.mall.entity.ServiceLog)	
     */	
    @Override	
    public Integer upd(ServiceLog serviceLog) {	
        return serviceLogMapper.updateByPrimaryKey(serviceLog);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.ServiceLogService#get(java.lang.String)	
     */	
    @Override	
    public ServiceLog get(Integer cid) {	
        return serviceLogMapper.selectByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.ServiceLogService#getAll()	
     */	
    @Override	
    public List<ServiceLog> getAll() {	
        return serviceLogMapper.selectByExample(null);	
    }

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.ServiceLogService#getByOrderId(java.lang.Integer)
     */
    @Override
    public ServiceLog getByOrderId(Integer orderId) {
        ServiceLogExample example = new ServiceLogExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<ServiceLog> serviceLogList = serviceLogMapper.selectByExample(example);
        if(serviceLogList.size()>0){
            return serviceLogList.get(0);
        }
        return null;
        
    }	
	
}	
