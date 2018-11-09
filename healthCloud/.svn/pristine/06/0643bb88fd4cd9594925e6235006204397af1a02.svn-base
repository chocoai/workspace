package com.yhcrt.healthcloud.mall.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.mall.entity.OrderRefund;
import com.yhcrt.healthcloud.mall.entity.WorkOrder;
import com.yhcrt.healthcloud.mall.mapper.OrderRefundMapper;
import com.yhcrt.healthcloud.mall.mapper.WorkOrderMapper;
import com.yhcrt.healthcloud.mall.service.WorkOrderService;
import com.yhcrt.healthcloud.util.DateUtil;

/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
 */
@Service
public class WorkOrderServiceImpl implements WorkOrderService {

	@Autowired
	private WorkOrderMapper workOrderMapper;
	@Autowired
	private OrderRefundMapper orderRefundMapper;

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
	public Integer toDoWorkNum(HashMap<String, Object> params) {
		return workOrderMapper.getToDoWorkNum(params);
	}

	@Override
	public Integer toDoGoodsNum() {
		return workOrderMapper.getToDoGoodsNum();
	}

	// 服务工单确认是否退款
	@Override
	public Map<String, String> update(OrderRefund refund) {
		Map<String, String> resMap = new HashMap<String, String>();
		String time = DateUtil.getDateTime();
		refund.setRefundTime(time);
		WorkOrder wo = workOrderMapper.selectByPrimaryKey(refund.getRefId());
		wo.setOrderStatus(refund.getStatus());
		wo.setHandleTime(time);

		Map<String, String> param = new HashMap<String, String>();
		param.put("payType", wo.getPayType());
		param.put("outTradeNo", wo.getCext2());
		param.put("refundMoney", refund.getRefundMoney().toString());
		param.put("totalAmount", wo.getActualPayment().toString()); // 微信退款总金额
		if (refund.getStatus() == 6) { // 确认退款
			resMap = new OrderRefundServiceImpl().refund(param);
			if ("SUCCESS".equals(resMap.get("state"))) {
				try {
					workOrderMapper.updateById(wo);
					orderRefundMapper.updateById(refund);
				} catch (Exception e) {
					e.printStackTrace();
					resMap.put("state", "FALSE");
					resMap.put("msg", "退款成功,修改数据失败");
				}
			}
		} else { // 拒绝退款
			try {
				workOrderMapper.updateById(wo);
				orderRefundMapper.updateById(refund);
				resMap.put("state", "SUCCESS");
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("state", "FALSE");
				resMap.put("msg", "拒绝退款修改数据失败");
			}
		}
		return resMap;
	}

	// 根据订单id查询信息
	@Override
	public WorkOrder queryByOrderId(Integer orderId) {
		return workOrderMapper.queryByOrderId(orderId);
	}

}
