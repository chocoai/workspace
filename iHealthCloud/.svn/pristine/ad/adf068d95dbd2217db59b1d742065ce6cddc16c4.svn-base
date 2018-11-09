package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Area;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.Service;
import com.yhcrt.iHealthCloud.entity.ServiceExample;
import com.yhcrt.iHealthCloud.entity.ServicePrice;
import com.yhcrt.iHealthCloud.entity.ServicePriceExample;
import com.yhcrt.iHealthCloud.entity.SysDictionary;
import com.yhcrt.iHealthCloud.entity.SysDictionaryExample;
import com.yhcrt.iHealthCloud.entity.UserComment;
import com.yhcrt.iHealthCloud.entity.YwImageExample;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.ServiceMapper;
import com.yhcrt.iHealthCloud.mapper.ServicePriceMapper;
import com.yhcrt.iHealthCloud.mapper.SysDictionaryMapper;
import com.yhcrt.iHealthCloud.mapper.UserCommentMapper;
import com.yhcrt.iHealthCloud.mapper.YwImageMapper;
import com.yhcrt.iHealthCloud.service.ServiceService;
import com.yhcrt.iHealthCloud.service.SysDictService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.OrgConst;

/**
 * 
 * @author rpf
 *
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl extends BaseService implements ServiceService {

	@Autowired
	private SysDictService sysDictService;
	@Autowired
	private ServiceMapper serviceMapper;
	@Autowired
	private SysDictionaryMapper sysDictionaryMappper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private YwImageMapper ywImageMapper;
	@Autowired
	private UserCommentMapper userCommentMapper;
	@Autowired
	private ServicePriceMapper servicePriceMapper;

	@Override
	public Integer add(Service service) {
		return serviceMapper.insert(service);
	}

	@Override
	public Integer del(Integer cid) {
		return serviceMapper.deleteByPrimaryKey(cid);
	}

	@Override
	public Integer upd(Service service) {
		return serviceMapper.updateByPrimaryKeySelective(service);
	}

	@Override
	public Service get(Integer cid) {
		return serviceMapper.selectByPrimaryKey(cid);
	}

	@Override
	public List<Service> getAll(HashMap<String, Object> params) {
		return serviceMapper.search(params);
	}

	@Override
	public String getServices(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String providerType = bizObj.getString("provider_type");
		String serviceType = bizObj.getString("service_type");
		String area = bizObj.getString("area");
		String orderBy = bizObj.getString("order_by");
		String currentPage = bizObj.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = bizObj.getString(Const.TAG_PAGE_SIZE);

		if (orderBy.equals("1")) {
			// 价格升序
			orderBy = "unit_price+0 ";
		} else if (orderBy.equals("2")) {
			// 价格降序
			orderBy = "unit_price+0 desc";
		} else if (orderBy.equals("3")) {
			// 分数降序
			orderBy = "score ";
		}else{
			// 热度降序
			orderBy = "iext2 desc";
		}
		
		List<Service> services = new ArrayList<Service>();
		if (StringUtils.isNotBlank(pageSize) && StringUtils.isNotBlank(currentPage)) {
			PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
			services = serviceMapper.getServices(providerType, area, serviceType, orderBy);
			PageInfo<Service> pageInfo = new PageInfo<Service>(services);
			setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
		}else {
			services = serviceMapper.getServices(providerType, area, serviceType, orderBy);
		}
		requestSucceed(pdataObj, services, "");
		return toJsonStringWithOutNull(pdataObj);
		
	}

	@Override
	public String getServiceFilterItem(JSONObject jsonObject) {
		try {
			SysDictionaryExample example = new SysDictionaryExample();
			example.createCriteria().andStatusEqualTo(0).andDictEnNameEqualTo(OrgConst.PROVIDER_TYPE);
			example.or().andStatusEqualTo(0).andDictEnNameEqualTo(OrgConst.SERVICE_TYPE);
			example.setOrderByClause("order_num desc,create_time desc");
			List<SysDictionary> sysDictionaryList = sysDictionaryMappper.selectByExample(example);
			
			JSONArray providerTypeArray = new JSONArray();
            JSONArray serviceArray = new JSONArray();
            JSONArray areaArray = new JSONArray();
            JSONObject bizJson = new JSONObject();
            
			for(SysDictionary sysDictionary : sysDictionaryList){
				JSONObject json = new JSONObject();
				json.put("cid", sysDictionary.getDictId());
				String name = sysDictionary.getDictKey();
				json.put("name", name);
				json.put("shortName", com.yhcrt.iHealthCloud.util.StringUtil.overSubstr(name, 4));
				json.put("value", sysDictionary.getDictValue());
              	if(OrgConst.PROVIDER_TYPE.equals(sysDictionary.getDictEnName())){
            		providerTypeArray.add(json);
            	}else{
            		serviceArray.add(json);
            	}
            }
			List<Area> areas = sysDictService.selectArea(1004);//武汉市区级别查询
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
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			jsonObject.put(Const.TAG_RMK, "获取成功");
			jsonObject.put(Const.TAG_BIZ, bizJson);
		} catch (Exception e) {
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			jsonObject.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String getServiceDetail(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String serviceId = bizObj.getString("service_id");
		if (StringUtils.isNotBlank(serviceId)) {
			Service service = get(Integer.parseInt(serviceId));
			requestSucceed(jsonObject, service, "");
		}
		return toJsonStringWithOutNull(jsonObject);
	}
	@Override
	public String getServiceDetailItem(JSONObject jsonObject) {
		// 获取参数
		JSONObject biz = jsonObject.getJSONObject("biz");
		String serviceId = biz.getString("service_id");
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(jsonObject, serviceId)) {
			// 向数据库请求数据并判断是否分页
			Service service = get(Integer.parseInt(serviceId));
			if (service != null) {
				JSONObject servicejson = (JSONObject) JSON.toJSON(service);
				YwImageExample example = new YwImageExample();
				example.createCriteria().andRefIdEqualTo(service.getServiceId()).andModuleCodeEqualTo(Constants.SERVICE);
				servicejson.put("images", ywImageMapper.selectByExample(example));
				
				UserComment comment = new UserComment();
				comment.setRefId(service.getServiceId());
				comment.setCommentType(Constants.SERVICE);
				
				Long count = userCommentMapper.countAll(comment);
				if(count!=0){
					List<UserComment> commentListAll = userCommentMapper.selectAll(comment, 2);
					JSONArray commentJsonList = new JSONArray();
					for(UserComment userComment : commentListAll){
						JSONObject userCommentJson = (JSONObject) JSON.toJSON(userComment);
						Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(userComment.getMemberId()));
						userCommentJson.put("userLogo", member.getHeadPic());
						userCommentJson.put("nickName", member.getNickName());
						commentJsonList.add(userCommentJson);
					}
					servicejson.put("comments", commentJsonList);
				}
				ServicePriceExample priceExample = new ServicePriceExample();
				priceExample.createCriteria().andServiceIdEqualTo(service.getServiceId());
				List<ServicePrice> priceList = servicePriceMapper.selectByExampleWithBLOBs(priceExample);
				for (ServicePrice servicePrice : priceList) {
					SysDictionary  dict = sysDictionaryMappper.getByDictValue(servicePrice.getUnit());
					if(dict==null){
						priceList.remove(servicePrice);
						continue;
					}
					servicePrice.setUnit(dict.getDictKey());
				}
				servicejson.put("titleImg", servicejson.get("titleImg")==null?"":servicejson.get("titleImg"));
				servicejson.put("tel", "18627899132");
				servicejson.put("priceList", priceList);
				servicejson.put("moreComments",count);
				jsonObject.put(Const.TAG_BIZ, servicejson);
				jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				jsonObject.put(Const.TAG_RMK, "获取成功！");
			} else {
				requestFailed(jsonObject, "不存在此服务");
			}
		} else {
			requestFailed(jsonObject, Const.RMK_BIZ_PARAM_NULL);
		}
		return jsonObject.toJSONString();
	}
	
	@Override
	public List<Service> selectByProviderId(Integer providerId) {
		ServiceExample example = new ServiceExample();
		example.setOrderByClause("score desc");
		example.createCriteria().andProviderIdEqualTo(providerId);
		return serviceMapper.selectByExample(example);
	}

	@Override
	public List<Service> selectBySearchText(String searchText) {
		ServiceExample example = new ServiceExample();
		example.setOrderByClause("create_time desc");
		example.createCriteria().andServiceNameLike("%"+searchText+"%");
		example.or().andServiceIntroLike("%"+searchText+"%");
		return serviceMapper.selectByExample(example);
	}

}
