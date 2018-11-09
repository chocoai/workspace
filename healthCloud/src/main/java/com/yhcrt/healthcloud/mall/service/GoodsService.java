package com.yhcrt.healthcloud.mall.service;	
	
import java.util.HashMap;
import java.util.List;	
	
import com.yhcrt.healthcloud.mall.entity.Goods;	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
public interface GoodsService {	
	
    Integer add(Goods goods);	
    Integer del(Integer cid);	
    Integer upd(Goods goods);	
    Goods get(Integer cid);	
    List<Goods> getAll(HashMap<String, Object> params);	
    	
}	
