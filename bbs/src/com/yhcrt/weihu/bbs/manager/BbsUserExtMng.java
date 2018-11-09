package com.yhcrt.weihu.bbs.manager;

import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.entity.BbsUserExt;

public interface BbsUserExtMng {
	public BbsUserExt update(BbsUserExt ext);
	
	public BbsUserExt findById(Integer id);
	
	public BbsUserExt save(BbsUserExt ext, BbsUser user);

	public BbsUserExt update(BbsUserExt ext, BbsUser user);
}