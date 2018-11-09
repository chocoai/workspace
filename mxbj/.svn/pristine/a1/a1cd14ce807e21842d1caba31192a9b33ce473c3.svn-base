package com.whty.mxbj.api.comparator;

import java.util.Comparator;

import com.whty.mxbj.api.model.Note;

/**
 * 实现Note对象按照createTime降序排列
 *
 */
public class NoteComparator implements Comparator<Note> {
	public int compare(Note note1, Note note2) {
		long t1 = note1.getCreateTime().getTime();
		long t2 = note2.getCreateTime().getTime();

		if (t1 < t2)
			return 1;
		else if (t1 > t2)
			return -1;
		else
			return 0;
	}

}
