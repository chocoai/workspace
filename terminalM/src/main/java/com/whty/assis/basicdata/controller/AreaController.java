/**
 * 
 */
package com.whty.assis.basicdata.controller;

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

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.dao.AreaDao;
import com.whty.assis.basicdata.model.SchoolAreaTreeNode;

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
	 * 获取行政区域树
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/queryAreaTree")
	public void queryAreaTree(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map<String, Object> provinceParam = new HashMap<String, Object>();
		provinceParam.put("levelId", "1");

		List<Map<String, Object>> provinceList = areaDao.listArea(provinceParam);
		List<SchoolAreaTreeNode> provinceTree = new ArrayList<SchoolAreaTreeNode>();
		for (Map<String, Object> province : provinceList) {
			SchoolAreaTreeNode tree = new SchoolAreaTreeNode();

			tree.setId(province.get("area_code").toString());
			tree.setName(province.get("area_name").toString());

			Map<String, Object> areaParam = new HashMap<String, Object>();

			areaParam.put("levelId", 2);
			areaParam.put("parentId", province.get("area_code").toString());

			List<Map<String, Object>> cityList = areaDao.listArea(areaParam);
			List<SchoolAreaTreeNode> citySubNodeList = new ArrayList<SchoolAreaTreeNode>();
			for (Map<String, Object> city : cityList) {
				SchoolAreaTreeNode cityTree = new SchoolAreaTreeNode();

				cityTree.setId(city.get("area_code").toString());
				cityTree.setName(city.get("area_name").toString());
				
				citySubNodeList.add(cityTree);
				
				
				Map<String,Object> cityParam = new HashMap<String,Object>();
				cityParam.put("levelId", 3);
				cityParam.put("parentId", city.get("area_code").toString());
				
				List<Map<String,Object>> areaList = areaDao.listArea(cityParam);
				List<SchoolAreaTreeNode> areaSubNodeList = new ArrayList<SchoolAreaTreeNode>();
				for(Map<String,Object> area :areaList){
					SchoolAreaTreeNode areaTree = new SchoolAreaTreeNode();
					
					areaTree.setId(area.get("area_code").toString());
					areaTree.setName(area.get("area_name").toString());
					
					areaSubNodeList.add(areaTree);
					
				}
				cityTree.setSubNodeList(areaSubNodeList);
			}
			tree.setSubNodeList(citySubNodeList);;
			provinceTree.add(tree);
		}
		
		Map resultMap = new HashMap();
		resultMap.put("list", provinceTree);
		printJson(response, resultMap);
	}

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

		List<Map<String, Object>> list = areaDao.listArea(paraMap);

		Map resultMap = new HashMap();
		resultMap.put("list", list);
		printJson(response, resultMap);
	}

}
