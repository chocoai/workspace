package com.yhcrt.healthcloud.device.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.device.entity.MemberDeviceVideo;
import com.yhcrt.healthcloud.device.mapper.MemberDeviceVideoMapper;
import com.yhcrt.healthcloud.device.service.DeviceVideoService;

@Service
public class DeviceVideoServiceImpl implements DeviceVideoService {

	@Autowired
	private MemberDeviceVideoMapper deviceVideoMapper;

	// 查询设备视频数据
	@Override
	public List<MemberDeviceVideo> queryPageList(Map<String, Object> args) {
		return deviceVideoMapper.queryPageList(args);
	}

	// 根据cid查询数据
	@Override
	public MemberDeviceVideo queryByCid(Integer cid) {
		return deviceVideoMapper.queryByCid(cid);
	}

	// 新增数据
	@Override
	public Integer insert(MemberDeviceVideo video) {
		return deviceVideoMapper.insert(video);
	}

	// 修改数据
	@Override
	public Integer update(MemberDeviceVideo video) {
		return deviceVideoMapper.update(video);
	}

	// 批量删除
	@Override
	public void batchDelete(Map<String, Object> map) {
		deviceVideoMapper.batchDelete(map);
	}
	
	// 根据设备id进行去重判断
	@Override
	public int countByVideo(MemberDeviceVideo video) {
		return deviceVideoMapper.countByVideo(video);
	}

}