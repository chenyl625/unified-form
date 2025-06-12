package com.jfbrother.standard.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.standard.model.StandardDataThemeModel;
import com.jfbrother.standard.model.param.StandardDataThemeModelParam;
import com.jfbrother.standard.model.extend.StandardDataThemeModelExtend;
import com.jfbrother.standard.model.search.StandardDataThemeModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
* 数据开放主题管理Service接口
* @author chenyl@jfbrother.com 2022-09-16
*/
public interface StandardDataThemeService extends BaseService{
    /**
    * 数据开放主题管理添加
    *
    * @param model 数据
    * @return
    */
    StandardDataThemeModelExtend post(StandardDataThemeModelParam model);

    /**
    * 数据开放主题管理更新
    *
    * @param id        主键id
    * @param model 数据
    * @return
    */
    StandardDataThemeModelExtend put(String id, StandardDataThemeModelParam model);

    /**
    * 数据开放主题管理部分更新
    *
    * @param id         主键id
    * @param model 数据
    * @return
    */
    StandardDataThemeModelExtend patch(String id, StandardDataThemeModelParam model);

    /**
    * 根据主键id获取数据
    *
    * @param id 主键id
    * @return
    */
    StandardDataThemeModelExtend get(String id);

    /**
    * 根据查询条件和分页条件（包括排序条件），查询数据
    *
    * @param search   查询条件
    * @param pageable 分页条件
    * @return
    */
    PageOb<StandardDataThemeModelExtend> get(StandardDataThemeModelSearch search, Pageable pageable);

    /**
    * 根据查询条件和排序条件，查询数据
    *
    * @param search 查询条件
    * @param sort   分页条件
    * @return
    */
    List<StandardDataThemeModelExtend> get(StandardDataThemeModelSearch search, Sort sort);

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
