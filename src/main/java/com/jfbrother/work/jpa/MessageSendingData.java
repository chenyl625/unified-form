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
*  消息发送数据管理表JPA对象
* @author chenyl@jfbrother.com 2022-12-15
*/
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name="message_sending_data")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class MessageSendingData extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 发送人
    */
    private String sendUser;

    /**
    * 接受人工号
    */
    private String receiveGh;

    /**
    * 接受人手机号
    */
    private String receivePhone;

    /**
    * 消息内容（正文）
    */
    private String messageContent;

    /**
    * 所属模版
    */
    private String belongTemplate;

    /**
    * 所属表单发布id
    */
    private String belongReleaseId;

    /**
     * 所属填报id
     */
    private String belongFillId;

    /**
    * 发送状态(0-失败，1-成功)
    */
    private Integer sendStatus;

    /**
    * 发送时间
    */
    private Long sendDateTime;

    /**
    * 接收人的微信userid
    */
    private String wechatUserId;

    /**
    * 消息完整对象
    */
    private String content;

    /**
    * 消息类型（1.短信 2.微信消息）
    */
    private Integer type;

    /**
    * 排序号
    */
    private Integer sortNum;


}