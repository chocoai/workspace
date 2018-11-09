/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.iot.util.LogUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.DeleteDeviceRequest;
import com.aliyuncs.iot.model.v20180120.DeleteDeviceResponse;
import com.aliyuncs.iot.model.v20180120.RegisterDeviceRequest;
import com.aliyuncs.iot.model.v20180120.RegisterDeviceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.KeyRepresentation;
import com.whty.assis.basicdata.dao.DeviceInfoDao;
import com.whty.assis.basicdata.dao.IdDao;
import com.whty.assis.basicdata.dao.OrderDao;
import com.whty.assis.basicdata.dao.OrderSchoolDao;
import com.whty.assis.basicdata.dao.OrderSchoolTerminalDeviceDao;
import com.whty.assis.basicdata.dao.OrderTerminalDeviceTypeDao;
import com.whty.assis.basicdata.model.DeviceInfo;
import com.whty.assis.basicdata.model.Order;
import com.whty.assis.basicdata.model.OrderSchool;
import com.whty.assis.basicdata.model.OrderSchoolTerminalDevice;
import com.whty.assis.basicdata.model.OrderTerminalDeviceType;
import com.whty.assis.basicdata.model.TerminalDeviceType;
import com.whty.assis.basicdata.service.OrderService;
import com.whty.assis.basicdata.service.TerminalDeviceTypeService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SnowflakeIdWorker;
import com.whty.common.util.SysConfig;
import com.whty.common.util.SysConstants;
import com.whty.common.util.TimeUtil;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月1日
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private IdDao idDao;

	@Autowired
	private DeviceInfoDao deviceInfoDao;

	@Autowired
	private OrderSchoolDao orderSchoolDao;

	@Autowired
	private OrderSchoolTerminalDeviceDao orderSchoolTerminalDeviceDao;

	@Autowired
	private OrderTerminalDeviceTypeDao orderTerminalDeviceTypeDao;

	@Autowired
	private TerminalDeviceTypeService terminalDeviceTypeService;

	// @Autowired
	// private OrderSchoolTerminalDeviceService
	// orderSchoolTerminalDeviceService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#saveOrder(javax.servlet.
	 * http.HttpServletRequest)
	 */
	@Override
	public String saveOrder(HttpServletRequest request) {

		String[] terminalDeviceTypeInfo = request.getParameterValues("terminalDeviceTypeInfo");

		String orderTime = request.getParameter("orderTime");
		// String orderType = request.getParameter("orderType");
		String provinceCode = request.getParameter("provinceCode");
		String cityCode = request.getParameter("cityCode");
		String areaCode = request.getParameter("areaCode");
		String memo = request.getParameter("memo");
		String supplierId = request.getParameter("supplierId");
		String contractNumber = request.getParameter("contractNumber");

		String orderType = null;
		if (StringUtils.isNotEmpty(provinceCode)) {
			orderType = "1";
		}

		if (StringUtils.isNotEmpty(cityCode)) {
			orderType = "2";
		}

		if (StringUtils.isNotEmpty(areaCode)) {
			orderType = "3";
		}

		// 先判断订单编号和合同编号是否存在
		Map<String, Object> contractNumberParam = new HashMap<String, Object>();
		contractNumberParam.put("contractNumber", contractNumber);
		List<Order> contractNumberList = orderDao.listByCondition(contractNumberParam);

		if (contractNumberList != null && contractNumberList.size() > 0) {
			return "合同编号已经存在";
		}

		SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
		Long orderNumber = idWorker.nextId();

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", SysConfig.getStrValue("t_order"));

		final Integer orderId = idDao.getId(idParam);

		for (int i = 0; i < terminalDeviceTypeInfo.length; i++) {
			String[] terminalDeviceType = terminalDeviceTypeInfo[i].split(":");
			OrderTerminalDeviceType bean = new OrderTerminalDeviceType();
			bean.setOrderId(orderId);
			bean.setTerminalDeviceTypeId(Integer.valueOf(terminalDeviceType[0]));
			bean.setQuan(Integer.valueOf(terminalDeviceType[1]));
			bean.setCreateTime(new Date());
			bean.setUpdateTime(new Date());
			orderDao.saveTerminalDeviceType(bean);// 订单设备类型

			// 生成设备基础数据
			for (int j = 0; j < Integer.valueOf(terminalDeviceType[1]); j++) {
				DeviceInfo deviceInfo = new DeviceInfo();
				deviceInfo.setCreateTime(new Date());
				deviceInfo.setUpdateTime(new Date());
				deviceInfo.setProvinceCode(provinceCode);
				deviceInfo.setCityCode(cityCode);
				deviceInfo.setAreaCode(areaCode);
				deviceInfo.setOrderId(orderId);
				deviceInfo.setDeviceStatus(1);
				TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
				deviceInfo.setCreator(mUser.getId());// TODO 创建人先写死
				deviceInfo.setTerminalDeviceTypeId(Integer.valueOf(terminalDeviceType[0]));
				deviceInfoDao.save(deviceInfo);
			}
		}

		Order order = new Order();
		order.setMemo(memo);
		order.setAreaCode(areaCode);
		order.setOrderNumber(orderNumber.toString());
		order.setOrderTime(TimeUtil.string2Date(orderTime, TimeUtil.DAY_FORMAT_1));
		order.setProvinceCode(provinceCode);
		order.setCityCode(cityCode);
		order.setAreaCode(areaCode);
		order.setSupplierId(Integer.valueOf(supplierId));
		order.setOrderType(Integer.valueOf(orderType));
		order.setContractNumber(contractNumber);
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		order.setCreator(mUser.getId());
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		order.setStatus(1);
		orderDao.save(order);

		// 生成设备
		new Thread(new Runnable() {
			@Override
			public void run() {
				createDevice(orderId.toString());
			}
		}).start();

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#listByCondition(java.util.
	 * Map)
	 */
	@Override
	public List<Order> listByCondition(Map<String, Object> paramMap) {
		return orderDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#updateBrand(com.whty.assis.
	 * basicdata.model.Order)
	 */
	@Override
	public String updateOrder(Order bean) {

		orderDao.update(bean);
		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderService#deleteBrand(java.lang.
	 * Integer)
	 */
	@Override
	public String deleteOrder(Integer id) {
		orderDao.deleteById(id);
		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#queryOrderPage(java.util.
	 * Map)
	 */
	@Override
	public HandlerResult queryOrderPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(orderDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#queryOrderPage(java.util.
	 * Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryOrderPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(orderDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#getOrderSchool(java.util.
	 * Map)
	 */
	// @Override
	// public List<OrderSchool> listOrderSchool(Map<String, Object> para) {
	// return orderSchoolDao.listByCondition(para);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#getOrderById(java.lang.
	 * Integer)
	 */
	@Override
	public Order getOrderById(Integer id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);

		List<Order> list = orderDao.listByCondition(param);

		Order order = null;

		if (list != null && list.size() > 0) {
			order = list.get(0);
		}

		return order;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#createDevice(java.lang.
	 * String)
	 */
	@Override
	public String createDevice(String orderId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);

		String regionId = SysConfig.getStrValue("iot.regionId");
		String accessKeyID = SysConfig.getStrValue("user.accessKeyID");
		String accessKeySecret = SysConfig.getStrValue("user.accessKeySecret");
		String domain = SysConfig.getStrValue("iot.domain");

		List<DeviceInfo> deviceInfolst = deviceInfoDao.listByCondition(param);

		for (DeviceInfo bean : deviceInfolst) {
			try {
				IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyID, accessKeySecret);
				DefaultProfile.addEndpoint(regionId, regionId, bean.getTerminalProductKey(), domain);
				DefaultAcsClient client = new DefaultAcsClient(profile);
				RegisterDeviceRequest registerDeviceRequest = new RegisterDeviceRequest();

				registerDeviceRequest.setProductKey(bean.getTerminalProductKey());

				RegisterDeviceResponse registerDeviceResponse = client.getAcsResponse(registerDeviceRequest);

				if (registerDeviceResponse != null && registerDeviceResponse.getSuccess() != false) {
					LogUtil.print("注册设备成功！ " + JSONObject.toJSONString(registerDeviceResponse));

					RegisterDeviceResponse.Data data = registerDeviceResponse.getData();

					TerminalDeviceType terminalDeviceType = terminalDeviceTypeService
							.getById(bean.getTerminalDeviceTypeId());

					String deviceCode = null;

					boolean flag = true;

					String cityCode = bean.getCityCode();

					if (StringUtils.isEmpty(cityCode)) {
						cityCode = bean.getProvinceCode();

						if (StringUtils.isEmpty(cityCode)) {
							cityCode = "000000";
						}

					}

					while (flag) {
						StringBuffer sb = new StringBuffer();
						sb.append(terminalDeviceType.getTerminalUserType()).append(bean.getTerminalDeviceTypeId())
								.append(cityCode);
						GoogleAuthenticatorConfigBuilder gacb = new GoogleAuthenticatorConfigBuilder()
								.setKeyRepresentation(KeyRepresentation.BASE64);
						GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator(gacb.build());
						final GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
						final List<Integer> scratchCodes = key.getScratchCodes();

						sb.append(scratchCodes.get(0).toString());

						Map<String, Object> listParam = new HashMap<String, Object>();
						listParam.put("deviceCode", sb.toString());

						List<DeviceInfo> ss = deviceInfoDao.listByCondition(listParam);

						if (ss == null || ss.size() == 0) {
							deviceCode = sb.toString();
							flag = false;
						}
					}

					bean.setDeviceCode(deviceCode);// 设置设备编号
					bean.setAliDeviceName(data.getDeviceName());
					bean.setAliDeviceSecret(data.getDeviceSecret());
					bean.setAliIotId(data.getIotId());
					bean.setAliProductKey(data.getProductKey());
					bean.setUpdateTime(new Date());
					bean.setDeviceStatus(1);// 未绑定设备
					deviceInfoDao.update(bean);
				} else {
					LogUtil.error("注册设备失败！requestId:" + registerDeviceResponse.getRequestId() + "原因："
							+ registerDeviceResponse.getErrorMessage());
				}
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		Order order = orderDao.getOrderById(Integer.valueOf(orderId));
		order.setStatus(2);
		orderDao.update(order);
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#deleteOrder(javax.servlet.
	 * http.HttpServletRequest)
	 */
	@Override
	public String deleteOrder(HttpServletRequest request) {
		String id = request.getParameter("id");// 订单id

		Map<String, Object> deviceParam = new HashMap<String, Object>();
		deviceParam.put("orderId", id);

		// Order order = orderDao.getOrderById(Integer.valueOf(id));

		List<DeviceInfo> deviceInfoList = deviceInfoDao.listByCondition(deviceParam);

		for (DeviceInfo deviceInfo : deviceInfoList) {
			if (deviceInfo.getDeviceStatus() == 2) {
				return "订单下有激活的设备，不能删除";
			}
		}

		String regionId = SysConfig.getStrValue("iot.regionId");
		String accessKeyID = SysConfig.getStrValue("user.accessKeyID");
		String accessKeySecret = SysConfig.getStrValue("user.accessKeySecret");
		String domain = SysConfig.getStrValue("iot.domain");

		for (DeviceInfo deviceInfo : deviceInfoList) {

			if (deviceInfo.getAliIotId() != null) {

				try {
					IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyID, accessKeySecret);
					DefaultProfile.addEndpoint(regionId, regionId, deviceInfo.getTerminalProductKey(), domain);
					DefaultAcsClient client = new DefaultAcsClient(profile);

					DeleteDeviceRequest deviceDeviceRequest = new DeleteDeviceRequest();

					deviceDeviceRequest.setIotId(deviceInfo.getAliIotId());
					deviceDeviceRequest.setProductKey(deviceInfo.getAliProductKey());
					deviceDeviceRequest.setDeviceName(deviceInfo.getDeviceName());

					DeleteDeviceResponse deleteDeviceResponse = client.getAcsResponse(deviceDeviceRequest);

					if (deleteDeviceResponse != null && deleteDeviceResponse.getSuccess() != false) {
						LogUtil.print("删除设备成功！ " + JSONObject.toJSONString(deleteDeviceResponse));

					} else {
						LogUtil.error("删除设备失败！requestId:" + deleteDeviceResponse.getRequestId() + "原因："
								+ deleteDeviceResponse.getErrorMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			deviceInfoDao.deleteById(deviceInfo.getId());
		}

		orderSchoolDao.deleteByOrder(Integer.valueOf(id));
		orderSchoolTerminalDeviceDao.deleteByOrder(Integer.valueOf(id));
		orderTerminalDeviceTypeDao.deleteByOrder(Integer.valueOf(id));

		orderDao.deleteById(Integer.valueOf(id));

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#addSchoolDeviceNum(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	public String addSchoolDeviceNum(HttpServletRequest request) {
		String result = "success";

		String orderId = request.getParameter("orderId");
		String schoolId = request.getParameter("schoolId");
		String[] deviceNumAttr = request.getParameterValues("deviceNumAttr");

		Map<String, Object> orderSchoolParam = new HashMap<String, Object>();
		orderSchoolParam.put("orderId", orderId);
		orderSchoolParam.put("schoolId", schoolId);

		List<OrderSchool> orderSchoolList = orderSchoolDao.listByCondition(orderSchoolParam);

		if (orderSchoolList != null && orderSchoolList.size() > 0) {
			return "不能添加相同的学校";
		}

		// 先生成授权码
		String licenceCode = null;
		boolean flag = true;
		while (flag) {
			GoogleAuthenticatorConfigBuilder gacb = new GoogleAuthenticatorConfigBuilder()
					.setKeyRepresentation(KeyRepresentation.BASE64);
			GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator(gacb.build());
			final GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
			final List<Integer> scratchCodes = key.getScratchCodes();
			Map<String, Object> listParam = new HashMap<String, Object>();
			listParam.put("licenceCode", scratchCodes.get(0).toString());
			List<OrderSchool> licenceCodeOrderSchoolList = orderSchoolDao.listByCondition(listParam);
			if (licenceCodeOrderSchoolList == null || licenceCodeOrderSchoolList.size() == 0) {
				licenceCode = scratchCodes.get(0).toString();
				flag = false;
			}
		}

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", "t_order_school");
		int orderSchoolId = idDao.getId(idParam);

		OrderSchool orderSchool = new OrderSchool();
		orderSchool.setCreateTime(new Date());
		orderSchool.setUpdateTime(new Date());
		orderSchool.setLicenceCode(licenceCode);
		orderSchool.setSchoolId(Integer.valueOf(schoolId));
		orderSchool.setOrderId(Integer.valueOf(orderId));

		orderSchoolDao.save(orderSchool);

		for (int i = 0; i < deviceNumAttr.length; i++) {
			String terminalTypeNumStr = deviceNumAttr[i];

			String[] terminalTypeNum = terminalTypeNumStr.split(",");

			Integer terminalDeviceTypeId = Integer.valueOf(terminalTypeNum[0]);
			Integer quan = Integer.valueOf(terminalTypeNum[1]);

			OrderSchoolTerminalDevice orderSchoolTerminalDevice = new OrderSchoolTerminalDevice();

			orderSchoolTerminalDevice.setOrderSchoolId(orderSchoolId);
			orderSchoolTerminalDevice.setQuan(quan);
			orderSchoolTerminalDevice.setOrderId(Integer.valueOf(orderId));
			orderSchoolTerminalDevice.setCreateTime(new Date());
			orderSchoolTerminalDevice.setUpdateTime(new Date());
			orderSchoolTerminalDevice.setTerminalDeviceTypeId(terminalDeviceTypeId);

			orderSchoolTerminalDeviceDao.save(orderSchoolTerminalDevice);

			Map<String, Object> deviceParam = new HashMap<String, Object>();
			deviceParam.put("orderId", orderId);
			deviceParam.put("terminalDeviceTypeId", terminalDeviceTypeId);
			deviceParam.put("deviceStatus", 1);

			List<DeviceInfo> deviceList = deviceInfoDao.listByCondition(deviceParam);

			int j = 0;
			for (DeviceInfo deviceInfo : deviceList) {

				if (j == quan)
					break;
				deviceInfo.setSchoolId(Integer.valueOf(schoolId));
				deviceInfo.setUpdateTime(new Date());
				deviceInfoDao.update(deviceInfo);
				j = j + 1;
			}

		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#updateSchoolTerminalDevice(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String updateSchoolTerminalDevice(HttpServletRequest request) {
		String orderSchoolId = request.getParameter("orderSchoolId");
		String[] deviceNumAttr = request.getParameterValues("deviceNumAttr");

		OrderSchool orderSchool = orderSchoolDao.loadById(Integer.valueOf(orderSchoolId));

		Map<String, Object> orderSchoolTerminalDeviceParam = new HashMap<String, Object>();
		orderSchoolTerminalDeviceParam.put("orderSchoolId", orderSchoolId);

		List<OrderSchoolTerminalDevice> orderSchoolTerminalDeviceList = orderSchoolTerminalDeviceDao
				.listByCondition(orderSchoolTerminalDeviceParam);

		for (OrderSchoolTerminalDevice bean : orderSchoolTerminalDeviceList) {
			orderSchoolTerminalDeviceDao.deleteById(bean.getId());
		}

		for (int i = 0; i < deviceNumAttr.length; i++) {
			String terminalTypeNumStr = deviceNumAttr[i];

			String[] terminalTypeNum = terminalTypeNumStr.split(",");

			Integer terminalDeviceTypeId = Integer.valueOf(terminalTypeNum[0]);
			Integer quan = Integer.valueOf(terminalTypeNum[1]);

			Map<String, Object> param = new HashMap<String, Object>();

			param.put("orderId", orderSchool.getOrderId());
			param.put("schoolId", orderSchool.getSchoolId());
			param.put("terminalDeviceTypeId", terminalDeviceTypeId);
			param.put("deviceStatus", 1);
			List<DeviceInfo> deviceInfoList = deviceInfoDao.listByCondition(param);// 订单学校下的设备列表

			for (DeviceInfo deviceInfo : deviceInfoList) {
				deviceInfo.setSchoolId(null);
				deviceInfo.setUpdateTime(new Date());
				deviceInfoDao.update(deviceInfo);
			}

			OrderSchoolTerminalDevice orderSchoolTerminalDevice = new OrderSchoolTerminalDevice();
			orderSchoolTerminalDevice.setOrderSchoolId(orderSchool.getId());
			orderSchoolTerminalDevice.setQuan(quan);
			orderSchoolTerminalDevice.setOrderId(orderSchool.getOrderId());
			orderSchoolTerminalDevice.setCreateTime(new Date());
			orderSchoolTerminalDevice.setUpdateTime(new Date());
			orderSchoolTerminalDevice.setTerminalDeviceTypeId(terminalDeviceTypeId);
			orderSchoolTerminalDeviceDao.save(orderSchoolTerminalDevice);

			Map<String, Object> deviceParam = new HashMap<String, Object>();
			deviceParam.put("orderId", orderSchool.getOrderId());
			deviceParam.put("terminalDeviceTypeId", terminalDeviceTypeId);
			deviceParam.put("deviceStatus", 1);

			List<DeviceInfo> deviceList = deviceInfoDao.listByCondition(deviceParam);

			int j = 0;
			for (DeviceInfo deviceInfo : deviceList) {

				if (j == quan)
					break;
				deviceInfo.setSchoolId(Integer.valueOf(orderSchool.getSchoolId()));
				deviceInfo.setUpdateTime(new Date());
				deviceInfoDao.update(deviceInfo);
				j = j + 1;
			}

		}

		String result = "success";
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderService#deleteOrderSchool(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	public String deleteOrderSchool(HttpServletRequest request) {
		String result = "success";

		String orderSchoolId = request.getParameter("orderSchoolId");

		OrderSchool orderSchool = orderSchoolDao.loadById(Integer.valueOf(orderSchoolId));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderSchool.getOrderId());
		param.put("schoolId", orderSchool.getSchoolId());
		// param.put("deviceStatus", 2);

		List<DeviceInfo> deivceInfoList = deviceInfoDao.listByCondition(param);

		// if(bindDeivceInfoList!=null && bindDeivceInfoList.size()>0){
		// return "学校下有绑定的设备，不能删除";
		// }

		for (DeviceInfo deviceInfo : deivceInfoList) {
			if (deviceInfo.getDeviceStatus() == 2) {
				return "学校下有绑定的设备，不能删除";
			}
		}

		for (DeviceInfo deviceInfo : deivceInfoList) {
			deviceInfo.setUpdateTime(new Date());
			deviceInfo.setSchoolId(null);
			deviceInfoDao.update(deviceInfo);
		}

		orderSchoolTerminalDeviceDao.deletebyOrderSchoolId(orderSchool.getId());
		orderSchoolDao.deleteById(orderSchool.getId());

		return result;
	}

}
