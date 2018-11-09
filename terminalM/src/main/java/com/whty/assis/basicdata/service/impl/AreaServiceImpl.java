package com.whty.assis.basicdata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.AreaDao;
import com.whty.assis.basicdata.model.Area;
import com.whty.assis.basicdata.model.SchoolAreaTreeNode;
import com.whty.assis.basicdata.service.AreaService;
import com.whty.common.util.CacheUtils;

@Service
public class AreaServiceImpl implements AreaService {

	private static List<SchoolAreaTreeNode> schoolTreeNode;

	@Autowired
	private AreaDao areaDao;

	@Override
	public List<Map<String, Object>> listArea(Map<String, Object> param) {
		return areaDao.listArea(param);
	}

	@Override
	public List<Map<String, Object>> queryArea(Map<String, Object> paraMap) {
		return areaDao.queryArea(paraMap);
	}

	@Override
	public List<String> queryAreaNameByAreaCode(Map<String, Object> param) throws Exception {
		return areaDao.queryAreaNameByAreaCode(param);
	}

	@Override
	public List<Map<String, Object>> getTAreaByCity(Map<String, Object> areaMap) {
		return areaDao.getTAreaByCity(areaMap);
	}

	@Override
	public List<Area> getArea(Map<String, Object> param) {
		return areaDao.getArea(param);
	}

	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.AreaService#queryAreaTree()
	 */
	@Override
	public List<SchoolAreaTreeNode> queryAreaTree() {

		if (schoolTreeNode != null)
			return schoolTreeNode;

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

				Map<String, Object> cityParam = new HashMap<String, Object>();
				cityParam.put("levelId", 3);
				cityParam.put("parentId", city.get("area_code").toString());

				List<Map<String, Object>> areaList = areaDao.listArea(cityParam);
				List<SchoolAreaTreeNode> areaSubNodeList = new ArrayList<SchoolAreaTreeNode>();
				for (Map<String, Object> area : areaList) {
					SchoolAreaTreeNode areaTree = new SchoolAreaTreeNode();

					areaTree.setId(area.get("area_code").toString());
					areaTree.setName(area.get("area_name").toString());

					areaSubNodeList.add(areaTree);

				}
				cityTree.setSubNodeList(areaSubNodeList);
			}
			tree.setSubNodeList(citySubNodeList);
			;
			provinceTree.add(tree);
		}

		schoolTreeNode = provinceTree;
		
		return provinceTree;
	}

	/* 
	 * @Title: getAreaByLevelId
	 * @param levelId
	 * @return 
	 */ 
	@Override
	public List<Area> getAreaByLevelId(Integer levelId) {
		return areaDao.getAreaByLevelId(levelId);
	}

	/* 
	 * @Title: getAreaByParentId
	 * @param parentId
	 * @return 
	 */ 
	@Override
	public List<Area> getAreaByParentId(Integer parentId) {
		@SuppressWarnings("unchecked")
		List<Area> areas = (List<Area>) CacheUtils.get(parentId.toString());
		if(areas==null || areas.size()==0){
			areas = areaDao.getAreaByParentId(parentId);
			CacheUtils.put(parentId.toString(), areas);
			System.out.println("缓存中存");
			return areas;
		}
		System.out.println("缓存中拿");
		return areas;
	}

}
