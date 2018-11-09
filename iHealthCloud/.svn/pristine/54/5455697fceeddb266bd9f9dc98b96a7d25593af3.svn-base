package com.yhcrt.iHealthCloud.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Goods;
import com.yhcrt.iHealthCloud.entity.GoodsExample;
import com.yhcrt.iHealthCloud.entity.GoodsExample.Criteria;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.SysDictionary;
import com.yhcrt.iHealthCloud.entity.SysDictionaryExample;
import com.yhcrt.iHealthCloud.entity.UserComment;
import com.yhcrt.iHealthCloud.entity.UserCommentExample;
import com.yhcrt.iHealthCloud.entity.YwImageExample;
import com.yhcrt.iHealthCloud.mapper.GoodsMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.SysDictionaryMapper;
import com.yhcrt.iHealthCloud.mapper.UserCommentMapper;
import com.yhcrt.iHealthCloud.mapper.YwImageMapper;
import com.yhcrt.iHealthCloud.service.GoodsService;
import com.yhcrt.iHealthCloud.util.Arith;
import com.yhcrt.iHealthCloud.util.Const;

/**
 * @Description:
 * @version 1.0 2017年9月7日
 * @author jimmy
 */
@Service
public class GoodsServiceImpl extends BaseService implements GoodsService {
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private YwImageMapper ywImageMapper;

	@Autowired
	private UserCommentMapper userCommentMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private SysDictionaryMapper sysDictMappper;

	@Override
	public Integer add(Goods goods) {
		return goodsMapper.insert(goods);
	}

	@Override
	public Integer del(Integer cid) {
		return goodsMapper.deleteByPrimaryKey(cid);
	}

	@Override
	public Integer upd(Goods goods) {
		return goodsMapper.updateByPrimaryKeySelective(goods);
	}

	@Override
	public Goods get(Integer cid) {
		return goodsMapper.selectByPrimaryKey(cid);
	}

	@Override
	public List<Goods> getAll(HashMap<String, Object> params) {
		return goodsMapper.search(params);
	}

	@Override
	public String getDiscountedGoods(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String currentPage = biz.getString("current_page");
		String pageSize = biz.getString("page_size");
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, currentPage,pageSize)) {
			// 向数据库请求数据并判断是否分页
			List<Goods> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = selectGoodsFromDiscounted();
				PageInfo<Goods> pageInfo = new PageInfo<>(list);
				setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
			} else {
				list = selectGoodsFromDiscounted();
			}
			pdataObj.put(Const.TAG_BIZ, list);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return pdataObj.toJSONString();
	}

	@Override
	public String getGoodsDetail(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String goodsid = biz.getString("goods_id");
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, goodsid)) {
			// 向数据库请求数据并判断是否分页
			Goods goods = goodsMapper.selectByPrimaryKey(Integer.valueOf(goodsid));
			if (goods != null) {
				JSONObject goodsjson = (JSONObject) JSON.toJSON(goods);
				Double discount = goods.getDiscount();
				if(discount==null || discount == 0 ){
					discount = 1d;
				}
				goodsjson.put("discountPrice", Arith.round(Arith.mul(goods.getUnitPrice(), discount), 2));
				YwImageExample example = new YwImageExample();
				example.createCriteria().andRefIdEqualTo(goods.getGoodsId()).andModuleCodeEqualTo(Constants.GOODS);
				goodsjson.put("images", ywImageMapper.selectByExample(example));
				UserCommentExample commentExample = new UserCommentExample();
				commentExample.createCriteria().andRefIdEqualTo(goods.getGoodsId()).andCommentTypeEqualTo(Constants.GOODS);
				List<UserComment> commentListAll = userCommentMapper.selectByExample(commentExample);
				JSONArray commentJsonList = new JSONArray();
				for(UserComment userComment : commentListAll){
					JSONObject userCommentJson = (JSONObject) JSON.toJSON(userComment);
					Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(userComment.getMemberId()));
					userCommentJson.put("userLogo", member.getHeadPic());
					userCommentJson.put("nickName", member.getNickName());
					if(commentJsonList.size()<2){
						commentJsonList.add(userCommentJson);
					}
				}
				goodsjson.put("tel", "18627899132");
				goodsjson.put("comments", commentJsonList);
				goodsjson.put("moreComments", commentListAll.size());
				pdataObj.put(Const.TAG_BIZ, goodsjson);
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "获取成功！");
			} else {
				requestFailed(pdataObj, "不存在此商品");
			}
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return pdataObj.toJSONString();
	}

	private List<Goods> selectGoodsFromDiscounted() {
		GoodsExample goodsExample = new GoodsExample();
		goodsExample.createCriteria().andDiscountIsNotNull().andDiscountLessThan(1.0)
				.andStatusEqualTo(Constants.STATUS_NORMAL);
		List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
		return goodsList;
	}

	@Override
	public String getGoodsList(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String first = bizObj.getString("first");
		String second = bizObj.getString("second");
		String orderNum = bizObj.getString("order_by");
		String currentPage = bizObj.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = bizObj.getString(Const.TAG_PAGE_SIZE); 
		String orderBy = null;
		if (orderNum.equals("1")) {
			// 价格升序
			orderBy = "unit_price";
		} else if (orderNum.equals("2")) {
			// 价格降序
			orderBy = "unit_price Desc";
		}else{
			// 热度降序
			orderBy = "iext2 Desc";
		}
		try {
			PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
			List<Goods> goods = goodsService.selectByExample(first,second,orderBy);
			PageInfo<Goods> pageInfo = new PageInfo<Goods>(goods);
			setPagingData(jsonObject, pageInfo.getPages(), pageInfo.getPageNum());
			requestSucceed(jsonObject, goods, "");
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String getGoodsFilterItem(JSONObject jsonObject) {
		
		SysDictionaryExample example = new SysDictionaryExample();
		// 商品类别
		example.createCriteria().andDictEnNameEqualTo("goods_category");
		List<SysDictionary> goodsCategorys = sysDictMappper.selectByExample(example);
        
		JSONArray array = new JSONArray();
        for(SysDictionary sysDictionary : goodsCategorys){
			JSONObject json = new JSONObject();
			json.put("cid", sysDictionary.getDictId());
			String name = sysDictionary.getDictKey();
			json.put("name", name);
			json.put("shortName", com.yhcrt.iHealthCloud.util.StringUtil.overSubstr(name, 4));
			json.put("value", sysDictionary.getDictValue());
          	array.add(json);
        }
		
        JSONObject jsonObj = new JSONObject();
		jsonObj.put("goodsCategorys", array);
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		jsonObject.put(Const.TAG_BIZ, jsonObj);
		jsonObject.put(Const.TAG_RMK, "获取成功");
		
		return toJsonStringWithOutNull(jsonObject);
	}
	
	@Override
	public List<Goods> selectByExample(String first,String second,String OrderBy) {
		GoodsExample example = new GoodsExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(0);//查询商品状态为0（上架）
		if (StringUtil.isNotEmpty(first)) {
			criteria.andGoodsCategoryEqualTo(first);
		}
		if (StringUtil.isNotEmpty(second)) {
			criteria.andGoodsTypeEqualTo(second);
		}
		example.setOrderByClause(OrderBy);
		return goodsMapper.selectByExample(example);
	}

	@Override
	public List<Goods> selectBySearchText(String searchText) {
		GoodsExample example = new GoodsExample();
		example.setOrderByClause("create_time desc");
		example.createCriteria().andGoodsNameLike("%"+searchText+"%");
		example.or().andGoodsCategoryLike("%"+searchText+"%");
		return goodsMapper.selectByExample(example);
	}
	
}
