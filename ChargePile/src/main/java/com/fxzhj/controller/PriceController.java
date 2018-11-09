package com.fxzhj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fxzhj.model.Price;
import com.fxzhj.service.PriceService;
import com.fxzhj.util.HttpClientUtils;

import tk.mybatis.mapper.util.StringUtil;

/**
 * 省,市,区 电价表
 * @author jifei
 *
 */
@Controller
@RequestMapping(value = "/price")
public class PriceController {
	
	@Autowired
	private PriceService priceService;
	
	@RequestMapping(value = "priceJsp")
	public String priceJsp(){
		return "price.jsp";
	}
	
	/**
	 * 根据节点查询电价
	 * @param comOrareId
	 * @param deep
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryPrice")
	@ResponseBody
	public List<Price> queryPrice(String comOrareId, String deep, HttpServletRequest request, HttpServletResponse response){
		List<Price> list = new ArrayList<Price>();
		Price price = new Price();
		try {
			if("1".equals(deep) || "2".equals(deep) || "3".equals(deep)){
				price.setAreaId(Integer.parseInt(comOrareId));
			}else if("9".equals(deep)){
				price.setCommunityId(Integer.parseInt(comOrareId));
			}
			list = priceService.queryPrice(price);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//批量新增
	@RequestMapping(value = "/addPrice")
	@ResponseBody
	public Map<String, Object> addPrice(String mins, String price, String deep, String comOrareId, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Price> list=new ArrayList<Price>();
		Price pri=new Price();
		if (StringUtil.isNotEmpty(mins) && StringUtil.isNotEmpty(price) ) {
			try {
				String[] minsArr=mins.split(",");
				String[] priceArr=price.split(",");
				if(minsArr.length==priceArr.length){
					for(int i=0;i<minsArr.length;i++){
						Price bean=new Price();
						if(StringUtil.isEmpty(minsArr[i]) || StringUtil.isEmpty(priceArr[i])){
							map.put("state", "FALSE");
							return map;
						}
						bean.setMins(Integer.parseInt(minsArr[i]));
						bean.setPrice(Float.parseFloat(priceArr[i]));
						if("9".equals(deep)){
							//9表示具体小区电价
							bean.setCommunityId(Integer.parseInt(comOrareId));
						}else if("1".equals(deep) || "2".equals(deep) || "3".equals(deep)){
							//1,2,3为区域电价
							bean.setAreaId(Integer.parseInt(comOrareId));
						}else{
							//为空或为0表示定义统一电价
							bean.setCommunityId(null);
							bean.setAreaId(null);
						}
						if("1".equals(minsArr[i])){
							//1分钟表示按时计算为后计算
							bean.setType(0);
						}else{
							bean.setType(1);
						}
						list.add(bean);
					}
					
					if("9".equals(deep)){
						//9表示具体小区电价
						pri.setCommunityId(Integer.parseInt(comOrareId));
					}else if("1".equals(deep) || "2".equals(deep) || "3".equals(deep)){
						//1,2,3为区域电价
						pri.setAreaId(Integer.parseInt(comOrareId));
					}
					
					priceService.deletePrice(pri);
					//批量新增
					priceService.batchAdd(list);
					map.put("state", "SUCCESS");
				}else{
					map.put("state", "FALSE");
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("state", "ERROR");
			}
		}else{
			map.put("state", "FALSE");
		}
		return map;
	}
	
	//启用或停用调用接口进行通知设备
	@RequestMapping(value = "/priceServer")
	@ResponseBody
	public Map<String, String> priceServer(String deep, String comOrareId, HttpServletRequest request, HttpServletResponse response) {
		Price bean=new Price();
		Map<String, String> mapJson = new HashMap<String, String>();
		mapJson.put("funCode", "fn1002");
		mapJson.put("comOrareId", comOrareId);
		mapJson.put("deep", deep);
		String jsonStr = HttpClientUtils.doPost("http://192.168.1.107:8080/api",mapJson);
		if(StringUtil.isNotEmpty(jsonStr)){
			Map<String, Object> jsonMap = JSON.parseObject(jsonStr,new TypeReference<Map<String, Object>>(){} );
			Object value = jsonMap.get("success");
			if(value!=null && "true".equals(value.toString())){
				if(StringUtil.isNotEmpty(comOrareId)){
					//表示启用
					bean.setStatus(1);
				}else{
					//表示停用
					bean.setStatus(0);
				}
				if("9".equals(deep)){
					//9表示具体小区电价
					bean.setCommunityId(Integer.parseInt(comOrareId));
					bean.setAreaId(null);
				}else {
					//1,2,3为区域电价
					bean.setAreaId(Integer.parseInt(comOrareId));
					bean.setCommunityId(null);
				}
				try {
					priceService.updateStatus(bean);
					mapJson.put("state", "SUCCESS");
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}else{
				mapJson.put("state", "FALSE");
			}
		}else{
			mapJson.put("state", "FALSE");
		}
		return mapJson;
	}
	

}
