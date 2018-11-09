package com.smart.web.action.notice;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Dept;
import com.smart.model.Notice;
import com.smart.model.NoticeItem;
import com.smart.service.DeptService;
import com.smart.service.NoticeItemService;
import com.smart.service.NoticeService;
import com.smart.util.Page;
import com.smart.util.ReflectionUtil;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

/**
 * 说明：根据包名的路径自动装配 比如本action路径，com.smart.demo.DemoAction，对应的页面文件夹content/demo
 * return "new" 则会找到content/demo/new.ftl
 * 
 * control-user 权限拦截，使用UserInterceptor拦截器
 */
@ParentPackage("control-user")
public class NoticeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DeptService deptService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private NoticeItemService noticeItemService;

	private NoticeItem noticeItem;

	private String noticeItemId;

	private Notice notice;

	private String ctimes;

	private String ctimee;

	private int id;

	/**
	 * 新建
	 */
	@Action("new")
	public String _new() {
		List<NoticeItem> noticeItemList = noticeItemService
				.getList(this.getMyDept().getComid());
		put("noticeItemList", noticeItemList);
		notice = new Notice();
		return "new";
	}

	@Action("newitem")
	public String newitem() {
		List<NoticeItem> noticeItemList = noticeItemService
				.getList(this.getMyDept().getComid());
		put("noticeItemList", noticeItemList);
		noticeItem = new NoticeItem();
		return "newitem";
	}

	/**
	 * 保存
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String save() {
		noticeItem = noticeItemService
				.get(StringUtil.str2Integer(noticeItemId));
		notice.setNoticeItem(noticeItem);
		if (notice.getId() == null) { // 新增
			notice.setUser(this.getUser());
			notice.setDept(this.getMyDept());
			notice.setComid(this.getMyDept().getComid());
			noticeService.save(notice);
		} else { // 更新
			Notice oldNotice = noticeService.get(notice.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldNotice, notice, "id, status,comid");
			oldNotice.setUser(this.getUser());
			oldNotice.setDept(this.getMyDept());
			noticeService.save(oldNotice);
		}
		return "list";
	}

	@Action(value = "saveitem", results = {
			@Result(name = "newitem", location = "newitem", type = "redirectAction") })
	public String saveitem() {
		if (noticeItem.getId() == null) { // 新增
			noticeItem.setUser(this.getUser());
			noticeItem.setDept(this.getMyDept());
			noticeItem.setComid(this.getMyDept().getComid());
			noticeItemService.save(noticeItem);
		} else { // 更新
			NoticeItem oldNoticeItem = noticeItemService
					.get(noticeItem.getId());
			// 属性拷贝，把更新内容拷贝到数据库查出的project里，最后的那个参数是不需要拷贝的属性名称
			ReflectionUtil.bean2Bean(oldNoticeItem, noticeItem,
					"id ,user,dept,comid");
			noticeItemService.update(oldNoticeItem);
		}
		return "newitem";
	}

	/**
	 * 编辑
	 */
	@Action("edit")
	public String edit() {
		String deptid = this.getMyDeptId();
		Dept dept1 = deptService.get(deptid);
		List<NoticeItem> noticeItemList = noticeItemService
				.getList(dept1.getComid());
		put("noticeItemList", noticeItemList);
		// 项目分类
		notice = noticeService.get(notice.getId());
		return "new";
	}

	/**
	 * 列表
	 */
	@Action("list")
	public String list() {
		notice = notice == null ? new Notice() : notice;
		noticeItem = noticeItemService.get(id);
		put("noticeItem", noticeItem);
		List<NoticeItem> Item = noticeItemService
				.getList(this.getMyDept().getComid());
		put("Item", Item);
		// String deptid = this.getMyDeptId();
		Page<Notice> pageBean = noticeService.getPage(getPageNo(),
				getPageSize(), id, notice.getTitle(), ctimes, ctimee,
				this.getMyDept().getComid());
		put("pageBean", pageBean);
		return "list";
	}

	@Action(value = "/notices/lists", results = {
			@Result(name = "lists", location = "/WEB-INF/content/notice/lists.ftl") })
	public String lists() {
		notice = notice == null ? new Notice() : notice;
		List<NoticeItem> noticeItemList = noticeItemService
				.getList(this.getMyDept().getComid());
		put("noticeItemList", noticeItemList);
		// String deptid = this.getMyDeptId();
		Page<Notice> pageBean = noticeService.getPage(getPageNo(),
				getPageSize(), id, notice.getTitle(), ctimes, ctimee,
				this.getMyDept().getComid());
		put("pageBean", pageBean);
		return "lists";
	}

	/**
	 * 显示
	 * 
	 * @return
	 */
	@Action("show")
	public String show() {
		notice = noticeService.get(notice.getId());
		return "show";
	}

	@Action(value = "/notices/shows", results = {
			@Result(name = "shows", location = "/WEB-INF/content/notice/shows.ftl") })
	public String shows() {
		notice = noticeService.get(notice.getId());
		return "shows";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		notice = noticeService.get(notice.getId());
		notice.setStatus(-1);
		noticeService.update(notice);

		write("1"); // ajax请求用write返回数据
		return null;
	}

	@Action(value = "deleteItem", results = {
			@Result(name = "newitem", location = "newitem", type = "redirectAction") })
	public String deleteItem() {
		noticeItem = noticeItemService.get(noticeItem.getId());
		noticeItem.setStatus(-1);
		noticeItemService.update(noticeItem);
		return "newitem";
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public String getCtimes() {
		return ctimes;
	}

	public void setCtimes(String ctimes) {
		this.ctimes = ctimes;
	}

	public String getCtimee() {
		return ctimee;
	}

	public void setCtimee(String ctimee) {
		this.ctimee = ctimee;
	}

	public NoticeItem getNoticeItem() {
		return noticeItem;
	}

	public void setNoticeItem(NoticeItem noticeItem) {
		this.noticeItem = noticeItem;
	}

	public String getNoticeItemId() {
		return noticeItemId;
	}

	public void setNoticeItemId(String noticeItemId) {
		this.noticeItemId = noticeItemId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
