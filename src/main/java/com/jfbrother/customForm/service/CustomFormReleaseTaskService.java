package com.jfbrother.customForm.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.customForm.model.CustomFormReleaseTaskModel;
import com.jfbrother.customForm.model.param.CustomFormReleaseTaskModelParam;
import com.jfbrother.customForm.model.extend.CustomFormReleaseTaskModelExtend;
import com.jfbrother.customForm.model.search.CustomFormReleaseTaskModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
* 自定义表单发布环节Service接口
* @author chenyl@jfbrother.com 2022-07-04
*/
public interface CustomFormReleaseTaskService extends BaseService{
    /**
    * 自定义表单发布环节添加
    *
    * @param model 数据
    * @return
    */
    CustomFormReleaseTaskModelExtend post(CustomFormReleaseTaskModelParam model);

    /**
    * 自定义表单发布环节更新
    *
    * @param id        主键id
    * @param model 数据
    * @return
    */
    CustomFormReleaseTaskModelExtend put(String id, CustomFormReleaseTaskModelParam model);

    /**
    * 自定义表单发布环节部分更新
    *
    * @param id         主键id
    * @param model 数据
    * @return
    */
    CustomFormReleaseTaskModelExtend patch(String id, CustomFormReleaseTaskModelParam model);

    /**
    * 根据主键id获取数据
    *
    * @param id 主键id
    * @return
    */
    CustomFormReleaseTaskModelExtend get(String id);

    /**
    * 根据查询条件和分页条件（包括排序条件），查询数据
    *
    * @param search   查询条件
    * @param pageable 分页条件
    * @return
    */
    PageOb<CustomFormReleaseTaskModelExtend> get(CustomFormReleaseTaskModelSearch search, Pageable pageable);

    /**
    * 根据查询条件和排序条件，查询数据
    *
    * @param search 查询条件
    * @param sort   分页条件
    * @return
    */
    List<CustomFormReleaseTaskModelExtend> get(CustomFormReleaseTaskModelSearch search, Sort sort);

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
