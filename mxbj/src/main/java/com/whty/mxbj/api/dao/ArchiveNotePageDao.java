package com.whty.mxbj.api.dao;

import com.whty.mxbj.api.model.ArchiveNotePage;
import com.whty.mxbj.base.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArchiveNotePageDao extends IBaseDao<ArchiveNotePage> {

	void deleteByArchiveNoteId(String archiveNoteId);

	void updateUrl(ArchiveNotePage archiveNotePage);

	List<ArchiveNotePage> queryByArchiveNoteId(@Param("userId") String userId,@Param("loginPlatformCode") String loginPlatformCode,@Param("archiveNoteId") String archiveNoteId);

}
