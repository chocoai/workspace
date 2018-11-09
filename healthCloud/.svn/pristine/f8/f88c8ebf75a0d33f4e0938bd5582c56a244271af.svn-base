package com.yhcrt.healthcloud.mall.service.impl;	
	
import java.util.List;	
	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	
	
import com.yhcrt.healthcloud.mall.entity.UserCollect;	
import com.yhcrt.healthcloud.mall.mapper.UserCollectMapper;	
import com.yhcrt.healthcloud.mall.service.UserCollectService;	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
@Service	
public class UserCollectServiceImpl implements UserCollectService{	
	
    @Autowired	
    private UserCollectMapper userCollectMapper;	
    	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCollectService#add(com.yhcrt.healthcloud.mall.entity.UserCollect)	
     */	
    @Override	
    public Integer add(UserCollect userCollect) {	
        return userCollectMapper.insert(userCollect);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCollectService#del(java.lang.String)	
     */	
    @Override	
    public Integer del(Integer cid) {	
        return userCollectMapper.deleteByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCollectService#upd(com.yhcrt.healthcloud.mall.entity.UserCollect)	
     */	
    @Override	
    public Integer upd(UserCollect userCollect) {	
        return userCollectMapper.updateByPrimaryKey(userCollect);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCollectService#get(java.lang.String)	
     */	
    @Override	
    public UserCollect get(Integer cid) {	
        return userCollectMapper.selectByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCollectService#getAll()	
     */	
    @Override	
    public List<UserCollect> getAll() {	
        return userCollectMapper.selectByExample(null);	
    }	
	
}	
