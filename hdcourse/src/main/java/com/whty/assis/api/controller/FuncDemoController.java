package com.whty.assis.api.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.api.dao.WidgetLogDao;
import com.whty.assis.api.model.HdktEventCount;
import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.excel.FileVo;
import com.whty.common.excel.PoiUtil;
import com.whty.common.util.SysConfigUtils;
import com.whty.common.util.TimeUtils;

import net.sf.json.JSONObject;

/*
 * 功能模块基础表
 */
@Controller
@RequestMapping("/api/funcDemo")
public class FuncDemoController extends BaseController {
	@Autowired
	private WidgetLogDao widgetLogDao;
	@Autowired
	private MongoTemplate mongoTemplate;

	/*
	 * 功能模块基础表
	 */
	@RequestMapping(value = "/demo")
	public void demo() {
		// Calendar cal1 = Calendar.getInstance(); // 当前日期
		// cal1.add(Calendar.MONTH, 0);
		// cal1.set(Calendar.DAY_OF_MONTH, 1); // 上月第一天
		// Calendar cal2 = Calendar.getInstance();
		// cal2.add(Calendar.MONTH, 1);
		// cal2.set(Calendar.DAY_OF_MONTH, 1); // 当月第一天

		// Calendar cal1 = Calendar.getInstance();
		// cal1.add(Calendar.DAY_OF_MONTH, -2);// 昨天
		// Calendar cal2 = Calendar.getInstance();
		// cal2.add(Calendar.DAY_OF_MONTH, -1);// 今日

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String startTime = sdf.format(cal1.getTime());
		// String endTime = sdf.format(cal2.getTime());

		// Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);
		// String startTime = TimeUtils.date2String(cal1.getTime(), "yyyy-MM-dd
		// HH") + ":00:00";
		// String endTime = TimeUtils.date2String(cal2.getTime(), "yyyy-MM-dd
		// HH") + ":59:59";

		// String startTime = "2018-06-27 19:30:00";
		// String endTime = "2018-06-27 20:00:00";

		// HdktEventCount aaa = new HdktEventCount();
		// aaa.setAccount("aaa");
		// mongoTemplate.insert(aaa);

		// Query query1 = new
		// Query(Criteria.where("account").is("wangsiwenty1"));
		// List<HdktEventCount> user1 = mongoTemplate.find(query1 ,
		// HdktEventCount.class);
		//
		// System.out.println(user1.size());

		// Query query = new
		// Query(Criteria.where("createTime").gte(startTime).lte(endTime));
		// long mm = mongoTemplate.count(query, HdktEventCount.class);
		// System.out.println(mm);
		//
		// List<HdktEventCount> user = mongoTemplate.find(query,
		// HdktEventCount.class);

		// for (HdktEventCount bean : user) {
		// String account = bean.getAccount();
		// String ss =
		// "http://116.211.105.38:13022/aamty/allAccount/queryAccountPlatformInfo";
		// // String ss =
		// //
		// "http://116.211.105.45:13022/aamty/allAccount/queryAccountPlatformInfo";
		// Map<String, Object> ssmap = new HashMap<String, Object>();
		// ssmap.put("account", account);
		// String result2;
		// try {
		// result2 = HttpUtils.getInstance().httpPost(ss,
		// JSONObject.fromObject(ssmap).toString());
		// JSONObject respJson = JSONObject.fromObject(result2);
		// JSONObject userObject = (JSONObject) respJson.get("data");
		// String platformCode = userObject.optString("platformCode");
		// System.out.println(platformCode);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// }

		FileVo file = new FileVo();
		File file1 = new File("E:\\Demo.xls");
		file.setFile(file1);
		file.setFileFileName(file1.getName());
		List<Object> list = PoiUtil.importFile(file, 1);
		for (int i = 0; i < list.size(); i++) {
			String[] str = new String[5];
			str = (String[]) list.get(i);
			String sb;
			for (int j = 0; j < 3; j++) {
				Map<String, Object> param = new HashMap<>();
				sb = j + "" + str[0].substring(7);
				param.put("eventId", sb);
				param.put("eventName", str[1]);
				param.put("subsection", str[2]);
				param.put("eventType", str[3]);
				param.put("description", str[4]);
				widgetLogDao.saveFuncDemo(param);
			}
		}

	}
}
