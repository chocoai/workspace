package com.whty.mxbj.api.dao;

import java.util.List;
import java.util.Map;

import com.whty.mxbj.api.model.BaseNote;
import com.whty.mxbj.api.model.Note;
import com.whty.mxbj.base.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

public interface NoteDao extends IBaseDao<Note> {
	
	Note loadNote(Map<String, Object> noteParam);
	
	public void deleteNote(Note note);

	List<BaseNote> findAllNote(Map<String, Object> paramMap);

	List<Note> queryByUserId(@Param("userId") String userId, @Param("loginPlatformCode") String loginPlatformCode);
}
