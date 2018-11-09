package com.smart.util;

import java.util.Date;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * spring mvc 日期格式转换
 * 
 * @author 充满智慧的威哥
 */
public class DateConverter implements WebBindingInitializer {

	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

}
