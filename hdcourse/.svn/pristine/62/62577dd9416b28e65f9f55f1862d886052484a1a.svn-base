package com.whty.assis.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.whty.assis.manage.model.BaseProperty;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.cache.data.CacheDataUtils;
import com.whty.common.cache.tools.CacheContainer;
import com.whty.common.util.CommonFunction;

public class ClearCacheJob {
	private static final Log log = LogFactory.getLog(ClearCacheJob.class);

	@Autowired
	private BasePropertyService basePropertyService;

	public void execute() {
		log.info("-------------- 开始执行  execute  清楚memcache缓存------------------------");
		runner();
		log.info("-------------- 执行  execute 清楚memcache缓存 ------------------------");
	}

	/**
	 * 清楚memcache缓存
	 */
	private void runner() {
		CacheContainer.clear("BaseProperty");
		// 加载参数配置缓存
		addBasePropertyCache();

		log.info("清楚memcache缓存完成======>成功");
	}

	/**
	 * 加载参数配置表缓存数据
	 */
	public void addBasePropertyCache() {
		log.info("加载参数配置表缓存数据");
		// 查询数据库里的数据
		@SuppressWarnings("rawtypes")
		List<BaseProperty> list = basePropertyService.queryBasePropertyList(new HashMap());
		try {
			List<BaseProperty> apiDomainList = new ArrayList<BaseProperty>();
			Date updateDate = null;
			for (BaseProperty bp : list) {
				CacheDataUtils.setData("BaseProperty", bp.getProperty_key() + "_" + bp.getPlatform_code(),
						bp.getProperty_value());
				CacheDataUtils.setObjectData("BaseProperty",
						bp.getProperty_key() + "_" + bp.getPlatform_code() + "_entity", bp);
				if (null != bp.getProperty_type_name() && bp.getProperty_type_name().trim().equals("api_domain")) {
					apiDomainList.add(bp);
					if (null == updateDate || updateDate.before(bp.getUpdate_time())) {
						updateDate = bp.getUpdate_time();
					}
				}
			}
			if (null != updateDate)
				CacheDataUtils.setData("BaseProperty", "api_domain_update_time",
						CommonFunction.getTimeSampleString(updateDate));
			CacheDataUtils.setObjectData("BaseProperty", "api_domain", apiDomainList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
