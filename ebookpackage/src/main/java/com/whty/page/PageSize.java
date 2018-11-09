package com.whty.page;

public enum PageSize {
	ten(10), twenty(20), thirty(30);

	private int size;

	private PageSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

}
