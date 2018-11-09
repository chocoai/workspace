package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Employee;
import com.yhcrt.iHealthCloud.entity.EmployeeExample;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.MemberAddress;
import com.yhcrt.iHealthCloud.entity.OrderRefund;
import com.yhcrt.iHealthCloud.entity.OrderRefundExample;
import com.yhcrt.iHealthCloud.entity.Organization;
import com.yhcrt.iHealthCloud.entity.ServiceLog;
import com.yhcrt.iHealthCloud.entity.ServicePrice;
import com.yhcrt.iHealthCloud.entity.WorkOrder;
import com.yhcrt.iHealthCloud.entity.WorkOrderExample;
import com.yhcrt.iHealthCloud.entity.YwImage;
import com.yhcrt.iHealthCloud.entity.YwImageExample;
import com.yhcrt.iHealthCloud.mapper.EmployeeMapper;
import com.yhcrt.iHealthCloud.mapper.MemberAddressMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.OrderRefundMapper;
import com.yhcrt.iHealthCloud.mapper.OrganizationMapper;
import com.yhcrt.iHealthCloud.mapper.ServiceLogMapper;
import com.yhcrt.iHealthCloud.mapper.ServiceMapper;
import com.yhcrt.iHealthCloud.mapper.ServicePriceMapper;
import com.yhcrt.iHealthCloud.mapper.WorkOrderMapper;
import com.yhcrt.iHealthCloud.mapper.YwImageMapper;
import com.yhcrt.iHealthCloud.pojo.OrderServiceLog;
import com.yhcrt.iHealthCloud.pojo.OrgServicer;
import com.yhcrt.iHealthCloud.pojo.WorkOrderTrace;
import com.yhcrt.iHealthCloud.service.WorkOrderService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;
import com.yhcrt.iHealthCloud.util.DateUtils;
import com.yhcrt.iHealthCloud.util.RequestUtils;
import com.yhcrt.iHealthCloud.util.WxAppletUserInfo;

@Service
public class WorkOrderServiceImpl extends BaseService implements WorkOrderService {

	@Autowired
	private WorkOrderMapper workOrderMapper;
	@Autowired
	private ServiceMapper serviceMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private MemberAddressMapper memberAddressMapper;
	@Autowired
	private OrderRefundMapper orderRefundMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private ServiceLogMapper serviceLogMapper;
	@Autowired
	private YwImageMapper ywImageMapper;
	@Autowired
	private ServicePriceMapper priceMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yhcrt.healthcloud.mall.service.WorkOrderService#add(com.yhcrt.healthcloud
	 * .mall.entity.WorkOrder)
	 */
	@Override
	public Integer add(WorkOrder workOrder) {
		return workOrderMapper.insert(workOrder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yhcrt.healthcloud.mall.service.WorkOrderService#del(java.lang.String)
	 */
	@Override
	public Integer del(Integer cid) {
		return workOrderMapper.deleteByPrimaryKey(cid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yhcrt.healthcloud.mall.service.WorkOrderService#upd(com.yhcrt.healthcloud
	 * .mall.entity.WorkOrder)
	 */
	@Override
	public Integer upd(WorkOrder workOrder) {
		return workOrderMapper.updateByPrimaryKey(workOrder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yhcrt.healthcloud.mall.service.WorkOrderService#get(java.lang.String)
	 */
	@Override
	public WorkOrder get(Integer cid) {
		return workOrderMapper.selectByPrimaryKey(cid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yhcrt.healthcloud.mall.service.WorkOrderService#getAll()
	 */
	@Override
	public List<WorkOrder> getAll(HashMap<String, Object> params) {
		return workOrderMapper.search(params);
	}

	@Override
	public WorkOrder getByOrderNo(String orderNo) {
		WorkOrderExample example = new WorkOrderExample();
		example.createCriteria().andCext2EqualTo(orderNo);
		List<WorkOrder> workOrderList = workOrderMapper.selectByExample(example);
		if (workOrderList.size() > 0) {
			return workOrderList.get(0);
		}
		return null;
	}

	/**
	 * 获取某个会员的服务工单列表
	 */
	@Override
	public String getWorkOrderList(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String memberId = biz.getString("member_id");
		String orderStatus = biz.getString("order_status");
		String currentPage = biz.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = biz.getString(Const.TAG_PAGE_SIZE);
		
		if (StringUtils.isBlank(memberId)) {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(pdataObj);
		}
		
		List<WorkOrder> workOrders = new ArrayList<WorkOrder>();
		
		if (StringUtils.isNotBlank(pageSize) && StringUtils.isNotBlank(currentPage)) {
			PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
			workOrders = selectWorkOrderList(memberId, orderStatus);
			PageInfo<WorkOrder> pageInfo = new PageInfo<>(workOrders);
			setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
		}else {
			workOrders = selectWorkOrderList(memberId, orderStatus);
		}
		
		pdataObj.put(Const.TAG_BIZ, workOrders);
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		pdataObj.put(Const.TAG_RMK, "获取成功！");
		return toJsonStringWithOutNull(pdataObj);
		
	}

	@Override
	public String getWorkOrderDetail(JSONObject jsonObject) {
		// 获取参数
		JSONObject biz = getBizObj(jsonObject);
		String ordernum = biz.getString("order_num");
		WorkOrderExample example = new WorkOrderExample();
		example.createCriteria().andCext1EqualTo(ordernum);
		List<WorkOrder> workOrders = workOrderMapper.selectByExample(example);
		if (workOrders.isEmpty()) {
			requestFailed(jsonObject, Constants.ExceptionMsg.ORDER_NOTFOUND);
			return toJsonStringWithOutNull(jsonObject);
		}
		
		WorkOrder workOrder = workOrders.get(0);
		jsonObject.put(Const.TAG_BIZ, workOrder);
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_RMK, "获取成功！");
		
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String getToDoWorkOrders(JSONObject jsonObject) {
		
		JSONObject bizObj = getBizObj(jsonObject);
		String orgId = bizObj.getString("org_id");
		String currentPage = bizObj.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = bizObj.getString(Const.TAG_PAGE_SIZE);
		
		if (StringUtils.isBlank(orgId)) {
			requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(jsonObject);
		}
		
		Organization org = organizationMapper.selectByPrimaryKey(Integer.valueOf(orgId));
		if (org == null) {
			requestFailed(jsonObject, "未查询到此机构！");
			return toJsonStringWithOutNull(jsonObject);
		}
		
		List<com.yhcrt.iHealthCloud.pojo.WorkOrderItem> workOrderList = new ArrayList<>();
		
		if(StringUtils.isNotBlank(pageSize) && StringUtils.isNotBlank(currentPage)){
			PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
			workOrderList = workOrderMapper.getToDoWorkOrders(org.getOrgCode());
			PageInfo<com.yhcrt.iHealthCloud.pojo.WorkOrderItem> pageInfo = new PageInfo<>(workOrderList);
			setPagingData(jsonObject, pageInfo.getPages(),pageInfo.getPageNum());
		}else {
			workOrderList = workOrderMapper.getToDoWorkOrders(org.getOrgCode());
		}
		
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_BIZ, workOrderList);
		return toJsonStringWithOutNull(jsonObject);
	}

	/**
	 * 获取全部工单
	 */
	@Override
	public String workOrdersAll(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String orgId = bizObj.getString("org_id");
		String status = bizObj.getString("status");
		String currentPage = bizObj.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = bizObj.getString(Const.TAG_PAGE_SIZE);
		
		if (StringUtils.isBlank(orgId)) {
			requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(jsonObject);
		}
		
		Organization org = organizationMapper.selectByPrimaryKey(Integer.valueOf(orgId));
		if (org == null) {
			requestFailed(jsonObject, "未查询到此机构！");
			return toJsonStringWithOutNull(jsonObject);
		}
		
		List<com.yhcrt.iHealthCloud.pojo.WorkOrderItem> workOrderList = new ArrayList<>();
		
		if(StringUtils.isNotBlank(pageSize) && StringUtils.isNotBlank(currentPage)){
			PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
			workOrderList = workOrderMapper.workOrdersAll(org.getOrgCode(), status);
			PageInfo<com.yhcrt.iHealthCloud.pojo.WorkOrderItem> pageInfo = new PageInfo<>(workOrderList);
			setPagingData(jsonObject, pageInfo.getPages(),pageInfo.getPageNum());
		}else {
			workOrderList = workOrderMapper.workOrdersAll(org.getOrgCode(), status);
		}
		
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_BIZ, workOrderList);
		return toJsonStringWithOutNull(jsonObject);
	}

	/**
	 * 分配给我的工单
	 */
	@Override
	public String sendToMe(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String empId = bizObj.getString("emp_id");
		String status = bizObj.getString("status");
		String currentPage = bizObj.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = bizObj.getString(Const.TAG_PAGE_SIZE);
		
		if (StringUtils.isBlank(empId)) {
			requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(jsonObject);
		}
		
		List<com.yhcrt.iHealthCloud.pojo.WorkOrderItem> workOrderList = new ArrayList<>();
		
		if(StringUtils.isNotBlank(pageSize) && StringUtils.isNotBlank(currentPage)){
			PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
			workOrderList = workOrderMapper.sendToMe(empId, status);
			PageInfo<com.yhcrt.iHealthCloud.pojo.WorkOrderItem> pageInfo = new PageInfo<>(workOrderList);
			setPagingData(jsonObject, pageInfo.getPages(),pageInfo.getPageNum());
		}else {
			workOrderList = workOrderMapper.sendToMe(empId, status);
		}
		
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_BIZ, workOrderList);
		return toJsonStringWithOutNull(jsonObject);		
	}

	@Override
	public String orderDetail(JSONObject jsonObject) {
		try {
			// 获取参数
			JSONObject biz = jsonObject.getJSONObject("biz");
			String order_id = biz.getString("order_id");
			if (StringUtils.isBlank(order_id)) {
				requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
				return toJsonStringWithOutNull(jsonObject);
			}
			WorkOrder workOrder = workOrderMapper.orderDetail(Integer.valueOf(order_id));
			if (workOrder == null) {
				requestFailed(jsonObject, Constants.ExceptionMsg.ORDER_NOTFOUND);
				return toJsonStringWithOutNull(jsonObject);
			}
			jsonObject.put(Const.TAG_BIZ, workOrder);
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			jsonObject.put(Const.TAG_RMK, "");
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String distribute(JSONObject pdataObj) {
		/** 分配服务人员 */
		try {
			// 获取参数
			JSONObject biz = pdataObj.getJSONObject("biz");
			String order_id = biz.getString("order_id");
			String emp_id = biz.getString("emp_id");
			String handler_id = biz.getString("handler_id");
			if (StringUtils.isEmpty(handler_id)) {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, "管理员id不能为空！");
				return pdataObj.toJSONString();
			}
			if (StringUtils.isEmpty(order_id)) {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, "工单id不能为空！");
				return pdataObj.toJSONString();
			}
			if (StringUtils.isEmpty(emp_id)) {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, "服务人员不能为空！");
				return pdataObj.toJSONString();
			}
			Employee emp = employeeMapper.selectByPrimaryKey(Integer.valueOf(emp_id));
			if (emp == null) {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, "未查询到此服务人员！");
				return pdataObj.toJSONString();
			}
			Employee handler = employeeMapper.selectByPrimaryKey(Integer.valueOf(handler_id));
			if (handler == null) {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, "未查询到此管理人员！");
				return pdataObj.toJSONString();
			}
			WorkOrder workOrder = workOrderMapper.orderDetail(Integer.valueOf(order_id));
			if (workOrder == null) {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, "未查询到此工单！");
				return pdataObj.toJSONString();
			}
			if (workOrder.getToUser() != null) {
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
				pdataObj.put(Const.TAG_RMK, "此工单已经分配过服务人员！");
				return pdataObj.toJSONString();
			}
			workOrder.setHandler(handler_id);
			workOrder.setCext3(DateUtils.getCurrentTime());
			workOrder.setHandleTime(DateUtils.getCurrentTime());
			workOrder.setToUser(emp_id);
			workOrder.setOrderStatus(Constants.WorkOrderStatus.INPROGRESS);
			workOrderMapper.updateByPrimaryKeySelective(workOrder);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "分配成功");
			return pdataObj.toJSONString();
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, "服务器内部错误");
			return pdataObj.toJSONString();
		}
	}

	@Override
	public String setWorkOrderStatus(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String ordernum = biz.getString("order_num");
		String orderstatus = biz.getString("order_status");
		if (StringUtils.isEmpty(orderstatus)) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, "订单状态参数不能为空！");
			return pdataObj.toJSONString();
		}
		WorkOrderExample example = new WorkOrderExample();
		example.createCriteria().andCext1EqualTo(ordernum);
		List<WorkOrder> empList = workOrderMapper.selectByExample(example);
		if (empList.size() > 0) {
			WorkOrder workOrder = empList.get(0);
			workOrder.setOrderStatus(Integer.valueOf(orderstatus));
			workOrderMapper.updateByPrimaryKeySelective(workOrder);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "数据修改成功！");
		} else {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, "获取失败,未查询到此订单号！");
		}
		return pdataObj.toJSONString();
	}

	private List<WorkOrder> selectWorkOrderList(String memberId, String orderStatus) {
		WorkOrderExample example = new WorkOrderExample();
		example.setOrderByClause("create_time desc");
		if (StringUtils.isNotBlank(orderStatus)) { // 1 2 3 4
			Integer status = null;
			// 页面中传值 1待支付 2进行中 3完成 4已关闭
			if ("1".equals(orderStatus)) {
				status = Constants.OrderType.ORDER_NOPAY;
			}
			if ("2".equals(orderStatus)) {
				status = Constants.OrderType.ORDER_DOING;
			}
			if ("3".equals(orderStatus)) {
				status = Constants.OrderType.ORDER_TODONE;
			}
			if ("4".equals(orderStatus)) {
				status = Constants.OrderType.ORDER_DONE;
			}
			example.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId)).andOrderStatusEqualTo(status);
		} else {
			example.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId));
		}
		List<WorkOrder> workOrders = workOrderMapper.selectByExample(example);
		return workOrders;
	}

	public List<WorkOrder> selectYwOrderList(Integer orderstatus) {
		WorkOrderExample example = new WorkOrderExample();
		example.setOrderByClause("create_time desc");
		if (orderstatus != null) {
			example.createCriteria().andOrderStatusEqualTo(orderstatus);
		} else {
			example.createCriteria();
		}
		return workOrderMapper.selectByExample(example);
	}

	public List<WorkOrder> selectYwOrderList(Integer memberId, Integer orderstatus) {
		WorkOrderExample example = new WorkOrderExample();
		example.setOrderByClause("create_time desc");
		if (orderstatus != null) {
			if (orderstatus.equals(Constants.OrderType.ORDER_HASPAY)) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(Constants.OrderType.ORDER_HASPAY);
				list.add(Constants.OrderType.ORDER_DOING);
				list.add(Constants.OrderType.ORDER_TODONE);
				example.createCriteria().andMemberIdEqualTo(memberId).andOrderStatusIn(list);
			} else {
				example.createCriteria().andMemberIdEqualTo(memberId).andOrderStatusEqualTo(orderstatus);
			}
		} else {
			example.createCriteria().andMemberIdEqualTo(memberId);
		}
		List<WorkOrder> list = workOrderMapper.selectByExample(example);
		for (WorkOrder workOrder : list) {
			com.yhcrt.iHealthCloud.entity.Service service = serviceMapper.selectByPrimaryKey(workOrder.getServiceId());
			workOrder.setService(service);
		}
		return list;
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public String sendServiceOrder(JSONObject pdata) {
		// 获取参数
		JSONObject biz = pdata.getJSONObject("biz");
		String serviceid = biz.getString("service_id");
		String memberid = biz.getString("member_id");
		String servicetime = biz.getString("service_time");
		String openid = biz.getString("open_id");
		String addressid = biz.getString("address_id");
		String paytype = biz.getString("pay_type"); // small小程序 wechat 微信app alipay支付宝 alipay_pc支付宝
		String servicePrice =  biz.getString("service_price");
		String priceId = biz.getString("price_id");
		
		if (!judgeAgumentsIsLegal(pdata, serviceid, memberid, paytype, addressid, servicetime,priceId)) {
			requestFailed(pdata, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(pdata);
		}

		com.yhcrt.iHealthCloud.entity.Service service = serviceMapper.selectByPrimaryKey(Integer.valueOf(serviceid));
		Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(memberid));
		MemberAddress address = memberAddressMapper.selectByPrimaryKey(Integer.valueOf(addressid));
		ServicePrice price = priceMapper.selectByPrimaryKey(Integer.valueOf(priceId));
		
		if (service == null) {
			requestFailed(pdata, "服务信息数据异常,无法下单购买");
			return toJsonStringWithOutNull(pdata);
		}
		
		if (member == null) {
			requestFailed(pdata, "您的账号存在异常无法购买");
			return toJsonStringWithOutNull(pdata);
		}
	
		WorkOrder workOrder = new WorkOrder();
		workOrder.setServiceId(Integer.valueOf(serviceid));
		workOrder.setMemberId(Integer.valueOf(memberid));
		workOrder.setOrderContent(service.getServiceName());
		workOrder.setServiceObject(address.getRecipient());
		workOrder.setTel(address.getTel());
		workOrder.setAddress(address.getAddress());
		workOrder.setCreateTime(DateUtils.getCurrentTime());
		workOrder.setOrderStatus(Constants.OrderType.ORDER_NOPAY); // 待支付
		workOrder.setServicePrice(servicePrice);
		workOrder.setStartTime(servicetime);
		workOrder.setUnitPrice(price.getPrice());
		workOrder.setAmount(1); // 默认数量1
		workOrder.setServiceFee(price.getPrice());
		workOrder.setDiscounts(0d);
		String orderNo = DateUtil.getOrderNo();
		workOrder.setCext1(orderNo);
		String orderNum = DateUtil.getTranNum();
		workOrder.setCext2(orderNum);
		workOrder.setPayType(paytype);
		
		// 调用支付接口
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("orderId", orderNum);
		jsonParam.put("totalAmount", price.getPrice().toString());
		jsonParam.put("body", service.getServiceName() + "购买");
		String url = "";
		switch (paytype) {
			case "small":
				url = RequestUtils.SMALL_URL;
				jsonParam.put("openid", openid);
				jsonParam.put("callback", RequestUtils.SMALL_NOTIFY);
				break;
			case "wechat":
				url = RequestUtils.WECHAT_URL;
				jsonParam.put("callback", RequestUtils.WECHAT_NOTIFY);
				break;
			case "alipay":
				url = RequestUtils.ALIPAY_URL;
				jsonParam.put("subject", service.getServiceName());
				jsonParam.put("callback", RequestUtils.ALIPAY_NOTIFY);
				break;
			case "alipay_pc":
				url = RequestUtils.ALIPAY_PC_URL;
				jsonParam.put("callback", "");
				break;
			default:
				break;
		}
		
		try {
			workOrderMapper.insertSelective(workOrder);
			String resulstStr = RequestUtils.httpPostWithJson(jsonParam, url);
			JSONObject json = JSONObject.parseObject(resulstStr);
			if ("small".equals(paytype) || "wechat".equals(paytype)) {
				json.put("order_num", orderNo);
				json.put("totalAmount", jsonParam.get("totalAmount"));
				json.put("createTime", workOrder.getCreateTime());
				json.put("accessToken", WxAppletUserInfo.getAccessToken().get("access_token"));
				pdata.put(Const.TAG_BIZ, json);
			} else {
				pdata.put(Const.TAG_BIZ, resulstStr);
			}
			pdata.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdata.put(Const.TAG_RMK, "获取成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			requestFailed(pdata, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(pdata);
	}

	@Override
	public String payWorkorder(JSONObject pdata) {
		// 获取参数
		JSONObject biz = pdata.getJSONObject("biz");
		String orderNo = biz.getString("order_num");
		// 传给微信的交易订单单号
		String tranNum = DateUtil.getTranNum();
		String openid = biz.getString("open_id");
		WorkOrderExample example = new WorkOrderExample();
		example.createCriteria().andCext1EqualTo(orderNo);
		List<WorkOrder> empList = workOrderMapper.selectByExample(example);
		if (empList.size() != 1) {
			pdata.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdata.put(Const.TAG_RMK, "该订单号有问题");
			return pdata.toJSONString();
		}
		WorkOrder workOrder = empList.get(0);
		if (workOrder.getOrderStatus() != null && workOrder.getOrderStatus() > Constants.OrderType.ORDER_NOPAY) { // 不是待支付
			pdata.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdata.put(Const.TAG_RMK, "该订单号已经支付");
			return pdata.toJSONString();
		}
		com.yhcrt.iHealthCloud.entity.Service service = serviceMapper.selectByPrimaryKey(Integer.valueOf(workOrder.getServiceId()));
		Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(workOrder.getMemberId()));
		if (service != null && member != null) {
			// 调用支付接口
			String paytype = workOrder.getPayType();
			JSONObject jsonParam = new JSONObject();
			jsonParam.put("orderId", tranNum);
			String totalAmount = service.getUnitPrice().toString();
			jsonParam.put("totalAmount", totalAmount);
			jsonParam.put("body", service.getServiceName() + "购买");

			String url = "";
			switch (paytype) {
			case "small":
				url = RequestUtils.SMALL_URL;
				jsonParam.put("openid", openid);
				jsonParam.put("callback", RequestUtils.SMALL_NOTIFY);
				break;
			case "wechat":
				url = RequestUtils.WECHAT_URL;
				jsonParam.put("callback", RequestUtils.WECHAT_NOTIFY);
				break;
			case "alipay":
				url = RequestUtils.ALIPAY_URL;
				jsonParam.put("subject", service.getServiceName());
				jsonParam.put("callback", RequestUtils.ALIPAY_NOTIFY);
				break;
			case "alipay_pc":
				url = RequestUtils.ALIPAY_PC_URL;
				jsonParam.put("callback", "");
				break;
			default:
				break;
			}
			String resulstStr = RequestUtils.httpPostWithJson(jsonParam, url);
			if (!StringUtils.isEmpty(resulstStr)) {
				if ("small".equals(paytype) || "wechat".equals(paytype)) {
					JSONObject json = JSONObject.parseObject(resulstStr);
					json.put("order_num", workOrder.getCext1());
					if ("small".equals(paytype)) {
						json.put("totalAmount", jsonParam.get("totalAmount"));
						json.put("createTime", workOrder.getCreateTime());
						json.put("accessToken", WxAppletUserInfo.getAccessToken().get("access_token"));
					}
					pdata.put(Const.TAG_BIZ, json);
				} else {
					pdata.put(Const.TAG_BIZ, resulstStr);
				}
				pdata.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdata.put(Const.TAG_RMK, "获取成功");

				// 不管成功或失败修改 数据库交易单号
				workOrder.setCext2(tranNum);
				workOrderMapper.updateTranNum(workOrder);
			}
		} else {
			requestFailed(pdata, "缺少会员或服务信息");
		}
		return pdata.toJSONString();
	}

	@Override
	public WorkOrder getOrderInfo(Integer cid) {
		WorkOrder order = get(cid);
		com.yhcrt.iHealthCloud.entity.Service service = serviceMapper.selectByPrimaryKey(order.getServiceId());
		order.setService(service);
		return order;
	}

	@Override
	public void updateStatus(Integer cid, Integer orderStatus, String cext3) {
		WorkOrder order = new WorkOrder();
		order.setOrderId(cid);
		order.setOrderStatus(orderStatus);
		order.setCext3(cext3);
		workOrderMapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public WorkOrder getByCext1(String cext1) {
		WorkOrderExample example = new WorkOrderExample();
		example.createCriteria().andCext1EqualTo(cext1);
		return workOrderMapper.selectByExample(example).size() > 0 ? workOrderMapper.selectByExample(example).get(0) : new WorkOrder();
	}

	@Override
	public void updateAddress(Integer cid, String serviceObject, String tel, String address) {
		WorkOrder order = new WorkOrder();
		order.setOrderId(cid);
		order.setServiceObject(serviceObject);
		order.setTel(tel);
		order.setAddress(address);
		workOrderMapper.updateByPrimaryKeySelective(order);

	}

	@Override
	@Transactional
	public void updateRefund(Integer orderid, Integer status, String refundtype, String content) {
		// 查询工单明细信息
		WorkOrder order = workOrderMapper.selectByPrimaryKey(orderid);
		if (order != null) {
			order.setOrderStatus(status);
			workOrderMapper.updateByPrimaryKey(order);

			// 根据cid和type查询记录是否存在
			OrderRefundExample example = new OrderRefundExample();
			example.createCriteria().andRefIdEqualTo(order.getOrderId()).andTypeEqualTo(Constants.SERVICE);
			long count = orderRefundMapper.countByExample(example);
			if (count == 0) {
				// 新增退款表数据
				OrderRefund orderRefund = new OrderRefund();
				orderRefund.setRefId(order.getOrderId());
				orderRefund.setApplyTime(DateUtil.getDateTime());
				orderRefund.setRefundMoney(order.getActualPayment());
				orderRefund.setRefundType(refundtype);
				orderRefund.setRefundReson(content);
				orderRefund.setType(Constants.SERVICE);
				orderRefund.setStatus(status); // 表示退款中
				orderRefundMapper.insert(orderRefund);
			}
		}
	}
	
	@Override
	public String uploadServiceLog(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String orderId = bizObj.getString("order_id");
		String empId = bizObj.getString("emp_id");
		String serviceContent = bizObj.getString("service_content");
		String servciePic = bizObj.getString("service_pic");
		String serviceAddress = bizObj.getString("service_address");
		if (StringUtils.isBlank(orderId) || StringUtils.isBlank(empId)) {
			requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(jsonObject);
		}
		try {
			ServiceLog serviceLog = new ServiceLog();
			serviceLog.setOrderId(Integer.valueOf(orderId));
			serviceLog.setHandler(empId);
			serviceLog.setServiceContent(serviceContent);
			serviceLog.setCreateTime(DateUtils.getCurrentTime());
			serviceLog.setServiceAddress(serviceAddress);
			serviceLogMapper.insert(serviceLog);
			// 上传服务场景图
			servciePic = StringUtils.isNotBlank(servciePic) ? servciePic : "";
			String[] pics = servciePic.split(",");
			for (String pic : pics) {
				YwImage image = new YwImage();
				image.setRefId(Integer.valueOf(orderId));
				image.setModuleCode("service_log");
				image.setImgPath(pic);
				image.setUploadTime(DateUtils.getCurrentTime());
				ywImageMapper.insert(image);
			}
			WorkOrder order = workOrderMapper.selectByPrimaryKey(Integer.valueOf(orderId));
			if (order != null) {
				order.setOrderStatus(Constants.WorkOrderStatus.COMPLETE_PENDING_CONFIRMATION);
				workOrderMapper.updateByPrimaryKeySelective(order);
			}
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String getServiceLogDetail(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String orderId = bizObj.getString("order_id");
		try {
			OrderServiceLog orderServiceLog = workOrderMapper.getWorkOrderServiceLogDetail(Integer.valueOf(orderId));
			if (orderServiceLog != null) {
				YwImageExample example = new YwImageExample();
				example.createCriteria().andRefIdEqualTo(Integer.valueOf(orderId)).andModuleCodeEqualTo("service_log");
				List<YwImage> servicePic = ywImageMapper.selectByExample(example);
				orderServiceLog.setServicePic(servicePic);
				jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				jsonObject.put(Const.TAG_BIZ, orderServiceLog);
			} else {
				requestFailed(jsonObject, Constants.ExceptionMsg.ORDER_NOTFOUND);
			}

		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String confirmCompleted(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String orderId = bizObj.getString("order_id");
		try {
			WorkOrder workOrder = workOrderMapper.selectByPrimaryKey(Integer.valueOf(orderId));
			workOrder.setOrderStatus(Constants.WorkOrderStatus.COMPLETED);
			workOrderMapper.updateByPrimaryKeySelective(workOrder);
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String getWorkOrderTraceDetail(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String orderId = bizObj.getString("order_id");
		if (StringUtils.isBlank(orderId)) {
			requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(jsonObject);
		}
		WorkOrderTrace workOrderTrace = workOrderMapper.getWorkOrderTraceDetail(Integer.valueOf(orderId));
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_BIZ, workOrderTrace);
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String getOrgServicers(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String orgId = bizObj.getString("org_id");
		if (StringUtils.isBlank(orgId)) {
			requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(jsonObject);
		}
		EmployeeExample example = new EmployeeExample();
		example.createCriteria().andOrgIdEqualTo(Integer.valueOf(orgId)).andSpecialtyEqualTo(Constants.EMP_SPECIALTY_SERVICER);
		List<OrgServicer> orgServicers = employeeMapper.getOrgServicersByExample(example);
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_BIZ, orgServicers);
		return toJsonStringWithOutNull(jsonObject);
	}
}
