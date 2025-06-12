package com.jfbrother.work.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.work.model.request.*;
import com.jfbrother.work.model.response.WorkFlowResponseModel;
import com.jfbrother.work.model.response.WorkFlowTaskResponseModel;
import com.jfbrother.work.model.response.WorkFlowTodoResponseModel;
import com.jfbrother.work.service.WorkFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 流程接口MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-06-03
 */
@ApiSort(10)
@Api(tags = {"流程接口-1.0.0-20220603"})
@Validated
@RestController("WorkFlow")
@RequestMapping("/api/{version}/workFlow")
public class WorkFlowController {

    @Autowired
    private WorkFlowService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1)
    @ApiOperation(value = "流程实例列表获取", notes = "流程实例列表获取接口")
    @ApiVersion(1)
    @GetMapping
    public Result<PageOb<WorkFlowResponseModel>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Sort.Direction.DESC)
                    Pageable pageable, WorkFlowRequestModel search) {

        PageOb<WorkFlowResponseModel> pageModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2)
    @ApiOperation(value = "流程环节基本信息获取", notes = "流程环节基本信息获取接口")
    @ApiVersion(1)
    @GetMapping("task")
    public Result<List<WorkFlowTaskResponseModel>> getTask(WorkFlowTaskRequestModel search) {
        List<WorkFlowTaskResponseModel> listModel = service.getTask(search);
        return ResultGenerator.genSuccessResult(listModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "启动流程", notes = "启动流程接口")
    @ApiVersion(1)
    @PostMapping("flowCreate")
    public Result<JSONObject> create(@RequestBody @Valid WorkFlowCreateRequestModel model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.create(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4)
    @ApiOperation(value = "驱动流程（环节提交）", notes = "驱动流程（环节提交）接口")
    @ApiVersion(1)
    @PostMapping("taskForward")
    public Result<JSONObject> taskForward(@RequestBody @Valid WorkFlowTaskForwardRequestModel model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.taskForward(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5)
    @ApiOperation(value = "表单组件值获取", notes = "表单组件值获取接口")
    @ApiVersion(1)
    @GetMapping("formData")
    public Result<JSONObject> getFormData(WorkFlowFormDataRequestModel search) {
        return ResultGenerator.genSuccessResult(service.getFormData(search));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6)
    @ApiOperation(value = "取消流程", notes = "取消流程接口")
    @ApiVersion(1)
    @PostMapping("flowCancel")
    public Result cancel(@RequestBody @Valid WorkFlowCancelRequestModel search) {
        service.cancel(search);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
    @ApiOperation(value = "下载流程打印文件", notes = "下载流程打印文件接口")
    @ApiVersion(1)
    @GetMapping(value = "/{wf_id}/download", produces = "application/octet-stream")
    public void download(@PathVariable int wf_id) {
        service.download(wf_id);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 9)
    @ApiOperation(value = "环节审批意见获取", notes = "环节审批意见获取接口")
    @ApiVersion(1)
    @GetMapping("taskApproval")
    public Result<JSONObject> taskApproval(WorkFlowTaskApprovalRequestModel search) {
        return ResultGenerator.genSuccessResult(service.taskApproval(search));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 10)
    @ApiOperation(value = "表单更新", notes = "表单更新接口")
    @ApiVersion(1)
    @PostMapping("formUpdate")
    public Result<JSONObject> formUpdate(@RequestBody @Valid WorkFlowFormUpdateRequestModel model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.formUpdate(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 11)
    @ApiOperation(value = "流程待办列表获取", notes = "流程待办列表获取接口")
    @ApiVersion(1)
    @GetMapping("getTodo")
    public Result<PageOb<WorkFlowTodoResponseModel>> getTodo(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Sort.Direction.DESC)
                    Pageable pageable, WorkFlowTodoRequestModel search) {
        PageOb<WorkFlowTodoResponseModel> pageModel = service.getTodo(search, pageable);
        return ResultGenerator.genSuccessResult(pageModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 12)
    @ApiOperation(value = "所有流程待办列表获取", notes = "所有流程待办列表获取接口")
    @ApiVersion(1)
    @GetMapping("getTodoAll")
    public Result<List<WorkFlowTodoResponseModel>> getTodoAll(WorkFlowTodoRequestModel search) {
        List<WorkFlowTodoResponseModel> listModel = service.getTodoAll(search);
        return ResultGenerator.genSuccessResult(listModel);
    }
}
