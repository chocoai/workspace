package com.yhcrt.iHealthCloud.service;	
	
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.UserComment;

	
	
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
    
    String getMoreGoodsComment(JSONObject pdata);
    
    String getMoreOrgComment(JSONObject pdata);
    
    String getMoreContentComment(JSONObject pdata);
    
    String addUserComment(JSONObject pdata);
    /**
     * 
     * @Description:   评价
     * @param rid      商品id
     * @param orderid  订单id
     * @param memberId 评价用户
     * @param type  
     * @param content
     * @param stars
     * @return void
     */
    void saveUserComment(Integer[] rid,Integer orderid,Integer memberId,String type,String[] content,Integer[] stars);
    /**
     * 
     * @Description: 获取评论
     * @param comment
     * @param limit  获取特定条数
     * @return
     * @return List<UserComment>
     */
    List<UserComment> selectAll(UserComment comment,Integer limit);
    
    List<UserComment> getCommentsListByRefIdAndCommentType(Integer refId, String commentType);
    	
}	