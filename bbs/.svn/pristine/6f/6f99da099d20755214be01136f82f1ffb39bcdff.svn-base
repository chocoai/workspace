package com.yhcrt.weihu.bbs.manager.impl;

import static com.yhcrt.weihu.bbs.entity.BbsFriendShip.ACCEPT;
import static com.yhcrt.weihu.bbs.entity.BbsFriendShip.APPLYING;
import static com.yhcrt.weihu.bbs.entity.BbsFriendShip.REFUSE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.bbs.dao.BbsFriendShipDao;
import com.yhcrt.weihu.bbs.entity.BbsFriendShip;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.manager.BbsFriendShipMng;
import com.yhcrt.weihu.bbs.manager.BbsFriendsMng;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.page.Pagination;

@Service
@Transactional
public class BbsFriendShipMngImpl implements BbsFriendShipMng {

	public void agree(Integer id){
		BbsFriendShip friend = findById(id);
		friend.setStatus(ACCEPT);
		//先查查看是不是已经是好友了（此情况在当这两个用户都申请了对方为好友，而且在没有处理的时候都进入了处理页面，那么当两个人都点击进行处理
		//	为同意的时候，避免插入两次数据，如果一个是同意一个是拒绝，那么以同意为准）
		boolean isFriend = bbsFriendsMng.isFriend(friend.getUser().getId(), friend.getFriend().getId());
		if(isFriend){
			return ;
		}
		
		//在同意加他人为好友的时候，如果存在两个人都申请了，那么另外的一个人的申请也得改
		BbsFriendShip o_friend = dao.getFriendShip(friend.getFriend().getId(), friend.getUser().getId());
		if(o_friend != null){
			o_friend.setStatus(ACCEPT);
		}
		//同意接受为好友后，向好友表里面插入好友记录，自己的和好友的，两个都需要设置，因为好友是双向的
		bbsFriendsMng.save(friend.getUser(), friend.getFriend());
		bbsFriendsMng.save(friend.getFriend(), friend.getUser());
	}
	
	@Override
	public Page<BbsFriendShip> getPage(Integer pageSize, Integer pageNo, Integer userId) {
		return dao.getPage(pageSize, pageNo, userId);
	}
	
	public List<Integer> getApplyIds(Integer userId){
		List<BbsFriendShip> applys = dao.getListByUserId(userId, APPLYING);
		List<Integer> applyIds = new ArrayList<Integer>();
		for(int i=0; i<applys.size(); i++){
			applyIds.add(applys.get(i).getFriend().getId());
		}
		return applyIds;
	}
	
	@Transactional(readOnly = true)
	public BbsFriendShip findById(Integer id) {
		BbsFriendShip entity = dao.findById(id);
		return entity;
	}

	public BbsFriendShip save(BbsFriendShip bean) {
		dao.save(bean);
		return bean;
	}

	public BbsFriendShip update(BbsFriendShip bean) {
		Updater<BbsFriendShip> updater = new Updater<BbsFriendShip>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsFriendShip deleteById(Integer id) {
		BbsFriendShip bean = dao.deleteById(id);
		return bean;
	}

	public BbsFriendShip[] deleteByIds(Integer[] ids) {
		BbsFriendShip[] beans = new BbsFriendShip[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public BbsFriendShip getFriendShip(Integer userId, Integer friendId) {
		return dao.getFriendShip(userId, friendId);
	}

	public void apply(BbsUser user, BbsUser friend,String applyDesc) {
		BbsFriendShip bean = getFriendShip(user.getId(), friend.getId());
		if (bean != null) {
			bean.setStatus(APPLYING);
			bean.setApplyDesc(applyDesc);
			bean.setCreateTime(new Date());
		}else{
			bean = new BbsFriendShip();
			bean.setUser(user);
			bean.setFriend(friend);
			bean.setApplyDesc(applyDesc);
			bean.init();
			save(bean);
		}
	}
	
	public void accept(BbsFriendShip friendShip) {
		friendShip.setStatus(ACCEPT);
		BbsFriendShip bean = new BbsFriendShip();
		bean.setUser(friendShip.getFriend());
		bean.setFriend(friendShip.getUser());
		bean.setStatus(ACCEPT);
		save(bean);
	}
	
	public void refuse(BbsFriendShip friendShip) {
		friendShip.setStatus(REFUSE);
	}
	
	public List<BbsFriendShip> getListByUserId(Integer userId){
		return dao.getListByUserId(userId,null);
	}

	public Pagination getPageByUserId(Integer userId, Integer pageNo,
			Integer pageSize) {
		return dao.getPageByUserId(userId, pageNo, pageSize);
	}
	
	public Pagination getApplyByUserId(Integer userId, Integer pageNo,
			Integer pageSize) {
		return dao.getApplyByUserId(userId, pageNo, pageSize);
	}
	
	public List<BbsFriendShip> getList(Integer userId){
		return dao.getList(userId);
	}

	private BbsFriendShipDao dao;
	private BbsFriendsMng bbsFriendsMng;

	@Autowired
	public void setDao(BbsFriendShipDao dao) {
		this.dao = dao;
	}
	@Autowired
	public void setBbsFriendsMng(BbsFriendsMng bbsFriendsMng){
		this.bbsFriendsMng = bbsFriendsMng;
	}

}