package cn.ahead.dcube.resource;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.ahead.dcube.config.ApplicationConfig;

/**
 * 通用配置
 * 
 * @author ruoyi
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {

	public static final String RESOURCE_PREFIX = "/profile";

	@Autowired
	private ApplicationConfig config;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/** 本地文件上传路径 */
		registry.addResourceHandler(RESOURCE_PREFIX + "/**").addResourceLocations("file:" + config.getProfile() + "/");

		/** swagger配置 */
		registry.addResourceHandler("/swagger-ui/**")
		        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
		        .setCacheControl(CacheControl.maxAge(5, TimeUnit.HOURS).cachePublic());
	}

}
