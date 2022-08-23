package cn.ahead.dcube.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.security.token.service.TokenService;

/**
 * 自定义退出处理类 返回成功
 * 
 * @author yangfei
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
	@Autowired
	private TokenService tokenService;

	/**
	 * 退出处理
	 * 
	 * @return
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// 退出
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpSession session = servletRequest.getSession();
		String token = tokenService.getTokenFromHeaders(request);
		tokenService.removeToken(token);
		session.removeAttribute(AheadSysConstant.SESSION_USER);
	}

}
