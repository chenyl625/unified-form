package com.jfbrother.digitalPortraitSnap.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

/**
 *  学生寝室晚归签到JPA对象
 * @author xinz@jfbrother.com 2023-03-09
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_stu_dorm_night_sign")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsStuDormNightSign implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * 主键
     */
    private String etlJfuuid;

    /**
     * 学号
     */
    private String xh;

    /**
     * 签到类别(3:晚归，4:夜不归宿)
     */
    private String type;

    /**
     * 夜检日期
     */
    private String checkTime;

    /**
     * 生成方式(1:夜检员，2:夜检学生扫码，3:自己扫二维码晚归，4:系统自动生成，5:自己登记)
     */
    private String way;

    /**
     * 晚归时间
     */
    private String lateTime;

    /**
     * 检查人员姓名
     */
    private String checkStu;

    /**
     * 检查人员学号
     */
    private String checkStuCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

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