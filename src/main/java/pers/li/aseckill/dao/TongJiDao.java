package pers.li.aseckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.li.aseckill.vo.TongJiVo;

import java.util.List;
import java.util.Map;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:26
 * 统计预约数，邮件自动发送
 */
@Mapper
public interface TongJiDao {

    @Select("SELECT * from yuyue_record where 1=1")
    List<TongJiVo> getAll();

    @Select("SELECT * from yuyue_record where id> #{id} ")
    List<TongJiVo> getAll2(@Param("id") int id);

    @Select("select mail from send_mail")
    List<String>  getmails();
}
