package cn.dcube.ahead.dynamicDS;

import cn.dcube.ahead.base.crypto.AESUtil;
import cn.dcube.ahead.base.util.StringUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date：2021-12-27 10:14<br>
 * @author：yangfei<br>
 * @version: v1.0
 */
@Slf4j
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    // 如配置文件中未指定数据源类型，使用该默认值
    private static final Object DATASOURCE_TYPE_DEFAULT = "com.alibaba.druid.pool.DruidDataSource";


    /**
     * 参数绑定工具
     */
    private Binder binder;
    /**
     * 配置上下文（也可以理解为配置文件的获取工具）
     */
    private Environment env;
    // 默认数据源
    private DataSource defaultDataSource;
    /**
     * 自定义数据源
     */
    private Map<String, DataSource> customDataSources = new HashMap<>();

    public DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            String driverClassName = dsMap.get("driverClassName").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            // 这里对密码加密
            String password = AESUtil.decryptWithBase64(dsMap.get("password").toString(), AESUtil.SEED);
            dsMap.put("password",password);
            DruidDataSourceBuilder factory = DruidDataSourceBuilder.create();
            DruidDataSource ds = factory.build(env, "spring.datasource.druid");
            ds.setDriverClassName(driverClassName);
            ds.setUrl(url);
            ds.setUsername("root");
            ds.setPassword("cecgw");
            return ds;
        } catch (Throwable e) {
            log.error("buildDataSource failed!", e);
        }
        return null;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 将主数据源添加到更多数据源中
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        // 添加更多数据源
        targetDataSources.putAll(customDataSources);
        // 创建DynamicDataSource
        DynamicDataSourceContextHolder.dataSourceIds.addAll(customDataSources.keySet());
        //bean定义类
        GenericBeanDefinition define = new GenericBeanDefinition();
        //设置bean的类型，此处DynamicDataSource是继承AbstractRoutingDataSource的实现类
        define.setBeanClass(DynamicDataSource.class);
        //需要注入的参数，类似spring配置文件中的<property/>
        MutablePropertyValues mpv = define.getPropertyValues();
        //添加默认数据源，避免key不存在的情况没有数据源可用
        mpv.add("defaultTargetDataSource", defaultDataSource);
        //添加其他数据源
        mpv.add("targetDataSources", targetDataSources);
        //将该bean注册为datasource，不使用spring-boot自动生成的datasource
        registry.registerBeanDefinition("datasource", define);
        log.info("Dynamic DataSource Registry success !");
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
        //绑定配置器
        binder = Binder.get(env);
        initCustomDataSources();
    }


    private void initCustomDataSources() {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
        List<Map> configs = binder.bind("dcube.datasource", Bindable.listOf(Map.class)).get();
        String dsPrefix;
        DataSource custom;
        for (Map config : configs) {
            String key = config.get("key").toString();
            if (!StringUtils.isNull(key)) {
                dsPrefix = key;
            } else {
                dsPrefix = "default";
            }
            custom = buildDataSource(config);
            customDataSources.put(dsPrefix, custom);
            Map druidProperties = (Map)config.get("druid");
            dataBinder(custom, druidProperties);
            //如果 default标识为true,则将其设置为defaultDataSource
            if (null != config.get("default") && "true".equals(config.get("default").toString())) {
                defaultDataSource = custom;
            }
        }
        //如果default数据源没有，将master设置为default的数据源。
        if (null == defaultDataSource) {
            defaultDataSource = customDataSources.get("master");
        }
    }

    private void dataBinder(DataSource dataSource, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binderEx = new Binder(source);
        //将参数绑定到对象
        binderEx.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(dataSource));
    }

}
