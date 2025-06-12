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
*  学生住宿信息JPA对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_gxxs_stu_dorm")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsGxxsStuDorm implements Serializable {
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
    private String studentCode;

    /**
    * 学生姓名
    */
    private String studentName;

    /**
    * 联系电话
    */
    private String studentPhone;

    /**
    * 床位号
    */
    private Integer bedCode;

    /**
    * 寝室区域
    */
    private String bedroomArea;

    /**
    * 寝室楼号
    */
    private String bedroomLou;

    /**
    * 寝室楼层
    */
    private Integer bedroomFloor;

    /**
    * 寝室名称
    */
    private String bedroomName;

    /**
    * 是否有空调（1：有 ，0：没有）
    */
    private Integer ifAir;

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