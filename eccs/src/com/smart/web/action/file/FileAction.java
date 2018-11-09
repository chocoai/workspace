package com.smart.web.action.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Annex;
import com.smart.model.AnnexType;
import com.smart.model.BidSummary;
import com.smart.model.ContractReview;
import com.smart.model.Doc;
import com.smart.model.Project;
import com.smart.model.Uptype;
import com.smart.service.AnnexService;
import com.smart.service.AnnexTypeService;
import com.smart.service.UptypeService;
import com.smart.web.action.BaseAction;

/**
 * 
 * 合同管理action
 * 
 */
@ParentPackage("control-user")
public class FileAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// @Autowired
	// private BidFileService bidFileService;
	@Autowired
	private AnnexTypeService annexTypeService;

	@Autowired
	private AnnexService annexService;

	@Autowired
	private UptypeService uptypeservice;

	private BidSummary bidSummary;

	private ContractReview contractReview;

	private Project project;

	public int proid;

	private Doc doc;

	public int getProid() {
		return proid;
	}

	public void setProid(int proid) {
		this.proid = proid;
	}

	/*
	 * 下载附件
	 */
	@Action("xiazai")
	public String xiazai() {
		Annex annexList;
		annexList = annexService.get(proid);
		String filePath = annexList.getPath();// 文件路径
		String fileName = annexList.getName();// 文件名
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			File file = new File(getAttachDir()+filePath);
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment;filename=" + fileName);
			String len = String.valueOf(file.length());
			response.setHeader("Content-Length", len);
			OutputStream os = response.getOutputStream();
			FileInputStream in = new FileInputStream(file);
			byte[] b = new byte[1024];
			int n;
			while ((n = in.read(b)) != -1) {
				os.write(b, 0, n);
			}
			in.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Action("fileSummary")
	public String fileSummary() { // 投标总结
		List<AnnexType> annexTypeList = annexTypeService.getByStep("-3", "yes"); //
		put("annexTypeList", annexTypeList);
		put("bidSummaryId", bidSummary.getId());
		return "filesummary";
	}

	@Action("fileDoc")
	public String fileDoc() { // 我的文档
		List<Uptype> upty = uptypeservice.getList((int) this.getUser().getId());
		put("upty", upty);
		doc = new Doc();
		return "filedoc";
	}

	@Action("fileReview")
	public String fileReview() { // 合同评审
		List<AnnexType> annexTypeList = annexTypeService.getByStep("-2", "yes"); //
		put("annexTypeList", annexTypeList);
		put("contractReviewId", contractReview.getId());
		return "filereview";
	}

	@Action("fileStep1")
	public String fileStep1() { // 咨询任务书
		List<AnnexType> annexTypeList = annexTypeService.getByStep("1", "yes");
		put("annexTypeList", annexTypeList);
		put("projectId", project.getId());
		return "filestep1";
	}

	@Action("fileStep15")
	public String fileStep15() { //
		List<AnnexType> annexTypeList = annexTypeService.getByStep("", "yes");
		put("annexTypeList", annexTypeList);
		put("projectId", project.getId());
		return "filestep15";
	}

	public BidSummary getBidSummary() {
		return bidSummary;
	}

	public void setBidSummary(BidSummary bidSummary) {
		this.bidSummary = bidSummary;
	}

	public ContractReview getContractReview() {
		return contractReview;
	}

	public void setContractReview(ContractReview contractReview) {
		this.contractReview = contractReview;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Doc getDoc() {
		return doc;
	}

	public void setDoc(Doc doc) {
		this.doc = doc;
	}

}