package pers.li.aseckill.config.db;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author lishengbo
 * @description 数据源管理
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    private static final Logger LOG = Logger.getLogger(DynamicRoutingDataSource.class);
    @Override
    protected Object determineCurrentLookupKey() {
        LOG.info("当前数据源：{}"+ DynamicDataSourceContextHolder.get());
        return DynamicDataSourceContextHolder.get();
    }
}
