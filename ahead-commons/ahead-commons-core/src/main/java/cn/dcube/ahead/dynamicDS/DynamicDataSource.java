package cn.dcube.ahead.dynamicDS;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @date：2021-12-27 10:03<br>
 * @author：yangfei<br>
 * @version: v1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
