package cn.ahead.dcube.commons.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/** 空字符串 */
	private static final String EMPTYSTR = "";

	/**
	 * 下划线
	 */
	private static final char SEPARATOR = '_';

	/**
	 * 判断字符串是否为空串
	 *
	 * @param value String
	 * @return true：空 false：非空
	 */
	public static boolean isEmpty(String value) {
		return isNull(value) || "".equals(value.trim());
	}

	/**
	 * 判断字符串不为空串
	 *
	 * @param value string
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	/**
	 * 判断字符串是否为null
	 *
	 * @param value string
	 * @return boolean
	 */
	public static boolean isNull(String value) {
		return value == null;
	}

	/**
	 * 判断对象是否为空
	 *
	 * @param value 需要判断的对象
	 * @return true 空 false 非空
	 */
	public static boolean isNull(Object value) {
		return value == null;
	}

	/**
	 * 判断对象是否非空
	 *
	 * @param value 需要判断的对象
	 * @return true 非空 false 空
	 */
	public static boolean isNotNull(Object value) {
		return !isNull(value);
	}

	/**
	 * 判断map对象是否非空
	 *
	 * @param map 判断对象
	 * @return true 非空 false 空
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	/**
	 * 判断map是否为空
	 *
	 * @param map map对象
	 * @return true 空 false 非空
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return isNull(map) || map.isEmpty();
	}

	/**
	 * 判断map是否为非空
	 *
	 * @param objects 数组对象
	 * @return true 非空 false 空
	 */
	public static boolean isNotEmpty(Object[] objects) {
		return !isEmpty(objects);
	}

	/**
	 * 判断数组是否为空
	 *
	 * @param objects 数组对象
	 * @return true 空 false 非空
	 */
	public static boolean isEmpty(Object[] objects) {
		return isNull(objects) || (objects.length == 0);
	}

	/**
	 * 判断集合是否为非空
	 *
	 * @param collection 集合对象
	 * @return true 非空 false 空
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

	/**
	 * 判断集合是否为空
	 *
	 * @param collection 集合
	 * @return true 空 false 非空
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return isNull(collection) || collection.isEmpty();
	}

	/**
	 * 驼峰式命名法 例如：user_name->userName
	 *
	 * @param s 转化字符串
	 * @return 结果字符串
	 */
	public static String toCamelCase(String s) {
		if (isNull(s)) {
			return null;
		}
		s = s.toLowerCase();
		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。
	 * 例如：HELLO_WORLD->HelloWorld
	 *
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String convertToCamelCase(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母大写
			return name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String[] camels = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 首字母大写
			result.append(camel.substring(0, 1).toUpperCase());
			result.append(camel.substring(1).toLowerCase());
		}
		return result.toString();
	}

	/**
	 * 格式化文本, {} 表示占位符<br>
	 * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
	 * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
	 * 例：<br>
	 * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
	 * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
	 * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
	 *
	 * @param template 文本模板，被替换的部分用 {} 表示
	 * @param params   参数值
	 * @return 格式化后的文本
	 */
	public static String format(String template, Object... params) {
		if (isEmpty(params) || isEmpty(template)) {
			return template;
		}
		return StringFormatter.format(template, params);
	}

	/**
	 * 数组转字符串
	 *
	 * @param strings string数组
	 * @return 字符串
	 */
	public static String stringArrToString(String[] strings) {
		if (strings == null || strings.length == 0) {
			return "";
		}
		StringBuffer stringBuffer = new StringBuffer();
		for (String string : strings) {
			stringBuffer.append(",").append(string);
		}
		return stringBuffer.toString().substring(1);
	}

	/**
	 * 将字符串首字母大写
	 *
	 * @param str 字符串
	 * @return 首字母大写字符串
	 */
	public static String toUpperFisrtChar(String str) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 根据className获取spring beanName
	 *
	 * @param str className
	 * @return beanName
	 */
	public static String getBeanNameByClassName(String str) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		String[] strings = str.split("\\.");
		String beanName = strings[strings.length - 1];
		return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
	}

	/**
	 * 从字符串根据标志提取字符串,例：string = "${带提取信息}" topSign = "${" endSign = "}" ;返回值 "带提取信息"
	 *
	 * @param string  待提取字符串
	 * @param topSign 匹配起始标志
	 * @param endSign 匹配终止标志
	 * @return 匹配结果
	 */
	public static String extractContent(String string, String topSign, String endSign) {
		String result = "";
		if (isNotEmpty(string)) {
			String regex = "(?<=" + topSign + ")[^" + endSign + "]+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(string);
			if (matcher.find()) {
				result = matcher.group();
			}
		}
		return result;
	}

	/**
	 * 字符串转boolean 判断规则： 字符串中是否包含 ok 、成功、true、success 字符 str2bool("ok") = true 、
	 * str2bool("ok") = true
	 *
	 * @param string 待转化字符串
	 * @return boolean
	 */
	public static boolean str2bool(String string) {
		if (isEmpty(string)) {
			return false;
		}
		if (string.contains("ok") || string.contains("成功") || string.contains("true") || string.contains("success")) {
			return true;
		}
		return false;
	}

	/**
	 * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
	 *
	 * @param str  指定字符串
	 * @param strs 需要检查的字符串数组
	 * @return 是否匹配
	 */
	public static boolean matches(String str, List<String> strs) {
		if (isEmpty(str) || isEmpty(strs)) {
			return false;
		}
		for (String testStr : strs) {
			if (matches(str, testStr)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 查找指定字符串是否匹配指定字符串数组中的任意一个字符串
	 *
	 * @param str  指定字符串
	 * @param strs 需要检查的字符串数组
	 * @return 是否匹配
	 */
	public static boolean matches(String str, String... strs) {
		if (isEmpty(str) || isEmpty(strs)) {
			return false;
		}
		for (String testStr : strs) {
			if (matches(str, testStr)) {
				return true;
			}
		}
		return false;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 截取字符串
	 * 
	 * @param str   字符串
	 * @param start 开始
	 * @return 结果
	 */
	public static String substring(final String str, int start) {
		if (str == null) {
			return EMPTYSTR;
		}

		if (start < 0) {
			start = str.length() + start;
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return EMPTYSTR;
		}

		return str.substring(start);
	}

	/**
	 * 截取字符串
	 * 
	 * @param str   字符串
	 * @param start 开始
	 * @param end   结束
	 * @return 结果
	 */
	public static String substring(final String str, int start, int end) {
		if (str == null) {
			return EMPTYSTR;
		}

		if (end < 0) {
			end = str.length() + end;
		}
		if (start < 0) {
			start = str.length() + start;
		}

		if (end > str.length()) {
			end = str.length();
		}

		if (start > end) {
			return EMPTYSTR;
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

}
