package cn.ahead.dcube.context;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring 运行上下文
 *
 * @date: 2021-12-21 11:06 <br>
 * @author: yangfei <br>
 * @version: 1.0
 */
@Component
@Slf4j
public class SpringContext implements ApplicationContextAware {

    /**
     * the spring application context
     */
    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	appContext = applicationContext;
	DataSource dataSource = appContext.getBean(DataSource.class);
	if (dataSource != null) {
	    try {
		String url = dataSource.getConnection().getMetaData().getURL();
		log.debug("初始化数据库信息:" + url);
	    } catch (SQLException e) {
		log.error("", e);
	    }

	}
    }

    public static ApplicationContext getContext() {
	return appContext;
    }

    /**
     * get spring bean by beanName.
     *
     * @param beanName the bean name.
     * @return the bean instance.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
	Assert.notNull(beanName, "beanName cannot be empty!");
	Object bean = appContext.getBean(beanName);
	if (bean != null) {
	    return (T) bean;
	}
	return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> requiredType) {
	Object bean = appContext.getBean(requiredType);
	if (bean != null) {
	    return (T) bean;
	}
	return null;
    }
}
