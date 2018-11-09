package com.whty.wfd.page.service.impl;

import com.whty.wfd.page.dao.CountMapper;
import com.whty.wfd.page.service.CountService;
import com.whty.wfd.page.vo.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* User: zjd \* Date: 2018/8/27 \* Time: 14:17 \* Description: \
 */
@Service
public class CountServiceImpl implements CountService {

	@Autowired
	private CountMapper countMapper;

	@Override
	public List<Count> getTeacherReceiveCount(Integer schoolId) {
		return countMapper.getTeacherReceiveCount(schoolId);
	}

	@Override
	public List<Count> getTeacherLikeCount(Integer schoolId) {
		return countMapper.getTeacherLikeCount(schoolId);
	}

	@Override
	public List<Count> getStudentPost(Integer schoolId) {
		return countMapper.getStudentPost(schoolId);
	}

	@Override
	public List<Count> getHotQeustion(Integer schoolId) {
		return countMapper.getHotQeustion(schoolId);
	}
}