package com.jfbrother.work.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jfbrother.baseserver.jpa.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

/**
*  消息发送模版表JPA对象
* @author chenyl@jfbrother.com 2022-12-15
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="message_sending_template")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class MessageSendingTemplate extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 模版名称
    */
    private String templateName;

    /**
    * 模版内容
    */
    private String templateContent;

    /**
    * 匹配的key
    */
    private String matchingKey;

    /**
    * 发送的对象（角色）
    */
    private String sendObj;

    /**
    * 类型（1.短信 2.微信消息）
    */
    private Integer type;

    /**
    * 是否开启（0.否 1.是）
    */
    private Integer isOpen;

    /**
    * 排序号
    */
    private Integer sortNum;


}