package com.whty.assis.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.demo.dao.AreaDao;
import com.whty.assis.demo.dao.LoginHistoryDao;
import com.whty.assis.demo.dao.TaUserDao;
import com.whty.assis.demo.dao.TerminalUseHistoryDao;
import com.whty.assis.demo.dao.WidgetHistoryDao;
import com.whty.assis.demo.service.UseTakingService;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.mongodb.MongoDBBaseDao;

@Service
public class UseTakingServiceImpl implements UseTakingService {
	@Resource(name = "mongoDBBaseDao")
	MongoDBBaseDao mongoDBBaseDao;
	@Autowired
	private LoginHistoryDao loginHistoryDao;

	@Autowired
	private TaUserDao taUserDao;

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private WidgetHistoryDao widgetHistoryDao;

	@Autowired
	private TerminalUseHistoryDao terminalUseHistoryDao;

	@Autowired
	private BasePropertyService basePropertyService;

}
