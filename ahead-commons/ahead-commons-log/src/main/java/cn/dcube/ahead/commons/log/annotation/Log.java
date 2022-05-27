package cn.dcube.ahead.commons.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.dcube.ahead.commons.log.enums.OperationType;

/**
 * 日志注解
 * @date: 2021-12-21 9:56 <br>
 * @author: yangfei <br>
 * @version: 1.0
 */
// 注解生命周期 程序运行时存在
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Documented
public @interface Log {


    /**
     * 模块名称
     *
     * @return 模块名称
     */
    public String module() default "";

    /**
     * 操作类型
     *
     * @return 操作类型
     */
    public OperationType operationType() default OperationType.OTHER;
    
    /**
     * 操作表名
     *
     * @return 操作表名
     */
    public String tableName() default "";


    /**
     * 是否保存请求参数
     *
     * @return 是否保存请求参数
     */
    public boolean isSaveParam() default true;

    
}
