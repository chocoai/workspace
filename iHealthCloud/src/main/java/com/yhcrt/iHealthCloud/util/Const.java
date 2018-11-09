package com.yhcrt.iHealthCloud.util;

public interface Const {
	public static final boolean IS_DEBUG = true;
	
	public static final String TAG_PDATA = "pdata"; // pdata

	public static final String TAG_BID = "bid"; // 2013082719093303452",

	public static final String TAG_UID = "uid"; // 18971690300",

	public static final String TAG_SID = "sid"; // health.dazhetuan.list",

	public static final String TAG_VER = "ver"; // 1",

	public static final String TAG_TOK = "tok"; // 0~9",

	// 下面三个参数 在分页请求时会出现此参数
	/**
	 * 返回总页数
	 */
//	public static final String TAG_TOTALPAGE = "totalpage";
	/**
	 * 返回当前页显示条数
	 */
//	public static final String TAG_CURRENTPAGE = "currentpage";
	/**
	 * 返回总条数
	 */
	public static final String TAG_TOTALCOUNT = "totalCount";
	
	// 通用的部分参数
	public static final String TAG_CID = "cid";
	public static final String TAG_MEMBER_ID = "member_id";
	public static final String TAG_START_TIME = "start_time";
	public static final String TAG_END_TIME = "end_time";
	public static final String TAG_RESULT = "result";
	public static final String TAG_IMEI = "imei";
	public static final String TAG_DATA_DATE = "data_date";
	public static final String TAG_USER_ID = "user_id";
	public static final String TAG_PASSWORD = "passwrod";
	public static final String TAG_CASE = "case";
	public static final String TAG_MAX = "max";
	public static final String TAG_MIN = "min";
	public static final String TAG_AVE = "ave";
	
	// 血糖参数
	public static final String TAG_BG_VALUE = "bg_value";
	public static final String TAG_BG_TYPE = "bg_type";
	
	// 血压参数
	public static final String TAG_SBP = "sbp";
	public static final String TAG_DBP = "dbp";
	
	// 运动量参数
	public static final String TAG_STEP_COUNT = "step_count";
	
	// 心率参数
	public static final String TAG_PLUSE = "pluse";
	
	// 睡眠参数
	public static final String TAG_SLEEP = "sleep";
	
	// 用户建议
	public static final String TAG_SUGGESTION_CONTENT = "suggestion_content";
	
	// 用户指南url
	public static final String HELP_FOR_APP_URL = "userDirection/index.html";
	public static final String TAG_DIRECTION_APP_URL = "userDirection/appshare.html";
	public static final String TAG_DIRECTION_WATCH_URL = "http://www.jd.com";
	
	// member conditions
	public static final String TAG_ORG_ID = "org_id";
	public static final String TAG_REAL_NAME = "real_name";
	public static final String TAG_IDENTITY_CARD = "identity_card";
	
	// 设备
	public static final String TAG_DEVICE_ID = "device_id";
	public static final String TAG_DEVICE_TYPE = "device_type";
	public static final String TAG_DEVICE_SIM = "sim";
	public static final String TAG_DEVICE_NAME = "device_name";
	public static final String TAG_DEVCIE_ISUSED = "is_used";
	
	// 分页信息
	public static final String TAG_CURRENT_PAGE = "current_page";
	public static final String TAG_TOTAL_PAGE = "total_page";
	public static final String TAG_PAGE_SIZE = "page_size";
	
	// 体检报告
	public static final String TAG_MER_ID = "mer_id";
	
	// 健康档案
	public static final String TAG_OPERATION_LIST = "operations";
	public static final String TAG_TRANSFUSION_LIST = "transfusions";
	public static final String TAG_TRAUMALIST_LIST = "traumas";
	
	
	// rmk sentence
	public static final String RMK_BIZ_PARAM_NULL = "error: some param of the 'biz' json object is null or unfound";
	public static final String RMK_SID_UNDEFINED = "the pdata's param 'sid' is undefined";
	
	// gender
	public static final String GENDER_MAN = "男";
	public static final String GENDER_WOMAN = "女";
	public static final String GENDER_UNDEFINED = "未知";
	
	// mer
	public static final String NO_MER_RECORD = "您目前还没有体检报告哦！";
	

	public static final String TAG_TYPE = "type"; //

	public static final String TAG_STS = "sts";// 0,

	public static final String TAG_RMK = "rmk"; // ",

	public static final String TAG_BIZ = "biz";//

	public static final String TAG_STS_FAIL = "0";// 0,

	public static final String TAG_STS_SUCCESS = "1";// 0,
	
	public static final String TAG_FAIL = "1";// 1,

	public static final String TAG_SUCCESS = "0";// 0,
	
	public static final String ERROR_DESC = "error_desc";

	/**
	 * UTF-8编码
	 */
	public static final String UTF8 = "UTF-8";

	/**
	 * HTTP POST请求
	 */
	public static final String POST = "POST";

	/**
	 * HTTP GET请求
	 */
	public static final String GET = "GET";
	
	public static final String CAPTCHA_TYPE_REGISTER = "Register";
	
	public static final String CAPTCHA_TYPE_RESETPWD = "resetPwd";
	
	public static final String CAPTCHA_TYPE_THIRDLOGIN = "thirdLogin";
	
	public static final Integer SMS_TIME_OUT = 5; // 发送验证码超时时限(分钟)
	/***订单有效时长(秒)***/
	public static final Integer VALID = 1800;

}
