
package com.yhcrt.controller.stsyem;

import java.util.ArrayList;
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
import com.yhcrt.entity.Tree;
import com.yhcrt.entity.system.SysDept;
import com.yhcrt.entity.system.SysMuserInfo;
import com.yhcrt.service.stsyem.SysDeptService;
import com.yhcrt.service.stsyem.SysDeptUserService;
import com.yhcrt.utils.StringUtils;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 系统部门控制层
 * @author 陈伟
 * 2017年5月23日 下午12:56:56
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/sysDept")
public class SysDeptController  extends BaseController {
	
	@Resource(name="sysDeptService")
	private SysDeptService sysDeptService;
	@Resource(name="sysDeptUserService")
	private SysDeptUserService sysDeptUserService;
	
	/**
	 * 
	 * @Title: listInfo
	 * @Description: 系统用户主页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView listInfo(Integer pCid){
		model.clear();
		model.put("pCid", pCid);
		return new ModelAndView("stsyem/indexDept",model);
		
	}
	/**
	 * AJAX分页查询
	 * @param jsonString
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(@RequestParam("currentPage")Integer currentPage,@RequestParam("pid")Integer pid){
		model.clear();
		try {
			 PageHelper.startPage(currentPage, getPageSize());
			 List<SysMuserInfo> list =  sysDeptUserService.getByDeptCid(pid);
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
	 * @Description: 获取部门信息
	 * @return: ModelAndView
	 */
	@RequestMapping("/updateInfo")
	public ModelAndView get(Integer cid,String deptName){
		model.clear();
		List<SysMuserInfo> deptUser = new ArrayList<SysMuserInfo>();
		SysDept info = null;
		try {
			if(StringUtils.isBlank(deptName)){
				info = sysDeptService.getByCid(cid);
				deptUser = sysDeptUserService.getByDeptCid(cid);
			}else {
				info = new SysDept();
				SysDept bean = new SysDept();
				bean.setDeptName(deptName);
				info.setSysDept(bean);
				info.setPareCdoe(cid);
			}
			List<SysMuserInfo> listUser = sysDeptUserService.getByNotDeptCid(cid);
			model.put("deptUser", deptUser);
			model.put("listUser", listUser);
			model.put("info", info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("stsyem/addInfoDept", model);
	}
	/**
	 * 
	 * @Title: doAddInfo
	 * @Description: 新增/更新数据
	 * @return: String
	 */
	@RequestMapping("/saveToUpdate")
	@ResponseBody
	public String saveToUpdate(@RequestParam("jsonString") String jsonString){
		String result="";
		if (jsonString==null||jsonString.equals("")) {
			result="nodata";
		}else {
			JSONObject infoJson=JSONObject.fromObject(jsonString);
			SysDept info = (SysDept) infoJson.toBean(infoJson, SysDept.class);
			Integer cid = info.getCid();
			try {
				if(cid==null){
					//userInfo.setCreaRen();
					//userInfo.setCreaTime(DateUtil.getTime());
					sysDeptService.saveSelective(info);
				}else{
					sysDeptService.updateByPrimaryKeySelective(info);
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
	 * 获取部门树结构
	 * @return
	 */
	@RequestMapping("/queryToTree")
	@ResponseBody
	public JSONArray queryToTree(){
		List<Tree> trees=new ArrayList<Tree>();
		try {
			List<SysDept> list = sysDeptService.queryToTree();
			for (SysDept sysDept :list) {
				Tree tree=new Tree();
				tree.setId(sysDept.getCid());
				tree.setParentId(sysDept.getPareCdoe());
				tree.setNodeValue(sysDept.getDeptName());
				trees.add(tree);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONArray.fromObject( trees );
	}
	
	/**
	 * 记录执行删除
	 * @param cids
	 * @return
	 */
	@RequestMapping("/deleteInfo")
	@ResponseBody
	public String deleteInfo(@RequestParam Integer cid){
		String result="";
		if (cid==null) {
			result="nodata";
		}else {
			try {
				result=  sysDeptService.deleteAll(cid);
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
}
