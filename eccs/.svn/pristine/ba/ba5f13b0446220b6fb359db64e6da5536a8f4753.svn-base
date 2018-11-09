package com.smart.util;

import org.springframework.util.StringUtils;
import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * spring mvc 日期格式转换
 * 
 * @author 充满智慧的威哥
 */
public class DateEditor extends PropertyEditorSupport {

	private static final DateFormat DATEFORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static final DateFormat TIMEFORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private DateFormat dateFormat;

	private boolean allowEmpty = true;

	public DateEditor() {
	}

	/**
	 * Parse the Date from the given text, using the specified DateFormat.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			setValue(null);
			return;
		}

		try {
			int length = text.trim().length();
			if (length == 10) {
				setValue(DATEFORMAT.parse(text));
			} else if (length == 19) {
				setValue(TIMEFORMAT.parse(text));
			}
		} catch (ParseException ex) {
			throw new IllegalArgumentException(
					"Could not parse date: " + ex.getMessage(), ex);
		}

	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		DateFormat dateFormat = this.dateFormat;
		if (dateFormat == null) {
			dateFormat = TIMEFORMAT;
		}
		return (value != null ? dateFormat.format(value) : "");
	}
}
