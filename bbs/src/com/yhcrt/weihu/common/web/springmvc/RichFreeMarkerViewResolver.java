package com.yhcrt.weihu.common.web.springmvc;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import com.yhcrt.weihu.common.web.springmvc.RichFreeMarkerView;

/**
 * ViewResolver for RichFreeMarkerView
 * 
 * Override buildView, if viewName start with / , then ignore prefix.
 * 
 * @author tom
 * 
 */
public class RichFreeMarkerViewResolver extends AbstractTemplateViewResolver {
	/**
	 * Set default viewClass
	 */
	public RichFreeMarkerViewResolver() {
		setViewClass(RichFreeMarkerView.class);
	}

	/**
	 * if viewName start with / , then ignore prefix.
	 */
	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		AbstractUrlBasedView view = super.buildView(viewName);
		// start with / ignore prefix
		if (viewName.startsWith("/")) {
			view.setUrl(viewName + getSuffix());
		}
		return view;
	}
}