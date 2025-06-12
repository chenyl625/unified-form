package com.jfbrother.dataCenter.jpa;

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
 * 教师全部信息JPA对象
 *
 * @author chenyl@jfbrother.com 2022-07-19
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "gs_teacher_all_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsTeacherAllInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
     * id
     */
    private String etlJfuuid;

    /**
     * 工号
     */
    private String gh;

    /**
     * 教师类别
     */
    private String rsType;

    /**
     * 姓名
     */
    private String name;

    /**
     * 曾用名
     */
    private String cym;

    /**
     * 拼音简码
     */
    private String pinyin;

    /**
     * 性别
     */
    private String sex;

    /**
     * 国籍
     */
    private String gj;

    /**
     * 身份证类别
     */
    private String sfzType;

    /**
     * 证件号码
     */
    private String sfzh;

    /**
     * 出生日期
     */
    private String birth;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 籍贯
     */
    private String jg;

    /**
     * 出生地
     */
    private String birthPlace;

    /**
     * 现户籍
     */
    private String nowHj;

    /**
     * 民族
     */
    private String nationName;

    /**
     * 最高学历
     */
    private String topEdu;

    /**
     * 最高学位
     */
    private String topDegree;

    /**
     * 政治面貌
     */
    private String zzmmName;

    /**
     * 婚姻状况
     */
    private String hyzk;

    /**
     * 宗教信仰
     */
    private String belief;

    /**
     * 毕业学校
     */
    private String byxx;

    /**
     * 毕业专业
     */
    private String byzy;

    /**
     * 是否双师型教师
     */
    private String isDouble;

    /**
     * 所属部门
     */
    private String deptName;

    /**
     * 中共党员填写入党时间
     */
    private String partyTime;

    /**
     * 办公电话
     */
    private String oficalPhone;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 参加工作时间
     */
    private String workTime;

    /**
     * 进校时间
     */
    private String schoolTime;

    /**
     * 系数
     */
    private BigDecimal coefficient;

    /**
     * 合同起始时间
     */
    private String conStartTime;

    /**
     * 合同终止时间
     */
    private String conEndTime;

    /**
     * 聘任时间
     */
    private String employTime;

    /**
     * 岗位类别
     */
    private String positionType;

    /**
     * 岗位等级
     */
    private String positionLevel;

    /**
     * 签订合同情况
     */
    private String conStatus;

    /**
     * 教职工来源
     */
    private String teaSource;

    /**
     * 教职工类别
     */
    private String teaType;

    /**
     * 用工类别
     */
    private String employType;

    /**
     * 专业技术职务
     */
    private String techPosition;

    /**
     * 现任职务
     */
    private String curPosition;

    /**
     * 进校时的学历
     */
    private String schoolEdu;

    /**
     * 进校时的学位
     */
    private String schoolDegree;

    /**
     * 是否有职业资格
     */
    private String isQuali;

    /**
     * 是否授课
     */
    private String isTeach;

    /**
     * 授课类别
     */
    private String teachType;

    /**
     * 是否有海外留学经历
     */
    private String isOverStudy;

    /**
     * 联通长号
     */
    private String unicomPhone;

    /**
     * 联通短号
     */
    private String unicomShortPhone;

    /**
     * 移动手机长号
     */
    private String mobilePhone;

    /**
     * 移动短号
     */
    private String mobileShortPhone;

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