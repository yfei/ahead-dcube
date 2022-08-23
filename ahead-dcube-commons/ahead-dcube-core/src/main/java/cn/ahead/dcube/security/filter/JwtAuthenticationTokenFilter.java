package cn.ahead.dcube.security.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.base.dto.LoginUserModel;
import cn.ahead.dcube.security.token.service.TokenService;
import cn.ahead.dcube.security.token.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * token过滤器 验证token有效性
 * 
 * @author yangfei
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpSession session = servletRequest.getSession();
		List<String> tokens = tokenService.getTokenByOrder(request);
		boolean noauth = true;
		if (tokens.size() > 0) {
			for (int i = 0; i < tokens.size(); i++) {
				if (TokenUtil.verifyOnly(tokens.get(i)) && tokenService.getTokenCache().exist(tokens.get(i))) {
					// token验证通过
					session.setAttribute(AheadSysConstant.SESSION_USER,
							tokenService.getTokenCache().get(tokens.get(i)));
					noauth = false;
					break;
				} else {
					log.warn("the token {} is invalid.", tokens.get(i));
				}
			}
		}
		if (!noauth) {
			LoginUserModel user = (LoginUserModel) session.getAttribute(AheadSysConstant.SESSION_USER);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
					null, user.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		chain.doFilter(request, response);
	}

}
