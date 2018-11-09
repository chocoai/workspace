package com.yhcrt.healthcloud.device.mapper;

import java.util.List;
import java.util.Map;

import com.yhcrt.healthcloud.device.entity.MemberDeviceVideo;

public interface MemberDeviceVideoMapper {

	// 查询设备视频数据
	List<MemberDeviceVideo> queryPageList(Map<String, Object> args);

	// 根据cid查询数据
	MemberDeviceVideo queryByCid(Integer cid);

	// 新增数据
	Integer insert(MemberDeviceVideo video);

	// 修改数据
	Integer update(MemberDeviceVideo video);

	// 批量删除
	void batchDelete(Map<String, Object> map);

	// 根据设备id进行去重判断
	int countByVideo(MemberDeviceVideo video);

}