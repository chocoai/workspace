package com.yhcrt.controller.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.project.ScoreRecord;
import com.yhcrt.service.project.ScoreRecordService;
import com.yhcrt.utils.ConstantsLog;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONObject;

/**
 * ScoreRecordController.java
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/scoreRecord")
public class ScoreRecordController extends BaseController{
	
	@Autowired
	private ScoreRecordService scoreRecordService;
	
	/**
	 * 查询计分记牌方式配置列表
	 * @param scoreRecord
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/index")
	public ModelAndView listScoreRecord(){
		return new ModelAndView("project/scoreRecordList");
	}
	
	/**
	 * 查询计分记牌方式列表分页显示
	 * @param jsonString
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(Integer currentPage,String findContent){
		model.clear();
		try {
			 PageHelper.startPage(currentPage, getPageSize());
			 List<ScoreRecord> list=scoreRecordService.queryList(findContent);
			 PageBean<ScoreRecord> pageInfo=new PageBean<ScoreRecord>(list);
			 model.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * 
	 * @Title: ropDown
	 * @Description: 获取下拉框列表
	 * @return: ModelAndView
	 */
	@RequestMapping("/dropDown")
	public ModelAndView queryType(@RequestParam String number,@RequestParam String type){
		try {
			List<ScoreRecord> list = scoreRecordService.queryList(null);
			model.put("list", list);
			model.put("number", number);
			model.put("type", type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("common/score.csi",model);
	}
	
	/**
	 * @Title: addToEdit
	 * @Description: 新增/更新跳转页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/updateInfo")
	public ModelAndView addToEdit(Integer cid){
		model.clear();
		ScoreRecord scoreRecord = null;
		try {
			if(cid != null){
				scoreRecord = scoreRecordService.searchObjectByCid(cid);
			}else {
				scoreRecord = new ScoreRecord();
			}
			 model.put("scoreRecord", scoreRecord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("project/addScoreRecord",model);
	}
	/**
	 * 
	 * @Title: saveToUpdate
	 * @Description: 新增/更新数据
	 * @return: String
	 */
	@RequestMapping("/saveToUpdate")
	@ResponseBody
	public String saveToUpdate(HttpServletRequest request,@RequestParam String jsonString){
		String result="";
		if (jsonString==null||jsonString.equals("")) {
			result="nodata";
		}else {
			JSONObject json= JSONObject.fromObject(jsonString);
			ScoreRecord scoreRecord= (ScoreRecord) JSONObject.toBean(json,ScoreRecord.class);
			Integer cid = scoreRecord.getCid();
			try {
				if(cid==null){
					result= scoreRecordService.insertScoreRecord(scoreRecord);
					sysStsyemLogService.saveSelective(ConstantsLog.SCORE_RECORD_SAVE, scoreRecord.getRuleName(), ConstantsLog.LOG_0);
				}else{
					result= scoreRecordService.updateScoreRecord(scoreRecord);
					sysStsyemLogService.saveSelective(ConstantsLog.SCORE_RECORD_EDIT, scoreRecord.getRuleName(), ConstantsLog.LOG_0);
				}
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	};
	
	/**
	 * 删除计分记牌方式
	 * @param scoreRecord
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> deleteScoreRecord(Integer cid){
		model.clear();
		if(cid==null){
			model.put("status", "nodata");
			return model;
		}
		try {
			ScoreRecord scoreRecord = scoreRecordService.searchObjectByCid(cid);
			model =  scoreRecordService.deleteScoreRecord(cid);
			
		    sysStsyemLogService.saveSelective(ConstantsLog.SCORE_RECORD_DELETE, scoreRecord.getRuleName(), ConstantsLog.LOG_0);
		} catch (Exception e) {
			e.printStackTrace();
			model.put("status", "failed");
		}
		return model;
	}
	
	/**
	 * 判断方式名称是否重复
	 * @param scoreRecord
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/isRuleName")
	public HashMap<String,Object> isRuleName(String ruleName){
		HashMap<String,Object> map = scoreRecordService.isRuleName(ruleName);
		return map;
	}
}
