package pers.li.aseckill.config.db;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pers.li.aseckill.annotation.TargetDataSource;

/**
 * @author
 * @version 1.0
 */
@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {
    private static final Logger LOG = Logger.getLogger(DynamicDataSourceAspect.class);


    /**
     * 执行方法前更换数据源
     *
     * @param joinPoint        切点
     * @param targetDataSource 动态数据源
     */
    @Before("@annotation(targetDataSource)")
    public void doBefore(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        DataSourceKey dataSourceKey = targetDataSource.dataSourceKey();
        LOG.info(String.format("设置数据源为  %s", DataSourceKey.DB_DEFAULT_DATABASE));
        DynamicDataSourceContextHolder.set(dataSourceKey);
       /* if (dataSourceKey == DataSourceKey.DB_OTHER) {
            LOG.info(String.format("设置数据源为  %s", DataSourceKey.DB_OTHER));
            DynamicDataSourceContextHolder.set(DataSourceKey.DB_OTHER);
        } else {
            LOG.info(String.format("使用默认数据源  %s", DataSourceKey.DB_MASTER));
            DynamicDataSourceContextHolder.set(DataSourceKey.DB_MASTER);
        }*/
    }

    /**
     * 执行方法后清除数据源设置
     *
     * @param joinPoint        切点
     * @param targetDataSource 动态数据源
     */
    @After("@annotation(targetDataSource)")
    public void doAfter(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        LOG.info(String.format("当前数据源  %s  执行清理方法", targetDataSource.dataSourceKey()));
        DynamicDataSourceContextHolder.clear();
    }

    /*

    @Pointcut("execution(* pers.li.aseckill.service.*.list*())")
    public void pointCut() {
    }
*/

    /**
     * service包下list* 的方法采用从库：此项目暂不设计：必须添加注解指明要连接哪些数据库
     * @param joinPoint
     */
    /*@Before(value = "pointCut()")
    public void doBeforeWithSlave(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取当前切点方法对象
        Method method = methodSignature.getMethod();
        //判断是否为借口方法
        if (method.getDeclaringClass().isInterface()) {
            try {
                //获取实际类型的方法对象
                method = joinPoint.getTarget().getClass()
                        .getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                LOG.error("方法不存在！", e);
            }
        }
        if (null == method.getAnnotation(TargetDataSource.class)) {
            DynamicDataSourceContextHolder.setSlave();
        }
    }*/
}
