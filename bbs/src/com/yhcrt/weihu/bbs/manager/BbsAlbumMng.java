package com.yhcrt.weihu.bbs.manager;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsAlbum;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.common.page.Page;

public interface BbsAlbumMng {

	public void updateCount(Integer albumId);
	public void setAlbumImg(String imgUrl,Integer albumId);
	public void rebuildImg(Integer albumId);
	public void setDefultImg(Integer userId);
	public List<BbsAlbum> getList(Integer userId);
	public boolean isMyAlbum(Integer userId,Integer albumId);
	public void deleteById(Integer id);
	public Page<BbsAlbum> getAlbumByUserId(Integer pageSize,Integer pageNo,Integer id);
	public BbsAlbum save(String name,String description,Integer visitAuth,BbsUser user);
	public BbsAlbum findById(Integer id);
	
}
