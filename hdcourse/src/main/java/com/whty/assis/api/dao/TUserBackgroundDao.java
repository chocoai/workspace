package com.whty.assis.api.dao;

import com.whty.assis.api.model.TUserBackground;

public interface TUserBackgroundDao {

	TUserBackground getListByTUserBackground(TUserBackground entity);

	void insert(TUserBackground entity);

	void delete(String backgroundCode);
}