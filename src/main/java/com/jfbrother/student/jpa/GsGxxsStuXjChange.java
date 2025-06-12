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
*  学生学籍异动最新记录JPA对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_gxxs_stu_xj_change")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsStuXjChange implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
    * id
    */
    private String etlJfuuid;

    /**
    * 学年
    */
    private String xn;

    /**
    * 学号
    */
    private String xh;

    /**
    * user_xh
    */
    private String userXh;

    /**
    * 异动类别
    */
    private String changeTypeCode;

    /**
    * 异动类别名称
    */
    private String changeTypeName;

    /**
    * 异动时间
    */
    private String changeTime;

    /**
    * 异动原因
    */
    private String changeReasonCode;

    /**
    * 确认时间
    */
    private String confirmTime;

    /**
    * 异动文号
    */
    private String changeNumber;

    /**
    * 异动说明
    */
    private String changeExplain;

    /**
    * 原年级
    */
    private String oldnj;

    /**
    * 原院系
    */
    private String oldyx;

    /**
    * 原专业
    */
    private String oldzy;

    /**
    * 原班级
    */
    private String oldbj;

    /**
    * 异动到年级
    */
    private String yddnj;

    /**
    * 异动到院系
    */
    private String yddyx;

    /**
    * 异动到专业
    */
    private String yddzy;

    /**
    * 异动到班级
    */
    private String yddbj;

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