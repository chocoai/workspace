package com.whty.mxbj.base.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SysConfigUtils {

	// @Autowired
	// private Environment env;

	@Value("#{systemProperties['ios_download_url']}") // 系统属性配置
	public String IOS_DOWNLOAD_URL;

}
