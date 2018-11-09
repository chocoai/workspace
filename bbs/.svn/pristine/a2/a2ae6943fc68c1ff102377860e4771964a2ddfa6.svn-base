package com.yhcrt.weihu.bbs.action.member;

import static com.yhcrt.weihu.bbs.Constants.TPLDIR_MEMBER;
import static com.yhcrt.weihu.bbs.Constants.TPLDIR_TOPIC;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhcrt.weihu.bbs.action.member.BbsReportFrontAct;
import com.yhcrt.weihu.bbs.entity.BbsReport;
import com.yhcrt.weihu.bbs.entity.BbsReportExt;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.manager.BbsReportExtMng;
import com.yhcrt.weihu.bbs.manager.BbsReportMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.core.entity.CmsSite;

@Controller
public class BbsReportFrontAct {
	private static final Logger log = LoggerFactory
			.getLogger(BbsReportFrontAct.class);
	public static final String TPL_NO_LOGIN = "tpl.nologin";
	public static final String TPL_REPORT_PAGE = "tpl.reportpage";
	public static final String ANCHOR = "#pid";

	/**
	 * 2016-11-4 将原本的方法改成了ajax提交及返回
	 */
	@RequestMapping("/member/getreportpage.jspx")
	@ResponseBody
	public String getbuymagicpage(String url,String pid, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		JSONObject json = new JSONObject();
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
//		model.addAttribute("url",site.getProtocol()+site.getDomain()+":"+site.getPort()+url+ANCHOR+pid);
//		return FrontUtils.getTplPath(request, site,
//				TPLDIR_MEMBER, TPL_REPORT_PAGE);
		json.put("url", site.getProtocol()+site.getDomain()+":"+site.getPort()+url+ANCHOR+pid);
		json.put("status", 1);
		return json.toString();
	}

	@RequestMapping(value="/member/report.jhtml",method = RequestMethod.POST)
	@ResponseBody
	public String report(String url, String reason,String pid, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		BbsUser user=CmsUtils.getUser(request);
		CmsSite site = CmsUtils.getSite(request);
		Calendar calendar=Calendar.getInstance();
		url = site.getProtocol()+site.getDomain()+":"+site.getPort()+url+ANCHOR+pid;
		BbsReport report=reportMng.findByUrl(url);
		BbsReportExt reportExt;
		//已经举报过该地址
		if(report!=null){
			reportExt=reportExtMng.findByReportUid(report.getId(), user.getId());
			//同一个人重复举报同一个地址
			if(reportExt!=null){
				reportExt.setReportReason(reason);
				reportExtMng.update(reportExt);
			}else{
				//不同人举报同一个地址
				reportExt=new BbsReportExt();
				reportExt.setReport(report);
				reportExt.setReportReason(reason);
				reportExt.setReportTime(calendar.getTime());
				reportExt.setReportUser(user);
				reportExtMng.save(reportExt);
			}
		}else{
			//举报新的地址
			report=new BbsReport();
			report.setReportUrl(url);
			report.setStatus(false);
			report.setReportTime(calendar.getTime());
			reportMng.save(report);
			reportExt=new BbsReportExt();
			reportExt.setReport(report);
			reportExt.setReportReason(reason);
			reportExt.setReportTime(calendar.getTime());
			reportExt.setReportUser(user);
			reportExtMng.save(reportExt);
		}
		return "1";
	}

	@Autowired
	private BbsReportMng reportMng;
	@Autowired
	private BbsReportExtMng reportExtMng;
}
