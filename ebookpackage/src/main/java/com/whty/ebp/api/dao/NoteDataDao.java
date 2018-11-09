package com.whty.ebp.api.dao;

import com.whty.ebp.api.model.NoteData;
import com.whty.ebp.api.model.OutSideNoteData;
import com.whty.ebp.base.dao.IBaseDao;

public interface NoteDataDao extends IBaseDao<NoteData>{

	void saveOutSideNoteData(OutSideNoteData bean);

}
