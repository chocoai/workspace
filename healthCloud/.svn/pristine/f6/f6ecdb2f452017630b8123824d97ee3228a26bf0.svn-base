package com.yhcrt.healthcloud.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants.OrderType;
import com.yhcrt.healthcloud.common.DataTable;
import com.yhcrt.healthcloud.mall.entity.OrderDetail;
import com.yhcrt.healthcloud.mall.entity.OrderRefund;
import com.yhcrt.healthcloud.mall.entity.YwOrder;
import com.yhcrt.healthcloud.mall.service.OrderDetailService;
import com.yhcrt.healthcloud.mall.service.OrderRefundService;
import com.yhcrt.healthcloud.mall.service.YwOrderService;

/**
 * 商品订单controller
 * @author PC
 *
 */
@Controller
@RequestMapping("/ywOrder")
public class YwOrderController extends BaseController {

	@Autowired
	private YwOrderService ywOrderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired  
    private OrderRefundService orderRefundService;

	/**
	 * @Title: getYwOrderList
	 * @Description: 根据条件查询出订单信息，返回到列表页
	 * @param request
	 * @param response
	 * @param orderId
	 * @param orderName
	 * @param orderStatus
	 * @return
	 */
	@RequestMapping("/list")
	public String getYwOrderList(HttpServletRequest request, HttpServletResponse response) {
		String cext1 = request.getParameter("cext1");
		String orderName = request.getParameter("orderName");
		String orderStatus = request.getParameter("orderStatus");
		if(StringUtils.isNotBlank(cext1)){
			request.setAttribute("cext1", cext1.trim());
		}
		if(StringUtils.isNotBlank(orderName)){
			request.setAttribute("orderName", orderName.trim());
		}
		if (StringUtils.isNotBlank(orderStatus)) {
			request.setAttribute("orderStatus", orderStatus);
		}
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(OrderType.ORDER_CANCER, "取消订单");
		map.put(OrderType.ORDER_NOPAY, "待支付");
		map.put(OrderType.ORDER_HASPAY, "待发货");
		map.put(OrderType.ORDER_DOING, "待收货");
		map.put(OrderType.ORDER_TODONE, "待评价");
		map.put(OrderType.ORDER_DONE, "已完成");
        request.setAttribute("orderType", map);
		return "mall/ywOrder/list";
	}
	
	/**
	 * 返回表格查询数据
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/pageList")
	public void pageList(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String,Object>();
		String cext1 = request.getParameter("cext1");
		String orderName = request.getParameter("orderName");
		String orderStatus = request.getParameter("orderStatus");
		if(StringUtils.isNotBlank(cext1)){
			map.put("cext1", cext1.trim());
		}
		if(StringUtils.isNotBlank(orderName)){
			map.put("orderName", orderName.trim());
		}
		if (StringUtils.isNotBlank(orderStatus)) {
			map.put("orderStatus", Integer.parseInt(orderStatus));
		}
		try {
			DataTable dataTable = new DataTable(request);
			PageHelper.startPage(dataTable.getPageNum(), dataTable.getPageSize());
			List<YwOrder> ywOrderList = ywOrderService.getAll(map);
			PageInfo<YwOrder> pageInfo = new PageInfo<YwOrder>(ywOrderList);
			//封装数据给DataTables  
			dataTable.setDraw(dataTable.getDraw());  
			dataTable.setData(pageInfo.getList());
			dataTable.setRecordsTotal((int)pageInfo.getTotal());    
			dataTable.setRecordsFiltered(dataTable.getRecordsTotal()); 
			String jsonString = JSON.toJSONString(dataTable);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: editYwOrder
	 * @Description: 根据orderId查询出订单及订单详情信息，返回到订单详情展示页
	 * @param request
	 * @param response
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String editYwOrder(HttpServletRequest request, HttpServletResponse response, Integer orderId) {
		if (null != orderId) {
			YwOrder order = ywOrderService.get(orderId);
			List<OrderDetail> orderDetails = orderDetailService.getDetailsByOrderId(orderId);
			request.setAttribute("orderDetails", orderDetails);
			request.setAttribute("order", order);
		}
		return "mall/ywOrder/show";

	}

	/**
	 * @Title: shipmentYwOrder
	 * @Description: 根据orderId查询出订单信息并返回到发货模态框
	 * @param request
	 * @param response
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/toShipment")
	public String shipmentYwOrder(HttpServletRequest request, HttpServletResponse response, Integer orderId) {
		if (null != orderId) {
			YwOrder order = ywOrderService.get(orderId);
			request.setAttribute("order", order);
		}
		return "mall/ywOrder/shipment";

	}

	/**
	 * @Title: assignAll
	 * @Description: 发货信息保存
	 * @param request
	 * @param response
	 * @param attr
	 * @param orderId
	 * @param logistics
	 * @param waybill
	 * @param freight
	 * @return
	 */
	@RequestMapping(value = "/shipment", method = RequestMethod.POST)
	public String assignAll(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr,
			Integer orderId, String logistics, String waybill, Double freight) {
		YwOrder order = ywOrderService.get(orderId);
		order.setLogistics(logistics);
		order.setWaybill(waybill);
		order.setFreight(freight);
		order.setOrderStatus(OrderType.ORDER_DOING);
		Integer i = 0;
		try {
			i = ywOrderService.update(order);
		} catch (Exception e) {
			e.printStackTrace();
			attr.addFlashAttribute("states", "FALSE");
		}
		if (i == 1) {
			attr.addFlashAttribute("states", "SUCCESS");
		} else {
			attr.addFlashAttribute("states", "FALSE");
		}
		return "redirect:list";
	}

	/**
	 * 根据orderId修改订单状态
	 * @param request
	 * @param response
	 * @param orderId
	 * @param status
	 */
	@ResponseBody
	@RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
	public void updateOrderStatus(HttpServletRequest request, HttpServletResponse response, 
			Integer orderId, Integer status) {
		YwOrder order = new YwOrder();
		order.setOrderId(orderId);
		order.setOrderStatus(status);
		ywOrderService.updateByStatus(order);
	}
	
	/**
     * 商品订单退款界面
     * @param request
     * @param response
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/toGoodsRefund")    
    public String toGoodsRefund(HttpServletRequest request,HttpServletResponse response,Integer orderId){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("orderId", orderId);
    	map.put("type", "goods");
    	List<OrderRefund> list = orderRefundService.queryGoodsList(map);
    	request.setAttribute("order", list);
        return "mall/refund/goodsRefund";   
    }
    
    /**
     * 退款操作
     * @param request
     * @param response
     * @param refund
     * @return
     */
    @RequestMapping(value = "/goodsRefund")    
    public String goodsRefund(HttpServletRequest request,HttpServletResponse response, 
    		RedirectAttributes attr){ 
    	String cid = request.getParameter("cid");
    	String status = request.getParameter("status");
        if(StringUtils.isNotBlank(cid) && StringUtils.isNotBlank(status)){
        	Map<String, String> resMap = ywOrderService.updateByDetailCid(cid,status);
            if("SUCCESS".equals(resMap.get("state")) && "6".equals(status)){ //退款成功
            	attr.addFlashAttribute("state", "SUCCESS");
            	attr.addFlashAttribute("msg", "退款已成功");
            }else if("SUCCESS".equals(resMap.get("state")) && "7".equals(status)){ //拒绝退款
            	attr.addFlashAttribute("state", "SUCCESS");
            	attr.addFlashAttribute("msg", "已拒绝退款");
            }else{
            	attr.addFlashAttribute("state", "FALSE");
            	attr.addFlashAttribute("msg", resMap.get("msg"));
            }
        }else{
        	attr.addFlashAttribute("state", "FALSE");
        	attr.addFlashAttribute("msg", "请求失败");
        }
        return "redirect:list";   
    }

}
