package cn.ahead.dcube.commons.regex;

import java.util.regex.Pattern;

public class AheadRegexUtil {
	
	/**
	 * 月份
	 */
	public final static Pattern MONTH = Pattern.compile("^[1-9]\\d{3}(0[1-9]|1[0-2])$");

}
