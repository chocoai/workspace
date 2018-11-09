package com.smart.web.action.bid.info;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.BidApplyAssess;
import com.smart.model.BidInfo;
import com.smart.model.BidInfoQuote;
import com.smart.model.ProceStepDef;
import com.smart.model.ProjectInfo;
import com.smart.model.ProjectProcessHistory;
import com.smart.model.ProjectProcessState;
import com.smart.model.ProjectSummary;
import com.smart.model.ProjectTransferRecord;
import com.smart.model.T_Customer;
import com.smart.model.T_hremployee;
import com.smart.model.User;
import com.smart.service.BidApplyAssessService;
import com.smart.service.BidInfoQuoteService;
import com.smart.service.BidInfoService;
import com.smart.service.ProceStepDefService;
import com.smart.service.ProjectInfoService;
import com.smart.service.ProjectProcessHistoryService;
import com.smart.service.ProjectProcessStateService;
import com.smart.service.ProjectSummaryService;
import com.smart.service.ProjectTransferRecordService;
import com.smart.service.T_hremployeeService;
import com.smart.service.UserService;
import com.smart.util.Constants;
import com.smart.util.DateUtils;
import com.smart.util.EmailSendTool;
import com.smart.util.Page;
import com.smart.web.action.BaseAction;

/**  
* @Description:开标中标模块
* @author hexiang
* @date 2017年7月5日 下午5:38:21
*/
@ParentPackage("control-user")
public class BidInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private BidInfoService bidInfoService;
	
	@Autowired
	private BidInfoQuoteService bidInfoQutoeService;
	
	@Autowired
	private ProjectSummaryService projectSummaryService;
	
	@Autowired
	private BidApplyAssessService bidApplyAssessService;
	
	@Autowired
	private ProjectTransferRecordService projectTransferRecordService;
	
	@Autowired
	private ProceStepDefService proceStepDefService;
	
	@Autowired
	private ProjectProcessStateService processStateService;
	
	@Autowired
	private T_hremployeeService hremployeeService;
	
	@Autowired
	private ProjectInfoService projectInfoService;

	@Autowired
	private ProjectProcessHistoryService projectProcessHistoryService;
	
	@Autowired
	private UserService userService;
	
	private ProjectInfo projectInfo; // 项目
	
	private BidInfo info;
	
	private String nextOperatorId;
	
	private String[] infoQuoteCompany;
	
	private Double[] infoQuoteQuotedPrice;
	
	private Double[] infoQuoteBudgetaryPrice;
	
	private Double[] infoQuoteRatio;
	
	private Integer[] infoQuoteCid;
	
	private Integer infoQuote;
	
	@Action("list")
	public String list(){
		info = info == null ? new BidInfo() : info;
		projectInfo = projectInfo == null ? new ProjectInfo() : projectInfo;
		T_Customer customer = projectInfo.getBidmen() == null?new T_Customer() : projectInfo.getBidmen();
		Page<BidInfo> pageBean = bidInfoService.getPage(pageNo, pageSize,
				customer.getCusName(), projectInfo.getProname(),projectInfo.getAgentcompany());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			BidInfo assess = pageBean.getList().get(i);
			ProjectInfo proj = assess.getProjectInfo();
			ProjectProcessState pps = processStateService
					.getOneByProjectInfoIdAndStepCode(proj.getId(),
							Constants.StepCode.BID_INFO);
			//是否可以进行编辑操作，当项目已经进行到下一步时，此处为false即不能操作
			if (pps != null) {
				String[] handlers = pps.getCurrentUsers().split(",");
				List<String> userList = Arrays.asList(handlers);
				if (userList.contains(getUser().getId().toString())) {
					assess.setResStates(true);
				}
			} else {
				assess.setResStates(false);
			}
		}
		put("pageBean", pageBean);
		put("info", info);
		put("projectInfo", projectInfo);
		return "list";
	}
	
	@Action("edit")
	public String edit(){
		info = bidInfoService.getInfoByProjectInfo(projectInfo);
		projectInfo = info.getProjectInfo();
		List<BidInfoQuote> infoQuoteList = bidInfoQutoeService.getInfoQuoteByProjectInfo(projectInfo);
		if (StringUtils.isNotBlank(projectInfo.getNextOperatorId())) {
			String[] userIds = projectInfo.getNextOperatorId().split(",");
			StringBuffer userNames = new StringBuffer();
			for (int i = 0; i < userIds.length; i++) {
				if (StringUtils.isNotBlank(userIds[i])) {
					User user = userService.get(Integer.parseInt(userIds[i]));
					userNames.append(user.getName()).append(",");	
				}
			}
			String nextOperatorName = StringUtils
					.removeEnd(userNames.toString(), ",");
			put("nextOperatorName", nextOperatorName);
			put("nextOperatorId",projectInfo.getNextOperatorId());
		}
		put("info",info);
		put("infoQuoteList",infoQuoteList);
		return "edit";
	}

	@Action("show")
	public String show(){
		info = bidInfoService.getInfoByProjectInfo(projectInfo);
		projectInfo = info.getProjectInfo();
		List<BidInfoQuote> infoQuoteList = bidInfoQutoeService.getInfoQuoteByProjectInfo(projectInfo);
		put("info",info);
		put("infoQuoteList",infoQuoteList);
		return "show";
	}

	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save(){
		
		BidInfo oldinfo = bidInfoService.get(info.getCid());
		projectInfo = oldinfo.getProjectInfo();
		
		projectInfo.setNextOperatorId(nextOperatorId);
		
		projectInfoService.update(projectInfo);
		
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_INFO);
		// 更新项目进程处理历史记录表
		ProceStepDef currentStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_INFO);
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(currentStep);
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SAVE);
		pph.setNextStep(currentStep);
		pph.setNextHandlers(pps.getCurrentUsers());
		projectProcessHistoryService.save(pph);

		oldinfo.setBidOpenPerson(info.getBidOpenPerson());
		oldinfo.setBidNumber(info.getBidNumber());
		oldinfo.setBidWinner(info.getBidWinner());
		oldinfo.setBidWinnerPrice(info.getBidWinnerPrice());
		oldinfo.setIsSuccess(info.getIsSuccess());
		bidInfoService.update(oldinfo);
		BidInfoQuote infoQuote = null;
		int i = 0;
		if(null != infoQuoteCid && infoQuoteCid.length>0){
			for(;i<infoQuoteCid.length;i++){
				infoQuote = bidInfoQutoeService.get(infoQuoteCid[i]);
				infoQuote.setProjectInfo(projectInfo);
				infoQuote.setCompany(infoQuoteCompany[i]);
				infoQuote.setBudgetaryPrice(infoQuoteBudgetaryPrice[i]);
				infoQuote.setQuotedPrice(infoQuoteQuotedPrice[i]);
				infoQuote.setRatio(infoQuoteRatio[i]);
				if(StringUtils.isNotBlank(infoQuoteCompany[i])){
					bidInfoQutoeService.update(infoQuote);
				}
			}
		}
		if(null != infoQuoteCompany && infoQuoteCompany.length>0){
			for(;i<infoQuoteCompany.length;i++){
				infoQuote = new BidInfoQuote();
				infoQuote.setProjectInfo(projectInfo);
				infoQuote.setCompany(infoQuoteCompany[i]);
				infoQuote.setBudgetaryPrice(infoQuoteBudgetaryPrice[i]);
				infoQuote.setQuotedPrice(infoQuoteQuotedPrice[i]);
				infoQuote.setRatio(infoQuoteRatio[i]);
				if(StringUtils.isNotBlank(infoQuoteCompany[i])){
					bidInfoQutoeService.save(infoQuote);
				}
			}
		}
		return "list";
	}

	@Action(value = "toNextStep", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String toNextStep(){

		BidInfo oldinfo = bidInfoService.get(info.getCid());
		projectInfo = oldinfo.getProjectInfo();
		
		projectInfo.setNextOperatorId(nextOperatorId);
		
		projectInfoService.update(projectInfo);
		
		// 下一环节处理人
		String nextOperatorId = get("nextOperatorId");

		ProceStepDef nextStep = proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_SUMMARY);
		if(info.getIsSuccess()==1){
			nextStep = proceStepDefService
					.getStepByStepCode(Constants.StepCode.BID_TRANSFER);
		}
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_INFO);
		pps.setCurrentStep(nextStep);
		pps.setCurrentUsers(nextOperatorId);
		pps.setCurrentState(ProjectProcessState.IN_PROGRESS);
		pps.setLastUpdateTime(DateUtils.getCurrentTime());
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_INFO));
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.SUBMIT);
		pph.setNextStep(nextStep);
		pph.setNextHandlers(nextOperatorId);
		projectProcessHistoryService.save(pph);
		
		oldinfo.setBidOpenPerson(info.getBidOpenPerson());
		oldinfo.setBidNumber(info.getBidNumber());
		oldinfo.setBidWinner(info.getBidWinner());
		oldinfo.setBidWinnerPrice(info.getBidWinnerPrice());
		oldinfo.setIsSuccess(info.getIsSuccess());
		bidInfoService.update(oldinfo);
		BidInfoQuote infoQuote = null;
		int i = 0;
		if(null != infoQuoteCid && infoQuoteCid.length>0){
			for(;i<infoQuoteCid.length;i++){
				infoQuote = bidInfoQutoeService.get(infoQuoteCid[i]);
				infoQuote.setCompany(infoQuoteCompany[i]);
				infoQuote.setBudgetaryPrice(infoQuoteBudgetaryPrice[i]);
				infoQuote.setQuotedPrice(infoQuoteQuotedPrice[i]);
				infoQuote.setRatio(infoQuoteRatio[i]);
				if(StringUtils.isNotBlank(infoQuoteCompany[i])){
					bidInfoQutoeService.update(infoQuote);
				}
			}
		}
		if(null != infoQuoteCompany && infoQuoteCompany.length>0){
			for(;i<infoQuoteCompany.length;i++){
				infoQuote = new BidInfoQuote();
				infoQuote.setCompany(infoQuoteCompany[i]);
				infoQuote.setBudgetaryPrice(infoQuoteBudgetaryPrice[i]);
				infoQuote.setQuotedPrice(infoQuoteQuotedPrice[i]);
				infoQuote.setRatio(infoQuoteRatio[i]);
				if(StringUtils.isNotBlank(infoQuoteCompany[i])){
					bidInfoQutoeService.save(infoQuote);
				}
			}
		}
		BidApplyAssess applyAssess = bidApplyAssessService.getApplyAssessByProjectInfo(projectInfo);
		if(info.getIsSuccess()==1){
			ProjectTransferRecord transfer = new ProjectTransferRecord();
			String applyNo = Calendar.getInstance().get(Calendar.YEAR) + String.format("%04d", (Integer.valueOf(projectTransferRecordService.getTransferLast().substring(4, 8))+1));
			transfer.setTransferNo(Integer.valueOf(applyNo));
			transfer.setProjectInfo(projectInfo);
			transfer.setConsultType(applyAssess.getConsultType());
			transfer.setCallBidFile(applyAssess.getCallBidFile());
			transfer.setBidPrice(info.getBidWinnerPrice());
			transfer.setStatus(1);
			transfer.setCreateTime(DateUtils.getCurrentTime());
			projectTransferRecordService.save(transfer);
		}
		ProjectSummary summary = new ProjectSummary();
		summary.setProjectInfo(projectInfo);
		summary.setCreateTime(DateUtils.getCurrentTime());
		summary.setStatus(1);
		projectSummaryService.save(summary);

	    //没有项目编号先空着
	    // 发送邮件通知下一步处理人有待办事项需要处理
		// 查询下一步处理人的邮箱地址
		StringBuilder emailAddress = new StringBuilder(); 
		if (StringUtils.isNotBlank(nextOperatorId)) {
			String[] userIds = nextOperatorId.split(",");
			for (int x = 0; x < userIds.length; x++) {
				if (StringUtils.isNumeric(userIds[x])) {
					T_hremployee hrEmployee = hremployeeService.getSysCode(Integer.valueOf(userIds[x]));
					if (hrEmployee != null) {
						emailAddress.append(hrEmployee.getEmail()).append(",");
					}
				}
			}
			EmailSendTool sendEmail = new EmailSendTool();
			String subject = sendEmail.getSubject();
			String emailContent = sendEmail.buildContent(projectInfo.getProNo(), projectInfo.getProname(), nextStep.getStepName());
			sendEmail.send(emailAddress.toString(), subject, emailContent);
		}
		return "list";
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		info = bidInfoService.get(info.getCid());
		info.setStatus(0);
		bidInfoService.update(info);
		// 更新项目进程状态表信息
		ProjectProcessState pps = processStateService
				.getOneByProjectInfoIdAndStepCode(projectInfo.getId(),
						Constants.StepCode.BID_INFO);
		pps.setCurrentStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.STOPED));
		pps.setCurrentUsers("");
		pps.setCurrentState(ProjectProcessState.STOPED);
		processStateService.update(pps);
		// 更新项目进程处理历史记录表
		ProjectProcessHistory pph = new ProjectProcessHistory();
		pph.setProjectInfo(projectInfo);
		pph.setOperateStep(proceStepDefService
				.getStepByStepCode(Constants.StepCode.BID_INFO));
		pph.setOperateUser(getUser());
		pph.setOperateTime(DateUtils.getCurrentTime());
		pph.setOperateType(Constants.OperateType.TERMINATE);
		pph.setNextStep(null);
		pph.setNextHandlers("");
		projectProcessHistoryService.save(pph);
		write("1"); // ajax请求用write返回数据
		return null;
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("deleteInfoQuote")
	public String deleteInfoQuote() {
		bidInfoQutoeService.delete(infoQuote);
		write("1"); // ajax请求用write返回数据
		return null;
	}

	public Integer getInfoQuote() {
		return infoQuote;
	}

	public void setInfoQuote(Integer infoQuote) {
		this.infoQuote = infoQuote;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public BidInfo getInfo() {
		return info;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public void setInfo(BidInfo info) {
		this.info = info;
	}

	public String getNextOperatorId() {
		return nextOperatorId;
	}

	public void setNextOperatorId(String nextOperatorId) {
		this.nextOperatorId = nextOperatorId;
	}
	
	public String[] getInfoQuoteCompany() {
		return infoQuoteCompany;
	}

	public Double[] getInfoQuoteQuotedPrice() {
		return infoQuoteQuotedPrice;
	}

	public Double[] getInfoQuoteBudgetaryPrice() {
		return infoQuoteBudgetaryPrice;
	}

	public Double[] getInfoQuoteRatio() {
		return infoQuoteRatio;
	}

	public void setInfoQuoteCompany(String[] infoQuoteCompany) {
		this.infoQuoteCompany = infoQuoteCompany;
	}

	public void setInfoQuoteQuotedPrice(Double[] infoQuoteQuotedPrice) {
		this.infoQuoteQuotedPrice = infoQuoteQuotedPrice;
	}

	public void setInfoQuoteBudgetaryPrice(Double[] infoQuoteBudgetaryPrice) {
		this.infoQuoteBudgetaryPrice = infoQuoteBudgetaryPrice;
	}

	public void setInfoQuoteRatio(Double[] infoQuoteRatio) {
		this.infoQuoteRatio = infoQuoteRatio;
	}
	
	public Integer[] getInfoQuoteCid() {
		return infoQuoteCid;
	}

	public void setInfoQuoteCid(Integer[] infoQuoteCid) {
		this.infoQuoteCid = infoQuoteCid;
	}


}


