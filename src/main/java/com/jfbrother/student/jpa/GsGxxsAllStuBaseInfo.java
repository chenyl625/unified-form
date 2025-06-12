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
 * 所有学生基本数据子类JPA对象
 *
 * @author chenyl@jfbrother.com 2022-08-26
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_gxxs_all_stu_base_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsAllStuBaseInfo implements Serializable {
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
     * 姓名
     */
    private String xm;

    /**
     * 籍贯(市/县)
     */
    private String jgId;

    /**
     * 身份证号
     */
    private String sfzh;

    /**
     * gkksh
     */
    private String gkksh;

    /**
     * 民族代码
     */
    private String mzdmId;

    /**
     * 姓名拼音
     */
    private String xmpy;

    /**
     * 出生日期
     */
    private String csrq;

    /**
     * 学院代码
     */
    private String xydm;

    /**
     * 学院名称
     */
    private String xymc;

    /**
     * 性别
     */
    private String xb;

    /**
     * 生源单位
     */
    private String sydw;

    /**
     * 港澳台代码
     */
    private String gatdmId;

    /**
     * 政治面貌
     */
    private String zzmmId;

    /**
     * 政治面貌名称
     */
    private String zzmmMc;

    /**
     * 班号
     */
    private String bh;

    /**
     * 学制
     */
    private String xz;

    /**
     * 班级名称
     */
    private String bjmc;

    /**
     * 是否在校
     */
    private String sfzx;

    /**
     * 学籍状态
     */
    private String xjzt;

    /**
     * 学籍状态名称
     */
    private String xjztMc;

    /**
     * 专业代码
     */
    private String zydm;

    /**
     * 专业名称
     */
    private String zymc;

    /**
     * 入学年月
     */
    private String rxny;

    /**
     * 入学日期
     */
    private String rxsj;

    /**
     * 现在年级
     */
    private String xznj;

    /**
     * xslb
     */
    private String xslb;

    /**
     * 入学年级
     */
    private String rxnj;

    /**
     * 班级年级
     */
    private String bjnj;

    /**
     * id
     */
    private String id;

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