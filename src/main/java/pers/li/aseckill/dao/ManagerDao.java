package pers.li.aseckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.li.aseckill.vo.TongJiVo;

import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:26
 * 添加服务
 */
@Mapper
public interface ManagerDao {

    @Select("SELECT * from yuyue_record where 1=1")
    List<TongJiVo> getAll();

    @Select("SELECT * from yuyue_record where id> #{id} ")
    List<TongJiVo> getAll2(@Param("id") int id);

    @Select("select mail from send_mail")
    List<String>  getmails();

    @Select("select CONCAT(m.id,' ',m.NICK_NAME,' ',d.HOSPITAL_NAME,' ',d.DEPT_NAME) as info from tbl_member m" +
            " LEFT JOIN tbl_doctor d on d.ID=m.ID where nick_NAME like #{doctor}")
    List<String> getBYName(@Param("doctor") String doctor);

    @Select("SELECT CONCAT(DEPT_CODE,\"_\",DEPT_NAME) as dept from tbl_doctor where HOSPITAL_CODE=#{s} GROUP BY DEPT_CODE")
    List<String> getDeptBYName(String s);

    @Select("SELECT CONCAT(id,\"_\",name)  from tbl_hospital where name like #{s}")
    List<String> getHospitalBYName(@Param("s")String s);


}
