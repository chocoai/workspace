/**
 * 
 */
package com.whty.assis.basicdata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.dao.DbViewDao;
import com.whty.assis.basicdata.model.Brand;
import com.whty.assis.basicdata.model.DbView;
import com.whty.assis.basicdata.service.BrandService;
import com.whty.assis.sysres.model.SysModular;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 品牌型号管理
 * 
 * @author zhangzheng
 * @date 2018年3月31日
 */
@Controller
@RequestMapping("/manage/brand")
public class BrandController extends BaseController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private DbViewDao dbViewDao;

	@RequestMapping(value = "/detailBrand")
	public void detailBrand(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String id = request.getParameter("id");
		Brand bread = brandService.loadById(Integer.valueOf(id));
		printJson(response, bread);
	}

	@RequestMapping(value = "/updateBrand")
	public void updateBrand(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		String brandName = request.getParameter("brandName");
		String modelName = request.getParameter("modelName");

		String oldModelName = request.getParameter("oldModelName");
		String oldBrandName = request.getParameter("oldBrandName");

		String vendor = request.getParameter("vendor");
		String id = request.getParameter("id");
		String memo = request.getParameter("memo");
		Map<String, Object> param = new HashMap<String, Object>();

		Brand brand = brandService.loadById(Integer.valueOf(id));
		brand.setId(Integer.valueOf(id));
		brand.setModelName(modelName);
		brand.setBrandName(brandName);
		brand.setVendor(vendor);
		brand.setMemo(memo);

		String result = "success";

		if (!brandName.equals(oldBrandName) || !modelName.equals(oldModelName)) {
			param.put("brandName", brand.getBrandName());
			param.put("modelName", brand.getModelName());
			param.put("terminalDeviceTypeId", brand.getTerminalDeviceTypeId());

			List<Brand> lst = brandService.listByCondition(param);

			if (lst != null && lst.size() > 0) {
				result = "已经存在该品牌型号";
				printText(response, result);
				return;
			}
		}

		brandService.updateBrand(brand);

		printText(response, result);
	}

	@RequestMapping(value = "/saveBrand")
	public void saveBrand(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		String result = brandService.saveBrand(request);
		printText(response, result);
	}

	@RequestMapping(value = "/deleteBrand")
	public void deleteBrand(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String id = request.getParameter("id");

		String result = brandService.deleteBrand(Integer.valueOf(id));
		printText(response, result);
	}

	@RequestMapping(value = "/querybrand")
	public void querybrand(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(name="pageValue",defaultValue="1")Integer page) throws Exception {
		Map<String, Object> paraMap = this.getParameterMap(request);

		String terminalDeviceTypeId = request.getParameter("terminalDeviceTypeId");

		String brandName = request.getParameter("brandName");
		String modelName = request.getParameter("modelName");
		if (terminalDeviceTypeId != null && !"".equals(terminalDeviceTypeId)) {
			paraMap.put("terminalDeviceTypeId", terminalDeviceTypeId);
		}

		if (brandName != null && !"".equals(brandName)) {
			paraMap.put("brandName", brandName);
		}

		if (modelName != null && !"".equals(modelName)) {
			paraMap.put("modelName", modelName);
		}
		PageHelper.startPage(page, 10);
		List<Brand> brands =brandService.listByCondition(paraMap);
		PageInfo<Brand> p = new PageInfo<Brand>(brands);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", brands);
		resultMap.put("pageNum", p.getPageNum());
		resultMap.put("pages", p.getPages());
		printJson(response, resultMap);
	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String terminalDeviceTypeId = request.getParameter("terminalDeviceTypeId");// 区域id

		if (terminalDeviceTypeId != null) {
			paramMap.put("terminalDeviceTypeId", terminalDeviceTypeId);
		}

		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		PageContext page = PageContext.getContext();

		if (null == currentPage || StringUtils.isNotEmpty(search_type)) {
			page.setCurrentPage(1);
			page.setPageSize(10);
			page.setTotalPage(0);
			page.setTotalRows(0);
		} else {
			page.setCurrentPage(Integer.parseInt(currentPage));
			page.setPageSize(Integer.parseInt(pageSize));
			page.setTotalPage(Integer.parseInt(totalPage));
		}
		page.setPagination(false);

		List<DbView> terminalDeviceTypeTree = dbViewDao.listTerminalDeviceTypeTree();

		page.setPagination(true);
		model.addAttribute("terminalDeviceTypeId", terminalDeviceTypeId);
		model.addAttribute("terminalDeviceTypeTree", terminalDeviceTypeTree);
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		return "brand.list";
	}

}
