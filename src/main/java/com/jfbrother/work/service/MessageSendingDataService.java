package com.jfbrother.work.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.work.model.MessageSendingDataModel;
import com.jfbrother.work.model.param.MessageSendingDataModelParam;
import com.jfbrother.work.model.extend.MessageSendingDataModelExtend;
import com.jfbrother.work.model.search.MessageSendingDataModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
* 消息发送数据管理表Service接口
* @author chenyl@jfbrother.com 2022-12-15
*/
public interface MessageSendingDataService extends BaseService{
    /**
    * 消息发送数据管理表添加
    *
    * @param model 数据
    * @return
    */
    MessageSendingDataModelExtend post(MessageSendingDataModelParam model);

    /**
    * 消息发送数据管理表更新
    *
    * @param id        主键id
    * @param model 数据
    * @return
    */
    MessageSendingDataModelExtend put(String id, MessageSendingDataModelParam model);

    /**
    * 消息发送数据管理表部分更新
    *
    * @param id         主键id
    * @param model 数据
    * @return
    */
    MessageSendingDataModelExtend patch(String id, MessageSendingDataModelParam model);

    /**
    * 根据主键id获取数据
    *
    * @param id 主键id
    * @return
    */
    MessageSendingDataModelExtend get(String id);

    /**
    * 根据查询条件和分页条件（包括排序条件），查询数据
    *
    * @param search   查询条件
    * @param pageable 分页条件
    * @return
    */
    PageOb<MessageSendingDataModelExtend> get(MessageSendingDataModelSearch search, Pageable pageable);

    /**
    * 根据查询条件和排序条件，查询数据
    *
    * @param search 查询条件
    * @param sort   分页条件
    * @return
    */
    List<MessageSendingDataModelExtend> get(MessageSendingDataModelSearch search, Sort sort);

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

    void sendMsgByGh(String gh,String matchingKey, Map<String, Object> paramMap);
}
