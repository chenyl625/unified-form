package com.jfbrother.work.service;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.work.model.request.*;
import com.jfbrother.work.model.response.WorkFlowResponseModel;
import com.jfbrother.work.model.response.WorkFlowTaskResponseModel;
import com.jfbrother.work.model.response.WorkFlowTodoResponseModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 流程Service接口
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
public interface WorkFlowService extends BaseService {

    /**
     * 流程实例列表
     *
     * @param search
     * @param pageable
     * @return
     */
    PageOb<WorkFlowResponseModel> get(WorkFlowRequestModel search, Pageable pageable);

    /**
     * 流程环节基本信息
     *
     * @param search
     * @return
     */
    List<WorkFlowTaskResponseModel> getTask(WorkFlowTaskRequestModel search);

    /**
     * 启动流程
     *
     * @param model
     * @return
     */
    JSONObject create(WorkFlowCreateRequestModel model);

    /**
     * 驱动流程（环节提交）
     *
     * @param model
     * @return
     */
    JSONObject taskForward(WorkFlowTaskForwardRequestModel model);

    /**
     * 获取表单组建值
     *
     * @param search
     * @return
     */
    JSONObject getFormData(WorkFlowFormDataRequestModel search);

    /**
     * 取消流程
     *
     * @param search
     */
    void cancel(WorkFlowCancelRequestModel search);

    /**
     * 下载流程打印文件
     *
     * @param wf_id
     */
    void download(int wf_id);

    /**
     * 环节审批意见
     *
     * @param search
     * @return
     */
    JSONObject taskApproval(WorkFlowTaskApprovalRequestModel search);

    /**
     * 表单更新
     *
     * @param model
     * @return
     */
    JSONObject formUpdate(WorkFlowFormUpdateRequestModel model);

    /**
     * 流程待办获取，分页
     *
     * @param search
     * @param pageable
     * @return
     */
    PageOb<WorkFlowTodoResponseModel> getTodo(WorkFlowTodoRequestModel search, Pageable pageable);

    /**
     * 流程待办获取
     *
     * @param search
     * @return
     */
    List<WorkFlowTodoResponseModel> getTodoAll(WorkFlowTodoRequestModel search);
}
