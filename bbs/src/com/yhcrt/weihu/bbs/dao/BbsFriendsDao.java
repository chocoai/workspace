package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsFriends;
import com.yhcrt.weihu.common.page.Page;

public interface BbsFriendsDao {

	public void delete(Integer userId,Integer friendId);
	public boolean isFriend(Integer userId,Integer friendId);
	public Page<BbsFriends> getMyFriends(Integer pageSize,Integer pageNo,Integer userId);
	public void save(BbsFriends bbsFriends);
	public List<BbsFriends> getMyFriends(Integer userId);
	
}
