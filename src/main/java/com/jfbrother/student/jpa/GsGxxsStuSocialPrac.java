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
import java.math.BigDecimal;

/**
*  学生社会实践JPA对象
* @author chenyl@jfbrother.com 2022-08-30
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_gxxs_stu_social_prac")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsStuSocialPrac implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
    * id
    */
    private String etlJfuuid;

    /**
    * 学号
    */
    private String xh;

    /**
    * 年级
    */
    private String nj;

    /**
    * 班级名称
    */
    private String bjmc;

    /**
    * 参与时长（分）
    */
    private Long participateMinute;

    /**
    * 活动分类
    */
    private String type;

    /**
    * 获取原因
    */
    private String acquireReason;

    /**
    * 所属院系
    */
    private String ssyx;

    /**
    * 是否有效
    */
    private String isEnable;

    /**
    * 学分类型
    */
    private String scoreType;

    /**
    * 年级简称
    */
    private String subNj;

    /**
    * 班级代码
    */
    private String bjdm;

    /**
    * 班级名称简称
    */
    private String subBjmc;

    /**
    * 开始时间
    */
    private String startDate;

    /**
    * 结束时间
    */
    private String endDate;

    /**
    * 获取时间
    */
    private String acquireDate;

    /**
    * 获得数量
    */
    private BigDecimal acquireNum;

    /**
    * 单位
    */
    private String unit;

    /**
    * 导入的类型（1-学分，2-志愿小时）
    */
    private String importType;

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