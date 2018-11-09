package com.whty.assis.api.service;

import com.whty.assis.api.model.UserBackgroundPic;

import java.util.List;

public interface UserBackgroundPicService {

	void insert(UserBackgroundPic userBackgroundPic);

	UserBackgroundPic selectByPrimaryKey(String resourceId);

	List<UserBackgroundPic> selectByUserBackgroundCode(String userBackgroundCode);

	void delete(String resourceId);
}
