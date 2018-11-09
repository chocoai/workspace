package com.whty.assis.manage.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.manage.model.BaseProperty;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.cache.data.CacheDataUtils;
import com.whty.common.cache.tools.CacheContainer;
import com.whty.common.util.HttpUtils;

/*
 * 缓存管理
 */
@Controller
@RequestMapping("/manage/cache")
public class CacheController extends BaseController {
	// private static Logger logger = Logger.getLogger(CacheController.class);
	@Autowired
	private BasePropertyService basePropertyService;

	/*
	 * 缓存管理页面
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		logger.info("缓存管理页面");
		return "cache/list";
	}

	/*
	 * 清空所有缓存，同时加入启动时的缓存
	 */
	@RequestMapping("/reload")
	public String reload(HttpServletRequest request, Model model) {
		logger.info("清空所有缓存");
		// CacheContainer.getCaches().clear();
		// //加载参数配置缓存
		// addBasePropertyCache();
		try {
			String url167 = CacheDataUtils.getData("BaseProperty", "reload_cache_167_888888");
			String url168 = CacheDataUtils.getData("BaseProperty", "reload_cache_168_888888");
			if (null != url167 && !url167.trim().equals("")) {
				HttpUtils.doGet(url167);
			}
			if (null != url168 && !url168.trim().equals("")) {
				HttpUtils.doGet(url168);
			}
			model.addAttribute("msg", "重置成功");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			model.addAttribute("msg", "重置失败");
		} catch (IOException e) {
			model.addAttribute("msg", "重置失败");
			e.printStackTrace();
		} catch (Exception e) {
			model.addAttribute("msg", "重置失败");
			e.printStackTrace();
		}
		return "cache/list";
	}

	/**
	 * 加载参数配置表缓存数据
	 */
	public void addBasePropertyCache() {
		logger.info("加载参数配置表缓存数据");
		// 查询数据库里的数据
		@SuppressWarnings("rawtypes")
		List<BaseProperty> list = basePropertyService.queryBasePropertyList(new HashMap());
		try {
			for (BaseProperty bp : list) {
				CacheDataUtils.setData("BaseProperty", bp.getProperty_key() + "_" + bp.getPlatform_code(),
						bp.getProperty_value());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 清空Cms接口数据缓存
	 */
	@RequestMapping("/clearCms")
	public String clearCms(HttpServletRequest request, Model model) {
		logger.info("清空Cms接口数据缓存");
		try {
			CacheContainer.getCache("cms-gateway").flushAll();
			model.addAttribute("msg", "清空Cms接口数据成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "cache/list";
	}

	/*
	 * 重新加载参数配置数据
	 */
	@RequestMapping("/reloadBaseProperty")
	public String reloadBaseProperty(HttpServletRequest request, Model model) {
		logger.info("重新加载参数配置数据");
		try {
			CacheContainer.getCache("BaseProperty").flushAll();
			addBasePropertyCache();
			model.addAttribute("msg", "重置参数配置数据成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "cache/list";
	}

}
