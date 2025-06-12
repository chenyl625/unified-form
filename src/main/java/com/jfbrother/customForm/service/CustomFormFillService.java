package com.jfbrother.customForm.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.customForm.model.extend.CustomFormFieldModelExtend;
import com.jfbrother.customForm.model.extend.CustomFormFillModelExtend;
import com.jfbrother.customForm.model.param.CustomFormFillModelParam;
import com.jfbrother.customForm.model.request.CustomFormFillRequestModel;
import com.jfbrother.customForm.model.request.CustomFormFillTodoRequestModel;
import com.jfbrother.customForm.model.response.CustomFormFillAuditResponseModel;
import com.jfbrother.customForm.model.search.CustomFormFillModelSearch;
import com.jfbrother.work.model.request.WorkFlowTodoRequestModel;
import com.jfbrother.work.model.response.WorkFlowTodoResponseModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 自定义表单填报Service接口
 *
 * @author chenyl@jfbrother.com 2022-06-21
 */
public interface CustomFormFillService extends BaseService {
    /**
     * 自定义表单填报添加
     *
     * @param model 数据
     * @return
     */
    CustomFormFillModelExtend post(CustomFormFillModelParam model);

    /**
     * 自定义表单填报更新
     *
     * @param id       主键id
     * @param model    数据
     * @param saveType
     * @return
     */
    CustomFormFillModelExtend put(String id, CustomFormFillModelParam model, Integer saveType);

    /**
     * 自定义表单填报部分更新
     *
     * @param id    主键id
     * @param model 数据
     * @return
     */
    CustomFormFillModelExtend patch(String id, CustomFormFillModelParam model);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    CustomFormFillModelExtend get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<CustomFormFillModelExtend> get(CustomFormFillModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<CustomFormFillModelExtend> get(CustomFormFillModelSearch search, Sort sort);

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
     * 用户自定义表单填报
     *
     * @param releaseId
     * @param saveType
     * @param model
     */
    CustomFormFillModelExtend fillForm(String releaseId, Integer saveType, CustomFormFillRequestModel model);

    /**
     * 自定义表单详细数据
     *
     * @param id
     * @return
     */
    CustomFormFillModelExtend getDetail(String id);

    /**
     * 自定义表单填报审核列表
     *
     * @param search
     * @param pageable
     * @return
     */
    PageOb<CustomFormFillAuditResponseModel> getAudit(CustomFormFillModelSearch search, Pageable pageable);

    /**
     * 根据流程id获取表单填报信息
     *
     * @param flowId
     * @return
     */
    CustomFormFillModelExtend getByFlowId(Integer flowId);

    /**
     * 处理自定义表单填报表
     *
     * @param taskId
     * @param model
     */
    void todo(Integer taskId, CustomFormFillTodoRequestModel model);

    /**
     * 分页获取待办列表
     *
     * @param search
     * @param pageable
     * @return
     */
    PageOb<WorkFlowTodoResponseModel> getTodo(WorkFlowTodoRequestModel search, Pageable pageable);

    /**
     * 获取发布的填报信息
     *
     * @param releaseId
     * @return
     */
    CustomFormFillModelExtend getReleaseFill(String releaseId);

    /**
     * 流程取消
     *
     * @param id
     */
    void flowCancel(String id);

    /**
     * 根据填报id和表名获取填报数据
     *
     * @param id
     * @param tableName
     * @return
     */
    JSONObject getFormFieldData(String id, String tableName);

    /**
     * 生成物理表数据
     *
     * @param tableName
     * @param tableColumn
     * @param dataList
     * @param businessId
     * @param sortNum
     */
    void createPhysicalData(String tableName, List<CustomFormFieldModelExtend> tableColumn, JSONArray dataList, String businessId, int sortNum);

    /**
     * 根据用户id获取表单填报状态
     *
     * @param ids
     */
    void statusNum(List<String> ids);

    /**
     * 自定义表单填报更新流程环节
     *
     * @param id
     */
    void flowRefresh(String id);

    CustomFormFillModelExtend put(String id,CustomFormFillModelParam param);

    CustomFormFillModelExtend getOneById(String id);

    void batchDownloadFiles(CustomFormFillModelSearch search);
}
