package com.smart.web.action.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.Calendar;
import com.smart.service.CalendarService;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class CalendarAction extends BaseAction {

	private static final long serialVersionUID = -3278707646998164665L;

	@Autowired
	private CalendarService calendarService;

	@Action("myCalendar")
	public String myCalendar() {

		return "myCalendar";
	}

	@Action("new")
	public String create() {
		return "new";
	}

	@Action("save")
	public void save() {
		JSONObject jsonObject = new JSONObject();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String calId = get("calId");

			Calendar calendar = new Calendar();

			calendar.setCalTitle(get("calTitle"));
			calendar.setCalType(Integer.parseInt(get("calType")));// 日程类型，0表示个人日程
			calendar.setCalContent(get("calContent"));
			calendar.setCalStartTime(sdf.parse(get("calStartTime")));
			calendar.setCalEndTime(sdf.parse(get("calEndTime")));
			calendar.setCalAddress(get("calAddress"));
			calendar.setCalPerson(get("calPerson"));
			calendar.setUser(getUser());
			calendar.setDept(getMyDept());
			calendar.setCalCreateTime(new Date());

			if (StringUtil.isBlank(calId)) {
				calId = Long.toString(System.currentTimeMillis());
				calendar.setCalId(calId);
				calendarService.save(calendar);
			} else {
				calendar.setCalId(calId);
				calendarService.update(calendar);
			}

			jsonObject.put("IsSuccess", true);
			jsonObject.put("Msg", "");
			jsonObject.put("Data", calendar.getCalId());
		} catch (ParseException e) {
			jsonObject.put("IsSuccess", false);
			jsonObject.put("Msg", e.toString());
			e.printStackTrace();
		} finally {
			writeJson(jsonObject.toString());
		}
	}

	/**
	 * 添加日程
	 * 
	 * @return
	 */
	@Action("add")
	public String add() {

		JSONObject jsonObject = new JSONObject();
		try {
			String calTitle = get("CalendarTitle");
			String calStartTime = get("CalendarStartTime");
			String calEndTime = get("CalendarEndTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			Calendar calendar = new Calendar();
			calendar.setCalId(Long.toString(System.currentTimeMillis()));
			calendar.setCalTitle(calTitle);
			calendar.setCalType(0);// 日程类型，0表示个人日程
			calendar.setCalContent(calTitle);
			calendar.setCalStartTime(sdf.parse(calStartTime));
			calendar.setCalEndTime(sdf.parse(calEndTime));
			calendar.setCalAddress("");
			calendar.setCalPerson("");
			calendar.setUser(getUser());
			calendar.setDept(getMyDept());
			calendar.setCalCreateTime(new Date());
			calendarService.save(calendar);

			jsonObject.put("IsSuccess", true);
			jsonObject.put("Msg", "");
			jsonObject.put("Data", calendar.getCalId());
		} catch (ParseException e) {
			jsonObject.put("IsSuccess", false);
			jsonObject.put("Msg", e.toString());
			e.printStackTrace();
		} finally {
			writeJson(jsonObject.toString());
		}
		return null;
	}

	/**
	 * 删除我的日程
	 * 
	 * @return
	 */
	@Action("delete")
	public String delete() {
		JSONObject jsonObject = new JSONObject();
		try {
			String calId = get("calendarId");
			calendarService.delete(calId);
			jsonObject.put("IsSuccess", true);
			jsonObject.put("Msg", "");
		} catch (Exception e) {
			jsonObject.put("IsSuccess", false);
			jsonObject.put("Msg", e.toString());
			e.printStackTrace();
		} finally {
			writeJson(jsonObject.toString());
		}
		return null;
	}

	@Action("edit")
	public String edit() {
		String calId = get("calId");
		Calendar calendar = calendarService.get(calId);
		put("calendar", calendar);
		return "new";
	}

	@Action("show")
	public String query() {
		String calId = get("calId");
		Calendar calendar = calendarService.get(calId);
		put("calendar", calendar);
		return "show";
	}

	/**
	 * 更新我的日程
	 * 
	 * @return
	 */
	@Action("update")
	public String update() {
		JSONObject jsonObject = new JSONObject();
		try {
			String calId = get("calendarId");

			String calStartTime = get("CalendarStartTime");
			String calEndTime = get("CalendarEndTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			Calendar calendar = calendarService.get(calId);
			calendar.setCalType(0);// 日程类型，0表示个人日程

			calendar.setCalStartTime(sdf.parse(calStartTime));
			calendar.setCalEndTime(sdf.parse(calEndTime));

			calendar.setUser(getUser());
			calendar.setDept(getMyDept());

			calendarService.update(calendar);

			jsonObject.put("IsSuccess", true);
			jsonObject.put("Msg", "");
		} catch (ParseException e) {
			jsonObject.put("IsSuccess", false);
			jsonObject.put("Msg", e.toString());
			e.printStackTrace();
		} finally {
			writeJson(jsonObject.toString());
		}
		return null;
	}

	/**
	 * 查询我的日程
	 * 
	 * @return
	 */
	@Action("list")
	public String list() {

		JSONObject jsonObject = new JSONObject();
		try {
			int userId = (int) this.getUseId();
			List<Calendar> calList = calendarService
					.getCalendarByUserId(userId);
			List<Object> events = new ArrayList<Object>();
			for (Calendar cal : calList) {
				Object[] item = { cal.getCalId(), cal.getCalTitle(),
						cal.getCalStartTime().getTime(),
						cal.getCalEndTime().getTime(), 0, 0, 0, 1, 1,
						cal.getCalAddress(), cal.getCalPerson() };
				events.add(item);
			}

			jsonObject.put("events", events.toArray());
			jsonObject.put("issort", true);
			jsonObject.put("start", new Date());
			jsonObject.put("end", new Date());
			jsonObject.put("error", null);
		} catch (Exception e) {
			jsonObject.put("IsSuccess", false);
			jsonObject.put("Msg", e.toString());
			e.printStackTrace();
		} finally {
			writeJson(jsonObject.toString());
		}

		return null;
	}

}
