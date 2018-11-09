package com.yhcrt.iHealthCloud.controller;

import static com.yhcrt.iHealthCloud.util.ChangeCharsetUtils.ISO_8859_1;
import static com.yhcrt.iHealthCloud.util.ChangeCharsetUtils.UTF_8;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Goods;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.OrderDetail;
import com.yhcrt.iHealthCloud.entity.Service;
import com.yhcrt.iHealthCloud.entity.WorkOrder;
import com.yhcrt.iHealthCloud.entity.YwOrder;
import com.yhcrt.iHealthCloud.service.GoodsService;
import com.yhcrt.iHealthCloud.service.OrderDetailService;
import com.yhcrt.iHealthCloud.service.ServiceService;
import com.yhcrt.iHealthCloud.service.WorkOrderService;
import com.yhcrt.iHealthCloud.service.YwOrderService;
import com.yhcrt.iHealthCloud.util.AlipayConfig;
import com.yhcrt.iHealthCloud.util.ChangeCharsetUtils;
import com.yhcrt.iHealthCloud.util.DateUtil;
import com.yhcrt.iHealthCloud.util.DateUtils;
import com.yhcrt.iHealthCloud.util.FileUtil;
import com.yhcrt.iHealthCloud.util.HttpGetUtil;
import com.yhcrt.iHealthCloud.util.HttpUtils;
import com.yhcrt.iHealthCloud.util.PropertiesUtil;
import com.yhcrt.iHealthCloud.util.XmlUtil;
import com.yhcrt.iHealthCloud.util.wxpay.CollectionUtil;
import com.yhcrt.iHealthCloud.util.wxpay.HttpXmlUtils;
import com.yhcrt.iHealthCloud.util.wxpay.PayUtil;
import com.yhcrt.iHealthCloud.util.wxpay.WXSignUtils;
import com.yhcrt.iHealthCloud.util.wxpay.WeixinConfigUtils;
import com.yhcrt.iHealthCloud.util.wxpay.WeixinJSBridge;

@Controller
@RequestMapping("/h5/pay")
public class PayController {
	private static final Logger LOG = Logger.getLogger(PayController.class);

	private static final String ORDER_PAY = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 统一下单

	private static final String ORDER_PAY_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery"; // 支付订单查询

	// 微信H5支付商品回调方法
	public static final String WEIXIN_H5YWPAY = "https://healthcloud.ejyhealth.com/api/h5/pay/api/weixinH5YwNotify";
	// 微信公众号支付商品回调方法
	public static final String WEIXIN_JSBRIDGE_YWPAY = "https://healthcloud.ejyhealth.com/api/h5/pay/api/weixinJsbridgeYwNotify";

	// 微信H5支付服务预约回调方法
	public static final String WEIXIN_H5WORKPAY = "https://healthcloud.ejyhealth.com/api/h5/pay/api/weixinH5WorkNotify";

	// 微信公众号支付服务预约回调方法
	public static final String WEIXIN_JSBRIDGE_WORKPAY = "https://healthcloud.ejyhealth.com/api/h5/pay/api/weixinJsbridgeWorkNotify";

	@Autowired
	private YwOrderService ywOderService;

	@Autowired
	private WorkOrderService workOrderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private ServiceService serviceService;

	/**
	 * @Description: 支付宝h5支付商品-手机端
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/aliApi/ywOrderPay" })
	public void ywOrderPay(HttpServletRequest request, HttpServletResponse response, String cext1) {
		try {
			// 获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.seller_id,
					AlipayConfig.merchant_private_key, "json", AlipayConfig.input_charset,
					AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();
			// 设置异步通知地址
			alipay_request.setNotifyUrl(PropertiesUtil.getProperty("/config.properties", "alipay.notify.url"));
			// 设置同步地址
			alipay_request.setReturnUrl(PropertiesUtil.getProperty("/config.properties", "alipay.return.url"));
			YwOrder ywOrder = ywOderService.getByCext1(cext1);
			if (null != ywOrder) {
				if (ywOrder.getOrderStatus() != Constants.OrderType.ORDER_NOPAY) {
					response.sendRedirect(request.getServletContext().getContextPath()
							+ "/h5/admin/pay/getPaydone?type=1&result=0&cext1=" + cext1 + "&status="
							+ ywOrder.getOrderStatus());
				} else {
					// 商户订单号，商户网站订单系统中唯一订单号，必填
					String out_trade_no = ChangeCharsetUtils.changeCharset(ywOrder.getCext1(), ISO_8859_1, UTF_8);
					// 付款金额，必填（保留两位小数）
					String total_amount = ChangeCharsetUtils.changeCharset("" + ywOrder.getOrderAmount(), ISO_8859_1,
							UTF_8); // 付款金额，必填
					// 订单名称，必填
					String subject = "商品";
					// 商品描述，可空
					String body = "商品";
					// 超时时间 可空
					String timeout_express = "2m";
					// 销售产品码 必填
					String product_code = "QUICK_WAP_WAY";
					// 封装请求支付信息
					AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
					model.setOutTradeNo(out_trade_no);
					model.setSubject(subject);
					model.setTotalAmount(total_amount);
					model.setBody(body);
					model.setTimeoutExpress(timeout_express);
					model.setProductCode(product_code);
					alipay_request.setBizModel(model);
					String form = alipayClient.pageExecute(alipay_request).getBody();
					response.setContentType("text/html;charset=" + ChangeCharsetUtils.UTF_8);// 防止数据传递乱码
					response.getWriter().write(form);// 直接将完整的表单html输出到页面
					response.getWriter().flush();
					response.getWriter().close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 支付宝h5支付商品-web端
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/aliApi/webYwOrderPay" })
	public void webYwOrderPay(HttpServletRequest request, HttpServletResponse response, String cext1) {
		try {
			// 获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.seller_id,
					AlipayConfig.merchant_private_key, "json", AlipayConfig.input_charset,
					AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(PropertiesUtil.getProperty("/config.properties", "alipay.return.url"));
			alipayRequest.setNotifyUrl(PropertiesUtil.getProperty("/config.properties", "alipay.notify.url"));
			YwOrder ywOrder = ywOderService.getByCext1(cext1);
			if (null != ywOrder) {
				if (ywOrder.getOrderStatus() != Constants.OrderType.ORDER_NOPAY) {
					response.sendRedirect(request.getServletContext().getContextPath()
							+ "/h5/admin/pay/getPaydone?type=1&result=0&cext1=" + cext1 + "&status="
							+ ywOrder.getOrderStatus());
				} else {
					// 商户订单号，商户网站订单系统中唯一订单号，必填
					String out_trade_no = ChangeCharsetUtils.changeCharset(ywOrder.getCext1(), ISO_8859_1, UTF_8);
					// 付款金额，必填（保留两位小数）
					String total_amount = ChangeCharsetUtils.changeCharset("" + ywOrder.getOrderAmount(), ISO_8859_1,
							UTF_8); // 付款金额，必填
					// 订单名称，必填
					String subject = "商品";
					// 商品描述，可空
					String body = "商品";
					alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
							+ total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
							+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
					// 请求
					String form = alipayClient.pageExecute(alipayRequest).getBody();// 调用SDK生成表单
					response.setContentType("text/html;charset=" + ChangeCharsetUtils.UTF_8);// 防止数据传递乱码
					response.getWriter().write(form);// 直接将完整的表单html输出到页面
					response.getWriter().flush();
					response.getWriter().close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 支付宝h5支付服务预约-手机端
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/aliApi/workOrderPay" })
	public void workOrderPay(HttpServletRequest request, HttpServletResponse response, String cext1) {
		try {
			// 获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.seller_id,
					AlipayConfig.merchant_private_key, "json", AlipayConfig.input_charset,
					AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();
			// 设置异步通知地址
			alipay_request.setNotifyUrl(PropertiesUtil.getProperty("/config.properties", "alipay.notify.url"));
			// 设置同步地址
			alipay_request.setReturnUrl(PropertiesUtil.getProperty("/config.properties", "alipay.return.url"));
			WorkOrder workOrder = workOrderService.getByCext1(cext1);
			if (null != workOrder) {
				if (workOrder.getOrderStatus() != Constants.OrderType.ORDER_NOPAY) {
					response.sendRedirect(request.getServletContext().getContextPath()
							+ "/h5/admin/pay/getPaydone?type=1&result=0&cext1=" + cext1 + "&status="
							+ workOrder.getOrderStatus());
				} else {
					// 商户订单号，商户网站订单系统中唯一订单号，必填
					String out_trade_no = ChangeCharsetUtils.changeCharset(workOrder.getCext1(), ISO_8859_1, UTF_8);
					// 付款金额，必填（保留两位小数）
					String total_amount = ChangeCharsetUtils.changeCharset("" + workOrder.getUnitPrice(), ISO_8859_1,
							UTF_8); // 付款金额，必填
					// 订单名称，必填
					String subject = "服务预约";
					// 商品描述，可空
					String body = "服务预约";
					// 超时时间 可空
					String timeout_express = "2m";
					// 销售产品码 必填
					String product_code = "QUICK_WAP_WAY";
					// 封装请求支付信息
					AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
					model.setOutTradeNo(out_trade_no);
					model.setSubject(subject);
					model.setTotalAmount(total_amount);
					model.setBody(body);
					model.setTimeoutExpress(timeout_express);
					model.setProductCode(product_code);
					alipay_request.setBizModel(model);
					String form = alipayClient.pageExecute(alipay_request).getBody();
					response.setContentType("text/html;charset=" + ChangeCharsetUtils.UTF_8);// 防止数据传递乱码
					response.getWriter().write(form);// 直接将完整的表单html输出到页面
					response.getWriter().flush();
					response.getWriter().close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 支付宝h5支付服务预约-web端
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/aliApi/webWorkOrderPay" })
	public void webWorkOrderPay(HttpServletRequest request, HttpServletResponse response, String cext1) {
		try {
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.seller_id,
					AlipayConfig.merchant_private_key, "json", AlipayConfig.input_charset,
					AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(PropertiesUtil.getProperty("/config.properties", "alipay.return.url"));
			alipayRequest.setNotifyUrl(PropertiesUtil.getProperty("/config.properties", "alipay.notify.url"));
			WorkOrder workOrder = workOrderService.getByCext1(cext1);
			if (null != workOrder) {
				if (workOrder.getOrderStatus() != Constants.OrderType.ORDER_NOPAY) {
					response.sendRedirect(request.getServletContext().getContextPath()
							+ "/h5/admin/pay/getPaydone?type=1&result=0&cext1=" + cext1 + "&status="
							+ workOrder.getOrderStatus());
				} else {
					// 商户订单号，商户网站订单系统中唯一订单号，必填
					String out_trade_no = ChangeCharsetUtils.changeCharset(workOrder.getCext1(), ISO_8859_1, UTF_8);
					// 付款金额，必填（保留两位小数）
					String total_amount = ChangeCharsetUtils.changeCharset("" + workOrder.getUnitPrice(), ISO_8859_1,
							UTF_8); // 付款金额，必填
					// 订单名称，必填
					String subject = "服务预约";
					// 商品描述，可空
					String body = "服务预约";
					alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
							+ total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
							+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
					// 请求
					String form = alipayClient.pageExecute(alipayRequest).getBody();// 调用SDK生成表单
					response.setContentType("text/html;charset=" + ChangeCharsetUtils.UTF_8);// 防止数据传递乱码
					response.getWriter().write(form);// 直接将完整的表单html输出到页面
					response.getWriter().flush();
					response.getWriter().close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 支付宝 商户系统请求支付宝接口alipay.trade.page.pay，支付宝对商户请求参数进行校验，而后重定向至用户登录页面。
	 * 用户确认支付后，支付宝get请求returnUrl（商户入参传入），返回同步返回参数。
	 * 交易成功后，支付宝post请求notifyUrl（商户入参传入），返回异步通知参数。
	 * 若由于网络等问题异步通知没有到达，商户可自行调用alipay.trade.query接口进行查询，根据查询接口获取交易以及支付信息（
	 * 商户也可以直接调用查询接口，不需要依赖异步通知）。
	 * 
	 * 注意：由于同步返回的不可靠性，支付结果必须以异步通知或查询接口返回为准，不能依赖同步跳转。
	 * 
	 * @Description: 支付接口（alipay.trade.page.pay）
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/payWork" })
	public void payWork(HttpServletRequest request, HttpServletResponse response, String cext1) {
		try {
			// 获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.seller_id,
					AlipayConfig.merchant_private_key, "json", AlipayConfig.input_charset,
					AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

			// 设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(PropertiesUtil.getProperty("/config.properties", "alipay.return.url"));
			alipayRequest.setNotifyUrl(PropertiesUtil.getProperty("/config.properties", "alipay.notify.url"));

			WorkOrder workOrder = workOrderService.getByCext1(cext1);
			// 商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = ChangeCharsetUtils.changeCharset(workOrder.getCext1(), ISO_8859_1, UTF_8);
			// 付款金额，必填（保留两位小数）
			String total_amount = ChangeCharsetUtils.changeCharset("" + workOrder.getServiceFee(), ISO_8859_1, UTF_8); // 付款金额，必填
			// 订单名称，必填
			String subject = workOrder.getService().getServiceName();
			// 商品描述，可空
			String body = workOrder.getService().getServiceIntro();
			// 填充业务参数
			alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
					+ total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

			// 请求
			String form = alipayClient.pageExecute(alipayRequest).getBody();// 调用SDK生成表单

			response.setContentType("text/html;charset=" + ChangeCharsetUtils.UTF_8);// 防止数据传递乱码
			response.getWriter().write(form);// 直接将完整的表单html输出到页面
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 支付宝-同步通知
	 * 
	 * @Description: 支付结果必须以异步通知或查询接口返回为准，不能依赖同步跳转即不能依靠该跳转
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/return_url" }, method = RequestMethod.GET)
	public void returnUrl(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("==============支付宝-同步通知");
			Integer type = 1;// 1 代表商品，2代表服务预约
			Integer result = 0;// 1代表成功，0代表失败
			// 获取支付宝GET过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = ChangeCharsetUtils.changeCharset(valueStr, ISO_8859_1, UTF_8);
				params.put(name, valueStr);
			}

			// 调用SDK验证签名
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
					AlipayConfig.input_charset, AlipayConfig.sign_type);
			if (!signVerified) {
				System.out.println("return_url 验证失败");
			}
			Enumeration<String> enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String paraName = (String) enu.nextElement();
				System.out.println(paraName + ": " + request.getParameter(paraName));
			}

			// 商户订单号
			String out_trade_no = ChangeCharsetUtils.changeCharset(request.getParameter("out_trade_no"), ISO_8859_1,
					UTF_8);
			// 支付宝交易号
			String trade_no = ChangeCharsetUtils.changeCharset(request.getParameter("trade_no"), ISO_8859_1, UTF_8);
			// 付款金额
			String total_amount = ChangeCharsetUtils.changeCharset(request.getParameter("total_amount"), ISO_8859_1,
					UTF_8);

			request.setAttribute("reason",
					"trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount);
			if (signVerified) {
				System.out.println("交易成功-------------------");
				YwOrder ywOrder = ywOderService.getByCext1(out_trade_no);
				if (null != ywOrder) {
					ywOrder.setActualPayment(Double.parseDouble(total_amount));
					ywOrder.setPayValue(Double.parseDouble(total_amount));
					ywOrder.setPayAccount(trade_no);
					ywOrder.setPayType("alipay_h5");
					ywOrder.setPayTime(DateUtil.date2StrLong(new Date()));
					ywOrder.setOrderStatus(Constants.OrderType.ORDER_HASPAY);
					ywOrder.setCext2(out_trade_no);
					ywOderService.upd(ywOrder);
					List<OrderDetail> orderDetails = orderDetailService.getDetailsByOrderId(ywOrder.getOrderId());
					if (null != orderDetails && orderDetails.size() > 0) {
						for (OrderDetail orderDetail : orderDetails) {
							orderDetail.setStatus(Constants.OrderType.ORDER_HASPAY);
							orderDetail.setActualPayment(orderDetail.getTotalPay());
							orderDetailService.upd(orderDetail);
							Goods goodsDto = orderDetail.getGoods();
							if (null != goodsDto) {
								Integer num = (goodsDto.getIext2()==null?0:goodsDto.getIext2()) + orderDetail.getAmount();;
								goodsDto.setIext2(num);
								goodsDto.setUpdateTime(ywOrder.getPayTime());
								goodsDto.setStockAmount(goodsDto.getStockAmount() - 1);
								goodsService.upd(goodsDto);
							}
						}
						result = 1;
					}
				} else {
					WorkOrder workOrder = workOrderService.getByCext1(out_trade_no);
					if (workOrder != null) {
						workOrder.setActualPayment(Double.parseDouble(total_amount));
						workOrder.setPayAccount(trade_no);
						workOrder.setPayType("alipay_h5");
						workOrder.setPayTime(DateUtil.date2StrLong(new Date()));
						workOrder.setPayValue(Double.parseDouble(total_amount));
						workOrder.setOrderStatus(Constants.OrderType.ORDER_HASPAY);
						workOrder.setCext2(out_trade_no);
						workOrderService.upd(workOrder);
						
						//修改服务订购数量
						Service service = serviceService.get(workOrder.getServiceId());
						int num = (service.getIext2()==null? 0:service.getIext2()) + workOrder.getAmount();
						service.setIext2(num);
						service.setUpdateTime(workOrder.getPayTime());
						serviceService.upd(service);
						result = 1;
						type = 2;
					}
				}
			}
			response.sendRedirect(request.getServletContext().getContextPath() + "/h5/admin/pay/getPaydone?type=" + type
					+ "&result=" + result + "&cext1=" + out_trade_no);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 支付宝-异步通知
	 * 
	 * @Description: 该方式的作用主要防止订单丢失，即页面跳转同步通知没有处理订单更新，它则去处理；
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/notify_url" }, method = RequestMethod.POST)
	public void notifyUrl(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("==============支付宝-异步通知");
		Integer type = 1;// 1 代表商品，2代表服务预约
		Integer result = 0;// 1代表成功，0代表失败
		try {
			// 获取支付宝GET过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = ChangeCharsetUtils.changeCharset(valueStr, ISO_8859_1, UTF_8);
				params.put(name, valueStr);
			}

			// 调用SDK验证签名
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
					AlipayConfig.input_charset, AlipayConfig.sign_type);
			if (!signVerified) {
				System.out.println("验签失败-------------------");
			}
			// 交易状态
			String trade_status = ChangeCharsetUtils.changeCharset(request.getParameter("trade_status"), ISO_8859_1,
					UTF_8);
			System.out.println("交易状态=======" + trade_status);
			if (!trade_status.equals("TRADE_FINISHED") && !trade_status.equals("TRADE_SUCCESS")) {
				System.out.println("交易失败-------------------");
				signVerified = false;
			}

			// 商户订单号
			String out_trade_no = ChangeCharsetUtils.changeCharset(request.getParameter("out_trade_no"), ISO_8859_1,
					UTF_8);
			// 支付宝交易号
			String trade_no = ChangeCharsetUtils.changeCharset(request.getParameter("trade_no"), ISO_8859_1, UTF_8);
			// 付款金额
			String total_amount = ChangeCharsetUtils.changeCharset(request.getParameter("total_amount"), ISO_8859_1,
					UTF_8);

			Enumeration<String> enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String paraName = (String) enu.nextElement();
				System.out.println(paraName + ": " + request.getParameter(paraName));
			}
			if (signVerified) {
				System.out.println("交易成功-------------------");
				YwOrder ywOrder = ywOderService.getByCext1(out_trade_no);
				if (null != ywOrder) {
					ywOrder.setActualPayment(Double.parseDouble(total_amount));
					ywOrder.setPayValue(Double.parseDouble(total_amount));
					ywOrder.setPayAccount(trade_no);
					ywOrder.setPayType("alipay_h5");
					ywOrder.setPayTime(DateUtil.date2StrLong(new Date()));
					ywOrder.setOrderStatus(Constants.OrderType.ORDER_HASPAY);
					ywOrder.setCext2(out_trade_no);
					ywOderService.upd(ywOrder);
					List<OrderDetail> orderDetails = orderDetailService.getDetailsByOrderId(ywOrder.getOrderId());
					if (null != orderDetails && orderDetails.size() > 0) {
						for (OrderDetail orderDetail : orderDetails) {
							orderDetail.setStatus(Constants.OrderType.ORDER_HASPAY);
							orderDetail.setActualPayment(orderDetail.getTotalPay());
							orderDetailService.upd(orderDetail);
							Goods goodsDto = orderDetail.getGoods();
							if (null != goodsDto) {
								goodsDto.setStockAmount(goodsDto.getStockAmount() - 1);
								goodsService.upd(goodsDto);
							}
						}
						result = 1;
					}
				} else {
					WorkOrder workOrder = workOrderService.getByCext1(out_trade_no);
					if (null != workOrder) {
						workOrder.setActualPayment(Double.parseDouble(total_amount));
						workOrder.setPayAccount(trade_no);
						workOrder.setPayType("alipay_h5");
						workOrder.setPayTime(DateUtil.date2StrLong(new Date()));
						workOrder.setPayValue(Double.parseDouble(total_amount));
						workOrder.setOrderStatus(Constants.OrderType.ORDER_HASPAY);
						workOrder.setCext2(out_trade_no);
						workOrderService.upd(workOrder);
						result = 1;
						type = 2;
					}
				}
			}
			response.sendRedirect(request.getServletContext().getContextPath() + "/h5/admin/pay/getPaydone?type=" + type
					+ "&result=" + result + "&cext1=" + out_trade_no);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 微信支付商品
	@ResponseBody
	@RequestMapping(value = { "/wx/ywOrderWxPay" })
	public void ywOrderWxPay(HttpServletRequest request, HttpServletResponse response, String cext1) {
		try {
			Map<String, String> restmap = null;
			YwOrder ywOrder = ywOderService.getByCext1(cext1);
			if (null != ywOrder) {
				if (ywOrder.getOrderStatus() != Constants.OrderType.ORDER_NOPAY) {
					response.sendRedirect(request.getServletContext().getContextPath()
							+ "/h5/admin/pay/getPaydone?type=1&result=0&cext1=" + cext1 + "&status="
							+ ywOrder.getOrderStatus());
				} else {
					try {
						String total_fee = new BigDecimal(ywOrder.getOrderAmount()).multiply(BigDecimal.valueOf(100))
								.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
						SortedMap<Object, Object> parm = new TreeMap<Object, Object>();

						parm.put("appid", WeixinConfigUtils.appid);
						parm.put("mch_id", WeixinConfigUtils.mch_id);
						parm.put("body", "商品购买");
						parm.put("nonce_str", PayUtil.getNonceStr());
						parm.put("device_info", "WEB");
						parm.put("out_trade_no", cext1);
						parm.put("total_fee", total_fee);
						parm.put("spbill_create_ip", getIpAddress(request));
						parm.put("notify_url", WEIXIN_H5YWPAY); // 微信服务器异步通知支付结果地址
																// 下面的回调方法
						parm.put("trade_type", "MWEB");
						String redirect_url = URLEncoder.encode(
								"https://healthcloud.ejyhealth.com/api/h5/admin/goods/getPayfor?cext1=" + cext1,
								"UTF-8");
						String sign = WXSignUtils.createSign("UTF-8", parm, WeixinConfigUtils.key);
						parm.put("sign", sign);

						// 生成Xml格式的字符串
						String requestXml = HttpXmlUtils.getRequestXml(parm);
						String method = "POST";
						String restxml = HttpXmlUtils.httpsRequest(ORDER_PAY, method, requestXml).toString();
						restmap = XmlUtil.xmlParse(restxml);
						restmap.put("mweb_url", restmap.get("mweb_url") + "&redirect_url=" + redirect_url);
						JSONObject json = new JSONObject();
						json.put("map", restmap);
						response.setContentType("application/json; charset=UTF-8");
						response.getWriter().write(json.toString());
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 微信公众号支付商品
	@ResponseBody
	@RequestMapping(value = { "/wx/ywOrderWeixinJSBridgePay" })
	public void ywOrderWeixinJSBridgePay(HttpServletRequest request, HttpServletResponse response, String cext1,
			String openid) {
		SortedMap<Object, Object> sortMap = new TreeMap<Object, Object>();
		try {
			JSONObject json = new JSONObject();
			Map<String, String> restmap = null;
			YwOrder ywOrder = ywOderService.getByCext1(cext1);
			if (null != ywOrder) {
				if (ywOrder.getOrderStatus() != Constants.OrderType.ORDER_NOPAY) {
					response.sendRedirect(request.getServletContext().getContextPath()
							+ "/h5/admin/pay/getPaydone?type=1&result=0&cext1=" + cext1 + "&status="
							+ ywOrder.getOrderStatus());
				} else {
					try {
						Member member = (Member) request.getSession().getAttribute("memberSession");
						String total_fee = new BigDecimal(ywOrder.getOrderAmount()).multiply(BigDecimal.valueOf(100))
								.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
						SortedMap<Object, Object> parm = new TreeMap<Object, Object>();

						parm.put("appid", WeixinJSBridge.appid);
						parm.put("body", "商品购买");
						parm.put("mch_id", WeixinJSBridge.mch_id);
						parm.put("nonce_str", PayUtil.getNonceStr());
						parm.put("notify_url", WEIXIN_JSBRIDGE_YWPAY); // 微信服务器异步通知支付结果地址
						parm.put("openid", member.getWxGzhOpenId());
						parm.put("out_trade_no", cext1);
						parm.put("spbill_create_ip", getIpAddress(request));
						parm.put("total_fee", total_fee);
						parm.put("trade_type", "JSAPI");
						String sign = WXSignUtils.createSign("UTF-8", parm, WeixinJSBridge.key);
						parm.put("sign", sign);

						// 生成Xml格式的字符串
						String requestXml = HttpXmlUtils.getRequestXml(parm);
						String method = "POST";
						String restxml = HttpXmlUtils.httpsRequest(ORDER_PAY, method, requestXml).toString();
						restmap = XmlUtil.xmlParse(restxml);
						if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
							sortMap.put("appId", WeixinJSBridge.appid);
							String timeStamp = PayUtil.payTimestamp();
							sortMap.put("timeStamp", timeStamp);
							sortMap.put("nonceStr", restmap.get("nonce_str"));
							sortMap.put("package", "prepay_id=" + restmap.get("prepay_id"));
							sortMap.put("signType", "MD5");
							sortMap.put("paySign", WXSignUtils.creSmallSign("UTF-8", sortMap, WeixinJSBridge.key));
							json.put("appId", WeixinJSBridge.appid);
							json.put("timeStamp", PayUtil.payTimestamp());
							json.put("nonceStr", restmap.get("nonce_str"));
							json.put("package", "prepay_id=" + restmap.get("prepay_id"));
							json.put("signType", "MD5");
							json.put("paySign", sortMap.get("paySign"));
						}
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 微信H5支付商品回调方法
	@RequestMapping(value = { "/api/weixinH5YwNotify" })
	public void weixinH5Notify(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		try {
			ServletInputStream in = request.getInputStream();
			String resxml = FileUtil.readInputStream2String(in);
			Map<String, String> restmap = XmlUtil.xmlParse(resxml);
			LOG.info("支付结果通知：" + restmap);
			System.out.println("支付结果通知===：" + restmap);
			if ("SUCCESS".equals(restmap.get("return_code"))) {
				// 订单支付成功 业务处理
				String out_trade_no = restmap.get("out_trade_no"); // 商户订单号
				// 通过商户订单判断是否该订单已经处理 如果处理跳过 如果未处理先校验sign签名 再进行订单业务相关的处理
				String transaction_id = restmap.get("transaction_id");// 微信订单号
				LOG.info("订单支付通知： 支付成功，订单号" + out_trade_no);
				YwOrder yworder = ywOderService.getByCext1(out_trade_no);
				if (yworder != null && yworder.getOrderStatus() == Constants.OrderType.ORDER_NOPAY) {
					// 进行业务处理
					yworder.setOrderStatus(Constants.OrderType.ORDER_HASPAY);
					yworder.setPayValue(yworder.getOrderAmount());
					yworder.setPayType("wechat_h5");
					yworder.setActualPayment(yworder.getOrderAmount());
					yworder.setPayTime(DateUtils.dateToString19(new Date()));
					yworder.setCext2(out_trade_no);
					yworder.setPayAccount(transaction_id);
					ywOderService.upd(yworder);
					List<OrderDetail> orderDetails = orderDetailService.getDetailsByOrderId(yworder.getOrderId());
					if (null != orderDetails && orderDetails.size() > 0) {
						for (OrderDetail orderDetail : orderDetails) {
							orderDetail.setStatus(Constants.OrderType.ORDER_HASPAY);
							orderDetail.setActualPayment(orderDetail.getTotalPay());
							orderDetailService.upd(orderDetail);
							Goods goodsDto = orderDetail.getGoods();
							if (null != goodsDto) {
								int num = (goodsDto.getIext2()==null? 0:goodsDto.getIext2()) + orderDetail.getAmount();
								goodsDto.setIext2(num);
								goodsDto.setUpdateTime(yworder.getPayTime());
								goodsDto.setStockAmount(goodsDto.getStockAmount() - 1);
								goodsService.upd(goodsDto);
							}
						}
					}
				}
			} else {
				LOG.info("订单支付通知：支付失败，" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	// 微信公众号支付商品回调方法
	@RequestMapping(value = { "/api/weixinJsbridgeYwNotify" })
	public void weixinJsbridgeYwNotify(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		try {
			ServletInputStream in = request.getInputStream();
			String resxml = FileUtil.readInputStream2String(in);
			Map<String, String> restmap = XmlUtil.xmlParse(resxml);
			LOG.info("支付结果通知：" + restmap);
			System.out.println("支付结果通知===：" + restmap);
			if ("SUCCESS".equals(restmap.get("return_code"))) {
				// 订单支付成功 业务处理
				String out_trade_no = restmap.get("out_trade_no"); // 商户订单号
				// 通过商户订单判断是否该订单已经处理 如果处理跳过 如果未处理先校验sign签名 再进行订单业务相关的处理
				String transaction_id = restmap.get("transaction_id");// 微信订单号
				YwOrder yworder = ywOderService.getByCext1(out_trade_no);
				if (yworder != null && yworder.getOrderStatus() == Constants.OrderType.ORDER_NOPAY) {
					// 进行业务处理
					yworder.setOrderStatus(Constants.OrderType.ORDER_HASPAY);
					yworder.setPayValue(yworder.getOrderAmount());
					yworder.setPayType("wechat_gzh");
					yworder.setActualPayment(yworder.getOrderAmount());
					yworder.setPayTime(DateUtils.dateToString19(new Date()));
					yworder.setCext2(out_trade_no);
					yworder.setPayAccount(transaction_id);
					ywOderService.upd(yworder);
					List<OrderDetail> orderDetails = orderDetailService.getDetailsByOrderId(yworder.getOrderId());
					if (null != orderDetails && orderDetails.size() > 0) {
						for (OrderDetail orderDetail : orderDetails) {
							orderDetail.setStatus(Constants.OrderType.ORDER_HASPAY);
							orderDetail.setActualPayment(orderDetail.getTotalPay());
							orderDetailService.upd(orderDetail);
							Goods goodsDto = orderDetail.getGoods();
							if (null != goodsDto) {
								int num = (goodsDto.getIext2()==null? 0:goodsDto.getIext2()) + orderDetail.getAmount();
								goodsDto.setIext2(num);
								goodsDto.setUpdateTime(yworder.getPayTime());
								goodsDto.setStockAmount(goodsDto.getStockAmount() - 1);
								goodsService.upd(goodsDto);
							}
						}
					}
				}
			} else {
				LOG.info("订单支付通知：支付失败，" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	// 微信支付服务预约
	@ResponseBody
	@RequestMapping(value = { "/wx/workOrderWxPay" })
	public void workOrderWxPay(HttpServletRequest request, HttpServletResponse response, String cext1) {
		try {
			Map<String, String> restmap = null;
			WorkOrder workOrder = workOrderService.getByCext1(cext1);
			if (null != workOrder) {
				if (workOrder.getOrderStatus() != Constants.OrderType.ORDER_NOPAY) {
					response.sendRedirect(request.getServletContext().getContextPath()
							+ "/h5/admin/pay/getPaydone?type=1&result=0&cext1=" + cext1 + "&status="
							+ workOrder.getOrderStatus());
				} else {
					try {
						String total_fee = new BigDecimal(workOrder.getUnitPrice()).multiply(BigDecimal.valueOf(100))
								.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
						SortedMap<Object, Object> parm = new TreeMap<Object, Object>();

						parm.put("appid", WeixinConfigUtils.appid);
						parm.put("mch_id", WeixinConfigUtils.mch_id);
						parm.put("body", "服务预约购买");
						parm.put("nonce_str", PayUtil.getNonceStr());
						parm.put("device_info", "WEB");
						parm.put("out_trade_no", cext1);
						parm.put("total_fee", total_fee);
						parm.put("spbill_create_ip", getIpAddress(request));
						parm.put("notify_url", WEIXIN_H5WORKPAY); // 微信服务器异步通知支付结果地址
																	// 下面的回调方法
						parm.put("trade_type", "MWEB");
						String redirect_url = URLEncoder.encode(
								"https://healthcloud.ejyhealth.com/api/h5/admin/service/getPayfor?cext1=" + cext1,
								"UTF-8");
						String sign = WXSignUtils.createSign("UTF-8", parm, WeixinConfigUtils.key);
						parm.put("sign", sign);

						// 生成Xml格式的字符串
						String requestXml = HttpXmlUtils.getRequestXml(parm);
						String method = "POST";
						String restxml = HttpXmlUtils.httpsRequest(ORDER_PAY, method, requestXml).toString();
						restmap = XmlUtil.xmlParse(restxml);
						restmap.put("mweb_url", restmap.get("mweb_url") + "&redirect_url=" + redirect_url);
						JSONObject json = new JSONObject();
						json.put("map", restmap);
						response.setContentType("application/json; charset=UTF-8");
						response.getWriter().write(json.toString());
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 微信公众号支付服务预约
	@ResponseBody
	@RequestMapping(value = { "/wx/wxWorkOrderWeixinJSBridge" })
	public void wxWorkOrderWeixinJSBridge(HttpServletRequest request, HttpServletResponse response, String cext1) {
		try {
			Map<String, String> restmap = null;
			SortedMap<Object, Object> sortMap = new TreeMap<Object, Object>();
			JSONObject json = new JSONObject();
			WorkOrder workOrder = workOrderService.getByCext1(cext1);
			if (null != workOrder) {
				if (workOrder.getOrderStatus() != Constants.OrderType.ORDER_NOPAY) {
					response.sendRedirect(request.getServletContext().getContextPath()
							+ "/h5/admin/pay/getPaydone?type=1&result=0&cext1=" + cext1 + "&status="
							+ workOrder.getOrderStatus());
				} else {
					try {
						Member member = (Member) request.getSession().getAttribute("memberSession");
						String total_fee = new BigDecimal(workOrder.getUnitPrice()).multiply(BigDecimal.valueOf(100))
								.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
						SortedMap<Object, Object> parm = new TreeMap<Object, Object>();
						parm.put("appid", WeixinJSBridge.appid);
						parm.put("body", "服务预约购买");
						parm.put("mch_id", WeixinJSBridge.mch_id);
						parm.put("nonce_str", PayUtil.getNonceStr());
						parm.put("notify_url", WEIXIN_JSBRIDGE_WORKPAY); // 微信服务器异步通知支付结果地址
						parm.put("openid", member.getWxGzhOpenId());
						parm.put("out_trade_no", cext1);
						parm.put("spbill_create_ip", getIpAddress(request));
						parm.put("total_fee", total_fee);
						parm.put("trade_type", "JSAPI");
						String sign = WXSignUtils.createSign("UTF-8", parm, WeixinJSBridge.key);
						parm.put("sign", sign);
						// 生成Xml格式的字符串
						String requestXml = HttpXmlUtils.getRequestXml(parm);
						String method = "POST";
						String restxml = HttpXmlUtils.httpsRequest(ORDER_PAY, method, requestXml).toString();
						restmap = XmlUtil.xmlParse(restxml);
						if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
							sortMap.put("appId", WeixinJSBridge.appid);
							String timeStamp = PayUtil.payTimestamp();
							sortMap.put("timeStamp", timeStamp);
							sortMap.put("nonceStr", restmap.get("nonce_str"));
							sortMap.put("package", "prepay_id=" + restmap.get("prepay_id"));
							sortMap.put("signType", "MD5");
							sortMap.put("paySign", WXSignUtils.creSmallSign("UTF-8", sortMap, WeixinJSBridge.key));
							json.put("appId", WeixinJSBridge.appid);
							json.put("timeStamp", PayUtil.payTimestamp());
							json.put("nonceStr", restmap.get("nonce_str"));
							json.put("package", "prepay_id=" + restmap.get("prepay_id"));
							json.put("signType", "MD5");
							json.put("paySign", sortMap.get("paySign"));
						}
						response.setContentType("application/json; charset=UTF-8");
						response.getWriter().write(json.toString());
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 微信H5支付服务预约回调方法
	@RequestMapping(value = { "/api/weixinH5WorkNotify" })
	public void weixinH5WorkNotify(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		try {
			ServletInputStream in = request.getInputStream();
			String resxml = FileUtil.readInputStream2String(in);
			Map<String, String> restmap = XmlUtil.xmlParse(resxml);
			LOG.info("支付结果通知：" + restmap);
			System.out.println("支付结果通知===：" + restmap);
			if ("SUCCESS".equals(restmap.get("return_code"))) {
				// 订单支付成功 业务处理
				String out_trade_no = restmap.get("out_trade_no"); // 商户订单号
				// 通过商户订单判断是否该订单已经处理 如果处理跳过 如果未处理先校验sign签名 再进行订单业务相关的处理
				String transaction_id = restmap.get("transaction_id");// 微信订单号
				String sing = restmap.get("sign"); // 返回的签名
				restmap.remove("sign");
				String signnow = PayUtil.getSign(restmap, WeixinConfigUtils.key);
				System.out.println("订单支付通知：" + signnow);
				if (signnow.equals(sing)) {
					// 进行业务处理
					LOG.info("订单支付通知： 支付成功，订单号" + out_trade_no);
					WorkOrder dto = workOrderService.getByCext1(out_trade_no);
					if (dto != null && dto.getOrderStatus() == Constants.OrderType.ORDER_NOPAY) {
						// 进行业务处理
						dto.setOrderStatus(Constants.OrderType.ORDER_HASPAY);
						dto.setPayValue(dto.getUnitPrice());
						dto.setPayType("wechat_h5");
						dto.setActualPayment(dto.getUnitPrice());
						dto.setPayTime(DateUtils.dateToString19(new Date()));
						dto.setCext2(out_trade_no);
						dto.setPayAccount(transaction_id);
						workOrderService.upd(dto);
						
						Service service = serviceService.get(dto.getServiceId());
						int num = (service.getIext2()==null? 0:service.getIext2()) + dto.getAmount();
						service.setIext2(num);
						service.setUpdateTime(dto.getPayTime());
						serviceService.upd(service);
					}
				} else {
					LOG.info("订单支付通知：签名错误");
				}
			} else {
				LOG.info("订单支付通知：支付失败，" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	// 微信公众号支付服务预约回调方法
	@RequestMapping(value = { "/api/weixinJsbridgeWorkNotify" })
	public void weixinJsbridgeWorkNotify(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		try {
			ServletInputStream in = request.getInputStream();
			String resxml = FileUtil.readInputStream2String(in);
			Map<String, String> restmap = XmlUtil.xmlParse(resxml);
			LOG.info("支付结果通知：" + restmap);
			System.out.println("支付结果通知===：" + restmap);
			if ("SUCCESS".equals(restmap.get("return_code"))) {
				// 订单支付成功 业务处理
				String out_trade_no = restmap.get("out_trade_no"); // 商户订单号
				// 通过商户订单判断是否该订单已经处理 如果处理跳过 如果未处理先校验sign签名 再进行订单业务相关的处理
				String transaction_id = restmap.get("transaction_id");// 微信订单号
				WorkOrder dto = workOrderService.getByCext1(out_trade_no);
				if (dto != null && dto.getOrderStatus() == Constants.OrderType.ORDER_NOPAY) {
					// 进行业务处理
					dto.setOrderStatus(Constants.OrderType.ORDER_HASPAY);
					dto.setPayValue(dto.getUnitPrice());
					dto.setPayType("wechat_gzh");
					dto.setActualPayment(dto.getUnitPrice());
					dto.setPayTime(DateUtils.dateToString19(new Date()));
					dto.setCext2(out_trade_no);
					dto.setPayAccount(transaction_id);
					workOrderService.upd(dto);
					
					Service service = serviceService.get(dto.getServiceId());
					int num = (service.getIext2()==null? 0:service.getIext2()) + dto.getAmount();
					service.setIext2(num);
					service.setUpdateTime(dto.getPayTime());
					serviceService.upd(service);
				}
			} else {
				LOG.info("订单支付通知：支付失败，" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 查询支付结果
	 * 
	 * @param request
	 * @param response
	 * @param tradeno
	 *            订单号
	 */
	@ResponseBody
	@RequestMapping(value = "/api/weixinH5Query", method = RequestMethod.POST)
	public void orderPayQuery(HttpServletRequest request, HttpServletResponse response, String tradeno) {
		Map<String, String> restmap = null;
		try {
			Map<String, String> parm = new HashMap<String, String>();
			parm.put("appid", WeixinConfigUtils.appid);
			parm.put("mch_id", WeixinConfigUtils.mch_id);
			parm.put("out_trade_no", tradeno);
			parm.put("nonce_str", PayUtil.getNonceStr());
			parm.put("sign", PayUtil.getSign(parm, WeixinConfigUtils.key));
			String restxml = HttpUtils.post(ORDER_PAY_QUERY, XmlUtil.xmlFormat(parm, false));
			restmap = XmlUtil.xmlParse(restxml);

			if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
				// 订单查询成功 处理业务逻辑
				LOG.info("订单查询：订单" + restmap.get("out_trade_no") + "支付成功");
				YwOrder dto = ywOderService.getByCext1(tradeno);
				if (null != dto) {
					restmap.put("payValue", dto.getPayValue() != null ? dto.getPayValue().toString() : "");
					restmap.put("payTime", dto.getPayTime() != null ? dto.getPayTime().toString() : "");
				} else {
					WorkOrder workOrder = workOrderService.getByCext1(tradeno);
					if (null != workOrder) {
						restmap.put("payValue",
								workOrder.getPayValue() != null ? workOrder.getPayValue().toString() : "");
						restmap.put("payTime", workOrder.getPayTime() != null ? workOrder.getPayTime().toString() : "");
					}
				}
			} else {
				if (CollectionUtil.isNotEmpty(restmap)) {
					LOG.info("订单支付失败：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
				}
			}
			JSONObject json = new JSONObject();
			json.put("map", restmap);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 使用原生浏览器打开URL
	 * 
	 * @param type
	 * @param cext1
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotobrowser")
	public String gotobrowser(String type, String cext1, ModelMap modelMap) throws IOException {
		modelMap.put("type", type);
		modelMap.put("cext1", cext1);
		return "gotobrowser.html";
	}

	@RequestMapping(value = "/weixinJSBridgeCode")
	public void weixinJSBridgeCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			String redirect_uri = URLEncoder.encode("https://healthcloud.ejyhealth.com/api/h5/login", "UTF-8");

			// 简单获取openid的话参数response_type与scope与state参数固定写死即可
			StringBuffer url = new StringBuffer(
					"https://open.weixin.qq.com/connect/oauth2/authorize?redirect_uri=" + redirect_uri + "&appid="
							+ WeixinJSBridge.appid + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
			response.sendRedirect(url.toString());// 这里请不要使用get请求单纯的将页面跳转到该url即可
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/weixinJSBridgeOpenId")
	public void weixinJSBridgeOpenId(HttpServletRequest request, HttpServletResponse response, String secret,
			String code) {
		try {
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			Map<String, String> params = new HashMap<String, String>();
			params.put("secret", WeixinJSBridge.secret);
			params.put("appid", WeixinJSBridge.appid);
			params.put("grant_type", "authorization_code");
			params.put("code", code);
			String result = HttpGetUtil.httpRequestToString("https://api.weixin.qq.com/sns/oauth2/access_token", params);
			JSONObject jsonObject = JSONObject.parseObject(result);
			String openid = jsonObject.get("openid").toString();
			JSONObject json = new JSONObject();
			json.put("openid", openid);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
