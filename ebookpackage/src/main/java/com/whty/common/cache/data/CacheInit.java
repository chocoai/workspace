/**
 * 
 */
package com.whty.common.cache.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.common.cache.tools.CacheContainer;
import com.whty.ebp.manage.model.BaseProperty;
import com.whty.ebp.manage.service.BasePropertyService;

/**
 * @author zhujg
 *
 */
@Service
public class CacheInit {
	public static final String AAM_URL_KEY = "aam_url";

	private Log logger = LogFactory.getLog(getClass());

	@Autowired
	private BasePropertyService basePropertyService;

	public void init() {
		logger.info("-----------------------清除以前的数据缓存...---------------------");
//		 CacheContainer.getCaches().clear();
		logger.info("-----------------------开始加载基础数据缓存...---------------------");

		// addBasePropertyCache();// 缓存配置文件表BASE_PROPERTY数据

		logger.info("---------------------Finish 加载基础数据缓存-------------------------");
//		ThreadWidgetLog.startSaveDBThread();
	}

	/**
	 * 加载参数配置表缓存数据
	 */
	@SuppressWarnings("rawtypes")
	public void addBasePropertyCache() {
		// 查询数据库里的数据
		List<BaseProperty> list = basePropertyService.queryBasePropertyList(new HashMap());

		try {
			List<BaseProperty> apiDomainList = new ArrayList<BaseProperty>();
			for (BaseProperty bp : list) {
				CacheDataUtils.setData("BaseProperty", bp.getProperty_key() + "_" + bp.getPlatform_code(),
						bp.getProperty_value());
				CacheDataUtils.setObjectData("BaseProperty",
						bp.getProperty_key() + "_" + bp.getPlatform_code() + "_entity", bp);
				if (null != bp.getProperty_type_name() && bp.getProperty_type_name().trim().equals("api_domain")) {
					apiDomainList.add(bp);
				}
			}
			CacheDataUtils.setObjectData("BaseProperty", "api_domain", apiDomainList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

}
