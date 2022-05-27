package cn.dcube.ahead.ds;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @date：2021-12-29 17:16<br>
 * @author：yangfei<br>
 * @version: v1.0
 */

public class DataSourceCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return null != env.getProperty("spring.datasource");
    }
}
