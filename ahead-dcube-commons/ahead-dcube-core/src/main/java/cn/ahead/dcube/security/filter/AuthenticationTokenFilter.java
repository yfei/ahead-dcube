package cn.ahead.dcube.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.ahead.dcube.security.token.service.TokenService;
import lombok.extern.slf4j.Slf4j;

/**
 * token过滤器 验证token有效性
 * 
 * @author yangfei
 */
@Component
@Slf4j
public class AuthenticationTokenFilter extends OncePerRequestFilter {

	public static final String WECHAT_PREFIX = "wechat:";

	@Autowired
	private TokenService tokenService;

	@Autowired
	private TokenAuthenticationContext taContext;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	        throws ServletException, IOException {
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpSession session = servletRequest.getSession();
		String token = tokenService.getTokenByOrder(request);
		if (StringUtils.hasLength(token)) {
			taContext.handleTokenAuthentication(servletRequest, session, token);
		}
		chain.doFilter(request, response);
	}

}
