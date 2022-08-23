package cn.ahead.dcube.security.token.config;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ConfigurationProperties(prefix = "token")
@Data
@Slf4j
public class TokenConfig {

	// 令牌自定义标识
	private String header;

	// 令牌有效期（默认30分钟）
	private long expireTime;

	// 令牌缓存类型 redis/memory
	private String cache;

	@PostConstruct
	public void info() {
		log.debug("the token config---> header: {}, expireTime: {}, cache: {}", header, expireTime, cache);
	}

}