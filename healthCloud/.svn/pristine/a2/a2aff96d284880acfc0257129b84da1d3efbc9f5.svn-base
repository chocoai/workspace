package com.yhcrt.healthcloud.mall.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.common.Constants.PayType;
import com.yhcrt.healthcloud.mall.entity.OrderRefund;
import com.yhcrt.healthcloud.mall.mapper.OrderRefundMapper;
import com.yhcrt.healthcloud.mall.service.OrderRefundService;
import com.yhcrt.healthcloud.util.HttpUtil;
import com.yhcrt.healthcloud.util.StringUtil;

@Service
public class OrderRefundServiceImpl implements OrderRefundService {

	@Autowired
	private OrderRefundMapper orderRefundMapper;

	//查询服务退款信息
	@Override
	public OrderRefund queryByMap(Map<String, Object> map) {
		return orderRefundMapper.queryByMap(map);
	}

	//查询商品退款信息
	@Override
	public List<OrderRefund> queryGoodsList(Map<String, Object> map) {
		return orderRefundMapper.queryGoodsList(map);
	}
	
	/**
	 * 调用退款接口
	 * @param refId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> refund(Map<String, String> map) {
		JSONObject jsonParam = new JSONObject();
		String payType = map.get("payType");
		String url = "";
		if(PayType.ALI_ALIPAY.equals(payType) || PayType.ALI_ALIPAY_H5.equals(payType) || 
				PayType.ALI_ALIPAY_PC.equals(payType)){ //支付宝支付退款
			jsonParam.put("outTradeNo", map.get("outTradeNo")); //交易订单号
			jsonParam.put("refundAmount", map.get("refundMoney")); //退款金额
			jsonParam.put("outRequestNo", StringUtil.getOutRequestNo()); //部分退款需要参数
			url = "http://47.96.31.109:9997/PayMent/aliApi/alipayRefund";
		}else if(PayType.WX_PC.equals(payType) || PayType.WX_WECHAT.equals(payType) || 
				PayType.WX_WECHAT_H5.equals(payType) ||  PayType.WX_GZH.equals(payType) || PayType.WX_SMALL.equals(payType)){ //微信支付退款
			jsonParam.put("outTradeNo", map.get("outTradeNo")); //交易订单号
			jsonParam.put("refundAmount", map.get("refundMoney")); //退款金额
			jsonParam.put("totalAmount", map.get("totalAmount")); //部分退款需要参数
			if(PayType.WX_GZH.equals(payType)){	//微信公众号退款
				url = "http://47.96.31.109:9997/PayMent/wechat/wxRefundGzh";
			}else{
				url = "http://47.96.31.109:9997/PayMent/wechat/wxRefund";
			}
		} 
		String jsonStr = "";
		try {
			jsonStr = HttpUtil.httpPostJson(jsonParam, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.clear();
		if(!StringUtils.isEmpty(jsonStr)){
			if(PayType.ALI_ALIPAY.equals(payType) || PayType.ALI_ALIPAY_H5.equals(payType) || 
					PayType.ALI_ALIPAY_PC.equals(payType)){ //支付宝退款返回结果
				JSONObject json = JSONObject.parseObject(JSONObject.parseObject(jsonStr).getString("alipay_trade_refund_response"));
				if("10000".equals(json.getString("code")) && "Success".equals(json.getString("msg")) && 
						"Y".equals(json.getString("fund_change"))){
					map.put("state", "SUCCESS");
					map.put("msg", "退款成功");
				}else{
					map.put("state", "FALSE");
					map.put("msg", json.getString("sub_msg"));
				}
			}else{	//微信退款返回结果
				Map<String, String> mapType = JSON.parseObject(jsonStr,Map.class); 
				String returnCode = mapType.get("return_code");
				String resultCode = mapType.get("result_code");
		        if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
		        	map.put("state", "SUCCESS");
					map.put("msg", "退款成功");
		        }else{
		        	map.put("state", "FALSE");
		        	map.put("msg", mapType.get("err_code_des"));
		        }
			}
		}else{
			map.put("state", "FALSE");
			map.put("msg", "退款失败");
		}
		return map;
	}

}
