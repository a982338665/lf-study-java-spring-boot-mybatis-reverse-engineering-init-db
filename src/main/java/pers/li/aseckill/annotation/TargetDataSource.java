package pers.li.aseckill.annotation;


import pers.li.aseckill.config.db.DataSourceKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {
    DataSourceKey dataSourceKey() default DataSourceKey.DB_DEFAULT_DATABASE;
}
