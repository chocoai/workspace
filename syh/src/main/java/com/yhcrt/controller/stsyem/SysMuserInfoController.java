
package com.yhcrt.controller.stsyem;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.system.SysMuserInfo;
import com.yhcrt.entity.system.SysUser;
import com.yhcrt.service.stsyem.SysMuserInfoService;
import com.yhcrt.service.stsyem.SysUserService;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.PublicUtil;
import com.yhcrt.utils.ReflectionUtil;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONObject;

/**
 * 系统权限控制层
 * @author 陈伟
 * 2017年5月23日 下午12:57:17
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/sysMuserInfo")
public class SysMuserInfoController extends BaseController {

	
	@Resource(name="sysMuserInfoService")
	private SysMuserInfoService sysMuserInfoService;
	@Resource(name="sysUserService")
	private SysUserService sysUserService;
	
	/**
	 * 
	 * @Title: listInfo
	 * @Description: 系统用户主页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView listInfo(){
		return new ModelAndView("stsyem/indexUser");
		
	}
	/**
	 * AJAX分页查询
	 * @param jsonString
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(Integer currentPage,String findContent){
		model.clear();
		try {
			 PageHelper.startPage(currentPage, getPageSize());
			 List<SysMuserInfo> list = sysMuserInfoService.quertByParam(findContent);
			 PageBean<SysMuserInfo> pageInfo=new PageBean<SysMuserInfo>(list);
			 model.put("pageInfo",pageInfo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * @Title: updateInfo
	 * @Description: 获取用户信息
	 * @return: ModelAndView
	 */
	@RequestMapping("/updateInfo")
	public ModelAndView get(Integer cid){
		model.clear();
		SysMuserInfo info = null;
		try {
			if(cid != null){
				info = sysMuserInfoService.getByCid(cid);
			}else {
				info = new SysMuserInfo();
			}
			 model.put("info", info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("stsyem/addInfoUser", model);
	}
	/**
	 * 
	 * @Title: doAddInfo
	 * @Description: 新增/更新数据
	 * @return: String
	 */
	@RequestMapping("/saveToUpdate")
	@ResponseBody
	public String saveToUpdate(@RequestParam String jsonString){
		String result="";
		if (jsonString==null||jsonString.equals("")) {
			result="nodata";
		}else {
			JSONObject userInfoJson=JSONObject.fromObject(jsonString);
			SysMuserInfo userInfo = (SysMuserInfo) JSONObject.toBean(userInfoJson, SysMuserInfo.class);
			Integer cid = userInfo.getCid();
			try {
				if(cid==null){
					//userInfo.setCreaRen();
					//userInfo.setCreaTime(DateUtil.getTime());
					sysMuserInfoService.saveSelective(userInfo);
				}else{
					SysMuserInfo info = sysMuserInfoService.getByCid(cid);
					info.setLastUpdateTime(DateUtil.getTime());
					info.setLastUpdateIp(PublicUtil.getIp());
					ReflectionUtil.bean2Bean(info,userInfo);
					sysMuserInfoService.updateUserAndRole(info);
				}
				result="success";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	};
	/**
	 * @Title: isUserCode
	 * @Description: 判断账号是否存在
	 * @return: ModelAndView
	 */
	@RequestMapping("/isUserCode")
	@ResponseBody
	public String  isUserCode(String userCode){
		String result="";
		if (userCode==null) {
			result="nodata";
		}else {
			try {
				boolean isName = sysUserService.isExist(userCode);
				result= isName == true?"true":"false";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: doUser
	 * @Description: 修改用户账号
	 * @return: String
	 */
	@RequestMapping("/doUser")
	@ResponseBody
	public String doUser(@RequestParam String jsonString){
		String result="";
		if (jsonString==null||jsonString.equals("")) {
			result="nodata";
		}else {
			try {
				JSONObject userInfoJson=JSONObject.fromObject(jsonString);
				SysUser info = (SysUser) JSONObject.toBean(userInfoJson, SysUser.class);
				String userCode = info.getUserCode();
				SysUser user = sysUserService.getByCid(info.getCid());
				if (user.getUserCode().equals(userCode)) {
					result="noedit";
				}else{
					user.setUserCode(userCode);
					sysUserService.updateByPrimaryKeySelective(user);
					result="success";
				}
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 
	 * @Title: updateByCidSates
	 * @Description: 用户的禁用
	 * @return: String
	 */
	@RequestMapping("/updateByCidSates")
	@ResponseBody
	public String updateByCidSates(@RequestParam("id") Integer id,@RequestParam("states") Integer states){
		String result="";
		if (id==null||states==null) {
			result="nodata";
		}else {
			try {
				sysMuserInfoService.updateByCidSates(id, states);
				result="success";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 记录执行删除
	 * @param cids
	 * @return
	 */
	@RequestMapping("/deleteInfo")
	@ResponseBody
	public String deleteInfo(@RequestParam Integer[] cids){
		String result="";
		if (cids==null||cids.length==0) {
			result="nodata";
		}else {
			try {
				result=  sysMuserInfoService.deleteAll(cids);
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
}
