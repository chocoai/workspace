package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.entity.BbsUserActiveLevel;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.page.Pagination;

/**
 * 用户DAO接口
 * 
 * @author tom
 * 
 */
public interface BbsUserDao {
	
	public List<BbsUser> getHostUser(Integer count);
	
	public Page<BbsUser> searchUser(Integer pageSize,Integer pageNo,String keywords);
	
	public List<BbsUser> getList(String username, Integer count);
	
	public Pagination getPage(String username, String email, Integer groupId,
			Boolean disabled, Boolean admin,Boolean official,Integer lastLoginDay,
			Integer orderBy, int pageNo, int pageSize);
	
	public List<BbsUser> getList(Integer count);
	
	public List<BbsUser> getAdminList(Integer siteId, Boolean allChannel,
			Boolean disabled, Integer rank);

	public BbsUser findById(Integer id);

	public BbsUser findByUsername(String username);

	public int countByUsername(String username);

	public int countByEmail(String email);

	public BbsUser save(BbsUser bean);

	public BbsUser updateByUpdater(Updater<BbsUser> updater);
	
	public BbsUser updateActiveLevel(BbsUser bean,BbsUserActiveLevel level);

	public BbsUser deleteById(Integer id);
	
	public List<BbsUser> getSuggestMember(String username, Integer count);
	
}