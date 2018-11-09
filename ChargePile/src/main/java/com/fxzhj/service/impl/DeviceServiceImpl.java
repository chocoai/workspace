package com.fxzhj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxzhj.mapper.DeviceMapper;
import com.fxzhj.mapper.NodeCodeMapper;
import com.fxzhj.mapper.NodeMapper;
import com.fxzhj.mapper.PortMapper;
import com.fxzhj.mapper.RangeDeviceMapper;
import com.fxzhj.model.Device;
import com.fxzhj.model.Node;
import com.fxzhj.model.Port;
import com.fxzhj.model.RangeDevice;
import com.fxzhj.service.DeviceService;
import com.fxzhj.util.DateUtil;

import tk.mybatis.mapper.util.StringUtil;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceMapper DMapper;
	
	@Autowired
	private RangeDeviceMapper RDMapper;
	
	@Autowired
	private PortMapper PMapper;
	
	@Autowired
	private NodeMapper NMapper;
	
	@Autowired
	private NodeCodeMapper NCMapper;
	
	// 查询设备
	@Override
	public List<Device> queryDevice(Device device) {
		if("--请选择--".equals(device.getMadeIn())){
			device.setMadeIn(null);
		}
		return DMapper.queryDevice(device);
	}

	//新增判断是否重复
	@Override
	public int count(Device device) {
		return DMapper.count(device);
	}

	//新增设备的数据同时批量新增设备端口数量
	@Override
	@Transactional
	public void addDevice(Device device) {
		//新增节点
		List<Node> listNode=new ArrayList<Node>();
		Date date=new Date();
		Node node=new Node();
		node.setNodeId(DateUtil.CDZstr()+"0");
		node.setNodeName(device.getName());
		node.setIsNode(0);
		node.setNodeStatus(0);
		node.setCreateTime(date);
		//需要修改为查询
		node.setParentId("CDZ20170622345");
		listNode.add(node);
		//新增设备
		device.setNodeId(node.getNodeId());
		DMapper.addDevice(device);
		List<Port> list=new ArrayList<Port>();
		for(int i=1;i<=device.getPortNum();i++){
			Node bean=new Node();
			bean.setNodeId(DateUtil.CDZstr()+i);
			bean.setNodeName(device.getName()+"-"+i);
			bean.setParentId(node.getNodeId());
			bean.setIsNode(1);
			bean.setNodeStatus(0);
			bean.setCreateTime(date);
			listNode.add(bean);
			
			Port port=new Port();
			port.setDeviceId(device.getId());
			port.setPortNo(i);
			port.setStatus(0);
			port.setNodeId(bean.getNodeId());
			list.add(port);
		}
		//新增设备端口号
		PMapper.batchPort(list);
		//新增节点信息
		NMapper.batchNode(listNode);
		
	}

	//修改判断是否重复
	@Override
	public int countClearById(Device device) {
		return DMapper.countClearById(device);
	}

	//修改数据
	@Override
	@Transactional
	public int updateDevice(Device device) {
		//根据id查询设备端口和节点编号
		Device dev=DMapper.queryById(device.getId());
		//查询设备端口的节点id
		List<Port> listPort=PMapper.queryPort(device.getId());
		//表示端口数量未发生变化
		if(dev.getPortNum()==device.getPortNum()){
			//修改设备节点名称
			NMapper.updateByNid(dev.getNodeId(), device.getName());
			for(int i=0;i<listPort.size();i++){
				//修改端口节点名称
				NMapper.updateByNid(listPort.get(i).getNodeId(), device.getName()+"-"+i);
			}
		}else{
			Port p = new Port();
			p.setNodeId(dev.getNodeId());
			listPort.add(p);
			//表示端口发生变化
			//判断端口中节点匹配url是否已绑定数据
			String[] nodeIds = new String[listPort.size()];
			for(int i=0;i<listPort.size();i++){
				nodeIds[i]=listPort.get(i).getNodeId();
			}
			//批量模糊查询
			int count = NCMapper.queryBatchByNid(nodeIds);
			if(count>0){
				return 0;
			}else{
				//批量删除节点
				NMapper.batchDelete(nodeIds);
				//根据设备id删除端口
				PMapper.delete(device.getId());
				//新增节点
				List<Node> listNode=new ArrayList<Node>();
				Date date=new Date();
				Node node=new Node();
				node.setNodeId(DateUtil.CDZstr()+"0");
				node.setNodeName(device.getName());
				node.setIsNode(0);
				node.setNodeStatus(dev.getStatus());
				node.setCreateTime(date);
				//需要修改为查询
				node.setParentId("CDZ20170622345");
				listNode.add(node);
				//将节点编号放入设备对象中
				device.setNodeId(node.getNodeId());
				
				List<Port> list=new ArrayList<Port>();
				for(int i=1;i<=device.getPortNum();i++){
					Node bean=new Node();
					bean.setNodeId(DateUtil.CDZstr()+i);
					bean.setNodeName(device.getName()+"-"+i);
					bean.setParentId(node.getNodeId());
					bean.setIsNode(1);
					bean.setNodeStatus(dev.getStatus());
					bean.setCreateTime(date);
					listNode.add(bean);
					
					Port port=new Port();
					port.setDeviceId(device.getId());
					port.setPortNo(i);
					port.setStatus(dev.getStatus());
					port.setNodeId(bean.getNodeId());
					list.add(port);
				}
				//新增设备端口号
				PMapper.batchPort(list);
				//新增节点信息
				NMapper.batchNode(listNode);
			}
		}
		//修改设备名称
		DMapper.updateDevice(device);
		return 1;
	}

	//查询所有未绑定的设备
	@Override
	public List<Device> queryUnboundedDevice(Device device) {
		return DMapper.queryUnboundedDevice(device);
	}

	//根据传入的条件修改设备相关表
	@Override
	@Transactional
	public void deviceService(RangeDevice rangeDevice) {
		Device device = new Device();
		if("9".equals(rangeDevice.getDeep())){
			//9表示设备绑定到具体小区
			RDMapper.deleteByCId(rangeDevice.getCommunityId());
			DMapper.updateByCId(rangeDevice.getCommunityId());
		}else{
			//修改区域中
			RDMapper.deleteByAId(rangeDevice.getCommunityId());
			DMapper.updateByAId(rangeDevice.getCommunityId());
		}
		if(StringUtil.isNotEmpty(rangeDevice.getIds())){
			String[] idStr = rangeDevice.getIds().split(",");
			for (int i = 0; i < idStr.length; i++) {
	        	if(StringUtil.isNotEmpty(idStr[i])){
	        		device.setId(Long.parseLong(idStr[i]));
	        		rangeDevice.setDeviceId(Long.parseLong(idStr[i]));
	        		//根据id修改设备表中的区域id
	        		if("9".equals(rangeDevice.getDeep())){
	        			//9表示设备绑定到具体小区
	        			device.setCommunityId(rangeDevice.getCommunityId());
	        		}else{
	        			//修改区域中
	        			device.setAreaId(rangeDevice.getCommunityId());
	        		}
	        		DMapper.updateDeviceAreaorComm(device);
	        		//新增保存
	        		RDMapper.addBean(rangeDevice);
	        	}
	        }
		}
	}

	//根据查询条件查询设备运营记录
	@Override
	public List<Device> queryOperateDevice(Device device) {
		if("0".equals(device.getDeep())){
			//根节点
			return null;
		}else if("9".equals(device.getDeep())){
			//9表示关联具体小区表查询
			device.setCommunityId(device.getComOrareId());
			device.setAreaId(null);
		}else{
			//其他表示区域关联查询
			device.setAreaId(device.getComOrareId());
			device.setCommunityId(null);
		}
		return DMapper.queryOperateDevice(device);
	}

	//根据id修改状态
	@Override
	@Transactional
	public void updateStatusById(Long id, Integer status) {
		//修改设备状态
		DMapper.updateStatusById(id,status);
		//修改设备对应端口状态
		PMapper.updateByDeviceId(id,status);
		//根据设备id修改节点信息
		NMapper.updateByDid(id,status);
	}

	//显示下拉框
	@Override
	public List<Device> showCombobox() {
		return DMapper.showCombobox();
	}

}