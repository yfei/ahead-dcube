package cn.dcube.ahead.ds;

import cn.dcube.ahead.base.crypto.AESUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 对数据库密码进行加密。
 * 当需要引用时,使用@Important引入即可
 *
 * @date：2021-12-29 14:28<br>
 * @author：yangfei<br>
 * @version: v1.0
 */
@Slf4j
@Configuration
@Conditional(DataSourceCondition.class)
public class DruidDataSourceConfig {

    @Autowired
    private Environment env;

    @Autowired
    private DataSourceProperties properties;

    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource() throws SQLException {
        log.info("init druid datasource....");
        DruidDataSourceBuilder factory = DruidDataSourceBuilder.create();
        DruidDataSource ds = factory.build(env, "spring.datasource.druid");
        try {
            String passwordDecypt = AESUtil.decryptWithBase64(properties.getPassword(), AESUtil.SEED);
            ds.setPassword(passwordDecypt);
        } catch (Exception e) {
            log.error("数据库密码{}解密失败!", properties.getPassword());
        }
        return ds;
    }

}
