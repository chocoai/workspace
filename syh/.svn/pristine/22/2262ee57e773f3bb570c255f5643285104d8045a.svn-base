
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
import com.yhcrt.entity.system.SysRes;
import com.yhcrt.service.stsyem.SysResService;
import com.yhcrt.utils.StringUtils;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 系统用户控制层
 * @author 陈伟
 * 2017年5月23日 下午12:57:37
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/sysRes")
public class SysResController  extends BaseController {

	@Resource(name="sysResService")
	private SysResService sysResService;
		
	/**
	 * 
	 * @Title: listInfo
	 * @Description: 系统权限主页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView listInfo(Integer pCid){
		model.clear();
		if(pCid==null) pCid=1;
		model.put("pCid", pCid);
		return new ModelAndView("stsyem/indexRes",model);
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
			 List<SysRes> list = sysResService.queryToTree(pid);
			 PageBean<SysRes> pageInfo=new PageBean<SysRes>(list);
			 model.put("pageInfo",pageInfo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * @Title: updateInfo
	 * @Description: 获取角色信息
	 * @return: ModelAndView
	 */
	@RequestMapping("/updateInfo")
	public ModelAndView get(Integer cid,String resName){
		model.clear();
		SysRes info = null;
		try {
			if(StringUtils.isBlank(resName)){
				info = sysResService.getByCid(cid);
			}else {
				info = new SysRes();
				SysRes bean = new SysRes();
				bean.setResName(resName);
				info.setPareId(cid);
				info.setPareSysRes(bean);
			}
			 model.put("info", info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("stsyem/addInfoRes", model);
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
			JSONObject roleInfoJson=JSONObject.fromObject(jsonString);
			SysRes roleInfo = (SysRes) JSONObject.toBean(roleInfoJson, SysRes.class);
			Integer cid = roleInfo.getCid();
			try {
				if(cid==null){
					//userInfo.setCreaRen();
					//userInfo.setCreaTime(DateUtil.getTime());
					sysResService.saveSelective(roleInfo);
				}else{
					sysResService.updateByPrimaryKeySelective(roleInfo);
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
				result=  sysResService.deleteAll(cids);
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	/**
	 * 获取权限树结构
	 * @return
	 */
	@RequestMapping("/queryToTree")
	@ResponseBody
	public JSONArray queryToTree(Integer pid){
		List<Tree> trees=new ArrayList<Tree>();
		try {
			List<SysRes> list = sysResService.queryToTree(pid);
			for (SysRes sysRes :list) {
				Tree tree=new Tree();
				tree.setId(sysRes.getCid());
				tree.setParentId(sysRes.getPareId());
				tree.setNodeValue(sysRes.getResName());
				trees.add(tree);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONArray.fromObject( trees );
	}
	/**
	 * 获取权限选中树结构
	 * @return
	 */
	@RequestMapping("/queryToCheckedTree")
	@ResponseBody
	public JSONArray queryToCheckedTree(Integer roleCid){
		List<Tree> trees = new ArrayList<Tree>() {
		};
		try {
			trees = sysResService.queryToCheckedTree(roleCid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONArray.fromObject( trees );
	}
}
