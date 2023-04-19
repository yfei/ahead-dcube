package cn.ahead.dcube.security.token.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ahead.dcube.base.dto.CommonLoginUser;
import cn.ahead.dcube.commons.util.StringUtils;
import cn.ahead.dcube.security.token.cache.TokenCache;
import cn.ahead.dcube.security.token.config.TokenConfig;
import lombok.Getter;

@Component
public class TokenService {

	public static final String AHEAD_TOKEN = "Ahead-token";

	@Autowired
	private TokenConfig config;

	@Autowired
	@Getter
	public TokenCache tokenCache;

	/**
	 * 设值token
	 * 
	 * @param loginUser
	 */
	public String setToken(CommonLoginUser loginUser) {
		return tokenCache.set(loginUser);
	}

	/**
	 * 更新时间
	 * 
	 * @param token
	 * @param loginUser
	 */
	public void updateTokenTimeout(String token, CommonLoginUser loginUser) {
		tokenCache.update(token, loginUser);
	}

	/**
	 * 设值token
	 * 
	 * @param loginUser
	 */
	public void removeToken(String token) {
		tokenCache.remove(token);
	}

	public String getTokenByOrder(HttpServletRequest request) {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String token = null;

		String headerToken = getTokenFromHeaders(servletRequest);
		if (StringUtils.isNotEmpty(headerToken)) {
			token = headerToken;
		} else {
			String cookieToken = getTokenFromCookie(servletRequest);
			if (StringUtils.isNotEmpty(cookieToken)) {
				token = cookieToken;
			}
		}

		return token;
	}

	public String getTokenFromHeaders(HttpServletRequest request) {
		String token = request.getHeader(config.getHeader());
		return token;
	}

	public String getTokenFromCookie(HttpServletRequest request) {
		String token = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (AHEAD_TOKEN.equals(cookie.getName())) {
					token = cookie.getValue();
					break;
				}
			}
		}
		return token;
	}

}
