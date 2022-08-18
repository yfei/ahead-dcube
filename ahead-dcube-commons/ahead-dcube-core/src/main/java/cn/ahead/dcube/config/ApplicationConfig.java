package cn.ahead.dcube.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 系统信息
 * @author yangfei
 *
 */
@Component
@ConfigurationProperties(prefix = "ahead")
@Data
public class ApplicationConfig {
	
	// 应用名称
	private String name;
	
	// 版本号
	private String version;
	
	// 版权信息
	private String copyright;
	
}
