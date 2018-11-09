package com.whty.mxbj.api.dao;

import java.util.List;
import java.util.Map;

import com.whty.mxbj.api.model.PenFirmvare;

public interface PenFirmvareDao {

	List<PenFirmvare> getNewVersionByPenModel(Map<String,Object> penModel);

}
