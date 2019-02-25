package pers.li.aseckill.dao;

import org.apache.ibatis.annotations.*;
import pers.li.aseckill.entity.SMenu;
import pers.li.aseckill.entity.SUser;
import pers.li.aseckill.vo.testVo;

import java.util.List;

/**
 * @author:luofeng
 * @createTime : 2018/10/10 9:26
 */
@Mapper
public interface SUserDao {

    @Select("select * from s_user where id=#{id}")
    SUser getUserById(@Param("id") long mobile);

    @Update("update s_user set password=#{password} where id = #{id}")
    void update(SUser toBeUpdate);

    @Insert("insert INTO test(`name`) VALUES (#{testVo.name})")
    @Options(useGeneratedKeys = true, keyProperty = "testVo.id")
    void insert(@Param("testVo") testVo vo);

    @Select("select * from test where id=#{id}")
    testVo getTestById(@Param("id") Integer id);

    @Select("select * from s_menu where status=1")
    List<SMenu> getMenu();


}
