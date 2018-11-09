package com.yhcrt.weihu.bbs.manager;

import com.yhcrt.weihu.bbs.entity.BbsAlbumImg;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.common.page.Page;

public interface BbsAlbumImgMng {

	public void deleteById(Integer id);
	public Page<BbsAlbumImg> getImgByAlbumId(Integer pageSize,Integer pageNo,Integer albumId);
	public BbsAlbumImg save(String name,String description,BbsUser user,String imgUrl,Integer albumId);
	public BbsAlbumImg findById(Integer id);
	
}
