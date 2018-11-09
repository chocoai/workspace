package com.yhcrt.controller.signUp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.signUp.UnitInfo;
import com.yhcrt.service.signUp.UnitInfoService;
import com.yhcrt.service.stsyem.SysStsyemLogService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.ConstantsLog;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.ReflectionUtil;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONObject;

/**
 * 参赛单位的控制层
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/unitInfo")
public class UnitInfoController extends BaseController{
	@Resource(name="unitInfoService")
	private UnitInfoService unitInfoService;
	@Resource(name="sysStsyemLogService")
	private SysStsyemLogService sysStsyemLogService;
	
	
	/**
	 * 
	 * @Title: listUnitInfo
	 * @Description: 参赛单位主页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView listInfo(){
		return new ModelAndView("signUp/index");
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
			 List<UnitInfo> list = unitInfoService.quertByUnitContent(findContent);
			 PageBean<UnitInfo> pageInfo=new PageBean<UnitInfo>(list);
			 model.put("pageInfo",pageInfo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * @Title: updateInfo
	 * @Description: 获取参赛单位信息
	 * @return: ModelAndView
	 */
	@RequestMapping("/updateInfo")
	public ModelAndView get(Integer cid){
		model.clear();
		UnitInfo info = null;
		try {
			if(cid != null){
				info = unitInfoService.getById(cid);
			}else {
				info = new UnitInfo();
			}
			 model.put("info", info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView("signUp/addInfo", model);
	}
	
	/**
	 * @Title: isName
	 * @Description: 判断参赛单位是否已录入
	 * @return: ModelAndView
	 */
	@RequestMapping("/isName")
	@ResponseBody
	public String  isName(String name){
		String result="";
		if (name==null) {
			result="nodata";
		}else {
			try {
				boolean isName = unitInfoService.isName(name);
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
	 * @Title: doAddInfo
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
			JSONObject unitInfoJson=JSONObject.fromObject(jsonString);
			UnitInfo unitInfo = (UnitInfo) JSONObject.toBean(unitInfoJson, UnitInfo .class);
			Integer cid = unitInfo.getCid();
			try {
				if(cid==null){
					unitInfo.setCreaRen(getUserCode());
					unitInfo.setCreaTime(DateUtil.getTime());
					unitInfo.setErsionNum(TokenManager.getErsionNum());
					unitInfoService.insert(unitInfo);
					sysStsyemLogService.saveSelective(ConstantsLog.UNIT_SAVE, unitInfo.getUnitName(), ConstantsLog.LOG_0);
				}else{
					UnitInfo info = unitInfoService.getById(cid);
					ReflectionUtil.bean2Bean(info,unitInfo,"creaRen,creaTime");
					unitInfoService.updateByPrimaryKey(info);
					sysStsyemLogService.saveSelective(ConstantsLog.UNIT_EDIT, unitInfo.getUnitName(), ConstantsLog.LOG_0);
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
				boolean isDelete = unitInfoService.deleteAll(cids);
				result= isDelete == true?"success":"nodelete";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: queryType
	 * @Description: 获取单位下拉列表
	 * @return: ModelAndView
	 */
	@RequestMapping("/queryType")
	@ResponseBody
	public ModelAndView queryType(@RequestParam String isTeam){
		model.clear();
		try {
			List<UnitInfo> list = unitInfoService.quertByUnitContent(null);
			model.put("list", list);
			model.put("type", isTeam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("common/unit.csi",model);
	}
}
