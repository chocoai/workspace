package com.yhcrt.weihu.bbs.manager.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.bbs.dao.BbsPointDetailDao;
import com.yhcrt.weihu.bbs.entity.BbsPointDetail;
import com.yhcrt.weihu.bbs.manager.BbsPointDetailMng;
import com.yhcrt.weihu.bbs.manager.BbsUserMng;
import com.yhcrt.weihu.common.page.Page;

@Service
@Transactional
public class BbsPointDetailMngImpl implements BbsPointDetailMng{

	@Override
	public Integer getPointsAll(Integer userId) {
		if(userId == null){
			return 0;
		}
		return dao.getPointsAll(userId);
	}

	@Override
	public boolean isTodayComplete(Integer userId, String type) {
		return dao.isTodayComplete(userId, type);
	}

	@Override
	public boolean isUploadImg(Integer userId) {
		return dao.isUploadImg(userId);
	}

	@Override
	public boolean isSign(Integer userId) {
		return dao.isSign(userId);
	}

	public Page<BbsPointDetail> getPage(Integer userId,Integer pageSize,Integer pageNo){
		return dao.getPage(userId, pageSize, pageNo);
	}
	
	public void save(Integer userId,Integer points,String description,String operation,String type){
		BbsPointDetail point = new BbsPointDetail();
		point.setCreater(bbsUserMng.findById(userId));
		point.setCreateTime(new Timestamp(System.currentTimeMillis()));
		point.setPoints(points);
		point.setDescription(description);
		point.setOperation(operation);
		point.setType(type);
		dao.save(point);
	}
	
	private BbsPointDetailDao dao;
	private BbsUserMng bbsUserMng;
	
	@Autowired
	public void setDao(BbsPointDetailDao dao){
		this.dao = dao;
	}
	@Autowired
	public void setBbsUserMng(BbsUserMng bbsUserMng) {
		this.bbsUserMng = bbsUserMng;
	}
	
}
