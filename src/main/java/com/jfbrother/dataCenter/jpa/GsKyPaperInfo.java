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
*  科研论文信息JPA对象
* @author chenyl@jfbrother.com 2022-07-27
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="gs_ky_paper_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GsKyPaperInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    /**
    * id
    */
    private String etlJfuuid;

    /**
    * unit_id
    */
    private String unitId;

    /**
    * 单位代码
    */
    private String unitCode;

    /**
    * 单位名称
    */
    private String unitName;

    /**
    * 论文名称
    */
    private String name;

    /**
    * publish_date
    */
    private String publishDate;

    /**
    * subject_class_id
    */
    private String subjectClassId;

    /**
    * 科目分类名称
    */
    private String subjectClassName;

    /**
    * 学科代码
    */
    private String subjectCode;

    /**
    * 科目名称
    */
    private String subjectName;

    /**
    * paper_mode_id
    */
    private String paperModeId;

    /**
    * 论文类型
    */
    private String paperModeName;

    /**
    * publish_unit
    */
    private String publishUnit;

    /**
    * word_number
    */
    private BigDecimal wordNumber;

    /**
    * author_number
    */
    private Integer authorNumber;

    /**
    * publish_range_id
    */
    private String publishRangeId;

    /**
    * publish_range
    */
    private String publishRange;

    /**
    * project_source_id
    */
    private String projectSourceId;

    /**
    * 项目来源
    */
    private String projectSourceName;

    /**
    * issn
    */
    private String issn;

    /**
    * product_abstract
    */
    private String productAbstract;

    /**
    * first_author_name
    */
    private String firstAuthorName;

    /**
    * first_author_code
    */
    private String firstAuthorCode;

    /**
    * 所有作者
    */
    private String members;

    /**
    * check_status_id
    */
    private String checkStatusId;

    /**
    * 审核状态
    */
    private String checkStatusName;

    /**
    * operate_time
    */
    private String operateTime;

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