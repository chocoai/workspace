package com.yhcrt.weihu.bbs.manager;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.yhcrt.weihu.bbs.entity.BbsSendCaptcha;
import com.yhcrt.weihu.common.email.EmailSender;
import com.yhcrt.weihu.common.email.MessageTemplate;
import com.yhcrt.weihu.core.entity.UnifiedUser;

public interface BbsSendCaptchaMng {

	/**
	 * @return   false:未过期    true:过期了
	 */
	public boolean isExpire(Integer userId,String captcha,String type);
	public BbsSendCaptcha findByUserIdAndType(Integer id,String Type);
	
	/**
	 * 发送验证码及发送记录保存方法
	 * @param email  发送邮件的工具
	 * @param tpl   邮件模板
	 * @param user  用户
	 * @param to   邮箱地址或手机号码
	 * @param type  类型：1：发送邮件，找回密码     2：发送短信：找回密码    3：发送短信，绑定手机
	 * @param captcha   验证码
	 * @param bunisessType   业务类型：邮件找回密码，短信找回密码，绑定手机等
	 * @throws UnsupportedEncodingException  发送邮件时的异常
	 * @throws MessagingException  发送邮件时的异常
	 */
	public void sendCaptcha(EmailSender email,MessageTemplate tpl,UnifiedUser user,String to,
			String type,String captcha,String bunisessType)throws UnsupportedEncodingException, MessagingException ;
	
}
