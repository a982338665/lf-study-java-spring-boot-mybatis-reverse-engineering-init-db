package pers.li.aseckill.config.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 监听类，项目启动完成执行
 */
@Component
@lombok.extern.slf4j.Slf4j
public class ApplicationRunnerDBInit implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.error("监听项目初始化 --> ApplicationRunnerDBInit");
    }


}