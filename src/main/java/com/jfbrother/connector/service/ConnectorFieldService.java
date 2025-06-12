package com.jfbrother.connector.service;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.connector.model.extend.ConnectorFieldModelExtend;
import com.jfbrother.connector.model.param.ConnectorFieldModelParam;
import com.jfbrother.connector.model.search.ConnectorFieldModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * 接口字段Service接口
 *
 * @author chenyl@jfbrother.com 2022-07-20
 */
public interface ConnectorFieldService extends BaseService {
    /**
     * 接口字段添加
     *
     * @param model 数据
     * @return
     */
    ConnectorFieldModelExtend post(ConnectorFieldModelParam model);

    /**
     * 接口字段更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    ConnectorFieldModelExtend put(String id, ConnectorFieldModelParam model);

    /**
     * 接口字段部分更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    ConnectorFieldModelExtend patch(String id, ConnectorFieldModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    ConnectorFieldModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<ConnectorFieldModelExtend> get(ConnectorFieldModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<ConnectorFieldModelExtend> get(ConnectorFieldModelSearch search, Sort sort);

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

    /**
     * 批量保存接口字段
     *
     * @param connId
     * @param list
     */
    void saveAll(String connId, List<ConnectorFieldModelParam> list);

    /**
     * 根据接口id获取字段
     *
     * @param connId
     * @return
     */
    Map<String, Map<String, Object>> getByConnId(String connId);
}
