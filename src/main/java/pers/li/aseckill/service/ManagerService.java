package pers.li.aseckill.service;

import org.springframework.stereotype.Service;
import pers.li.aseckill.annotation.TargetDataSource;
import pers.li.aseckill.config.db.DataSourceKey;
import pers.li.aseckill.dao.ManagerDao;
import pers.li.aseckill.utils.JDBCUtils;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:31
 */
@Service
public class ManagerService {

    @Resource
    ManagerDao dao;

    @TargetDataSource(dataSourceKey = DataSourceKey.DB_DEFAULT_DATABASE)
    public List<String> getBYName(String doctor) {
        return dao.getBYName("%"+doctor+"%");
    }
    @TargetDataSource(dataSourceKey = DataSourceKey.DB_DEFAULT_DATABASE)
    public List<String> getHospitalBYName(String hospital) {
        return dao.getHospitalBYName("%"+hospital+"%");
    }
    @TargetDataSource(dataSourceKey = DataSourceKey.DB_DEFAULT_DATABASE)
    public List<String> getDeptBYName(String s) {
        return dao.getDeptBYName( s);
    }

    @TargetDataSource(dataSourceKey = DataSourceKey.DB_DEFAULT_DATABASE)
    public List<String> getBYNameTest(String doctor) {
        return dao.getBYName("%"+doctor+"%");
    }
    @TargetDataSource(dataSourceKey = DataSourceKey.DB_DEFAULT_DATABASE)
    public List<String> getHospitalBYNameTest(String hospital) {
        return dao.getHospitalBYName("%"+hospital+"%");
    }
    @TargetDataSource(dataSourceKey = DataSourceKey.DB_DEFAULT_DATABASE)
    public List<String> getDeptBYNameTest(String s) {
        return dao.getDeptBYName( s);
    }
}
