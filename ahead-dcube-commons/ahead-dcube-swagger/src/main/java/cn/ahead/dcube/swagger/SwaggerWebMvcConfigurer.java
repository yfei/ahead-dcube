package cn.ahead.dcube.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringBoot自动配置本身并不会把/swagger-ui.html这个路径映射到对应的目录META-INF/resources/下面。
 * 我们加上这个映射即可。构建一个WebMvcConfig 类。
 * 
 * @author y
 */
@Configuration
@EnableWebMvc
public class SwaggerWebMvcConfigurer implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/** swagger配置 */
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}