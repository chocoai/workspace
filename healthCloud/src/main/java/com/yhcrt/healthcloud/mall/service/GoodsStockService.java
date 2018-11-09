package com.yhcrt.healthcloud.mall.service;	
	
import java.util.List;	
	
import com.yhcrt.healthcloud.mall.entity.GoodsStock;	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
public interface GoodsStockService {	
	
    Integer add(GoodsStock goodsStock);	
    Integer del(Integer cid);	
    Integer upd(GoodsStock goodsStock);	
    GoodsStock get(Integer cid);	
    List<GoodsStock> getAll();	
    	
}	
