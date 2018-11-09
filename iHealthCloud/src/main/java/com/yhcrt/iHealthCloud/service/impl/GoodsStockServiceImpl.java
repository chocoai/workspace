package com.yhcrt.iHealthCloud.service.impl;	
	
import java.util.List;	

	



import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	

import com.yhcrt.iHealthCloud.entity.GoodsStock;
import com.yhcrt.iHealthCloud.mapper.GoodsStockMapper;
import com.yhcrt.iHealthCloud.service.GoodsStockService;
		
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
@Service	
public class GoodsStockServiceImpl implements GoodsStockService{	
	
    @Autowired	
    private GoodsStockMapper goodsStockMapper;	
    	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.GoodsStockService#add(com.yhcrt.healthcloud.mall.entity.GoodsStock)	
     */	
    @Override	
    public Integer add(GoodsStock goodsStock) {	
        return goodsStockMapper.insert(goodsStock);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.GoodsStockService#del(java.lang.String)	
     */	
    @Override	
    public Integer del(Integer cid) {	
        return goodsStockMapper.deleteByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.GoodsStockService#upd(com.yhcrt.healthcloud.mall.entity.GoodsStock)	
     */	
    @Override	
    public Integer upd(GoodsStock goodsStock) {	
        return goodsStockMapper.updateByPrimaryKey(goodsStock);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.GoodsStockService#get(java.lang.String)	
     */	
    @Override	
    public GoodsStock get(Integer cid) {	
        return goodsStockMapper.selectByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.GoodsStockService#getAll()	
     */	
    @Override	
    public List<GoodsStock> getAll() {	
        return goodsStockMapper.selectByExample(null);	
    }	
	
}	
