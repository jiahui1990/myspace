package com.jve.util;

import java.text.DecimalFormat;

public class StringUtil {
	/**
	 * 把科学计数法转换为标准金钱格式
	 * @param number
	 * @return
	 */
	public static String formatDouble(double number) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(number);
	}
}
