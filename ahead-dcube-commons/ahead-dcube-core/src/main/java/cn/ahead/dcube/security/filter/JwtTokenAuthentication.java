package cn.ahead.dcube.security.filter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.security.dto.SysLoginUser;
import cn.ahead.dcube.security.token.service.TokenService;
import cn.ahead.dcube.security.token.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtTokenAuthentication implements ITokenAuthentication {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private TokenAuthenticationContext context;

	@Override
	public void handle(HttpServletRequest request, HttpSession session, String token) {
		boolean authed = false; // 是否验证通过
		if (TokenUtil.verifyOnly(token) && tokenService.getTokenCache().exist(token)) {
			// token验证通过
			session.setAttribute(AheadSysConstant.SESSION_USER, tokenService.getTokenCache().get(token));
			// 重新设置
			tokenService.updateTokenTimeout(token, tokenService.getTokenCache().get(token));
			authed = true;
		} else {
			log.warn("the token {} is invalid.", token);
		}
		if (authed) {
			SysLoginUser user = (SysLoginUser) session.getAttribute(AheadSysConstant.SESSION_USER);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
			        null, user.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}

	}

	@Override
	public boolean ofType(String token) {
		return !token.startsWith("wechat");
	}

	@Override
	public boolean enable() {
		return true;
	}

	@Override
	public String type() {
		return "JWT";
	}

}
