package com.whty.assis.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bigdata.assis.manage.dao.TestDao;
import com.bigdata.assis.manage.model.Test;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.model.Demo;
import com.whty.assis.demo.service.DemoService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 示例Controller
 * 
 * @author shijiapeng
 * @date 2015年8月11日 下午3:21:12
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {

	@Autowired
	private DemoService demoService;

	@Autowired
	private TestDao testDao;

	@RequestMapping(value = "/test")
	public void test(Model model) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		List<Test> list = testDao.listByCondition(param);

		for (Test bean : list) {
			System.out.println(bean.getProvCode());
		}

		com.whty.oraclepage.PageContext page = com.whty.oraclepage.PageContext.getContext();

		page.setPagination(true);

		HandlerResult rs = new HandlerResult();
		rs.setResultList(testDao.listByCondition(param));

		System.out.println(1);
	}

	/**
	 * demo列表页数据加载
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/demoList")
	public String demoList(Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("demo列表页数据加载");
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		// paramMap.put("createrName", "createrName");
		List<Demo> demoList = demoService.listDemo(paramMap);

		model.addAttribute("demoList", demoList);

		return "demo/demoList";
	}

	/**
	 * demo列表页数据加载
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/demoListPage")
	public String demoListPage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("demo列表页数据加载");
		}
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("createrName", null);
		PageContext page = PageContext.getContext();
		// 请自行验证
		if (null == currentPage) {
			page.setCurrentPage(1);
			page.setPageSize(10);
		} else {
			page.setCurrentPage(Integer.parseInt(currentPage));
			page.setPageSize(Integer.parseInt(pageSize));
			page.setTotalPage(Integer.parseInt(totalPage));
		}
		page.setPagination(true);
		HandlerResult handlerResult = demoService.listByConditionListPage(paramMap);
		model.addAttribute("demoList", handlerResult.getResultList());
		model.addAttribute("page", page);
		return "demo/demoListPage";
	}
}
