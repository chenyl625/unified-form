package com.jfbrother.work.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.work.model.MessageSendingTemplateModel;
import com.jfbrother.work.model.param.MessageSendingTemplateModelParam;
import com.jfbrother.work.model.extend.MessageSendingTemplateModelExtend;
import com.jfbrother.work.model.search.MessageSendingTemplateModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
* 消息发送模版表Service接口
* @author chenyl@jfbrother.com 2022-12-15
*/
public interface MessageSendingTemplateService extends BaseService{
    /**
    * 消息发送模版表添加
    *
    * @param model 数据
    * @return
    */
    MessageSendingTemplateModelExtend post(MessageSendingTemplateModelParam model);

    /**
    * 消息发送模版表更新
    *
    * @param id        主键id
    * @param model 数据
    * @return
    */
    MessageSendingTemplateModelExtend put(String id, MessageSendingTemplateModelParam model);

    /**
    * 消息发送模版表部分更新
    *
    * @param id         主键id
    * @param model 数据
    * @return
    */
    MessageSendingTemplateModelExtend patch(String id, MessageSendingTemplateModelParam model);

    /**
    * 根据主键id获取数据
    *
    * @param id 主键id
    * @return
    */
    MessageSendingTemplateModelExtend get(String id);

    /**
    * 根据查询条件和分页条件（包括排序条件），查询数据
    *
    * @param search   查询条件
    * @param pageable 分页条件
    * @return
    */
    PageOb<MessageSendingTemplateModelExtend> get(MessageSendingTemplateModelSearch search, Pageable pageable);

    /**
    * 根据查询条件和排序条件，查询数据
    *
    * @param search 查询条件
    * @param sort   分页条件
    * @return
    */
    List<MessageSendingTemplateModelExtend> get(MessageSendingTemplateModelSearch search, Sort sort);

    /**
    * 根据ids删除数据
    *
    * @param ids id列表
    */
    void delete(List<String> ids);

    /**
    * 根据ids逻辑删除数据
    *
    * @param ids id列表
    */
    void deleteLogical(List<String> ids);
}
