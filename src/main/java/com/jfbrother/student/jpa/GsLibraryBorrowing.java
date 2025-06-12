package com.jfbrother.student.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 在借记录JPA对象
 *
 * @author chenyl@jfbrother.com 2022-09-02
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_library_borrowing")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsLibraryBorrowing implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 借阅id
     */
    private Integer loanId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 读者条码
     */
    private String userBarcode;

    /**
     * 题名
     */
    private String title;

    /**
     * 条形码
     */
    private String tmh;

    /**
     * 所属馆藏name
     */
    private String locationName;

    /**
     * 借出日期
     */
    private String lendDate;

    /**
     * 应还日期
     */
    private String returnDate;

    /**
     * 续借日期
     */
    private String renewDate;

    /**
     * 续借次数
     */
    private Integer renewTimes;

    /**
     * 操作类型\r\n      0表示借阅\r\n      1表示续借\r\n      2表示催还
     */
    private String loanType;

    /**
     * 借阅方式 0普通借阅，1委托借阅，2预约借阅，3pda借阅，4物流到家
     */
    private String loanMode;

    /**
     * 学年
     */
    private String xn;

    /**
     * 业务主键
     */
    private String etlPri;

    /**
     * gp删除标志位 1：删除 0未删除
     */
    private Integer etlIsDelete;

    /**
     * gp是否已完成数据质检标志位 1：是 0：否
     */
    private Integer etlIsChecked;

    /**
     * 校验唯一性
     */
    private String etlMd5;

    /**
     * 多主键表使用
     */
    private String etlKeyMd5;

    /**
     * 变动类型
     */
    private String etlCheckType;

    /**
     * 变动时间
     */
    private String etlCheckDate;


}