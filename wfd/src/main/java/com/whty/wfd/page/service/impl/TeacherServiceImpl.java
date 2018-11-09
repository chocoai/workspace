/**
 * 
 */
package com.whty.wfd.page.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.wfd.page.dao.TPlatePostImgMapper;
import com.whty.wfd.page.dao.TPlatePostMapper;
import com.whty.wfd.page.model.TPlatePost;
import com.whty.wfd.page.model.TPlatePostExample;
import com.whty.wfd.page.service.TeacherService;

/**
 * @author zhangzheng
 * @date 2018年8月16日
 */
@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TPlatePostMapper tPlatePostMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.wfd.page.service.TeacherService#getTopPlatePost(java.util.Map)
	 */
	@Override
	public List<TPlatePost> getTopPlatePost(Map<String, Object> param) {
		TPlatePostExample bean = new TPlatePostExample();
		bean.createCriteria().andCreatorEqualTo(1).andIsTopEqualTo(true).andIsDeleteEqualTo(false)
				.andPlateIdEqualTo(param.get("plateId").toString());

		List<TPlatePost> topPlatePostList = tPlatePostMapper.selectByExampleWithBLOBs(bean);

		return topPlatePostList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.wfd.page.service.TeacherService#getPlatePost(java.util.Map)
	 */
	@Override
	public List<TPlatePost> getPlatePost(Map<String, Object> param) {
		List<TPlatePost> topPlatePostList = tPlatePostMapper.selectPlatePost(param);
		return topPlatePostList;
	}

}
