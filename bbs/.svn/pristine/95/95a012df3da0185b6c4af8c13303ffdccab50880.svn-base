package com.yhcrt.weihu.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsSendCaptchaDao;
import com.yhcrt.weihu.bbs.entity.BbsSendCaptcha;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;

@Repository
public class BbsSendCaptchaDaoImpl extends HibernateBaseDao<BbsSendCaptcha, Integer> implements BbsSendCaptchaDao{

	@Override
	public boolean isExpire(Integer userId,String captcha,String type) {
		String hql = "select count(*) from BbsSendCaptcha s where s.creater.id="+userId
				+" and s.businessType='"+type+"' and s.captcha='"+captcha+"' and s.expireTime>NOW()";
		Object obj = getSession().createQuery(hql).uniqueResult();
		if(obj == null){
			return true;
		}else{
			if(((Number)obj).intValue()>0){
				return false;
			}else{
				return true;
			}
		}
	}

	@Override
	public void save(BbsSendCaptcha bbsSendCaptcha) {
		getSession().save(bbsSendCaptcha);
		
	}
	
	@Override
	public BbsSendCaptcha findByUserIdAndType(Integer id,String type) {
		String hql = "from BbsSendCaptcha s where s.creater.id=? and s.businessType=?";
		return (BbsSendCaptcha)findUnique(hql, id,type);
	}

	@Override
	protected Class<BbsSendCaptcha> getEntityClass() {
		return BbsSendCaptcha.class;
	}

}
