package com.yhcrt.weihu.bbs.manager.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.mail.MessagingException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.bbs.Constants;
import com.yhcrt.weihu.bbs.dao.BbsSendCaptchaDao;
import com.yhcrt.weihu.bbs.entity.BbsSendCaptcha;
import com.yhcrt.weihu.bbs.manager.BbsSendCaptchaMng;
import com.yhcrt.weihu.bbs.manager.BbsUserMng;
import com.yhcrt.weihu.common.email.EmailSendTool;
import com.yhcrt.weihu.common.email.EmailSender;
import com.yhcrt.weihu.common.email.MessageTemplate;
import com.yhcrt.weihu.core.entity.UnifiedUser;

@Service
@Transactional
public class BbsSendCaptchaMngImpl implements BbsSendCaptchaMng {

	@Override
	public boolean isExpire(Integer userId,String captcha,String type) {
		return bbsSendCaptchaDao.isExpire(userId,captcha,type);
	}

	@Override
	public BbsSendCaptcha findByUserIdAndType(Integer id,String type) {
		return bbsSendCaptchaDao.findByUserIdAndType(id,type);
	}

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
	@Override
	public void sendCaptcha(EmailSender email, MessageTemplate tpl, UnifiedUser user, String to,
			String type,String captcha,String bunisessType) throws UnsupportedEncodingException, MessagingException {
		if(StringUtils.isBlank(type)){
			return ;
		}
		BbsSendCaptcha bbsSendCaptcha = bbsSendCaptchaDao.findByUserIdAndType(user.getId(),bunisessType);
		if(bbsSendCaptcha == null){
			bbsSendCaptcha = new BbsSendCaptcha();
			bbsSendCaptcha.setCreater(bbsUserMng.findById(user.getId()));
			bbsSendCaptcha.setExpireTime(new Timestamp(System.currentTimeMillis()+Constants.CAPTCHA_EXPIRE_TIME*1000));
			bbsSendCaptcha.setTarget(to);
			if(type.equals("1")){
				bbsSendCaptcha.setBusinessType(Constants.CAPTCHA_BUSINESS_PASSWORD_EMAIL);
			}else if(type.equals("2")){
				bbsSendCaptcha.setBusinessType(Constants.CAPTCHA_BUSINESS_PASSWORD_PHONE);
			}else if(type.equals("3")){
				bbsSendCaptcha.setBusinessType(Constants.CAPTCHA_BUSINESS_BINDING_PHONE);
			}
			bbsSendCaptcha.setCaptcha(captcha);
			bbsSendCaptchaDao.save(bbsSendCaptcha);
		}else{
			bbsSendCaptcha.setCreater(bbsUserMng.findById(user.getId()));
			bbsSendCaptcha.setExpireTime(new Timestamp(System.currentTimeMillis()+Constants.CAPTCHA_EXPIRE_TIME*1000));
			bbsSendCaptcha.setTarget(to);
			if(type.equals("1")){
				bbsSendCaptcha.setBusinessType(Constants.CAPTCHA_BUSINESS_PASSWORD_EMAIL);
			}else if(type.equals("2")){
				bbsSendCaptcha.setBusinessType(Constants.CAPTCHA_BUSINESS_PASSWORD_PHONE);
			}else if(type.equals("3")){
				bbsSendCaptcha.setBusinessType(Constants.CAPTCHA_BUSINESS_BINDING_PHONE);
			}
			bbsSendCaptcha.setCaptcha(captcha);
		}
		
		if(type.equals("1")){
			sendEmailCaptcha(email, tpl, user, captcha, to);
		}else if(type.equals("2")){
			
		}else if(type.equals("3")){
			//此处无需发送操作，因为发送成功之后才进行的数据库插入数据
		}
	}

	private void sendEmailCaptcha(final EmailSender email,final MessageTemplate tpl,final UnifiedUser user,
			final String captcha,final String toEmail) throws UnsupportedEncodingException, MessagingException {
		String text = tpl.getForgotPasswordText();
		text = StringUtils.replace(text, "${uid}", user.getId()+"");
		text = StringUtils.replace(text, "${username}", user.getUsername());
		text = StringUtils.replace(text, "${captcha}", captcha);
		EmailSendTool sendEmail = new EmailSendTool(email.getHost(), email
				.getUsername(), email.getPassword(), toEmail, tpl
				.getForgotPasswordSubject(), text, email.getPersonal(), "", "");
		sendEmail.send();
	}
	
	private BbsSendCaptchaDao bbsSendCaptchaDao;
	@Autowired
	private BbsUserMng bbsUserMng;
	
	@Autowired
	public void setBbsSendCaptchaDao(BbsSendCaptchaDao bbsSendCaptchaDao){
		this.bbsSendCaptchaDao = bbsSendCaptchaDao;
	}
	
}
