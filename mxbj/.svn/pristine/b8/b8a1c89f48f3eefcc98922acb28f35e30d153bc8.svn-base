package com.whty.mxbj.api.thread;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.mxbj.api.service.NoteService;

public class ShareToCloudThread implements Runnable {
	private final NoteService noteService = (NoteService) SpringContextUtil.getBean("noteService");
	// @Autowired
	// private NoteService noteService;

	private static Logger log = LoggerFactory.getLogger(ShareToCloudThread.class);

	private Map<String, Object> param;

	public ShareToCloudThread(Map<String, Object> param) {
		this.param = param;
	}

	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
		log.info("-----------开始执行同教育云任务-------------" + this.param.toString() + "---------");
		noteService.shareToCloud(this.param);

		long endTime = System.currentTimeMillis();
		log.info("执行同教育云任务完成! 耗时：" + (endTime - beginTime) + "ms");

	}
}
