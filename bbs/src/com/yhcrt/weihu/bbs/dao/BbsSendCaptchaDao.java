package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.BbsSendCaptcha;

public interface BbsSendCaptchaDao {
	
	/**
	 * 验证码是否过期了
	 * @return  false:没有过期       true:过期了
	 */
	public boolean isExpire(Integer userId,String captcha,String type);
	public BbsSendCaptcha findByUserIdAndType(Integer id,String type);
	public void save(BbsSendCaptcha bbsSendCaptcha);
	
}
