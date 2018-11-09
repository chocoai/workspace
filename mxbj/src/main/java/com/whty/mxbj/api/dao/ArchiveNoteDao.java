package com.whty.mxbj.api.dao;

import com.whty.mxbj.api.model.ArchiveNote;
import com.whty.mxbj.base.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArchiveNoteDao extends IBaseDao<ArchiveNote> {

	public void updateArchiveTime(ArchiveNote bean);

	List<ArchiveNote> queryByUserId(@Param("userId") String userId,@Param("loginPlatformCode") String loginPlatformCode);
}
