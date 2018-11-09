package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsAlbumImg;
import com.yhcrt.weihu.common.page.Page;

public interface BbsAlbumImgDao {

	public List<BbsAlbumImg> getList(Integer albumId);
	public List<BbsAlbumImg> getListOne(Integer albumId);
	public void deleteById(BbsAlbumImg albumImg);
	public Page<BbsAlbumImg> getImgByAlbumId(Integer pageSize,Integer pageNo,Integer albumId);
	public BbsAlbumImg save(BbsAlbumImg album);
	public BbsAlbumImg findById(Integer id);
	
}
