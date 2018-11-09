package com.smart.web.action.t_liborrow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.T_borrow_registration;
import com.smart.model.T_handle;
import com.smart.model.T_liborrow;
import com.smart.model.T_liregistration;
import com.smart.service.T_borrow_registrationService;
import com.smart.service.T_handleService;
import com.smart.service.T_liborrowService;
import com.smart.service.T_liregistrationService;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class T_liborrowAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private T_liborrowService liborrowService;

	@Autowired
	private T_handleService handleService;

	@Autowired
	private T_borrow_registrationService borrow_registrationService;

	private T_borrow_registration borrow_registration;

	@Autowired
	private T_liregistrationService liregistrationService;

	private T_liborrow liborrow;

	private T_liregistration liregistration;

	private T_handle handle;

	private String rtimec;// 登记开始时间

	private String rtimej;// 登记结束时间

	private String Stringid;// 删除所需字符串id数组

	private String uname;// 登记人名称--查询

	private String lireid;// 证照id数组

	private Integer zzid;// 借据表id

	private Integer clid;// 处理id

	/**
	 * 列表and条件查询
	 */
	@Action("list")
	public String list() {
		liborrow = liborrow == null ? new T_liborrow() : liborrow;
		Page<T_liborrow> pageBean = liborrowService.getPage(pageNo, 10,
				liborrow.getDocuments(), getRtimec(), getRtimej(), getUname(),
				this.getMyDeptId(), this.getUser().getId());
		put("pageBean", pageBean);
		put("user", this.getUser().getId());
		put("dept", this.getMyDept().getId());
		return "list";
	}

	/**
	 * 归还
	 */
	@Action("revert")
	public String revert() {
		liborrow = liborrowService.get(getZzid());
		if (liborrow.getHandle().getUser().getId() == this.getUser().getId()) {
			liregistration = liregistration == null ? new T_liregistration()
					: liregistration;
			List<T_borrow_registration> list = borrow_registrationService
					.getList(liborrow.getId());
			for (int i = 0; i < list.size(); i++) {
				liregistration = list.get(i).getRegistration();
				liregistration.setLicstatus(1);// 空闲状态
				liregistrationService.update(liregistration);
			}
			liborrow = liborrowService.get(zzid);
			liborrow.setState(1);// 归还
			liborrow.setGh_date(new Date());
			liborrow.setGh_userid(this.getUser());
			liborrowService.update(liborrow);
			write("1");
		}
		return null;
	}

	/**
	 * 选择
	 */
	@Action("zzlist")
	public String zzlist() {
		// project=project==null?new Project():project;
		liregistration = liregistration == null ? new T_liregistration()
				: liregistration;
		Page<T_liregistration> pageBean = liregistrationService.getPage(pageNo,
				10, liregistration.getLicname(), liregistration.getLicnumber(),
				1);
		// List<T_liregistration> pageBean=liregistrationService.getList();
		put("pageBean", pageBean);

		return "selectRow";
	}

	/**
	 * 添加
	 */
	@Action("new")
	public String news() {
		if (liborrow != null && liborrow.getId() != null) {
			liborrow = liborrowService.get(liborrow.getId());
			put("t_liborrow", liborrow);
			List<T_borrow_registration> list = borrow_registrationService
					.getList(liborrow.getId());
			put("list", list);
		} else {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String no = "ZZSQ." + format.format(date);
			put("no", no);
			put("time", StringUtil.date2str(date, 1));
			put("name", this.getUser().getName());
		}
		return "new";
	}

	/**
	 * 删除
	 */
	@Action("delete")
	public String delete() {
		String[] a = Stringid.split(",");
		for (int i = 0; i < a.length; i++) {
			liborrow = liborrowService.get(Integer.parseInt(a[i]));
			liborrow.setStatus(-1);
			liborrowService.update(liborrow);
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 删除借用证照
	 */
	@Action("deletecls")
	public String deletecls() {
		if (!StringUtil.isBlank(getStringid())) {
			String[] a = Stringid.split(",");
			for (int i = 0; i < a.length; i++) {
				borrow_registration = borrow_registrationService
						.get(Integer.parseInt(a[i].trim()));
				if (borrow_registration != null) {
					borrow_registration.setStatus(-1);
					borrow_registrationService.update(borrow_registration);
					liregistration = borrow_registration.getRegistration();
					liregistration.setLicstatus(1);// 改为闲置状态
					liregistrationService.update(liregistration);
				}
			}
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 保存
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() {
		// 借据表
		if (liborrow != null && liborrow.getId() != null) {
			T_liborrow px = liborrowService.get(liborrow.getId());
			px.setReturntime(liborrow.getReturntime());
			px.setRemarks(liborrow.getRemarks());
			px.setSdeptid(liborrow.getSdeptid());
			liborrowService.update(px);
		} else {
			liborrow.setUser(this.getUser());
			liborrowService.save(liborrow);
		}
		if (!StringUtil.isBlank(getLireid())) {
			String[] list = lireid.split(",");
			for (int i = 0; i < list.length; i++) {
				T_liregistration liregistrations = new T_liregistration();
				liregistrations = liregistrationService
						.get(Integer.parseInt(list[i].trim()));
				// liregistrations.setLiborrow(liborrow);

				liregistrations.setLicstatus(2);// 改为占用
				liregistrationService.update(liregistrations);

				// ----------------
				T_borrow_registration borrow_registrations = new T_borrow_registration();
				borrow_registrations.setBorrow(liborrow);
				borrow_registrations.setRegistration(liregistrations);
				borrow_registrationService.save(borrow_registrations);
			}

		}
		return "list";
	}

	/**
	 * 处理
	 */
	@Action("manage")
	public String manage() {
		liborrow = liborrowService.get(getClid());
		put("t_liborrow", liborrow);
		handle = liborrow.getHandle();
		put("t_handle", handle);
		List<T_borrow_registration> list = borrow_registrationService
				.getList(getClid());
		put("list", list);
		if ((liborrow.getSdeptid().getId()).equals(this.getMyDept().getId())) {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String rctime = format.format(date);
			put("rctime", rctime);
			put("username", this.getUser().getName());
			put("clid", clid);
			return "manage";
		}
		return null;
	}

	/**
	 * 查看
	 */
	@Action("show")
	public String show() {
		liborrow = liborrowService.get(liborrow.getId());
		put("t_liborrow", liborrow);
		handle = liborrow.getHandle();
		put("t_handle", handle);
		List<T_borrow_registration> list = borrow_registrationService
				.getList(liborrow.getId());
		put("list", list);
		return "show";
	}

	/**
	 * 处理保存
	 */
	@Action(value = "managesave", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String managesave() {
		liborrow = liborrowService.get(getClid());
		if (liborrow.getHandle() == null && (liborrow.getSdeptid().getId())
				.equals(this.getMyDept().getId())) {
			handle.setUser(this.getUser());
			handle.setRtime(new Date());
			handleService.save(handle);
			liborrow = liborrowService.get(getClid());
			liborrow.setHandle(handle);
			if (handle.getSuggestion() == 2 || handle.getSuggestion() == 3) {// 不准许改证照为空闲状态
				List<T_borrow_registration> list = borrow_registrationService
						.getList(liborrow.getId());
				for (int i = 0; i < list.size(); i++) {
					liregistration = list.get(i).getRegistration();
					liregistration.setLicstatus(1);// 空闲状态
					liregistrationService.update(liregistration);
				}
			}
			if (handle.getSuggestion() == 1) {// 准许
				liborrow.setState(2);// 未归还
			}
			liborrowService.update(liborrow);
			return "list";
		}
		return null;
	}

	/*--------------------------get and set-------------------------------------*/
	public T_liborrow getLiborrow() {
		return liborrow;
	}

	public void setLiborrow(T_liborrow liborrow) {
		this.liborrow = liborrow;
	}

	public String getRtimec() {
		return rtimec;
	}

	public void setRtimec(String rtimec) {
		this.rtimec = rtimec;
	}

	public String getRtimej() {
		return rtimej;
	}

	public void setRtimej(String rtimej) {
		this.rtimej = rtimej;
	}

	public String getStringid() {
		return Stringid;
	}

	public void setStringid(String stringid) {
		Stringid = stringid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public T_handle getHandle() {
		return handle;
	}

	public void setHandle(T_handle handle) {
		this.handle = handle;
	}

	public T_liregistration getLiregistration() {
		return liregistration;
	}

	public void setLiregistration(T_liregistration liregistration) {
		this.liregistration = liregistration;
	}

	public String getLireid() {
		return lireid;
	}

	public void setLireid(String lireid) {
		this.lireid = lireid;
	}

	public Integer getZzid() {
		return zzid;
	}

	public void setZzid(Integer zzid) {
		this.zzid = zzid;
	}

	public Integer getClid() {
		return clid;
	}

	public void setClid(Integer clid) {
		this.clid = clid;
	}

	public T_borrow_registration getBorrow_registration() {
		return borrow_registration;
	}

	public void setBorrow_registration(
			T_borrow_registration borrow_registration) {
		this.borrow_registration = borrow_registration;
	}

}
