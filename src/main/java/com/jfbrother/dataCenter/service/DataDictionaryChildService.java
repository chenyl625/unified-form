package com.jfbrother.dataCenter.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.dataCenter.model.DataDictionaryChildModel;
import com.jfbrother.dataCenter.model.param.DataDictionaryChildModelParam;
import com.jfbrother.dataCenter.model.extend.DataDictionaryChildModelExtend;
import com.jfbrother.dataCenter.model.search.DataDictionaryChildModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
* 数据字典子表Service接口
* @author chenyl@jfbrother.com 2022-09-01
*/
public interface DataDictionaryChildService extends BaseService{
    /**
    * 数据字典子表添加
    *
    * @param model 数据
    * @return
    */
    DataDictionaryChildModelExtend post(DataDictionaryChildModelParam model);

    /**
    * 数据字典子表更新
    *
    * @param id        主键id
    * @param model 数据
    * @return
    */
    DataDictionaryChildModelExtend put(String id, DataDictionaryChildModelParam model);

    /**
    * 数据字典子表部分更新
    *
    * @param id         主键id
    * @param model 数据
    * @return
    */
    DataDictionaryChildModelExtend patch(String id, DataDictionaryChildModelParam model);

    /**
    * 根据主键id获取数据
    *
    * @param id 主键id
    * @return
    */
    DataDictionaryChildModelExtend get(String id);

    /**
    * 根据查询条件和分页条件（包括排序条件），查询数据
    *
    * @param search   查询条件
    * @param pageable 分页条件
    * @return
    */
    PageOb<DataDictionaryChildModelExtend> get(DataDictionaryChildModelSearch search, Pageable pageable);

    /**
    * 根据查询条件和排序条件，查询数据
    *
    * @param search 查询条件
    * @param sort   分页条件
    * @return
    */
    List<DataDictionaryChildModelExtend> get(DataDictionaryChildModelSearch search, Sort sort);

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
