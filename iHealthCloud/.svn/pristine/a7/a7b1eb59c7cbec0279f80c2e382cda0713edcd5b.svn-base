package com.yhcrt.iHealthCloud.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.entity.Doctor;
import com.yhcrt.iHealthCloud.entity.DoctorExample;
import com.yhcrt.iHealthCloud.entity.Employee;
import com.yhcrt.iHealthCloud.entity.EmployeeExample;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.MemberExample;
import com.yhcrt.iHealthCloud.mapper.DoctorMapper;
import com.yhcrt.iHealthCloud.mapper.EmployeeMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.pojo.MemberDTO;
import com.yhcrt.iHealthCloud.service.ManagerService;
import com.yhcrt.iHealthCloud.util.Const;

/**
 * 
 * @author huzelin
 *
 */
@Service
public class ManagerServiceImpl extends BaseService implements ManagerService {

	@Autowired
	private DoctorMapper docMapper;
	@Autowired
	private EmployeeMapper empMapper;
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public String getMemberListByOrgId(JSONObject pdataObj) {

		// 获取参数
		String orgId = getOrgId(pdataObj);

		// 判断参数是否为空
		if (judgeAgumentsIsLegal(pdataObj, orgId)) {

			// 向数据库请求数据
			MemberExample example = new MemberExample();
			example.createCriteria().andOrgIdEqualTo(Integer.parseInt(orgId));
			example.setOrderByClause("create_time desc");
			List<MemberDTO> list = memberMapper.listMemberByExample(example);
			requestSucceed(pdataObj, list, "");

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getMemberByCondition(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		// 获取查询条件
		String orgId = getOrgId(pdataObj);
		String param = bizObj.getString("param");
		// 判断参数是否为空
		if (judgeAgumentsIsLegal(pdataObj, orgId)) {
			if (param == null)
				param = "";
			// 向数据库请求数据
			MemberExample example = new MemberExample();
			example.or().andOrgIdEqualTo(Integer.parseInt(orgId)).andRealNameLike("%" + param + "%");
			example.or().andOrgIdEqualTo(Integer.parseInt(orgId)).andIdentityCardLike("%" + param + "%");
			example.setOrderByClause("member_id desc");
			List<MemberDTO> list = memberMapper.listMemberByExample(example);
			requestSucceed(pdataObj, list, "");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getMemberByMemberId(JSONObject pdataObj) {

		// 获取参数
		String memberId = getMemberId(pdataObj);

		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId)) {

			Member member = memberMapper.selectByPrimaryKey(Integer.parseInt(memberId));
			requestSucceed(pdataObj, JSON.toJSON(member), "");

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getEmployeeListByOrgId(JSONObject pdataObj) {
		// 获取参数
		String orgId = getOrgId(pdataObj);
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, orgId)) {
			// 向数据库请求数据并判断是否分页
			List<Employee> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = selectEmployeeListByOrgId(orgId);
				PageInfo<Employee> pageInfo = new PageInfo<>(list);
				setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
			} else {
				list = selectEmployeeListByOrgId(orgId);
			}
			requestSucceed(pdataObj, JSON.toJSON(list), "");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	private List<Employee> selectEmployeeListByOrgId(String orgId) {
		EmployeeExample example = new EmployeeExample();
		example.createCriteria().andOrgIdEqualTo(Integer.parseInt(orgId));
		List<Employee> empList = empMapper.selectByExample(example);
		return empList;
	}

	@Override
	public String getDoctorListByOrgId(JSONObject pdataObj) {
		// 获取参数
		String orgId = getOrgId(pdataObj);
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);
		// 判断参数是否为空
		if (judgeAgumentsIsLegal(pdataObj, orgId)) {
			// 向数据库请求数据并判断是否分页
			List<Doctor> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = selectDoctorListByOrgId(orgId);
				PageInfo<Doctor> p = new PageInfo<>(list);
				setPagingData(pdataObj, p.getPages(), p.getPageNum());
			} else {
				list = selectDoctorListByOrgId(orgId);
			}
			requestSucceed(pdataObj, JSON.toJSON(list), "");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	private List<Doctor> selectDoctorListByOrgId(String orgId) {
		DoctorExample docExample = new DoctorExample();
		docExample.createCriteria().andOrgIdEqualTo(Integer.parseInt(orgId));
		List<Doctor> doctorList = docMapper.selectByExample(docExample);
		return doctorList;
	}

	@Override
	public String getLinkmansByOrgId(JSONObject pdataObj) {
		String orgId = getOrgId(pdataObj);
		if (StringUtils.isBlank(orgId)) {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(pdataObj);
		}
		MemberExample example = new MemberExample();
		example.createCriteria().andOrgIdEqualTo(Integer.parseInt(orgId));
		List<MemberDTO> members = memberMapper.listMemberByExample(example);
		
		EmployeeExample employeeExample = new EmployeeExample();
		employeeExample.createCriteria().andOrgIdEqualTo(Integer.parseInt(orgId));
		List<Employee> emps = empMapper.selectByExample(employeeExample);
		
		DoctorExample doctorExample = new DoctorExample();
		doctorExample.createCriteria().andOrgIdEqualTo(Integer.parseInt(orgId));
		List<Doctor> doctors = docMapper.selectByExample(doctorExample);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("members", members);
		jsonObj.put("emps", emps);
		jsonObj.put("doctors", doctors);
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		pdataObj.put(Const.TAG_BIZ, jsonObj);
		return toJsonStringWithOutNull(pdataObj);
	}

}
