/**
 * @Title:   SysDictServiceImpl.java 
 * @Package: com.yhcrt.healthcloud.system.service.impl  
 * @Description: 
 * @author: rpf
 * @date: 2017年5月26日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Area;
import com.yhcrt.iHealthCloud.entity.AreaExample;
import com.yhcrt.iHealthCloud.entity.SysDictionary;
import com.yhcrt.iHealthCloud.entity.SysDictionaryExample;
import com.yhcrt.iHealthCloud.entity.SysDictionaryExample.Criteria;
import com.yhcrt.iHealthCloud.mapper.AreaMapper;
import com.yhcrt.iHealthCloud.mapper.SysDictionaryMapper;
import com.yhcrt.iHealthCloud.service.SysDictService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.OrgConst;

/**
 * @ClassName: SysDictServiceImpl
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2017年5月26日 
 */

@Service
@Transactional
public class SysDictServiceImpl extends BaseService  implements SysDictService {

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;
	@Autowired
	private AreaMapper areaMapper;

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysDictService#insert(com.yhcrt.healthcloud.system.entity.SysDictionary)
	 */
	@Override
	public int insert(SysDictionary sysDictionary) {
		return sysDictionaryMapper.insert(sysDictionary);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysDictService#deleteByDictId(java.lang.Integer)
	 */
	@Override
	public int deleteByDictId(Integer dictId) {
		return sysDictionaryMapper.deleteByPrimaryKey(dictId);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysDictService#updateByDictId(com.yhcrt.healthcloud.system.entity.SysDictionary)
	 */
	@Override
	public int updateByDictId(SysDictionary sysDictionary) {
		return sysDictionaryMapper.updateByPrimaryKey(sysDictionary);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysDictService#listAllSysDict()
	 */
	@Override
	public List<SysDictionary> listAllSysDict() {
		SysDictionaryExample example = new SysDictionaryExample();
		example.createCriteria().andStatusEqualTo(Constants.STATUS_NORMAL);
		example.setOrderByClause("create_time desc");
		return sysDictionaryMapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.healthcloud.system.service.SysDictService#getSysDictByDictId(java.lang.Integer)
	 */
	@Override
	public SysDictionary getSysDictByDictId(Integer dictId) {
		return sysDictionaryMapper.selectByPrimaryKey(dictId);
	}
	
	/**
	 * 获取供应商类型和服务类型数据字典项
	 */
	@Override
	public String getItems(JSONObject jsonObject){
		try {
			SysDictionaryExample example = new SysDictionaryExample();
			example.createCriteria().andDictEnNameEqualTo("provider_type").andStatusEqualTo(Constants.STATUS_NORMAL);
			List<SysDictionary> providerTypes =  sysDictionaryMapper.selectByExample(example);
			example.clear();
			example.createCriteria().andDictEnNameEqualTo("service_type").andStatusEqualTo(Constants.STATUS_NORMAL);
			List<SysDictionary> serviceTypes =  sysDictionaryMapper.selectByExample(example);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("providerTypes", providerTypes);
			jsonObj.put("serviceTypes", serviceTypes);
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			jsonObject.put(Const.TAG_BIZ, jsonObj);
			jsonObject.put(Const.TAG_RMK, "获取成功");
			
		} catch (Exception e) {
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObject.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}
	
	@Override
	public String getItemsMore(JSONObject pdataObj){
		try {
			SysDictionaryExample example = new SysDictionaryExample();
			example.createCriteria().andStatusEqualTo(0).andDictEnNameEqualTo("service_type");
			example.or().andStatusEqualTo(0).andDictEnNameEqualTo("old_age");
			example.setOrderByClause("order_num desc,create_time desc");
			List<SysDictionary> sysDictionaryList = sysDictionaryMapper.selectByExample(example);
			
			JSONArray serviceTypeArray = new JSONArray();
            JSONArray oldAgeArray = new JSONArray();
            JSONObject bizJson = new JSONObject();
            for(SysDictionary sysDictionary : sysDictionaryList){
              	if("service_type".equals(sysDictionary.getDictEnName())){
            		JSONObject serviceTypeJson = new JSONObject();
            		serviceTypeJson.put("cid", sysDictionary.getDictId());
            		serviceTypeJson.put("name", sysDictionary.getDictKey());
            		serviceTypeJson.put("value", sysDictionary.getDictValue());
            		serviceTypeJson.put("icon", sysDictionary.getDictIcon()==null?"":sysDictionary.getDictIcon());
            		serviceTypeArray.add(serviceTypeJson);
            	}
            	if("old_age".equals(sysDictionary.getDictEnName())){
            		JSONObject orgTypeJson = new JSONObject();
            		orgTypeJson.put("cid", sysDictionary.getDictId());
            		orgTypeJson.put("name", sysDictionary.getDictKey());
            		orgTypeJson.put("value", sysDictionary.getDictValue());
            		orgTypeJson.put("icon", sysDictionary.getDictIcon()==null?"":sysDictionary.getDictIcon());
            		oldAgeArray.add(orgTypeJson);
            	}
            }
			bizJson.put("service", serviceTypeArray);
			bizJson.put("oldAge", oldAgeArray);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功");
			pdataObj.put(Const.TAG_BIZ, bizJson);
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	@Override
	public String selectDictByParam(JSONObject pdataObj){
		try {
			JSONObject bizObj = getBizObj(pdataObj);
			String parentId = bizObj.getString("parent_id");
			
			SysDictionaryExample example = new SysDictionaryExample();
			Criteria criteria = example.createCriteria().andStatusEqualTo(0);
			if(judgeAgumentsIsLegal(pdataObj, parentId)){
				criteria.andParentIdEqualTo(parentId);
			}else{
				example.or().andParentIdIsNull().andParentIdEqualTo("");
			}
			
			example.setOrderByClause("order_num desc,create_time desc");
			List<SysDictionary> sysDictionaryList = sysDictionaryMapper.selectByExample(example);
			
			JSONArray dictArray = new JSONArray();
			JSONObject bizJson = new JSONObject();
			
			for(SysDictionary sysDictionary : sysDictionaryList){
				JSONObject json = new JSONObject();
				json.put("cid", sysDictionary.getDictId());
				String name = sysDictionary.getDictKey();
				json.put("name", name);
				json.put("shortName", com.yhcrt.iHealthCloud.util.StringUtil.overSubstr(name, 4));
				json.put("value", sysDictionary.getDictValue());
				dictArray.add(json);
			}
			
			bizJson.put("dict", dictArray);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功");
			pdataObj.put(Const.TAG_BIZ, bizJson);
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	@Override
	public String selectSeviceType(JSONObject pdataObj){
		try {
			SysDictionaryExample example = new SysDictionaryExample();
			example.createCriteria().andStatusEqualTo(0).andDictEnNameEqualTo(OrgConst.PROVIDER_TYPE);
			example.or().andStatusEqualTo(0).andDictEnNameEqualTo(OrgConst.SERVICE_TYPE);
			example.setOrderByClause("order_num desc,create_time desc");
			List<SysDictionary> sysDictionaryList = sysDictionaryMapper.selectByExample(example);
			
			JSONArray providerTypeArray = new JSONArray();
            JSONArray serviceArray = new JSONArray();
            JSONArray areaArray = new JSONArray();
            JSONObject bizJson = new JSONObject();
            
			for(SysDictionary sysDictionary : sysDictionaryList){
				JSONObject json = new JSONObject();
				json.put("cid", sysDictionary.getDictId());
				json.put("name", sysDictionary.getDictKey());
				json.put("value", sysDictionary.getDictValue());
              	if(OrgConst.PROVIDER_TYPE.equals(sysDictionary.getDictEnName())){
            		providerTypeArray.add(json);
            	}else{
            		serviceArray.add(json);
            	}
            }
			List<Area> areas = selectArea(1004);//武汉市区级别查询
			for (Area area : areas) {
				JSONObject json = new JSONObject();
				json.put("cid", area.getAreaId());
				json.put("name", area.getAreaName());
				json.put("value", area.getAreaId());
				areaArray.add(json);
			}
			
			bizJson.put("providerType", providerTypeArray);
			bizJson.put("serviceType", serviceArray);
			bizJson.put("areaType", areaArray);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功");
			pdataObj.put(Const.TAG_BIZ, bizJson);
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String geHotServiceType(JSONObject jsonObject) {
		try {
			SysDictionaryExample example = new SysDictionaryExample();
			example.createCriteria().andDictEnNameEqualTo("service_type").andStatusEqualTo(Constants.STATUS_NORMAL);
			List<SysDictionary> serviceTypes = sysDictionaryMapper.selectByExample(example);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("serviceTypes", serviceTypes);
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			jsonObject.put(Const.TAG_BIZ, jsonObj);
			jsonObject.put(Const.TAG_RMK, "获取成功");

		} catch (Exception e) {
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObject.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}
	
	@Override
	public String getServiceDetail(JSONObject pdataObj){
		try {
            List<SysDictionary> sysDictionaryList = sysDictionaryMapper.getItems();
            JSONArray serviceTypeArray = new JSONArray();
            for(SysDictionary sysDictionary : sysDictionaryList){
            	if("service_type".equals(sysDictionary.getDictEnName())){
            		JSONObject serviceTypeJson = new JSONObject();
            		serviceTypeJson.put("cid", sysDictionary.getDictId());
            		serviceTypeJson.put("serviceType", sysDictionary.getDictKey());
            		if(serviceTypeArray.size()<6){ //只展示6个
            			serviceTypeArray.add(serviceTypeJson);
            		}
            	}
            }
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功");
			pdataObj.put(Const.TAG_BIZ, serviceTypeArray);
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String getServiceType(JSONObject jsonObject){
		try {
			SysDictionaryExample example = new SysDictionaryExample();
			example.createCriteria().andDictEnNameEqualTo("service_type");
			List<SysDictionary> serviceTypes =  sysDictionaryMapper.selectByExample(example);
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			jsonObject.put(Const.TAG_BIZ, serviceTypes);
			jsonObject.put(Const.TAG_RMK, "获取成功");
			
		} catch (Exception e) {
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObject.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	//查询所有"org_type"的服务类型
	@Override
	public String getAllItems(JSONObject jsonObject) {
		try {
			SysDictionaryExample example = new SysDictionaryExample();
			example.createCriteria().andDictEnNameEqualTo("provider_type");
			List<SysDictionary> serviceTypes =  sysDictionaryMapper.selectByExample(example);
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			jsonObject.put(Const.TAG_BIZ, serviceTypes);
			jsonObject.put(Const.TAG_RMK, "获取成功");
			
		} catch (Exception e) {
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObject.put(Const.TAG_RMK, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public SysDictionary selectDictEnNameAndValue(String dictEnName,String dictValue) {
		SysDictionaryExample example = new SysDictionaryExample();
		if(StringUtil.isNotEmpty(dictValue)){
			example.createCriteria().andDictEnNameEqualTo(dictEnName).andDictKeyEqualTo(dictValue);
			return sysDictionaryMapper.selectByExample(example).size()>0?sysDictionaryMapper.selectByExample(example).get(0):null;
		}
		return null;
	}
	
	@Override
	public Area selectAreaAndName(Integer parentId, String areaName) {
		AreaExample example = new AreaExample();
		if(StringUtil.isNotEmpty(areaName)){
			example.createCriteria().andParentIdEqualTo(parentId).andAreaNameEqualTo(areaName);
			return areaMapper.selectByExample(example).size()>0?areaMapper.selectByExample(example).get(0):null;
		}
		return null;
	}

	@Override
	public List<SysDictionary> selectDictEnName(String dictEnName) {
		SysDictionaryExample example = new SysDictionaryExample();
		example.createCriteria().andDictEnNameEqualTo(dictEnName).andStatusEqualTo(0);
		example.setOrderByClause("order_num desc,create_time desc");
		return sysDictionaryMapper.selectByExample(example);
	}

	@Override
	public List<Area> selectArea(Integer parentId) {
		AreaExample example = new AreaExample();
		example.createCriteria().andParentIdEqualTo(parentId).andStatusEqualTo(0);
		example.setOrderByClause("order_num desc,create_time desc");
		return areaMapper.selectByExample(example);
	}
	
	@Override
	public List<SysDictionary> getSysDictParentId(String parentId) {
		SysDictionaryExample example = new SysDictionaryExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		example.setOrderByClause("create_time desc");
		return sysDictionaryMapper.selectByExample(example);
	}
}
