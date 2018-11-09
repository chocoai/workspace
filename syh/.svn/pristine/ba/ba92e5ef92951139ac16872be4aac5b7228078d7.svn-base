package com.yhcrt.controller.queryStatistical;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.project.ProjectManager;
import com.yhcrt.entity.queryStatistical.ScoreInfo;
import com.yhcrt.entity.queryStatistical.ScoresBase;
import com.yhcrt.service.CommonService;
import com.yhcrt.service.project.ProjectManagerService;
import com.yhcrt.service.signUp.ParticipatInfoService;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.ExportExcel;

@Controller
@RequestMapping("/queryScoreInfo")
public class QueryScoreInfo extends BaseController{
	@Resource
	private CommonService commonService;
	@Resource
	private ProjectManagerService projectManagerService;
	@Resource
	private ParticipatInfoService participatInfoService;
	
	
	
	@RequestMapping("/index")
	public ModelAndView queryScoreInfo(){
		return new ModelAndView("queryStatistical/queryScoreInfo");
	}
	
	@RequestMapping("/queryProjectScore")
	@ResponseBody
	public Map<String, Object> queryProjectScore(Integer projectCid){
		model.clear();
		if(projectCid!=null){
			try {
				model.put("pid", projectCid);
				model.put("ranking", 9);//默认取名次前八的运动员
				List<ScoresBase> list = new ArrayList<ScoresBase>();
				List<ScoreInfo> list1 = participatInfoService.queryScoreInfo(model);
				
				ProjectManager projectManager = projectManagerService.queryProjectByCid(projectCid);
				String pName = "";
				pName = commonService.getFullName(projectManager.getpCid(), Constants.Middle_2,pName);
				String projectName=projectManager.getProjectName()+"("+pName+")";
				
				for (ScoreInfo scoreInfo : list1) {
					if(projectManager.getIsTeam().equals(Constants.Middle)){//个人赛：输出运动员健将等级
						if(scoreInfo.getJudgeLevel()!=null){
							switch (scoreInfo.getJudgeLevel()) {
							case 1:
								scoreInfo.setBackUp("健将");
								break;
							case 2:
								scoreInfo.setBackUp("一级运动员");
								break;
							case 3:
								scoreInfo.setBackUp("二级运动员");
								break;
							case 4:
								scoreInfo.setBackUp("三级运动员");
								break;
							default:
								scoreInfo.setBackUp("");
								break;
							}
						}else{
							scoreInfo.setBackUp("");
						}
						
					}else{//团体赛：默认 运动员个数大于2时 名字显示为单位
						if(scoreInfo.getAthleteNum()>2){
							scoreInfo.setAthleteName(scoreInfo.getUnitName());
						}
					}
					list.add(scoreInfo.getScoresBase());
				}
				model.put("resultType", projectManager.getResultType());
				model.put("projectName", projectName);
				model.put("list", list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return model;
	}
	
	/**
	 * 导出excel
	 * @param response
	 * @param projectCid
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response,Integer projectCid,String tableHead){
		model.clear();
		List<ScoresBase> list = new ArrayList<ScoresBase>();
		if(projectCid!=null){
			model.put("pid", projectCid);
			model.put("ranking", 9);//默认取名次前八的运动员
			List<ScoreInfo> list1 = participatInfoService.queryScoreInfo(model);
			ProjectManager projectManager = projectManagerService.queryProjectByCid(projectCid);
			
			for (ScoreInfo scoreInfo : list1) {
				if(projectManager.getIsTeam().equals(Constants.Middle)){//个人赛：输出运动员健将等级
					if(scoreInfo.getJudgeLevel()!=null){
						switch (scoreInfo.getJudgeLevel()) {
						case 1:
							scoreInfo.setBackUp("健将");
							break;
						case 2:
							scoreInfo.setBackUp("一级运动员");
							break;
						case 3:
							scoreInfo.setBackUp("二级运动员");
							break;
						case 4:
							scoreInfo.setBackUp("三级运动员");
							break;
						default:
							scoreInfo.setBackUp("");
							break;
						}
					}else{
						scoreInfo.setBackUp("");
					}
					
				}else{//团体赛：默认 运动员个数大于2时 名字显示为单位
					if(scoreInfo.getAthleteNum()>2){
						scoreInfo.setAthleteName(scoreInfo.getUnitName());
					}
				}
				list.add(scoreInfo.getScoresBase());
			}
			
			String fileName="";
			try {
				fileName = new String(tableHead.getBytes("gb2312"), "ISO8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			String[] headers = { "名次", "单位", "姓名", "成绩", "得分","备注" };
			ExportExcel<ScoresBase> ex = new ExportExcel<ScoresBase>();
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
