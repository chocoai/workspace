package com.whty.common.util;

import java.math.BigDecimal;

public class BigDecimalUtils {

	/**
	 * 去掉多余的0
	 * 
	 * @param number
	 * @return
	 */
	public static String getPrettyNumber(String number) {
		return BigDecimal.valueOf(Double.parseDouble(number)).stripTrailingZeros().toPlainString();
	}

	public static String calculateRate(BigDecimal b1, BigDecimal b2) {
		if (b2.toString().equals("0"))
			return "0";

		String rate = BigDecimalUtils
				.getPrettyNumber(b1.divide(b2, 2, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)).toString());

		return rate;
	}

}
