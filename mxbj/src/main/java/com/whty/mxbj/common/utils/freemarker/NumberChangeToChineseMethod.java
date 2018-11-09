package com.whty.mxbj.common.utils.freemarker;

import java.util.List;

import com.whty.mxbj.common.utils.NumberChangeToChinese;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class NumberChangeToChineseMethod implements TemplateMethodModelEx {

	@Override
	public Object exec(List args) throws TemplateModelException {
		if (args.size() != 1) {
			throw new TemplateModelException("Wrong arguments");
		}
		return new NumberChangeToChinese().numberToChinese(Integer.valueOf(String.valueOf(args.get(0))));
	}

}
