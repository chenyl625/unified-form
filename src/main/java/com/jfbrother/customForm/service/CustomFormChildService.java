package com.jfbrother.customForm.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.customForm.model.extend.CustomFormChildModelExtend;
import com.jfbrother.customForm.model.param.CustomFormChildModelParam;
import com.jfbrother.customForm.model.search.CustomFormChildModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 自定义表单子表Service接口
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
public interface CustomFormChildService extends BaseService {
    /**
     * 自定义表单子表添加
     *
     * @param model 数据
     * @return
     */
    CustomFormChildModelExtend post(CustomFormChildModelParam model);

    /**
     * 自定义表单子表更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    CustomFormChildModelExtend put(String id, CustomFormChildModelParam model);

    /**
     * 自定义表单子表部分更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    CustomFormChildModelExtend patch(String id, CustomFormChildModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    CustomFormChildModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<CustomFormChildModelExtend> get(CustomFormChildModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<CustomFormChildModelExtend> get(CustomFormChildModelSearch search, Sort sort);

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
     * 根据表单id获取子表信息
     *
     * @param formId
     * @return
     */
    List<CustomFormChildModelExtend> getChildFormsByFormId(String formId);

    /**
     * 批量保存
     *
     * @param formId
     * @param list
     */
    void saveAll(String formId, List<CustomFormChildModelParam> list);

    /**
     * 保存
     *
     * @param formId
     * @param item
     */
    void save(String formId, CustomFormChildModelParam item);
}
