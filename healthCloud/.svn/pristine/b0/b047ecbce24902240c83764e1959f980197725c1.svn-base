package com.yhcrt.healthcloud.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.healthcloud.organization.entity.Doctor;
import com.yhcrt.healthcloud.organization.entity.Employee;
import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.organization.service.DoctorService;
import com.yhcrt.healthcloud.organization.service.EmployeeService;
import com.yhcrt.healthcloud.organization.service.OrganizationService;
import com.yhcrt.healthcloud.system.entity.SysUser;

/* @Description: Controller中常用的方法
 * @version	1.0		2017年5月31日
 * @author jimmy
*/
public class CommonMethod {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private OrganizationService organizationService;

    /**
     * @Title: getLoginUser
     * @Description: 获取登录用户
     * @return
     */
    public SysUser getLoginUser(HttpServletRequest request,HttpServletResponse response){
        SysUser loginUser = (SysUser) request.getSession().getAttribute("loginUser");
        return loginUser;
    }
    
    public String getLoginOrgCode(HttpServletRequest request){
        return (String) request.getSession().getAttribute("orgCode");
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
     * @Description: 获取当前登录用户的机构Code
     * @param loginUser
     * @return
     */
    public String getLoginOrgCode(SysUser loginUser){
        String orgCode = null;
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
        if(org!=null){
            orgCode = org.getOrgCode();
        }
        return orgCode;
    }
    
    /**
     * @Title: getLoginOrgCode
     * @Description: 获取登录用户机构
     * @param loginUser
     * @return
     */
    public Organization getLoginOrgId(SysUser loginUser){
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
    
/*    @RequestMapping(value = "/getOrgTree", method = RequestMethod.POST)
    public void getOrgTree(HttpServletRequest request, HttpServletResponse response){
        //获取当前登录用户的机构
        String orgCode = getLoginOrgCode(request);
        //通过orgCode获取该机构及该机构所有的下级机构
        List<Organization> org_list = organizationService.getAllChildOrgByParentCode(orgCode);
        //将机构数据转换为jsonarray形式
        JSONArray jsonArray = new JSONArray();
        try {
            for (Organization org : org_list) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("id", org.getOrgId());
                jsonObj.put("name", org.getOrgName());
                jsonObj.put("pId", org.getParentOrgId());
                jsonArray.add(jsonObj);
            }
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
}
