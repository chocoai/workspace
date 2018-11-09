package com.yhcrt.weihu.bbs.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.bbs.dao.BbsAlbumDao;
import com.yhcrt.weihu.bbs.dao.BbsAlbumImgDao;
import com.yhcrt.weihu.bbs.entity.BbsAlbum;
import com.yhcrt.weihu.bbs.entity.BbsAlbumImg;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.manager.BbsAlbumMng;
import com.yhcrt.weihu.common.page.Page;

@Service
@Transactional
public class BbsAlbumMngImpl implements BbsAlbumMng{

	@Override
	public void updateCount(Integer albumId){
		BbsAlbum album = dao.findById(albumId);
		List<BbsAlbumImg> list = bbsAlbumImgDao.getList(albumId);
		if(list != null){
			album.setCount(list.size());
		}else{
			album.setCount(0);
		}
		dao.update(album);
	}
	
	@Override
	public void setAlbumImg(String imgUrl,Integer albumId){
		BbsAlbum album = dao.findById(albumId);
		album.setImgUrl(imgUrl);
	}
	
	@Override
	public void rebuildImg(Integer albumId) {
		BbsAlbum album = dao.findById(albumId);
		album.setImgUrl(bbsAlbumImgDao.getListOne(albumId).get(0).getImgUrl());
	}

	@Override
	public void setDefultImg(Integer userId) {
		List<BbsAlbum> list = dao.getListNotImg(userId);
		for(int i=0; i<list.size(); i++){
			list.get(i).setImgUrl(bbsAlbumImgDao.getListOne(list.get(i).getId()).get(0).getImgUrl());
		}
	}

	@Override
	public List<BbsAlbum> getList(Integer userId) {
		return dao.getList(userId);
	}

	@Override
	public boolean isMyAlbum(Integer userId, Integer albumId) {
		return dao.isMyAlbum(userId, albumId);
	}

	@Override
	public void deleteById(Integer id) {
		BbsAlbum album = dao.findById(id);
		album.setStatus(1);
		dao.deleteById(album);
	}

	@Override
	public Page<BbsAlbum> getAlbumByUserId(Integer pageSize,Integer pageNo,Integer id) {
		return dao.getAlbumByUserId(pageSize,pageNo,id);
	}

	@Override
	public BbsAlbum save(String name,String description,Integer visitAuth,BbsUser user) {
		BbsAlbum album = new BbsAlbum();
		album.setCreater(user);
		album.setCreateTime(new Date());
		album.setDescription(description);
		album.setVisitAuth(visitAuth);
		album.setName(name);
		return dao.save(album);
	}

	@Override
	public BbsAlbum findById(Integer id) {
		return dao.findById(id);
	}

	private BbsAlbumDao dao;
	private BbsAlbumImgDao bbsAlbumImgDao;
	
	@Autowired
	public void setBbsAlbumDao(BbsAlbumDao dao){
		this.dao = dao;
	}
	@Autowired
	public void setBbsAlbumImgDao(BbsAlbumImgDao bbsAlbumImgDao){
		this.bbsAlbumImgDao = bbsAlbumImgDao;
	}
	
}
