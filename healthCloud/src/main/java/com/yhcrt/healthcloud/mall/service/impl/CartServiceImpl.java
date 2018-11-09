package com.yhcrt.healthcloud.mall.service.impl;	
	
import java.util.List;	
	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;	
	
import com.yhcrt.healthcloud.mall.entity.Cart;	
import com.yhcrt.healthcloud.mall.mapper.CartMapper;	
import com.yhcrt.healthcloud.mall.service.CartService;	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
@Service	
public class CartServiceImpl implements CartService{	
	
    @Autowired	
    private CartMapper cartMapper;	
    	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.CartService#add(com.yhcrt.healthcloud.mall.entity.Cart)	
     */	
    @Override	
    public Integer add(Cart cart) {	
        return cartMapper.insert(cart);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.CartService#del(java.lang.String)	
     */	
    @Override	
    public Integer del(Integer cid) {	
        return cartMapper.deleteByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.CartService#upd(com.yhcrt.healthcloud.mall.entity.Cart)	
     */	
    @Override	
    public Integer upd(Cart cart) {	
        return cartMapper.updateByPrimaryKey(cart);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.CartService#get(java.lang.String)	
     */	
    @Override	
    public Cart get(Integer cid) {	
        return cartMapper.selectByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.CartService#getAll()	
     */	
    @Override	
    public List<Cart> getAll() {	
        return cartMapper.selectByExample(null);	
    }	
	
}	
