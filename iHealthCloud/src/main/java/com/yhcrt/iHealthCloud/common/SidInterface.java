package com.yhcrt.iHealthCloud.common;

/**
 * sid 
 * @author huzelin
 *
 */
public interface SidInterface {
	
	// 获取应用最新版本
	public static final String HEALTHCLOUD_APP_GETNEWVERSION = "healthCloud.app.getNewVersion";
	
	// 统计下载apk包次数
	public static final String HEALTHCLOUD_APP_TOTALVERSIONNUM = "healthCloud.app.totalVersionNum";
	
	// 会员端 注册获取验证码
	public static final String HEALTHCLOUD_APP_REGISTER_GETCAPTCHA = "healthCloud.app.register.getCaptcha";

	// 用户登录接口
	public static final String HEALTHCLOUD_APP_LOGIN = "healthCloud.app.login";
	
	// 会员注册接口
	public static final String HEALTHCLOUD_APP_REGISTER = "healthCloud.app.register";
	
	// 找回密码 获取验证码
	public static final String HEALTHCLOUD_APP_RETRIEVEPASSWORD_GETCAPTCHA = "healthCloud.app.retrievePassword.getCaptcha";
	
	// 找回密码 校验验证码
	public static final String HEALTHCLOUD_APP_RETRIEVEPASSWORD_CHECKCAPTCHA = "healthCloud.app.retrievePassword.checkCaptcha";
	
	// 找回密码 重置密码
	public static final String HEALTHCLOUD_APP_RETRIEVEPASSWORD_RESETPASSWORD = "healthCloud.app.retrievePassword.resetPassword";
	
	// 会员修改个人资料
	public static final String HEALTHCLOUD_APP_PROFILE_UPDATE = "healthCloud.app.profile.update";
	
	// 修改登录密码
	public static final String HEALTHCLOUD_APP_CHANGEPASSWORD = "healthCloud.app.changePassword";
	
	// 客户端app 医师建议列表
	public static final String HEALTHCLOUD_APP_PROPOSALLIST = "healthCloud.app.proposalList"; 
	
	// 客户端app 医师建议详情
	public static final String HEALTHCLOUD_APP_PROPOSAL = "healthCloud.app.proposal";
	// 客户端app 医师建议添加
	public static final String HEALTHCLOUD_APP_PROPOSALINSERT = "healthCloud.app.proposal.insert";
	
	// 客户端app 血糖历史数据(可分页)
	public static final String HEALTHCLOUD_APP_HDBLOODGLUCOSE = "healthCloud.app.hdBloodGlucose";
	
	// 客户端app 获取血糖最新测量数据
	public static final String HEALTHCLOUD_APP_GETNEWESTBLOODGLUCOSE = "healthCloud.app.getNewestBloodGlucose";
	
	// 客户端app 血糖查询(时间段查询)
	public static final String HEALTHCLOUD_APP_HDBLOODGLUCOSE_BY_TIME = "healthCloud.app.hdBloodGlucose.byTime";
	
	// 客户端app 血糖查询(近一周数据)
	public static final String HEALTHCLOUD_APP_HDBLOODGLUCOSE_FOR_WEEK = "healthCloud.app.hdBloodGlucose.forWeek";
	
	// 客户端app 插入血糖数据
	public static final String HEALTHCLOUD_APP_HDBLOODGLUCOSE_INSERT = "healthCloud.app.hdBloodGlucose.insert";
	
	// 客户端app 血压历史数据(可分页)
	public static final String HEALTHCLOUD_APP_HDBLOODPRESSURE = "healthCloud.app.hdBloodPressure";
	
	// 客户端app 血压查询(时间段查询)
	public static final String HEALTHCLOUD_APP_HDBLOODPRESSURE_BY_TIME = "healthCloud.app.hdBloodPressure.byTime";
	
	// 客户端app 血压查询(近一周数据)
	public static final String HEALTHCLOUD_APP_HDBLOODPRESSURE_FOR_WEEK = "healthCloud.app.hdBloodPressure.forWeek";
	
	// 客户端app 插入血糖数据
	public static final String HEALTHCLOUD_APP_HDBLOODPRESSURE_INSERT = "healthCloud.app.hdBloodPressure.insert";
	
	// 插入跌倒报警数据
	public static final String HEALTHCLOUD_FALLDOWN_INSERT = "healthCloud.fallDown.insert";
	
	// 客户端app 心率历史数据
	public static final String HEALTHCLOUD_APP_HDPULSE = "healthCloud.app.hdPulse";
	
	// 客户端app 查询心率(时间段查询)
	public static final String HEALTHCLOUD_APP_HDPULSE_BY_TIME = "healthCloud.app.hdPulse.byTime";
	
	// 客户端app 心率查询(近一周数据)
	public static final String HEALTHCLOUD_APP_HDPULSE_FOR_WEEK = "healthCloud.app.hdPulse.forWeek";
	
	// 客户端app 运动量历史数据
	public static final String HEALTHCLOUD_APP_HDSTEP = "healthCloud.app.hdStep";
	
	// 客户端app 运动量查询(时间段查询)
	public static final String HEALTHCLOUD_APP_HDSTEP_BY_TIME = "healthCloud.app.hdStep.byTime";
	
	// 客户端app 运动刚查询(近一周数据)
	public static final String HEALTHCLOUD_APP_HDSTEP_FOR_WEEK = "healthCloud.app.hdStep.forWeek";
	
	// 客户端app 插入运动量数据
	public static final String HEALTHCLOUD_APP_HDSTEP_INSERT = "healthCloud.app.hdStep.insert";
	
	// 客户端app 插入心率数据
	public static final String HEALTHCLOUD_APP_HEARTRATE_INSERT = "healthCloud.app.hdPulse.upload";
	
	// 客户端app 深度睡眠历史数据(可分页)
	public static final String HEALTHCLOUD_APP_HDSLEEP = "healthCloud.app.hdSleep";
	
	// 客户端app 深度睡眠查询(时间段查询)
	public static final String HEALTHCLOUD_APP_HDSLEEP_BY_TIME = "healthCloud.app.hdSleep.byTime";
	
	// 客户端app 深度睡眠查询(近一周数据)
	public static final String HEALTHCLOUD_APP_HDSLEEP_FOR_WEEK = "healthCloud.app.hdSleep.forWeek";
	//睡眠插入
	public static final String HEALTHCLOUD_APP_HDSLEEP_INSERT = "healthCloud.app.hdSleep.insert";
	
	// 客户端app 健康档案查询
	public static final String HEALTHCLOUD_APP_HEALTHRECORD = "healthCloud.app.healthRecord";
	
	// 客户端app 体检报告列表
	public static final String HEALTHCLOUD_APP_MER = "healthCloud.app.mer";
	
	// 客户端app 体检报告详情
	public static final String HEALTHCLOUD_APP_MER_DETAIL = "healthCloud.app.mer.detail";
	
	// 客户端app 体检报告详情(最近要次报告)
	public static final String HEALTHCLOUD_APP_MER_LATEST = "healthCloud.app.mer.latest";
	
	/** 我的关注 */
	public static final String HEALTHCLOUD_APP_MYATTENTION = "healthCloud.app.myAttention";
	
	// 会员端 获取我关注的人
	public static final String HEALTHCLOUD_APP_MYFOLLOWING = "healthCloud.app.myFollowing";
	
	// 会员端 获取关注我的人
	public static final String HEALTHCLOUD_APP_MYFOLLOWERS = "healthCloud.app.myFollowers";
	
	//会员获取关注 点赞 和积分
	public static final String HEALTHCLOUD_APP_GETFOLLOWANDSCORE = "healthCloud.app.getFollowAndScore";
	
	// 客户端app 终端设备详情
	public static final String HEALTHCLOUD_APP_DEVICE = "healthCloud.app.device";
	
	// 客户端app 终端设备列表
	public static final String HEALTHCLOUD_APP_DEVICE_LIST = "healthCloud.app.deviceList";
	
	// 客户端app 用户反馈建议详情
	public static final String HEALTHCLOUD_APP_SUGGESTION = "healthCloud.app.suggestion";
	
	// 客户端app 用户反馈列表
	public static final String HEALTHCLOUD_APP_SUGGESTION_LIST =  "healthCloud.app.suggestionList";
	
	// 客户端app 插入用户反馈信息
	public static final String HEALTHCLOUD_APP_SUGGESTION_INSERT =  "healthCloud.app.suggestion.insert";
	
	// 客户端app 用户指南
	public static final String HEALTHCLOUD_APP_DIRECTION = "healthCloud.app.direction";
	
	// 智能手表用户指南
	public static final String HEALTHCLOUD_WATCH_DIRECTION = "healthCloud.watch.direction";
	
	// 管理端app 会员详情
	public static final String HEALTHCLOUD_MANAGER_APP_MEMBER = "healthCloud.manager.app.member";
	
	// 管理端app 会员列表
	public static final String HEALTHCLOUD_MANAGER_APP_MEMBER_LIST = "healthCloud.manager.app.memberList";
	
	// 管理端app 会员条件查询
	public static final String HEALTHCLOUD_MANAGER_APP_MEMBER_LIST_CONDITION = "healthCloud.manager.app.memberList.condition";
	
	// 健康管理首页数据(步数、心率、血压)
	public static final String HEALTHCLOUD_MANAGER_APP_HEALTHINDEXDATA = "healthCloud.manager.app.healthIndexData";
	
	// 管理端app 健康档案
	public static final String HEALTHCLOUD_MANAGER_APP_HEALTH_REPORT = "healthCloud.manager.app.healthReport";
	
	// 管理端app 设备详情
	public static final String HEALTHCLOUD_MANAGER_APP_DEVICE = "healthCloud.manager.app.device";
	
	// 管理端app 设备列表
	public static final String HEALTHCLOUD_MANAGER_APP_DEVICE_LIST = "healthCloud.manager.app.deviceList";
	
	// 管理端app 设备条件查询
	public static final String HEALTHCLOUD_MANAGER_APP_DEVICE_LIST_CONDITION = "healthCloud.manager.app.deviceList.condition";
	
	// 管理端app 插入反馈数据
	public static final String HEALTHCLOUD_MANAGER_APP_SUGGESTION_INSERT = "healthCloud.manager.app.suggestion.insert";
	
	// 管理端app 修改密码
	public static final String HEALTHCLOUD_MANAGER_APP_RESET_PASSWORD = "healthCloud.manager.app.resetPassword";
	
	// 管理端app 员工列表
	public static final String HEALTHCLOUD_MANAGER_APP_EMP_LIST = "healthCloud.manager.app.empList";
	
	// 管理端app 医师列表
	public static final String HEALTHCLOUD_MANAGER_APP_DOCTOR_LIST = "healthCloud.manager.app.docList";
	
	public static final String HEALTHCLOUD_APP_MYORGSERVICER = "healthCloud.app.myOrgServicer";
	
	// 会员端 关注其他会员
	public static final String HEALTHCLOUD_APP_TOFOLLOW = "healthCloud.app.toFollow";
	
	// 会员端 取消关注
	public static final String HEALTHCLOUD_APP_UNFOLLOW = "healthCloud.app.unFollow";
	
	public static final String HEALTHCLOUD_APP_FOLLOWERDETAIL = "healthCloud.app.followerDetail";
	
	// 会员端  绑定终端设备
	public static final String HEALTHCLOUD_APP_BINDDEVICE = "healthCloud.app.bindDevice";
	
	// 第一次启用终端设备
	public static final String HEALTHCLOUD_WATCH_STRTUSE = "healthCloud.watch.startUse";
	
	// 会员端  解绑终端设备
	public static final String HEALTHCLOUD_APP_UNBINDDEVICE = "healthCloud.app.unBindDevice";
	
	// 会员端 获取我的终端设备
	public static final String HEALTHCLOUD_APP_GETMYDEVICES = "healthCloud.app.getMyDevices";
	
	// 会员端 终端设备详情
	public static final String HEALTHCLOUD_APP_DEVICEDETAIL = "healthCloud.app.deviceDetail";
	
	// 会员端 分享给亲友
	public static final String HEALTHCLOUD_APP_SHARE = "healthCloud.app.share";
	
	// 终端设置联系人设置接口
	public static final String HEALTHCLOUD_APP_SETLINKMAN = "healthCloud.app.setLinkman";
	// 终端设置联系人修改接口
	public static final String HEALTHCLOUD_APP_MODIFYLINKMAN = "healthCloud.app.modifyLinkman";
	// 终端设置联系人删除接口
	public static final String HEALTHCLOUD_APP_DELLINKMAN = "healthCloud.app.delLinkman";
	
	// 终端设置查询联系人接口
	public static final String HEALTHCLOUD_APP_GETLINKMAN = "healthCloud.app.getLinkman";
	
	// 终端设置提醒设置接口
	public static final String HEALTHCLOUD_APP_SETREMIND = "healthCloud.app.setRemind";
	
	// 终端设置查询提醒设置接口
	public static final String HEALTHCLOUD_APP_GETREMINDSET = "healthCloud.app.getRemindSet";
	
	// 终端设置心率安全范围设置接口
	public static final String HEALTHCLOUD_APP_SETHEART = "healthCloud.app.setHeart";
	
	// 终端设置查询心率安全范围设置接口
	public static final String HEALTHCLOUD_APP_GETHEARTSET = "healthCloud.app.getHeartSet";
	
	// 终端设置安全定位设置接口
	public static final String HEALTHCLOUD_APP_SETSECURELOCATION = "healthCloud.app.setSecureLocation";
	
	// 终端设置查询安全定位设置接口
	public static final String HEALTHCLOUD_APP_GETSECURELOCATION = "healthCloud.app.getSecureLocation";
	
	public static final String HEALTHCLOUD_APP_GETLOCTION = "healthCloud.app.getLoction";
	
	public static final String HEALTHCLOUD_APP_GETLOCTIONHIS = "healthCloud.app.getLoctionHis";
	
	// 管理端获取个人信息接口
	public static final String HEALTHCLOUD_MANAGER_APP_GETPROFILE = "healthCloud.manager.app.getProfile";
	
	// 修改个人信息接口
	public static final String HEALTHCLOUD_MANAGER_APP_UPDATEPROFILE = "healthCloud.manager.app.updateProfile";
	
	public static final String HEALTHCLOUD_MANAGER_APP_GETDEVICEMEMBERS = "healthCloud.manager.app.getDeviceMembers";
	
	public static final String HEALTHCLOUD_MANAGER_APP_GETAPPMEMBER = "healthCloud.manager.app.getAppMember";
	
	public static final String HEALTHCLOUD_MANAGER_APP_GETUSERPROFILE = "healthCloud.manager.app.getUserProfile";
	
//	public static final String HEALTHCLOUD_MANAGER_APP_GETMEMBERIMEIS = "healthCloud.manager.app.getMemberImeis";
	
	public static final String HEALTHCLOUD_MANAGER_APP_SEARCHDEVICEMEMBER = "healthCloud.manager.app.searchDeviceMember";
	
	// 会员端 查询运动目标设置
	public static final String HEALTHCLOUD_APP_GETSTEPSETTING = "healthCloud.app.getStepSetting";
	
	// 会员端 设置运动目标
	public static final String HEALTHCLOUD_APP_UPDATESTEPSETTING = "healthCloud.app.updateStepSetting";
	
	// 会员端 查询血压安全范围设置
	public static final String HEALTHCLOUD_APP_GETBLOODPRESSURESETTING = "healthCloud.app.getBloodPressureSetting";
	
	// 会员端 设置血压安全范围
	public static final String HEALTHCLOUD_APP_UPDATEBLOODPRESSURESETTING = "healthCloud.app.updateBloodPressureSetting";
	
	// 终端设备定位数据上传接口
	public static final String  HEALTHCLOUD_APP_UPLOADLOCATION = "healthCloud.app.location.upload";
	
	// 获取预警消息列表接口
	public static final String  HEALTHCLOUD_APP_ALARMMSG_LIST = "healthCloud.app.alarmMsg.list";
	
	// 清除预警消息接口
	public static final String  HEALTHCLOUD_APP_ALARMMSG_CLEAR = "healthCloud.app.alarmMsg.clear";
	
	/**清除所有预警消息接口*/
	public static final String  HEALTHCLOUD_APP_ALARMMSG_CLEARALL = "healthCloud.app.alarmMsg.clearAll";
	
	// 第三方登录接口
	public static final String  HEALTHCLOUD_APP_THIRDLOGIN = "healthCloud.app.thirdLogin";
	
	// 第三方登录获取验证码接口
	public static final String  HEALTHCLOUD_APP_THIRDLOGIN_GETCAPTCHA = "healthCloud.app.thirdLogin.getCaptcha";
	
	// 第三方登录绑定手机号码接口
	public static final String  HEALTHCLOUD_APP_THIRDLOGIN_BINDPHONE = "healthCloud.app.thirdLogin.bindPhone";
	// 功能介绍
	public static final String  HEALTHCLOUD_APP_VERSIONLIST = "healthCloud.app.getVersionList";
	// 功能说明
	public static final String  HEALTHCLOUD_APP_VERSIONDESC = "healthCloud.app.getVersionDesc";
	
	/*************************************小程序接口 *************************************/
	// 小程序注册接口
	public static final String  HEALTHCLOUD_SMALL_REGISTER = "healthCloud.small.register";
	// 小程序获取验证码
	public static final String  HEALTHCLOUD_SMALL_GETTELCODE = "healthCloud.small.getTelCode";
	// 小程序绑定手机
	public static final String  HEALTHCLOUD_SMALL_BINDINGTEL = "healthCloud.small.bindingTel";
	// 获取地区编码
	public static final String  HEALTHCLOUD_SMALL_GETAREACODE = "healthCloud.small.getAreaCodeByCity";
	// 首页推荐图片
	public static final String  HEALTHCLOUD_SMALL_IMAGES = "healthCloud.small.getImages";
	// 小程序获取首页的服务类型item和机构类型item
	public static final String  HEALTHCLOUD_SMALL_GETITEMS = "healthCloud.small.getItems";
	// 小程序获取首页更多服务类型items
	public static final String  HEALTHCLOUD_SMALL_GETITEMSMORE = "healthCloud.small.getItemsMore";
	// 根据条件查询数据字典
	public static final String  HEALTHCLOUD_SMALL_SELECTDICTBYPARAM = "healthCloud.small.selectDictByParam";
	// 小程序获取所有"org_type"的服务类型item和机构类型item
	public static final String  HEALTHCLOUD_SMALL_GETALLITEMS = "healthCloud.small.getAllItems";
	// 小程序首页获取2条热门机构
	public static final String  HEALTHCLOUD_SMALL_GETHOTORGBYCITY = "healthCloud.small.getHotOrgByCity";
	// 小程序获取打折的商品列表
	public static final String  HEALTHCLOUD_SMALL_GETDISCOUNTEDGOODS = "healthCloud.small.getDiscountedGoods";
	// 商品详情
	public static final String  HEALTHCLOUD_SMALL_GETGOODSDTAIL = "healthCloud.small.getGoodsDetail";
	// 更多评价  商品
	public static final String  HEALTHCLOUD_SMALL_MOREGOODSCOMMENT = "healthCloud.small.getMoreGoodsComment";
	// 获取热门服务项目类型
	public static final String  HEALTHCLOUD_SMALL_GETHOTSERVICETYPE = "healthCloud.small.getHotServiceType";
	// 获取服务项目类型
	public static final String  HEALTHCLOUD_SMALL_GETSERVICETYPE = "healthCloud.small.getServiceType";
	// 获取服务类型下的机构
	public static final String  HEALTHCLOUD_SMALL_GETORGBYSERVICETYPE = "healthCloud.small.getOrgByServiceType";
	// 按机构类型查询机构
	public static final String  HEALTHCLOUD_SMALL_GETORGBYTYPE = "healthCloud.small.getOrgByType";
	// 获取服务类型下的机构
	public static final String  HEALTHCLOUD_SMALL_GETORGDETAIL = "healthCloud.small.getOrgDetail";
	// 预约服务项目
	public static final String  HEALTHCLOUD_SMALL_SENDSERVICEORDER = "healthCloud.small.sendServiceOrder";
	// 服务订单支付
	public static final String  HEALTHCLOUD_SMALL_PAYWORKORDER = "healthCloud.small.payWorkOrder";
	// 商品订单支付
	public static final String  HEALTHCLOUD_SMALL_PAYYWORDER = "healthCloud.small.payYwOrder";
	// 商品购买
	public static final String  HEALTHCLOUD_SMALL_SENDGOODSORDER = "healthCloud.small.sendGoodsOrder";
	// 商品购买--购物车模式
	public static final String  HEALTHCLOUD_SMALL_SENDCARTORDER = "healthCloud.small.sendCartOrder";
	// 更多评价 机构
	public static final String  HEALTHCLOUD_SMALL_MOREORGCOMMENT = "healthCloud.small.getMoreOrgComment";
	// 附近5公里机构
	public static final String  HEALTHCLOUD_SMALL_GETORGLISTBYGPS = "healthCloud.small.getOrgListByGps";
	// 资讯滑动图片
	public static final String  HEALTHCLOUD_SMALL_GETRECOMMENDCMS = "healthCloud.small.getRecommendCms";
	// 资讯分类
	public static final String  HEALTHCLOUD_SMALL_GETCMSCHANNEL = "healthCloud.small.getCmsChannel";
	// 资讯列表
	public static final String  HEALTHCLOUD_SMALL_GETCMSCONTENTLIST = "healthCloud.small.getCmsContentList";
	// 资讯详情
	public static final String  HEALTHCLOUD_SMALL_GETCMSCONTENTDETAIL = "healthCloud.small.getCmsContentDetail";
	// 更多评价 资讯
	public static final String  HEALTHCLOUD_SMALL_MORECONTENTCOMMENT = "healthCloud.small.getMoreContentComment";
	//根据手机号查询个人信息
	public static final String HEALTHCLOUD_MANAGER_APP_GETINFOBYTEL = "healthCloud.manager.app.getInfoByTel";
	//根据手机号绑定亲属
	public static final String HEALTHCLOUD_MANAGER_APP_TOFOLLOWBYTEL= "healthCloud.small.toFollowByTel";
	//查询商品订单列表
    public static final String HEALTHCLOUD_SAMLL_GETYWORDERLIST= "healthCloud.small.getYwOrderList";
    //商品退款
    public static final String HEALTHCLOUD_SAMLL_REFUND= "healthCloud.small.refund";
    //商品退款保存
    public static final String HEALTHCLOUD_SAMLL_SAVEREFUND= "healthCloud.small.saveRefund";
	//商品订单详情详情
    public static final String HEALTHCLOUD_SAMLL_GETYWORDERDETAIL= "healthCloud.small.getYwOrderDetail";
	//服务工单状态更改
    public static final String HEALTHCLOUD_SAMLL_SETWORKORDERSTATUS= "healthCloud.small.setWorkOrderStatus";
	//商品订单状态更改
    public static final String HEALTHCLOUD_SAMLL_SETYWORDERSTATUS= "healthCloud.small.setYwOrderStatus";
	//查询服务工单单列表
    public static final String HEALTHCLOUD_SAMLL_GETWORKORDERLIST= "healthCloud.small.getWorkOrderList";
	//查询服务工单详情
    public static final String HEALTHCLOUD_SAMLL_GETWORKORDERDETAIL= "healthCloud.small.getWorkOrderDetail";
    //添加评论
    public static final String HEALTHCLOUD_SAMLL_ADDUSERCOMMENT= "healthCloud.small.addUserComment";
    //获取收货地址id
    public static final String HEALTHCLOUD_SAMLL_GETADDRESSID= "healthCloud.small.getAddressId";
	// 获取我关注的人
	public static final String HEALTHCLOUD_SMALL_MYFOLLOWING = "healthCloud.small.myFollowing";
	// 小程序健康数据
	public static final String HEALTHCLOUD_SMALL_GETHEALTHDATA = "healthCloud.small.getHealthData";
	// 获取服务供应商列表
	public static final String HEALTHCLOUD_SMALL_GETSERVICEPROVIDERS = "healthCloud.small.getServiceProviders";
	// 获取当前地理位置附近的服务供应商
	public static final String HEALTHCLOUD_SMALL_GETPROVIDERSBYGPS = "healthCloud.small.getProvidersByGps";
	// 获取热门推荐服务供应商
	public static final String HEALTHCLOUD_SMALL_GETRECOMMENDPROVIDERS = "healthCloud.small.getRecommendProviders";
    
    
    /************************************* 二期APP接口 *************************************/
	/**获取用户收货地址信息列表接口*/
	public static final String  HEALTHCLOUD_APP_GETMEMBERADDRESS = "healthCloud.app.getMemberAddress";
	
	/**获取用户的默认收货地址信息*/
	public static final String  HEALTHCLOUD_APP_DEFAULTADDRESS = "healthCloud.app.getDefaultAddress";
	
	/**新增收货地址接口*/
	public static final String  HEALTHCLOUD_APP_ADDMEMBERADDRESS = "healthCloud.app.addMemberAddress";
	
	/**编辑收货地址接口*/
	public static final String  HEALTHCLOUD_APP_UPDATEMEMBERADDRESS = "healthCloud.app.updateMemberAddress";
	
	/**删除收货地址接口*/
	public static final String  HEALTHCLOUD_APP_DELETEMEMBERADDRESS = "healthCloud.app.deleteMemberAddress";
	
	/**设置默认收货地址接口*/
	public static final String  HEALTHCLOUD_APP_SETDEFAULTADDRESS = "healthCloud.app.setDefaultAddress";
	
	/**获取推荐服务组织、养老机构、日间照料中心、居家养老服务组织接口*/
	public static final String  HEALTHCLOUD_APP_GETRECOMMENDORG = "healthCloud.app.getRecommendOrg";
	
	/**获取预约服务列表接口*/
	public static final String  HEALTHCLOUD_APP_GETSERVICES = "healthCloud.app.getServices";
	
	/**获取预约服务筛选接口*/
	public static final String  HEALTHCLOUD_APP_GETSERVICEFILTERITEM = "healthCloud.app.getServiceFilterItem";
	
	/**获取服务详情接口*/
	public static final String  HEALTHCLOUD_APP_GETSERVICEDETAIL = "healthCloud.app.getServiceDetail";
	/**服务供应商详情**/
	public static final String  HEALTHCLOUD_SMALL_GETSERVICEPROVIDER = "healthCloud.small.getServiceProviderDetail";
	
	/**收藏服务接口*/
	public static final String  HEALTHCLOUD_APP_COLLECTSERVICE = "healthCloud.app.collectService";
	
	/**获取商品列表接口*/
	public static final String  HEALTHCLOUD_APP_GETGOODSLIST = "healthCloud.app.getGoodsList";
	
	/**获取商品筛选接口*/
	public static final String  HEALTHCLOUD_APP_GETGOODSFILTERITEM = "healthCloud.app.getGoodsFilterItem";

	/**收藏商品接口*/
	public static final String  HEALTHCLOUD_APP_COLLECTGOODS = "healthCloud.app.collectGoods";
	
	/**获取用户收藏列表*/
	public static final String  HEALTHCLOUD_APP_GETUSERCOLLECT = "healthCloud.app.getUserCollect";
	
	/**获取今日步数接口*/
	public static final String  HEALTHCLOUD_APP_GETTODAYSTEP = "healthCloud.app.getTodayStep";
	
	/**获取最新心率接口*/
	public static final String  HEALTHCLOUD_APP_GETLATESTPULSE = "healthCloud.app.getLatestPulse";
	
	/**获取服务机构列表接口*/
	public static final String  HEALTHCLOUD_APP_GETORGS = "healthCloud.app.getOrgs";
	
	/** 获取服务机构筛选接口 */
	public static final String HEALTHCLOUD_APP_GETORGSFILTERITEM = "healthCloud.app.getOrgsFilterItem";
	
	/** 收藏服务机构接口 */
	public static final String HEALTHCLOUD_APP_COLLECTORG = "healthCloud.app.collectOrg";
	
	/** 获取APP首页数据接口 */
	public static final String HEALTHCLOUD_APP_GETINDEXDATA = "healthCloud.app.getIndexData";
	
	/** 获取最近一周的健康数据*/
	public static final String HEALTHCLOUD_APP_HEALTHDATA_FORWEEK = "healthCloud.app.healthData.forWeek";
	
	/** 获取在线设备的定位信息*/
	public static final String HEALTHCLOUD_APP_DEVICE_LOCATION = "healthCloud.app.device.location";
	
	/** 搜索在线设备的定位信息*/
	public static final String HEALTHCLOUD_APP_DEVICE_LOCATION_SEARCH = "healthCloud.app.device.location.search";
	
	/** 血氧数据上传*/
	public static final String HEALTHCLOUD_APP_BLOODOXYGEN_UPLOAD = "healthCloud.app.bloodOxygen.upload";
	
	/** 体温数据上传*/
	public static final String HEALTHCLOUD_APP_TEMPERATURE_UPLOAD = "healthCloud.app.temperature.upload";

	/** 获取联系人*/
	public static final String HEALTHCLOUD_MANAGER_APP_LINKMANS = "healthCloud.manager.app.linkmans";
	
	/** 获取待分配工单列表*/
	public static final String HEALTHCLOUD_WORKORDERS_TODOWORKORDERS = "healthCloud.workOrders.ToDoWorkOrders";
	
	/** 获取全部工单列表*/
	public static final String HEALTHCLOUD_WORKORDERS_ALL = "healthCloud.workOrders.all";
	
	/** 获取工单详情*/
	public static final String HEALTHCLOUD_WORKORDERS_ORDERDETAIL = "healthCloud.workOrders.orderDetail";
	
	/** 分配服务人员*/
	public static final String HEALTHCLOUD_WORKORDERS_DISTRIBUTE = "healthCloud.workOrders.distribute";
	
	/** 获取指派给我的工单*/
	public static final String HEALTHCLOUD_WORKORDERS_MINE = "healthCloud.workOrders.mine";
	
	/** 服务人员提交服务日志信息*/
	public static final String HEALTHCLOUD_WORKORDERS_SERVICELOG_UPLOAD = "healthCloud.workOrders.serviceLog.upload";

	/** 获取工单的服务日志详细信息*/
	public static final String HEALTHCLOUD_WORKORDERS_SERVICELOG_DETAIL = "healthCloud.workOrders.serviceLog.detail";

	/** 确认工单完成接口*/
	public static final String HEALTHCLOUD_WORKORDERS_CONFIRMCOMPLETED = "healthCloud.workOrders.confirmCompleted";
	
	/** 获取工单跟踪信息接口*/
	public static final String HEALTHCLOUD_WORKORDERS_TRACEDETAIL = "healthCloud.workOrders.traceDetail";
	
	public static final String HEALTHCLOUD_WORKORDERS_ORGSERVICERS = "healthCloud.workOrders.getOrgServicers";
	
	public static final String HEALTHCLOUD_APP_SETDEFAULTDEVICE = "healthCloud.app.setDefaultDevice";
	

}
