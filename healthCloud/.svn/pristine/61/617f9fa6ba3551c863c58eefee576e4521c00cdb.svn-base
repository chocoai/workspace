/**
 * 
 */
package com.yhcrt.healthcloud.base.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.healthcloud.organization.entity.Doctor;
import com.yhcrt.healthcloud.organization.entity.Employee;
import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.organization.service.DoctorService;
import com.yhcrt.healthcloud.organization.service.EmployeeService;
import com.yhcrt.healthcloud.organization.service.OrganizationService;
import com.yhcrt.healthcloud.provider.entity.ServiceProvider;
import com.yhcrt.healthcloud.provider.service.ProviderService;
import com.yhcrt.healthcloud.system.entity.SysDictionary;
import com.yhcrt.healthcloud.system.entity.SysDictionaryExample;
import com.yhcrt.healthcloud.system.entity.SysRole;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.mapper.SysDictionaryMapper;
import com.yhcrt.healthcloud.system.service.SysRoleService;
import com.yhcrt.healthcloud.system.service.SysSequenceService;
import com.yhcrt.healthcloud.system.service.UserRoleService;

/**
 * 控制器基类
 * 
 */
public abstract class BaseController {
	
	@Autowired
	protected SysRoleService sysRoleService;
	@Autowired
	protected UserRoleService userRoleService;
	@Autowired
    private DoctorService doctorService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    protected OrganizationService organizationService;
    @Autowired
    protected ProviderService providerService;
    @Autowired	
    protected SysDictionaryMapper sysDictionaryMapper;
    @Autowired
	protected SysSequenceService sysSequenceService;
    
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @Title: getLoginUser
     * @Description: 获取登录用户
     * @return
     */
    public SysUser getLoginUser(){
        SysUser loginUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        return loginUser;
    }
    
    /**
     * 根据当前登录人查询能分配角色
     * @return
     */
    public List<Integer> getRoleIdList(){
    	List<Integer> roleIds =new ArrayList<Integer>();
    	//根据当前登录用户查询其角色
        List<Integer> roleList=userRoleService.queryByUid(getLoginUser().getUserId());
        if(!roleList.isEmpty()){
        	//查询其角色查询其能分配的角色
        	List<SysRole> childList = sysRoleService.queryByList(roleList);
        	if(!childList.isEmpty()){
        		Set<String> set = new HashSet<String>();
                for (SysRole sysRole: childList) {
                	if(StringUtils.isNotBlank(sysRole.getIsChild())){
                		String[] ids = sysRole.getIsChild().split(",");
    					for (String id : ids) {
    						if(StringUtils.isNotBlank(id) && set.add(id.trim())){
    							roleIds.add(Integer.parseInt(id.trim()));
                         	}
    					}
                	}
                }
        	}
        }
		return roleIds;
    }
    
    /**
     * @Title: getLoginInformation
     * @Description: 获取当前登录用户的员工或医师对象
     * @param loginUser
     * @return
     */
    public Map<Integer,Object> getLoginInformation(SysUser loginUser){
        Map<Integer,Object> map = new HashMap<Integer,Object>();
        if(loginUser.getUserType()==-1){
            List<Doctor> doc = doctorService.selectByUserId(loginUser.getUserId());
            map.put(-1, doc.size()>0?doc.get(0):null);
        }else if(loginUser.getUserType()==0){
            List<Employee> emp = employeeService.selectByUserId(loginUser.getUserId());
            map.put(0, emp.size()>0?emp.get(0):null);
        }else{
            return null;
        }
        return map;
    }
    
    /**
     * @Title: getLoginOrgCode
     * @Description: 获取登录用户机构
     * @param loginUser
     * @return
     */
    public Organization getLoginOrg(SysUser loginUser){
        Integer orgId = null;
        Map<Integer,Object> map = getLoginInformation(loginUser);
        if(map==null){
            return null;
        }
        try{
            if(map.get(-1)!=null){
                orgId = ((Doctor)map.get(-1)).getOrgId();
            }
        } catch(Exception e){
            System.out.println("类型转换异常，获取用户的医师信息失败");
            orgId = null;
        }
        try{
            if(map.get(0)!=null){
                orgId = ((Employee)map.get(0)).getOrgId();
            }
        } catch(Exception e){
            System.out.println("类型转换异常，获取用户的员工信息失败");
            orgId = null;
        }
        Organization org = organizationService.selectByPrimaryKey(orgId);
        if(org==null){
            org = new Organization();
        }
        return org;
    }
	    
    /**
     * @Title: getRealName
     * @Description: 获取真实姓名
     * @param loginUser
     * @return
     */
    public String getRealName(SysUser loginUser){
        String userName;
        if(loginUser.getEmp()!=null){
            userName = loginUser.getEmp().getRealName();
        }else if(loginUser.getDoc()!=null){
            userName = loginUser.getDoc().getRealName();
        }else{
            userName = "匿名";
        }
        return userName;
    }
    
    /**
     * @Title: getOrgList
     * @Description: 获取一个机构ID的集合，规则为如果参数为null，则查询出所有登录用户所属机构及子机构的ID，否则将该参数放入list
     * @param orgId
     * @return
     */
    public List<Integer> getOrgList(Integer orgId){
    	String orgCode = null;
    	Organization org = new Organization();
        //机构ID的集合
        List<Integer> orgId_list = new ArrayList<Integer>();
        if(orgId==null){
            //获取登录用户
            SysUser loginUser = getLoginUser();
            //获取当前登录用户的机构
            orgCode = getLoginOrg(loginUser).getOrgCode();
        }else{
        	//根据orgId查询用户所属机构及子机构的ID
        	org = organizationService.selectByPrimaryKey(orgId);
        	orgCode = org.getOrgCode();
        }
        //通过orgCode获取该机构及该机构所有的下级机构
        List<Organization> org_list = organizationService.getAllChildOrgByParentCode(orgCode);
        for(int i=0;i<org_list.size();i++){
            //获取查询出的机构对象的ID并存入到ID条件集合中
            orgId_list.add(i, org_list.get(i).getOrgId());
        }
        return orgId_list;
    }
    
    /**
     * 获取当前登录用户下及其子孙机构所有的服务供应商集合
     * @return
     */
    public List<ServiceProvider> getProList(){
        //机构ID的集合
        List<Integer> orgId_list = new ArrayList<Integer>();
        //获取登录用户
        SysUser loginUser = getLoginUser();
        //获取当前登录用户的机构
        String orgCode = getLoginOrg(loginUser).getOrgCode();
        //通过orgCode获取该机构及该机构所有的下级机构
        List<Organization> org_list = organizationService.getAllChildOrgByParentCode(orgCode);
        for(int i=0;i<org_list.size();i++){
            orgId_list.add(i, org_list.get(i).getOrgId());
        }
        //根据集合查询机构服务商
        return providerService.queryByOrgList(orgId_list);
    }
    
    //二级联动下拉查询
    protected List<SysDictionary> getTypeItem(String type, String category) {
    	SysDictionaryExample example = new SysDictionaryExample();
        example.createCriteria().andDictEnNameEqualTo(type).andDictValueEqualTo(category).andStatusEqualTo(0);
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
    
}
