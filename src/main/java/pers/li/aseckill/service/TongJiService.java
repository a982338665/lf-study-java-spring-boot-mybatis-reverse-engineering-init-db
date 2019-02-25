package pers.li.aseckill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.li.aseckill.dao.ManagerDao;
import pers.li.aseckill.dao.SUserDao;
import pers.li.aseckill.vo.testVo;

import javax.annotation.Resource;

/**
 * @author:luofeng
 * @createTime : 2018/10/24 13:06
 */
@Service
public class TongJiService {

    @Resource
    ManagerDao dao;
    @Autowired
    SUserDao sUserDao;

    public void select() {
        testVo testVo=new testVo();
        testVo.setName("li2");
        sUserDao.insert(testVo);
    }
}
