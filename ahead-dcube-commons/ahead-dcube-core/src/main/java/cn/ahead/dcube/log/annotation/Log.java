package cn.ahead.dcube.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.ahead.dcube.log.enums.CallType;
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
	 * 模块描述
	 */
	public String module() default "";

	/**
	 * 功能描述
	 */
	public String function() default "";

	/**
	 * 功能
	 */
	public OperType oper() default OperType.OTHER;

	/**
	 * 是否定时任务
	 * 
	 * @return
	 */
	public CallType callby() default CallType.REQUEST;

	/**
	 * 是否保存请求的参数信息
	 */
	public boolean details() default true;
}
