package cn.ahead.dcube.security.token.cache.redis;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import cn.ahead.dcube.base.dto.CommonLoginUser;
import cn.ahead.dcube.security.token.cache.TokenCache;
import cn.ahead.dcube.security.token.config.TokenConfig;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author yangfei
 *
 */
@ConditionalOnProperty(prefix = "token", name = "cache", havingValue = "redis", matchIfMissing = true)
@Component
@Slf4j
public class TokenRedisCache implements TokenCache{
	
	@Autowired
	private TokenConfig config;
	
	@PostConstruct
	public void init() {
		log.debug("the token cache is TokenRedisCache.");
	}

	
	@Override
	public boolean exist(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CommonLoginUser get(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String set(CommonLoginUser loginUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String token) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Map<String, CommonLoginUser> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
