package com.yhcrt.healthcloud.mall.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.mall.entity.YwImage;
import com.yhcrt.healthcloud.mall.service.YwImageService;
import com.yhcrt.healthcloud.system.service.SysSequenceService;

/* @Description: 上传文件controller层
 * @version	1.0		2017年9月7日
 * @author jimmy
*/
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController{

    @Autowired
    private YwImageService ywImageService;
    @Autowired
    private SysSequenceService sysSequenceService;

    /**
     * @Title: upload
     * @Description: 通用上传图片方法
     * @param request
     * @param response
     * @param commonId  图片要关联的数据的ID
     * @param type  图片类型(org/service/goods)
     * @param upload_file   图片文件
     * @param id    图片唯一标识符，用于拼接在时间后面，避免同一时间内上传了多张图片导致重名覆盖
     * @param size  图片大小
     */
    @RequestMapping("/img")
    @ResponseBody
    public void upload(HttpServletRequest request,HttpServletResponse response,Integer commonId,String module_code,
            @RequestParam("upfile")MultipartFile upload_file,String id,String size){
        SimpleDateFormat sdForName = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileOSPath = new File(request.getSession().getServletContext().getRealPath("/")).getParent();
        String fileAbsolutePath = "/upload/"+module_code+"/"+commonId+"/"+sdForName.format(new Date())+id+
                upload_file.getOriginalFilename().substring(upload_file.getOriginalFilename().lastIndexOf('.'));
        File dest = new File(fileOSPath+fileAbsolutePath);
        JSONObject obj = new JSONObject();
        //返回上传结果，0为失败，1为成功
        int i = 0;
        try {
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            upload_file.transferTo(dest);
            YwImage img = new YwImage();
            img.setImgId(sysSequenceService.getSequenceValue(Constants.SequenceName.YW_IMG));
            img.setRefId(commonId);
            img.setModuleCode(module_code);
            img.setImgPath(fileAbsolutePath);
            img.setPathPrefix(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());
            img.setRealPath(fileOSPath+fileAbsolutePath);
            img.setUploadTime(sdf.format(new Date()));
            img.setUploadUser(getLoginUser().getUserId().toString());
            img.setStatus(Constants.STATUS_NORMAL);
            i = ywImageService.add(img);
            if(i == 1){
                obj.put("state", "SUCCESS");
                obj.put("url", fileAbsolutePath);
            }else{
                obj.put("state", "FALSE");
            }
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 删除文件同时删除数据库图片所在的数据
     * @param request
     * @param response
     * @param imgId
     */
    @RequestMapping(value = "delImg", method = RequestMethod.POST)
    @ResponseBody
    public void delImg(HttpServletRequest request, HttpServletResponse response,Integer imgId){
        YwImage img = ywImageService.get(imgId);
        File file = new File(img.getRealPath());
        file.delete();
        ywImageService.del(imgId);
    }
    
}
