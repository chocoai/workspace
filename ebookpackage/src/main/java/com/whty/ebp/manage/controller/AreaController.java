/**
 * 
 */
package com.whty.ebp.manage.controller;

import java.util.ArrayList;
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

import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.dao.AreaDao;

/**
 * @author zhangzheng
 * @date 2018年4月2日
 */
@Controller
@RequestMapping(value = "/manage/area")
public class AreaController extends BaseController {

	@Autowired
	private AreaDao areaDao;

	

	/**
	 * 查询行政区数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/queryArea")
	public void queryArea(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String parentId = request.getParameter("parentId");
		String levelId = request.getParameter("levelId");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(parentId)) {
			paraMap.put("parentId", parentId);
		}
		if (StringUtils.isNotEmpty(levelId)) {
			paraMap.put("levelId", levelId);
		}

		List<Map<String, Object>> list = areaDao.queryArea(paraMap);

		Map resultMap = new HashMap();
		resultMap.put("list", list);
		printJson(response, resultMap);
	}

}
