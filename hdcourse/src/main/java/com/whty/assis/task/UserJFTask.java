package com.whty.assis.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.annotation.Resource;

import com.whty.common.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.whty.assis.demo.dao.TaUserDao;
import com.whty.assis.demo.model.Ta_user;
import com.whty.assis.demo.model.WidgetLog;
import com.whty.common.mongodb.MongoDBBaseDao;

public class UserJFTask extends TimerTask {
	private Ta_user taUser;

	@Autowired
	private TaUserDao taUserDao;

	@Resource(name = "mongoDBBaseDao")
	MongoDBBaseDao mongoDBBaseDao;

	public UserJFTask(Ta_user taUser) {
		this.taUser = taUser;
	}

	@Override
	public void run() {
		Query query = new Query();

		query.addCriteria(Criteria.where("userId").is(taUser.getUser_id()));
		query.addCriteria(Criteria.where("createTime").is(taUser.getUser_id()));

		// List<HdktLoginLog> list =
		// mongoDBBaseDao.getMongoTemplate().find(query, HdktLoginLog.class);

		Map<String, Object> param = new HashMap<String, Object>(10);

		param.put("startTime", TimeUtil.date2String(taUser.getLast_time(), TimeUtil.TIME_FORMAT));
		Date newData = new Date();
		param.put("endTime", TimeUtil.date2String(newData, TimeUtil.TIME_FORMAT));

		List<WidgetLog> widgetLogList = taUserDao.getWidgetLog(param);

		if (!widgetLogList.isEmpty()) {
			// TODO 不加积分
		} else {
			// TODO 加积分
		}

	}

}
