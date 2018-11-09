package com.whty.mxbj.api.dao;

import java.util.Map;

import com.whty.mxbj.api.model.Pen;
import com.whty.mxbj.base.dao.IBaseDao;

public interface PenDao extends IBaseDao<Pen> {

	public void saveUserPenMap(Map<String, Object> param);

	public void deletePenById(String penId);

	public void deleteUserPenMap(Map<String, Object> param);

	public void deletePen(Map<String, Object> param);

	public Pen getPenByUserAccountAndPlatform(Map<String, Object> param);
}
