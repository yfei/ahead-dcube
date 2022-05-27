package cn.dcube.ahead.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author hejunjian
 * @date 2020/12/10 17:10
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisService {

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 获取keys
	 * @param pattern
	 * @return
	 */
	public Set<String> getKeys(String pattern) {
		if (pattern == null) {
			pattern = "*";
		}
		return redisTemplate.keys(pattern);
	}

	/**
	 * 缓存基本对象，String 、Integer、实体类
	 *
	 * @param key   缓存的键值
	 * @param value 缓存的值
	 * @return 缓存的对象
	 */
	public <T> ValueOperations<String, T> set(String key, T value) {
		ValueOperations<String, T> operations = redisTemplate.opsForValue();
		operations.set(key, value);
		return operations;
	}

	/**
	 * 缓存基本对象，String 、Integer、实体类
	 *
	 * @param key      缓存的键值
	 * @param value    缓存的值
	 * @param timeOut  超时时长
	 * @param timeUnit 时间颗粒度
	 * @return 缓存的对象
	 */
	public <T> ValueOperations<String, T> set(String key, T value, Integer timeOut, TimeUnit timeUnit) {
		ValueOperations<String, T> operations = redisTemplate.opsForValue();
		operations.set(key, value, timeOut, timeUnit);
		return operations;
	}

	/**
	 * 根据key获取缓存对象值
	 *
	 * @param key 缓存的键值
	 * @return 缓存对象值
	 */
	public <T> T get(String key) {
		ValueOperations<String, T> operations = redisTemplate.opsForValue();
		return operations.get(key);
	}

	/**
	 * 根据key删除存储对象
	 *
	 * @param key 缓存对象键值
	 */
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 获取Hash中的数据
	 *
	 * @param key  Redis键
	 * @param hKey Hash键
	 * @return Hash中的对象
	 */
	public <T> T getCacheMapValue(final String key, final String hKey) {
		HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
		return opsForHash.get(key, hKey);
	}

	/**
	 * 往Hash中存入数据
	 *
	 * @param key   Redis键
	 * @param hKey  Hash键
	 * @param value 值
	 */
	public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
		redisTemplate.opsForHash().put(key, hKey, value);
	}

	/**
	 * 获得缓存的Map
	 *
	 * @param key
	 * @return
	 */
	public <T> Map<String, T> getCacheMap(final String key) {
		return redisTemplate.opsForHash().entries(key);
	}
	
	public <T> Set<T> zrangeByScore(final String key, double min,double max,int offset,int limit) {
		return redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, limit);
	}

}
