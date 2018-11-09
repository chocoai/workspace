package com.whty.page;


public class PageContext extends Page {
	private static final long serialVersionUID = 1L;
	
	private static ThreadLocal<PageContext> context = new ThreadLocal<PageContext>();

	public static PageContext getContext() {
		PageContext ci = context.get();
		if (ci == null) {
			ci = new PageContext();
			context.set(ci);
		}
		return ci;
	}

	public static void removeContext() {
		context.remove();
	}

	protected void initialize() {

	}

}
