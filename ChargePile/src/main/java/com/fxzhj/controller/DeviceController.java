package com.fxzhj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fxzhj.model.Device;
import com.fxzhj.model.RangeDevice;
import com.fxzhj.service.DeviceService;
import com.fxzhj.util.HttpClientUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.util.StringUtil;

/**
 * 设备台账管理表
 * @author jifei
 *
 */
@Controller
@RequestMapping(value = "/device")
public class DeviceController {
	
	@Autowired
	private DeviceService deviceService;
	
	/**
	 * 跳转设备台账管理界面
	 * @return
	 */
	@RequestMapping(value="/deviceJsp")
	public String deviceJsp(){
		return "device.jsp";
	}
	
	@RequestMapping(value = "operateJsp")
	public String operateJsp(){
		return "operate.jsp";
	}
	
	/**
	 * 下拉框查询
	 * @param comId
	 * @return
	 */
	@RequestMapping(value = "showCombobox")
	@ResponseBody
	public List<Device> showCombobox(){
		Device device = new Device();
		device.setMadeIn("--请选择--");
		List<Device> list = deviceService.showCombobox();
		list.add(0, device);
		return list;
	}
	
	/**
	 * 根据条件查询设备
	 * @param device
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryDevice")
	@ResponseBody
	public Map<String, Object> queryDevice(Device device, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(request.getParameter("page")) && StringUtil.isNotEmpty(request.getParameter("rows"))){
			int pageNo = Integer.parseInt((String) request.getParameter("page"));
			// 当前检索的条数
			int pageSize = Integer.parseInt((String) request.getParameter("rows"));
			PageHelper.startPage(pageNo, pageSize);
		}
		List<Device> list =deviceService.queryDevice(device);
		PageInfo<Device> pageList = null;
		if(list!=null&&list.size()>0){
			pageList = new PageInfo<Device>(list);
		}
		
		if (pageList != null && pageList.getList() != null && pageList.getList().size()>0) {
			map.put("total", pageList.getTotal());
			map.put("rows", pageList.getList());
		}else{
			map.put("total", 0);
			map.put("rows", new ArrayList<Device>());
		}
		return map;
	} 
	
	/**
	 * 根据传入对象判断id是否为空 
	 * 从而判断是新增还是修改
	 * @param device
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveDevice")
	@ResponseBody
	public Map<String, Object> saveDevice(Device device, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String json=request.getParameter("data");
		if (StringUtil.isNotEmpty(json)) {
			device = JSON.parseObject(json, Device.class);
		}
		device.setInDate(new Date());
		try {
			if(device.getId() == null){
				//根据设备的名称和编号判断是否重复
				int count = deviceService.count(device);
				if(count == 0){
					deviceService.addDevice(device);
					map.put("state", "SUCCESS");
				}else{
					map.put("state", "SAME");
				}
			}else{
				//根据id后排除本条数据后再根据设备的名称和编号，判断是否重复
				int count = deviceService.countClearById(device);
				if(count == 0){
					int num = deviceService.updateDevice(device);
					if(num == 1){
						map.put("state", "SUCCESS");
					}else{
						map.put("state", "BANDING");
					}
				}else{
					map.put("state", "SAME");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "FALSE");
		}
		return map;
	}
	
	/**
	 * 查询未绑定的数据
	 * @param comOrareId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryDeviceByComm")
	@ResponseBody
	public List<Device> queryDeviceByComm(String comOrareId, String deep, HttpServletRequest request, HttpServletResponse response){
		List<Device> list = new ArrayList<Device>();
		Device device = new Device();
		try {
			if(StringUtil.isNotEmpty(comOrareId) && StringUtil.isNotEmpty(deep)){
				//查询所有未绑定的设备
				if("0".equals(deep)){
					return list;
				}else if("9".equals(deep)){
					device.setCommunityId(Integer.parseInt(comOrareId));
				}else{
					device.setAreaId(Integer.parseInt(comOrareId));
				}
				list = deviceService.queryUnboundedDevice(device);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据传入的条件修改设备相关表
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateRelatDevice")
	@ResponseBody
	public Map<String, Object> updateRelatDevice(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String json=request.getParameter("data");
		RangeDevice rangeDevice=new RangeDevice();
		if (StringUtil.isNotEmpty(json)) {
			rangeDevice = JSON.parseObject(json, RangeDevice.class);
		}else{
			map.put("state", "FALSE");
			return map;
		}
		try {
			deviceService.deviceService(rangeDevice);
			map.put("state", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "ERROR");
		}
		return map;
	}
	
	/**
	 * 根据查询条件查询设备运营记录
	 * @param device
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryOperateDevice")
	@ResponseBody
	public Map<String, Object> queryOperateDevice(Device device, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(request.getParameter("page")) && StringUtil.isNotEmpty(request.getParameter("rows"))){
			int pageNo = Integer.parseInt((String) request.getParameter("page"));
			// 当前检索的条数
			int pageSize = Integer.parseInt((String) request.getParameter("rows"));
			PageHelper.startPage(pageNo, pageSize);
		}
		List<Device> list =deviceService.queryOperateDevice(device);
		PageInfo<Device> pageList = null;
		if(list!=null&&list.size()>0){
			pageList = new PageInfo<Device>(list);
		}
		
		if (pageList != null && pageList.getList() != null && pageList.getList().size()>0) {
			map.put("total", pageList.getTotal());
			map.put("rows", pageList.getList());
		}else{
			map.put("total", 0);
			map.put("rows", new ArrayList<Device>());
		}
		return map;
	}
	
	/**
	 * 根据id修改状态
	 * @param id
	 * @param status
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateStatusById")
	@ResponseBody
	public Map<String, Object> updateStatusById(String id, String status, String deviceNum, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(id) && StringUtil.isNotEmpty(status) && StringUtil.isNotEmpty(deviceNum)) {
			try {
				//需要调用服务层接口,根据返回的条件修改设备状态
				Map<String, String> mapData = new HashMap<String, String>();
				mapData.put("funCode", "fn1001");
				mapData.put("deviceNo", deviceNum);
				mapData.put("status", status);
				String jsonStr = HttpClientUtils.doPostForValues("http://192.168.1.142:8080/api",mapData);
				if(StringUtil.isNotEmpty(jsonStr)){
					Map<String, Object> jsonMap = JSON.parseObject(jsonStr,new TypeReference<Map<String, Object>>(){} );
					Object value = jsonMap.get("success");
					if(value!=null && "true".equals(value.toString())){
						deviceService.updateStatusById(Long.parseLong(id),Integer.parseInt(status));
						map.put("state", "SUCCESS");
					}else{
						map.put("state", "SFALSE");
					}
				}else{
					map.put("state", "SFALSE");
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("state", "ERROR");
			}
		}else{
			map.put("state", "FALSE");
			return map;
		}
		return map;
	}
	

}
