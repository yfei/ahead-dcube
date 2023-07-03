package cn.ahead.dcube.config;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统信息
 * 
 * @author yangfei
 *
 */
@Component
@ConfigurationProperties(prefix = "ahead")
@Data
@Slf4j
public class ApplicationConfig {

	// 应用名称
	private String name;

	// 版本号
	private String version;

	// 版权信息
	private String copyright;

	// 文件profile
	private String profile;

	@PostConstruct
	public void info() {
		log.info("the application name: {}", name);
		log.info("the application version: {}", version);
		log.info("the application copyright: {}", copyright);
	}

}
