package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.entity.Device;
import com.yhcrt.iHealthCloud.entity.DeviceExample;
import com.yhcrt.iHealthCloud.entity.MemberDevice;
import com.yhcrt.iHealthCloud.entity.MemberDeviceExample;
import com.yhcrt.iHealthCloud.mapper.DeviceMapper;
import com.yhcrt.iHealthCloud.mapper.LocationMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.pojo.LocationData;
import com.yhcrt.iHealthCloud.service.DeviceService;
import com.yhcrt.iHealthCloud.util.Const;

@Service
public class DeviceServiceImpl extends BaseService implements DeviceService {
	
	protected boolean isDebug = true;
	
	@Autowired
	private MemberDeviceMapper memberDeviceMappler;
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private LocationMapper locationMapper;

	@Override
	public String getDeviceListByMemberId(JSONObject pdataObj) {
		
		// 获取参数memberId的值
		String memberId = getMemberId(pdataObj);
		String currentPage = getCid(pdataObj);
		String pageSize = getPageSize(pdataObj);
		
		// 对参数进行非空判断
		if(judgeAgumentsIsLegal(pdataObj, memberId)){
			
			// 在member_device表中进行查询
			MemberDeviceExample example = new MemberDeviceExample();
			example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
			example.setOrderByClause("cid desc");
			List<MemberDevice> list = memberDeviceMappler.selectByExample(example);
			
			List<String> imeiList = new ArrayList<>();
			for (MemberDevice memberDevice : list) {
				imeiList.add(memberDevice.getImei());
			}
			
			List<Device> deviceList;
			if(judgePageInfoIsLegal(currentPage, pageSize)){
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				deviceList = getDeviceListInImei(imeiList);
				PageInfo<Device> pageInfo = new PageInfo<>(deviceList);
				setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
			}else{
				deviceList = getDeviceListInImei(imeiList);
			}
			
			requestSucceed(pdataObj, deviceList, "");
			
			
		}else{
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		
		return pdataObj.toJSONString();
	}

	private List<Device> getDeviceListInImei(List<String> imeiList) {
		List<Device> deviceList;
		DeviceExample deviceExample = new DeviceExample();
		deviceExample.createCriteria().andImeiIn(imeiList);
		deviceExample.setOrderByClause("device_id desc");
		deviceList = deviceMapper.selectByExample(deviceExample);
		return deviceList;
	}

	@Override
	public String getDeviceDetailByDeviceId(JSONObject pdataObj) {
		
		// 获取参数cid的值
		String deviceId = getDeviceId(pdataObj);
		
		// 对参数进行非空判断
		if(judgeAgumentsIsLegal(pdataObj, deviceId)){
			
			// 在member_device表中进行查询
			Device device = deviceMapper.selectByPrimaryKey(Integer.parseInt(deviceId));
			
			if(device == null){
				requestFailed(pdataObj, "no such device");
			}else{
				requestSucceed(pdataObj, device, "");
			}
			
		}else{
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		
		return pdataObj.toJSONString();
	}

	@Override
	public String getDeviceListByOrgId(JSONObject pdataObj) {
		
		String orgId = getOrgId(pdataObj);
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);
		
		if(judgeAgumentsIsLegal(pdataObj, orgId)){
			
			List<Device> list;
			if(judgePageInfoIsLegal(currentPage, pageSize)){
				
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = selectDeviceListByOrdId(orgId);
				PageInfo<Device> p = new PageInfo<>(list);
				setPagingData(pdataObj, p.getPages(), p.getPageNum());
				
			}else{
				list = selectDeviceListByOrdId(orgId);
			}
			
			requestSucceed(pdataObj, list, "");
			
		}else{
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return pdataObj.toJSONString();
	}

	private List<Device> selectDeviceListByOrdId(String orgId) {
		List<Device> list;
		DeviceExample example = new DeviceExample();
		example.createCriteria().andOrgIdEqualTo(Integer.parseInt(orgId));
		example.setOrderByClause("device_id desc");
		list = deviceMapper.selectByExample(example);
		return list;
	}

	@Override
	public String getDeviceByCondition(JSONObject pdataObj) {
		
		// 获取参数
		String orgId = getOrgId(pdataObj);
		String deviceType = getDeviceType(pdataObj);
		String sim = getDeviceSim(pdataObj);
		String deviceName = getDeviceName(pdataObj);
		String isUsed = getDeviceIsUsed(pdataObj);
		
		// 获取分页信息
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);
		
		// 判断参数是否为空
		if(judgeAgumentsIsLegal(pdataObj, orgId)){
			
			if(deviceType == null) deviceType = "";
			if(sim == null) sim = "";
			if(deviceName == null) deviceName = "";
			if(isUsed == null) isUsed = "";
			
			// 查询member_device表
			MemberDeviceExample example = new MemberDeviceExample();
			if("".equals(deviceType)){
				example.createCriteria()
				.andOrgIdEqualTo(Integer.parseInt(orgId))
				.andSimLike("%" + sim + "%");
				example.setOrderByClause("cid desc");
			}else{
				example.createCriteria()
				.andOrgIdEqualTo(Integer.parseInt(orgId))
				.andDeviceTypeEqualTo(deviceType)
				.andSimLike("%" + sim + "%");
				example.setOrderByClause("cid desc");
			}
			List<MemberDevice> list = memberDeviceMappler.selectByExample(example);
			// 得到imei集合
			List<String> imeiList = new ArrayList<>();
			for (MemberDevice memberDevice : list) {
				imeiList.add(memberDevice.getImei());
			}
			
			// 根据获得的imei集合查询device表
			List<Device> deviceList;
			if(imeiList.size()>0){
				// 分页
				if(judgePageInfoIsLegal(currentPage, pageSize)){
					
					PageHelper.startPage(Integer.parseInt(currentPage),Integer.parseInt(pageSize));
					deviceList = getDeviceListInImeiAndisUsed(deviceName, isUsed, imeiList);
					PageInfo<Device> p = new PageInfo<>(deviceList);
					setPagingData(pdataObj, p.getPages(), p.getPageNum());
					
				}else{
					deviceList = getDeviceListInImeiAndisUsed(deviceName, isUsed, imeiList);
				}
				
				
			}else{
				deviceList = new ArrayList<>();
			}
			
			requestSucceed(pdataObj, deviceList, "");
		}else{
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		
		return pdataObj.toJSONString();
	}

	private List<Device> getDeviceListInImeiAndisUsed(String deviceName, String isUsed, List<String> imeiList) {
		List<Device> deviceList;
		DeviceExample deviceExample = new DeviceExample();
		if("".equals(isUsed)){
			deviceExample.createCriteria().andDeviceNameLike("%" + deviceName + "%").andImeiIn(imeiList);
		}else{
			int i;
			try {
				i = Integer.parseInt(isUsed);
			} catch (Exception e) {
				i = 100;
			}
			deviceExample.createCriteria().andIsUseEqualTo(i).andDeviceNameLike("%" + deviceName + "%").andImeiIn(imeiList);
		}
		deviceExample.setOrderByClause("device_id desc");
		deviceList = deviceMapper.selectByExample(deviceExample);
		return deviceList;
	}

	
	@Override
	public String getOnlineDeviceLocation(JSONObject pdataObj) {
		JSONObject bizObj = pdataObj.getJSONObject(Const.TAG_BIZ);
		String orgId = bizObj.getString("org_id");
		if(StringUtils.isNotBlank(orgId)){
			List<LocationData> locations = locationMapper.ListOnlineDeviceLocation(orgId);
			requestSucceed(pdataObj, locations, "");
		}else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.iHealthCloud.service.DeviceService#searchOnlineDeviceLocation(com.alibaba.fastjson.JSONObject)
	 */
	@Override
	public String searchOnlineDeviceLocation(JSONObject pdataObj) {
		JSONObject bizObj = pdataObj.getJSONObject(Const.TAG_BIZ);
		String orgId = bizObj.getString("org_id");
		String param = bizObj.getString("param");
		if(StringUtils.isNotBlank(orgId)){
			// 我关注的人
			List<LocationData> locations = locationMapper.searchOnlineDeviceLocation(orgId,param);
			requestSucceed(pdataObj, locations, "");
		}else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}


}
