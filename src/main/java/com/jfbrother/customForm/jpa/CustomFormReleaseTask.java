package com.jfbrother.customForm.jpa;

import com.jfbrother.baseserver.jpa.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
*  自定义表单发布环节JPA对象
* @author chenyl@jfbrother.com 2022-07-04
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="custom_form_release_task")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CustomFormReleaseTask extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 发布id
    */
    private String releaseId;

    /**
    * 环节标识
    */
    private String taskIdentify;

    /**
    * 环节标识名称
    */
    private String taskIdentifyName;

    /**
    * 排序号
    */
    private Integer sortNum;


}