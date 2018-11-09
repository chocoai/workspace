/**
 * 
 */
package com.whty.assis.demo.service;

import java.util.Map;

import com.whty.assis.demo.model.BaiduFile;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年8月8日
 */
public interface BaiduFileService {

	public void save(BaiduFile baiduFile);

	public void update(BaiduFile baiduFile);

	public HandlerResult listByConditionListPage(Map<String, Object> paramMap) throws Exception;

}
