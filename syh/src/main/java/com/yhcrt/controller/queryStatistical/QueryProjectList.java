package com.yhcrt.controller.queryStatistical;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 项目信息统计管理
 * @author 陈伟
 * 2017年6月13日 上午10:00:45
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/queryProjectList")
public class QueryProjectList {
	
	@RequestMapping("/index")
	public ModelAndView queryScoreInfo(){
		return new ModelAndView("queryStatistical/queryProjectList");
	}
}