package com.yhcrt.healthcloud.health.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.DataTable;
import com.yhcrt.healthcloud.health.entity.AlarmMsg;
import com.yhcrt.healthcloud.health.entity.HdBloodPressure;
import com.yhcrt.healthcloud.health.entity.HdPulse;
import com.yhcrt.healthcloud.health.entity.HdSleep;
import com.yhcrt.healthcloud.health.entity.HdStep;
import com.yhcrt.healthcloud.health.service.AlarmMsgService;
import com.yhcrt.healthcloud.health.service.HealthDataService;
import com.yhcrt.healthcloud.health.service.MemberQueryService;
import com.yhcrt.healthcloud.health.vo.MemberListItem;
import com.yhcrt.healthcloud.memberBack.entity.MemberBack;
import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.organization.service.OrganizationService;

/**
 * 当期模块主页面/会员列表Controller
 * 
 * @author huzelin
 * 
 */
@Controller
@RequestMapping("/healthService")
public class MainMemberListController extends BaseController {

	@Autowired
	private MemberQueryService memberQueryService;
	@Autowired
	private HealthDataService healthDataService;
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private AlarmMsgService alarmMsgService;

	@RequestMapping(value = "/list")
	public String memberList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgId = request.getParameter("orgId");
		String realName = request.getParameter("realName");
		if(StringUtils.isNotBlank(realName)){
			realName = realName.trim();
		}
		String idCard = request.getParameter("idCard");
		if(StringUtils.isNotBlank(idCard)){
			idCard = idCard.trim();
		}
		// 获取机构
		orgId = StringUtils.isBlank(orgId) ? getLoginOrg(getLoginUser()).getOrgId().toString() : orgId;
		List<Integer> orgList = getOrgList(Integer.parseInt(orgId));
		Organization org = orgService.selectByPrimaryKey(Integer.parseInt(orgId));
		List<MemberBack> memberList = memberQueryService.getMemberByCondition(orgList, realName, idCard);
		// 传入数据
		modelMap.addAttribute("org", org);
		modelMap.addAttribute("items", initVoList(memberList));
		modelMap.addAttribute("realName", realName);
		modelMap.addAttribute("idCard", idCard);
		return "/health/jsp/member_list";
	}

	/**
	 * 健康预警分页
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param page
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/amlist")
	public String amlist(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgId = request.getParameter("orgId");
        Organization selectOrg = new Organization();
        if(StringUtils.isBlank(orgId)){
        	selectOrg = getLoginOrg(getLoginUser());
        }else{
        	selectOrg = orgService.selectByPrimaryKey(Integer.parseInt(orgId));
        }
        String realName = request.getParameter("realName");
		if (StringUtils.isNotBlank(realName)) {
			modelMap.addAttribute("realName", realName.trim());
		}
		String idCard = request.getParameter("idCard");
		if (StringUtils.isNotBlank(idCard)) {
			modelMap.addAttribute("idCard", idCard.trim());
		}
		modelMap.addAttribute("org", selectOrg);
		return "/health/jsp/alarm_list";
	}
	
	/**
	 * 返回表格查询数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/pageList")
	public void pageList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String orgId = request.getParameter("orgId");
		String realName = request.getParameter("realName");
		if (StringUtils.isNotBlank(realName)) {
			map.put("realName", realName.trim());
		}
		String idCard = request.getParameter("idCard");
		if (StringUtils.isNotBlank(idCard)) {
			map.put("idCard", idCard.trim());
		}
		try {
			DataTable dataTable = new DataTable(request);
			List<Integer> orgId_list = getOrgList(StringUtils.isNotBlank(orgId) ? Integer.parseInt(orgId) : null);
			map.put("orgId_list", orgId_list);
			PageHelper.startPage(dataTable.getPageNum(), dataTable.getPageSize());
			List<AlarmMsg> amList = alarmMsgService.getAlarmMsgsByMap(map);
			PageInfo<AlarmMsg> pageInfo = new PageInfo<AlarmMsg>(amList);
			// 封装数据给DataTables
			dataTable.setDraw(dataTable.getDraw());
			dataTable.setData(pageInfo.getList());
			dataTable.setRecordsTotal((int) pageInfo.getTotal());
			dataTable.setRecordsFiltered(dataTable.getRecordsTotal());
			String jsonString = JSON.toJSONString(dataTable);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化vo集合
	 * 
	 * @param memberList 会员集合
	 * @return vo 集合
	 */
	public List<MemberListItem> initVoList(List<MemberBack> memberList) {
		// 创建vo集合
		List<MemberListItem> items = new ArrayList<>();
		// 遍历会员集合
		for (MemberBack member : memberList) {
			// 创建vo
			MemberListItem item = new MemberListItem();
			// 绑定会员对象
			item.setMember(member);
			int memberId = member.getMemberId();
			// 最近一次脉搏数据
			HdPulse pulseLatest = healthDataService.getPulseLatest(memberId);
			if (pulseLatest != null) {
				item.setPulse(pulseLatest);
			}
			// 最近一次计步数据
			HdStep stepLatest = healthDataService.getStepLatest(memberId);
			if (stepLatest != null) {
				item.setStep(stepLatest);
			}
			// 最近一次深度睡眠数据
			HdSleep sleepLatest = healthDataService.getSleepLatest(memberId);
			if (sleepLatest != null) {
				item.setSleep(sleepLatest);
			}
			// 最近一次血压数据
			HdBloodPressure pressureLatest = healthDataService.getPressureLatest(memberId);
			if (pressureLatest != null) {
				item.setPressure(pressureLatest);
			}
			// 添加至vo集合
			items.add(item);
		}
		return items;
	}
}
