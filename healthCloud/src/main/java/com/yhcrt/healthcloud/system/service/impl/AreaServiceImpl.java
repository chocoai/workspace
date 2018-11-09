package com.yhcrt.healthcloud.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.system.entity.Area;
import com.yhcrt.healthcloud.system.entity.AreaExample;
import com.yhcrt.healthcloud.system.mapper.AreaMapper;
import com.yhcrt.healthcloud.system.service.AreaService;

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
	
	

}
