
package com.yhcrt.controller.stsyem;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.system.SysSystemLog;
import com.yhcrt.service.stsyem.SysStsyemLogService;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.StringUtils;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 系统操作日志控制层
 * @author 陈伟
 * 2017年5月23日 下午12:56:56
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController  extends BaseController {
	
	@Resource(name="sysStsyemLogService")
	private SysStsyemLogService sysStsyemLogService;
	
	/**
	 * 
	 * @Title: listInfo
	 * @Description: 系统用户主页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView listInfo(){
		return new ModelAndView("stsyem/indexLog");
		
	}
	/**
	 * AJAX分页查询
	 * @param jsonString
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public JSONObject queryPage(@RequestParam("page")Integer page,@RequestParam("rows")Integer pageSize,String jsonString){
		SysSystemLog bean= null;
		if(!StringUtils.isBlank(jsonString)){
			JSONObject infoJson= JSONObject.fromObject(jsonString);
			bean= (SysSystemLog) JSONObject.toBean(infoJson,SysSystemLog.class);
		}
		JSONObject json = new JSONObject();
		page = page ==null ? 1 :page;
		try {
			 PageHelper.startPage(page, pageSize);
			 List<SysSystemLog> list = sysStsyemLogService.quertByParam(bean);
			 PageBean<SysSystemLog> pageInfo=new PageBean<SysSystemLog>(list);
			 json.put("total", pageInfo.getPages());
			 json.put("page", pageInfo.getPageNum());
			 json.put("records", pageInfo.getTotal());
			 list = list==null?new ArrayList<SysSystemLog>():list;
			 json.put("rows", JSONArray.fromObject(list));
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 批量删除
	 * @param cids
	 * @return
	 */
	@RequestMapping("/deleteInfo")
	@ResponseBody
	public String deleteInfo(Integer deleteType,Integer[] cids,String jsonString){
		try {
		
			if(deleteType.equals(Constants.Middle)){//选中删除
				if (cids==null||cids.length==0) return "nodata";
				sysStsyemLogService.removeByCids(cids); return "success";
			}else{//条件删除
				if(StringUtils.isBlank(jsonString)) return "nodata";
				JSONObject infoJson= JSONObject.fromObject(jsonString);
				SysSystemLog bean = (SysSystemLog) JSONObject.toBean(infoJson,SysSystemLog.class);
				if(bean.getOpeartionType()==null) return "nodata";
				sysStsyemLogService.removeByParam(bean);
				return "success";
			}
		
		} catch (Exception e) {
			return "failed";
		}
	}
	
}
