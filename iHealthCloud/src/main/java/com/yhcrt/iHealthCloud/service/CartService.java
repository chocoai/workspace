package com.yhcrt.iHealthCloud.service;	
	
import java.util.List;	

import com.yhcrt.iHealthCloud.entity.Cart;

	
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
public interface CartService {	
	
    Integer add(Cart cart);	
    Integer del(Integer cid);	
    Integer upd(Cart cart);	
    Cart get(Integer cid);	
    List<Cart> getAll();	
    List<Cart> selectByMemberId(Integer memberId);
	List<Cart> validateCart(Integer memberId,Integer goodsId);
    	
}	