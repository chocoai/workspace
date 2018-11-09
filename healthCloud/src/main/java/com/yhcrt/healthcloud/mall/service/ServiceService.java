package com.yhcrt.healthcloud.mall.service;

import java.util.HashMap;
import java.util.List;

import com.yhcrt.healthcloud.mall.entity.Service;

/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
 */
public interface ServiceService {

	Integer add(Service service);

	Integer del(Integer cid);

	Integer upd(Service service);

	Service get(Integer cid);

	List<Service> getAll(HashMap<String, Object> params);

}
