package com.yhcrt.healthcloud.mall.service;	
	
import java.util.List;	
	
import com.yhcrt.healthcloud.mall.entity.UserCollect;	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
public interface UserCollectService {	
	
    Integer add(UserCollect userCollect);	
    Integer del(Integer cid);	
    Integer upd(UserCollect userCollect);	
    UserCollect get(Integer cid);	
    List<UserCollect> getAll();	
    	
}	
