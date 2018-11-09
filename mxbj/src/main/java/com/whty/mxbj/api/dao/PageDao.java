package com.whty.mxbj.api.dao;

import java.util.List;
import java.util.Map;

import com.whty.mxbj.api.model.Page;
import com.whty.mxbj.base.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

public interface PageDao extends IBaseDao<Page>{

	Page getPage(Map<String, Object> pageMap);

	
	void deletePage(Map<String, Object> pageMap);

	List<Page> queryByUserId(@Param("userId") String userId,@Param("loginPlatformCode") String loginPlatformCode,@Param("noteId") String noteId);
}
