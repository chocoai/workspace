package com.yhcrt.weihu.cms.action.front;

import static com.yhcrt.weihu.cms.Constants.TPLDIR_SPECIAL;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yhcrt.weihu.cms.entity.assist.CmsTT;
import com.yhcrt.weihu.cms.entity.assist.CmsTType;
import com.yhcrt.weihu.cms.manager.assist.CmsTTMng;
import com.yhcrt.weihu.cms.manager.assist.CmsTTypeMng;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.web.util.CmsUtils;
import com.yhcrt.weihu.core.web.util.FrontUtils;

@Controller
public class TTAct {


	public static final String TT_INDEX = "tpl.ttIndex";
	
	public static final String TT_DETAIL = "tpl.ttDetail";

	/**
	 * 天天信息首页
	 * 
	 * @param typeId 类别
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tt.jspx", method = RequestMethod.GET)
	public String index(Integer typeId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		CmsTType type = null;
		if (typeId != null) {
			type = cmsTTypeMng.findById(typeId);
		}
		List<CmsTType> typeList = cmsTTypeMng.getList(site.getId());
		model.addAttribute("typeId", typeId);
		model.addAttribute("typeList", typeList);
		model.addAttribute("type", type);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_SPECIAL, TT_INDEX);
	}


	
	@RequestMapping(value = "/tt/{id}.jspx", method = RequestMethod.GET)
	public String detail(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsTT tt = null;
		if (id != null) {
			tt = cmsTTMng.findById(id);
		}
		List<CmsTType> typeList = cmsTTypeMng.getList(site.getId());
		//浏览数+1
		if(tt.getReadCount() == null){
			tt.setReadCount(1);
		}else{
			tt.setReadCount(tt.getReadCount() + 1);
		}
		cmsTTMng.update(tt);
		model.addAttribute("typeList", typeList);
		model.addAttribute("tt", tt);
		
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_SPECIAL, TT_DETAIL);

	}

	@Autowired
	private CmsTTypeMng cmsTTypeMng;
	@Autowired
	private CmsTTMng cmsTTMng;

}
