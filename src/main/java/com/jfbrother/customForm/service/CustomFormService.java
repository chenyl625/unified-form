package com.jfbrother.customForm.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.customForm.model.JimuReportModel;
import com.jfbrother.customForm.model.extend.CustomFormModelExtend;
import com.jfbrother.customForm.model.param.CustomFormModelParam;
import com.jfbrother.customForm.model.response.CustomFormSelfYearResponseModel;
import com.jfbrother.customForm.model.search.CustomFormModelSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 自定义表单Service接口
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
public interface CustomFormService extends BaseService {
    /**
     * 自定义表单添加
     *
     * @param model 数据
     * @return
     */
    CustomFormModelExtend post(CustomFormModelParam model);

    /**
     * 自定义表单更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    CustomFormModelExtend put(String id, CustomFormModelParam model);

    /**
     * 自定义表单部分更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    CustomFormModelExtend patch(String id, CustomFormModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    CustomFormModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<CustomFormModelExtend> get(CustomFormModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<CustomFormModelExtend> get(CustomFormModelSearch search, Sort sort);

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
     * 我的填报（按年份）
     *
     * @param search
     * @return
     */
    List<CustomFormSelfYearResponseModel> getSelfYear(CustomFormModelSearch search);

    /**
     * 根据发布id获取自定义表单信息
     *
     * @param releaseId
     * @return
     */
    CustomFormModelExtend getByReleaseId(String releaseId);

    /**
     * 保存积木报表
     *
     * @param id
     */
    void saveJimuReport(String id);

    /**
     * 积木报表列表获取
     *
     * @return
     */
    List<JimuReportModel> getJimuReport();

    /**
     * 复制积木报表
     *
     * @param id
     * @param jimuReportId
     */
    void copyJimuReport(String id, String jimuReportId);

    /**
     * 移除积木报表
     *
     * @param ids
     */
    void removeJimuReport(List<String> ids);
}
