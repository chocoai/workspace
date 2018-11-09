package com.yhcrt.healthcloud.system.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.organization.entity.Doctor;
import com.yhcrt.healthcloud.organization.entity.Employee;
import com.yhcrt.healthcloud.organization.service.DoctorService;
import com.yhcrt.healthcloud.organization.service.EmployeeService;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.service.SysUserService;
import com.yhcrt.healthcloud.util.DateUtil;
import com.yhcrt.healthcloud.util.Md5PwdEncoder;
@Controller
@RequestMapping("/personal")
public class PersonalSetController extends BaseController{
	
	@Autowired
	private EmployeeService employeeService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private DoctorService doctorService;
    /**
     * 进入个人设置页面
     * @param request
     * @param response
     * @return personal/index
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request,HttpServletResponse response){
    	SysUser user = getLoginUser();
    	if(getLoginInformation(user)==null){
            return null;
        }
        try{
            if(getLoginInformation(user).get(-1)!=null){
            	Doctor doc  = ((Doctor)getLoginInformation(user).get(-1));
            	request.setAttribute("doc", doc);
            	return "personal/index";
            }
        } catch(Exception e){
            System.out.println("类型转换异常，获取用户的医师信息失败");
            e.printStackTrace();
        }
        try{
            if(getLoginInformation(user).get(0)!=null){
            	Employee emp = ((Employee)getLoginInformation(user).get(0));
            	request.setAttribute("emp", emp);
            	return "personal/index";
            }
        } catch(Exception e){
            System.out.println("类型转换异常，获取用户的医师信息失败");
            e.printStackTrace();
        }
		return "personal/index";
    }
    
    /**
     * 个人设置修改信息
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request,HttpServletResponse response,Integer empId,Integer docId,
            String realName,String specialty,String phoneNo, String email,String remark,RedirectAttributes attr){
    	if(empId!=null){
    		Employee emp = new Employee();
        	emp.setEmpId(empId);
        	emp.setRealName(realName);
        	emp.setSpecialty(specialty);
            emp.setPhoneNo(phoneNo);
            emp.setEmail(email);
            emp.setRemark(remark);
            emp.setUpdateTime(DateUtil.getDateTime("yyyy-MM-dd"));
        	int i = employeeService.updateEmp(emp);
        	if(i==1){
                attr.addFlashAttribute("result", "更新成功");
                getLoginUser().setEmp(emp);
                request.setAttribute("emp", emp);
                return "redirect:index";
            }else{
                attr.addFlashAttribute("result", "更新失败");
                return "redirect:index";
            }
    	}
    	if(docId!=null){
    		Doctor doc =new Doctor();
    		doc.setDocId(docId);
    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		doc.setRealName(realName);
    		doc.setSpecialty(specialty);
    		doc.setPhoneNo(phoneNo);
    		doc.setEmail(email);
    		doc.setUpdateTime(sdf.format(new Date()));
    		doc.setRemark(remark);
    		int i = doctorService.updateDoc(doc);
    		request.setAttribute("doc", doctorService.select(docId));
        	if(i==1){
                attr.addFlashAttribute("result", "更新成功");
                getLoginUser().setDoc(doc);
                request.setAttribute("doc", doc);
                return "redirect:index";
            }else{
                attr.addFlashAttribute("result", "更新失败");
                return "redirect:index";
            }
    	}
		return null;
    }
    
    /**
     * 个人设置修改密码
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping("/updatePwd")
    public String updatePwd(HttpServletRequest request,HttpServletResponse response,String oldPwd,String newPwd){
    	if(oldPwd=="" || oldPwd==null){
    		request.setAttribute("result", 1);
    		return "personal/updatePwd";
    	}
    	if(newPwd=="" || newPwd==null){
    		request.setAttribute("result", 2);
    		return "personal/updatePwd";
    	}
    	SysUser user = (SysUser) request.getSession().getAttribute("loginUser");
    	user.setPassword(Md5PwdEncoder.encodePassword(newPwd));
    	SysUser newUser =sysUserService.selectByUserCode(user.getUserCode());
    	if(Md5PwdEncoder.encodePassword(oldPwd).equals(newUser.getPassword())){
    		sysUserService.updateByUserCode(user);
    		request.getSession().setAttribute("loginUser", newUser);
    		request.setAttribute("result", 4);
    		return "personal/updatePwd";
    	}else{
    		request.setAttribute("result", 3);
    		return "personal/updatePwd";
    	}
    }
}
