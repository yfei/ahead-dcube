package cn.ahead.dcube.security.token.cache.memory;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import cn.ahead.dcube.base.dto.LoginUserModel;
import cn.ahead.dcube.security.token.cache.TokenCache;
import cn.ahead.dcube.security.token.config.TokenConfig;
import cn.ahead.dcube.security.token.util.TokenUtil;
import cn.hutool.cache.impl.TimedCache;
import lombok.extern.slf4j.Slf4j;

/**
 * 内存实现
 * 
 * @author yangfei
 *
 */
@ConditionalOnProperty(prefix = "token", name = "cache", havingValue = "memory", matchIfMissing = true)
@Component
@Slf4j
public class TokenMemoryCache implements TokenCache {

	private TimedCache<String, LoginUserModel> tokenCache;

	@Autowired
	private TokenConfig config;

	@PostConstruct
	public void init() {
		log.debug("the token cache is TokenMemoryCache.");
		tokenCache = new TimedCache<String, LoginUserModel>(config.getExpireTime() * 60 * 1000L);
	}

	@Override
	public boolean exist(String token) {
		return tokenCache.containsKey(token);
	}

	@Override
	public LoginUserModel get(String token) {
		return tokenCache.get(token);
	}

	@Override
	public String set(LoginUserModel loginUser) {
		String token = TokenUtil.sign(loginUser);
		tokenCache.put(token, loginUser);
		return token;
	}

	@Override
	public void remove(String token) {
		tokenCache.remove(token);
	}

	@Override
	public Map<String, LoginUserModel> list() {
		return null;
	}

}
