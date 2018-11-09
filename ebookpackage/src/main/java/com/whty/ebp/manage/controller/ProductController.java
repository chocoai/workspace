package com.whty.ebp.manage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.whty.common.util.GUIDGenerator;
import com.whty.common.util.Plupload;
import com.whty.common.util.PluploadUtil;
import com.whty.common.util.SysConfig;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.model.Product;
import com.whty.ebp.manage.service.ProductService;
import com.whty.ebp.manage.utils.QrCode;
import com.whty.page.PageContext;
import com.whty.page.request.PageRequest;
import com.whty.page.util.HandlerResult;


@Controller
@RequestMapping(value="/sys/product")
public class ProductController extends BaseController{
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/showQrCode",method=RequestMethod.GET)
	public void showQrCode(HttpServletResponse response,String qrCodePath){
		File file = new File(qrCodePath);
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[4096];
			ServletOutputStream fos = response.getOutputStream();
			int readLength = 2048;
			while((readLength = fis.read(buf))!=-1){
				fos.write(buf, 0, readLength);
			}
			fos.flush();
			if(fos!=null){
				fos.close();
			}
			if(fis!=null){
				fis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value="/deleteProduct",method=RequestMethod.POST)
	public void deleteProduct(HttpServletResponse response,String id){
		JSONObject json = new JSONObject();
		try {
			productService.deleteById(id);
			json.put("status", "success");
			json.put("info", "删除成功");
		} catch (Exception e) {
			json.put("status", "error");
			json.put("info", "删除失败");
			e.printStackTrace();
		}
		printJson(response, json);
	}
	
	@RequestMapping(value="/editProduct",method=RequestMethod.POST)
	public void editProductById(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", request.getParameter("id"));
			param.put("productName", request.getParameter("productName"));
			param.put("productCode", request.getParameter("productCode"));
			param.put("description", request.getParameter("description"));
			param.put("icoPath", request.getParameter("icoPath"));
			param.put("icoOldName", request.getParameter("icoOldName"));
			param.put("icoNewName", request.getParameter("icoNewName"));
			param.put("updateTime", new Date());
			productService.updateProduct(param);
			json.put("info", "修改成功");
		} catch (Exception e) {
			json.put("info", "修改失败");
			e.printStackTrace();
		}
		printJson(response, json);
	}
	
	@RequestMapping(value="/loadProductById",method=RequestMethod.POST)
	public void loadProductById(HttpServletResponse response,String id) throws Exception{
		Product pro = productService.loadProductById(id);
		printJson(response, pro);
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.POST)
	public void addProduct(HttpServletResponse response, Product product){
		String guid = GUIDGenerator.getGUID().toString();
		JSONObject json = new JSONObject();
		product.setCreateTime(new Date());
		product.setId(guid);
		product.setUpdateTime(new Date());
		String appDownlocdUrl = SysConfig.getStrValue("app_download_url")+guid;
		String saveQrCodePath = SysConfig.getStrValue("product_qrcode_path")+guid;
		String format = "png";
		int width = 200;
		int height = 200;
		File file = new File(saveQrCodePath+".png");
		if(!file.exists()){
			file.mkdirs();
		}
		QrCode.makeQrCode(format, appDownlocdUrl, width, height, file);
		product.setQrCodePath(file.getAbsolutePath());
		
		
		product.setEbookpackageCode(SysConfig.getStrValue("ebookpackage_code"));
		
		
		try {
			productService.addProduct(product);
			json.put("info", "添加成功");
		}catch (Exception e) {
			json.put("info", "添加失败");
			e.printStackTrace();
		}
		printJson(response, json);
	}

	@RequestMapping(value="/listProduct",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView listProduct(PageRequest pageRequest,Model model,String currentPage1,String productName,String productType){
		ModelAndView mv = new ModelAndView("product/listProduct");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productName", productName);
		if(productType != null && productType !=""){
			param.put("productType", Integer.parseInt(productType));
		}else{
			param.put("productType", null);
		}
		
		param.put("ebookpackageCode",SysConfig.getStrValue("ebookpackage_code"));
		
		
		PageContext page = PageContext.getContext();
		page.setCurrentPage(pageRequest.getCurrPage());
		if(currentPage1 != null&&currentPage1!=""){
			page.setCurrentPage(Integer.parseInt(currentPage1));
		}
		page.setPageSize(pageRequest.getPageSize());
		page.setTotalPage(pageRequest.getTotalPage());
		page.setPagination(true);
		
		HandlerResult pageList = new HandlerResult();
		try {
			pageList = productService.listProductByPage(param,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("productName", productName);
		model.addAttribute("productType", productType);
		model.addAttribute("pageList", pageList);
		mv.addObject(model);
		return mv;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/productList_all")
	public String productList_all(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("productList_all----start");
		String productType = request.getParameter("productType");
		Map paramap = new HashMap();
		
//		paramap.put(key, value);
		
		paramap.put("ebookpackageCode",SysConfig.getStrValue("ebookpackage_code"));
		
		paramap.put("productType", productType);
		List<Map> list = productService.queryProductByType(paramap);
		return JSONArray.fromObject(list).toString();
	}
	
	/**
	 * 上传
	 */
	@RequestMapping(value="/uploadInfo", method = RequestMethod.POST)
	public void uploadInfo1(Plupload plupload,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		plupload.setRequest(request);
		Calendar cal = Calendar.getInstance();
		String strD = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
		//相对路径文件夹
		String relation_path = SysConfig.getStrValue("productIco_upload_dir")+strD+File.separator;
		//绝对路径
		String uploadDir=SysConfig.getStrValue("file_path_pre")+relation_path;
		File uploadDirFile = new File(uploadDir);
		if(!uploadDirFile.exists()){
			uploadDirFile.mkdir();
		}
		
		response.setCharacterEncoding("UTF-8");
		
		try {
            //上传文件
            PluploadUtil.upload(plupload, new File(uploadDir));
            //判断文件是否上传成功（被分成块的文件是否全部上传完成）
            if (PluploadUtil.isUploadFinish(plupload)) {
                System.out.println(plupload.getName() + "-----------上传完毕");
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Product product = new Product();
        product.setIcoOldName(plupload.getName());
        product.setIcoNewName(plupload.getRename());
        product.setIcoPath(relation_path + plupload.getRename());
		printJson(response, product);
	}
	
	/**
	 * 删除上传的无用文件
	 */
	@RequestMapping(value="/deleteDisableFile")
	public void deleteDisableFile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("删除上传的无用文件");
		}
		String icoPath = request.getParameter("icoPath");
		if(StringUtils.isNotEmpty(icoPath)){
			File softFile = new File(SysConfig.getStrValue("file_path_pre")+icoPath);
			if(softFile.exists()){
				softFile.deleteOnExit();
				try {
					FileUtils.forceDelete(softFile);
//					FileUtils.deleteDirectory(softFile);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		printText(response, "success");
	}
}
