package com.whty.assis.manage.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {
	
	public static String getExceptionStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String msg = sw.toString();
		return msg;
	}
	
}
