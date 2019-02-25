package pers.li.aseckill.utils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


@SuppressWarnings("all")
public class TestSendAAAAAAAAAAAAAAAAAA {

    public  static void   send() throws MessagingException, UnsupportedEncodingException {
        Map<String,String> map= new HashMap<String,String>();
        SendMail mail = new SendMail(PropertiesUtils.getStaticProperty("mail_username"),PropertiesUtils.getStaticProperty("mail_password"));

        //map.put("mail.smtp.host", "imap.exmail.qq.com");
        map.put("mail.smtp.host",PropertiesUtils.getStaticProperty("mail_smtp_host"));

        //暂时未成功，需要调试
        map.put("mail.smtp.auth", "true");
        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        map.put("mail.smtp.port", "465");
        map.put("mail.smtp.socketFactory.port", "465");
        mail.setPros(map);
        mail.initMessage();
        /*
         * 添加收件人有三种方法：
         * 1,单人添加(单人发送)调用setRecipient(str);发送String类型
         * 2,多人添加(群发)调用setRecipients(list);发送list集合类型
         * 3,多人添加(群发)调用setRecipients(sb);发送StringBuffer类型
         */
        List<String> list = new ArrayList<String>();
//        //list.add("2661783058@qq.com");
//        //邮箱
        list.add("982338665@qq.com");
//        list.add("lishengbo@9khealthy.com");
        //list.add("****@163.com");
        mail.setRecipients(list);
        /*String defaultStr = "283942930@qq.com,429353942@qq.com,2355663819@qq.com,381766286@qq.com;
        StringBuffer sb = new StringBuffer();
        sb.append(defaultStr);
        sb.append(",316121113@qq.com");
        mail.setRecipients(sb);*/
        //======================================
        //主题名称
        mail.setSubject("预约患者名单推送");//mail.setText("谢谢合作,hello");
        mail.setText("bacouo");
        //发件时间
        mail.setDate(new Date());
        //发件人名称
        mail.setFrom(PropertiesUtils.getStaticProperty("mail_send_name"));
        //发送附件所在的位置
//        mail.setMultipart("D:/aaaaaaaaaaaaa/48.mp4");
        //正文内容--自动识别html
//        mail.setContent(html.content(), "text/html; charset=UTF-8");
        //纯文本内容发送--text/plain
//        mail.setContent(html.content(), "text/plain; charset=UTF-8");
//        List<String> fileList = new ArrayList<String>();
//        fileList.add("D:/aaaa——sql脚本/t_user_info1.sql");
//        fileList.add("D:/aaaa——sql脚本/t_user_info2.sql");
//        fileList.add("D:/aaaa——sql脚本/t_user_info3.sql");
//        fileList.add("D:/aaaa——sql脚本/t_user_info.sql");
//        mail.setMultiparts(fileList);
        System.out.println(mail.sendMessage());
    }

    public static void main(String[] args) throws MessagingException, IOException
    {

        Map<String,String> map= new HashMap<String,String>();
        SendMail mail = new SendMail(PropertiesUtils.getStaticProperty("mail_username"),PropertiesUtils.getStaticProperty("mail_password"));

        //map.put("mail.smtp.host", "imap.exmail.qq.com");
        map.put("mail.smtp.host",PropertiesUtils.getStaticProperty("mail_smtp_host"));

        //暂时未成功，需要调试
        map.put("mail.smtp.auth", "true");
        map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        map.put("mail.smtp.port", "465");
        map.put("mail.smtp.socketFactory.port", "465");
        mail.setPros(map);
        mail.initMessage();
        /*
         * 添加收件人有三种方法：
         * 1,单人添加(单人发送)调用setRecipient(str);发送String类型
         * 2,多人添加(群发)调用setRecipients(list);发送list集合类型
         * 3,多人添加(群发)调用setRecipients(sb);发送StringBuffer类型
         */
        List<String> list = new ArrayList<String>();
        //list.add("2661783058@qq.com");
        //邮箱
        list.add("982338665@qq.com");
        list.add("lishengbo@9khealthy.com");
        //list.add("****@163.com");
        mail.setRecipients(list);
        /*String defaultStr = "283942930@qq.com,429353942@qq.com,2355663819@qq.com,381766286@qq.com;
        StringBuffer sb = new StringBuffer();
        sb.append(defaultStr);
        sb.append(",316121113@qq.com");
        mail.setRecipients(sb);*/
        //======================================
        //主题名称
        mail.setSubject("预约患者名单推送");//mail.setText("谢谢合作,hello");
        mail.setText("xinl");
        //发件时间
        mail.setDate(new Date());
        //发件人名称
        mail.setFrom(PropertiesUtils.getStaticProperty("mail_send_name"));
        //发送附件所在的位置
//        mail.setMultipart("D:/aaaaaaaaaaaaa/48.mp4");
        //正文内容--自动识别html
//        mail.setContent(html.content(), "text/html; charset=UTF-8");
        //纯文本内容发送--text/plain
//        mail.setContent(html.content(), "text/plain; charset=UTF-8");
//        List<String> fileList = new ArrayList<String>();
//        fileList.add("D:/aaaa——sql脚本/t_user_info1.sql");
//        fileList.add("D:/aaaa——sql脚本/t_user_info2.sql");
//        fileList.add("D:/aaaa——sql脚本/t_user_info3.sql");
//        fileList.add("D:/aaaa——sql脚本/t_user_info.sql");
//        mail.setMultiparts(fileList);
        System.out.println(mail.sendMessage());
    }

}