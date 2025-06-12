package com.jfbrother.connector.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.connector.model.extend.ConnectorInfoModelExtend;
import com.jfbrother.connector.model.param.ConnectorInfoModelParam;
import com.jfbrother.connector.model.search.ConnectorInfoModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * 接口管理Service接口
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
public interface ConnectorInfoService extends BaseService {
    /**
     * 接口管理添加
     *
     * @param model 数据
     * @return
     */
    ConnectorInfoModelExtend post(ConnectorInfoModelParam model);

    /**
     * 接口管理更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    ConnectorInfoModelExtend put(String id, ConnectorInfoModelParam model);

    /**
     * 接口管理部分更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    ConnectorInfoModelExtend patch(String id, ConnectorInfoModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    ConnectorInfoModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<ConnectorInfoModelExtend> get(ConnectorInfoModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<ConnectorInfoModelExtend> get(ConnectorInfoModelSearch search, Sort sort);

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
     * 接口字段获取
     *
     * @param id
     * @return
     */
    JSONObject getField(String id);

    /**
     * 获取接口数据
     *
     * @param id    接口id
     * @param param 参数
     * @return
     */
    JSONArray getData(String id, Map<String, Object> param);

    /**
     * 获取接口数据字典信息
     *
     * @param apiUrl
     * @return
     */
    Map<String, Map<String, String>> getCommonDictMap(String apiUrl);
}
