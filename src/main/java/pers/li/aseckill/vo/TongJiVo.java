package pers.li.aseckill.vo;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import pers.li.aseckill.validator.IsMobile;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author:luofeng
 * @createTime : 2018/10/11 9:23
 * controller层数据传递
 * @Select("SELECT `预约类型`  servertype,\n" +
"\t`医生姓名` doctorname,\n" +
"\t`订单号` ordernum,\n" +
"\t`患者姓名` patientname,\n" +
"\t`患者性别` patientsex,\n" +
"\t`患者年龄` patientage,\n" +
"\t`患者联系电话` patientmobile,\n" +
"\t`患者所患疾病` patientdiease,\n" +
"\t`患者病情描述` patientdesc,\n" +
"\t`预约时间` ordertime\n" +
"\n" +
"from yuyue_record ")
 */
@Data
@ToString
public class TongJiVo {

    private String id;
    private String serverType;
    private String doctorName;
    private String orderNum;
    private String patientName;
    private String patientSex;
    private String patientAge;
    private String patientMobile;
    private String patientDiease;
    private String patientDesc;
    private String orderTime;



}

