package cn.ahead.dcube.commons.regex;

import java.util.regex.Pattern;

public class AheadRegexUtil {
	
	/**
	 * 月份 202301
	 */
	public final static Pattern MONTH = Pattern.compile("^[1-9]\\d{3}(0[1-9]|1[0-2])$");
	
	/**
	 * 日期
	 */
	public final static Pattern TIMESTAMP = Pattern.compile("^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$");

}
