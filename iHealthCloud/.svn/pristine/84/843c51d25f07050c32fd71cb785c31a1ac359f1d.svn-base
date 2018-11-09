package com.yhcrt.iHealthCloud.service.impl;	
	
import java.util.List;	

	






import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	

import com.yhcrt.iHealthCloud.entity.OrderDetail;
import com.yhcrt.iHealthCloud.entity.OrderDetailExample;
import com.yhcrt.iHealthCloud.mapper.OrderDetailMapper;
import com.yhcrt.iHealthCloud.service.OrderDetailService;
	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
@Service	
public class OrderDetailServiceImpl implements OrderDetailService{	
	
    @Autowired	
    private OrderDetailMapper orderDetailMapper;	
    	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.OrderDetailService#add(com.yhcrt.healthcloud.mall.entity.OrderDetail)	
     */	
    @Override	
    public Integer add(OrderDetail orderDetail) {	
        return orderDetailMapper.insert(orderDetail);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.OrderDetailService#del(java.lang.String)	
     */	
    @Override	
    public Integer del(Integer cid) {	
        return orderDetailMapper.deleteByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.OrderDetailService#upd(com.yhcrt.healthcloud.mall.entity.OrderDetail)	
     */	
    @Override	
    public Integer upd(OrderDetail orderDetail) {	
        return orderDetailMapper.updateByPrimaryKey(orderDetail);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.OrderDetailService#get(java.lang.String)	
     */	
    @Override	
    public OrderDetail get(Integer cid) {	
        return orderDetailMapper.selectByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.OrderDetailService#getAll()	
     */	
    @Override	
    public List<OrderDetail> getAll() {	
        return orderDetailMapper.selectByExample(null);	
    }

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.OrderDetailService#getDetailsByOrderId(java.lang.Integer)
     */
    @Override
    public List<OrderDetail> getDetailsByOrderId(Integer orderId) {
        OrderDetailExample example = new OrderDetailExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        return orderDetailMapper.selectByExample(example);
    }

	@Override
	public void setDetailStatus(Integer orderId,Integer orderStatus) {
		orderDetailMapper.setDetailStatus(orderId,orderStatus);
	}	
    
}	
