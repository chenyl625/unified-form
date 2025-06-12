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
*  教务系统里的在校生JPA对象
* @author chenyl@jfbrother.com 2022-08-26
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_gxxs_stu_base_entire")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsStuBaseEntire implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
    * id
    */
    private String etlJfuuid;

    /**
    * 班级代码
    */
    private String ssbjId;

    /**
    * 姓名
    */
    private String xm;

    /**
    * 性别
    */
    private String xb;

    /**
    * 身份证号
    */
    private String sfzh;

    /**
    * 出生日期
    */
    private String csrq;

    /**
    * 民族代码
    */
    private String mzdmId;

    /**
    * 籍贯(市/县)
    */
    private String jgId;

    /**
    * 生源省份
    */
    private String sysfId;

    /**
    * 班级名称
    */
    private String bjmc;

    /**
    * 所属院系
    */
    private String yxId;

    /**
    * 所属专业
    */
    private String zyId;

    /**
    * 学制
    */
    private String xz;

    /**
    * 年级
    */
    private Integer nj;

    /**
    * 学号
    */
    private String xh;

    /**
    * 入学年级
    */
    private String rxnj;

    /**
    * 入学日期
    */
    private String rxsj;

    /**
    * 联系电话
    */
    private String studentPhone;

    /**
    * 政治面貌
    */
    private String zzmmId;

    /**
    * 学籍状态
    */
    private String xjzt;

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