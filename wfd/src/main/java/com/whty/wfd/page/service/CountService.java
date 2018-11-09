package com.whty.wfd.page.service;

import com.whty.wfd.page.vo.Count;

import java.util.List;

public interface CountService {

	// 全校老师回复问题数
	List<Count> getTeacherReceiveCount(Integer schoolId);

	// 全校老师被点赞数
	List<Count> getTeacherLikeCount(Integer schoolId);

	// 全校学生提问数
	List<Count> getStudentPost(Integer schoolId);

	// 热门问题数量
	List<Count> getHotQeustion(Integer schoolId);
}
