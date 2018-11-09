package com.whty.wfd.page.dao;

import com.whty.wfd.page.vo.Count;
import com.whty.wfd.page.vo.Like;

import java.util.List;

/**
 * \* User: zjd \* Date: 2018/8/17 \* Time: 16:01 \* Description: \
 */
public interface CountMapper {

	// 全校老师回复问题数
	List<Count> getTeacherReceiveCount(Integer schoolId);

	// 全校老师被点赞数
	List<Count> getTeacherLikeCount(Integer schoolId);

	// 全校学生提问数
	List<Count> getStudentPost(Integer schoolId);

	// 热门问题数量
	List<Count> getHotQeustion(Integer schoolId);
}