package com.whty.assis.api.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.api.model.EbpUserClass;
import com.whty.assis.api.model.HdktLoginLog;
import com.whty.assis.api.service.UserClassService;
import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.demo.dao.TaUserDao;
import com.whty.assis.demo.model.Ta_user;
import com.whty.assis.demo.model.WidgetLog;
import com.whty.assis.demo.service.SoftService;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.excel.PoiExcelUtils;
import com.whty.common.mongodb.MongoDBBaseDao;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/test")
public class TestController extends BaseController {

	@Autowired
	UserClassService userClassService;

	@Autowired
	TaUserDao taUserDao;

	@Autowired
	SoftService softService;

	@Autowired
	BasePropertyService basePropertyService;

	@Resource(name = "mongoDBBaseDao")
	MongoDBBaseDao mongoDBBaseDao;

	@RequestMapping(value = "/test5", method = RequestMethod.GET)
	@ResponseBody
	public void test5(HttpServletRequest req, HttpServletResponse resp) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				Query query = new Query();
				int totalCount = (int) mongoDBBaseDao.getMongoTemplate().count(query, EbpUserClass.class);
				System.err.println(totalCount);
				int pageIndex = 1;
				int pageSize = 5;
				int totalPageNo = 0;// 当且页面

				totalPageNo = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

				List<EbpUserClass> saveList = new ArrayList<EbpUserClass>();
				// for (int i = 0; i < totalPageNo; i++) {

				Pageable pageable = new PageRequest(pageIndex, pageSize);
				logger.info("---------" + pageIndex);
				// List<EbpUserClass> list =
				// mongoDBBaseDao.getMongoTemplate().find(query.with(pageable),
				// EbpUserClass.class);
				// List<EbpUserClass> list =
				// mongoDBBaseDao.getMongoTemplate().find(EbpUserClass.class);

				List<EbpUserClass> list = mongoDBBaseDao.getMongoTemplate().find(query, EbpUserClass.class);
				System.out.println(list.size());

				for (EbpUserClass bean : list) {

					String aamUrl = basePropertyService.getPropertyValue("platform_url", bean.getPlatformCode());

					if (aamUrl != null) {
						aamUrl = aamUrl + "user/" + bean.getUserId();

						try {
							String result = HttpUtils.getInstance().httpGet(aamUrl);

							if (StringUtils.isEmpty(result))
								continue;

							JSONObject resultJson = JSONObject.fromObject(result);

							if (!resultJson.optString("result").equals("000000"))
								continue;

							JSONObject userJson = resultJson.optJSONObject("userinfo");

							// 根据用户平台编码去找
							String platformCode = userJson.optString("platformCode");
							Query query2 = new Query(
									Criteria.where("userId").is(bean.getUserId()).and("platformCode").is(platformCode));
							List<EbpUserClass> userPlatformList = mongoDBBaseDao.getMongoTemplate().find(query2,
									EbpUserClass.class);

							if (userPlatformList == null || userPlatformList.size() == 0) {
								bean.setPlatformCode(platformCode);
								saveList.add(bean);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					// TODO
					// if (saveList.size() > 10) {
					// break;
					// }

				}
				// TODO
				// if (saveList.size() > 10) {
				// break;
				// }
				pageIndex = pageIndex + pageSize;
				// }

				for (EbpUserClass bean : saveList) {
					mongoDBBaseDao.getMongoTemplate().save(bean);
				}
				logger.info("数据同步完成!");
			}

		}).start();

	}

	@RequestMapping(value = "/test4", method = RequestMethod.GET)
	@ResponseBody
	public void test4(HttpServletRequest req, HttpServletResponse resp) {

		Ta_user taUser = new Ta_user();

		// taUser.setUser_id("94094");
		// taUser.setLast_time(TimeUtil.string2Date("2018-03-27 13:32:47",
		// TimeUtil.TIME_FORMAT));
		//
		// String startTime = TimeUtil.date2String(taUser.getLast_time(),
		// TimeUtil.TIME_FORMAT);
		// String endTime = "2018-03-27 16:47:47";
		//
		// Query query = new Query(
		// Criteria.where("userId").is(taUser.getUser_id()).and("createTime").gte(startTime).lte(endTime));
		//
		// List<HdktLoginLog> list =
		// mongoDBBaseDao.getMongoTemplate().find(query, HdktLoginLog.class);
		//
		// Query query2 = new
		// Query(Criteria.where("userId").is(taUser.getUser_id()));
		//
		// List<HdktLoginLog> list2 =
		// mongoDBBaseDao.getMongoTemplate().find(query2, HdktLoginLog.class);
		// List<HdktLoginLog> list2441 =
		// mongoDBBaseDao.getMongoTemplate().find(new Query(),
		// HdktLoginLog.class);
		// List<HdktLoginLog> list23 =
		// mongoDBBaseDao.getMongoTemplate().find(query2, HdktLoginLog.class,
		// "hdktLoginLog");
		// Query query3 = new Query();
		//
		// List<EbpUserClass> list3 =
		// mongoDBBaseDao.getMongoTemplate().find(query3, EbpUserClass.class);
		//
		// List<EbpUserClass> list4 =
		// mongoDBBaseDao.getMongoTemplate().find(query3, EbpUserClass.class,
		// "ebpUserClass");
		//
		// Map<String, Object> param = new HashMap<String, Object>(10);
		//
		// param.put("startTime", startTime);
		// // Date newData = new Date();
		// param.put("endTime", endTime);
		// param.put("userId", taUser.getUser_id());
		//
		// List<WidgetLog> widgetLogList = taUserDao.getWidgetLog3(param);
		//
		// if (list.isEmpty() || widgetLogList.isEmpty()) {
		// // TODO 不加积分
		// System.out.println("不加分");
		// } else {
		// System.out.println("加分");
		// // TODO 加积分
		// }

	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public void test(HttpServletRequest req, HttpServletResponse resp) {

		List<Ta_user> sss = taUserDao.getUserList2(new HashMap<String, Object>());

		for (Ta_user user : sss) {

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("usercode", user.getUser_id());
			param.put("typecode", "JF000360");
			param.put("comeFrom", "1");
			param.put("relatedId", "3");

			String result2;
			try {
				result2 = HttpUtils.getInstance().httpPost("http://yun.zjer.cn:20041/sme-gateway/addpoint",
						JSONObject.fromObject(param).toString());
				System.out.println(result2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	@ResponseBody
	public void student(HttpServletRequest req, HttpServletResponse resp) {
		userClassService.studnet2();
	}

	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	@ResponseBody
	public void test3(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<Map<String, Object>> aab = taUserDao.qqbb(new HashMap<String, Object>());

		String[] headers = { "用户@user_id", "时长@use_taking", "姓名@real_name", "最后登录时间@last_time", "登录次数@login_count",
				"学校@org_name" };
		HSSFWorkbook aa = PoiExcelUtils.createExcel2Export("bb", "统计", headers, aab);

		// File file = new File("d:\\a.xls");

		FileOutputStream os = new FileOutputStream("d:\\workbook.xls");

		aa.write(os);
		os.close();
		// aa.write(stream);
		// for(Map<String,Object> map:aab){

		//
		//
		// }

		System.out.println(aab.size());

	}

	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	@ResponseBody
	public void test2(HttpServletRequest req, HttpServletResponse resp) {
		String startTime = "2016-01-01";
		String endTime = "2016-12-31";
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("startTime", startTime);
		param.put("endTime", endTime);
		List<WidgetLog> list = taUserDao.getWidgetLog2(param);

		for (WidgetLog bean : list) {
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("id", bean.getId());
			mapParam.put("userId", bean.getUserId());
			mapParam.put("platformCode", bean.getPlatformCode());
			mapParam.put("classId", bean.getClassId());
			mapParam.put("cpuInfo", bean.getCpuInfo());
			mapParam.put("memory", bean.getMemory());
			mapParam.put("operationSystemsType", bean.getOperationSystemType());
			mapParam.put("isTransfer", bean.getIsTransfer());
			mapParam.put("terminalVersion", bean.getTerminalVersion());
			mapParam.put("classType", bean.getClassType());
			mapParam.put("loginSource", bean.getLoginSource());
			mapParam.put("loginTaking", bean.getLoginTaking());
			mapParam.put("courseNum", bean.getCourseNum());
			mapParam.put("useTaking", bean.getUseTaking());
			mapParam.put("useCount", bean.getUseCount());
			mapParam.put("widgetId", bean.getWidgetId());
			mapParam.put("machineInfo", bean.getMachineInfo());
			mapParam.put("operationSystemInfo", bean.getOperationSystemInfo());

			taUserDao.saveWidgetInfoLogBack(mapParam);
		}

	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public void test(HttpServletRequest req, HttpServletResponse resp, @RequestBody String body) throws IOException {
		String ip = req.getHeader("X-Forwarded-For");

		// logger.info(ip);
	}
}
