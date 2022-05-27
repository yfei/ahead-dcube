package cn.dcube.ahead.module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"cn.dcube.ahead"})
@MapperScan(basePackages = {"cn.dcube"})
// @Import({DynamicDataSourceRegister.class}) // 多数据源时引用
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ApplicationMain{
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}

}
