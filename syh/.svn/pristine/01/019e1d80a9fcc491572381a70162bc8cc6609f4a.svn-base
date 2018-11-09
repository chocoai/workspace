package com.yhcrt.controller.signUp;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.signUp.AthleteBaseInfo;
import com.yhcrt.entity.signUp.UnitInfo;
import com.yhcrt.service.signUp.AthleteBaseInfoService;
import com.yhcrt.service.signUp.UnitInfoService;
import com.yhcrt.service.stsyem.SysDictService;
import com.yhcrt.utils.ConstantsLog;
import com.yhcrt.utils.ExportExcel;
import com.yhcrt.utils.ReflectionUtil;
import com.yhcrt.utils.StringUtils;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONObject;

/**
 * 运动员基本信息的控制层
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/athleteBaseInfo")
public class AthleteBaseInfoController extends BaseController {
	@Resource(name="athleteBaseInfoService")
	private AthleteBaseInfoService athleteBaseInfoService;
	@Resource(name="unitInfoService")
	private UnitInfoService unitInfoService;
	@Resource(name="sysDictService")
	private SysDictService sysDictService;
	/**
	 * @Title: listUnitInfo
	 * @Description: 参赛运动员主页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView listInfo(){
		return new ModelAndView("athleteBaseInfo/index");
	}
	
	/**
	 * AJAX分页查询
	 * @param jsonString
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(Integer currentPage,@RequestParam String jsonString){
		model.clear();
		JSONObject infoJson= JSONObject.fromObject(jsonString);
		AthleteBaseInfo athleteBaseInfo= (AthleteBaseInfo) JSONObject.toBean(infoJson,AthleteBaseInfo.class);
		try {
			PageHelper.startPage(currentPage, getPageSize());
			List<AthleteBaseInfo> list = athleteBaseInfoService.quertAll(athleteBaseInfo);
			PageBean<AthleteBaseInfo> pageInfo = new PageBean<AthleteBaseInfo>(list);
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
	public ModelAndView updateInfo(HttpServletRequest request,Integer cid){
		model.clear();
		AthleteBaseInfo info = null;
		try {
			if(cid != null){
				info = athleteBaseInfoService.getById(cid);
			}else {
				String unitCid = request.getParameter("unitCid");
				info = new AthleteBaseInfo();
				if(!StringUtils.isBlank(unitCid)){
					info.setUnitCid(Integer.parseInt(unitCid));
					UnitInfo unitInfo = new UnitInfo();
					unitInfo.setUnitName(request.getParameter("unitName"));
					info.setUnitInfo(unitInfo);
				}
			}
			 model.put("info", info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView("athleteBaseInfo/addInfo", model);
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
			JSONObject unitInfoJson=JSONObject.fromObject(jsonString);
			AthleteBaseInfo abInfo = (AthleteBaseInfo) JSONObject.toBean(unitInfoJson, AthleteBaseInfo.class);
			Integer cid = abInfo.getCid();
			try {
				if(cid==null){
					athleteBaseInfoService.insert(abInfo);
					sysStsyemLogService.saveSelective(ConstantsLog.ATHLETE_INFO_SAVE, abInfo.getAthleteName(), ConstantsLog.LOG_0);
				}else{
					AthleteBaseInfo info = athleteBaseInfoService.getById(cid);
					ReflectionUtil.bean2Bean(info,abInfo,"creaRen,creaTime");
					athleteBaseInfoService.updateByPrimaryKey(info);
					sysStsyemLogService.saveSelective(ConstantsLog.ATHLETE_INFO_EDIT, abInfo.getAthleteName(), ConstantsLog.LOG_0);
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
	 * @Title: isName
	 * @Description: 判断参赛运动员是否重名
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
				boolean isName = athleteBaseInfoService.isName(name);
				result= isName == true?"true":"false";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * @Title: isIDcard
	 * @Description: 判断参赛运动员是否已录入
	 * @return: ModelAndView
	 */
	@RequestMapping("/isIDcard")
	@ResponseBody
	public String  isIDcard(String IDcard){
		String result="";
		if (IDcard==null) {
			result="nodata";
		}else {
			try {
				boolean isIDcard = athleteBaseInfoService.isIDcard(IDcard);
				result= isIDcard == true?"true":"false";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * @Title: isIDcard
	 * @Description: 判断参赛运动员是跨单位或重复录入
	 * @return: ModelAndView
	 */
	@RequestMapping("/isManyUnit")
	@ResponseBody
	public String  isManyUnit(@RequestParam String IDcard,@RequestParam Integer unitCid){
		String result="";
		if (IDcard==null) {
			result="nodata";
		}else {
			try {
				boolean isManyUnit = athleteBaseInfoService.isManyUnit(IDcard,unitCid);//判断该单位是否已经录入
				if(isManyUnit){
					result = "input";
				}else{
					boolean isIDcard = athleteBaseInfoService.isIDcard(IDcard);//判断是否录入
					result= isIDcard == true?"true":"false";
				}
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
				result=athleteBaseInfoService.deleteAll(cids);
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 	
	 * @Title: selectAthlete
	 * @Description: TODO
	 * @return: ModelAndView
	 */
	@ResponseBody
	@RequestMapping("/selectAthlete")
	public Map<String, Object> selectAthlete(Integer athleteType,Integer[] unitCids ,Integer[] athleteCids,Integer isSex){
		model.clear(); 
		List<AthleteBaseInfo> list = athleteBaseInfoService.selectAthlete(athleteType,unitCids,athleteCids,isSex);
		model.put("list", list);
		return model;
	}
	/**
	 * 
	 * @Title: moveAthlete
	 * @Description: 移动运动员
	 * @return: ModelAndView
	 */
	@ResponseBody
	@RequestMapping("/moveAthlete")
	public String moveAthlete(Integer unitCid,Integer[] ahtleteCids ){
		if(unitCid==null||ahtleteCids ==null||ahtleteCids .length==0){
			return "nodata";
		}else{
			try {
				athleteBaseInfoService.updateAthlete(unitCid,ahtleteCids);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "failed";
			}
		}
	}
	/**
	 * 
	 * @Title: importExcel
	 * @Description: 导入运动员
	 * @return: ModelAndView
	 */
	@RequestMapping("/importExcel")
	public void importExcel(HttpServletResponse response,MultipartFile file,Integer unitCid){
		byte[] b=athleteBaseInfoService.saveExcel1(unitCid,file);
		String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		String name = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf('.'));
		
		
		try {
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename="+new String((name+"_导入详情"+type).getBytes("gb2312"), "ISO8859-1"));
			response.setContentLength(b.length);
			response.getOutputStream().write(b);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 导出excel模板
	 * @param response
	 * @param projectCid
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response){
			String[] headers = { "姓名", "别名", "性别", "民族", "出生日期","证件号" ,"是否参加反兴奋剂培训","运动员类型"};
			String fileName  = "运动员导入模板";
			String tableHead = "运动员清单";
			
			try {
				ByteArrayOutputStream outputStream=null;
				
				String[] sexStr = new String[] { "男", "女"};
				List<String> nationList =  sysDictService.selectByItemKey("nation");
				List<String> athletesTypeList =  sysDictService.selectByItemKey("athletesType");
				
				String[] nationStr = StringUtils.list2array(nationList);
				String[] isYesNo = new String[] { "是", "否"};
				String[] athletesType = StringUtils.list2array(athletesTypeList);
			
				// 声明一个工作薄
				HSSFWorkbook workbook = new HSSFWorkbook();
				// 生成一个表格
				HSSFSheet sheet = workbook.createSheet(tableHead);
				
				// 生成一个样式
				HSSFCellStyle style = ExportExcel.setHeadStyle(workbook);
				// 生成并设置另一个样式
				HSSFCellStyle style2 = ExportExcel.setBodyStyle(workbook);
	
		        HSSFRow row = sheet.createRow(0);  //第一行：抬头
		        //四个参数分别是：起始行，起始列，结束行，结束列
				sheet.addMergedRegion(new   Region(0,(short)(0),0,(short)(headers.length-1))); //设置抬头跨列数
				HSSFCell cell2= row.createCell(0);
				cell2.setCellStyle(style);
				cell2.setCellValue(tableHead);  //设置抬头
			  
				row = sheet.createRow(1);    //设置标题
				for (short i = 0; i < headers.length; i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style);
					HSSFRichTextString text = new HSSFRichTextString(headers[i]);
					cell.setCellValue(text);
				}
				// 第3列的第3行到第50行单元格性别下拉
		        // hidden_sex 为隐藏的sheet的别名，1为这个sheet的索引 ，考虑到有多个列绑定下拉列表
				workbook = ExportExcel.dropDownList2003(workbook, sheet, sexStr, 2, 50, 2, 2, "hidden_sex", 1);
				workbook = ExportExcel.dropDownList2003(workbook, sheet, nationStr, 2, 50, 3, 3, "hidden_nation", 2);
				workbook = ExportExcel.dropDownList2003(workbook, sheet, isYesNo, 2, 50, 6, 6, "hidden_yesNo", 3);
				workbook = ExportExcel.dropDownList2003(workbook, sheet, athletesType, 2, 50, 7, 7, "hidden_athletesType", 4);
				
				
				fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
				outputStream=new ByteArrayOutputStream();
				workbook.write(outputStream);
				byte[] b=outputStream.toByteArray();
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition", "attachment;filename="+fileName+".xls");
				response.setContentLength(b.length);
				response.getOutputStream().write(b);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}
	
}
