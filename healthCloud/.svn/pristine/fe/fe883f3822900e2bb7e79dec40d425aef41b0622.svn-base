package com.yhcrt.healthcloud.mall.service.impl;	
	
import java.util.List;	
	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	
	
import com.yhcrt.healthcloud.mall.entity.UserComment;	
import com.yhcrt.healthcloud.mall.mapper.UserCommentMapper;	
import com.yhcrt.healthcloud.mall.service.UserCommentService;	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
@Service	
public class UserCommentServiceImpl implements UserCommentService{	
	
    @Autowired	
    private UserCommentMapper userCommentMapper;	
    	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCommentService#add(com.yhcrt.healthcloud.mall.entity.UserComment)	
     */	
    @Override	
    public Integer add(UserComment userComment) {	
        return userCommentMapper.insert(userComment);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCommentService#del(java.lang.String)	
     */	
    @Override	
    public Integer del(Integer cid) {	
        return userCommentMapper.deleteByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCommentService#upd(com.yhcrt.healthcloud.mall.entity.UserComment)	
     */	
    @Override	
    public Integer upd(UserComment userComment) {	
        return userCommentMapper.updateByPrimaryKey(userComment);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCommentService#get(java.lang.String)	
     */	
    @Override	
    public UserComment get(Integer cid) {	
        return userCommentMapper.selectByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCommentService#getAll()	
     */	
    @Override	
    public List<UserComment> getAll() {	
        return userCommentMapper.selectByExample(null);	
    }	
	
}	
