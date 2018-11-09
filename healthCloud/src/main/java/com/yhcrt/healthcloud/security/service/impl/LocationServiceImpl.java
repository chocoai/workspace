package com.yhcrt.healthcloud.security.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.healthcloud.security.entity.Location;
import com.yhcrt.healthcloud.security.entity.LocationExample;
import com.yhcrt.healthcloud.security.mapper.LocationMapper;
import com.yhcrt.healthcloud.security.service.LocationService;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {
	@Autowired
	private LocationMapper locationMapper;
	
	@Override
	public long countByExample(LocationExample example) {
		return locationMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(LocationExample example) {
		return locationMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer locationId) {
		return locationMapper.deleteByPrimaryKey(locationId);
	}

	@Override
	public int insert(Location record) {
		return locationMapper.insert(record);
	}

	@Override
	public int insertSelective(Location record) {
		return locationMapper.insertSelective(record);
	}

	@Override
	public List<Location> selectByExample(LocationExample example) {
		return locationMapper.selectByExample(example);
	}

	@Override
	public List<Location> selectByPrimaryKey(String imei) {
		return locationMapper.selectByPrimaryKey(imei);
	}

	@Override
	public int updateByExampleSelective(Location record, LocationExample example) {
		return locationMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Location record, LocationExample example) {
		return locationMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Location record) {
		return locationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Location record) {
		return locationMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Location> getLocation(Map<String, Object> map) {
		List<Location> list = locationMapper.getLocation(map);
		if(!list.isEmpty() && list.size() > 0){
			for(Location location : list){
				if(location.getMemberDevice()!=null && location.getMemberDevice().getMember()!=null){
					if(StringUtils.isNotBlank(location.getMemberDevice().getMember().getRealName())){
						location.setName(location.getMemberDevice().getMember().getRealName());
					}else{
						if(StringUtils.isNotBlank(location.getMemberDevice().getMember().getNickName())){
							location.setName(location.getMemberDevice().getMember().getNickName());
						}else{
							location.setName("-");
						}
					}
				}else{
					location.setName("-");
				}
			}
		}
		return list;
	}

	@Override
	public List<Location> getOrbit(String imei,String locationTime) {
		return locationMapper.getOrbit(imei,locationTime);
	}

}
