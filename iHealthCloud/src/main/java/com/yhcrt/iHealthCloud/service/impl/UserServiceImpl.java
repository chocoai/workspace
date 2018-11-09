package com.yhcrt.iHealthCloud.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.common.Constants.MemberType;
import com.yhcrt.iHealthCloud.entity.Doctor;
import com.yhcrt.iHealthCloud.entity.Employee;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.MemberDevice;
import com.yhcrt.iHealthCloud.entity.MemberDeviceExample;
import com.yhcrt.iHealthCloud.entity.MessageCode;
import com.yhcrt.iHealthCloud.entity.SysUser;
import com.yhcrt.iHealthCloud.entity.ThirdLogin;
import com.yhcrt.iHealthCloud.mapper.DoctorMapper;
import com.yhcrt.iHealthCloud.mapper.EmployeeMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.SysUserMapper;
import com.yhcrt.iHealthCloud.mapper.ThirdLoginMapper;
import com.yhcrt.iHealthCloud.service.MessageCodeService;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.service.UserService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtils;
import com.yhcrt.iHealthCloud.util.Md5PwdEncoder;
import com.yhcrt.iHealthCloud.util.PhoneSecurityCode;
import com.yhcrt.iHealthCloud.util.SendSMSUtil;
import com.yhcrt.iHealthCloud.util.UUIDGenerator;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private MessageCodeService messageCodeService;
	@Autowired
	private SysSequenceService sysSequenceService;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private DoctorMapper doctorMapper;
	@Autowired
	private ThirdLoginMapper thirdLoginMapper;
	@Autowired
	private MemberDeviceMapper memberDeviceMapper;
	
	@Override
	public String getCaptcha(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String userCode = bizObj.getString("user_code");
		// 检查此手机号码是否已注册
		SysUser user = sysUserMapper.selectByUserCode(userCode);
		try {
			if (null != user) 
				throw new Exception(Constants.ExceptionMsg.PHONE_NUMBER_HAS_BEEN_REGISTERED);
			// 生成验证码
			String captcha = PhoneSecurityCode.getSecurityCode();
			// 发送验证码
			SendSMSUtil.send(captcha, userCode);
			// 保存验证码
			messageCodeService.insert(userCode, captcha, Const.CAPTCHA_TYPE_REGISTER);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "验证码发送成功");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String getTelCode(JSONObject pdataObj) {
		JSONObject bizObj = pdataObj.getJSONObject(Const.TAG_BIZ);
		String userCode = bizObj.getString("user_code");
		try {
			// 生成验证码
			String captcha = PhoneSecurityCode.getSecurityCode();
			// 发送验证码
			SendSMSUtil.send(captcha, userCode);
			// 保存验证码
			messageCodeService.insert(userCode, captcha, Const.CAPTCHA_TYPE_REGISTER);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "验证码发送成功");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String bindingTel(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String userCode = bizObj.getString("user_code");
		String password = bizObj.getString("password");
		String captcha = bizObj.getString("tel_code");
		String nickName = bizObj.getString("nickName");
		String headPic = bizObj.getString("headPic");
		String gender = bizObj.getString("gender");
		try {
			// 检查验证码是否正确
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("user_code", userCode);
			params.put("business_type", Const.CAPTCHA_TYPE_REGISTER);
			params.put("captcha", captcha);
			List<MessageCode> messageCodes = messageCodeService.listByParams(params);
			if (messageCodes.isEmpty()) 
				throw new Exception(Constants.ExceptionMsg.CAPTCHA_ERROR);
			MessageCode bean = messageCodes.get(0);
			long sendTime = DateUtils.stringToDate19(bean.getSendTime()).getTime();
			if ((System.currentTimeMillis() - sendTime) > Const.SMS_TIME_OUT * 60 * 1000) 
				throw new Exception(Constants.ExceptionMsg.CAPTCHA_TIME_OUT);
			// 检查此手机号码是否已注册
			SysUser user = sysUserMapper.selectByUserCode(userCode);
			if (null != user) {
				Integer type = user.getUserType(); //（-1:医师用户; 0：员工用户；1：用户）
				if(type==-1){
					Doctor doctor = doctorMapper.selectByUserId(user.getUserId());
					JSONObject doctorJson = (JSONObject) JSONObject.toJSON(doctor);
					doctorJson.put("userType", "-1");
					pdataObj.put(Const.TAG_BIZ, doctorJson);
				}else if(type==0){
					Employee employee = employeeMapper.selectByUserId(user.getUserId());
					JSONObject employeeJson = (JSONObject) JSONObject.toJSON(employee);
					employeeJson.put("userType", "0");
					pdataObj.put(Const.TAG_BIZ, employeeJson);
				}else if(type==1){
					Member member = memberMapper.selectByUserId(user.getUserId());
					JSONObject memberJson = (JSONObject) JSONObject.toJSON(member);
					MemberDeviceExample memberDeviceExample = new MemberDeviceExample();
					memberDeviceExample.createCriteria().andMemberIdEqualTo(member.getMemberId()).andStatusEqualTo(Constants.STATUS_NORMAL);
					List<MemberDevice> memberdeviceList = memberDeviceMapper.selectByExample(memberDeviceExample);
					if(memberdeviceList.size()>0){
						memberJson.put("userType", "1");
						memberJson.put("imei", memberdeviceList.get(0).getImei());
					}else{
						memberJson.put("userType", "2");
					}
					pdataObj.put(Const.TAG_BIZ, memberJson);
				}
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, "此手机号已经绑定");
			}else{
				register(userCode, password,nickName,headPic,Integer.parseInt(gender));
				SysUser userNew = sysUserMapper.selectByUserCode(userCode);
				Member member = memberMapper.selectByUserId(userNew.getUserId());
				JSONObject memberJson = (JSONObject) JSONObject.toJSON(member);
				MemberDeviceExample memberDeviceExample = new MemberDeviceExample();
				memberDeviceExample.createCriteria().andMemberIdEqualTo(member.getMemberId()).andStatusEqualTo(Constants.STATUS_NORMAL);
				List<MemberDevice> memberdeviceList = memberDeviceMapper.selectByExample(memberDeviceExample);
				if(memberdeviceList.size()>0){
					memberJson.put("userType", "1");
					memberJson.put("imei", memberdeviceList.get(0).getImei());
				}else{
					memberJson.put("userType", "2");
				}
				pdataObj.put(Const.TAG_BIZ, memberJson);
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "绑定成功");
			}

		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}

	@Override
	public String register(JSONObject pdataObj) {
		JSONObject bizObj = pdataObj.getJSONObject(Const.TAG_BIZ);
		String userCode = bizObj.getString("user_code");
		String password = bizObj.getString("password");
		String captcha = bizObj.getString("captcha");
		
		try {
			// 检查此手机号码是否已注册
			SysUser user = sysUserMapper.selectByUserCode(userCode);
			if (null != user) 
				throw new Exception(Constants.ExceptionMsg.PHONE_NUMBER_HAS_BEEN_REGISTERED);
			// 检查验证码是否正确
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("user_code", userCode);
			params.put("business_type", Const.CAPTCHA_TYPE_REGISTER);
			params.put("captcha", captcha);
			List<MessageCode> messageCodes = messageCodeService.listByParams(params);
			if (messageCodes.isEmpty()) 
				throw new Exception(Constants.ExceptionMsg.CAPTCHA_ERROR);
			MessageCode bean = messageCodes.get(0);
			long sendTime = DateUtils.stringToDate19(bean.getSendTime()).getTime();
			if ((System.currentTimeMillis() - sendTime) > Const.SMS_TIME_OUT * 60 * 1000) 
				throw new Exception(Constants.ExceptionMsg.CAPTCHA_TIME_OUT);
			register(userCode, password,null,null,null);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "注册成功");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	
	//微信小程序注册
	@Override
	public String registerSmall(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String nickname = bizObj.getString("nick_name");
		bizObj.put("nickName", nickname);
		String headpic = bizObj.getString("head_pic");
		bizObj.put("headPic", headpic);
		String openid = bizObj.getString("open_id");
		Integer sex = bizObj.getInteger("sex");
		pdataObj.put(Const.TAG_BIZ, bizObj);
		try {
            ThirdLogin thirdLogin = thirdLoginMapper.selectByUid(openid);
            if (null != thirdLogin) {
				throw new Exception("重复注册！");
            }else{
            	thirdLogin = new ThirdLogin();
            }
            thirdLogin.setCid(UUIDGenerator.getId());
            thirdLogin.setCreateTime(DateUtils.dateToString14(new Date()));
            thirdLogin.setHeadPic(headpic);
            thirdLogin.setNickName(nickname);
            thirdLogin.setLoginType("small"); 
            thirdLogin.setSex(sex);
            thirdLogin.setStatus(0);
            thirdLogin.setUid(openid);
            thirdLoginMapper.insert(thirdLogin);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "注册成功");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String login(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String userCode = bizObj.getString("user_code");
		String password = bizObj.getString("password");
		String loginType = bizObj.getString("login_type");
		SysUser user = sysUserMapper.selectByUserCode(userCode);
		try {
			if (null == user)
				throw new Exception(Constants.ExceptionMsg.USER_NOT_EXIST);
			if (!Md5PwdEncoder.isPasswordValid(user.getPassword(), password))
				throw new Exception(Constants.ExceptionMsg.PASSWORD_ERROR);
			if (user.getUserType() == Constants.UserType.USER_TYPE_MEMBER
					&& Constants.LoginType.XKGJ.equalsIgnoreCase(loginType))
				throw new Exception(Constants.ExceptionMsg.USER_NOT_EXIST);
			if (user.getUserType() == Constants.UserType.USER_TYPE_MEMBER) {
				Member member = memberMapper.selectByUserId(user.getUserId());
				pdataObj.put(Const.TAG_BIZ, member);
			} else if (user.getUserType() == Constants.UserType.USER_TYPE_EMP) {
				Employee emp = employeeMapper.selectByUserId(user.getUserId());
				pdataObj.put(Const.TAG_BIZ, emp);
			} else if (user.getUserType() == Constants.UserType.USER_TYPE_DOC) {
				Doctor doc = doctorMapper.selectByUserId(user.getUserId());
				pdataObj.put(Const.TAG_BIZ, doc);
			} else {
				pdataObj.put(Const.TAG_BIZ, bizObj);
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "登录成功");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}
	
	@Override
	public String getCaptchaRetrievePassword(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String userCode = bizObj.getString("user_code");
		// 检查此手机号码是否已注册
		SysUser user = sysUserMapper.selectByUserCode(userCode);
		try {
			if (null == user) 
				throw new Exception(Constants.ExceptionMsg.USER_NOT_EXIST);
			// 生成验证码
			String captcha = PhoneSecurityCode.getSecurityCode();
			// 发送验证码
			SendSMSUtil.send(captcha, userCode);
			// 保存验证码
			messageCodeService.insert(userCode, captcha, Const.CAPTCHA_TYPE_RESETPWD);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "验证码发送成功");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String checkCaptchaRetrievePassword(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String userCode = bizObj.getString("user_code");
		String captcha = bizObj.getString("captcha");
		// 检查验证码是否正确
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("user_code", userCode);
		params.put("business_type", Const.CAPTCHA_TYPE_RESETPWD);
		params.put("captcha", captcha);
		List<MessageCode> messageCodes = messageCodeService.listByParams(params);
		try {
			if (messageCodes.isEmpty()) {
				throw new Exception(Constants.ExceptionMsg.CAPTCHA_ERROR);
			}
			MessageCode bean = messageCodes.get(0);
			long sendTime = DateUtils.stringToDate19(bean.getSendTime()).getTime();
			if ((System.currentTimeMillis() - sendTime) > Const.SMS_TIME_OUT * 60 * 1000) {
				throw new Exception(Constants.ExceptionMsg.CAPTCHA_TIME_OUT);
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}

	@Override
	public String resetPassword(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String userCode = bizObj.getString("user_code");
		String password = bizObj.getString("password");
		SysUser user = sysUserMapper.selectByUserCode(userCode);
		try {
			if (null == user) {
				throw new Exception(Constants.ExceptionMsg.USER_NOT_EXIST);
			}
			user.setPassword(Md5PwdEncoder.encodePassword(password));
			sysUserMapper.updateByUserCode(user);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}

	@Override
	public String updateProfile(JSONObject pdataObj) {
		String bizStr = pdataObj.getString(Const.TAG_BIZ);
		Member member = JSON.parseObject(bizStr, Member.class);
		try {
			Member bean = memberMapper.selectByPrimaryKey(member.getMemberId());
			if (bean == null) {
				throw new Exception("member info not found");
			}
			bean.setRealName(member.getRealName());
			bean.setNickName(member.getNickName());
			bean.setGender(member.getGender());
			bean.setIdentityCard(member.getIdentityCard());
			bean.setPhoneNo(member.getPhoneNo());
			bean.setEmail(member.getEmail());
			bean.setUpdateTime(DateUtils.getCurrentTime());
			if(null != member.getHeadPic() && !"".equals(member.getHeadPic())){
				bean.setHeadPic(member.getHeadPic());
			}
			memberMapper.updateByPrimaryKeySelective(bean);
			member = memberMapper.selectByPrimaryKey(bean.getMemberId());
			pdataObj.put(Const.TAG_BIZ, member);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String updatePassword(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String userCode = bizObj.getString("user_code");
		String old_password = bizObj.getString("old_password");
		String new_password = bizObj.getString("new_password");
		SysUser user = sysUserMapper.selectByUserCode(userCode);
		try {
			if (null == user) {
				throw new Exception(Constants.ExceptionMsg.USER_NOT_EXIST);
			}
			if (!Md5PwdEncoder.isPasswordValid(user.getPassword(), old_password)) {
				throw new Exception(Constants.ExceptionMsg.ORIGINAL_PASSWORD_ERROR);
			}
			user.setPassword(Md5PwdEncoder.encodePassword(new_password));
			sysUserMapper.updateByUserCode(user);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "update password successfully");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	
	public void register(String userCode,String password,String nickName,String headPic,Integer gender){
		SysUser sysUser = new SysUser();
		sysUser.setUserId(sysSequenceService.getSequenceValue(Constants.SequenceName.SYS_USER));
		sysUser.setUserCode(userCode);
		sysUser.setPassword(Md5PwdEncoder.encodePassword(password));
		sysUser.setUserType(Constants.UserType.USER_TYPE_MEMBER);
		sysUser.setStatus(Constants.STATUS_NORMAL);
		sysUser.setCreateTime(DateUtils.getCurrentTime());
		sysUserMapper.insert(sysUser);
		
		Member member = new Member();
		member.setMemberId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEMBER));
		member.setPhoneNo(userCode);
		member.setCreateTime(DateUtils.getCurrentTime());
		member.setMemberType(MemberType.COMMON);
		member.setUserId(sysUser.getUserId());
		member.setStatus(Constants.STATUS_NORMAL);
		member.setNickName(nickName);
		member.setHeadPic(headPic);
		member.setGender(gender);
		memberMapper.insert(member);
	}

	@Override
	public String getUserProfile(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String userId = bizObj.getString("user_id");
		try {
			SysUser user = sysUserMapper.selectByPrimaryKey(Integer.parseInt(userId));
			if (null == user) 
				throw new Exception(Constants.ExceptionMsg.USER_NOT_EXIST);
			if (user.getUserType() == Constants.UserType.USER_TYPE_EMP) {
				Employee emp = employeeMapper.selectByUserId(user.getUserId());
				pdataObj.put(Const.TAG_BIZ, JSON.toJSON(emp));
			}else if (user.getUserType() == Constants.UserType.USER_TYPE_DOC){
				Doctor doc = doctorMapper.selectByUserId(user.getUserId());
				pdataObj.put(Const.TAG_BIZ, JSON.toJSON(doc));
			}else if(user.getUserType() == Constants.UserType.USER_TYPE_MEMBER){
				Member member = memberMapper.selectByUserId(user.getUserId());
				pdataObj.put(Const.TAG_BIZ, JSON.toJSON(member));
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.toString());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}

	@Override
	public String updateUserProfile(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String userId = bizObj.getString("user_id");
		String nickName = bizObj.getString("nick_name");
		String headPic = bizObj.getString("head_pic");
		String realName = bizObj.getString("real_name");
		String specialty = bizObj.getString("specialty");
		String sex = bizObj.getString("sex");
		String phoneNo = bizObj.getString("phone_no");
		String email = bizObj.getString("email");
		String identityCard = bizObj.getString("identity_card");
		try {
			SysUser user = sysUserMapper.selectByPrimaryKey(Integer.parseInt(userId));
			if (user.getUserType() == Constants.UserType.USER_TYPE_EMP) {
				Employee emp = employeeMapper.selectByUserId(user.getUserId());
				emp.setNickName(nickName);
				emp.setIdentityCard(identityCard);
				emp.setHeadPic(headPic);
				emp.setRealName(realName);
				emp.setSpecialty(specialty);
				emp.setSex(Integer.parseInt(sex));
				emp.setPhoneNo(phoneNo);
				emp.setEmail(email);
				employeeMapper.updateByPrimaryKeySelective(emp);
				pdataObj.put(Const.TAG_BIZ, JSON.toJSON(emp));
			}else if (user.getUserType() == Constants.UserType.USER_TYPE_DOC){
				Doctor doc = doctorMapper.selectByUserId(user.getUserId());
				doc.setNickName(nickName);
				doc.setIdentityCard(identityCard);
				doc.setHeadPic(headPic);
				doc.setRealName(realName);
				doc.setSpecialty(specialty);
				doc.setSex(Integer.parseInt(sex));
				doc.setPhoneNo(phoneNo);
				doc.setEmail(email);
				doctorMapper.updateByPrimaryKeySelective(doc);
				pdataObj.put(Const.TAG_BIZ, JSON.toJSON(doc));
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			requestFailed(pdataObj, e.toString());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}

	@Override
	public String thirdLogin(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String uid = bizObj.getString("uid");
		// String loginType = bizObj.getString("login_type");
		ThirdLogin thirdLogin = thirdLoginMapper.selectByUid(uid);
		if (thirdLogin == null) {
			bizObj.put("is_auth", false);
		}else{
			bizObj.put("is_auth", true);
			bizObj.put("profile", thirdLogin.getMember());
		}
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		pdataObj.put(Const.TAG_RMK, "");
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String thirdLoginCaptcha(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String userCode = bizObj.getString("phone_num");
		try {
			// 生成验证码
			String captcha = PhoneSecurityCode.getSecurityCode();
			// 发送验证码
			SendSMSUtil.send(captcha, userCode);
			// 保存验证码
			messageCodeService.insert(userCode, captcha, Const.CAPTCHA_TYPE_THIRDLOGIN);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "验证码发送成功");
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public String thirdLoginBindPhone(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String userCode = bizObj.getString("phone_num");
		String captcha = bizObj.getString("captcha");
		String uid = bizObj.getString("uid");
		String loginType = bizObj.getString("login_type");
		
		String nickName = bizObj.getString("nick_name");
		String gender = bizObj.getString("gender");
		String headPic = bizObj.getString("head_pic");
		
		try {
			// 检查验证码是否正确
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("user_code", userCode);
			params.put("business_type", Const.CAPTCHA_TYPE_THIRDLOGIN);
			params.put("captcha", captcha);
			List<MessageCode> messageCodes = messageCodeService.listByParams(params);
			if (messageCodes.isEmpty()){
				requestFailed(pdataObj, Constants.ExceptionMsg.CAPTCHA_ERROR);
				return pdataObj.toJSONString();
			}
			MessageCode bean = messageCodes.get(0);
			long sendTime = DateUtils.stringToDate19(bean.getSendTime()).getTime();
			if ((System.currentTimeMillis() - sendTime) > Const.SMS_TIME_OUT * 60 * 1000){
				requestFailed(pdataObj, Constants.ExceptionMsg.CAPTCHA_TIME_OUT);
				return pdataObj.toJSONString();
			}
			ThirdLogin thirdLogin = new ThirdLogin();
			// 检查此手机号码是否已注册
			SysUser user = sysUserMapper.selectByUserCode(userCode);
			if (null == user){
				SysUser sysUser = new SysUser();
				sysUser.setUserId(sysSequenceService.getSequenceValue(Constants.SequenceName.SYS_USER));
				sysUser.setUserCode(userCode);
				sysUser.setUserType(Constants.UserType.USER_TYPE_MEMBER);
				sysUser.setStatus(Constants.STATUS_NORMAL);
				sysUser.setCreateTime(DateUtils.getCurrentTime());
				sysUserMapper.insert(sysUser);
				thirdLogin.setUserId(sysUser.getUserId().toString());
				
				Member member = new Member();
				member.setMemberId(sysSequenceService.getSequenceValue(Constants.SequenceName.MEMBER));
				member.setPhoneNo(userCode);
				member.setCreateTime(DateUtils.getCurrentTime());
				member.setMemberType(MemberType.COMMON);
				member.setUserId(sysUser.getUserId());
				member.setStatus(Constants.STATUS_NORMAL);
				member.setHeadPic(headPic);
				member.setNickName(nickName);
				member.setGender(Integer.parseInt(gender));
				memberMapper.insert(member);
				pdataObj.put(Const.TAG_BIZ, JSON.toJSON(member));
			}else{
				thirdLogin.setUserId(user.getUserId().toString());
				Member member = memberMapper.selectByUserId(user.getUserId());
				pdataObj.put(Const.TAG_BIZ, JSON.toJSON(member));
			}
			
			thirdLogin.setCid(UUIDGenerator.getId());
			thirdLogin.setLoginType(loginType);
			thirdLogin.setUid(uid);
			thirdLogin.setCreateTime(DateUtils.getCurrentTime());
			thirdLogin.setStatus(Constants.STATUS_NORMAL);
			thirdLoginMapper.insert(thirdLogin);
			
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdataObj);
	}

}
