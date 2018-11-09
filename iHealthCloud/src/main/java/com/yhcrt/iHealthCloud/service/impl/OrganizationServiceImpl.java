package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Area;
import com.yhcrt.iHealthCloud.entity.AreaExample;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.Organization;
import com.yhcrt.iHealthCloud.entity.OrganizationExample;
import com.yhcrt.iHealthCloud.entity.OrganizationExample.Criteria;
import com.yhcrt.iHealthCloud.entity.ServiceExample;
import com.yhcrt.iHealthCloud.entity.SysDictionary;
import com.yhcrt.iHealthCloud.entity.SysDictionaryExample;
import com.yhcrt.iHealthCloud.entity.UserComment;
import com.yhcrt.iHealthCloud.entity.UserCommentExample;
import com.yhcrt.iHealthCloud.entity.YwImageExample;
import com.yhcrt.iHealthCloud.mapper.AreaMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.OrganizationMapper;
import com.yhcrt.iHealthCloud.mapper.ServiceMapper;
import com.yhcrt.iHealthCloud.mapper.SysDictionaryMapper;
import com.yhcrt.iHealthCloud.mapper.UserCommentMapper;
import com.yhcrt.iHealthCloud.mapper.YwImageMapper;
import com.yhcrt.iHealthCloud.service.AreaService;
import com.yhcrt.iHealthCloud.service.OrganizationService;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;

/**
 * @Description: 机构SERVICE层实现类
 * @author gongjun
 * 2017年5月17日
 * 版权所有：武汉炎黄创新科技服务有限公司
 */

@Service
public class OrganizationServiceImpl extends BaseService implements OrganizationService {
	
	@Autowired
	private SysSequenceService sysSequenceService;

	@Autowired
	private  OrganizationMapper organizationMapper;
	
	@Autowired
	private  AreaMapper areaMapper;
	
    @Autowired	
    private YwImageMapper ywImageMapper;	
    
    @Autowired	
    private ServiceMapper serviceMapper;	
    
    @Autowired	
    private UserCommentMapper userCommentMapper;
    
    @Autowired	
    private MemberMapper memberMapper;
    
	@Autowired
	private SysDictionaryMapper sysDictMappper;
	
	@Autowired
	private AreaService areaService;
	
	/**
	 * 根据机构ID查询机构信息
	 * @param orgId
	 * @return
	 */
	@Override
	public Organization selectByPrimaryKey(Integer orgId) {
		return organizationMapper.selectByPrimaryKey(orgId);
	}

	@Override
	public Organization getOrgRootNode() {
		return organizationMapper.getOrgRootNode();
	}

	@Override
	public List<Organization> getChildOrgByParentId(Integer parentOrgId) {
		return organizationMapper.getChildOrgByParentId(parentOrgId);
	}

	@Override
	public int insert(Organization organization) {
		organization.setOrgId(sysSequenceService.getSequenceValue(Constants.SequenceName.ORGANIZATION));
		organization.setStatus(Constants.STATUS_NORMAL);
		organization.setCreateTime(DateUtil.getDateTime());
		String orgCode;
		// 查询当前的资源编码
		Organization parentOrg = organizationMapper.selectByPrimaryKey(organization.getParentOrgId());
		String lastOrgCode = organizationMapper.getLastOrgCodeByParentId(organization.getParentOrgId());
		if (StringUtils.isNotBlank(lastOrgCode)) {
			orgCode = (Integer.parseInt(lastOrgCode) + 1) + "";
		} else {
			orgCode = parentOrg.getOrgCode() + organizationMapper.getOrgRootNode().getOrgCode();
		}
		organization.setOrgCode(orgCode);
		return organizationMapper.insert(organization);
	}

	@Override
	public int deleteByExample(OrganizationExample example) {
		return organizationMapper.deleteByExample(example);
	}

	@Override
	public int deleteByOrgId(Integer orgId) {
		return organizationMapper.deleteByPrimaryKey(orgId);
	}

	@Override
	public int update(Organization organization) {
		return organizationMapper.updateByPrimaryKeySelective(organization);
	}

	@Override
	public List<Organization> listAllOrg() {
		OrganizationExample example = new OrganizationExample();
		example.createCriteria().andStatusEqualTo(Constants.STATUS_NORMAL);
		return organizationMapper.selectByExample(example);
	}


    @Override
    public List<Organization> getAllChildOrgByParentCode(String orgCode) {
        return organizationMapper.getAllChildOrgByParentCode(orgCode);
    }

	@Override
	public List<Organization> listOrgsByArgs(HashMap<String, Object> args) {
		OrganizationExample example = new OrganizationExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constants.STATUS_NORMAL);
		if (args.get("orgName") != null) {
			criteria.andOrgNameEqualTo((String) args.get("orgName"));
		}
		if (args.get("parentOrgId") != null) {
			criteria.andParentOrgIdEqualTo(Integer.valueOf((String) args.get("parentOrgId")));
		}
		return organizationMapper.selectByExample(example);
	}

	@Override
	public String getLastOrgCodeByParentId(Integer parentOrgId) {
		return organizationMapper.getLastOrgCodeByParentId(parentOrgId);
	}

	@Override
	public boolean isExistOrg(String orgId, String parentOrgId, String orgName) {
		OrganizationExample example = new OrganizationExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constants.STATUS_NORMAL);
		criteria.andParentOrgIdEqualTo(Integer.parseInt(parentOrgId));
		criteria.andOrgNameEqualTo(orgName);
		if(StringUtils.isNoneBlank(orgId)){
			criteria.andOrgIdNotEqualTo(Integer.parseInt(orgId));
		}
		List<Organization> orgs = organizationMapper.selectByExample(example);
		if (orgs.size() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	@Override
	public String getOrgByServiceType(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String areacode = bizObj.getString("area_code");
		String servicetype = bizObj.getString("service_type");
		// "照料护理";
		String orgName = bizObj.getString("org_name");
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);
		try {
			if(StringUtils.isEmpty(areacode)){
				throw new Exception("城市编码为空！");
			}
			AreaExample areaExample = new AreaExample();
			com.yhcrt.iHealthCloud.entity.AreaExample.Criteria criteria = areaExample.createCriteria();
			criteria.andAreaCodeEqualTo(areacode);
			List<Area> areaList = areaMapper.selectByExample(areaExample);
			if(areaList.size()==0){
				throw new Exception("改编码的城市不存在！");
			}
			Area area = areaList.get(0);
			if("1".equals(area.getAreaType())){
				areacode = areacode.substring(0, areacode.length()-4);
			}else if("2".equals(area.getAreaType())){
				areacode = areacode.substring(0, areacode.length()-2);
			}
			List<Organization> organizationList;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				organizationList = organizationMapper.getOrgByServiceType(areacode,servicetype, orgName);
				PageInfo<Organization> pageInfo = new PageInfo<>(organizationList);
				setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
			} else {
				organizationList = organizationMapper.getOrgByServiceType(areacode,servicetype, orgName);
			}
			JSONArray organizationJsonList = new JSONArray();
			for(Organization org : organizationList){
				if(org.getOrgName()!=null && org.getOrgName().length()>5){
					org.setOrgName(org.getOrgName().substring(0, 5)+"...");
				}
				if(org.getAddress()!=null && org.getAddress().length()>5){
					org.setAddress(org.getAddress().substring(0, 5)+"...");
				}
				org.setOrgIntro("");
				JSONObject orgJson = (JSONObject) JSON.toJSON(org);
				orgJson.remove("parentOrg");
				ServiceExample example = new ServiceExample();
				// example.createCriteria().andOrgIdEqualTo(org.getOrgId()).andStatusEqualTo(Constants.STATUS_NORMAL);
				example.setOrderByClause("create_time desc");
				List<com.yhcrt.iHealthCloud.entity.Service> serviceList = serviceMapper.selectByExample(example);
				List<com.yhcrt.iHealthCloud.entity.Service> serviceTwoList = new ArrayList<>();
				if(serviceList.size()>0){
					for(int i= 0 ; i<serviceList.size(); i++){
						if(i<2){
							serviceTwoList.add(serviceList.get(i));
						}
					}
					orgJson.put("service", serviceTwoList);
				}
				organizationJsonList.add(orgJson);
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功");
			pdataObj.put(Const.TAG_BIZ, organizationJsonList);
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String getOrgByType(JSONObject pdataObj) {
		JSONObject bizObj = JSONObject.parseObject(pdataObj.getString(Const.TAG_BIZ));
		String areaname = bizObj.getString("area_name");
//				"武汉市";
		String orgName = bizObj.getString("org_name");
		String orgtype = bizObj.getString("org_type");
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);
		try {
			if(StringUtils.isEmpty(areaname)){
				throw new Exception("地区不能为空！");
			}
			List<Organization> organizationList;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				OrganizationExample orgexample = new OrganizationExample();
				orgexample.createCriteria().andOrgTypeEqualTo(orgtype).andAreaLike("%"+areaname+"%").andOrgNameLike("%"+orgName+"%");
				organizationList = organizationMapper.selectByExample(orgexample);
				PageInfo<Organization> pageInfo = new PageInfo<>(organizationList);
				setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
			} else {
				OrganizationExample orgexample = new OrganizationExample();
				orgexample.createCriteria().andOrgTypeEqualTo(orgtype).andAreaLike("%"+areaname+"%");
				organizationList = organizationMapper.selectByExample(orgexample);
			}
			JSONArray organizationJsonList = new JSONArray();
			for(Organization org : organizationList){
				if(org.getOrgName()!=null && org.getOrgName().length()>5){
					org.setOrgName(org.getOrgName().substring(0, 5)+"...");
				}
				if(org.getAddress()!=null && org.getAddress().length()>5){
					org.setAddress(org.getAddress().substring(0, 5)+"...");
				}
				org.setOrgIntro("");
				JSONObject orgJson = (JSONObject) JSON.toJSON(org);
				orgJson.remove("parentOrg");
				ServiceExample example = new ServiceExample();
				// example.createCriteria().andOrgIdEqualTo(org.getOrgId()).andStatusEqualTo(Constants.STATUS_NORMAL);
				example.setOrderByClause("create_time desc");
				List<com.yhcrt.iHealthCloud.entity.Service> serviceList = serviceMapper.selectByExample(example);
				List<com.yhcrt.iHealthCloud.entity.Service> serviceTwoList = new ArrayList<>();
				if(serviceList.size()>0){
					for(int i=0;i<serviceList.size();i++){
						if(i<2){
							serviceTwoList.add(serviceList.get(i));
						}
					}
					orgJson.put("service", serviceTwoList);
				}
				organizationJsonList.add(orgJson);
			}
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功");
			pdataObj.put(Const.TAG_BIZ, organizationJsonList);
		} catch (Exception e) {
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_FAIL);
			pdataObj.put(Const.TAG_RMK, e.getMessage());
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String getOrgDetail(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String orgid = biz.getString("org_id");
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, orgid)) {
			// 向数据库请求数据并判断是否分页
			Organization org = organizationMapper.selectByPrimaryKey(Integer.valueOf(orgid));
			if(org!=null){
				JSONObject orgjson = (JSONObject) JSON.toJSON(org);
				orgjson.remove("parentOrg");
				YwImageExample example = new YwImageExample();
				example.createCriteria().andRefIdEqualTo(org.getOrgId()).andModuleCodeEqualTo("org");
				orgjson.put("images", ywImageMapper.selectByExample(example));
				
				UserCommentExample commentExample = new UserCommentExample();
				commentExample.createCriteria().andRefIdEqualTo(org.getOrgId()).andCommentTypeEqualTo("org");
				commentExample.setOrderByClause("create_time desc");
				List<UserComment> commentListAll = userCommentMapper.selectByExample(commentExample);
				JSONArray commentJsonList = new JSONArray();
				for(UserComment userComment : commentListAll){
					JSONObject userCommentJson = (JSONObject) JSON.toJSON(userComment);
					Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(userComment.getMemberId()));
					userCommentJson.put("userLogo", member.getHeadPic());
					userCommentJson.put("nickName", member.getNickName());
					if(commentJsonList.size()<2){
						commentJsonList.add(userCommentJson);
					}
				}
				orgjson.put("comments", commentJsonList);
				orgjson.put("moreComments", commentListAll.size());
				ServiceExample serviceExample = new ServiceExample();
				// serviceExample.createCriteria().andOrgIdEqualTo(org.getOrgId()).andStatusEqualTo(Constants.STATUS_NORMAL);
				serviceExample.setOrderByClause("create_time desc");
				List<com.yhcrt.iHealthCloud.entity.Service> serviceList = serviceMapper.selectByExample(serviceExample);
				orgjson.put("services", serviceList);
				pdataObj.put(Const.TAG_BIZ, orgjson);
				pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
				pdataObj.put(Const.TAG_RMK, "获取成功！");
			}else{
				requestFailed(pdataObj,"不存在此机构");
			}
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String getOrgListByGps(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String lat = biz.getString("lat");
		String lgt = biz.getString("lgt");
		String orgtype = biz.getString("org_type");
		String servicetype = biz.getString("service_type");
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, lat,lgt)) {
			// 向数据库请求数据并判断是否分页
			Double latValue = Double.valueOf(lat);
			Double lgtValue = Double.valueOf(lgt);
			List<Organization> orgList = organizationMapper.getOrgListByGps(latValue,lgtValue,orgtype,servicetype);
			JSONArray organizationJsonList = new JSONArray();
			for(Organization org : orgList){
				if(org.getOrgName()!=null && org.getOrgName().length()>5){
					org.setOrgName(org.getOrgName().substring(0, 5)+"...");
				}
				if(org.getAddress()!=null && org.getAddress().length()>5){
					org.setAddress(org.getAddress().substring(0, 5)+"...");
				}
				org.setOrgIntro("");
				JSONObject orgJson = (JSONObject) JSON.toJSON(org);
				orgJson.remove("parentOrg");
				ServiceExample example = new ServiceExample();
				// example.createCriteria().andOrgIdEqualTo(org.getOrgId()).andStatusEqualTo(Constants.STATUS_NORMAL);
				example.setOrderByClause("create_time desc");
				List<com.yhcrt.iHealthCloud.entity.Service> serviceList = serviceMapper.selectByExample(example);
				List<com.yhcrt.iHealthCloud.entity.Service> serviceTwoList = new ArrayList<>();
				if(serviceList.size()>0){
					for(int i= 0 ; i<serviceList.size(); i++){
						if(i<2){
							serviceTwoList.add(serviceList.get(i));
						}
					}
					orgJson.put("service", serviceTwoList);
				}
				organizationJsonList.add(orgJson);
			}
			pdataObj.put(Const.TAG_BIZ, organizationJsonList);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功！");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getRecommendOrg(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		try {
			OrganizationExample example = new OrganizationExample();
			example.setOrderByClause("org_score desc");
			// 优秀服务机构
			PageHelper.startPage(1, 6);
			List<Organization> excellentOrgs = organizationMapper.selectByExample(example);
			// 养老机构
			PageHelper.startPage(1, 6);
			example.createCriteria().andOrgTypeEqualTo(Constants.OrgType.PENSION_ORG);
			List<Organization> pensionOrgs = organizationMapper.selectByExample(example);
			// 日间照料中心
			PageHelper.startPage(1, 6);
			example.createCriteria().andOrgTypeEqualTo(Constants.OrgType.DAY_CARE_CENTER);
			List<Organization> dayCareCenters = organizationMapper.selectByExample(example);
			// 居家养老服务组织
			PageHelper.startPage(1, 6);
			example.createCriteria().andOrgTypeEqualTo(Constants.OrgType.HOME_CARE_SERVICE);
			List<Organization> homeCareServiceOrgs = organizationMapper.selectByExample(example);
			bizObj.put("excellentOrgs", excellentOrgs);
			bizObj.put("pensionOrgs", pensionOrgs);
			bizObj.put("dayCareCenters", dayCareCenters);
			bizObj.put("homeCareServiceOrgs", homeCareServiceOrgs);
			jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String getOrgs(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String orgType = bizObj.getString("org_type");
		String orgNature = bizObj.getString("org_nature");
		String area = bizObj.getString("area");
		String currentPage = bizObj.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = bizObj.getString(Const.TAG_PAGE_SIZE);
		
		OrganizationExample example = new OrganizationExample();
		example.setOrderByClause("create_time desc");
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(orgType)) {
			criteria.andOrgTypeEqualTo(orgType);
		}
		if (StringUtils.isNotBlank(orgNature)) {
			criteria.andOrgNatureEqualTo(orgNature);
		}
		if (StringUtils.isNotBlank(area)) {
			criteria.andAreaLike("%"+area+"%");
		}
		try {
			PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
			List<Organization> orgList = organizationMapper.selectByExample(example);
			PageInfo<Organization> pageInfo = new PageInfo<Organization>(orgList);
			setPagingData(jsonObject, pageInfo.getPages(), pageInfo.getPageNum());
			requestSucceed(jsonObject, orgList, "");
		} catch (Exception e) {
			requestFailed(jsonObject, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String getOrgsFilterItem(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		SysDictionaryExample example = new SysDictionaryExample();
		// 机构类型
		example.createCriteria().andDictEnNameEqualTo("org_type");
		List<SysDictionary> orgTypes = sysDictMappper.selectByExample(example);
		// 服务类型
		example.clear();
		example.createCriteria().andDictEnNameEqualTo("org_nature");
		List<SysDictionary> orgNatures = sysDictMappper.selectByExample(example);
		// 地区列表,目前暂取武汉市各区
		final Integer whAreaId = 1004;
		List<Area> areas = areaService.getChildAreaByParentId(whAreaId);
		
		bizObj.put("orgTypes", orgTypes);
		bizObj.put("serviceTypes", orgNatures);
		bizObj.put("areas", areas);
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		return toJsonStringWithOutNull(jsonObject);
	}
	
}