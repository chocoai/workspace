package com.yhcrt.weihu.bbs.dao;


import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsFriendShip;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsFriendShipDao {
	
	public Page<BbsFriendShip> getPage(Integer pageSize,Integer pageNo,Integer userId);
	
	public BbsFriendShip findById(Integer id);

	public BbsFriendShip save(BbsFriendShip bean);

	public BbsFriendShip updateByUpdater(Updater<BbsFriendShip> updater);

	public BbsFriendShip deleteById(Integer id);

	public BbsFriendShip getFriendShip(Integer userId, Integer friendId);

	public Pagination getPageByUserId(Integer userId, Integer pageNo, Integer pageSize);
	
	public List<BbsFriendShip> getListByUserId(Integer userId,Integer status);

	public Pagination getApplyByUserId(Integer userId, Integer pageNo,
			Integer pageSize);
	
	public List<BbsFriendShip> getList(Integer userId);
}