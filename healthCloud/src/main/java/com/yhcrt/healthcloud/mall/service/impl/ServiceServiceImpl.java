package com.yhcrt.healthcloud.mall.service.impl;	
	
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.healthcloud.mall.entity.Service;
import com.yhcrt.healthcloud.mall.mapper.ServiceMapper;
import com.yhcrt.healthcloud.mall.service.ServiceService;	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
@org.springframework.stereotype.Service	
public class ServiceServiceImpl implements ServiceService{	
	
    @Autowired	
    private ServiceMapper serviceMapper;	
    	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.ServiceService#add(com.yhcrt.healthcloud.mall.entity.Service)	
     */	
    @Override	
    public Integer add(Service service) {	
        return serviceMapper.insert(service);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.ServiceService#del(java.lang.String)	
     */	
    @Override	
    public Integer del(Integer cid) {	
        return serviceMapper.deleteByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.ServiceService#upd(com.yhcrt.healthcloud.mall.entity.Service)	
     */	
    @Override	
    public Integer upd(Service service) {	
        return serviceMapper.updateByPrimaryKeySelective(service);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.ServiceService#get(java.lang.String)	
     */	
    @Override	
    public Service get(Integer cid) {	
        return serviceMapper.selectByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.ServiceService#getAll()	
     */	
    @Override	
    public List<Service> getAll(HashMap<String, Object> params) {
        return serviceMapper.search(params);	
    }	
	
}	
