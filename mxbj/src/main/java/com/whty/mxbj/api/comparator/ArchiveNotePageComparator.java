package com.whty.mxbj.api.comparator;

import java.util.Comparator;

import com.whty.mxbj.api.model.ArchiveNotePage;

/**
 * 实现ArchiveNotePage按照pageId升序
 */
public class ArchiveNotePageComparator implements Comparator<ArchiveNotePage> {

	@Override
	public int compare(ArchiveNotePage page1, ArchiveNotePage page2) {
		Integer i1 = page1.getPageId();
		Integer i2 = page2.getPageId();
		if (i1 > i2)
			return 1;
		else if (i1 < i2)
			return -1;
		else
			return 0;
	}

}
