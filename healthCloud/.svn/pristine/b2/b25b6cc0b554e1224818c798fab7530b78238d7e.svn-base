package com.yhcrt.healthcloud.device.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.device.entity.Device;
import com.yhcrt.healthcloud.device.entity.DeviceExample;
import com.yhcrt.healthcloud.device.entity.DeviceExample.Criteria;
import com.yhcrt.healthcloud.device.mapper.DeviceMapper;
import com.yhcrt.healthcloud.device.service.DeviceService;
/**
 * @Description:设备SERVICE层实现类
 * @author gongjun
 * 2017年5月17日
 * 版权所有：武汉炎黄创新科技服务有限公司
 */

@Service
public class DeviceServiceImpl implements DeviceService {
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	/**
	 * 根据设备IMEI码查询设备信息
	 * @param imei
	 * @return
	 */
	@Override
	public Device selectByImei(String imei) {
		List<Device> devices = deviceMapper.selectByImei(imei);
		if (devices.size() > 0) {
			return devices.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 根据设备PrimaryKey更新设备信息
	 * @param Device
	 * @return
	 */
	@Override
	public int updateByPrimaryKey(Device record) {
		return deviceMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insert(Device device) {
		return deviceMapper.insert(device);
	}

	@Override
	public int deleteByDeviceId(Integer deviceId) {
		return deviceMapper.deleteByPrimaryKey(deviceId);
	}

	@Override
	public int update(Device device) {
		return deviceMapper.updateByPrimaryKey(device);
	}

	@Override
	public List<Device> listAll() {
		return deviceMapper.selectByExample(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> listDevicesByArgs(Map<String, Object> args) {
		DeviceExample deviceExample = new DeviceExample();
		Criteria criteria = deviceExample.createCriteria();
		if (args.get("orgId_list") != null ) {
			criteria.andOrgIdIn((List<Integer>)args.get("orgId_list"));
		}
		if (args.get("imei") != null) {
			criteria.andImeiLike("%" + (String) args.get("imei") + "%");
		}
		if (args.get("startStatus") != null) {
			criteria.andStartStatusEqualTo(Integer.valueOf(args.get("startStatus").toString()));
		}
		if (args.get("isUse") != null) {
			criteria.andIsUseEqualTo(Integer.valueOf(args.get("isUse").toString()));
		}
		if (args.get("deviceType") != null) {
			criteria.andDeviceTypeLike("%" + (String) args.get("deviceType") + "%");
		}
		if (args.get("sim") != null) {
			criteria.andSimEqualTo((String) args.get("sim"));
		}
		criteria.andStatusEqualTo(Constants.STATUS_NORMAL);
		deviceExample.setOrderByClause("first_start_time desc,create_time desc");
		return deviceMapper.selectByExample(deviceExample);
	}

	@Override
	public Device getDeviceByDeviceId(Integer deviceId) {
		return deviceMapper.selectByPrimaryKey(deviceId);
	}

	//新增修改时排除重复
	@Override
	public int countByMap(Map<String, Object> map) {
		return deviceMapper.countByMap(map);
	}

}