package cn.dcube.ahead.base.crypto;

import java.security.MessageDigest;

/**
 * 
 * 
 * @author：yangfei<br>
 * @date：2021年3月24日上午9:05:15
 * @since 1.0
 */
public class Md5Util {

	/**
	 * 字符串转MD5
	 *
	 * @param plainText
	 *            文本字符串
	 * @return MD5码
	 * @throws Exception
	 */
	public static String md5(String plainText) throws Exception {
		StringBuffer buf = new StringBuffer();
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(plainText.getBytes("UTF-8"));
		byte b[] = md.digest();
		int i;
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}
}
