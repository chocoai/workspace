package com.yhcrt.iHealthCloud.common;

/**
 * 常量�?
 * 
 */
public interface Constants {
	
	/**
	 * global debug mode
	 */
	public static final boolean IS_DEUBG = true;

	public static interface SequenceName {

		public static final String DEVICE = "device";

		public static final String MEMBER = "member";

		public static final String SYS_RES = "sys_res";

		public static final String SYS_ROLE = "sys_role";

		public static final String SYS_USER = "sys_user";

		public static final String MEMBER_DEVICE = "member_device";

		public static final String EMPLOYEE = "employee";

		public static final String DOCTOR = "doctor";
		
		public static final String DOCTOR_PROPOSAL = "doctor_proposal";

		public static final String HEALTH_RECORD = "health_record";

		public static final String ORGANIZATION = "organization";

		public static final String SYS_DICT = "sys_dict";

		public static final String HR_OPERATION = "hr_operation";

		public static final String HR_TRANSFUSION = "hr_transfusion";

		public static final String HR_TRAUMA = "hr_trauma";

		public static final String MSG_ID = "msg_id";

		public static final String HD_BLOOD_GLUCOSE = "hd_blood_glucose";
		
		public static final String HD_BLOOD_PRESSURE = "hd_blood_pressure";
		
		public static final String HD_STEP = "hd_step";
		
		public static final String HD_PULSE = "hd_pulse";
		
		public static final String HD_SLEEP = "hd_sleep";

		public static final String SOS_LINKMAN = "sos_linkman";

		public static final String REMIND_SET = "remind_set";

		public static final String MEMBER_RELATIONSHIP = "member_relationship";

		public static final String USER_ROLE = "user_role";

		public static final String SECURE_LOCATION = "secure_location";
		
		public static final String LOCATION = "location";

		public static final String DSF_SETING = "dsf_seting";

		public static final String HEART_SECURITY_SETTING = "heart_security_setting";
		
		public static final String USER_SUGGESTIONS = "user_suggestions";
		
		public static final String STEP_SETTING = "step_setting";

		public static final String BLOOD_PRESSURE_SETTING = "blood_pressure_setting";
		
		public static final String ALARM_MSG = "alarm_msg";
		
		public static final String MER_BMI = "mer_bmi";
		
		public static final String MER_BODY_FAT = "mer_body_fat";
		
		public static final String MER_BLOOD_GLUCOSE = "mer_blood_glucose";
		
		public static final String MER_BLOOD_OXYGEN = "mer_blood_oxygen";
		
		public static final String MER_BLOOD_PRESSURE = "mer_blood_pressure";
		
		public static final String MER_CHOLESTEROL = "mer_cholesterol";
		
		public static final String MER_ELECTROCARDIOGRAM = "mer_electrocardiogram";
		
		public static final String MER_TEMPERATURE = "mer_temperature";
		
		public static final String MER_URIC_ACID = "mer_uric_acid";
		
		public static final String MER_WAIST_HIP_RATIO = "mer_waist_hip_ratio";
		
		public static final String WORK_ORDER = "work_order";
		
		public static final String YW_ORDER = "yw_order";
		
		public static final String ORDER_DETAIL = "order_detail";
		
		public static final String MEMBER_ADDRESS = "member_address";

		public static final String USER_COMMENT = "user_comment";
		
		public static final String USER_COLLECT = "user_collect";
		
		public static final String CART = "cart";
		
		public static final String GOODS_STOCK = "goods_stock";
		
		public static final String MEMBER_INFO = "member_info";
	}
	
	public static interface ExceptionMsg{
		/**
		 * 会员未找到
		 */
		public static final String MEMBER_NOT_FOUND = "会员未找到";
		
		/**
		 * 终端设备未找到
		 */
		public static final String DEVICE_NOT_FOUND = "终端设备未找到";
		/**
		 * 此终端设备已经被绑定过了
		 */
		public static final String DEVICE_HAS_BEEN_BINDING = "此终端设备已经被绑定过了";
		
		/**
		 * 此SIM卡已经被绑定过了
		 */
		public static final String SIM_HAS_BEEN_BINDING = "此SIM卡已经被绑定过了";
		
		/**
		 * 上传的文件过大,超过了限制值
		 */
		public static final String MAX_UPLOAD_SIZE_EXCEEDED = "上传的文件过大,超过了限制值";
		
		/**
		 *文件上传失败
		 */
		public static final String UPLOAD_FAILED = "上传失败";
		
		/**
		 * 服务器内部错误
		 */
		public static final String SERVER_ERROR = "服务器内部发生了错误";
		
		/**
		 * 此手机号码已被注册
		 */
		public static final String PHONE_NUMBER_HAS_BEEN_REGISTERED = "此手机号码已被注册";
		
		/**
		 * 此手机号码已被注册
		 */
		public static final String PHONE_NUMBER_HAS_BEEN_BIND = "此手机号码已被其他用户绑定";
		
		/**
		 * 验证码错误
		 */
		public static final String CAPTCHA_ERROR = "验证码错误";
		
		/**
		 * 验证码错误
		 */
		public static final String CAPTCHA_TIME_OUT = "验证码已失效，请重新获取";
		
		/**
		 * 用户不存在
		 */
		public static final String USER_NOT_EXIST = "用户不存在";
		
		/**
		 * 密码错误
		 */
		public static final String PASSWORD_ERROR = "密码错误";
		
		/**
		 * 原密码错误
		 */
		public static final String ORIGINAL_PASSWORD_ERROR = "原密码错误";
		
		public static final String INVALID_IMEI = "无效的IMEI";
		
		public static final String ORDER_NOTFOUND = "工单未找到";
	}

	/**
	 * 数据状�?�标识位�?0 正常
	 */
	public static final Integer STATUS_NORMAL = 0;

	/**
	 * 数据状�?�标识位�?1 禁用或删�?
	 */
	public static final Integer STATUS_DISABLE = 1;

	public static interface Device {
		/**
		 * 终端设备使用状�?�，1表示已使�?
		 */
		public static final Integer USED = 1;

		/**
		 * 终端设备使用状�?�，0表示未使�?
		 */
		public static final Integer UNUSED = 0;
	}
	
	public static interface OrderType {  //订单工单状态
		
		public static final Integer ORDER_CANCER = -1;  //取消

		public static final Integer ORDER_NOPAY = 0;  //待支付

		public static final Integer ORDER_HASPAY = 1; // 已支付（待发货）
		
		public static final Integer ORDER_DOING = 2; // 订单：已发货      工单：进行中
		
		public static final Integer ORDER_TODONE = 3; // 订单：已收货（待评价）       工单： 完成待确认
		
		public static final Integer ORDER_DONE = 4; // 订单：完成       工单： 完成
		
		public static Integer BACKORDER = 5;//退货申请中
		
		public static Integer RETURNORDER = 6;//退货完成
		
		public static Integer SERVICE_RETURNORDER = 8;//交易完成
	}
	
	//项目中的三种类型
	/**服务**/
	public static final String 	SERVICE = "service";
	/**商品**/
	public static final String 	GOODS = "goods";
	/**机构**/
	public static final String 	ORG = "org";
	/**服务供应商**/
	public static final String 	PROVIDER = "provider";

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
		 * 終端設備設置-数据采集类型：安全定�?
		 */
		public static final Integer DATA_TYPE_LOCATION = 0;
		/**
		 * 終端設備設置-数据采集类型：心�?
		 */
		public static final Integer DATA_TYPE_HEARTRATE = 1;
		/**
		 * 終端設備設置-数据采集类型：步�?
		 */
		public static final Integer DATA_TYPE_STEP = 2;
		/**
		 * 終端設備設置-数据采集类型：睡�?
		 */
		public static final Integer DATA_TYPE_SLEEP = 3;

	}
	
	public static interface UserType {
		public static final Integer USER_TYPE_MEMBER = 1;
		public static final Integer USER_TYPE_EMP = 0;
		public static final Integer USER_TYPE_DOC = -1;
	}
	
	public static interface LoginType {
		public static final String XKT = "XKT";
		public static final String XKGJ = "XKGJ";
	}
	
	public static interface MemberType{
		/**
		 * 普通会员
		 */
		public static final Integer COMMON = 0;
		/**
		 * VIP会员
		 */
		public static final Integer VIP = 1;
	}
	

	public static interface AlarmType {
		/**
		 * 电子围栏告警
		 */
		public static final String LOCATION = "location";
		/**
		 * SOS呼叫告警
		 */
		public static final String SOS = "sos";
		/**
		 *心率告警
		 */
		public static final String HEARTRATE = "heartRate";
		/**
		 *血压告警
		 */
		public static final String BLOODPRESSURE = "bloodPressure";
		/**
		 *跌倒告警
		 */
		public static final String FALLDOWN = "fallDown";
		
	}
	
	public static interface Gender {
		
		public static final String MALE = "男";
		
		public static final String FEMALE = "女";
	}
	
	public static final Integer UNREAD = 0;
	
	public static final Integer READED = 1;
	
	public static final Integer YES = 1;

	public static final Integer NO = 0;
	
	public static interface OrgType {
		/**
		 * 养老机构
		 */
		public static final String PENSION_ORG = "A";
		/**
		 * 日间照料中心
		 */
		public static final String DAY_CARE_CENTER = "B";
		/**
		 * 居家养老服务组织
		 */
		public static final String HOME_CARE_SERVICE = "C";

	}
	
	/**
	 * 根据计步数据计算消耗的热量系数
	 */
	public static final Double CALORIE_COEFFICIENT = 0.035d;
	
	/**
	 * 根据计步数据计算步行的距离系数
	 */
	public static final Double DISTANCE_COEFFICIENT = 0.6d;
	
	/**
	 * 目标步数默认值，未设置时将取此值
	 */
	public static final Integer DEFAULT_TARGET_STEP = 10000;
	
	/**
	 * 心率默认安全范围
	 */
	public static interface HeartRate {
		public static final Integer DEFAULT_HIGH_VALUE = 100;
		public static final Integer DEFAULT_LOW_VALUE = 60;
	}
	
	/**
	 * 血压安全范围
	 */
	public static interface BloodPressure {
		public static final Integer DEFAULT_DBP_MAX_VALUE = 90;
		public static final Integer DEFAULT_DBP_MIN_VALUE = 60;
		public static final Integer DEFAULT_SBP_MAX_VALUE = 140;
		public static final Integer DEFAULT_SBP_MIN_VALUE = 90;
	}
	
	public static interface WorkOrderStatus {
		public static final Integer COMPLETED = 4;
		
		public static final Integer COMPLETE_PENDING_CONFIRMATION = 3;
		
		public static final Integer INPROGRESS = 2;
	}
	
	public static String EMP_SPECIALTY_SERVICER = "servicer";

}
