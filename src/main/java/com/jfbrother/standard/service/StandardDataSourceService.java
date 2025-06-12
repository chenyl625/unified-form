package com.jfbrother.standard.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.standard.model.StandardDataSourceModel;
import com.jfbrother.standard.model.param.StandardDataSourceModelParam;
import com.jfbrother.standard.model.extend.StandardDataSourceModelExtend;
import com.jfbrother.standard.model.search.StandardDataSourceModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
* 数据来源标准库Service接口
* @author chenyl@jfbrother.com 2022-08-04
*/
public interface StandardDataSourceService extends BaseService{
    /**
    * 数据来源标准库添加
    *
    * @param model 数据
    * @return
    */
    StandardDataSourceModelExtend post(StandardDataSourceModelParam model);

    /**
    * 数据来源标准库更新
    *
    * @param id        主键id
    * @param model 数据
    * @return
    */
    StandardDataSourceModelExtend put(String id, StandardDataSourceModelParam model);

    /**
    * 数据来源标准库部分更新
    *
    * @param id         主键id
    * @param model 数据
    * @return
    */
    StandardDataSourceModelExtend patch(String id, StandardDataSourceModelParam model);

    /**
    * 根据主键id获取数据
    *
    * @param id 主键id
    * @return
    */
    StandardDataSourceModelExtend get(String id);

    /**
    * 根据查询条件和分页条件（包括排序条件），查询数据
    *
    * @param search   查询条件
    * @param pageable 分页条件
    * @return
    */
    PageOb<StandardDataSourceModelExtend> get(StandardDataSourceModelSearch search, Pageable pageable);

    /**
    * 根据查询条件和排序条件，查询数据
    *
    * @param search 查询条件
    * @param sort   分页条件
    * @return
    */
    List<StandardDataSourceModelExtend> get(StandardDataSourceModelSearch search, Sort sort);

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
