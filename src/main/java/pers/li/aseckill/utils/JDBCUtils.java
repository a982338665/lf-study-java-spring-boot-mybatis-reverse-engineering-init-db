package pers.li.aseckill.utils;

import java.sql.*;

/**
 * @author:luofeng
 * @createTime : 2018/10/19 13:29
 */
@SuppressWarnings("all")
public class JDBCUtils {

    public static final String driver="com.mysql.jdbc.Driver";
    public static final String url_skk_srv_order_online="jdbc:mysql://114.55.214.122:3306/skk_srv_order_online?"
                                        + "useUnicode=true&characterEncoding=UTF8";
    public static final String url_skk_mem_member_online="jdbc:mysql://114.55.214.122:3306/skk_mem_member_online?"
                                        + "useUnicode=true&characterEncoding=UTF8";
    public static final String url_skk_bdt_hdd_online="jdbc:mysql://114.55.214.122:3306/skk_bdt_hdd_online?"
                                        + "useUnicode=true&characterEncoding=UTF8";
    public static final String user="jk_skk_online";
    public static final String pass="skk_online@521";

     private static Connection conn= null;
     private static Statement stmt=null;
     private static ResultSet rs=null;

    public  static ResultSet select(String url,String sql){
        try{
//            String querySql="SELECT * from yuyue_record";
            Class.forName(driver);
            conn= DriverManager.getConnection(url,user,pass);
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            return  rs;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void  close(){
        if(rs !=null){
            try{
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(stmt !=null){
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(conn !=null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
