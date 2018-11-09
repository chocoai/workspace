package com.yhcrt.weihu.bbs.manager;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsFriends;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.common.page.Page;

public interface BbsFriendsMng {

	public void delete(Integer userId,Integer friendId);
	public boolean isFriend(Integer userId,Integer friendId);
	public Page<BbsFriends> getMyFriends(Integer pageSize,Integer pageNo,Integer userId);
	public void save(BbsUser user,BbsUser friend);
	public List<Integer> getMyFriendsId(Integer userId);
	
}
