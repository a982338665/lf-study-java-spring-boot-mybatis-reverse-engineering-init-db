package pers.li.aseckill.config.db;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lisehngbo
 * @description:数据源注入spring
 */
@MapperScan(basePackages = "pers.li.aseckill.dao")
@Configuration
public class DynamicDataSourceConfiguration {


    @Bean
    @ConfigurationProperties(prefix = "multiple.datasource.other_database_01")
    public DataSource dbOther01() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "multiple.datasource.other_database_02")
    public DataSource dbOther02() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "multiple.datasource.default_database")
    public DataSource dbDefault() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "multiple.datasource.slave_database_01")
    public DataSource dbSlave01() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "multiple.datasource.slave_database_02")
    public DataSource dbSlave02() {
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * 核心动态数据源
     * @return 数据源实例
     */
    @Bean
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setDefaultTargetDataSource(dbDefault());
        Map<Object, Object> dataSourceMap = new HashMap<>(5);
        dataSourceMap.put(DataSourceKey.DB_DEFAULT_DATABASE, dbDefault());
        dataSourceMap.put(DataSourceKey.DB_OTHER_DATABASE_01, dbOther01());
        dataSourceMap.put(DataSourceKey.DB_OTHER_DATABASE_02, dbOther02());
        dataSourceMap.put(DataSourceKey.DB_SLAVE_DATABASE_01, dbSlave01());
        dataSourceMap.put(DataSourceKey.DB_SLAVE_DATABASE_02, dbSlave02());
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        //此处设置为了解决找不到mapper文件的问题
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:pers/li/aseckill/dao/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    /**
     * 事务管理
     *
     * @return 事务管理实例
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
