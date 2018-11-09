package com.yhcrt.weihu.bbs.manager.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.bbs.dao.BbsConcernDao;
import com.yhcrt.weihu.bbs.entity.BbsConcern;
import com.yhcrt.weihu.bbs.manager.BbsConcernMng;
import com.yhcrt.weihu.bbs.manager.BbsUserMng;
import com.yhcrt.weihu.common.page.Page;

@Service
@Transactional
public class BbsConcernMngImpl implements BbsConcernMng{

	@Override
	public Page<BbsConcern> getPage(Integer userId, Integer pageSize, Integer pageNo) {
		return dao.getPage(userId, pageSize, pageNo);
	}

	@Override
	public List<BbsConcern> getListByUser(Integer userId) {
		if(userId == null){
			return null;
		}
		return dao.getListByUser(userId);
	}

	@Override
	public int deleteByUser(Integer userId, Integer concernUserId) {
		return dao.deleteByUser(userId, concernUserId);
	}

	@Override
	public Boolean isConcernByUser(Integer userId, Integer concernUserId) {
		if(concernUserId == null){
			return null;
		}
		return dao.isConcernByUser(userId, concernUserId);
	}

	@Override
	public void delete(Integer concernId) {
		if(concernId == null){
			return ;
		}
		dao.deleteById(concernId);
	}

	@Override
	public void save(Integer userId, Integer concernUserId) {
		BbsConcern bbsConcern = new BbsConcern();
		bbsConcern.setCreater(bbsUserMng.findById(userId));
		bbsConcern.setConcernUser(bbsUserMng.findById(concernUserId));
		bbsConcern.setCreateTime(new Timestamp(System.currentTimeMillis()));
		dao.save(bbsConcern);
	}
	
	private BbsConcernDao dao;
	private BbsUserMng bbsUserMng;
	
	@Autowired
	public void setDao(BbsConcernDao dao){
		this.dao = dao;
	}
	@Autowired
	public void setBbsUserMng(BbsUserMng bbsUserMng){
		this.bbsUserMng = bbsUserMng;
	}
	
}
