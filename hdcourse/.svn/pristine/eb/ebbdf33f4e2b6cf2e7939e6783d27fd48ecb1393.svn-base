package com.whty.assis.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.assis.api.service.DataMoveService;

@Controller
@RequestMapping("/api/dataMove")
public class DataMoveController {

	@Autowired
	private DataMoveService dataMoveService;

	/**
	 * 数据迁移
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	private void test() throws Exception {
		dataMoveService.test();
	}

	/**
	 * 数据迁移
	 */
	@RequestMapping(value = "/hdktEvent", method = RequestMethod.POST)
	private void hdkt() throws Exception {

		new Thread(new Runnable() {
			@Override
			public void run() {
				/**
				 * 互动课堂事件表
				 */
				dataMoveService.saveHdktEventCount();

				/**
				 * 互动课堂登录表
				 */
				dataMoveService.saveHdktLoginLog();

				/**
				 * 互动课堂页面表
				 */
				// dataMoveService.saveHdktPageCount();

				/**
				 * 互动课堂终端统计表
				 */
				// dataMoveService.saveHdktTerminalLinkCount();
			}
		}).start();
	}

}
