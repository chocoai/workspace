package com.yhcrt.iHealthCloud.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Cart;
import com.yhcrt.iHealthCloud.entity.Goods;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.MemberAddress;
import com.yhcrt.iHealthCloud.entity.OrderDetail;
import com.yhcrt.iHealthCloud.entity.Service;
import com.yhcrt.iHealthCloud.entity.ServicePrice;
import com.yhcrt.iHealthCloud.entity.SysDictionary;
import com.yhcrt.iHealthCloud.entity.UserCollect;
import com.yhcrt.iHealthCloud.entity.WorkOrder;
import com.yhcrt.iHealthCloud.entity.YwOrder;
import com.yhcrt.iHealthCloud.mapper.CartMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.SysUserMapper;
import com.yhcrt.iHealthCloud.service.CartService;
import com.yhcrt.iHealthCloud.service.GoodsService;
import com.yhcrt.iHealthCloud.service.MemberAddressService;
import com.yhcrt.iHealthCloud.service.OrderDetailService;
import com.yhcrt.iHealthCloud.service.ServicePriceService;
import com.yhcrt.iHealthCloud.service.ServiceService;
import com.yhcrt.iHealthCloud.service.SysDictService;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.service.UserCollectService;
import com.yhcrt.iHealthCloud.service.UserCommentService;
import com.yhcrt.iHealthCloud.service.UserService;
import com.yhcrt.iHealthCloud.service.WorkOrderService;
import com.yhcrt.iHealthCloud.service.YwOrderService;
import com.yhcrt.iHealthCloud.util.Arith;
import com.yhcrt.iHealthCloud.util.DateUtil;
import com.yhcrt.iHealthCloud.util.DateUtils;
import com.yhcrt.iHealthCloud.util.OrgConst;

/**
 * h5 已登录
 * 
 * @author ql
 *
 */
@Controller
@RequestMapping(value = "/h5/admin")
public class AdminController {

	@Autowired
	MemberDeviceMapper memberDeviceMapper;
	@Autowired
	CartMapper cartMapper;
	@Autowired
	SysUserMapper sysUserMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	GoodsService goodsService;
	@Autowired
	ServiceService serviceService;
	@Autowired
	SysDictService sysDictService;
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	SysSequenceService sysSequenceService;
	@Autowired
	YwOrderService ywOrderService;
	@Autowired
	WorkOrderService workOrderService;
	@Autowired
	UserCollectService userCollectService;
	@Autowired
	MemberAddressService memberAddressService;
	@Autowired
	UserCommentService userCommentService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	ServicePriceService servicePriceService;

	// 添加商品到购物车
	@RequestMapping("/cart/add")
	@ResponseBody
	public void insertCart(HttpServletRequest request, HttpServletResponse response, Integer goodsId, Integer amount) {
		try {
			String result = "";
			Member member = (Member) request.getSession().getAttribute("memberSession");
			if (member == null) {
				result = "未登陆";
			}
			List<Cart> carts = cartService.validateCart(member.getMemberId(), goodsId);
			if (carts.size() > 0) {
				result = "购物车中已存在此商品";
			} else {
				Cart cart = new Cart();
				cart.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.CART));
				cart.setMemberId(member.getMemberId());
				cart.setGoodsId(goodsId);
				cart.setAmount(amount);
				cart.setStatus(1);
				cart.setCreateTime(DateUtil.date2StrLong(new Date()));
				int i = cartService.add(cart);
				if (i > 0) {
					result = "加入购物车成功";
				} else {
					result = "加入购物车失败";
				}
			}
			JSONObject json = new JSONObject();
			json.put("rmk", result);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除我的购物车
	 * 
	 * @param request
	 * @param response
	 * @param cartIdSub
	 */
	@RequestMapping("/cart/delete")
	@ResponseBody
	public void delete(HttpServletRequest request, HttpServletResponse response, String cartIdSub) {
		try {
			String result = "删除成功";
			if (cartIdSub != null && !"".equals(cartIdSub)) {
				for (String cartId : cartIdSub.split(",")) {
					cartMapper.deleteByPrimaryKey(Integer.parseInt(cartId));
				}
			}
			JSONObject json = new JSONObject();
			json.put("rmk", result);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改我的购物车
	 * 
	 * @param request
	 * @param response
	 * @param cid
	 * @param amount
	 */
	@RequestMapping("/cart/update")
	@ResponseBody
	public void update(HttpServletRequest request, HttpServletResponse response, Integer cid, Integer amount) {
		try {
			String result = "修改成功";
			Cart dto = cartMapper.selectByPrimaryKey(cid);
			if (null != dto) {
				dto.setAmount(amount);
				cartMapper.updateByPrimaryKey(dto);
			}
			JSONObject json = new JSONObject();
			json.put("rmk", result);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 进入提交订单页
	 * 
	 * @param request
	 * @param response
	 * @param idSub
	 *            购物车多个id之间用,隔开
	 * @param id
	 *            商品单个id
	 * @param amount
	 *            数量
	 * @param entrance
	 *            1代表购物车进入，2直接购买进入
	 * @return html
	 */
	@RequestMapping("/cart/getSubmitOrder")
	public String getSubmitOrder(HttpServletRequest request, HttpServletResponse response, Integer entrance,
			String idSub, Integer id, Integer amount, ModelMap modelMap) {
		Member member = (Member) request.getSession().getAttribute("memberSession");
		double total = 0D;
		if (entrance == 1) {
			if (idSub != null && !idSub.equals("")) {
				List<Cart> cart = new ArrayList<Cart>();
				for (String cid : idSub.split(",")) {
					Cart dto = cartService.get(Integer.parseInt(cid));
					if (null != dto) {
						cart.add(dto);
						total = Arith.round(Arith.add(total,Arith.mul(dto.getAmount(), dto.getGoods().getUnitPrice())), 2);
					}
				}
				modelMap.put("cart", cart);
			}
		} else if (entrance == 2) {
			Goods dto = goodsService.get(id);
			if (null != dto) {
				modelMap.put("cart", dto);
				total = Arith.round(Arith.mul(dto.getUnitPrice(), amount), 2);
			}
			modelMap.put("dto", dto);
			idSub = id.toString();
		}
		MemberAddress addressDto = memberAddressService.selectByIsDefault(member.getMemberId());
		if (null != addressDto) {
			modelMap.put("address", addressDto);
		} else {
			List<MemberAddress> list = memberAddressService.selectByExample(member.getMemberId());
			if (null != list && list.size() > 0) {
				MemberAddress dto = list.get(0);
				modelMap.put("address", dto);
				dto.setIsDefault(1);
				memberAddressService.updMemberAddress(dto);
			} else {
				modelMap.put("address", null);
			}
		}
		modelMap.put("amount", amount);
		modelMap.put("entrance", entrance);
		modelMap.put("total", total);
		modelMap.put("idSub", idSub);
		modelMap.put("id", id);
		return "submit_order.html";
	}

	/**
	 * 进入提交订单页
	 * 
	 * @param request
	 * @param id
	 *            服务单个id
	 * @param time
	 *            预约时间
	 * @param priceType
	 *            预约服务分类
	 * @param priceId
	 *            服务分类id
	 * @return html
	 */
	@RequestMapping("/getSubmitOrderService")
	public String getSubmitOrderService(HttpServletRequest request, Integer id, String time, Integer priceId,
			ModelMap modelMap) {
		Member member = (Member) request.getSession().getAttribute("memberSession");

		MemberAddress addressDto = memberAddressService.selectByIsDefault(member.getMemberId());
		if (null != addressDto) {
			modelMap.put("address", addressDto);
		} else {
			List<MemberAddress> list = memberAddressService.selectByExample(member.getMemberId());
			if (null != list && list.size() > 0) {
				MemberAddress dto = list.get(0);
				modelMap.put("address", dto);
				dto.setIsDefault(1);
				memberAddressService.updMemberAddress(dto);
			} else {
				modelMap.put("address", null);
			}
		}
		Service service = serviceService.get(id);
		if (priceId != null) {
			ServicePrice price = servicePriceService.findById(priceId);
			modelMap.put("price", price);
		}
		modelMap.put("service", service);
		modelMap.put("time", time);
		modelMap.put("id", id);
		return "submit_order_service.html";
	}

	@RequestMapping("/service/saveWorkOrder")
	@ResponseBody
	public void saveWorkOrder(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			Integer serviceId, Integer addressId, String time, Double price, String priceStr) {
		JSONObject json = new JSONObject();
		try {
			Member member = (Member) request.getSession().getAttribute("memberSession");
			MemberAddress memberAddress = memberAddressService.getMemberAddress(addressId);
			Service service = serviceService.get(serviceId);
			String orderNo = DateUtil.getOrderNo();

			WorkOrder order = new WorkOrder();
			Integer orderId = sysSequenceService.getSequenceValue(Constants.SequenceName.WORK_ORDER);
			order.setOrderId(orderId);
			order.setServiceId(serviceId);
			order.setMemberId(member.getMemberId());
			order.setServiceObject(memberAddress.getRecipient());
			order.setOrderContent(service.getServiceName());
			order.setTel(memberAddress.getTel());
			order.setAddress(memberAddress.getAddress());
			order.setStartTime(time);
			order.setCreateTime(DateUtil.date2StrLong(new Date()));
			order.setOrderStatus(Constants.OrderType.ORDER_NOPAY);
			order.setUnitPrice(price);
			order.setAmount(1);
			order.setServiceFee(price);
			order.setDiscounts(0d);
			order.setCext1(orderNo);
			order.setServicePrice(priceStr);
			Integer n = workOrderService.add(order);
			if (n > 0) {
				json.put("orderNo", orderNo);
			}
			json.put("result", n);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/service/getPayfor")
	public String getPayforService(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			String cext1, Integer type) {
		if (null != cext1) {
			WorkOrder dto = workOrderService.getByCext1(cext1);
			if (null != dto) {
				modelMap.put("totalMoney", dto.getUnitPrice());
				modelMap.put("orderNo", cext1);
				modelMap.put("type", Constants.SERVICE);
				if (type == null) {
					// 微信支付回调才用得上
					modelMap.put("weixinPay", "1");
				}
				modelMap.put("orderId", dto.getOrderId());
				modelMap.put("valid", DateUtil.validTime(dto.getCreateTime()));
				return "payfor.html";
			}
		}
		return "500.html";
	}

	/**
	 * 我的收货地址页
	 * 
	 * @param request
	 * @param response
	 * @param status
	 *            1：修改用户默认地址 2：更新订单中收获地址
	 * @param type
	 *            goods 商品订单 service 服务订单
	 * @param orderId
	 *            订单id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/cart/getChooseAddress")
	public String getChooseAddress(HttpServletRequest request, HttpServletResponse response, Integer status,
			String type, Integer orderId, ModelMap modelMap) {

		Member member = (Member) request.getSession().getAttribute("memberSession");
		List<MemberAddress> list = memberAddressService.selectByExample(member.getMemberId());
		modelMap.put("list", list!=null&&list.size()>0?list:null);
		modelMap.put("status", status);
		modelMap.put("type", type);
		modelMap.put("orderId", orderId);
		
		String  param = request.getQueryString();
		if(StringUtils.isBlank(param)){
			String  url =  request.getParameter("url");
			modelMap.put("url", url);
		}else{
			Integer index = param.lastIndexOf("http");
			if(index>0){
				String  url =  param.substring(index);
				modelMap.put("url", url);
			}
		}
		return "choose_address.html";
	}
	


	/**
	 * 进入添加/修改收货地址页
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param id
	 *            地址id
	 */
	@RequestMapping("/member/getAddAddress")
	public String getAddAddress(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Integer memberAddressId,
			Integer status,String type) {
		if (null != memberAddressId) {
			MemberAddress addressDto = memberAddressService.getMemberAddress(memberAddressId);
			if (null != addressDto) {
				modelMap.put("dto", addressDto);
			}
		} else {
			MemberAddress addressDto = new MemberAddress();
			addressDto.setArea("省 > 市 > 区/县");
			modelMap.put("dto", addressDto);
		}
		String  param = request.getQueryString();
		if(StringUtils.isBlank(param)){
			String  url =  request.getParameter("url");
			modelMap.put("url", url);
		}else{
			Integer index = param.lastIndexOf("http");
			if(index>0){
				String  url =  param.substring(index);
				modelMap.put("url", url);
			}
		}
		modelMap.put("type", type);
		modelMap.put("memberAddressId", memberAddressId);
		modelMap.put("status", status);
		return "add_address.html";
	}

	/**
	 * 修改收货人地址
	 * 
	 * @param request
	 * @param response
	 * @param dto
	 */
	@RequestMapping("/member/updateMemberAddress")
	public String updateMemberAddress(HttpServletRequest request, HttpServletResponse response, Integer memberAddressId,
			String recipient, String phoneNum, String area, String address, String postcode,Integer status,String type,String  url) {
		try {
			MemberAddress dto = memberAddressService.getMemberAddress(memberAddressId);
			if (null != dto) {
				dto.setRecipient(recipient);
				dto.setTel(phoneNum);
				dto.setArea(area.replace(" > ", ""));
				dto.setAddress(address);
				dto.setPostcode(postcode);
				memberAddressService.updMemberAddress(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/h5/admin/cart/getChooseAddress?type="+type+"&status="+status+"&url=" + url;
	}

	/**
	 * 添加收货人地址
	 * 
	 * @param request
	 * @param response
	 * @param dto
	 */
	@RequestMapping(value = "/member/addMemberAddress", method = RequestMethod.POST)
	public String addMemberAddress(HttpServletRequest request, HttpServletResponse response, String recipient,
			String phoneNum, String area, String address, String postcode,Integer status,String type,String url) {
		try {
			Member member = (Member) request.getSession().getAttribute("memberSession");
			MemberAddress dto = new MemberAddress();
			dto.setMemberId(member.getMemberId());
			dto.setRecipient(recipient);
			dto.setTel(phoneNum);
			dto.setArea(area.replace(" > ", ""));
			dto.setAddress(dto.getArea()+address);
			dto.setPostcode(postcode);
			dto.setStatus(0);
			memberAddressService.addMemberAddress(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/h5/admin/cart/getChooseAddress?type="+type+"&status="+status+"&url=" + url;
	}

	/**
	 * 根据cid删除某一个收货地址
	 * 
	 * @param request
	 * @param response
	 * @param cid
	 */
	@RequestMapping("/member/deleteMemberAddress")
	@ResponseBody
	public void deleteMemberAddress(HttpServletRequest request, HttpServletResponse response, Integer cid) {
		try {
			Integer result = 0;
			MemberAddress dto = memberAddressService.getMemberAddress(cid);
			if (null != dto) {
				dto.setStatus(1);
				memberAddressService.updMemberAddress(dto);
				result = 1;
			}
			JSONObject json = new JSONObject();
			json.put("result", result);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改我的默认收货地址选项
	 * 
	 * @param request
	 * @param response
	 * @param cid
	 * @param status
	 *            1：修改用户默认地址 2：更新订单中收获地址
	 * @param type
	 *            goods 商品订单 service 服务订单
	 * @param orderId
	 *            订单id
	 */
	@RequestMapping("/member/updateMemberAddressIsdefault")
	@ResponseBody
	public void updateIsdefault(HttpServletRequest request, HttpServletResponse response, Integer cid, Integer status,
			String type, Integer orderId) {
		try {
			Member member = (Member) request.getSession().getAttribute("memberSession");
			MemberAddress record = new MemberAddress();
			record.setIsDefault(0);
			record.setMemberId(member.getMemberId());
			Integer result = memberAddressService.updateByIsDefault(record);
			if (result > 0) {
				MemberAddress dto = memberAddressService.getMemberAddress(cid);
				if (dto != null) {
					dto.setIsDefault(1);
					result = memberAddressService.updMemberAddress(dto);
					if (status == 2) {
						if (type.equals(Constants.GOODS)) {
							ywOrderService.updateAddress(orderId, cid);
						} else {
							workOrderService.updateAddress(orderId, dto.getRecipient(), dto.getTel(), dto.getAddress());
						}
					}
				}
			}
			JSONObject json = new JSONObject();
			json.put("result", result);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商品提交订单方法
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param entrance
	 * @param addressId
	 * @param totalMoney
	 * @param idArray
	 * @param amount
	 */
	@RequestMapping("/goods/saveYwOrder")
	@ResponseBody
	public void saveYwOrder(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			Integer entrance, Integer addressId, Double totalMoney, String idArray, Integer amount) {
		try {
			JSONObject json = new JSONObject();
			Integer result = 0;
			Member member = (Member) request.getSession().getAttribute("memberSession");
			Integer orderId = sysSequenceService.getSequenceValue(Constants.SequenceName.YW_ORDER);
			MemberAddress memberAddress = memberAddressService.getMemberAddress(addressId);
			if (entrance == 2) {
				Goods goods = goodsService.get(Integer.parseInt(idArray));
				YwOrder ywOrder = new YwOrder();
				ywOrder.setOrderId(orderId);
				ywOrder.setMemberId(member.getMemberId());
				ywOrder.setAddressId(memberAddress.getCid());
				ywOrder.setRecipient(memberAddress.getRecipient());
				ywOrder.setAddress(memberAddress.getAddress());
				ywOrder.setPhoneNum(memberAddress.getTel());
				ywOrder.setOrderStatus(Constants.OrderType.ORDER_NOPAY);
				ywOrder.setCreateTime(DateUtil.date2StrLong(new Date()));
				Double totalPay = Arith.round(Arith.mul(goods.getUnitPrice(), amount), 2);
				ywOrder.setOrderAmount(totalPay);
				ywOrder.setCext1(DateUtil.getOrderNo());
				result = ywOrderService.add(ywOrder);
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.ORDER_DETAIL));
				orderDetail.setGoodsId(Integer.parseInt(idArray));
				orderDetail.setOrderId(ywOrder.getOrderId());
				orderDetail.setPrice(goods.getUnitPrice());
				orderDetail.setAmount(amount);
				orderDetail.setCreateTime(DateUtil.date2StrLong(new Date()));
				orderDetail.setTotalPay(totalPay);
				orderDetail.setStatus(Constants.OrderType.ORDER_NOPAY);
				result = orderDetailService.add(orderDetail);
				if (result > 0) {
					json.put("orderNo", ywOrder.getCext1());
				}
			} else {
				YwOrder ywOrder = new YwOrder();
				ywOrder.setOrderId(orderId);
				ywOrder.setMemberId(member.getMemberId());
				ywOrder.setAddressId(addressId);
				ywOrder.setRecipient(memberAddress.getRecipient());
				ywOrder.setAddress(memberAddress.getAddress());
				ywOrder.setPhoneNum(memberAddress.getTel());
				ywOrder.setOrderStatus(Constants.OrderType.ORDER_NOPAY);
				ywOrder.setOrderAmount(totalMoney);
				ywOrder.setCreateTime(DateUtil.date2StrLong(new Date()));
				ywOrder.setCext1(DateUtil.getOrderNo());
				result = ywOrderService.add(ywOrder);
				String[] idSub = idArray.split(",");
				for (int j = 0; j < idSub.length; j++) {
					Cart cartDto = cartService.get(Integer.parseInt(idSub[j]));
					Goods goods = cartDto.getGoods();
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.ORDER_DETAIL));
					orderDetail.setGoodsId(goods.getGoodsId());
					orderDetail.setOrderId(ywOrder.getOrderId());
					orderDetail.setPrice(goods.getUnitPrice());
					orderDetail.setAmount(cartDto.getAmount());
					Double totalPay = Arith.round(Arith.mul(goods.getUnitPrice(), cartDto.getAmount()), 2);
					orderDetail.setTotalPay(totalPay);
					orderDetail.setCreateTime(DateUtil.date2StrLong(new Date()));
					orderDetail.setStatus(Constants.OrderType.ORDER_NOPAY);
					result = orderDetailService.add(orderDetail);
					cartService.del(cartDto.getCid());
				}
				if (result > 0) {
					json.put("orderNo", ywOrder.getCext1());
				}
			}
			json.put("result", result);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 订单查询
	 */
	@RequestMapping("/goods/getPayfor")
	public String getPayfor(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String cext1,
			Integer type) {
		try {
			if (null != cext1) {
				YwOrder dto = ywOrderService.getByCext1(cext1);
				if (null != dto) {
					modelMap.put("totalMoney", dto.getOrderAmount());
					modelMap.put("orderNo", cext1);
					modelMap.put("type", Constants.GOODS);
					if (type == null) {
						modelMap.put("weixinPay", "1");
					}
					modelMap.put("orderId", dto.getOrderId());
					modelMap.put("valid", DateUtil.validTime(dto.getCreateTime()));
					return "payfor.html";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "500.html";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param type
	 *            1代表商品,2代表服务
	 * @param cext1
	 * @param result
	 *            1代表成功,0代表失败
	 * @param status
	 * @return
	 */
	@RequestMapping("/pay/getPaydone")
	public String getPaydone(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Integer type,
			String cext1, Integer result, Integer status) {
		if (result == 1) {
			if (type == 1) {
				YwOrder dto = ywOrderService.getByCext1(cext1);
				if (null != dto) {
					modelMap.put("dto", dto);
					modelMap.put("message", dto.getOrderStatus() == Constants.OrderType.ORDER_HASPAY ? "支付成功" : "待支付");
				}
			} else {
				WorkOrder dto = workOrderService.getByCext1(cext1);
				if (null != dto) {
					modelMap.put("dto", dto);
					modelMap.put("message", dto.getOrderStatus() == Constants.OrderType.ORDER_HASPAY ? "支付成功" : "待支付");
				}
			}
		} else {
			if (result != 2) {
				if (null != status) {
					modelMap.put("message", status == Constants.OrderType.ORDER_CANCER ? "订单已取消" : "订单已支付");
				} else {
					modelMap.put("message", "支付失败");
				}
			} else {
				if (type == 1) {
					YwOrder dto = ywOrderService.getByCext1(cext1);
					modelMap.put("dto", dto);
				} else {
					WorkOrder dto = workOrderService.getByCext1(cext1);
					modelMap.put("dto", dto);
				}
			}
		}
		modelMap.put("result", result);
		modelMap.put("type", type);
		modelMap.put("cext1", cext1);
		return "paydone.html";
	}

	/**
	 * 收藏或取消收藏
	 * 
	 * @param request
	 * @param response
	 * @param refId
	 *            外键ID
	 * @param type
	 *            收藏类型
	 */
	@RequestMapping("/member/doUserCollect")
	@ResponseBody
	public void doUserCollect(HttpServletRequest request, HttpServletResponse response, Integer refId, String type) {
		try {
			Integer result = 0;
			Member member = (Member) request.getSession().getAttribute("memberSession");
			UserCollect dto = userCollectService.getUserCollect(member.getMemberId(), type, refId);
			if (null != dto) {
				result = userCollectService.del(dto.getCid());
			} else {
				dto = new UserCollect();
				dto.setMemberId(member.getMemberId());
				dto.setRefId(refId);
				dto.setCollectType(type);
				dto.setCreateTime(DateUtil.date2StrLong(new Date()));
				result = userCollectService.add(dto);
			}
			JSONObject json = new JSONObject();
			json.put("result", result);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断此订单是否超过30分钟
	 * 
	 * @param request
	 * @param response
	 * @param orderNo
	 *            订单编号
	 * @param type
	 */
	@RequestMapping("/pay/getOrderNoOvertime")
	@ResponseBody
	public void getOrderNoOvertime(HttpServletRequest request, HttpServletResponse response, String orderNo,
			String type) {
		try {
			JSONObject json = new JSONObject();
			Integer result = 0;
			if (type.equals(Constants.GOODS)) {
				YwOrder dto = ywOrderService.getByCext1(orderNo);
				if (dto.getOrderStatus() < 1) {
					long now = new Date().getTime();
					long beg = DateUtils.stringToDate19(dto.getCreateTime()).getTime();
					long num = (now - beg) / 1000 / 60;
					json.put("num", num);
					result = 1;
					// 验证商品是否还存在库存
					List<OrderDetail> list = orderDetailService.getDetailsByOrderId(dto.getOrderId());
					if (null != list && list.size() > 0) {
						for (OrderDetail orderDetail : list) {
							Goods goods = goodsService.get(orderDetail.getGoodsId());
							if (null != goods && goods.getStockAmount() == 0) {
								result = 3;
								json.put("goodsName", goods.getGoodsName());
								break;
							}
						}
					}
				} else {
					result = 2;
				}
			} else {
				WorkOrder dto = workOrderService.getByCext1(orderNo);
				if (dto.getOrderStatus() < 1) {
					long now = new Date().getTime();
					long beg = DateUtils.stringToDate19(dto.getCreateTime()).getTime();
					long num = (now - beg) / 1000 / 60;
					json.put("num", num);
					result = 1;
				} else {
					result = 2;
				}
			}
			json.put("result", result);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/******************************************************* 个人中心 ************************************************/

	/**
	 * 
	 * @Description: 我的订单
	 * @param request
	 * @param orderstatus
	 * @param page
	 * @param modelMap
	 * @return String
	 */
	@RequestMapping(value = "/myOrders/{type}")
	public String myOrders(HttpServletRequest request, Integer orderstatus,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum, @PathVariable String type,
			ModelMap modelMap) {

		Member member = (Member) request.getSession().getAttribute("memberSession");
		if (member == null) {
			return "redirect:/h5/login";
		}
		if (pageNum < 1) {
			pageNum = 1;
		}
		PageHelper.startPage(pageNum, 8);
		List<?> list = null;
		if (type.equals(Constants.GOODS)) {
			list = ywOrderService.selectYwOrderList(member.getMemberId(), orderstatus);
		} else {
			list = workOrderService.selectYwOrderList(member.getMemberId(), orderstatus);
		}
		PageInfo<?> p = new PageInfo<>(list);
		modelMap.put("list", list);
		modelMap.put("type", type);
		modelMap.put("pageNum", p.getPageNum());
		modelMap.put("pages", p.getPages());
		modelMap.put("orderstatus", orderstatus);
		return "myorders.html";
	}

	/**
	 * 
	 * @Description: 订单详情
	 * @param request
	 * @param type
	 * @param cid
	 * @param modelMap
	 * @return String
	 */
	@RequestMapping(value = "/myOrdersInfo/{type}")
	public String myOrdersInfo(HttpServletRequest request, @PathVariable String type, Integer cid, ModelMap modelMap) {

		Member member = (Member) request.getSession().getAttribute("memberSession");
		if (member == null) {
			return "redirect:/h5/login";
		}
		if (type.equals(Constants.GOODS)) {
			YwOrder order = ywOrderService.get(cid);
			modelMap.put("order", order);
			modelMap.put("valid", DateUtil.validTime(order.getCreateTime()));
			modelMap.put("orderNumber", order.getCext1());
		} else {
			WorkOrder order = workOrderService.getOrderInfo(cid);
			if (order.getIext1() != null) {
				ServicePrice price = servicePriceService.findById(order.getIext1());
				modelMap.put("price", price);
			}
			modelMap.put("valid", DateUtil.validTime(order.getCreateTime()));
			modelMap.put("order", order);
			modelMap.put("orderNumber", order.getCext1());
		}
		modelMap.put("type", type);
		modelMap.put("cid", cid);
		return "order_detail.html";
	}

	/**
	 * 
	 * @Description: 订单删除
	 * @param request
	 * @param type
	 * @param cid
	 * @param modelMap
	 * @return String
	 */
	@RequestMapping(value = "/myOrdersDel/{type}")
	@ResponseBody
	public void myOrdersDel(HttpServletRequest request, HttpServletResponse response, @PathVariable String type,
			Integer cid, ModelMap modelMap) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", 0);
		try {
			Member member = (Member) request.getSession().getAttribute("memberSession");
			if (member == null) {
				jsonObj.put("status", 202);
			} else {
				if (type.equals(Constants.GOODS)) {
					ywOrderService.updateStatus(cid, Constants.OrderType.ORDER_CANCER, null);
				} else {
					workOrderService.updateStatus(cid, Constants.OrderType.ORDER_CANCER, null);
				}
				jsonObj.put("status", 1);
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(jsonObj.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Description: 商品收货
	 * @param request
	 * @param cid
	 * @return String
	 */
	@RequestMapping(value = "/myOrdersPeceipt/goods")
	public String myOrdersPeceipt(HttpServletRequest request,Integer cid) {
		Member member = (Member) request.getSession().getAttribute("memberSession");
		if (member == null) {
			return "redirect:/h5/login";
		}
		ywOrderService.updateStatus(cid, Constants.OrderType.ORDER_TODONE, null);
		return "redirect:/h5/admin/myOrders/goods";
	}
	/**
	 * 
	 * @Description: 服务完成
	 * @param request
	 * @param cid
	 * @return String
	 */
	@RequestMapping(value = "/myOrdersComplete/service")
	public String myOrdersComplete(HttpServletRequest request,Integer cid) {
		Member member = (Member) request.getSession().getAttribute("memberSession");
		if (member == null) {
			return "redirect:/h5/login";
		}
		workOrderService.updateStatus(cid, Constants.OrderType.ORDER_DONE,null);
		return "redirect:/h5/admin/myOrders/service";
	}

	/**
	 * 
	 * @Description: 订单评价
	 * @param request
	 * @param type
	 * @param cid
	 * @param modelMap
	 * @return String
	 */
	@RequestMapping(value = "/myOrdersComTo/{type}")
	public String myOrdersComTo(HttpServletRequest request, @PathVariable String type, Integer cid, ModelMap modelMap) {
		Member member = (Member) request.getSession().getAttribute("memberSession");
		if (member == null) {
			return "redirect:/h5/login";
		}
		if (type.equals(Constants.GOODS)) {
			YwOrder order = ywOrderService.get(cid);
			modelMap.put("order", order);
		} else {
			WorkOrder order = workOrderService.getOrderInfo(cid);
			modelMap.put("order", order);
		}
		modelMap.put("type", type);
		modelMap.put("cid", cid);
		return "myOrderCommet.html";
	}

	/**
	 * 
	 * @Description: 订单评价保存
	 * @param request
	 * @param type
	 * @param cid
	 * @param modelMap
	 * @return String
	 */
	@RequestMapping(value = "/myOrdersCom/{type}")
	@ResponseBody
	public void myOrdersCom(HttpServletRequest request, HttpServletResponse response, @PathVariable String type,
			Integer[] cid, Integer orderid, String[] content, Integer[] stars) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", 0);
		try {
			Member member = (Member) request.getSession().getAttribute("memberSession");
			if (member == null) {
				jsonObj.put("status", 202);
			} else {
				userCommentService.saveUserComment(cid, orderid, member.getMemberId(), type, content, stars);
				jsonObj.put("status", 1);
				jsonObj.put("type", type);
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(jsonObj.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Description: 获取退款类型
	 * @param response
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = "/myBackOrder/refundtype")
	public void refundtype(HttpServletResponse response) {
		try {
			JSONArray array = new JSONArray();
			List<SysDictionary> refundtype = sysDictService.selectDictEnName(OrgConst.REFUND_TYPE);
			for (SysDictionary d : refundtype) {
				JSONObject json = new JSONObject();
				json.put("title", d.getDictKey());
				json.put("value", d.getDictValue());
				array.add(json);
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * @Description: 订单退款
	 * @param request
	 * @param type
	 * @param cid
	 * @param modelMap
	 * @return String
	 */
	@RequestMapping(value = "/myBackOrderTo/{type}")
	public String myBackOrderTo(HttpServletRequest request, @PathVariable String type, 
			Integer orderid, Integer cid, ModelMap modelMap) {
		Member member = (Member) request.getSession().getAttribute("memberSession");
		if (member == null) {
			return "redirect:/h5/login";
		}
		if (type.equals(Constants.GOODS)) {
			OrderDetail order = orderDetailService.get(cid);
			modelMap.put("order", order);
		} else {
			WorkOrder order = workOrderService.getOrderInfo(orderid);
			modelMap.put("order", order);
		}
		
		modelMap.put("type", type);
		modelMap.put("orderid", orderid);
		modelMap.put("cid", cid);
		return "myBackOrder.html";
	}

	/**
	 * 
	 * @Description: 订单退款保存
	 * @param request
	 * @param type
	 * @param cid
	 * @param modelMap
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/myBackOrder/{type}")
	public void myBackOrder(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String type, Integer orderid, Integer cid ,String refundtype ,String content) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", 0);
		try {
			Member member = (Member) request.getSession().getAttribute("memberSession");
			if (member == null) {
				jsonObj.put("status", 202);
			} else {
				if(type.equals(Constants.GOODS)){
					ywOrderService.updateRefund(orderid, cid, Constants.OrderType.BACKORDER, refundtype, content);
				}else{
					workOrderService.updateRefund(orderid,Constants.OrderType.BACKORDER, refundtype, content);
				}
				jsonObj.put("status", 1);
				jsonObj.put("type", type);
				response.setContentType("application/json; charset=UTF-8");
				response.getWriter().write(jsonObj.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description: 收藏列表
	 * @param request
	 * @param type
	 * @param pageNum
	 * @param modelMap
	 * @return String
	 */
	@RequestMapping(value = "/myColl/{type}")
	public String collOrder(HttpServletRequest request, @PathVariable String type,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum, ModelMap modelMap) {
		Member member = (Member) request.getSession().getAttribute("memberSession");
		if (member == null) {
			return "redirect:/h5/login";
		}
		if (pageNum < 1) {
			pageNum = 1;
		}
		PageHelper.startPage(pageNum, 8);
		List<UserCollect> list = userCollectService.getUserCollect(member.getMemberId(), type);
		for (UserCollect c : list) {
			if (type.equals(Constants.GOODS)) {
				Goods goods = goodsService.get(c.getRefId());
				c.setGoods(goods);
			} else if (type.equals(Constants.SERVICE)) {
				Service service = serviceService.get(c.getRefId());
				c.setService(service);
			} else {

			}
		}
		PageInfo<UserCollect> p = new PageInfo<UserCollect>(list);
		modelMap.put("list", list);
		modelMap.put("type", type);
		modelMap.put("pageNum", p.getPageNum());
		modelMap.put("pages", p.getPages());
		return "mycollection.html";
	}
}
