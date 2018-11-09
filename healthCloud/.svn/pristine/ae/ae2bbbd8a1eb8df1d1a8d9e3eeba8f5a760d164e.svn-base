package com.yhcrt.healthcloud.memberBack.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.common.DataTable;
import com.yhcrt.healthcloud.mall.entity.Service;
import com.yhcrt.healthcloud.mall.entity.ServiceExample;
import com.yhcrt.healthcloud.mall.entity.WorkOrder;
import com.yhcrt.healthcloud.mall.mapper.ServiceMapper;
import com.yhcrt.healthcloud.mall.mapper.WorkOrderMapper;
import com.yhcrt.healthcloud.memberBack.entity.MemberBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberContact;
import com.yhcrt.healthcloud.memberBack.entity.MemberDeviceBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberRelationshipBack;
import com.yhcrt.healthcloud.memberBack.service.MemberBackService;
import com.yhcrt.healthcloud.memberBack.service.MemberContactService;
import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.system.entity.SysDictionary;
import com.yhcrt.healthcloud.system.entity.SysDictionaryExample;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.service.SysUserService;
import com.yhcrt.healthcloud.util.DateUtil;

/* @Description: 后台会员管理模块
 * @version	1.0		2017年5月22日
 * @author jimmy
*/
@Controller
@RequestMapping("/member")
public class MemberBackController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private MemberBackService memberBackService;
	@Autowired	
    private MemberContactService memberContactService;
	@Autowired	
    private ServiceMapper serviceMapper;
	@Autowired	
    private WorkOrderMapper workOrderMapper;
	

	/**
	 * @Title: 根据条件查询会员
	 * @Description: 根据传入的条件查询出对应的会员信息，将结果集返回到list页面。
	 * @param request
	 * @param response
	 * @param orgId
	 * @param userCode
	 * @param realName
	 * @param specialty
	 * @param status
	 * @return 携带结果集返回到会员列表页
	 */
	@RequestMapping("/list")
	public String search(HttpServletRequest request, HttpServletResponse response, Integer status, Integer orgId,
			String realName, String imei, String identity_card, Integer reqType) {
		// 机构ID的集合
		List<Integer> orgId_list = getOrgList(orgId);
		Organization selectOrg;
        if(orgId == null){
        	selectOrg = getLoginOrg(getLoginUser());
        }else{
        	selectOrg = organizationService.selectByPrimaryKey(orgId);
        }
		List<MemberBack> list = memberBackService.search(orgId_list, realName, imei, identity_card, status);
		request.setAttribute("member_list", list);
		request.setAttribute("reqType", reqType);
		request.setAttribute("org", selectOrg);
		request.setAttribute("imei", imei);
		request.setAttribute("identity_card", identity_card);
		request.setAttribute("realName", realName);
		if (status != null && status == 1) {
			return "member/disabled";
		} else {
			return "member/list";
		}
	}
	
	/**
	 * 根据memberId查询数据跳转编辑界面
	 * @param request
	 * @param response
	 * @param memberId
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, HttpServletResponse response, Integer memberId) {
		MemberBack member = memberBackService.queryByMemberId(memberId);
		request.setAttribute("member", member);
		return "member/edit";
	}
	
	/**
	 * 编辑保存
	 * @param request
	 * @param response
	 * @param attr
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr, MemberBack member) {
		Integer i = 0;
		try {
			member.setUpdateTime(DateUtil.getDateTime());
			memberBackService.updateByMemberAndInfo(member);
			i=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (i == 1) {
			attr.addFlashAttribute("state", "SUCCESS");
		} else{
			attr.addFlashAttribute("state", "FALSE");
		}
		return "redirect:list";
	}
	
	/**
	 * 查询未绑定机构的会员
	 * @param request
	 * @param response
	 * @param realName
	 * @param identity_card
	 * @param page
	 * @return
	 */
	@RequestMapping("/unbandList")
	public String unbandList(HttpServletRequest request, HttpServletResponse response) {
		String realName = request.getParameter("realName");
		String identityCard = request.getParameter("identityCard");
		String phoneNo = request.getParameter("phoneNo");
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isNotBlank(realName)){
			realName = realName.trim();
			map.put("realName", realName);
		}
		if(StringUtils.isNotBlank(identityCard)){
			identityCard = identityCard.trim();
			map.put("identityCard", identityCard);
		}
		if(StringUtils.isNotBlank(phoneNo)){
			phoneNo = phoneNo.trim();
			map.put("phoneNo", phoneNo);
		}
		List<MemberBack> list = memberBackService.queryByNameAndCard(map);
		request.setAttribute("member_list", list);
		request.setAttribute("identityCard", identityCard);
		request.setAttribute("realName", realName);
		request.setAttribute("phoneNo", phoneNo);
		return "member/unboundList";
	}

	/**
	 * @Title: 禁用会员
	 * @Description: 禁用选中的会员，即将该会员的状态改为1
	 * @param request
	 * @param response
	 * @param member
	 * @return 禁用结果
	 */
	@RequestMapping("/disabled")
	@ResponseBody
	public String disabled(HttpServletRequest request, HttpServletResponse response, Integer memberId) {
		MemberBack member = memberBackService.select(memberId);
		SysUser user = sysUserService.selUser(member.getUserId());
		int i = 0;
		try {
			member.setUpdateTime(sdf.format(new Date()));
			user.setUpdateTime(sdf.format(new Date()));
			i = memberBackService.delete(member, user);
		} catch (NullPointerException n) {
			System.out.println("没有对应的user对象");
			i = 0;
		} catch (RuntimeException r) {
			System.out.println("新增异常，开始回滚");
			i = 0;
		}
		if (i == 1) {
			return "禁用成功";
		} else {
			return "禁用失败";
		}
	}

	/**
	 * @Title: 恢复被禁用的会员
	 * @Description: 恢复选中的被禁用会员，即将该会员的状态改为1
	 * @param request
	 * @param response
	 * @param member
	 * @return 恢复结果
	 */
	@RequestMapping("/recover")
	@ResponseBody
	public String recover(HttpServletRequest request, HttpServletResponse response, Integer memberId) {
		MemberBack member = memberBackService.select(memberId);
		SysUser user = sysUserService.selUser(member.getUserId());
		member.setUpdateTime(sdf.format(new Date()));
		user.setUpdateTime(sdf.format(new Date()));
		int i = 0;
		try {
			i = memberBackService.recover(member, user);
		} catch (RuntimeException r) {
			System.out.println("新增异常，开始回滚");
			i = 0;
		}
		if (i == 1) {
			return "恢复成功";
		} else {
			return "恢复失败";
		}
	}

	/**
	 * @Title: deviceList
	 * @Description: 用户的设备列表页
	 * @param request
	 * @param response
	 * @param memberId
	 * @param page
	 * @return
	 */
	@RequestMapping("/deviceList")
	public String deviceList(HttpServletRequest request, HttpServletResponse response, Integer memberId) {
		MemberBack member = memberBackService.select(memberId);
		request.setAttribute("member", member);
		return "member/deviceList";
	}
	
	/**
	 * 返回表格查询数据
	 * @param request
	 * @param response
	 * @param memberId
	 */
	@RequestMapping(value = "/pageList")
	public void pageList(HttpServletRequest request, HttpServletResponse response, Integer memberId) {
		try {
			DataTable dataTable = new DataTable(request);
			PageHelper.startPage(dataTable.getPageNum(), dataTable.getPageSize());
			List<MemberDeviceBack> deviceList = memberBackService.selectDeviceByMemberId(memberId);
			PageInfo<MemberDeviceBack> pageInfo = new PageInfo<MemberDeviceBack>(deviceList);
			//封装数据给DataTables  
			dataTable.setDraw(dataTable.getDraw());  
			dataTable.setData(pageInfo.getList());
			dataTable.setRecordsTotal((int)pageInfo.getTotal());    
			dataTable.setRecordsFiltered(dataTable.getRecordsTotal()); 
			String jsonString = JSON.toJSONString(dataTable);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: relation
	 * @Description: 为用户服务的人员
	 * @param request
	 * @param response
	 * @param memberId
	 * @param page
	 * @return
	 */
	@RequestMapping("/org_relation")
	public String relation(HttpServletRequest request, HttpServletResponse response, Integer memberId) {
		MemberBack member = memberBackService.select(memberId);
		List<MemberBack> list = memberBackService.getByMemberId(memberId);
		request.setAttribute("memberList", list);
		request.setAttribute("member", member);
		return "member/memberRelation";
	}

	/**
	 * @Title: memberFollow
	 * @Description: 该会员关注的会员
	 * @return
	 */
	@RequestMapping("/memberFollow")
	public String memberFollow(HttpServletRequest request, HttpServletResponse response, Integer memberId) {
		MemberBack member = memberBackService.select(memberId);
		List<MemberRelationshipBack> memberFollow = memberBackService.selectMemberFollow(memberId);
		request.setAttribute("member", member);
		request.setAttribute("memberFollow", memberFollow);
		return "member/memberFollow";
	}

	/**
	 * @Title: followMember
	 * @Description: 关注该会员的会员
	 * @return
	 */
	@RequestMapping("/followMember")
	public String followMember(HttpServletRequest request, HttpServletResponse response, Integer memberId) {
		MemberBack member = memberBackService.select(memberId);
		List<MemberRelationshipBack> followMember = memberBackService.selectFollowMember(memberId);
		request.setAttribute("member", member);
		request.setAttribute("followMember", followMember);
		return "member/followMember";
	}
	
	/**
	 * 根据memberId查询会员个人信息
	 * @param request
	 * @param response
	 * @param memberId
	 * @param status
	 * @return
	 */
	@RequestMapping("/memberInformation")
	public String memberInformation(HttpServletRequest request, HttpServletResponse response, Integer memberId, String type) {
		MemberBack member = memberBackService.queryByMemberId(memberId);
		List<MemberContact> memberContactList = memberContactService.queryByMemberId(memberId);
		request.setAttribute("member", member);
		request.setAttribute("memberContactList", memberContactList);
		request.setAttribute("type", type);
		return "member/memberInformation";
	}
	
	@RequestMapping("/memberWindow")
	public String memberWindow(HttpServletRequest request, HttpServletResponse response, String tel) {
		MemberBack member = memberBackService.queryByTel(tel);
		if(member==null){
			member = new MemberBack();
			member.setPhoneNo(tel);
			member.setMemberType(9); //页面规定9代表未知类型
			member.setOrg(new Organization());
			member.setUser(new SysUser());
		}
		request.setAttribute("service", new Service());
		request.setAttribute("member", member);
		return "member/memberWindow";
	}
	
    @RequestMapping(value = "/getTypeItem", method = RequestMethod.POST)	
    @ResponseBody
    public List<SysDictionary> getTypeItem(HttpServletRequest request,HttpServletResponse response,String parentValue,String type){	
       SysDictionaryExample example = new SysDictionaryExample();
       example.createCriteria().andDictEnNameEqualTo(type).andDictValueEqualTo(parentValue).andStatusEqualTo(0);
       List<SysDictionary> list = sysDictionaryMapper.selectByExample(example);
       List<SysDictionary> list2 = new ArrayList<SysDictionary>();
       if(list.size()>0){
    	   SysDictionary sysDictionary = list.get(0);
    	   example = new SysDictionaryExample();
           example.createCriteria().andParentIdEqualTo(String.valueOf(sysDictionary.getDictId())).andStatusEqualTo(0);
           list2 = sysDictionaryMapper.selectByExample(example);
       }
       return list2;		
    }
    
    @ResponseBody
    @RequestMapping(value = "/getServiceItem", method = RequestMethod.POST)	
    public JSONArray getServiceItem(HttpServletRequest request,HttpServletResponse response,String serviceType){	
       ServiceExample example = new ServiceExample();
       example.createCriteria().andServiceTypeEqualTo(serviceType).andStatusEqualTo(Constants.STATUS_NORMAL);
       List<Service> list = serviceMapper.selectByExample(example);
       JSONArray jsonlist = new JSONArray();
       for(Service service : list){
    	   JSONObject servicejson = new JSONObject();
    	   servicejson.put("serviceName", service.getServiceName());
    	   servicejson.put("servicePrice", service.getUnitPrice());
    	   servicejson.put("serviceUnit", service.getCext1());
    	   servicejson.put("serviceOrg", service.getProvider().getProviderName());
    	   servicejson.put("serviceId", service.getServiceId());
    	   jsonlist.add(servicejson);
       }
       return jsonlist;		
    }
    
    @ResponseBody
    @RequestMapping(value = "/creatOrderFromTel", method = RequestMethod.POST)	
    public JSONObject creatOrderFromTel(String memberId,String serviceId,String serviceObject,String phoneNo,String startTime,String address,String remark){	
      WorkOrder order = new WorkOrder();
      JSONObject result = new JSONObject();
      result.put("success", true);
      if(StringUtils.isEmpty(serviceId)){
    	  result.put("success", false);
    	  return null;		
      }
      Service service  = serviceMapper.selectByPrimaryKey(Integer.valueOf(serviceId));
      int orderId = sysSequenceService.getSequenceValue("work_order");
      order.setOrderId(orderId);
      order.setServiceId(service.getServiceId());
      order.setServiceName(service.getServiceName());
      order.setServiceObject(serviceObject);
      order.setUnitPrice(Double.valueOf(service.getUnitPrice()));
      order.setAddress(address);
      if(!StringUtils.isEmpty(memberId)){
     	order.setMemberId(Integer.valueOf(memberId));
      }
      order.setStartTime(startTime);
      order.setTel(phoneNo);
      order.setOrderContent(service.getServiceName());
      order.setAmount(1);
      order.setPayStatus(0);   //0线下付款  1线上付款	
      order.setOrderStatus(2);  //状态直接进行中
      order.setCext3(remark); 
      order.setCreateTime(DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss"));
      workOrderMapper.insert(order);
      return result;
    }
   

}
