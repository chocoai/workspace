package com.whty.mxbj.common.constants;

import com.whty.mxbj.common.utils.SysConfigUtils;

/**
 * 系统常量
 * 
 * @author zhangzheng
 *
 */
public final class SysConstants {
	public static final String ARCHIVE_NOTE = "收藏";

	public static final String PRODUCT_NAME = "墨香笔记";

	public static final String MANAGE_USER_INVALID = "1";// 用户状态0可用 1不可用

	public static final String CACHE_KEY_SEPERATER = "_";

	public static final String BASE_PROPERTY_CACHE_KEY = "base_property_cache_key";

	public final static String NOTES_VERSION = SysConfigUtils.getStrValue("notes_version");

	public final static String NOTES_IDS = SysConfigUtils.getStrValue("notes_ids");

	public static final String USER_THRID_ACCOUNT = "000020";

	public static final String TWO_PWD_EQ = "000210";
	public static final String TWO_PWD_EQ_MESSAGE = "修改后的密码不能和原密码相同.";

	public static final String USER_NOT_EXIST = "000001";
	public static final String WRONG_PASSWARD = "000002";
	public static final String USER_FROZEN = "000003";
	public static final String ACCOUNT_IS_INACTIVE = "000004";
	public static final String ACCOUNT_IS_DISABLE = "000005";
	public static final String MANAY_USER = "000006";
	public static final String USER_SSIONID_VERIFY_FAIL = "000007";
	public static final String PLATFORM_UNUSED = "000008";
	public static final String NOT_TEACHER = "000009";
	public static final String ONE_OF_MANY = "000010";

	public static final String LOGIN_FROM_HUIJIAOYUN_FAIL = "000012";
	public static final String VERIFY_CODE_HAS_SENDED = "000013";
	public static final String VERIFY_CODE_SEND_FAIL = "000014";
	public static final String VERIFY_CODE_CHECK_FAIL = "000015";
	public static final String USER_EXIST_IN_HUIJIAOYUN = "000016";

	public static final String VERIFY_CODE_SEND_EXCEPTION = "000017";

	public static final String USER_NULL_CODE = "301001";

	public static final String MSG_USER_NULL = "用户不存在";
	public static final String MSG_USER_RE = "用户已注册";

	public static final String MSG_USER_THRID_ACCOUNT = "该手机是第三方帐号，请在教育云登录页面找回密码.";
	// 登录接口状态提示
	public static final String MSG_SUCCESS_CODE = "用户登陆成功";
	public static final String MSG_FAIL_CODE = "登录接口返回空";
	public static final String MSG_USER_NOT_EXIST = "账号不存在，请重新输入";
	public static final String MSG_WRONG_PASSWARD = "密码错误，请重新输入";
	public static final String MSG_USER_FROZEN = "账号被冻结，请联系在线客服";
	public static final String MSG_ACCOUNT_IS_INACTIVE = "此账号没有激活";
	public static final String MSG_ACCOUNT_IS_DISABLE = "帐号被禁用";
	public static final String MSG_MANAY_USER = "用户存在重复";
	public static final String MSG_USER_SSIONID_VERIFY_FAIL = "userSession验证失败";
	public static final String MSG_PLATFORM_UNUSED = "登录的平台还未接入";
	public static final String MSG_NOT_TEACHER = "非老师帐号不能登录";
	public static final String MSG_ONE_OF_MANY = "多平台帐号只接入了一个平台";

	public static final String MSG_VERIFY_CODE_SEND_EXCEPTION = "验证码发送异常";

	public static final String FAIL_COMPUTER_INFO_SAME_CODE = "102006";
	public static final String FAIL_COMPUTER_INFO_NOSAME_CODE = "102007";

	public static final String MSG_LOGIN_FROM_HUIJIAOYUN_FAIL = "登录失败，请输入正确的账号和密码";
	public static final String MSG_VERIFY_CODE_HAS_SENDED = "注册验证码已发送";
	public static final String MSG_VERIFY_CODE_SEND_FAIL = "注册验证码发送失败，请重新获取注册验证码";
	public static final String MSG_VERIFY_CODE_CHECK_FAIL = "验证码验证失败，请重新获取验证码";
	public static final String MSG_USER_EXIST_IN_HUIJIAOYUN = "您的手机号已在慧教云/教育云注册，可以直接登录或者从慧教云/教育云登录";

	public static final String FILE_USER_REGISTER_CODE = "000017";
	public static final String MSG_USER_REGISTER = "您的手机号已经注册，请直接登录";

	public static final String MULTI_USER_PLATFORM_CODE = "040001";// 多个平台
	public static final String MULTI_USER_PLATFORM_MESSAGE = "该手机号绑定了多个平台";

	public static final String FILE_PLATFORM_CODE = "000031";
	public static final String MSG_PLATFORM_CODE = "platformCode平台编码不能空";

	public static final String FILE_USER_ID_CODE = "000032";
	public static final String MSG_USER_ID_CODE = "userId平台编码不能空";
}
