package com.yhcrt.healthcloud.common;

/**
 * @author rpf 常量表
 * 
 */
public interface Constants {
	/**
	 * 异常信息统一头信息<br>
	 * 非常遗憾的通知您,程序发生了异常
	 */
	public static final String EXCEPTION_HEAD = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :";
	/** 操作名称 */
	public static final String OPERATION_NAME = "OPERATION_NAME";
	/** 客户端语言 */
	public static final String USERLANGUAGE = "userLanguage";
	/** 客户端主题 */
	public static final String WEBTHEME = "webTheme";
	/** 当前用户 */
	public static final String CURRENT_USER = "CURRENT_USER";
	/** 在线用户数量 */
	public static final String ALLUSER_NUMBER = "ALLUSER_NUMBER";
	/** 登录用户数量 */
	public static final String USER_NUMBER = "USER_NUMBER";
	/** 上次请求地址 */
	public static final String PREREQUEST = "PREREQUEST";
	/** 上次请求时间 */
	public static final String PREREQUEST_TIME = "PREREQUEST_TIME";
	/** 登录地址 */
	public static final String LOGIN_URL = "/login.html";
	/** 非法请求次数 */
	public static final String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";

	// socket 通讯相关常量
	// 电话本更新
	public final static String FUN_TEL = "S57";
	// 心率阀值设置
	public final static String FUN_HEART_RATE_LIMIT = "47";
	// 联系人设置
	public final static String FUN_CONTACTS_SET = "17";
	// 数据频率采集设置
	public final static String FUN_DATA_RATE = "datarate";

	/** 日志表状态 */
	public interface JobState {
		/**
		 * 日志表状态，初始状态，插入
		 */
		public static final String INIT_STATS = "I";
		/**
		 * 日志表状态，成功
		 */
		public static final String SUCCESS_STATS = "S";
		/**
		 * 日志表状态，失败
		 */
		public static final String ERROR_STATS = "E";
		/**
		 * 日志表状态，未执行
		 */
		public static final String UN_STATS = "N";
	}

	/** 发送验证码超时时限(分钟) */
	public static final Integer SMS_TIME_OUT = 5;

	public static interface SequenceName {

		public static final String DEVICE = "device";
		
		public static final String DEVICE_VIDEO = "member_device_video";

		public static final String MEMBER = "member";

		public static final String SYS_RES = "sys_res";

		public static final String SYS_ROLE = "sys_role";

		public static final String SYS_USER = "sys_user";

		public static final String MEMBER_DEVICE = "member_device";

		public static final String EMPLOYEE = "employee";

		public static final String DOCTOR = "doctor";

		public static final String HEALTH_RECORD = "health_record";

		public static final String ORGANIZATION = "organization";
		
		public static final String PROVIDER = "provider";

		public static final String SYS_DICT = "sys_dict";

		public static final String HR_OPERATION = "hr_operation";

		public static final String HR_TRANSFUSION = "hr_transfusion";

		public static final String HR_TRAUMA = "hr_trauma";

		public static final String MSG_ID = "msg_id";

		public static final String HD_BLOOD_GLUCOSE = "hd_blood_glucose";

		public static final String SOS_LINKMAN = "sos_linkman";

		public static final String REMIND_SET = "remind_set";

		public static final String MEMBER_RELATIONSHIP = "member_relationship";

		public static final String USER_ROLE = "user_role";

		public static final String SECURE_LOCATION = "secure_location";

		public static final String DSF_SETING = "dsf_seting";

		public static final String HEART_SECURITY_SETTING = "heart_security_setting";

		public static final String GOODS = "goods";

		public static final String SERVICE = "service";

		public static final String YW_IMG = "yw_image";

		public static final String AREA = "area";
		
		public static final String SERVICE_PRICE = "service_price";

		public static final String MER_BLOOD_GLUCOSE = "mer_blood_glucose";
		public static final String MER_BLOOD_OXYGEN = "mer_blood_oxygen";
		public static final String MER_BLOOD_PRESSURE = "mer_blood_pressure";
		public static final String MER_BMI = "mer_bmi";
		public static final String MER_BODY_FAT = "mer_body_fat";
		public static final String MER_CHOLESTEROL = "mer_cholesterol";
		public static final String MER_ELECTROCARDIOGRAM = "mer_electrocardiogram";
		public static final String MER_TEMPERATURE = "mer_temperature";
		public static final String MER_URIC_ACID = "mer_uric_acid";
		public static final String MER_WAIST_HIP_RATIO = "mer_waist_hip_ratio";
		public static final String MEDICAL_EXAMINATION_REPORT = "medical_examination_report";

	}

	/**
	 * 数据状态标识位，0 正常
	 */
	public static final Integer STATUS_NORMAL = 0;

	/**
	 * 数据状态标识位，1 禁用或删除
	 */
	public static final Integer STATUS_DISABLE = 1;

	public static interface Device {
		/**
		 * 终端设备绑定状态，1表示已绑定
		 */
		public static final Integer USED = 1;

		/**
		 * 终端设备绑定状态，0表示未绑定
		 */
		public static final Integer UNUSED = 0;
		
	}

	public static interface RemindSetting {
		/**
		 * 终端设备提醒类型 0表示步长提醒
		 */
		public static final Integer REMIND_TYEP_STEP = 0;

		/**
		 * 终端设备提醒类型 1表示心率提醒
		 */
		public static final Integer REMIND_TYEP_HEARTRATE = 1;

		/**
		 * 终端设备提醒类型 2表示天气提醒
		 */
		public static final Integer REMIND_TYEP_WEATHER = 2;
	}

	public static interface DSFSetting {
		/**
		 * 終端設備設置-数据采集类型：安全定位
		 */
		public static final Integer DATA_TYPE_LOCATION = 0;
		/**
		 * 終端設備設置-数据采集类型：心率
		 */
		public static final Integer DATA_TYPE_HEARTRATE = 1;
		/**
		 * 終端設備設置-数据采集类型：步长
		 */
		public static final Integer DATA_TYPE_STEP = 2;
		/**
		 * 終端設備設置-数据采集类型：睡眠
		 */
		public static final Integer DATA_TYPE_SLEEP = 3;

	}

	public static interface MemberType {
		/**
		 * 普通会员
		 */
		public static Integer COMMON = 0;
		/**
		 * VIP会员
		 */
		public static Integer VIP = 1;
	}

	public static interface Content {
		/**
		 * 审核未通过
		 */
		public static final Integer STATUS_NO_PASS = -1;

		/**
		 * 审核中
		 */
		public static final Integer STATUS_AUDIT = 0;

		/**
		 * 审核通过
		 */
		public static final Integer STATUS_PASS = 1;

		/**
		 * 已归档
		 */
		public static final Integer STATUS_ARCHIVED = 2;
	}

	public static interface OrderType { // 订单工单状态

		public static final Integer ORDER_CANCER = -1; // 取消

		public static final Integer ORDER_NOPAY = 0; // 待支付

		public static final Integer ORDER_HASPAY = 1; // 已支付（待发货）

		public static final Integer ORDER_DOING = 2; // 订单：已发货 工单：进行中

		public static final Integer ORDER_TODONE = 3; // 订单：已收货（待评价） 工单： 完成待审核

		public static final Integer ORDER_DONE = 4; // 订单：完成 工单： 完成待评价
		
		public static final Integer ORDER_TOREFUND = 5; // 退款中  
		
		public static final Integer ORDER_REFUND = 6; // 已退款
		
		public static final Integer ORDER_NOREFUND = 7;	//拒绝退款
		
		public static final Integer ORDER_SUCCESS = 8;	//订单完成
		
	}
	
	public static interface CallType { // 呼叫类型

		public static final String CALL_MEMBER = "member"; // 会员

		public static final String CALL_EMP = "emp"; // 员工

		public static final String CALL_DOC = "doc"; // 医师

	}
	
	public static interface PayType { // 订单工单支付类型
		
		public static final String WX_PC = "wechat_pc"; // 小程序
		
		public static final String WX_SMALL = "small"; // 小程序

		public static final String WX_WECHAT = "wechat"; // 微信app支付
		
		public static final String WX_GZH = "wechat_gzh"; // 微信公众号支付
		
		public static final String WX_WECHAT_H5 = "wechat_h5"; // 微信H5支付

		public static final String ALI_ALIPAY = "alipay"; // 支付宝app支付
		
		public static final String ALI_ALIPAY_PC = "alipay_pc"; // 支付宝网页
		
		public static final String ALI_ALIPAY_H5 = "alipay_h5"; // 支付宝H5支付

	}
	
	public static interface UserType {
		public static final Integer USER_TYPE_MEMBER = 1;
		public static final Integer USER_TYPE_EMP = 0;
		public static final Integer USER_TYPE_DOC = -1;
	}

}
