package com.yhcrt.controller.queryStatistical;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.queryStatistical.ThreeList;
import com.yhcrt.service.signUp.ParticipatInfoService;
import com.yhcrt.utils.ExportExcel;
/**
 * 
 * 青少年三榜查询
 * @author 陈伟
 * 2017年7月18日 下午2:05:48
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/queryTeenagersThreeList")
public class QueryTeenagersThreeList extends BaseController{

	@Resource
	private ParticipatInfoService participatInfoService;
	
	@RequestMapping("/index")
	public ModelAndView queryThreeList(){
		return new ModelAndView("queryStatistical/queryTeenagersThreeList");
	}
	/**
	 * 
	 * @Title: query
	 * @Description: 三榜信息
	 * @return: Map<String,Object>
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> query(Integer type,Integer order){
		model.clear();
		model.put("type", type);
		model.put("order", order);
		List<ThreeList> list = participatInfoService.queryTeenageThree(model);
		model.put("list", list);
		return model;
	}
	
	/**
	 * 导出excel
	 * @param response
	 * @param projectCid
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response,Integer type,Integer order,String tableHead){
		model.clear();
		if(type!=null){
			model.put("type", type);
			model.put("order", order);
			List<ThreeList> list = participatInfoService.queryTeenageThree(model);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setIndex(i+1);
			}
			
			String fileName="";
			try {
				fileName = new String(tableHead.getBytes("GB2312"), "ISO_8859_1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			String[] headers = { "排名", "单位", "总分", "金牌", "银牌","铜牌","奖牌","输送奖励金牌" };
			ExportExcel<ThreeList> ex = new ExportExcel<ThreeList>();
			byte[] b=ex.exportExcel(tableHead,headers,tableHead ,list);
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename="+fileName+".xls");
			response.setContentLength(b.length);
			try {
				response.getOutputStream().write(b);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
}
