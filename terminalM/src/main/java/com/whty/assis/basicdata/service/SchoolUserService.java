/**
 * 
 */
package com.whty.assis.basicdata.service;

import java.util.Map;

import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年5月21日
 */
public interface SchoolUserService {

	public HandlerResult querySchoolUserPage(Map<String, Object> paramMap, PageContext page);

}
