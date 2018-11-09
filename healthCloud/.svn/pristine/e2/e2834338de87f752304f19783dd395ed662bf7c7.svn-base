package com.yhcrt.healthcloud.mall.controller;	
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.common.Constants.OrderType;
import com.yhcrt.healthcloud.mall.entity.OrderRefund;
import com.yhcrt.healthcloud.mall.entity.ServiceLog;
import com.yhcrt.healthcloud.mall.entity.WorkOrder;
import com.yhcrt.healthcloud.mall.entity.YwImage;
import com.yhcrt.healthcloud.mall.service.OrderRefundService;
import com.yhcrt.healthcloud.mall.service.ServiceLogService;
import com.yhcrt.healthcloud.mall.service.WorkOrderService;
import com.yhcrt.healthcloud.mall.service.YwImageService;
import com.yhcrt.healthcloud.organization.entity.Employee;
import com.yhcrt.healthcloud.organization.service.EmployeeService;
import com.yhcrt.healthcloud.provider.entity.ServiceProvider;
import com.yhcrt.healthcloud.util.DateUtil;
	
/**
 * 服务工单controller	
 * @author PC
 *
 */
@Controller	
@RequestMapping("/workOrder")
public class WorkOrderController extends BaseController{	
    	
    @Autowired	
    private WorkOrderService workOrderService;	
    @Autowired  
    private YwImageService ywImageService;  
    @Autowired  
    private ServiceLogService serviceLogService;
    @Autowired  
    private EmployeeService employeeService;
    @Autowired  
    private OrderRefundService orderRefundService;
    	
    /**
     * @Title: getWorkOrderList
     * @Description: 根据条件查询工单，返回列表页
     * @param request
     * @param response
     * @param orgId
     * @param orderId
     * @param orderStatus
     * @param payStatus
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)	
    public String getWorkOrderList(HttpServletRequest request,HttpServletResponse response,	
            Integer providerId, String cext1, Integer orderStatus){
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(OrderType.ORDER_CANCER, "取消订单");
        map.put(OrderType.ORDER_NOPAY, "待支付");
        map.put(OrderType.ORDER_HASPAY, "已支付");
        map.put(OrderType.ORDER_DOING, "进行中");
        map.put(OrderType.ORDER_TODONE, "完成待审核");
        map.put(OrderType.ORDER_DONE, "完成待评价");
//        map.put(OrderType.ORDER_TOREFUND, "退款中");
//        map.put(OrderType.ORDER_REFUND, "已退款");
//        map.put(OrderType.ORDER_NOREFUND, "拒绝退款");
        map.put(OrderType.ORDER_SUCCESS, "交易完成");
        
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("providerId", providerId);
        params.put("cext1", cext1);
        params.put("orderStatus", orderStatus);
        List<WorkOrder> workOrderList = workOrderService.getAll(params);
        request.setAttribute("workOrderList",workOrderList); 
        request.setAttribute("providerList", getProList());
        request.setAttribute("providerId",providerId);
        request.setAttribute("cext1",cext1);
        request.setAttribute("orderType",map);
        request.setAttribute("orderStatus",orderStatus);
        return "mall/workOrder/list";
    	
    }	
    
    @ResponseBody
    @RequestMapping(value = "/getToDoNum", method = RequestMethod.GET)	
    public String getToDoNum(HttpServletRequest request,HttpServletResponse response){
        //服务商的集合
        List<Integer> proList = new ArrayList<Integer>();
        HashMap<String, Object> params = new HashMap<String, Object>();
        List<ServiceProvider> prolist = getProList();
        if(!prolist.isEmpty() && prolist.size()>0){
        	for(int i=0;i<prolist.size();i++){
                //获取查询出的机构对象的ID并存入到ID条件集合中
                proList.add(i, prolist.get(i).getProviderId());
            }
        	params.put("proList", proList);
        }
        Integer toDoWorkNum = workOrderService.toDoWorkNum(params);
        Integer toDoGoodsNum = workOrderService.toDoGoodsNum();
        JSONObject resjosn = new JSONObject();
        resjosn.put("success", true);
        resjosn.put("toDoWorkNum", toDoWorkNum);
        resjosn.put("toDoGoodsNum", toDoGoodsNum);
        return resjosn.toJSONString();
    	
    }
    	
    /**
     * @Title: editWorkOrder
     * @Description: 根据orderId查询出对应的工单信息，返回到内容详情展示页
     * @param request
     * @param response
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)	
    public String editWorkOrder(HttpServletRequest request,HttpServletResponse response,Integer orderId){	
        if(null != orderId){
            WorkOrder workOrder = workOrderService.queryByOrderId(orderId);	
            request.setAttribute("workOrder", workOrder);	
        }	
        return "mall/workOrder/show";	
        	
    }	
    
    /**
     * @Title: showLog
     * @Description: 根据orderId查询出服务日志记录及工单，根据日志查询出日志关联的图片，携带信息返回到日志详情展示页
     * @param request
     * @param response
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/showLog", method = RequestMethod.GET)	
    public String showLog(HttpServletRequest request,HttpServletResponse response,Integer orderId){	
        if(null != orderId){
            ServiceLog serviceLog = serviceLogService.getByOrderId(orderId);
            if(serviceLog != null){
                List<YwImage> imgList = ywImageService.getByRefId(orderId,"service_log");  
                request.setAttribute("imgList", imgList);
            }
            request.setAttribute("serviceLog", serviceLog);	
        }	
        return "mall/workOrder/showLog";	
        
    }
    
    /**
     * @Title: toAssign
     * @Description: 根据登录管理员所属机构查询出员工信息，返回到分配工单的模态框
     * @param request
     * @param response
     * @param orderId
     * @param orderIds
     * @return
     */
    @RequestMapping(value = "/toAssign", method = RequestMethod.GET)
    public String toAssign(HttpServletRequest request, HttpServletResponse response, Integer orderId, Integer[] orderIds){
        if(orderId != null){
            WorkOrder workOrder = workOrderService.queryByOrderId(orderId);
            request.setAttribute("orderId", orderId);
            request.setAttribute("workOrder", workOrder);
        }else{
            request.setAttribute("orderIds", orderIds);
        }
        return "mall/workOrder/assign";
        
    }
    
    @ResponseBody
    @RequestMapping(value = "getEmpByOrgId", method = RequestMethod.POST)
    public void  getEmpByOrgId(HttpServletRequest request, HttpServletResponse response, Integer orgId){
    	List<Integer> orgId_list = getOrgList(orgId);
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("orgId_list", orgId_list);
    	param.put("specialty", "servicer");	//服务人员
    	param.put("status", Constants.STATUS_NORMAL);
        List<Employee> emp_list = employeeService.queryList(param);
        JSONArray emp_array = new JSONArray();
        for(Employee emp:emp_list){
            JSONObject object = new JSONObject();
            object.put("empId", emp.getEmpId());
            object.put("empName", emp.getRealName());
            emp_array.add(object);
        }
        response.setContentType("application/json; charset:UTF-8");
        try {
            response.getWriter().write(emp_array.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * @Title: assignAll
     * @Description: 分配工单及批量分配工单操作
     * @param request
     * @param response
     * @param orderIds
     * @param toUser
     * @param orderId
     * @param remark
     */
    @ResponseBody
    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    public void assignAll(HttpServletRequest request,HttpServletResponse response,Integer[] orderIds,
            String toUser, Integer orderId, String remark){
    	String time = DateUtil.getDateTime();
        WorkOrder order = null;
        if(orderId != null){
            order = workOrderService.get(orderId);
            order.setHandler(getLoginUser().getUserId().toString());
            order.setToUser(toUser);
            order.setOrderStatus(OrderType.ORDER_DOING);
            order.setRemark(remark);
            order.setHandleTime(time);
            order.setCext3(time);
            workOrderService.upd(order);
        }else{
            for(Integer cid:orderIds){
                order = workOrderService.get(cid);
                order.setHandler(getLoginUser().getUserId().toString());
                order.setToUser(toUser);
                order.setOrderStatus(OrderType.ORDER_DOING);
                order.setRemark(remark);
                order.setHandleTime(time);
                order.setCext3(time);
                workOrderService.upd(order);
            }
        }
    }
    
    /**
     * 工单完成
     * @param request
     * @param response
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/completed", method = RequestMethod.GET)    
    public String completed(HttpServletRequest request,HttpServletResponse response,Integer orderId){   
        if(null != orderId){    
            WorkOrder workOrder = workOrderService.get(orderId);  
            workOrder.setOrderStatus(OrderType.ORDER_DONE);
            workOrderService.upd(workOrder);
        }   
        return "redirect:list";   
    }
    
    /**
     * 服务工单退款界面
     * @param request
     * @param response
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/toServiceRefund")    
    public String toServiceRefund(HttpServletRequest request,HttpServletResponse response,Integer orderId){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("orderId", orderId);
    	map.put("type", "service");
    	OrderRefund orderRefund = orderRefundService.queryByMap(map);
    	request.setAttribute("order", orderRefund);
        return "mall/refund/serviceRefund";   
    }
    
    /**
     * 退款操作
     * @param request
     * @param response
     * @param refund
     * @return
     */
    @RequestMapping(value = "/serviceRefund")    
    public String serviceRefund(HttpServletRequest request,HttpServletResponse response, 
    		RedirectAttributes attr, OrderRefund refund){   
        if(refund != null){
            Map<String, String> resMap = workOrderService.update(refund);
            if("SUCCESS".equals(resMap.get("state")) && refund.getStatus() == 6){ //退款成功
            	attr.addFlashAttribute("state", "SUCCESS");
            	attr.addFlashAttribute("msg", "退款已成功");
            }else if("SUCCESS".equals(resMap.get("state")) && refund.getStatus() == 7){ //拒绝退款
            	attr.addFlashAttribute("state", "SUCCESS");
            	attr.addFlashAttribute("msg", "已拒绝退款");
            }else{
            	attr.addFlashAttribute("state", "FALSE");
            	attr.addFlashAttribute("msg", resMap.get("msg"));
            }
        }else{
        	attr.addFlashAttribute("state", "FALSE");
        	attr.addFlashAttribute("msg", "请求参数为空");
        }
        return "redirect:list";   
    }
    
}	
