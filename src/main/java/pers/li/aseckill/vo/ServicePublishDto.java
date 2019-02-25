package pers.li.aseckill.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 服务发布
 */
@Getter
@Setter
@ToString
public class ServicePublishDto {

    /** 医生 id  not-null */
    private String doctor;

    /** 医院 code not-null */
    private String hospital;

    /** 科室 id not-null */
    private String hospitalDept;

    /**
     * 发布类型 --- 服务类型 not-null
     * <p>
     * 引用 {@link serviceType} code  ---> 入库
     */
    private String serviceType;

    /** 总坐席数 */
    private String totalSeat;

    /** 价格 */
    private String price;

    /** 开始时间 */
    private String startTime;

    /** 结束时间  not-null */
    private String endTime;
    /** 0测试 1正式*/
    private String envType;

}
