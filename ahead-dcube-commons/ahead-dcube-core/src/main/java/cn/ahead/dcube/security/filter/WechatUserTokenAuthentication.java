package cn.ahead.dcube.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.base.enums.SNSType;
import cn.ahead.dcube.context.SpringContext;
import cn.ahead.dcube.security.dto.SysLoginUser;
import cn.ahead.dcube.security.service.IUserSecurityService;
import cn.ahead.dcube.security.token.service.TokenService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @desc: 如果微信号绑定了账号,使用此种方式,从sns表中读取数据
 * @date: 2023年4月4日 下午2:14:26<br>
 * @author:yangfei<br> 
 * @since 1.0.0
 */
@Service
@Slf4j
public class WechatUserTokenAuthentication implements ITokenAuthentication {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private TokenAuthenticationContext context;

	public static final String WECHAT_PREFIX = "wechat:";

	public static final String WECHAT_PHONE_PREFIX = "wechat_phone:";

	@Override
	public void handle(HttpServletRequest request, HttpSession session, String token) {
		boolean authed = false; // 是否验证通过
		// 微信小程序等
		if (this.wechatToken(token)) {
			if (!request.getRequestURI().contains("/login")) { // 非登录
				SysLoginUser user = SpringContext.getBean(IUserSecurityService.class).getBySNS(SNSType.WEIXIN.getCode(),
				        this.wechatUnionId(token));
				if (user != null && user.getId() != null) {
					// token验证通过
					session.setAttribute(AheadSysConstant.SESSION_USER, user);
					authed = true;
					tokenService.updateTokenTimeout(token, user);
				} else {
					session.removeAttribute(AheadSysConstant.SESSION_USER);
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

	@Override
	public boolean ofType(String token) {
		return wechatToken(token);
	}

	@Override
	public boolean enable() {
		return true;
	}

	@Override
	public String type() {
		return "WECHAT_USER";
	}
}
