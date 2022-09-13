package cn.ahead.dcube.security.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.base.dto.CommonLoginUser;
import cn.ahead.dcube.base.exception.AheadBaseException;
import cn.ahead.dcube.base.response.code.SecurityResponseCode;
import cn.ahead.dcube.commons.crypto.Md5Util;
import cn.ahead.dcube.security.dto.SysLoginUser;
import cn.ahead.dcube.utils.ServletUtils;

public class SecurityUtil {

	public static SysLoginUser getCurrentUser() {
		HttpServletRequest request = ServletUtils.getRequestAttributes().getRequest();
		SysLoginUser user = (SysLoginUser) request.getSession().getAttribute(AheadSysConstant.SESSION_USER);
		if (user == null) {
			throw new AheadBaseException(SecurityResponseCode.UNAUTHORIZED);
		}
		return user;
	}

	/**
	 * 生成BCryptPasswordEncoder密码
	 *
	 * @param password 密码
	 * @return 加密字符串
	 */
	public static String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	/**
	 * 判断密码是否相同
	 *
	 * @param rawPassword     真实密码
	 * @param encodedPassword 加密后字符
	 * @return 结果
	 */
	public static boolean matchesPassword(String rawPassword, String encodedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encryptPassword(Md5Util.md5("123456")));
	}
}
