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
import com.yhcrt.entity.queryStatistical.ThreeListDetail;
import com.yhcrt.entity.queryStatistical.ThreeListProject;
import com.yhcrt.entity.queryStatistical.ThreeListUnit;
import com.yhcrt.service.signUp.ParticipatInfoService;
import com.yhcrt.utils.ExportExcel;

@Controller
@RequestMapping("/queryDetailList")
public class QueryDetailList extends BaseController{

	@Resource
	private ParticipatInfoService participatInfoService;
	
	@RequestMapping("/index")
	public ModelAndView queryDetailList(){
		return new ModelAndView("queryStatistical/queryDetailList");
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
		Map<String, Object> map= participatInfoService.queryTeenageThreeDetail(model);
		return  map;
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
			
			Map<String, Object> map= participatInfoService.queryTeenageThreeDetail(model);
			
			Object projectListObj = map.get("project");
			if(projectListObj!=null){
				List<ThreeListProject> projectList =  (List<ThreeListProject>) projectListObj;
				List<ThreeListUnit> unitList =  (List<ThreeListUnit>)map.get("unit") ;
				List<ThreeListDetail> list =  (List<ThreeListDetail>)map.get("rows");
				Integer row = projectList.size()+1;//总行数（包含合计）
				Integer rank = unitList.size()*5;//金银铜总分奖牌
				
				//表头数组
				String[] headers1 = new String[unitList.size()+1];//一级表头
				headers1[0] = "项目";
				String[] headers2 = new String[rank];//二级表头
				
				//数据数组
			    String[][] datas = new  String[row][rank+1];
			    datas[row-1][0] = "合计";
			    //填充数据数据
				for (int i = 0; i < projectList.size(); i++) {
					ThreeListProject project = projectList.get(i);
					datas[i][0] = project.getProjectName();//填充项目
					
					for (int j = 0; j < unitList.size(); j++) {
						ThreeListUnit unit = unitList.get(j);
						headers1[j+1] = unit.getUnitName();
						
						headers2[5*j] = "金";
						headers2[5*j+1] = "银";
						headers2[5*j+2] = "铜";
						headers2[5*j+3] = "总分";
						headers2[5*j+4] = "奖牌";
						
						datas[row-1][5*j+1] = unit.getGold();
						datas[row-1][5*j+2] = unit.getSilver();
						datas[row-1][5*j+3] = unit.getCopper();
						datas[row-1][5*j+4] = unit.getIntrgral();
						datas[row-1][5*j+5] = unit.getTotal();
						
						datas[i][5*j+1] = "0.00";
						datas[i][5*j+2] = "0.00";
						datas[i][5*j+3] = "0.00";
						datas[i][5*j+4]   = "0.00";
						datas[i][5*j+5] = "0.00";
						for (int j2 = 0; j2 < list.size(); j2++) {
							ThreeListDetail detail = list.get(j2);
							if(detail.getClassCid().equals(project.getProjectCid())&&detail.getUnitCid().equals(unit.getUnitCid())){
								datas[i][5*j+1] = detail.getGold();
								datas[i][5*j+2] = detail.getSilver();
								datas[i][5*j+3] = detail.getCopper();
								datas[i][5*j+4] = detail.getIntrgral();
								datas[i][5*j+5] = detail.getTotal();
							}
						}
					}
				}
				
				//报表生成
				String fileName="";
				try {
					fileName = new String(tableHead.getBytes("GB2312"), "ISO_8859_1");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				ExportExcel ex = new ExportExcel();
				byte[] b=ex.exportExcel1(tableHead,headers1,headers2,datas);
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
}
