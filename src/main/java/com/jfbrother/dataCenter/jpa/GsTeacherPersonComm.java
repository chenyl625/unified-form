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

/**
*  教师个人通讯方式JPA对象
* @author chenyl@jfbrother.com 2022-07-26
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_teacher_person_comm")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsTeacherPersonComm implements Serializable {
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
    * 电子邮箱
    */
    private String email;

    /**
    * 办公电话
    */
    private String bgPhone;

    /**
    * 手机号
    */
    private String telephone;

    /**
    * 联通长号
    */
    private String ltLong;

    /**
    * 联通短号
    */
    private String ltShort;

    /**
    * 移动长号
    */
    private String ydLong;

    /**
    * 移动短号
    */
    private String ydShort;

    /**
    * qq
    */
    private String qq;

    /**
    * 联系地址
    */
    private String address;

    /**
     * 主键
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