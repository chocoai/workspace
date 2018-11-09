package com.yhcrt.iHealthCloud.service;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.Goods;

/**
 * @Description:
 * @version 1.0 2017年9月7日
 * @author jimmy
 */
public interface GoodsService {

	Integer add(Goods goods);

	Integer del(Integer cid);

	Integer upd(Goods goods);

	Goods get(Integer cid);

	List<Goods> getAll(HashMap<String, Object> params);

	String getDiscountedGoods(JSONObject pdata);

	String getGoodsDetail(JSONObject pdata);
	
	/**
	 * 根据筛选条件查询服务列表
	 * @param jsonObject
	 * @return
	 */
	String getGoodsList(JSONObject jsonObject);
	
	/**
	 * 获取商品筛选接口
	 * @param jsonObject
	 * @return
	 */
	String getGoodsFilterItem(JSONObject jsonObject);
	
	
	public List<Goods> selectByExample(String first,String second,String OrderBy);
	/**
	 * 
	 * @Description: 商品搜索
	 * @param searchText
	 * @return List<Goods>
	 */
	List<Goods> selectBySearchText(String searchText);
}
