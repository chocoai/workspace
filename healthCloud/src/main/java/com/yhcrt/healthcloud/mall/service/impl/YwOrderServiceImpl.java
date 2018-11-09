package com.yhcrt.healthcloud.mall.service.impl;	
	
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.common.Constants.OrderType;
import com.yhcrt.healthcloud.mall.entity.Goods;
import com.yhcrt.healthcloud.mall.entity.OrderDetail;
import com.yhcrt.healthcloud.mall.entity.OrderRefund;
import com.yhcrt.healthcloud.mall.entity.YwOrder;
import com.yhcrt.healthcloud.mall.mapper.GoodsMapper;
import com.yhcrt.healthcloud.mall.mapper.OrderDetailMapper;
import com.yhcrt.healthcloud.mall.mapper.OrderRefundMapper;
import com.yhcrt.healthcloud.mall.mapper.YwOrderMapper;
import com.yhcrt.healthcloud.mall.service.YwOrderService;
import com.yhcrt.healthcloud.util.DateUtil;
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
@Service	
public class YwOrderServiceImpl implements YwOrderService{	
	
    @Autowired	
    private YwOrderMapper ywOrderMapper;
    @Autowired	
    private OrderDetailMapper orderDetailMapper;
    @Autowired	
    private OrderRefundMapper orderRefundMapper;
    @Autowired	
    private GoodsMapper goodsMapper;
    	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.YwOrderService#add(com.yhcrt.healthcloud.mall.entity.YwOrder)	
     */	
    @Override	
    public Integer add(YwOrder ywOrder) {	
        return ywOrderMapper.insert(ywOrder);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.YwOrderService#del(java.lang.String)	
     */	
    @Override	
    public Integer del(Integer cid) {	
        return ywOrderMapper.deleteByPrimaryKey(cid);	
    }	
	
    // 修改订单和明细订单状态
    @Override	
    public Integer update(YwOrder ywOrder) {
    	OrderDetail orderDetail = new OrderDetail();
    	orderDetail.setOrderId(ywOrder.getOrderId());
    	orderDetail.setStatus(ywOrder.getOrderStatus());
    	orderDetailMapper.updateByOrderId(orderDetail);
        return ywOrderMapper.updateByPrimaryKey(ywOrder);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.YwOrderService#get(java.lang.String)	
     */	
    @Override	
    public YwOrder get(Integer cid) {	
        return ywOrderMapper.selectByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.YwOrderService#getAll()	
     */	
    @Override	
    public List<YwOrder> getAll(Map<String, Object> params) {
        return ywOrderMapper.search(params);    
    }

    //根据orderId修改订单状态
	@Override
	public void updateByStatus(YwOrder order) {
		OrderDetail orderDetail = new OrderDetail();
    	orderDetail.setOrderId(order.getOrderId());
    	orderDetail.setStatus(order.getOrderStatus());
    	orderDetailMapper.updateByOrderId(orderDetail);
		ywOrderMapper.updateByStatus(order);
	}

	@Override
	public Map<String, String> updateByDetailCid(String cid, String status) {
		Map<String, String> resMap = new HashMap<String, String>();
		//根据cid查询明细表记录
		OrderDetail od = orderDetailMapper.selectByPrimaryKey(Integer.parseInt(cid));
		od.setStatus(Integer.parseInt(status));
		
		//根据cid查询退款表中数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", Integer.parseInt(cid));
		map.put("type", "goods");
		OrderRefund refund = orderRefundMapper.queryByMap(map);
		refund.setRefundTime(DateUtil.getDateTime());
		refund.setStatus(Integer.parseInt(status));
		
		//根据明细表中的订单号查询主订单信息
		YwOrder yo = ywOrderMapper.selectByPrimaryKey(od.getOrderId());
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("payType", yo.getPayType());
		param.put("outTradeNo", yo.getCext2());
		param.put("refundMoney", refund.getRefundMoney().toString());
		param.put("totalAmount", yo.getActualPayment().toString());	//微信退款总金额 
		if("6".equals(status)){	//确认退款
			resMap = new OrderRefundServiceImpl().refund(param);
			if("SUCCESS".equals(resMap.get("state"))){
				try {
					orderDetailMapper.updateByCid(od);
					orderRefundMapper.updateById(refund);
					//商品退款库存+1
					Goods goods = goodsMapper.selectByPrimaryKey(od.getGoodsId());
					goods.setStockAmount(goods.getStockAmount()+od.getAmount());
					goodsMapper.updateByPrimaryKey(goods);
					
					//查询是否还有其他子订单在进行中,如果有不修改订单状态,如果没有修改订单状态为已完成
					int count = orderDetailMapper.countByBean(od);
					if(count == 0){ //表示已经没有明细订单了在进行中
						yo.setOrderStatus(OrderType.ORDER_DONE);
						ywOrderMapper.updateByStatus(yo);
					}
				} catch (Exception e) {
					e.printStackTrace();
					resMap.put("state", "FALSE");
					resMap.put("msg", "拒绝退款修改数据失败");
				}
				
			}
		}else{	//拒绝退款
			try {
				orderDetailMapper.updateByCid(od);
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
	
}	
