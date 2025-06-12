package com.jfbrother.customForm.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.customForm.model.CustomFormFieldModel;
import com.jfbrother.customForm.model.extend.CustomFormFieldModelExtend;
import com.jfbrother.customForm.model.param.CustomFormFieldModelParam;
import com.jfbrother.customForm.model.search.CustomFormFieldModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * 自定义表单字段Service接口
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
public interface CustomFormFieldService extends BaseService {
    /**
     * 自定义表单字段添加
     *
     * @param model 数据
     * @return
     */
    CustomFormFieldModelExtend post(CustomFormFieldModelParam model);

    /**
     * 自定义表单字段更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    CustomFormFieldModelExtend put(String id, CustomFormFieldModelParam model);

    /**
     * 自定义表单字段部分更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    CustomFormFieldModelExtend patch(String id, CustomFormFieldModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    CustomFormFieldModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<CustomFormFieldModelExtend> get(CustomFormFieldModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<CustomFormFieldModelExtend> get(CustomFormFieldModelSearch search, Sort sort);

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
     * 批量保存
     *
     * @param pid
     * @param list
     */
    void saveAll(String pid, List<CustomFormFieldModel> list);

    /**
     * 创建物理表
     *
     * @param tableName
     * @param tableComment
     * @param tableColumn
     */
    void createPhysicalTable(String tableName, String tableComment, List<CustomFormFieldModel> tableColumn);

    /**
     * 更新物理表
     *
     * @param tableName
     * @param tableComment
     * @param tableColumn
     * @param oldTableColumn
     */
    void updatePhysicalTable(String tableName, String tableComment, List<CustomFormFieldModel> tableColumn, List<CustomFormFieldModel> oldTableColumn);

    /**
     * 自定义表单字段根据父id获取
     *
     * @param pid
     * @return
     */
    List<Map<String, Object>> getField(String pid);
}
