package pers.li.aseckill.config.db;


import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * @Description ：多数据源配置下，数据初始化问题
 * 实现核心
 * 在SpringBoot的架构中，DataSourceInitializer类可以实现自动执行脚本的功能。
 * 通过自定义DataSourceInitializer Bean就可以实现按照业务要求执行特定的脚本。
 * 实现方法
 * 前提
 * 已经构建了DataSource Bean。
 * 方法
 * 通过@Configuration、@Bean和@Value三个注解实现自定义DataSourceInitializer Bean，
 * 现在Bean的定义中实现自动化执行脚本的业务逻辑。
 */
@Configuration
@lombok.extern.slf4j.Slf4j
public class BatchTaskDataSourceInitializer {
    /**
     * 构建Resource对象
     */
    @Value("classpath:data.sql")
    private Resource businessScript;
    @Value("classpath:db/schema.sql")
    private Resource dbdataScript;
    @Value("classpath:db/data.sql")
    private Resource dbschemaScript;

    @javax.annotation.Resource
    private  ApplicationContext applicationContext;
    /**
     * 自定义Bean实现业务的特殊需求
     * 此处参数 final DataSource dataSource 取datasource
     * 加注解 DynamicDataSourceConfiguration @Primary 作为初始化数据位置
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        log.error("初始化数据执行-->BatchTaskDataSourceInitializer --> dataSource");
        //初始化数据的数据源选择
        Object dbSlave01 = applicationContext.getBean("dbSlave01");
        //Object dbDefault = applicationContext.getBean("dbSlave02");
        //设置需要执行sql的数据源
//        initializer.setDataSource((DataSource)dbSlave01);
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(businessScript,dbdataScript,dbschemaScript);
        return populator;
    }
}