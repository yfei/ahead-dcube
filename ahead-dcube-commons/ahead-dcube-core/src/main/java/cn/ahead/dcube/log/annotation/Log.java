package cn.ahead.dcube.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.ahead.dcube.log.enums.OperType;

/**
 * 自定义操作日志记录注解
 * 
 * @author ruoyi
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
	/**
	 * 功能描述
	 */
	public String title() default "";

	/**
	 * 功能
	 */
	public OperType oper() default OperType.OTHER;

	/**
	 * 是否保存请求的参数
	 */
	public boolean saveRequest() default true;
}
