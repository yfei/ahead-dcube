package cn.dcube.ahead.module;

import cn.dcube.ahead.dynamicDS.DynamicDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @date：2021-12-29 17:26<br>
 * @author：yangfei<br>
 * @version: v1.0
 */
@Service
public class DatasouceInfo {

    @Resource
    private DataSource ds;

    @PostConstruct
    public void init() {
        if (ds instanceof DynamicDataSource) {
            System.out.println("采用多数据源");
            DynamicDataSource dys = (DynamicDataSource) ds;
            DruidDataSource druidDataSource = (DruidDataSource) dys.getResolvedDefaultDataSource();
            System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
            System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
        } else if (ds instanceof DruidDataSource) {
            DruidDataSource druidDataSource = (DruidDataSource) ds;
            System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
            System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
        }
    }
}
