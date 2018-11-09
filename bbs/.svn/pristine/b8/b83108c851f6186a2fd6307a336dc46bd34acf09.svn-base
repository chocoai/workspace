package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsAlbum;
import com.yhcrt.weihu.common.page.Page;

public interface BbsAlbumDao {
	
	public void update(BbsAlbum album);
	public List<BbsAlbum> getListNotImg(Integer userId);
	public List<BbsAlbum> getList(Integer userId);
	public boolean isMyAlbum(Integer userId,Integer albumId);
	public void deleteById(BbsAlbum album);
	public Page<BbsAlbum> getAlbumByUserId(Integer pageSize,Integer pageNo,Integer id);
	public BbsAlbum save(BbsAlbum album);
	public BbsAlbum findById(Integer id);
	
}
