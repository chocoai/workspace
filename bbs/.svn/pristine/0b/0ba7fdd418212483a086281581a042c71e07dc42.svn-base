package com.yhcrt.weihu.bbs.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.bbs.dao.BbsAlbumImgDao;
import com.yhcrt.weihu.bbs.entity.BbsAlbum;
import com.yhcrt.weihu.bbs.entity.BbsAlbumImg;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.manager.BbsAlbumImgMng;
import com.yhcrt.weihu.bbs.manager.BbsAlbumMng;
import com.yhcrt.weihu.common.page.Page;

@Service
@Transactional
public class BbsAlbumImgMngImpl implements BbsAlbumImgMng{

	@Override
	public void deleteById(Integer id){
		BbsAlbumImg albumImg = dao.findById(id);
		albumImg.setStatus(1);
		BbsAlbum album = bbsAlbumMng.findById(albumImg.getAlbum().getId());
		album.setCount(album.getCount()-1);
		if(albumImg.getAlbum().getImgUrl().equals(albumImg.getImgUrl())){
			if(album.getCount() <= 0){
				album.setImgUrl(null);
			}else{
				bbsAlbumMng.rebuildImg(albumImg.getAlbum().getId());
			}
		}
		dao.deleteById(albumImg);
	}
	
	@Override
	public Page<BbsAlbumImg> getImgByAlbumId(Integer pageSize,Integer pageNo,Integer albumId) {
		return dao.getImgByAlbumId(pageSize, pageNo, albumId);
	}

	@Override
	public BbsAlbumImg save(String name,String description,BbsUser user,String imgUrl,Integer albumId) {
		BbsAlbumImg albumImg = new BbsAlbumImg();
		BbsAlbum album = bbsAlbumMng.findById(albumId);
		albumImg.setAlbum(album);
		albumImg.setCreateTime(new Date());
		albumImg.setCreater(user);
		albumImg.setName(name);
		albumImg.setDescription(description);
		albumImg.setImgUrl(imgUrl);
		return dao.save(albumImg);
	}

	@Override
	public BbsAlbumImg findById(Integer id) {
		return dao.findById(id);
	}

	private BbsAlbumImgDao dao;
	private BbsAlbumMng bbsAlbumMng;
	
	@Autowired
	public void setBbsAlbumImgDao(BbsAlbumImgDao dao){
		this.dao = dao;
	}
	@Autowired
	public void setBbsAlbumMng(BbsAlbumMng bbsAlbumMng){
		this.bbsAlbumMng = bbsAlbumMng;
	}
	
}
