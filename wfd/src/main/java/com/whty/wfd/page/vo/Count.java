package com.whty.wfd.page.vo;

/**
 * \* User: zjd \* Date: 2018/8/27 \* Time: 14:18 \* Description: \
 */
public class Count {

	private Integer id;
	private String name;
	/**
	 * 老师回复统计
	 */
	private Integer teacherReceiveCount;
	/**
	 * 老师点赞数
	 */
	private Integer teacherLikeCount;
	/**
	 * 学生提问数
	 */
	private Integer studentPost;
	/**
	 * 热门问题数
	 */
	private Integer hotQeustion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeacherReceiveCount() {
		return teacherReceiveCount;
	}

	public void setTeacherReceiveCount(Integer teacherReceiveCount) {
		this.teacherReceiveCount = teacherReceiveCount;
	}

	public Integer getTeacherLikeCount() {
		return teacherLikeCount;
	}

	public void setTeacherLikeCount(Integer teacherLikeCount) {
		this.teacherLikeCount = teacherLikeCount;
	}

	public Integer getStudentPost() {
		return studentPost;
	}

	public void setStudentPost(Integer studentPost) {
		this.studentPost = studentPost;
	}

	public Integer getHotQeustion() {
		return hotQeustion;
	}

	public void setHotQeustion(Integer hotQeustion) {
		this.hotQeustion = hotQeustion;
	}
}