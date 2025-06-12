package com.jfbrother.dataCenter.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 教师基本信息JPA对象
 *
 * @author chenyl@jfbrother.com 2022-07-18
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_teacher_base_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@EntityListeners(AuditingEntityListener.class)
public class GsTeacherBaseInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 工号
     */
    private String gh;

    /**
     * 曾用名
     */
    private String cym;

    /**
     * 性别
     */
    private String xb;

    /**
     * 现任职务
     */
    private String xrzwDm;

    /**
     * 出生日期
     */
    private String csrq;

    /**
     * 证件号码
     */
    private String sfzh;

    /**
     * 民族
     */
    private String mzDm;

    /**
     * 手机号
     */
    private String sj;

    /**
     * 是否授课
     */
    private String isTeach;

    /**
     * 是否双师型教师
     */
    private Double isDoubleTeacher;

    /**
     * 部门号
     */
    private String bmh;

    /**
     * 教师类型
     */
    private String type;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 政治面貌代码
     */
    private String zzmmDm;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 办公电话
     */
    private String officePhone;

    /**
     * 联通短号
     */
    private String unicomShortPhone;

    /**
     * 联通长号
     */
    private String unicomPhone;

    /**
     * 移动短号
     */
    private String mobileShortPhone;

    /**
     * 移动长号
     */
    private String mobilePhone;

    /**
     * 人员分类
     */
    private String peopleSort;

    /**
     * 排序号
     */
    private Integer sortNum;

    /**
     * 籍贯
     */
    private String jg;

    /**
     * 宗教信仰
     */
    private String xyzjm;

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