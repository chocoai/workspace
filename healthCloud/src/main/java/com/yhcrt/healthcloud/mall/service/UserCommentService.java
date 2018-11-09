package com.yhcrt.healthcloud.mall.service;	
	
import java.util.List;	
	
import com.yhcrt.healthcloud.mall.entity.UserComment;	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
public interface UserCommentService {	
	
    Integer add(UserComment userComment);	
    Integer del(Integer cid);	
    Integer upd(UserComment userComment);	
    UserComment get(Integer cid);	
    List<UserComment> getAll();	
    	
}	
