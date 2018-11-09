package com.yhcrt.iHealthCloud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.common.SidInterface;
import com.yhcrt.iHealthCloud.entity.Goods;
import com.yhcrt.iHealthCloud.entity.OrderDetail;
import com.yhcrt.iHealthCloud.entity.Service;
import com.yhcrt.iHealthCloud.entity.SysInterfaceLogWithBLOBs;
import com.yhcrt.iHealthCloud.entity.WorkOrder;
import com.yhcrt.iHealthCloud.entity.YwOrder;
import com.yhcrt.iHealthCloud.mapper.SysInterfaceLogMapper;
import com.yhcrt.iHealthCloud.service.AreaService;
import com.yhcrt.iHealthCloud.service.CmsChannelService;
import com.yhcrt.iHealthCloud.service.CmsContentService;
import com.yhcrt.iHealthCloud.service.DeviceService;
import com.yhcrt.iHealthCloud.service.DoctorProposalService;
import com.yhcrt.iHealthCloud.service.GoodsService;
import com.yhcrt.iHealthCloud.service.HealthDataBloodGlucoseService;
import com.yhcrt.iHealthCloud.service.HealthDataBloodPressureService;
import com.yhcrt.iHealthCloud.service.HealthDataPulseService;
import com.yhcrt.iHealthCloud.service.HealthDataService;
import com.yhcrt.iHealthCloud.service.HealthDataSleepService;
import com.yhcrt.iHealthCloud.service.HealthDataStepService;
import com.yhcrt.iHealthCloud.service.ManagerService;
import com.yhcrt.iHealthCloud.service.MemberService;
import com.yhcrt.iHealthCloud.service.MerService;
import com.yhcrt.iHealthCloud.service.OrderDetailService;
import com.yhcrt.iHealthCloud.service.OrganizationService;
import com.yhcrt.iHealthCloud.service.PersonalInfoService;
import com.yhcrt.iHealthCloud.service.ServiceProviderService;
import com.yhcrt.iHealthCloud.service.ServiceService;
import com.yhcrt.iHealthCloud.service.ShareService;
import com.yhcrt.iHealthCloud.service.SysDictService;
import com.yhcrt.iHealthCloud.service.SysVersionService;
import com.yhcrt.iHealthCloud.service.UserCollectService;
import com.yhcrt.iHealthCloud.service.UserCommentService;
import com.yhcrt.iHealthCloud.service.UserDirectionService;
import com.yhcrt.iHealthCloud.service.UserService;
import com.yhcrt.iHealthCloud.service.UserSuggestionService;
import com.yhcrt.iHealthCloud.service.WorkOrderService;
import com.yhcrt.iHealthCloud.service.YwOrderService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtils;
import com.yhcrt.iHealthCloud.util.FileUtil;
import com.yhcrt.iHealthCloud.util.PropertiesUtil;
import com.yhcrt.iHealthCloud.util.RequestUtils;
import com.yhcrt.iHealthCloud.util.UUIDGenerator;
import com.yhcrt.iHealthCloud.util.WxAppletUserInfo;
import com.yhcrt.iHealthCloud.util.XmlUtil;

@Controller
public class ServiceController {

	@Autowired
	private UserService userService;
	@Autowired
	private PersonalInfoService personalInfoService;
	@Autowired
	private HealthDataSleepService sleepService;
	@Autowired
	private HealthDataBloodPressureService pressureService;
	@Autowired
	private HealthDataBloodGlucoseService glucoseService;
	@Autowired
	private HealthDataStepService stepService;
	@Autowired
	private HealthDataPulseService pulseService;
	@Autowired
	private DoctorProposalService proposalService;
	@Autowired
	private MerService merService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private UserSuggestionService suggestionService;
	@Autowired
	private UserDirectionService directionService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private SysDictService sysDictService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private UserCommentService userCommentService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private YwOrderService ywOrderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private CmsContentService cmsContentService;
	@Autowired
	private CmsChannelService cmsChannelService;
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private UserCollectService userCollectService;
	@Autowired
	private SysInterfaceLogMapper interfaceLogMapper;
	@Autowired
	private SysVersionService sysVersionService;
	@Autowired
	private HealthDataService healthDataService;
	@Autowired
	private ServiceProviderService serviceProviderService;
	
	private static Logger Log = LoggerFactory.getLogger(ServiceController.class);

	/**
	 * 访问接口测试页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	/**
	 * 访问用户使用说明（静态页面）
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/userDirection")
	public String userDirection(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "userDirection/index";
	}
	
	/**
	 * 微信支付回调接口
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/wechatNotify",produces="application/json")
	public void  wechatNotify(HttpServletRequest request,HttpServletResponse response){
		try {
			ServletInputStream in = request.getInputStream();
			String resxml = FileUtil.readInputStream2String(in);
			Map<String, String> restmap = XmlUtil.xmlParse(resxml);
			if ("SUCCESS".equals(restmap.get("return_code"))) {
				// 订单支付成功 业务处理
				String out_trade_no = restmap.get("out_trade_no"); // 商户订单号
				String transaction_id = restmap.get("transaction_id"); // 商户订单号
				String updateTime = DateUtils.dateToString19(new Date());
				// 通过商户订单判断是否该订单已经处理 如果处理跳过 如果未处理先校验sign签名 再进行订单业务相关的处理
				WorkOrder workorder = workOrderService.getByOrderNo(out_trade_no);
				if(workorder != null ){
					// 进行业务处理
					workorder.setOrderStatus(Constants.OrderType.ORDER_HASPAY); //-1取消  0待支付  1已支付 2已发货/进行中   3待评价/待审核  4完成  
					workorder.setPayValue(workorder.getServiceFee());
					workorder.setActualPayment(workorder.getServiceFee());
					workorder.setPayTime(updateTime);
					workorder.setPayAccount(transaction_id);
					workOrderService.upd(workorder);
					//修改服务订购数量
					Service service = serviceService.get(workorder.getServiceId());
					int num = (service.getIext2()==null? 0:service.getIext2()) + workorder.getAmount();
					service.setIext2(num);
					service.setUpdateTime(updateTime);
					serviceService.upd(service);
					return ;
				}
					
				YwOrder yworder = ywOrderService.getByOrderNo(out_trade_no);
				if(yworder != null ){
					// 进行业务处理
					yworder.setOrderStatus(Constants.OrderType.ORDER_HASPAY); //-1取消  0待支付  1已支付 2已发货/进行中   3待评价/待审核  4完成  
					yworder.setPayValue(yworder.getOrderAmount());
					yworder.setActualPayment(yworder.getOrderAmount());
					yworder.setPayTime(updateTime);
					yworder.setPayAccount(transaction_id);
					ywOrderService.upd(yworder);
					orderDetailService.setDetailStatus(yworder.getOrderId(),2); //订单支付成功 子订单修改状态
					//查询商品订单数量 并修改购买数量
					List<OrderDetail> list = orderDetailService.getDetailsByOrderId(yworder.getOrderId());
					for(OrderDetail detail : list){
						Goods goods = goodsService.get(detail.getGoodsId());
						int num = (goods.getIext2()==null? 0:goods.getIext2()) + detail.getAmount();
						goods.setIext2(num);
						goods.setUpdateTime(updateTime);
						goodsService.upd(goods);
					}
					return ;
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 支付宝回调接口
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/alipayNotify",produces="application/json")
	public void alipayNotify( HttpServletRequest request ) throws Exception {
        //接收支付宝返回的请求参数
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8"); //商户订单号
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8"); //交易状态
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8"); //交易状态
		String updateTime = DateUtils.dateToString19(new Date());
		if(trade_status.equals("TRADE_SUCCESS")) {
        	WorkOrder workorder = workOrderService.getByOrderNo(out_trade_no);
			if(workorder != null ){
				// 进行业务处理
				workorder.setOrderStatus(Constants.OrderType.ORDER_HASPAY); //-1取消  0待支付  1已支付 2已发货/进行中   3待评价/待审核  4完成  
				workorder.setPayValue(workorder.getServiceFee());
				workorder.setActualPayment(workorder.getServiceFee());
				workorder.setPayTime(updateTime);
				workorder.setPayAccount(trade_no);
				workOrderService.upd(workorder);
				//修改服务订购数量
				Service service = serviceService.get(workorder.getServiceId());
				int num = (service.getIext2()==null? 0:service.getIext2()) + workorder.getAmount();
				service.setIext2(num);
				service.setUpdateTime(updateTime);
				serviceService.upd(service);
				return ;
			}
			YwOrder yworder = ywOrderService.getByOrderNo(out_trade_no);
			if(yworder != null ){
				// 进行业务处理
				yworder.setOrderStatus(Constants.OrderType.ORDER_HASPAY); //-1取消  0待支付  1已支付 2已发货/进行中   3待评价/待审核  4完成  
				yworder.setPayValue(yworder.getOrderAmount());
				yworder.setActualPayment(yworder.getOrderAmount());
				yworder.setPayTime(updateTime);
				yworder.setPayAccount(trade_no);
				ywOrderService.upd(yworder);
				orderDetailService.setDetailStatus(yworder.getOrderId(),2); //订单支付成功 子订单修改状态
				//查询商品订单数量 并修改购买数量
				List<OrderDetail> list = orderDetailService.getDetailsByOrderId(yworder.getOrderId());
				for(OrderDetail detail : list){
					Goods goods = goodsService.get(detail.getGoodsId());
					int num = (goods.getIext2()==null? 0:goods.getIext2()) + detail.getAmount();
					goods.setIext2(num);
					goods.setUpdateTime(updateTime);
					goodsService.upd(goods);
				}
				return ;
			}
			
        }
    }

	/**
	 * 访问接口
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws Exception
	 */
	@RequestMapping(value = "/services")
	public void services(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap) throws Exception {
		SysInterfaceLogWithBLOBs interfaceLog = new SysInterfaceLogWithBLOBs();
		String callTime = DateUtils.getCurrentTime();
		JSONObject jsonObj = new JSONObject();
		String jsonResult = "";
		String serviceName = "";
		// 获取pdata json字符串
		// String pdata = request.getParameter(Const.TAG_PDATA);
		String pdata = URLDecoder.decode(request.getParameter(Const.TAG_PDATA), "UTF-8");
		Log.info(" Request Parameter $pdata :  {}", pdata);
		try {
			if (StringUtils.isBlank(pdata)) {
				jsonObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				jsonObj.put(Const.TAG_RMK, "the param 'pdata' can not be null object or null character string");
				jsonResult = jsonObj.toJSONString();
			} else {
				JSONObject pdataObj = JSONObject.parseObject(pdata);
				serviceName = pdataObj.getString(Const.TAG_SID);
				jsonResult = filterSid(pdataObj, request);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			jsonObj.put(Const.TAG_STS, "json parsing exception");
			jsonResult = jsonObj.toJSONString();
		} catch (Exception e) {
			jsonObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			jsonResult = jsonObj.toJSONString();
			interfaceLog.setExceptionMsg(e.getMessage());
			e.printStackTrace();
		} finally {
			// 输出结果
			PrintWriter out = setResponseEncodingToUTF8andGetWriter(response);
			out.print(jsonResult);
			out.flush();
			out.close();
			interfaceLog.setCid(UUIDGenerator.getId());
			interfaceLog.setCallTime(callTime);
			interfaceLog.setCallIp(RequestUtils.getIpAddr(request));
			interfaceLog.setServiceName(serviceName);
			interfaceLog.setRequestParams(pdata);
			interfaceLog.setCallUrl(request.getRequestURL().toString());
			interfaceLog.setReturnValue(jsonResult);
			interfaceLog.setReplyTime(DateUtils.getCurrentTime());
			interfaceLogMapper.insert(interfaceLog);
		}
	}
	
	/**
	 * 与socket通讯服务端接口访问
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws Exception
	 */
	@RequestMapping(value = "/socketServices", method = RequestMethod.POST)
	public void socketServices(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysInterfaceLogWithBLOBs interfaceLog = new SysInterfaceLogWithBLOBs();
		String callTime = DateUtils.getCurrentTime();
		JSONObject jsonObj = new JSONObject();
		String jsonResult = "";
		String serviceName = "";
		// 获取pdata json字符串
		String pdata = RequestUtils.getJsonString(request);
		Log.info(" Request Parameter $pdata :  {}", pdata);
		try {
			if (StringUtils.isBlank(pdata)) {
				jsonObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				jsonObj.put(Const.TAG_RMK, "the param 'pdata' can not be null object or null character string");
				jsonResult = jsonObj.toJSONString();
			} else {
				JSONObject pdataObj = JSONObject.parseObject(pdata);
				serviceName = pdataObj.getString(Const.TAG_SID);
				jsonResult = filterSid(pdataObj, request);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			jsonObj.put(Const.TAG_STS, "json parsing exception");
			jsonResult = jsonObj.toJSONString();
		} catch (Exception e) {
			jsonObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObj.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			jsonResult = jsonObj.toJSONString();
			interfaceLog.setExceptionMsg(e.getMessage());
			e.printStackTrace();
		} finally {
			// 输出结果
			PrintWriter out = setResponseEncodingToUTF8andGetWriter(response);
			out.print(jsonResult);
			out.flush();
			out.close();
			interfaceLog.setCid(UUIDGenerator.getId());
			interfaceLog.setCallTime(callTime);
			interfaceLog.setCallIp(RequestUtils.getIpAddr(request));
			interfaceLog.setServiceName(serviceName);
			interfaceLog.setRequestParams(pdata);
			interfaceLog.setCallUrl(request.getRequestURL().toString());
			interfaceLog.setReturnValue(jsonResult);
			interfaceLog.setReplyTime(DateUtils.getCurrentTime());
			interfaceLogMapper.insert(interfaceLog);
		}
	}

	/**
	 * 对sid进行匹配
	 * 
	 * @param sid
	 * @return
	 */
	private String filterSid(JSONObject pdataObj, HttpServletRequest request) {
		String jsonResult = "";
		String sid = pdataObj.getString(Const.TAG_SID);

		if (StringUtils.isBlank(sid)) {

			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, "the pdata's param 'sid' can not be null String or empty String");
			jsonResult = pdataObj.toJSONString();

		} else {
			// 对sid进行匹配
			switch (sid) {
			case SidInterface.HEALTHCLOUD_APP_GETNEWVERSION: // 获取最新版本
				jsonResult = sysVersionService.queryNewVersion(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_TOTALVERSIONNUM: // 统计下载版本次数
				jsonResult = sysVersionService.totalVersionNum(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_REGISTER_GETCAPTCHA: // 注册获取验证码接口
				jsonResult = userService.getCaptcha(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_REGISTER: // 注册
				jsonResult = userService.register(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_LOGIN: // 登录
				jsonResult = userService.login(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_RETRIEVEPASSWORD_GETCAPTCHA: // 找回密码获取验证码
				jsonResult = userService.getCaptchaRetrievePassword(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_RETRIEVEPASSWORD_CHECKCAPTCHA: // 找回密码校验验证码
				jsonResult = userService.checkCaptchaRetrievePassword(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_RETRIEVEPASSWORD_RESETPASSWORD: // 找回密码重置验证码
				jsonResult = userService.resetPassword(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_PROFILE_UPDATE: // 修改个人资料
				jsonResult = userService.updateProfile(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_CHANGEPASSWORD: // 修改密码
				jsonResult = userService.updatePassword(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_PROPOSALINSERT: // 医师建议数据插入
				jsonResult = proposalService.insertProposal(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_PROPOSAL: // 医师建议
				jsonResult = proposalService.getProposalByCid(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_PROPOSALLIST: // 医师建议列表
				jsonResult = proposalService.getProposalListByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HEALTHRECORD: // 健康档案
				jsonResult = personalInfoService.getHealthRecordByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDBLOODGLUCOSE: // 健康数据-血糖
				jsonResult = glucoseService.getGlucoseByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDBLOODGLUCOSE_BY_TIME: // 健康数据-血糖时间段内数据
				jsonResult = glucoseService.getGlucoseByTime(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDBLOODGLUCOSE_FOR_WEEK: // 健康数据-血糖-最近一周
				jsonResult = glucoseService.getGlucoseForAWeekByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDBLOODGLUCOSE_INSERT: // 健康数据-血糖-插入数据
				jsonResult = glucoseService.insertGlucose(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDBLOODPRESSURE: // 健康数据-血压
				jsonResult = pressureService.getPressureByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDBLOODPRESSURE_BY_TIME: // 健康数据-血压
																		// 时间段内数据
				jsonResult = pressureService.getPressureByTime(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDBLOODPRESSURE_FOR_WEEK: // 健康数据-血压-最近一周
				jsonResult = pressureService.getPressureForAWeekByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDBLOODPRESSURE_INSERT: // 健康数据-血压-插入数据
				jsonResult = pressureService.insertPressure(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_FALLDOWN_INSERT: // 跌倒报警数据插入
				jsonResult = stepService.insertFallDow(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_GETTODAYSTEP: // 获取今日步数接口
				jsonResult = stepService.getTodayStep(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDSTEP: // 健康数据-运动量
				jsonResult = stepService.getStepByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDSTEP_BY_TIME: // 健康数据-运动量
																// 根据时间段查询
				jsonResult = stepService.getStepByTime(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDSTEP_FOR_WEEK: // 健康数据-运动量-最近一周
				jsonResult = stepService.getStepForAWeekByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDSTEP_INSERT: // 健康数据-运动量-插入数据
				jsonResult = stepService.insertStep(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HEARTRATE_INSERT: // 健康数据-心率-插入数据
				jsonResult = pulseService.insertHeartRate(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDSLEEP: // 健康数据-深度睡眠
				jsonResult = sleepService.getSleepByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDSLEEP_BY_TIME: // 健康数据-深度睡眠
																// 时间段内数据
				jsonResult = sleepService.getSleepByTime(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDSLEEP_FOR_WEEK: // 健康数据-深度睡眠-最近一周
				jsonResult = sleepService.getSleepForAWeekByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDSLEEP_INSERT: // 健康数据-睡眠插入
				jsonResult = sleepService.insertSleep(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDPULSE: // 健康数据-心率
				jsonResult = pulseService.getPulseByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_GETLATESTPULSE: // 获取最新心率接口
				jsonResult = pulseService.getLatestPulse(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDPULSE_BY_TIME: // 健康数据-心率 时间段内数据
				jsonResult = pulseService.getPulseByTime(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_HDPULSE_FOR_WEEK: // 健康数据-心率-最近一周
				jsonResult = pulseService.getPulseForAWeekByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_MER: // 体检报告列表
				jsonResult = merService.getMerListByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_MER_DETAIL: // 体检报告详情
				jsonResult = merService.getMerByMerId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_MER_LATEST: // 最近一次体检报告
				jsonResult = merService.getLatestMerByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_DEVICE_LIST: // 设备列表
				jsonResult = deviceService.getDeviceListByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_DEVICE: // 设备详情
				jsonResult = deviceService.getDeviceDetailByDeviceId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_SUGGESTION: // 用户建议
				jsonResult = suggestionService.getSuggestionByCid(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_SUGGESTION_LIST: // 用户建议列表
				jsonResult = suggestionService.getSuggestionListByUserId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_SUGGESTION_INSERT: // 插入用户建议记录
				jsonResult = suggestionService.commitSugesstion(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_DIRECTION: // app用户使用说明
				jsonResult = directionService.getDirectionUrl4App(pdataObj, request);
				break;
			case SidInterface.HEALTHCLOUD_WATCH_DIRECTION: // watch用户使用说明
				jsonResult = directionService.getDirectionUrl4Watch(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_MEMBER: // 机构管理员获取会员详情
				jsonResult = managerService.getMemberByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_MEMBER_LIST: // 机构管理员获取会员列表
				jsonResult = managerService.getMemberListByOrgId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_MEMBER_LIST_CONDITION: // 机构管理员条件查询获取会员列表
				jsonResult = managerService.getMemberByCondition(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_HEALTH_REPORT: // 机构管理员查询健康报告
				jsonResult = personalInfoService.getHealthRecordByMemberId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_DEVICE_LIST: // 机构管理员查询机构下设备列表
				jsonResult = deviceService.getDeviceListByOrgId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_DEVICE: // 机构管理员查询设备详情
				jsonResult = deviceService.getDeviceDetailByDeviceId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_DEVICE_LIST_CONDITION: // 机构管理员通过条件查询设备列表
				jsonResult = deviceService.getDeviceByCondition(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_RESET_PASSWORD: // 机构管理员重置密码
				jsonResult = userService.updatePassword(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_SUGGESTION_INSERT: // 机构管理员插入建议反馈
				jsonResult = suggestionService.commitSugesstion(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_EMP_LIST: // 机构管理员获取员工列表
				jsonResult = managerService.getEmployeeListByOrgId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_DOCTOR_LIST: // 机构管理员获取医师列表
				jsonResult = managerService.getDoctorListByOrgId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_HEALTHINDEXDATA: // 健康管理首页数据(步数、心率、血压)
				jsonResult = healthDataService.getHealthIndexData(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_BLOODOXYGEN_UPLOAD: // 
				jsonResult = healthDataService.uploadBloodOxygen(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_TEMPERATURE_UPLOAD: // 
				jsonResult = healthDataService.uploadTemperature(pdataObj);
				break;	
				
			/** 获取APP首页数据接口 */
			case SidInterface.HEALTHCLOUD_APP_GETINDEXDATA: 
				jsonResult = memberService.getIndexData(pdataObj);
				break;
			/************************************ 关注 ************************************/
			// 我的关注
			case SidInterface.HEALTHCLOUD_APP_MYATTENTION:
				jsonResult = memberService.getMyAttention(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_MYFOLLOWING: // 我关注的人
				jsonResult = memberService.getMyFollowing(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_MYFOLLOWERS: // 关注我的人
				jsonResult = memberService.getMyFollowers(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_GETFOLLOWANDSCORE: // 关注  积分 点赞---手表显示
				jsonResult = memberService.getFollowAndScore(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_MYORGSERVICER: // 我的机构服务人员
				jsonResult = memberService.getMyOrgServicer(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_TOFOLLOW: // 添加关注人接口
				jsonResult = memberService.toFollow(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_UNFOLLOW: // 取消关注接口
				jsonResult = memberService.unFollow(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_FOLLOWERDETAIL: // 关注人员详情接口
				jsonResult = memberService.followerDetail(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_BINDDEVICE: // 绑定终端设备接口
				jsonResult = memberService.bindDevice(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_WATCH_STRTUSE: // 终端设备开始启用
				jsonResult = memberService.startDevice(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_UNBINDDEVICE: // 解绑终端设备接口
				jsonResult = memberService.unBindDevice(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_SETDEFAULTDEVICE: // 解绑终端设备接口
				jsonResult = memberService.setDefaultDevice(pdataObj);
				break;	
			case SidInterface.HEALTHCLOUD_APP_GETMYDEVICES: // 我的终端设备列表接口
				jsonResult = memberService.getMyDevices(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_DEVICEDETAIL: // 我的终端设详情接口
				jsonResult = memberService.getDeviceDetail(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_SETLINKMAN: // 终端设置联系人设置接口
				jsonResult = memberService.setLinkman(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_MODIFYLINKMAN: // 终端设置联系人修改接口
				jsonResult = memberService.modifyLinkman(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_DELLINKMAN: // 终端设置联系人删除接口
				jsonResult = memberService.delLinkman(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_GETLINKMAN: // 终端设置查询联系人接口
				jsonResult = memberService.getLinkman(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_SETREMIND: // 终端设置提醒设置接口
				jsonResult = memberService.setRemind(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_GETREMINDSET: // 终端设置提醒设置接口
				jsonResult = memberService.getRemindSet(pdataObj);
				break;	
			case SidInterface.HEALTHCLOUD_APP_SETHEART: // 终端设置心率安全范围设置接口
				jsonResult = memberService.setHeart(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_GETHEARTSET: // 终端设置查询心率安全范围设置接口
				jsonResult = memberService.getHeartSet(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_SETSECURELOCATION: // 终端设置安全定位设置接口
				jsonResult = memberService.setSecureLocation(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_GETSECURELOCATION: // 终端设置查询安全定位设置接口
				jsonResult = memberService.getSecureLocation(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_GETLOCTION: // 获取关注入的终端设备位置信息接口
				jsonResult = memberService.getLoction(pdataObj);
				break;

			case SidInterface.HEALTHCLOUD_APP_GETLOCTIONHIS: // 获取关注入的运动轨迹接口
				jsonResult = memberService.getLoctionHis(pdataObj);
				break;
				
			case SidInterface.HEALTHCLOUD_MANAGER_APP_GETPROFILE: // 获取个人信息接口(管理APP)
				jsonResult = userService.getUserProfile(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_UPDATEPROFILE: // 修改个人信息接口(管理APP)
				jsonResult = userService.updateUserProfile(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_GETDEVICEMEMBERS: //获取终端用户列表接口(管理APP)
				jsonResult = memberService.getDeviceMembers(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_GETAPPMEMBER: //获取终端用户列表接口(管理APP)
				jsonResult = memberService.getAppMembers(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_GETUSERPROFILE: //获取用户详细信息接口(管理APP)
				jsonResult = userService.getUserProfile(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_SHARE: // 推荐给亲友接口
				jsonResult = shareService.getShareUrl(pdataObj, request);
				break;

			case SidInterface.HEALTHCLOUD_APP_GETSTEPSETTING: // 会员端 查询运动目标设置
				jsonResult = memberService.getStepSetting(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_UPDATESTEPSETTING: // 会员端 设置运动目标
				jsonResult = memberService.updateStepSetting(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_GETBLOODPRESSURESETTING: // 会员端
																		// 查询血压安全范围设置
				jsonResult = memberService.getBloodPressureSetting(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_UPDATEBLOODPRESSURESETTING: // 会员端
																			// 设置血压安全范围
				jsonResult = memberService.updateBloodPressureSetting(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_UPLOADLOCATION: // 终端设备定位数据上传接口
				jsonResult = memberService.uploadLocation(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_ALARMMSG_LIST: // 获取预警消息列表接口
				jsonResult = memberService.getAlarmMsgs(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_ALARMMSG_CLEAR: // 清除预警消息接口
				jsonResult = memberService.clearAlarmMsg(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_ALARMMSG_CLEARALL: // 清除所有预警消息接口
				jsonResult = memberService.clearAllAlarmMsg(pdataObj);
				break;

			case SidInterface.HEALTHCLOUD_APP_THIRDLOGIN: // 第三方登录接口
				jsonResult = userService.thirdLogin(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_THIRDLOGIN_GETCAPTCHA: // 第三方登录获取验证码接口
				jsonResult = userService.thirdLoginCaptcha(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_THIRDLOGIN_BINDPHONE: // 第三方登录绑定手机号码接口
				jsonResult = userService.thirdLoginBindPhone(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_VERSIONLIST: // 功能介绍
				jsonResult = sysVersionService.getVersionList(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_APP_VERSIONDESC: // 功能说明
				jsonResult = sysVersionService.getVersionDesc(pdataObj);
				break;
			// 获取最近一周的健康数据	
			case SidInterface.HEALTHCLOUD_APP_HEALTHDATA_FORWEEK: 
				jsonResult = healthDataService.getHealthDataForWeek(pdataObj);
				break;	
			case SidInterface.HEALTHCLOUD_APP_DEVICE_LOCATION: 
				jsonResult = deviceService.getOnlineDeviceLocation(pdataObj);
				break;	
			case SidInterface.HEALTHCLOUD_APP_DEVICE_LOCATION_SEARCH: 
				jsonResult = deviceService.searchOnlineDeviceLocation(pdataObj);
				break;		
/*****************************************二期接口以及小程序接口***********************************************/				
			case SidInterface.HEALTHCLOUD_SMALL_REGISTER: // 小程序注册
				jsonResult = userService.registerSmall(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETTELCODE: // 小程序获取验证码
				jsonResult = userService.getTelCode(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_BINDINGTEL: // 输入验证码后下一步
				jsonResult = userService.bindingTel(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETAREACODE: // 获取地区编码
				jsonResult = areaService.getAreaCode(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_IMAGES: // 小程序获取首页推荐
				jsonResult = cmsContentService.getImages(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETITEMS: // 小程序获取首页的服务类型item和机构类型item
				jsonResult = sysDictService.getItems(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETITEMSMORE: // 小程序获取首页更多服务类型items
				jsonResult = sysDictService.getItemsMore(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_SELECTDICTBYPARAM: //根据条件查询数据字典
				jsonResult = sysDictService.selectDictByParam(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETALLITEMS: // 小程序获取所有"org_type"的服务类型item和机构类型item
				jsonResult = sysDictService.getAllItems(pdataObj);
				break;
//			case SidInterface.HEALTHCLOUD_SMALL_GETHOTORGBYCITY: // 小程序获取热门机构
//				jsonResult = organizationService.getHotOrgByCity(pdataObj);
//				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETDISCOUNTEDGOODS: // 小程序获取活动商品
				jsonResult = goodsService.getDiscountedGoods(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETGOODSDTAIL: // 获取商品详情
				jsonResult = goodsService.getGoodsDetail(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_MOREGOODSCOMMENT: // 获取更多评价-商品
				jsonResult = userCommentService.getMoreGoodsComment(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETHOTSERVICETYPE: // 获取热门服务类型
				jsonResult = sysDictService.geHotServiceType(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETSERVICETYPE: // 获取所有服务类型
				jsonResult = sysDictService.getServiceType(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETORGBYSERVICETYPE: // 获取服务类型下的机构列表
				jsonResult = organizationService.getOrgByServiceType(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETORGBYTYPE: // 获取机构类型下的机构列表
				jsonResult = organizationService.getOrgByType(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETORGDETAIL: // 获取机构详情
				jsonResult = organizationService.getOrgDetail(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_SENDSERVICEORDER: // 预约服务
				jsonResult = workOrderService.sendServiceOrder(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_PAYWORKORDER: // 服务工单支付
				jsonResult = workOrderService.payWorkorder(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_SENDGOODSORDER: // 购买商品
				jsonResult = ywOrderService.sendGoodsOrder(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_PAYYWORDER: 	// 商品订单支付
				jsonResult = ywOrderService.payYworder(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_SENDCARTORDER: // 购买商品--购物车模式
				jsonResult = ywOrderService.sendCartOrder(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_MOREORGCOMMENT: // 获取更多评价--机构
				jsonResult = userCommentService.getMoreOrgComment(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETORGLISTBYGPS: // gps附近的机构
				jsonResult = organizationService.getOrgListByGps(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETRECOMMENDCMS: // 资讯滑动图片
				jsonResult = cmsContentService.getRecommendCms(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETCMSCHANNEL: // 资讯分类信息
				jsonResult = cmsChannelService.getCmsChannel(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETCMSCONTENTLIST: // 资讯列表
				jsonResult = cmsContentService.getCmsContentList(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETCMSCONTENTDETAIL: // 资讯详情
				jsonResult = cmsContentService.getCmsContentDetail(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_MORECONTENTCOMMENT: // 更多资讯评价
				jsonResult = userCommentService.getMoreContentComment(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_GETINFOBYTEL: // 手机号查询个人信息
				jsonResult = memberService.getInfoByTel(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_MANAGER_APP_TOFOLLOWBYTEL: // 根据手机号绑定亲人
				jsonResult = memberService.toFollowByTel(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SAMLL_GETYWORDERLIST: // 商品订单列表
				jsonResult = ywOrderService.getYwOrderList(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SAMLL_REFUND: // 商品退款
				jsonResult = ywOrderService.refund(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SAMLL_SAVEREFUND: // 商品退款保存
				jsonResult = ywOrderService.updateRefund(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SAMLL_SETYWORDERSTATUS: //商品订单状态更改
				jsonResult = ywOrderService.setYwOrderStatus(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SAMLL_SETWORKORDERSTATUS: //服务工单状态更改
				jsonResult = workOrderService.setWorkOrderStatus(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SAMLL_GETYWORDERDETAIL: // 商品订单详情
				jsonResult = ywOrderService.getYwOrderDetail(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SAMLL_GETWORKORDERLIST: // 服务工单列表
				jsonResult = workOrderService.getWorkOrderList(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SAMLL_GETWORKORDERDETAIL: // 服务工单详情
				jsonResult = workOrderService.getWorkOrderDetail(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SAMLL_ADDUSERCOMMENT: // 添加评论
				jsonResult = userCommentService.addUserComment(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SAMLL_GETADDRESSID: // 获取收货地址id
				jsonResult = memberService.getAddressId(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_MYFOLLOWING: // 我关注的人
				jsonResult = memberService.getMyFollowingForSmall(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETHEALTHDATA: // 小程序健康数据  血糖 血压 心率 步数 睡眠 五项值
				jsonResult = memberService.getHealthData(pdataObj);
				break;
				
			case SidInterface.HEALTHCLOUD_SMALL_GETSERVICEPROVIDERS: // 获取服务供应商列表
				jsonResult = serviceProviderService.getServiceProviders(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETSERVICEPROVIDER: // 获取服务供应商详情
				jsonResult = serviceProviderService.getServiceProviderDetail(pdataObj);
				break;
			case SidInterface.HEALTHCLOUD_SMALL_GETPROVIDERSBYGPS: // 获取当前地理位置附近的服务供应商
				jsonResult = serviceProviderService.getProvidersByGps(pdataObj);
				break;	
			case SidInterface.HEALTHCLOUD_SMALL_GETRECOMMENDPROVIDERS: // 获取热门推荐服务供应商
				jsonResult = serviceProviderService.getRecommendProviders(pdataObj);
				break;
				
			/************************************* 二期APP接口 *************************************/
			// 获取推荐服务组织、养老机构、日间照料中心、居家养老服务组织接口
			case SidInterface.HEALTHCLOUD_APP_GETRECOMMENDORG:
				jsonResult = organizationService.getRecommendOrg(pdataObj);
				break;
			// 获取预约服务列表接口
			case SidInterface.HEALTHCLOUD_APP_GETSERVICES:
				jsonResult = serviceService.getServices(pdataObj);
				break;
			// 获取预约服务筛选接口
			case SidInterface.HEALTHCLOUD_APP_GETSERVICEFILTERITEM:
				jsonResult = serviceService.getServiceFilterItem(pdataObj);
				break;
			// 获取服务详情接口
			case SidInterface.HEALTHCLOUD_APP_GETSERVICEDETAIL:
				jsonResult = serviceService.getServiceDetailItem(pdataObj);
				break;
			// 收藏服务接口
			case SidInterface.HEALTHCLOUD_APP_COLLECTSERVICE:
				jsonResult = userCollectService.collectService(pdataObj);
				break;
			// 获取商品列表接口
			case SidInterface.HEALTHCLOUD_APP_GETGOODSLIST:
				jsonResult = goodsService.getGoodsList(pdataObj);
				break;
			// 获取商品筛选接口
			case SidInterface.HEALTHCLOUD_APP_GETGOODSFILTERITEM:
				jsonResult = goodsService.getGoodsFilterItem(pdataObj);
				break;
			// 收藏商品接口
			case SidInterface.HEALTHCLOUD_APP_COLLECTGOODS:
				jsonResult = userCollectService.collectGoods(pdataObj);
				break;
			// 获取服务机构列表接口
			case SidInterface.HEALTHCLOUD_APP_GETORGS:
				jsonResult = organizationService.getOrgs(pdataObj);
				break;
			// 获取服务机构筛选接口
			case SidInterface.HEALTHCLOUD_APP_GETORGSFILTERITEM:
				jsonResult = organizationService.getOrgsFilterItem(pdataObj);
				break;
			// 收藏服务机构接口
			case SidInterface.HEALTHCLOUD_APP_COLLECTORG:
				jsonResult = userCollectService.collectOrg(pdataObj);
				break;								
				
			// 获取用户收货地址信息列表接口
			case SidInterface.HEALTHCLOUD_APP_GETMEMBERADDRESS:
				jsonResult = memberService.getMemberAddress(pdataObj);
				break;
			//获取用户的默认收货地址信息	
			case SidInterface.HEALTHCLOUD_APP_DEFAULTADDRESS:
				jsonResult = memberService.getMemberDefaultAddress(pdataObj);
				break;
			// 新增收货地址接口
			case SidInterface.HEALTHCLOUD_APP_ADDMEMBERADDRESS:
				jsonResult = memberService.addMemberAddress(pdataObj);
				break;
			// 编辑收货地址接口
			case SidInterface.HEALTHCLOUD_APP_UPDATEMEMBERADDRESS:
				jsonResult = memberService.updateMemberAddress(pdataObj);
				break;
			// 删除收货地址接口
			case SidInterface.HEALTHCLOUD_APP_DELETEMEMBERADDRESS:
				jsonResult = memberService.deleteMemberAddress(pdataObj);
				break;
			// 删除收货地址接口
			case SidInterface.HEALTHCLOUD_APP_SETDEFAULTADDRESS:
				jsonResult = memberService.setDefaultAddress(pdataObj);
				break;
			// 获取用户收藏列表
			case SidInterface.HEALTHCLOUD_APP_GETUSERCOLLECT:
				jsonResult = userCollectService.getUserCollect(pdataObj);
				break;
			// 获取联系人
			case SidInterface.HEALTHCLOUD_MANAGER_APP_LINKMANS:
				jsonResult = managerService.getLinkmansByOrgId(pdataObj);
				break;
			/** 获取待分配工单列表 */
			case SidInterface.HEALTHCLOUD_WORKORDERS_TODOWORKORDERS:
				jsonResult = workOrderService.getToDoWorkOrders(pdataObj);
				break;
			/** 获取全部工单列表 */
			case SidInterface.HEALTHCLOUD_WORKORDERS_ALL:
				jsonResult = workOrderService.workOrdersAll(pdataObj);
				break;
			/** 获取工单详情 */
			case SidInterface.HEALTHCLOUD_WORKORDERS_ORDERDETAIL:
				jsonResult = workOrderService.orderDetail(pdataObj);
				break;
			/** 分配服务人员 */
			case SidInterface.HEALTHCLOUD_WORKORDERS_DISTRIBUTE:
				jsonResult = workOrderService.distribute(pdataObj);
				break;
			/** 获取指派给我的工单 */
			case SidInterface.HEALTHCLOUD_WORKORDERS_MINE:
				jsonResult = workOrderService.sendToMe(pdataObj);
				break;
			// 服务人员提交服务日志信息
			case SidInterface.HEALTHCLOUD_WORKORDERS_SERVICELOG_UPLOAD:
				jsonResult = workOrderService.uploadServiceLog(pdataObj);
				break;
			// 获取工单的服务日志详细信息
			case SidInterface.HEALTHCLOUD_WORKORDERS_SERVICELOG_DETAIL:
				jsonResult = workOrderService.getServiceLogDetail(pdataObj);
				break;
			// 确认工单完成接口
			case SidInterface.HEALTHCLOUD_WORKORDERS_CONFIRMCOMPLETED:
				jsonResult = workOrderService.confirmCompleted(pdataObj);
				break;
			// 获取工单跟踪信息接口
			case SidInterface.HEALTHCLOUD_WORKORDERS_TRACEDETAIL:
				jsonResult = workOrderService.getWorkOrderTraceDetail(pdataObj);
				break;
			// 获取机构服务人员
			case SidInterface.HEALTHCLOUD_WORKORDERS_ORGSERVICERS:
				jsonResult = workOrderService.getOrgServicers(pdataObj);
				break;
			default: // undefined 'sid'
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, Const.RMK_SID_UNDEFINED);
				jsonResult = pdataObj.toJSONString();
				break;
			}
		}
		return jsonResult;
	}

	/**
	 * 将response的字符集设为utf-8并返回response的writer
	 * 
	 * @param HttpServletResponse
	 *            response
	 * @return PrintWriter
	 * @throws IOException
	 */
	private PrintWriter setResponseEncodingToUTF8andGetWriter(HttpServletResponse response) throws IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		return response.getWriter();
	}

	/**
	 * 微信小程序授权登录方法获取openid，session_key字段
	 * @param code
	 * @throws IOException 
	 */
	@RequestMapping(value = "/weixin/xcx/getRdSession")
	public void getRdSession(HttpServletRequest request, HttpServletResponse response,String code) throws IOException {
		JSONObject jsonObj = new JSONObject();
		try {
			String appId = PropertiesUtil.getProperty("/config.properties", "weixin.xcx.appId");
			String secret = PropertiesUtil.getProperty("/config.properties", "weixin.xcx.secret");
			jsonObj = RequestUtils.httpGet("https://api.weixin.qq.com/sns/jscode2session?appid=" + appId
					+ "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code");
			jsonObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			jsonObj.put(Const.ERROR_DESC, "");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObj.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} 
		finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	/**
	 * 微信用户获取过去三十天的微信运动步数
	 * @param request
	 * @param response
	 * @param sessionKey
	 * @param encryptedData
	 * @param iv
	 * @throws IOException
	 */
	@RequestMapping(value = "/weixin/xcx/getWeRunData")
	public void getWeRunData(HttpServletRequest request, HttpServletResponse response, String sessionKey,
			String encryptedData, String iv) throws IOException {
		JSONObject jsonObject = new JSONObject();
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		try {
			jsonObject = WxAppletUserInfo.getUserInfo(encryptedData, sessionKey, iv);
			if (jsonObject != null) {
				String json = (String) jsonObject.get("result");
				JSONObject jsStr = JSONObject.parseObject(json);
				JSONArray list = jsStr.getJSONArray("stepInfoList");
				if(null!=list&&list.size()>0){
					Map<String,Object> map = null;
					for (int i = 0; i < list.size(); i++) {
						map = new HashMap<String,Object>();
						JSONObject object = list.getJSONObject(i);
						map.put("date", Timestamp(Long.parseLong(object.get("timestamp").toString()+"000")));
						map.put("step", object.get("step"));
						mapList.add(map);
					}
				}
				jsonObject.put("list", mapList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObject.put(Const.ERROR_DESC, Constants.ExceptionMsg.SERVER_ERROR);
		} finally {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObject!=null?jsonObject.toString():"");
		}
	}

	public static String Timestamp(Long time) {
		java.sql.Timestamp ts = new java.sql.Timestamp(time);  
	    DateFormat sdf = new SimpleDateFormat("MM-dd");  
	    return sdf.format(ts);  
	}
}