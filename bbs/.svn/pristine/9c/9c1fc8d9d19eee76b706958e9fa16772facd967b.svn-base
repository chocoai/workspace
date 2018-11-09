package com.yhcrt.weihu.bbs.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.bbs.dao.BbsFriendsDao;
import com.yhcrt.weihu.bbs.entity.BbsFriends;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.manager.BbsFriendsMng;
import com.yhcrt.weihu.common.page.Page;

@Service
@Transactional
public class BbsFriendsMngImpl implements BbsFriendsMng {

	@Override
	public void delete(Integer userId, Integer friendId) {
		dao.delete(userId, friendId);
		dao.delete(friendId, userId);
	}

	@Override
	public boolean isFriend(Integer userId, Integer friendId) {
		return dao.isFriend(userId, friendId);
	}

	@Override
	public Page<BbsFriends> getMyFriends(Integer pageSize, Integer pageNo, Integer userId) {
		return dao.getMyFriends(pageSize, pageNo, userId);
	}

	@Override
	public void save(BbsUser user, BbsUser friend) {
		BbsFriends friends = new BbsFriends();
		friends.setCreateTime(new Date());
		friends.setUser(user);
		friends.setFriend(friend);
		dao.save(friends);
	}

	@Override
	public List<Integer> getMyFriendsId(Integer userId) {
		List<BbsFriends> friends = dao.getMyFriends(userId);
		List<Integer> ids = new ArrayList<Integer>();
		for(int i=0; i<friends.size(); i++){
			ids.add(friends.get(i).getFriend().getId());
		}
		return ids;
	}

	private BbsFriendsDao dao;
	
	@Autowired
	public void setDao(BbsFriendsDao dao){
		this.dao = dao;
	}
	
}
