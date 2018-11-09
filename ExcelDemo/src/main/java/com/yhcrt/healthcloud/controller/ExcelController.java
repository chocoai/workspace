/**
 * 
 */
package com.yhcrt.healthcloud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.util.ExportUtil;
import com.yhcrt.healthcloud.util.ImportExcelUtil;

/** 
 * @ClassName: ExcelController 
 * @Description: TODO
 * @author: lx
 * @date: 2017年12月15日 下午2:44:35  
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {
	
	/**
	 * 导出数据到excel
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping("/out")
	public void exportToExcel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		//设置excel表头
        String[] header = {"设备名称","IMEI号","所属机构","设备类别","设备型号","SIM卡号","是否使用","录入时间","备注"};
		//设置表名称
        String title = "终端设备数据";
        //数据
        List<Map<Integer, Object>> list = new ArrayList<Map<Integer,Object>>();
        for(int i = 0;i<20;i++){
        	HashMap<Integer,Object> map = new HashMap<Integer,Object>();
    		map.put(0, "你");
    		map.put(1, "好");
    		map.put(2, "啊");
    		map.put(3, "！");
    		map.put(4, "我");
    		map.put(5, "很");
    		map.put(6, "好");
    		map.put(7, "啊");
    		map.put(8, "。");
    		list.add(map);
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
	 * 批量导入数据预处理
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void batchImport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		JSONObject jsonObj = new JSONObject();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile mutiparFile = multipartRequest.getFile("deviceFile");
			if(mutiparFile == null){  
	            throw new Exception("文件不存在,请上传Excel文件");  
	        }
			//获取文件名
			String fileName =mutiparFile.getOriginalFilename();
			//获取文件名后缀
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!suffix.contains("xls") && !suffix.contains("xlsx")) {
				throw new Exception("上传文件只支持(xls、xlsx)文件格式"); 
			}
			if (mutiparFile.getSize() >  5 * 1024 * 1024) {
				throw new Exception("文件过大,请将大小控制在5M以内");
			}
			
			InputStream in = mutiparFile.getInputStream();
			List<List<Object>> objList = ImportExcelUtil.getBankListByExcel(in,mutiparFile.getOriginalFilename());  
			for (int i = 0; i < objList.size(); i++) {
				List<Object> obj = objList.get(i);
				//int index = i+1;
				/*if (StringUtils.isBlank(String.valueOf(obj.get(1)))) {
					throw new Exception("成功导入"+i+"条数据,第"+index+"条数据格式有误，IMEI号为必填项");
				}
				if(null != ""){
					throw new Exception("成功导入"+i+"条数据,第"+index+"条数据格式有误，IMEI号格式有误");
				}
				if(null != ""){
					throw new Exception("成功导入"+i+"条数据,第"+index+"条数据有误，IMEI号重复");
				}
				if(null != ""){
					throw new Exception("成功导入"+i+"条数据,第"+index+"条数据格式有误，SIM号格式有误");
				}*/
				System.out.println("我是第"+i+"个:------------->"+obj.get(0)+obj.get(1)+obj.get(2)+obj.get(3)+obj.get(4)+obj.get(5)+obj.get(6)+obj.get(7)+obj.get(8));
			}
			jsonObj.put("result", true);
			jsonObj.put("msg", "导入成功");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", false);
			jsonObj.put("msg", e.getMessage().toString());
		}finally{
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		}
	}
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
    public void test(){
        System.out.println("调用controller里面的方法！！！");
    }
}
