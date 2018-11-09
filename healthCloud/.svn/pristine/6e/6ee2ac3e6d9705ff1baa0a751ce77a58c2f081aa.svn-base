package com.yhcrt.healthcloud.mall.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.mall.entity.Goods;
import com.yhcrt.healthcloud.mall.entity.YwImage;
import com.yhcrt.healthcloud.mall.service.GoodsService;
import com.yhcrt.healthcloud.mall.service.YwImageService;
import com.yhcrt.healthcloud.system.entity.SysDictionary;
import com.yhcrt.healthcloud.system.entity.SysDictionaryExample;
import com.yhcrt.healthcloud.system.mapper.SysDictionaryMapper;
import com.yhcrt.healthcloud.system.service.SysSequenceService;
import com.yhcrt.healthcloud.util.UploadUtils;

/* @Description: 商品管理模块	
 * @version 1.0     2017年8月28日	
 * @author jimmy	
 */
@RequestMapping("/goods")
@Controller
public class GoodsController extends BaseController {

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private YwImageService ywImageService;
	@Autowired
	private SysSequenceService sysSequenceService;
	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;

	/**
	 * @Title: getGoodsList
	 * @Description: 根据条件查询商品，返回列表页
	 * @param request
	 * @param response
	 * @param goodsName
	 * @param goodsCategory
	 * @param goodsStatus
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getGoodsList(HttpServletRequest request, HttpServletResponse response, Goods goods) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("goodsName", goods.getGoodsName());
		params.put("goodsCategory", goods.getGoodsCategory());
		params.put("goodsType", goods.getGoodsType());
		params.put("goodsStatus", goods.getStatus());
		List<Goods> goodsList = goodsService.getAll(params);
		request.setAttribute("goodsList", goodsList);
		if(StringUtils.isNoneBlank(goods.getGoodsCategory())){
			request.setAttribute("goodsTypeList", getTypeItem(goods.getGoodsCategory()));
		}
		request.setAttribute("goods", goods);
		return "mall/goods/list";
	}

	/**
	 * @Title: editGoods
	 * @Description: 调到编辑商品信息页面，如果goodsId为null，则是新增操作，携带id返回到编辑页面 否则从数据库中查询出商品信息及商品关联的主图，携带这些信息返回到编辑页面
	 * @param request
	 * @param response
	 * @param goodsId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editGoods(HttpServletRequest request, HttpServletResponse response, Integer goodsId) {
		// 判断进入界面
		if (null == goodsId) {
			goodsId = sysSequenceService.getSequenceValue(Constants.SequenceName.GOODS);
			request.setAttribute("goodsId", goodsId);
			request.setAttribute("goods", new Goods());
		} else {
			List<YwImage> imgList = ywImageService.getByRefId(goodsId, "goods");
			request.setAttribute("imgList", imgList);
			Goods goods = goodsService.get(goodsId);
			request.setAttribute("goodsTypeList", getTypeItem(goods.getGoodsCategory()));
			request.setAttribute("goods", goods);
		}
		return "mall/goods/edit";
	}

	/**
	 * @Title: saveGoods
	 * @Description: 根据editType参数来决定请求是新增还是修改
	 * @param request
	 * @param response
	 * @param attr
	 * @param goods
	 * @param titleImgFile
	 * @param editType
	 * @return
	 */
	@RequestMapping("/save")
	public String saveGoods(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr, Goods goods,
			String editType) {
		String titleImg = UploadUtils.uploadFile(request, "titleImgFile", "goods");
		if (StringUtils.isNoneBlank(titleImg)) {
			goods.setTitleImg(titleImg);
		}
		goods.setStatus(Constants.STATUS_NORMAL);
		goods.setUpdateTime(sdf.format(new Date()));
		Integer i = 0;
		if ("edit".equals(editType)) {
			// 这里的upd调用的是updateByPrimaryKeySelective方法，为null的字段不会更新
			i = goodsService.upd(goods);
		} else if ("new".equals(editType)) {
			goods.setCreateTime(sdf.format(new Date()));
			goods.setCreateUser(getLoginUser().getUserId().toString());
			i = goodsService.add(goods);
		}
		if (i == 1) {
			attr.addFlashAttribute("state", "SUCCESS");
		} else {
			attr.addFlashAttribute("state", "FALSE");
		}
		return "redirect:list";

	}

	/**
	 * @Title: changeServiceStatus
	 * @Description: 对商品的上架下架操作，具体是上架还是下架由前台传入的值决定
	 * @param request
	 * @param response
	 * @param attr
	 * @param goodsId
	 * @param goodsStatus
	 */
	@RequestMapping(value = "changeGoodsStatus", method = RequestMethod.POST)
	@ResponseBody
	public void changeServiceStatus(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr,
			Integer goodsId, Integer goodsStatus) {

		Goods goods = goodsService.get(goodsId);
		goods.setStatus(goodsStatus);
		goods.setUpdateTime(sdf.format(new Date()));
		goodsService.upd(goods);

	}

	/**
	 * @Title: batchChangeGoodsStatus
	 * @Description: 对商品的批量下架操作
	 * @param request
	 * @param response
	 * @param attr
	 * @param goodsIds
	 */
	@RequestMapping(value = "batchChangeGoodsStatus", method = RequestMethod.POST)
	@ResponseBody
	public void batchChangeGoodsStatus(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr,
			Integer[] goodsIds) {

		for (Integer goodsId : goodsIds) {
			Goods goods = goodsService.get(goodsId);
			goods.setStatus(Constants.STATUS_DISABLE);
			goods.setUpdateTime(sdf.format(new Date()));
			goodsService.upd(goods);
		}

	}

	// 二级联动下拉查询
	private List<SysDictionary> getTypeItem(String goodsCategory) {
		SysDictionaryExample example = new SysDictionaryExample();
		example.createCriteria().andDictEnNameEqualTo("goods_category").andDictValueEqualTo(goodsCategory).andStatusEqualTo(0);
		List<SysDictionary> list = sysDictionaryMapper.selectByExample(example);
		List<SysDictionary> list2 = new ArrayList<SysDictionary>();
		if (!list.isEmpty() && list.size() > 0) {
			SysDictionary sysDictionary = list.get(0);
			example = new SysDictionaryExample();
			example.createCriteria().andParentIdEqualTo(String.valueOf(sysDictionary.getDictId())).andStatusEqualTo(0);
			list2 = sysDictionaryMapper.selectByExample(example);
		}
		return list2;
	}

}
