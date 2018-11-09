package com.whty.wfd.common.utils.freemarker;

import java.math.BigDecimal;
import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class FormatMacMethod implements TemplateMethodModelEx {

	@Override
	public Object exec(List args) throws TemplateModelException {
		if (args.size() != 1) {
			throw new TemplateModelException("Wrong arguments");
		}

		String ss = String.valueOf(args.get(0));

		String[] s = ss.split("\\|");

		StringBuffer result = new StringBuffer();
		for (int i = 0; i < s.length; i++) {
			String q = s[i];

			StringBuilder sb = new StringBuilder(s[i]);
			sb.insert(2, ":");
			sb.insert(5, ":");
			sb.insert(8, ":");
			sb.insert(11, ":");
			sb.insert(14, ":");
			result.append(sb).append(" ");
		}
		return result.toString();
	}

	public static void main(String[] args) {
		String ss = "507B9D2392BC|4C3488A2AC42|4E3488A2AC43|4E3488A2AC42";

	}

}
