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
import cn.ahead.dcube.base.enums.SNSType;
import cn.ahead.dcube.context.SpringContext;
import cn.ahead.dcube.security.dto.SysLoginUser;
import cn.ahead.dcube.security.service.IUserSecurityService;
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
	
	public static final String WECHAT_PREFIX = "wechat:";

	@Autowired
	private TokenService tokenService;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpSession session = servletRequest.getSession();
		List<String> tokens = tokenService.getTokenByOrder(request);
		boolean authed = false; // 是否验证通过
		if (tokens.size() > 0) {
			for (int i = 0; i < tokens.size(); i++) {
				String token = tokens.get(i);
				if (this.wechatToken(token)) {
					if (!request.getRequestURI().contains("/login")) { // 非登录
						SysLoginUser user = (SysLoginUser) session.getAttribute(AheadSysConstant.SESSION_USER);
						if (user == null) {
							// 这里造成事务的问题
							user = SpringContext.getBean(IUserSecurityService.class).getBySNS(SNSType.WEIXIN.getCode(), this.wechatUnionId(token));
							// token验证通过
							session.setAttribute(AheadSysConstant.SESSION_USER, user);
							authed = true;
							break;
						}
					}
						
				} else {
					if (TokenUtil.verifyOnly(token) && tokenService.getTokenCache().exist(token)) {
						// token验证通过
						session.setAttribute(AheadSysConstant.SESSION_USER, tokenService.getTokenCache().get(token));
						// 重新设置
						tokenService.updateTokenTimeout(token, tokenService.getTokenCache().get(token));
						authed = true;
						break;
					} else {
						log.warn("the token {} is invalid.", token);
					}
				}
			}
		}
		if (authed) {
			SysLoginUser user = (SysLoginUser) session.getAttribute(AheadSysConstant.SESSION_USER);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
					null, user.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		chain.doFilter(request, response);
	}

	private boolean wechatToken(String token) {
		if (token.startsWith(WECHAT_PREFIX)) {
			return true;
		}
		return false;
	}
	
	private String wechatUnionId(String token) {
		return token.substring(WECHAT_PREFIX.length());
	}

}
