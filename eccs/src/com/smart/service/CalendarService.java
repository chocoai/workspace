/**
 * 
 */
package com.smart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.CalendarDao;
import com.smart.model.Calendar;

/**
 * @Description:
 * @author raopanfeng
 * @date 2016-3-20 上午10:24:15
 */

@Service
public class CalendarService {

	@Autowired
	private CalendarDao calendarDao;

	public Calendar get(String id) {
		return calendarDao.get(id);
	}

	public void save(Calendar entity) {
		calendarDao.save(entity);
	}

	public void update(Calendar entity) {
		calendarDao.update(entity);
	}

	public void delete(String id) {
		calendarDao.delete(id);
	}

	public List<Calendar> getAll() {
		return calendarDao.getAll();
	}

	/**
	 * 根据用户ID获取用户的日程安排
	 * 
	 * @param userId
	 * @return
	 */
	public List<Calendar> getCalendarByUserId(int userId) {
		StringBuilder hql = new StringBuilder(
				"from Calendar o where o.user.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(userId);
		List<Calendar> list = calendarDao.getList(hql.toString(), paramList);
		return list;
	}

}
