package com.yhcrt.healthcloud.mall.service.impl;	
	
import java.util.HashMap;
import java.util.List;	
	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	
	
import com.yhcrt.healthcloud.mall.entity.Goods;	
import com.yhcrt.healthcloud.mall.mapper.GoodsMapper;	
import com.yhcrt.healthcloud.mall.service.GoodsService;	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
@Service	
public class GoodsServiceImpl implements GoodsService{	
	
    @Autowired	
    private GoodsMapper goodsMapper;	
    	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.GoodsService#add(com.yhcrt.healthcloud.mall.entity.Goods)	
     */	
    @Override	
    public Integer add(Goods goods) {	
        return goodsMapper.insert(goods);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.GoodsService#del(java.lang.String)	
     */	
    @Override	
    public Integer del(Integer cid) {	
        return goodsMapper.deleteByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.GoodsService#upd(com.yhcrt.healthcloud.mall.entity.Goods)	
     */	
    @Override	
    public Integer upd(Goods goods) {	
        return goodsMapper.updateByPrimaryKeySelective(goods);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.GoodsService#get(java.lang.String)	
     */	
    @Override	
    public Goods get(Integer cid) {	
        return goodsMapper.selectByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.GoodsService#getAll()	
     */	
    @Override	
    public List<Goods> getAll(HashMap<String, Object> params) {	
        return goodsMapper.search(params);  
    }	
	
}	
