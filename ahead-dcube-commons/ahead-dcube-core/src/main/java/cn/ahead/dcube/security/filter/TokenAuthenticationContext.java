package cn.ahead.dcube.security.filter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ahead.dcube.security.exception.AheadSecurityException;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @desc: TOKEN 处理方式
 * @date: 2023年4月4日 下午1:58:41<br>
 * @author:yangfei<br>
 * @since 1.0.0
 */
@Service
@Slf4j
public class TokenAuthenticationContext {

	private Map<String, ITokenAuthentication> taCatgory = new ConcurrentHashMap<>();

	@Autowired
	private List<ITokenAuthentication> authentications;

	@PostConstruct
	public void init() {
		log.info("注册token认证服务!");
		if (authentications != null) {
			authentications.forEach(a -> {
				if (a.enable()) {
					this.regist(a.type(), a);
				}
			});
		}
	}

	public void regist(String key, ITokenAuthentication ta) {
		taCatgory.put(key, ta);
	}

	public void handleTokenAuthentication(HttpServletRequest request, HttpSession session, String token) {
		if (token != null) {
			String key = this.getTokenCategoryKey(token);
			if (key != null && taCatgory.containsKey(key)) {
				taCatgory.get(key).handle(request, session, token);
			} else {
				throw new AheadSecurityException("没有Token认证服务!");
			}
		}
	}

	public String getTokenCategoryKey(String token) {
		String key = "JWT";
		for (ITokenAuthentication a : authentications) {
			if (a.enable() && a.ofType(token)) {
				key = a.type();
				break;
			}
		}
		return key;
	}
}
