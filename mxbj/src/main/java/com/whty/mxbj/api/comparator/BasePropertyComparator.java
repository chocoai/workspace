package com.whty.mxbj.api.comparator;

import java.util.Comparator;

import com.whty.mxbj.api.model.BaseProperty;

/**
 * BaseProperty按照update_time降序排列
 */

public class BasePropertyComparator implements Comparator<BaseProperty> {

	@Override
	public int compare(BaseProperty baseProperty1, BaseProperty baseProperty2) {
		long t1 = baseProperty1.getUpdate_time().getTime();
		long t2 = baseProperty2.getUpdate_time().getTime();
		if (t1 < t2)
			return 1;
		else if (t1 > t2)
			return -1;
		else
			return 0;
	}

}
