package com.jfbrother.dataCenter.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.dataCenter.model.extend.DataDictionaryModelExtend;
import com.jfbrother.dataCenter.model.param.DataDictionaryModelParam;
import com.jfbrother.dataCenter.model.search.DataDictionaryModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 数据字典表Service接口
 *
 * @author chenyl@jfbrother.com 2022-09-01
 */
public interface DataDictionaryService extends BaseService {
    /**
     * 数据字典表添加
     *
     * @param model 数据
     * @return
     */
    DataDictionaryModelExtend post(DataDictionaryModelParam model);

    /**
     * 数据字典表更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    DataDictionaryModelExtend put(String id, DataDictionaryModelParam model);

    /**
     * 数据字典表部分更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    DataDictionaryModelExtend patch(String id, DataDictionaryModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    DataDictionaryModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<DataDictionaryModelExtend> get(DataDictionaryModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<DataDictionaryModelExtend> get(DataDictionaryModelSearch search, Sort sort);

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
     * 同步标准数据
     */
    void syncStandardData();
}
