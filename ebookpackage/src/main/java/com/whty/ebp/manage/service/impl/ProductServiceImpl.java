package com.whty.ebp.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.ebp.manage.dao.ProductDao;
import com.whty.ebp.manage.model.Product;
import com.whty.ebp.manage.service.ProductService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	public HandlerResult listProductByPage(Map<String, Object> param,
			PageContext page) throws Exception {
		HandlerResult re = new HandlerResult();
		@SuppressWarnings("unchecked")
		List<Product> productList = productDao.listByCondition(param);
		re.setResultList(productList);
		re.setPage(page);
		return re;
	}
	
	public List<Map> queryAllProduct(){
		return productDao.queryAllProduct();
	}
	
	public List<Map> queryProductByType(Map paramap){
		return productDao.queryProductByType(paramap);
	}

	public void addProduct(Product product) throws Exception {
		productDao.save(product);
	}

	public Product loadProductById(String productId) throws Exception {
		// TODO Auto-generated method stub
		return productDao.loadById(productId);
	}

	public void updateProduct(Map<String, Object> param) throws Exception {
		productDao.updateByCondition(param);
	}

	public void deleteById(String id) throws Exception {
		productDao.deleteById(id);
	}

}
