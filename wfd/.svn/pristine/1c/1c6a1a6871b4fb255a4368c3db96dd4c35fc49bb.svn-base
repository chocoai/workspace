package com.whty.wfd.common.utils.freemarker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class AppNameMethod implements TemplateMethodModelEx {
	@Override
	public Object exec(List args) throws TemplateModelException {
		if (args.size() != 1) {
			throw new TemplateModelException("Wrong arguments");
		}

		String appName = String.valueOf(args.get(0));

		String[] appNames = appName.split(",");

		Set<String> aa = new HashSet<String>();
		for (int i = 0; i < appNames.length; i++) {
			if (aa.size() >= 5) {
				break;
			}
			aa.add(appNames[i]);
		}

		return StringUtils.join(aa, ",");

	}
}
