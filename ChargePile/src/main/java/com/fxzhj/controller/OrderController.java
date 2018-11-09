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

import com.fxzhj.model.Order;
import com.fxzhj.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.util.StringUtil;

/**
 * 充电记录
 * @author jifei
 *
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "orderJsp")
	public String orderJsp(){
		return "order.jsp";
	}
	
	/**
	 * 根据条件查询充电订单
	 * @param order
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryOrder")
	@ResponseBody
	public Map<String, Object> queryOrder(Order order, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(request.getParameter("page")) && StringUtil.isNotEmpty(request.getParameter("rows"))){
			int pageNo = Integer.parseInt((String) request.getParameter("page"));
			// 当前检索的条数
			int pageSize = Integer.parseInt((String) request.getParameter("rows"));
			PageHelper.startPage(pageNo, pageSize);
		}
		List<Order> list =orderService.queryOrder(order);
		PageInfo<Order> pageList = null;
		if(list!=null&&list.size()>0){
			pageList = new PageInfo<Order>(list);
		}
		
		if (pageList != null && pageList.getList() != null && pageList.getList().size()>0) {
			map.put("total", pageList.getTotal());
			map.put("rows", pageList.getList());
		}else{
			map.put("total", 0);
			map.put("rows", new ArrayList<Order>());
		}
		return map;
	} 


}
