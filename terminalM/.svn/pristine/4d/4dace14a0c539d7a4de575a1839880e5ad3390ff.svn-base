/**
 * 
 */
package com.whty.assis.count.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLEncoder;

import com.whty.assis.count.service.impl.TDataGatherCountLogServiceImpl;
import com.whty.assis.sysres.model.TaManageUserInfo;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whty.common.util.BigDecimalUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.whty.assis.count.model.TDataGatherCountLog;
import com.whty.assis.count.service.TDataGatherCountLogService;
import com.whty.common.util.DateUtils;
import com.whty.common.util.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.whty.assis.basicdata.service.DeviceInfoService;

/** 
 * @ClassName: CountController 
 * @author: zjd
 * @date: 2018年6月19日 上午10:45:42  
 */
@Controller
@RequestMapping(value="/manage/count")
public class CountController {
	
	@Autowired
	private TDataGatherCountLogService tDataGatherLogService;
	@Autowired
    private DeviceInfoService DeviceInfoService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 一体机统计首页
     * @param request
     * @param response
     * @param map
     * @return
     */
	@RequestMapping(value = "/list")
	public String  list(HttpServletRequest request, HttpServletResponse response,ModelMap map){
		//累计使用人数
		List<TDataGatherCountLog> tDataGatherLogs = tDataGatherLogService.selectByUseCount(request,7);

		//设备开机数
		List<TDataGatherCountLog> devices = tDataGatherLogService.selectByDevice(request,7);

		//应用统计
		List<TDataGatherCountLog> softwares = tDataGatherLogService.selectBySoftware(request,7);
        //设备总数
        int deviceCount = DeviceInfoService.selectDeviceInfo();
        map.put("deviceCount", deviceCount);
        map.put("tDataGatherLogs", tDataGatherLogs);
		map.put("devices", devices);
        map.put("softwares", softwares);
		return "count.list";
	}

    /**
     * 教师使用排名
     * @param response
     * @param timeType
     */
	@RequestMapping(value = "/getTeacherRank")
    @ResponseBody
    public void getTeacherRank(HttpServletRequest request,HttpServletResponse response,Integer timeType) {
        JSONObject object = new JSONObject();
        List<TDataGatherCountLog> teacher = tDataGatherLogService.selectByTeacherRank(request,timeType);
        object.put("top",teacher);
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 时长统计
     * @param response
     * @param timeType
     */
	@RequestMapping(value = "/getUseTaking")
    @ResponseBody
    public void getUseTaking(HttpServletRequest request,HttpServletResponse response,Integer timeType){
        JSONArray resultList = tDataGatherLogService.selectByUseTaking(request,timeType);
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(resultList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 学科排名
     * @param response
     * @param timeType
     */
    @RequestMapping(value = "/getSubjectRank")
    @ResponseBody
    public void getSubjectRank(HttpServletRequest request,HttpServletResponse response,Integer timeType){
        List<TDataGatherCountLog> subjects = tDataGatherLogService.selectBySubject(request,timeType);
        JSONObject object = new JSONObject();
        object.put("subject",subjects);
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *应用统计
     * @param timeType
     * @return
     */
    @RequestMapping(value = "/getSoftware")
    @ResponseBody
    public void getSoftware(HttpServletRequest request,HttpServletResponse response,Integer timeType){
        List<TDataGatherCountLog> softwares= tDataGatherLogService.selectBySoftware(request,timeType);
        JSONObject object = new JSONObject();
        object.put("software",softwares);
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 教师统计页面
     * @param response
     */
    @RequestMapping(value = "/teacherUseCount")
    public String teacherUseCount(HttpServletRequest request,HttpServletResponse response,ModelMap map, @RequestParam(name="pageValue",defaultValue="1")Integer page, String startTime,String endTime){
        if(page<1){
            page = 1;
        }
        PageInfo<TDataGatherCountLog> p =tDataGatherLogService.selectByTeacher(request,startTime,endTime,page);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("teachers",p.getList());
        map.put("pageNum", p.getPageNum());
        map.put("pages", p.getPages());
        return "teacherCount.list";
    }

    /**
     * 教师使用明细导出
     * @param startTime
     * @param endTime
     * @param response
     * @throws IOException
     */
    @RequestMapping("/teacherExcel")
    public void teacherExcel(HttpServletRequest request,String startTime,String endTime,HttpServletResponse response) throws IOException {
        //设置excel表头
        String[] header = {"编号","教师","学科","学校","使用时长","使用次数"};
        //设置表名称
        String title = "教师使用明细数据";
        //数据
        List<Map<Integer, Object>> list = new ArrayList<>();

        Map<String, Object> param = new HashMap<String, Object>();
        if(StringUtil.isNotEmpty(startTime) && (StringUtil.isNotEmpty(endTime))){
            param.put("startTime",startTime+" 00:00:00");
            param.put("endTime",endTime+" 23:59:59");
        }
        List<TDataGatherCountLog> teachers= tDataGatherLogService.selectByTeacherExcel(param);
        if(teachers.size()>0){
            for(int i = 0;i<teachers.size();i++){
                HashMap<Integer,Object> map = new HashMap<Integer,Object>();
                map.put(0, i+1);
                map.put(1, teachers.get(i).getUserName());
                map.put(2, teachers.get(i).getSubjectId());
                map.put(3, teachers.get(i));
                map.put(4, TDataGatherCountLogServiceImpl.changgeUseTaking(teachers.get(i).getUseTaking()));
                map.put(5, teachers.get(i).getUserCount());
                list.add(map);
            }
        }
        Workbook wb = ExportUtil.create(title, header, list);
        String fileName = "测试.xlsx";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();

    }


    /**
     * 设备统计页面
     * @param response
     */
    @RequestMapping(value = "/deviceUseCount")
    public String deviceUseCount(HttpServletRequest request,HttpServletResponse response,ModelMap map, @RequestParam(name="pageValue",defaultValue="1")Integer page, String startTime,String endTime){
        if(page<1){
            page = 1;
        }
        PageInfo<TDataGatherCountLog> p =tDataGatherLogService.selectByDeviceUse(request,startTime,endTime,page);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("devices",p.getList());
        map.put("pageNum", p.getPageNum());
        map.put("pages", p.getPages());
        return "deviceCount.list";
    }








}
