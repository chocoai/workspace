package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Cart;
import com.yhcrt.iHealthCloud.entity.CartExample;
import com.yhcrt.iHealthCloud.entity.Goods;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.MemberAddress;
import com.yhcrt.iHealthCloud.entity.OrderDetail;
import com.yhcrt.iHealthCloud.entity.OrderDetailExample;
import com.yhcrt.iHealthCloud.entity.OrderRefund;
import com.yhcrt.iHealthCloud.entity.OrderRefundExample;
import com.yhcrt.iHealthCloud.entity.SysDictionary;
import com.yhcrt.iHealthCloud.entity.YwOrder;
import com.yhcrt.iHealthCloud.entity.YwOrderExample;
import com.yhcrt.iHealthCloud.mapper.CartMapper;
import com.yhcrt.iHealthCloud.mapper.GoodsMapper;
import com.yhcrt.iHealthCloud.mapper.MemberAddressMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.OrderDetailMapper;
import com.yhcrt.iHealthCloud.mapper.OrderRefundMapper;
import com.yhcrt.iHealthCloud.mapper.YwOrderMapper;
import com.yhcrt.iHealthCloud.service.OrderDetailService;
import com.yhcrt.iHealthCloud.service.SysDictService;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.service.YwOrderService;
import com.yhcrt.iHealthCloud.util.Arith;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;
import com.yhcrt.iHealthCloud.util.DateUtils;
import com.yhcrt.iHealthCloud.util.EmojiUtil;
import com.yhcrt.iHealthCloud.util.OrgConst;
import com.yhcrt.iHealthCloud.util.RequestUtils;
import com.yhcrt.iHealthCloud.util.StringUtil;
import com.yhcrt.iHealthCloud.util.WxAppletUserInfo;

/**
 * @Description:
 * @version 1.0 2017年9月7日
 * @author jimmy
 */
@Service
public class YwOrderServiceImpl extends BaseService implements YwOrderService {

	@Autowired
	private YwOrderMapper ywOrderMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private MemberAddressMapper memberAddressMapper;
	@Autowired
	private OrderRefundMapper orderRefundMapper;
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private SysSequenceService sequenceService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	SysDictService sysDictService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yhcrt.healthcloud.mall.service.YwOrderService#add(com.yhcrt.
	 * healthcloud.mall.entity.YwOrder)
	 */
	@Override
	public Integer add(YwOrder ywOrder) {
		return ywOrderMapper.insert(ywOrder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yhcrt.healthcloud.mall.service.YwOrderService#del(java.lang.String)
	 */
	@Override
	public Integer del(Integer cid) {
		return ywOrderMapper.deleteByPrimaryKey(cid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yhcrt.healthcloud.mall.service.YwOrderService#upd(com.yhcrt.
	 * healthcloud.mall.entity.YwOrder)
	 */
	@Override
	public Integer upd(YwOrder ywOrder) {
		return ywOrderMapper.updateByPrimaryKey(ywOrder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yhcrt.healthcloud.mall.service.YwOrderService#get(java.lang.String)
	 */
	@Override
	public YwOrder get(Integer cid) {
		return ywOrderMapper.selectByPrimaryKey(cid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yhcrt.healthcloud.mall.service.YwOrderService#getAll()
	 */
	@Override
	public List<YwOrder> getAll(HashMap<String, Object> params) {
		return ywOrderMapper.search(params);
	}

	@Override
	public YwOrder getByOrderNo(String orderNo) {
		YwOrderExample example = new YwOrderExample();
		example.createCriteria().andCext2EqualTo(orderNo);
		List<YwOrder> ywOrderList = ywOrderMapper.selectByExample(example);
		if (ywOrderList.size() > 0) {
			return ywOrderList.get(0);
		}
		return null;
	}

	@Override
	public String sendGoodsOrder(JSONObject pdata) { // 商品购买 （不包含购物车支付）
		// 获取参数
		JSONObject biz = pdata.getJSONObject("biz");
		String goodsid = biz.getString("goods_id");
		String memberid = biz.getString("member_id");
		String goodsnumber = biz.getString("goods_number");
		String openid = biz.getString("open_id");
		String addressid = biz.getString("address_id");
		String paytype = biz.getString("pay_type"); // small小程序 wechat 微信app alipay支付宝  alipay_pc支付宝
		if (judgeAgumentsIsLegal(pdata, goodsid, memberid, paytype, addressid, goodsnumber)) {
			Goods goods = goodsMapper.selectByPrimaryKey(Integer.valueOf(goodsid));
			Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(memberid));
			MemberAddress address = memberAddressMapper.selectByPrimaryKey(Integer.valueOf(addressid));
			if (goods != null && member != null) {

				int orderId = sequenceService.getSequenceValue(Constants.SequenceName.YW_ORDER);
				// 订单明细表保存
				OrderDetail orderDetail = new OrderDetail();
				int orderDetailId = sequenceService.getSequenceValue(Constants.SequenceName.ORDER_DETAIL);
				orderDetail.setCid(orderDetailId);
				orderDetail.setOrderId(orderId);
				orderDetail.setGoodsId(goods.getGoodsId());
				orderDetail.setPrice(goods.getUnitPrice());
				orderDetail.setAmount(Integer.valueOf(goodsnumber));
				Double totalPay = Arith.round(Arith.mul(goods.getUnitPrice(), Double.parseDouble(goodsnumber)), 2);
				orderDetail.setTotalPay(totalPay);
				orderDetail.setActualPayment(totalPay);
				orderDetail.setCreateTime(DateUtils.dateToString19(new Date()));
				orderDetail.setStatus(Constants.OrderType.ORDER_NOPAY); // -1取消  0待支付 1已支付 2已发货/进行中 3待评价/待审核 4完成
				orderDetailMapper.insert(orderDetail);
				// 订单表保存
				YwOrder goodsOrder = new YwOrder();
				goodsOrder.setOrderId(orderId);
				goodsOrder.setMemberId(Integer.valueOf(memberid));
				goodsOrder.setAddressId(address.getCid());
				goodsOrder.setRecipient(address.getRecipient());
				goodsOrder.setPhoneNum(address.getTel());
				goodsOrder.setAddress(address.getAddress());
				goodsOrder.setCreateTime(DateUtils.dateToString19(new Date()));
				goodsOrder.setOrderStatus(Constants.OrderType.ORDER_NOPAY); // -1取消 0待支付 1已支付 2已发货/进行中 3待评价/待审核 4完成
				goodsOrder.setOrderAmount(orderDetail.getTotalPay());
				goodsOrder.setDiscounts(0d);
				String orderNo = DateUtil.getOrderNo();
				goodsOrder.setCext1(orderNo);
				String orderNum = DateUtil.getTranNum();
				goodsOrder.setCext2(orderNum);
				goodsOrder.setPayType(paytype);
				ywOrderMapper.insertSelective(goodsOrder);

				// 调用支付接口
				JSONObject jsonParam = new JSONObject();
				jsonParam.put("orderId", orderNum);
				jsonParam.put("totalAmount", String.valueOf(totalPay));
				jsonParam.put("body", goods.getGoodsName() + "购买");
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
					jsonParam.put("subject", goods.getGoodsName());
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
						json.put("order_num", orderNo);
						if ("small".equals(paytype)) {
							json.put("amount", goodsnumber);
							json.put("totalAmount", jsonParam.get("totalAmount"));
							json.put("createTime", goodsOrder.getCreateTime());
							json.put("accessToken", WxAppletUserInfo.getAccessToken().get("access_token"));
						}
						pdata.put(Const.TAG_BIZ, json);
					} else {
						pdata.put(Const.TAG_BIZ, resulstStr);
					}
					pdata.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
					pdata.put(Const.TAG_RMK, "获取成功");
				}
				pdata.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdata.put(Const.TAG_RMK, "获取成功");
			} else {
				requestFailed(pdata, "缺少会员或服务信息");
			}
		} else {
			requestFailed(pdata, Const.RMK_BIZ_PARAM_NULL);
		}
		return pdata.toJSONString();
	}

	@Override
	public String sendCartOrder(JSONObject pdata) { // 商品购买 ----购物车模式
		// 获取参数
		JSONObject biz = pdata.getJSONObject("biz");
		String memberid = biz.getString("member_id");
		String openid = biz.getString("open_id");
		String addressid = biz.getString("address_id");
		String paytype = biz.getString("pay_type"); // small小程序 wechat 微信app alipay支付宝 alipay_pc支付宝
		if (judgeAgumentsIsLegal(pdata, memberid, paytype, addressid)) {
			Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(memberid));
			CartExample cartExample = new CartExample();
			cartExample.createCriteria().andMemberIdEqualTo(member.getMemberId()).andStatusEqualTo(Constants.STATUS_NORMAL);
			List<Cart> cartList = cartMapper.selectByExample(cartExample);
			MemberAddress address = memberAddressMapper.selectByPrimaryKey(Integer.valueOf(addressid));
			Integer orderId = null;
			Double totalAmount = 0d;
			if (cartList.size() > 0) {
				orderId = sequenceService.getSequenceValue(Constants.SequenceName.YW_ORDER);
			}
			for (Cart cart : cartList) {
				Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
				int orderDetailId = sequenceService.getSequenceValue(Constants.SequenceName.ORDER_DETAIL);
				// 订单明细表保存
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setCid(orderDetailId);
				orderDetail.setOrderId(orderId);
				orderDetail.setGoodsId(goods.getGoodsId());
				orderDetail.setPrice(goods.getUnitPrice());
				orderDetail.setAmount(cart.getAmount());
				Double totalPay = Arith.round(Arith.mul(goods.getUnitPrice(), cart.getAmount()), 2);
				orderDetail.setTotalPay(totalPay);
				orderDetail.setActualPayment(totalPay);
				totalAmount = Arith.round(Arith.add(totalPay, totalAmount), 2);
				orderDetail.setCreateTime(DateUtils.dateToString19(new Date()));
				orderDetail.setStatus(Constants.OrderType.ORDER_NOPAY); // -1取消  0待支付  1已支付  2已发货/进行中 3待评价/待审核 4完成
				orderDetailMapper.insert(orderDetail);
			}
			if (orderId != null) {
				// 订单表保存
				YwOrder goodsOrder = new YwOrder();
				goodsOrder.setOrderId(orderId);
				goodsOrder.setMemberId(Integer.valueOf(memberid));
				goodsOrder.setAddressId(address.getCid());
				goodsOrder.setCreateTime(DateUtils.dateToString19(new Date()));
				goodsOrder.setOrderStatus(Constants.OrderType.ORDER_NOPAY); // -1取消 0待支付 1已支付 2已发货/进行中 3待评价/待审核 4完成
				goodsOrder.setOrderAmount(totalAmount);
				goodsOrder.setDiscounts(0d);
				String orderNo = DateUtil.getOrderNo();
				goodsOrder.setCext1(orderNo);
				String orderNum = DateUtil.getTranNum();
				goodsOrder.setCext2(orderNum);
				goodsOrder.setPayType(paytype);
				goodsOrder.setRecipient(address.getRecipient());
				goodsOrder.setPhoneNum(address.getTel());
				goodsOrder.setAddress(address.getAddress());
				ywOrderMapper.insertSelective(goodsOrder);
				CartExample example = new CartExample();
				example.createCriteria().andMemberIdEqualTo(member.getMemberId()).andStatusEqualTo(Constants.STATUS_NORMAL);
				cartMapper.deleteByExample(example); // 生成订单后将购物车清空

				// 调用支付接口
				JSONObject jsonParam = new JSONObject();
				jsonParam.put("orderId", orderNum);
				jsonParam.put("totalAmount", String.valueOf(totalAmount));
				jsonParam.put("body", "购物车商品购买");
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
						json.put("order_num", orderNo);
						pdata.put(Const.TAG_BIZ, json);
					} else {
						pdata.put(Const.TAG_BIZ, resulstStr);
					}
					pdata.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
					pdata.put(Const.TAG_RMK, "获取成功");
				}
				pdata.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdata.put(Const.TAG_RMK, "获取成功");
			}
		} else {
			requestFailed(pdata, Const.RMK_BIZ_PARAM_NULL);
		}
		return pdata.toJSONString();
	}

	@Override
	public String payYworder(JSONObject pdata) {
		// 获取参数
		JSONObject biz = pdata.getJSONObject("biz");
		String orderNo = biz.getString("order_num");
		// 支付类型
		String paytype = biz.getString("pay_type");
		// 传给微信的交易订单单号
		String tranNum = DateUtil.getTranNum();
		String openid = biz.getString("open_id");
		YwOrderExample example = new YwOrderExample();
		example.createCriteria().andCext1EqualTo(orderNo);
		List<YwOrder> empList = ywOrderMapper.selectByExample(example);
		if (empList.size() != 1) {
			pdata.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdata.put(Const.TAG_RMK, "该订单号有问题");
			return pdata.toJSONString();
		}
		YwOrder ywOrder = empList.get(0);
		if (ywOrder.getOrderStatus() != null && ywOrder.getOrderStatus() >= Constants.OrderType.ORDER_HASPAY) { // 已支付
			pdata.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdata.put(Const.TAG_RMK, "该订单号已经支付");
			return pdata.toJSONString();
		}
		// 调用支付接口
		JSONObject jsonParam = new JSONObject();
		// String paytype = ywOrder.getPayType();
		jsonParam.put("orderId", tranNum);
		jsonParam.put("totalAmount", String.valueOf(ywOrder.getOrderAmount()));
		jsonParam.put("body", "商品购买");
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
			jsonParam.put("subject", "商品购买");
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
				json.put("order_num", ywOrder.getCext1());
				if ("small".equals(paytype)) {
					json.put("totalAmount", jsonParam.get("totalAmount"));
					json.put("createTime", ywOrder.getCreateTime());
					json.put("accessToken", WxAppletUserInfo.getAccessToken().get("access_token"));
				}
				pdata.put(Const.TAG_BIZ, json);
			} else {
				pdata.put(Const.TAG_BIZ, resulstStr);
			}
			pdata.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdata.put(Const.TAG_RMK, "获取成功");

			// 不管成功或失败修改 数据库交易单号
			ywOrder.setCext2(tranNum);
			ywOrderMapper.updateTranNum(ywOrder);
		}
		return pdata.toJSONString();
	}

	@Override
	public String getYwOrderList(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String memberId = biz.getString("member_id");
		String orderStatus = biz.getString("order_status");
		String currentPage = biz.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = biz.getString(Const.TAG_PAGE_SIZE);
		// 对参数进行非空判断
		if (StringUtils.isBlank(memberId)) {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(pdataObj);
		}
		// 向数据库请求数据并判断是否分页
		List<YwOrder> orders = new ArrayList<YwOrder>();
		if (StringUtils.isNotBlank(pageSize) && StringUtils.isNotBlank(currentPage)) {
			PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
			orders = selectYwOrderList(memberId, orderStatus);
			PageInfo<YwOrder> pageInfo = new PageInfo<>(orders);
			setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
		} else {
			orders = selectYwOrderList(memberId, orderStatus);
		}
		pdataObj.put(Const.TAG_BIZ, orders);
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		pdataObj.put(Const.TAG_RMK, "获取成功！");
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getYwOrderDetail(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String ordernum = biz.getString("order_num");
		YwOrderExample example = new YwOrderExample();
		example.createCriteria().andCext1EqualTo(ordernum);
		List<YwOrder> orders = ywOrderMapper.selectByExample(example);
		if (orders.size() < 1) {
			requestFailed(pdataObj, "获取失败,未查询到此订单号！");
			return toJsonStringWithOutNull(pdataObj);
		}
		pdataObj.put(Const.TAG_BIZ, orders.get(0));
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		pdataObj.put(Const.TAG_RMK, "获取成功！");
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String setYwOrderStatus(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String ordernum = biz.getString("order_num");
		String orderstatus = biz.getString("order_status");
		if (StringUtils.isEmpty(orderstatus)) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, "订单状态参数不能为空！");
			return pdataObj.toJSONString();
		}
		YwOrderExample example = new YwOrderExample();
		example.createCriteria().andCext1EqualTo(ordernum);
		List<YwOrder> empList = ywOrderMapper.selectByExample(example);
		if (empList.size() > 0) {
			YwOrder ywOrder = empList.get(0);
			ywOrder.setOrderStatus(Integer.valueOf(orderstatus));
			ywOrderMapper.updateByPrimaryKeySelective(ywOrder);
			orderDetailMapper.setDetailStatus(ywOrder.getOrderId(), Integer.valueOf(orderstatus));
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "数据修改成功！");
		} else {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, "获取失败,未查询到此订单号！");
		}
		return pdataObj.toJSONString();
	}

	private List<YwOrder> selectYwOrderList(String memberId, String orderstatus) {
		YwOrderExample example = new YwOrderExample();
		example.setOrderByClause("create_time desc");
		if (StringUtils.isNotBlank(orderstatus)) {
			example.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId))
					.andOrderStatusEqualTo(Integer.valueOf(orderstatus));
		} else {
			example.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId));
		}
		List<YwOrder> orders = ywOrderMapper.selectByExample(example);
		return orders;
	}
	@Override
	public YwOrder getByCext1(String cext1) {
		return ywOrderMapper.selectByCext1(cext1);
	}

	public List<YwOrder> selectYwOrderList(Integer memberId, Integer orderstatus) {
		YwOrderExample example = new YwOrderExample();
		example.setOrderByClause("create_time desc");
		if(orderstatus==null){
			example.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId));
		}else{
			if(orderstatus.equals(Constants.OrderType.ORDER_HASPAY)){
				List<Integer> list = new ArrayList<Integer>();
				list.add(Constants.OrderType.ORDER_HASPAY);
				list.add(Constants.OrderType.ORDER_DOING);
				list.add(Constants.OrderType.ORDER_TODONE);
				example.createCriteria().andMemberIdEqualTo(memberId).andOrderStatusIn(list);
			}else{
				example.createCriteria().andMemberIdEqualTo(memberId).andOrderStatusEqualTo(orderstatus);
			}
		}
		List<YwOrder> list = ywOrderMapper.selectByExample(example);
		if (list.size() > 0) {
			for (YwOrder ywOrder : list) {
				OrderDetailExample orderDetailExample = new OrderDetailExample();
				orderDetailExample.createCriteria().andOrderIdEqualTo(ywOrder.getOrderId());
				List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(orderDetailExample);
				ywOrder.setOrderDetails(orderDetails);
			}
		}
		return list;
	}
	public List<YwOrder> selectYwOrderList(Integer orderstatus) {
		YwOrderExample example = new YwOrderExample();
		example.setOrderByClause("create_time desc");
		if(orderstatus==null){
			example.createCriteria();
		}else{
			example.createCriteria().andOrderStatusEqualTo(orderstatus);

		}
		return ywOrderMapper.selectByExample(example);
	}


	@Override
	public void updateStatus(Integer cid, Integer orderStatus,String cetx3) {
		orderDetailMapper.setDetailStatus(cid, orderStatus);
		YwOrder ywOrder = new YwOrder();
		ywOrder.setOrderId(cid);
		ywOrder.setOrderStatus(orderStatus);
		ywOrder.setCext3(cetx3);
		ywOrderMapper.updateByPrimaryKeySelective(ywOrder);
	}
	
	@Override
	public void updateAddress(Integer cid,Integer addressId) {
		YwOrder ywOrder = new YwOrder();
		ywOrder.setOrderId(cid);
		ywOrder.setAddressId(addressId);
		ywOrderMapper.updateByPrimaryKeySelective(ywOrder);
	}

	// 退款修改订单状态
	@Override
	@Transactional
	public void updateRefund(Integer orderid, Integer cid, Integer status, 
			String refundtype, String content) {
		if(cid != null){	//表示从主订单中推某一商品
			//查询商品明细信息
			OrderDetail detail = orderDetailMapper.selectByPrimaryKey(cid);
			if(detail != null){
				detail.setStatus(status);
				orderDetailMapper.updateByPrimaryKey(detail);
				
				//根据cid和type查询记录是否存在
				OrderRefundExample example = new OrderRefundExample();
				example.createCriteria().andRefIdEqualTo(cid).andTypeEqualTo(Constants.GOODS);
				long count = orderRefundMapper.countByExample(example);
				if(count == 0){
					//新增退款表数据
					OrderRefund orderRefund = new OrderRefund();
					orderRefund.setRefId(cid);
					orderRefund.setApplyTime(DateUtil.getDateTime());
					orderRefund.setRefundMoney(detail.getActualPayment());
					orderRefund.setRefundType(refundtype);
					orderRefund.setRefundReson(content);
					orderRefund.setType(Constants.GOODS);
					orderRefund.setStatus(status);	//表示退款中
					orderRefundMapper.insert(orderRefund);
				}
				
			}
		}else{	//表示主订单中所有商品全部退款
			
		}
	}

	@Override
	public String refund(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String orderDetailId = bizObj.getString("order_detail_id");
		Integer id = Integer.parseInt(orderDetailId);
		OrderDetail order = orderDetailService.get(id);
		
		JSONArray array = new JSONArray();
		List<SysDictionary> refundtype = sysDictService.selectDictEnName(OrgConst.REFUND_TYPE);
		for (SysDictionary d : refundtype) {
			JSONObject json = new JSONObject();
			json.put("title", d.getDictKey());
			json.put("value", d.getDictValue());
			array.add(json);
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("orderDetail", order);
		jsonObj.put("refundType", array);
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_BIZ, jsonObj);
		return toJsonStringWithOutNull(jsonObject);
	}
	
	// 退款修改订单状态
	@Override
	@Transactional
	public String updateRefund(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		// String orderId = bizObj.getString("order__id");
		String orderDetailId = bizObj.getString("order_detail_id");
		String refundType = bizObj.getString("refund_type");
		String content = bizObj.getString("content");
		if(StringUtil.isNotEmpty(content)){
			content = EmojiUtil.emojiConvert(content);
		}
		try {
			if(!StringUtil.isEmpty(orderDetailId)){	//表示从主订单中推某一商品
				//查询商品明细信息
				Integer  cid = Integer.parseInt(orderDetailId);
				OrderDetail detail = orderDetailMapper.selectByPrimaryKey(cid);
				if(detail != null){
					detail.setStatus(Constants.OrderType.BACKORDER);
					orderDetailMapper.updateByPrimaryKey(detail);
						
					//根据cid和type查询记录是否存在
					OrderRefundExample example = new OrderRefundExample();
					example.createCriteria().andRefIdEqualTo(cid).andTypeEqualTo(Constants.GOODS);
						long count = orderRefundMapper.countByExample(example);
						if(count == 0){
							//新增退款表数据
							OrderRefund orderRefund = new OrderRefund();
							orderRefund.setRefId(cid);
							orderRefund.setApplyTime(DateUtil.getDateTime());
							orderRefund.setRefundMoney(detail.getActualPayment());
							orderRefund.setRefundType(refundType);
							orderRefund.setRefundReson(content);
							orderRefund.setType(Constants.GOODS);
							orderRefund.setStatus(Constants.OrderType.BACKORDER);	//表示退款中
							orderRefundMapper.insert(orderRefund);
							jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
							jsonObject.put(Const.TAG_RMK, "退货成功！");
						}else{
							jsonObject.put(Const.TAG_STS, Const.TAG_STS_FAIL);
							jsonObject.put(Const.TAG_RMK, "该订单明已被退货");
						}
				}else{
					jsonObject.put(Const.TAG_STS, Const.TAG_STS_FAIL);
					jsonObject.put(Const.TAG_RMK, "不存在该订单明细！");
				}
			}else{	//表示主订单中所有商品全部退款
					
			}
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}
}
