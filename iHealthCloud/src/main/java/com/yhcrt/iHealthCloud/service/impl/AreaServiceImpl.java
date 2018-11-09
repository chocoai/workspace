package com.yhcrt.iHealthCloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Area;
import com.yhcrt.iHealthCloud.entity.AreaExample;
import com.yhcrt.iHealthCloud.mapper.AreaMapper;
import com.yhcrt.iHealthCloud.service.AreaService;
import com.yhcrt.iHealthCloud.util.Const;


@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaMapper areaMapper;

	@Override
	public List<Area> getAllArea() {
		AreaExample example = new AreaExample();
		example.createCriteria().andStatusEqualTo(Constants.STATUS_NORMAL);
		return areaMapper.selectByExample(example);
	}

	@Override
	public Area getAreaRootNode() {
		return areaMapper.getAreaRootNode();
	}

	@Override
	public List<Area> getChildAreaByParentId(Integer parentId) {
		return areaMapper.getChildAreaByParentId(parentId);
	}

	@Override
	public Area getAreaById(Integer areaId) {
		return areaMapper.selectByPrimaryKey(areaId);
	}

	@Override
	public int update(Area area) {
		return areaMapper.updateByPrimaryKey(area);
	}

	@Override
	public int insert(Area area) {
		return areaMapper.insert(area);
	}

	@Override
	public String getAreaFullName(Integer parentAreaId, String areaName) {
		Area parentArea = getAreaById(parentAreaId);
		if (parentArea != null && parentArea.getParentId() != null) {
			String fullName = getAreaFullName(parentArea.getParentId(), parentArea.getAreaName()) + areaName;
			return fullName;
		}
		return areaName;
	}
	
	@Override
	public String getAreaCode(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String cityname = bizObj.getString("city_name");
		try {
			if(StringUtils.isEmpty(cityname)){
				throw new Exception("城市名称为空！");
			}
            List<Area> areaList = areaMapper.getAreaByName(cityname);
            String areaCode ="";
            if (areaList.size()==0) {
				throw new Exception("不存城市名称为"+cityname+"的编码");
            }else{
            	areaCode = areaList.get(0).getAreaCode();
            }
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功");
			pdataObj.put(Const.TAG_BIZ, areaCode);
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	

}
