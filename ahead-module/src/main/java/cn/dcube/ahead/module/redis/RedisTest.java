package cn.dcube.ahead.module.redis;

import cn.dcube.ahead.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

// @Component
public class RedisTest {
	
	@Autowired
	private RedisService redisService;
	
	// @PostConstruct
	public void test() {
		Set<String> sets = redisService.zrangeByScore("geolite_city_ipv4", 0d, 16777471d, 0,1);
		System.out.println(sets.size());
	}

}
