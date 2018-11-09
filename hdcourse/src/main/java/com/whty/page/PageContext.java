package com.whty.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageContext extends Page {
	/**
	 * 
	 */

	// private static final Logger logger = Logger.getLogger(PageContext.class);

	private static final Logger logger = LoggerFactory.getLogger(PageContext.class);

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
