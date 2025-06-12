package com.jfbrother.work.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.work.jpa.MessageSendingTemplate;
import org.springframework.stereotype.Repository;

/**
*  消息发送模版表数据库操作对象
* @author chenyl@jfbrother.com 2022-12-15
*/
@Repository
public interface MessageSendingTemplateRepository extends BaseRepository<MessageSendingTemplate> {

}
