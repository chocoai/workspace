package com.whty.ebp.manage.dao;


import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.manage.model.Product;

public interface ProductDao extends IBaseDao<Product>{
	
	/**
	 * 查询产品列表用于发送消息
	 * @return
	 */
	public List<Map> queryAllProduct();
	
	/**
	 * 根据产品类型查询列表
	 * @return
	 */
	public List<Map> queryProductByType(Map paramap);
}
