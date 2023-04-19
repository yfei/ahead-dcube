package cn.ahead.dcube.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.security.config.SecurityConfig;
import cn.ahead.dcube.security.dto.SysLoginUser;
import cn.ahead.dcube.security.token.service.TokenService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WechatPhoneTokenAuthentication implements ITokenAuthentication {

	@Autowired
	private TokenService tokenService;

	public static final String WECHAT_PHONE_PREFIX = "wechat2:";

	@Override
	public void handle(HttpServletRequest request, HttpSession session, String token) {
		boolean authed = false; // 是否验证通过

		SysLoginUser user = new SysLoginUser();
		// 设置账号 unionid
		String unionId = this.wechatUnionId(token);
		String phone = request.getHeader("wxphoneNumber");
		String url = request.getRequestURI();
		if (!url.contains("/login") && !url.contains("/anonymous")) { // 非登录和匿名
			if (StringUtils.hasLength(unionId) && StringUtils.hasLength(phone)) {
				String phoneNumber = this.wechatPhone(phone);
				if (StringUtils.hasLength(phoneNumber)) {
					authed = true;
					// session中模拟加入user
					user.setAccount(phoneNumber);
					user.setPhone(phoneNumber);
					user.setUnionId(unionId);
					session.setAttribute(AheadSysConstant.SESSION_USER, user);
					tokenService.updateTokenTimeout(token, user);
				}
			}
		}
		if (authed) {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
			        null, user.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}

	}

	private boolean wechatToken(String token) {
		if (token.startsWith(WECHAT_PHONE_PREFIX)) {
			return true;
		}
		return false;
	}

	private String wechatUnionId(String token) {
		return token.substring(WECHAT_PHONE_PREFIX.length());
	}

	private String wechatPhone(String phone) {
		return phone.substring(WECHAT_PHONE_PREFIX.length());
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
		return "WECHAT_PHONE";
	}
}
