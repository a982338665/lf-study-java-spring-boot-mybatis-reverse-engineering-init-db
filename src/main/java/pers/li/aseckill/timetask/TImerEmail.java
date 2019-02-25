package pers.li.aseckill.timetask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pers.li.aseckill.utils.SendMailUtil;
import pers.li.aseckill.vo.TongJiVo;
import pers.li.aseckill.web.LoginController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author:luofeng
 * @createTime : 2018/10/18 15:31
 */
//@Component
public class TImerEmail {

    private static Logger log= LoggerFactory.getLogger(LoginController.class);
    private ConcurrentMap<String,String> map=new ConcurrentHashMap<String,String>();

    /**
     * 两分钟发一次
     */
//    @Scheduled(initialDelay = 1000, fixedDelay=120000)
    public void updateAjax() {
        log.info("-------------------------->开始进行后台数据更新----->");
        List<TongJiVo> list=new ArrayList();
        String id=map.get("last");
        List<String> getmails = getAll(list, id);

        if(list.size()>0&&id!=null){
            for (TongJiVo s:list
                    ) {
                StringBuffer stringBuffer=new StringBuffer("");
                stringBuffer.append("预约类型:"+s.getServerType()+"\r\n");
                stringBuffer.append("医生姓名:"+s.getDoctorName()+"\r\n");
                stringBuffer.append("订单号:"+s.getOrderNum()+"\r\n");
                stringBuffer.append("患者姓名:"+s.getPatientName()+"\r\n");
                stringBuffer.append("患者性别:"+s.getPatientSex()+"\r\n");
                stringBuffer.append("患者年龄:"+s.getPatientAge()+"\r\n");
                stringBuffer.append("患者电话:"+s.getPatientMobile()+"\r\n");
                stringBuffer.append("患者疾病:"+s.getPatientDiease()+"\r\n");
                stringBuffer.append("病情描述:"+s.getPatientDesc()+"\r\n");
                stringBuffer.append("预约时间:"+s.getOrderTime()+"\r\n");
                try {
                    SendMailUtil.send(stringBuffer.toString(),getmails);
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        if(list.size()>0){
            map.remove("last");
            map.put("last", list.get(list.size() - 1).getId());
        }

    }

    /**
     * 间隔30秒检测修改记录最后id,一分钟检测
     */
//    @Scheduled(initialDelay = 1000, fixedDelay=60000)
    public void Listener() throws ClassNotFoundException, SQLException {
        log.info("-------------------------->开始检测是否有预约用户----->");
        //首次判断时候查询最后一条记录 包含lastId
        String id = map.get("id");
        if(id==null){
            id="175";
            map.put("id",id);
        }

        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://114.55.214.122:3306/skk_srv_order_online?"
                + "useUnicode=true&characterEncoding=UTF8";
        String user="jk_skk_online";
        String pass="skk_online@521";
        Class.forName(driver);
        Connection conn= DriverManager.getConnection(url,user,pass);
        PreparedStatement pstmt;
        //查询最后一条记录放入map
        String querySql="SELECT id from tbl_appointment_order_detail ORDER BY id desc limit 1";
        pstmt = conn.prepareStatement(querySql);
        ResultSet rs = pstmt.executeQuery();
        String lastId="";
        while (rs.next()) {
            lastId= rs.getString("id");
        }
        if(!id.equals(lastId)){
            querySql="UPDATE tbl_appointment_order_detail SET CREATE_time=create_time where id >"+id;
            pstmt = conn.prepareStatement(querySql);
            pstmt.executeUpdate();
            map.remove("id");
            map.put("id",lastId);
        }
    }



    private List<String> getAll(List<TongJiVo> list,String id) {
        List<String> getmails = new ArrayList<>();
        try{
            String driver="com.mysql.jdbc.Driver";
            String url="jdbc:mysql://114.55.214.122:3306/skk_srv_order_online?"
                    + "useUnicode=true&characterEncoding=UTF8";
            String user="jk_skk_online";
            String pass="skk_online@521";
            Class.forName(driver);
            Connection conn= DriverManager.getConnection(url,user,pass);
            Statement stmt=conn.createStatement();
            String querySql="SELECT * from yuyue_record";
            if(id!=null){
                querySql=querySql+" where id>"+id;
            }
            ResultSet rs=stmt.executeQuery(querySql);
            while(rs.next()){
                String string = rs.getString("id");
                String string1 = rs.getString("server_type");
                String string2 = rs.getString("doctor_name");
                String string3= rs.getString("order_num");
                String string4 = rs.getString("patient_name");
                String string5 = rs.getString("patient_sex");
                String string6 = rs.getString("patient_age");
                String string7 = rs.getString("patient_mobile");
                String string8 = rs.getString("patient_desc");
                String string9 = rs.getString("order_time");
                String string10 = rs.getString("patient_diease");
                TongJiVo tongJiVo=new TongJiVo();
                tongJiVo.setId(string);
                tongJiVo.setServerType(string1);
                tongJiVo.setDoctorName(string2);
                tongJiVo.setOrderNum(string3);
                tongJiVo.setPatientName(string4);
                tongJiVo.setPatientSex(string5);
                tongJiVo.setPatientAge(string6);
                tongJiVo.setPatientMobile(string7);
                tongJiVo.setPatientDesc(string8);
                tongJiVo.setOrderTime(string9);
                tongJiVo.setPatientDiease(string10);
                list.add(tongJiVo);
            }
            querySql="select mail from send_mail";
            rs=stmt.executeQuery(querySql);
            while(rs.next()){
                String string = rs.getString("mail");
                getmails.add(string);
            }
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return getmails;
    }


}
