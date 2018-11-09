/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.SchoolUserDao;
import com.whty.assis.basicdata.service.SchoolUserService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date   2018年5月21日
 */
@Service
public class SchoolUserServiceImpl implements SchoolUserService{

	@Autowired
	private SchoolUserDao schoolUserDao;
	
	/* (non-Javadoc)
	 * @see com.whty.assis.basicdata.service.SchoolUserService#queryStudentPage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult querySchoolUserPage(Map<String, Object> paramMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(schoolUserDao.listByCondition(paramMap));	
		rs.setPage(page);
		return rs;
	
	}



}
