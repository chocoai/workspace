package com.whty.ebp.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.manage.model.Product;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface ProductService {
	/**
	 * 逻辑删除产品
	 * @param id
	 * @throws Exception
	 */
	void deleteById(String id) throws Exception;
	/**
	 * 修改产品数据
	 * @param p
	 * @throws Exception
	 */
	void updateProduct(Map<String, Object> param) throws Exception;
	/**
	 * 根据ID查询产品
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Product loadProductById(String productId) throws Exception;
	/**
	 * 新增产品
	 * @param product
	 * @throws Exception
	 */
	void addProduct(Product product) throws Exception;
	/**
	 * 查询产品列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	HandlerResult listProductByPage(Map<String, Object> param,PageContext page) throws Exception;
	
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
