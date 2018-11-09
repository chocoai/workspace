package com.yhcrt.healthcloud.mall.controller;	
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.mall.entity.YwImage;
import com.yhcrt.healthcloud.mall.service.YwImageService;	
	
/* @Description: 图片展示	
 * @version 1.0 
 * @author hull	
*/	
@RequestMapping("/ywImage")	
@Controller	
public class YwImageController extends BaseController{	
    @Autowired  
    private YwImageService ywImageService;
    	
    @RequestMapping(value = "/showImage", method = RequestMethod.POST)
    @ResponseBody
    public List<YwImage> showImage(Integer uid ,String type){	
        List<YwImage> imgList = ywImageService.getByRefId(uid, type);
        return imgList;
    }	

}	
