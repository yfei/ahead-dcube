package cn.ahead.dcube.security.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.base.dto.LoginUserModel;
import cn.ahead.dcube.base.exception.AheadBaseException;
import cn.ahead.dcube.base.response.code.SecurityResponseCode;

public class SecurityUtil {

	public static LoginUserModel getCurrentUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		LoginUserModel user = (LoginUserModel) request.getSession().getAttribute(AheadSysConstant.SESSION_USER);
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

}