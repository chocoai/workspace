package com.smart.web.action.index;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@Results({
		@Result(name = "workbench", location = "/WEB-INF/content/index/workbench.ftl"),
		@Result(name = "marketManager", location = "/WEB-INF/content/index/marketManager.ftl"),
		@Result(name = "bidPlanning", location = "/WEB-INF/content/index/bidPlanning.ftl"),
		@Result(name = "projectManager", location = "/WEB-INF/content/index/projectManager.ftl"),
		@Result(name = "serror1", location = "/WEB-INF/content/index/serror1.ftl"),
		@Result(name = "serror2", location = "/WEB-INF/content/index/serror2.ftl"),
		@Result(name = "systemManager", location = "/WEB-INF/content/index/systemManager.ftl"),
		@Result(name = "dailyOfficeManager", location = "/WEB-INF/content/index/dailyOfficeManager.ftl"),
		@Result(name = "performanceManager", location = "/WEB-INF/content/index/performanceManager.ftl"),
		@Result(name = "financeManager", location = "/WEB-INF/content/index/financeManager.ftl"),
		@Result(name = "knoledgeManager", location = "/WEB-INF/content/index/knoledgeManager.ftl"),
		@Result(name = "cusManager", location = "/WEB-INF/content/index/cusManager.ftl"), })

@ParentPackage("control-user")
public class IndexAction extends BaseAction {

	private static final long serialVersionUID = 8339132241607266067L;

	private String serviceTypeId;

	@Action("/workbench")
	public String workbench() {
		return "workbench";
	}

	@Action("/marketManager")
	public String marketManager() {
		return "marketManager";
	}
	
	@Action("/bidPlanning")
	public String bidPlanning() {
		return "bidPlanning";
	}

	@Action("/cusManager")
	public String cusManager() {
		return "cusManager";
	}

	@Action("/projectManager")
	public String projectManager() {
		if (!StringUtil.isBlank(serviceTypeId)) {// 5 3
			getSession().put("serviceTypeId", serviceTypeId);
		} else {
			serviceTypeId = (String) getSession().get("serviceTypeId");
			if (!StringUtil.isBlank(serviceTypeId)) {
				getSession().put("serviceTypeId", serviceTypeId);
			}
		}
		if (serviceTypeId.equals("3")) {
			return "serror1";
		} else if (serviceTypeId.equals("5")) {
			return "projectManager";
		} else {
			return "serror2";
		}
	}

	@Action("/systemManager")
	public String systemManager() {
		return "systemManager";
	}

	@Action("/performanceManager")
	public String performanceManager() {
		return "performanceManager";
	}

	@Action("/dailyOfficeManager")
	public String dailyOfficeManager() {
		return "dailyOfficeManager";
	}

	@Action("/financeManager")
	public String financeManager() {
		return "financeManager";
	}

	@Action("/knoledgeManager")
	public String knoledgeManager() {
		return "knoledgeManager";
	}

	public String getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

}
