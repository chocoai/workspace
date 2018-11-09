package com.yhcrt.weihu.bbs.manager;


import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsFriendShip;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.page.Pagination;


public interface BbsFriendShipMng {
	
	public void agree(Integer id);
	
	public Page<BbsFriendShip> getPage(Integer pageSize,Integer pageNo,Integer userId);
	
	/**
	 * 查询我的好友申请的状态为申请中的所有用户ID
	 * @param userId
	 * @return
	 */
	public List<Integer> getApplyIds(Integer userId);
	
	public BbsFriendShip findById(Integer id);

	public BbsFriendShip save(BbsFriendShip bean);

	public BbsFriendShip update(BbsFriendShip bean);

	public BbsFriendShip deleteById(Integer id);

	public BbsFriendShip[] deleteByIds(Integer[] ids);
	
	public BbsFriendShip getFriendShip(Integer userId, Integer friendId);

	public void apply(BbsUser user, BbsUser friend,String applyDesc);
	
	public void accept(BbsFriendShip friendShip);
	
	public void refuse(BbsFriendShip friendShip);
	
	public Pagination getPageByUserId(Integer userId, Integer pageNo, Integer pageSize);
	
	public List<BbsFriendShip> getListByUserId(Integer userId);
	
	public Pagination getApplyByUserId(Integer userId, Integer pageNo, Integer pageSize);
	
	public List<BbsFriendShip> getList(Integer userId);
}