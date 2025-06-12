package com.jfbrother.customForm.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.customForm.model.extend.CustomFormRuleModelExtend;
import com.jfbrother.customForm.model.param.CustomFormRuleModelParam;
import com.jfbrother.customForm.model.search.CustomFormRuleModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 自定义表单规则Service接口
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
public interface CustomFormRuleService extends BaseService {
    /**
     * 自定义表单规则添加
     *
     * @param model 数据
     * @return
     */
    CustomFormRuleModelExtend post(CustomFormRuleModelParam model);

    /**
     * 自定义表单规则更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    CustomFormRuleModelExtend put(String id, CustomFormRuleModelParam model);

    /**
     * 自定义表单规则部分更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    CustomFormRuleModelExtend patch(String id, CustomFormRuleModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    CustomFormRuleModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<CustomFormRuleModelExtend> get(CustomFormRuleModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<CustomFormRuleModelExtend> get(CustomFormRuleModelSearch search, Sort sort);

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
     * 根据表单id获取规则
     *
     * @param formId
     * @return
     */
    CustomFormRuleModelExtend findCustomFormRuleByFormId(String formId);
}
