package cn.dcube.ahead.dynamicDS;

import java.lang.annotation.*;

@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDS {
    String name() default "master";
}
