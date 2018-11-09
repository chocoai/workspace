package com.smart.web.action.invoice;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Contract;
import com.smart.model.Project;
import com.smart.model.T_Invoice;
import com.smart.service.ContractService;
import com.smart.service.ProjectService;
import com.smart.service.T_InvoiceService;
import com.smart.service.T_ReceivablesService;
import com.smart.util.Page;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class T_InvoiceAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private T_InvoiceService invoiceService;

	@Autowired
	private T_ReceivablesService receivablesService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ContractService contractService;

	private T_Invoice invoice;

	private Project project;

	public T_Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(T_Invoice invoice) {
		this.invoice = invoice;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		List<Project> prjList = projectService.getAll();
		List<Contract> clist = contractService.getAll();
		List<T_Invoice> tiList = invoiceService.getAll();
		List<?> aclist = invoiceService.getcAccount();
		Object[] obj = new Object[2];
		int tpid = 0;
		int cpid = 0;
		for (int i = 0; i < tiList.size(); i++) {
			tpid = tiList.get(i).getProject().getId();
			// 项目应收款
			for (int j = 0; j < clist.size(); j++) {
				cpid = clist.get(j).getProject().getId();
				if (tpid == cpid) {
					tiList.get(i).setrAccount(clist.get(j).getPayAmount());
				}
			}
			// 累计已开票
			for (int k = 0; k < aclist.size(); k++) {
				obj = (Object[]) aclist.get(k);
				if (tpid == Integer.parseInt(obj[0].toString())) {
					tiList.get(i)
							.setcAccount(Double.parseDouble(obj[1].toString()));
				}
			}

		}
		// 未开发票
		for (int i = 0; i < tiList.size(); i++) {
			tiList.get(i).setwAccount(
					tiList.get(i).getrAccount() - tiList.get(i).getcAccount());
		}

		put("prjList", prjList);
		put("tiList", tiList);
		invoice = new T_Invoice();
		return "new";
	}

	/**
	 * 编辑
	 */
	@Action("edit")
	public String edit() {
		invoice = invoiceService.get(invoice.getId());
		return "new";
	}

	/**
	 * 保存
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() {

		invoice = new T_Invoice();
		return "list";
	}

	/**
	 * 列表
	 */
	@Action("list")
	public String list() {
		// 项目累计开票票
		List<?> aclist = invoiceService.getcAccount();
		// 发票累计收款
		List<?> dzlist = receivablesService.getsumAccount();
		List<Contract> clist = contractService.getAll();
		Object[] obj = new Object[2];
		invoice = invoice == null ? new T_Invoice() : invoice;
		project = project == null ? new Project() : project;

		int tpid = 0;
		int cpid = 0;
		int id = 0;
		Page<T_Invoice> pageBean = invoiceService.getPage(getPageNo(), pageSize,
				project.getName(), invoice.getInvoiceNo(), project.getNo(),
				invoice.getReStatus(), null, null);
		for (int i = 0; i < pageBean.getList().size(); i++) {
			tpid = pageBean.getList().get(i).getProject().getId();
			id = pageBean.getList().get(i).getId();
			// 项目应收款
			for (int j = 0; j < clist.size(); j++) {
				cpid = clist.get(j).getProject().getId();
				if (tpid == cpid) {
					pageBean.getList().get(i)
							.setrAccount(clist.get(j).getPayAmount());
				}
			}
			// 累计已开票
			for (int k = 0; k < aclist.size(); k++) {
				obj = (Object[]) aclist.get(k);
				if (tpid == Integer.parseInt(obj[0].toString())) {
					pageBean.getList().get(i)
							.setcAccount(Double.parseDouble(obj[1].toString()));
				}
			}
			// 已收款总额
			for (int k = 0; k < dzlist.size(); k++) {
				obj = (Object[]) dzlist.get(k);
				if (id == Integer.parseInt(obj[0].toString())) {
					pageBean.getList().get(i)
							.setaAccount(Double.parseDouble(obj[1].toString()));
				}
			}
		}
		put("pageBean", pageBean);
		return "list";
	}

}
