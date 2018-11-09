/**
 * 
 */
package com.yhcrt.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yhcrt.demo.model.SortData;
import com.yhcrt.demo.model.YwOrder;
import com.yhcrt.demo.model.YwOrderExample;
import com.yhcrt.demo.model.YwOrderExample.Criteria;
import com.yhcrt.demo.service.YwOrderService;
import com.yhcrt.demo.util.ExportUtil;
import com.yhcrt.demo.util.ListView;
import com.yhcrt.demo.util.UploadService;

/** 
 * @ClassName: OrderManController 
 * @author: zjd
 * @date: 2018年3月22日 上午11:07:03  
 */
@Controller
@RequestMapping("/sys/order")
public class OrderManController {

	@Autowired
	private YwOrderService orderService;
	
	@RequestMapping(value = "/getOrders",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getOrders(HttpServletRequest request, HttpServletResponse response,YwOrder order){
		String sort= request.getParameter("sort");//要排序的列名--无需定义，ext自动后传[{"property":"id","direction":"DESC"}]
		//可能是多个排序字段
		Gson gson = new Gson();  
    	List<SortData> sortData = gson.fromJson(sort, new TypeToken<List<SortData>>(){}.getType());  
    	String property = sortData.get(0).getDirection();  
    	String direction = camelToUnderline(sortData.get(0).getProperty());  
		Integer firstResult = Integer.valueOf(request.getParameter("start"));//start是开始记录数，limit是本页最大记录数。
		firstResult = (1+firstResult/20);
		Integer maxResults = Integer.valueOf(request.getParameter("limit"));
		PageHelper.startPage(firstResult,maxResults);
		YwOrderExample example = new YwOrderExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNoneBlank(order.getCext1())){
			criteria.andCext1EqualTo(order.getCext1());
		}
		if(null != order.getMemberId()){
			criteria.andMemberIdEqualTo(order.getMemberId());
		}
		if(StringUtils.isNoneBlank(order.getRecipient())){
			criteria.andRecipientEqualTo(order.getRecipient());
		}
		if(StringUtils.isNoneBlank(order.getPhoneNum())){
			criteria.andPhoneNumEqualTo(order.getPhoneNum());
		}
		if(StringUtils.isNoneBlank(direction) && StringUtils.isNoneBlank(property)){
			example.setOrderByClause(direction+" "+property);
		}
		List<YwOrder> orders = orderService.selectByExample(example);
		PageInfo<YwOrder> p = new PageInfo<YwOrder>(orders);
		ListView<YwOrder> YhcrtListView = new ListView<YwOrder>();
		YhcrtListView.setData(orders);
		YhcrtListView.setTotalRecord(p.getTotal());
		return JSONObject.toJSONString(YhcrtListView);
	}
	
	/*
	 * 导出
	 */
	@RequestMapping(value = "/getExport",produces = "application/json;charset=utf-8")
	@ResponseBody
	public void getExport(HttpServletRequest request, HttpServletResponse response,@RequestParam("ids") Integer[] ids) throws IOException{
		//设置excel表头
        String[] header = {"id","会员id","订单总价","运费","优惠额","实际付款","收货人","联系电话","收货地址"};
		//设置表名称
        String title = "订单数据";
      //数据
        List<Map<Integer, Object>> list = new ArrayList<Map<Integer,Object>>();
        for(int i = 0;i<ids.length;i++){
        	HashMap<Integer,Object> map = new HashMap<Integer,Object>();
        	YwOrder order = orderService.selectByPrimaryKey(ids[i]);
    		map.put(0, order.getOrderId());
    		map.put(1, order.getMemberId());
    		map.put(2, order.getOrderAmount());
    		map.put(3, order.getFreight());
    		map.put(4, order.getDiscounts());
    		map.put(5, order.getActualPayment());
    		map.put(6, order.getRecipient());
    		map.put(7, order.getPhoneNum());
    		map.put(8, order.getAddress());
    		list.add(map);
        }
		Workbook wb = ExportUtil.create(title, header, list);
		String fileName = "订单数据.xlsx";
        response.setContentType("application/vnd.ms-excel");  
        response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);    
        ouputStream.flush();    
        ouputStream.close();
       }
	
	/*
	 * 导入
	 */
	@RequestMapping(value = "/getImport",produces = "application/json;charset=utf-8")
	@ResponseBody
	public void getImport(HttpServletRequest request, HttpServletResponse response){
		String name = UploadService.upload(request);
		System.out.println(name);
	}
	
	/**
	 * 将字段从小驼峰转换为下划线方式
	 */
	public static String camelToUnderline(String param) {
	    if ((param == null) || ("".equals(param.trim()))) {
	        return "";
	    }
	    int len = param.length();
	    StringBuilder sb = new StringBuilder(len);
	    for (int i = 0; i < len; ++i) {
	        char c = param.charAt(i);
	        if (Character.isUpperCase(c)) {
	            sb.append('_');
	            sb.append(Character.toLowerCase(c));
	        } else {
	            sb.append(c);
	        }
	    }
	    return sb.toString();
	}
}
