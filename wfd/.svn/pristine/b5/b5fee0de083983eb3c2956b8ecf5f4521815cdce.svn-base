package com.whty.wfd.common.utils.freemarker;

import java.math.BigDecimal;
import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class FormatDuring implements TemplateMethodModelEx {

	@Override
	public Object exec(List args) throws TemplateModelException {
		if (args.size() != 1) {
			throw new TemplateModelException("Wrong arguments");
		}

		Long mss = Long.valueOf(String.valueOf(args.get(0)));
		// long days = mss / (1000 * 60 * 60 * 24);
		// long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		// long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		// long seconds = (mss % (1000 * 60)) / 1000;
		BigDecimal b1 = new BigDecimal(mss + "");
		BigDecimal[] s1 = b1.divideAndRemainder(BigDecimal.valueOf(60 * 60));
		String hours = s1[0].toString();
		BigDecimal[] s2 = s1[1].divideAndRemainder(BigDecimal.valueOf(60));
		String minutes = s2[0].toString();
		String seconds = s2[1].toString();

		StringBuffer sb = new StringBuffer();

		if (!hours.equals("0")) {
			sb.append(hours).append(" 小时");
		}
		if (!minutes.equals("0")) {
			sb.append(minutes).append(" 分钟");
		}
		if (!seconds.equals("0")) {
			sb.append(seconds).append(" 秒");
		}

		return sb.toString();
	}

}
