package com.jfbrother.customForm.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.model.FileModel;
import com.jfbrother.baseserver.model.SysFileModel;
import com.jfbrother.baseserver.service.SysFileService;
import com.jfbrother.baseserver.utils.FileUtils;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.customForm.model.extend.CustomFormFillModelExtend;
import com.jfbrother.customForm.model.param.CustomFormFillModelParam;
import com.jfbrother.customForm.model.request.CustomFormFillRequestModel;
import com.jfbrother.customForm.model.request.CustomFormFillTodoRequestModel;
import com.jfbrother.customForm.model.response.CustomFormFillAuditResponseModel;
import com.jfbrother.customForm.model.search.CustomFormFillModelSearch;
import com.jfbrother.customForm.service.CustomFormFillService;
import com.jfbrother.work.model.request.WorkFlowTodoRequestModel;
import com.jfbrother.work.model.response.WorkFlowTodoResponseModel;
import com.jfbrother.work.service.WorkFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


/**
 * 自定义表单填报MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-06-21
 */
@ApiSort(10)
@Api(tags = {"自定义表单填报接口-1.0.0-20220621"})
@Validated
@RestController("CustomFormFill")
@RequestMapping("/api/v1/customFormFill")
public class CustomFormFillController {
    @Autowired
    private CustomFormFillService service;
    @Autowired
    private WorkFlowService workFlowService;
    @Autowired
    private SysFileService sysFileService;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单填报列表获取", notes = "自定义表单填报列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<CustomFormFillModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, CustomFormFillModelSearch search) {
        PageOb<CustomFormFillModelExtend> pageCustomFormFillModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageCustomFormFillModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单填报列表获取，不分页，默认上限100", notes = "自定义表单填报列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<CustomFormFillModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, CustomFormFillModelSearch search) {
        List<CustomFormFillModelExtend> listCustomFormFillModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listCustomFormFillModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "自定义表单填报获取", notes = "自定义表单填报获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自定义表单填报id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<CustomFormFillModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单填报添加", notes = "自定义表单填报添加接口")
    @ApiVersion(1)
    @PostMapping
    public Result<CustomFormFillModelExtend> post(@RequestBody @Valid CustomFormFillModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单填报全更新", notes = "自定义表单填报全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单填报id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "saveType", value = "保存类型(0:保存,1:提交,2:退回)", required = true, dataType = "int")
    })
    @PutMapping(value = "/{id}")
    public Result<CustomFormFillModelExtend> put(@PathVariable String id, @RequestBody @Valid CustomFormFillModelParam model
            , @RequestParam(defaultValue = "0", required = false) Integer saveType) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model, saveType));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单填报部分更新", notes = "自定义表单填报部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单填报id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    public Result<CustomFormFillModelExtend> patch(@PathVariable String id, @RequestBody CustomFormFillModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
    @ApiOperation(value = "自定义表单填报删除", notes = "自定义表单填报删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public Result delete(@RequestBody @NotNull(message = "自定义表单填报id列表不能为空") @Size(min = 1, message = "自定义表单填报id至少存在一个") List<String> ids) {
        service.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
    @ApiOperation(value = "自定义表单填报逻辑删除", notes = "自定义表单填报逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "自定义表单填报id列表不能为空") @Size(min = 1, message = "自定义表单填报id至少存在一个") List<String> ids) {
        service.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 9, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "用户自定义表单填报", notes = "用户自定义表单填报添加接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "releaseId", value = "填报的发布表单id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "saveType", value = "保存类型(0:保存,1:提交,2:退回)", required = true, dataType = "int")
    })
    @PostMapping("/{releaseId}/fillForm")
    public Result<CustomFormFillModelExtend> fillForm(@PathVariable String releaseId
            , @RequestBody CustomFormFillRequestModel model
            , @RequestParam(defaultValue = "0", required = false) Integer saveType) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.fillForm(releaseId, saveType, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 10)
    @ApiOperation(value = "自定义表单填报获取", notes = "自定义表单填报获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自定义表单填报id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}/detail")
    public Result<CustomFormFillModelExtend> getDetail(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.getDetail(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 11, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单填报审核列表获取", notes = "自定义表单填报审核列表获取接口")
    @ApiVersion(1)
    @GetMapping("audit")
    public Result<PageOb<CustomFormFillAuditResponseModel>> getAudit(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, CustomFormFillModelSearch search) {
        PageOb<CustomFormFillAuditResponseModel> pageCustomFormFillModel = service.getAudit(search, pageable);
        return ResultGenerator.genSuccessResult(pageCustomFormFillModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 12, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单填报待办列表获取", notes = "自定义表单填报待办列表获取接口")
    @ApiVersion(1)
    @GetMapping("todo")
    public Result<PageOb<WorkFlowTodoResponseModel>> getTodo(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, WorkFlowTodoRequestModel search) {
        PageOb<WorkFlowTodoResponseModel> pageCustomFormFillModel = service.getTodo(search, pageable);
        return ResultGenerator.genSuccessResult(pageCustomFormFillModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 13)
    @ApiOperation(value = "自定义表单填报根据流程id获取", notes = "自定义表单填报根据流程id获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "flowId", value = "自定义表单填报的流程id", required = true, dataType = "int")
    })
    @GetMapping(value = "/{flowId}/flowId")
    public Result<CustomFormFillModelExtend> getByFlowId(@PathVariable Integer flowId) {
        return ResultGenerator.genSuccessResult(service.getByFlowId(flowId));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 14, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "处理自定义表单填报表", notes = "处理自定义表单填报表接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "环节id", required = true, dataType = "int"),
    })
    @PutMapping(value = "/{taskId}/todo")
    public Result<CustomFormFillModelExtend> todo(@PathVariable Integer taskId, @RequestBody @Valid CustomFormFillTodoRequestModel model) {
        service.todo(taskId, model);
        return ResultGenerator.genSuccessResult(ResultCode.OK);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "自定义表单发布的填报保存信息获取", notes = "自定义表单发布的填报保存信息获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "releaseId", value = "自定义表单发布id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{releaseId}/releaseFill")
    public Result<CustomFormFillModelExtend> getReleaseFill(@PathVariable String releaseId) {
        return ResultGenerator.genSuccessResult(service.getReleaseFill(releaseId));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单流程取消", notes = "自定义表单流程取消接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自定义表单填报id", required = true, dataType = "string"),
    })
    @PutMapping(value = "/{id}/flowCancel")
    public Result<CustomFormFillModelExtend> flowCancel(@PathVariable String id) {
        service.flowCancel(id);
        return ResultGenerator.genSuccessResult(ResultCode.OK);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "自定义表单填报获取", notes = "自定义表单填报获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自定义表单填报id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "tableName", value = "表名", required = true, dataType = "string"),
    })
    @GetMapping(value = "/{id}/formFieldData/{tableName}")
    public JSONObject getFormFieldData(@PathVariable String id, @PathVariable String tableName) {
        return service.getFormFieldData(id, tableName);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
    @ApiOperation(value = "自定义表单填报状态更新", notes = "自定义表单填报状态更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @PutMapping("statusNum")
    public Result statusNum(@RequestBody @NotNull(message = "自定义表单填报id列表不能为空") @Size(min = 1, message = "自定义表单填报id至少存在一个") List<String> ids) {
        service.statusNum(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
    @ApiOperation(value = "自定义表单填报更新流程环节", notes = "自定义表单填报更新流程环节接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "string")
    })
    @PutMapping("/{id}/flowRefresh")
    public Result flowRefresh(@PathVariable String id) {
        service.flowRefresh(id);
        return ResultGenerator.genSuccessResult(ResultCode.OK);
    }
    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单填报数据全更新", notes = "自定义表单填报数据全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单填报id", required = true, dataType = "string")
    })
    @PutMapping(value = "updateFormData/{id}")
    public Result<CustomFormFillModelExtend> updateFormData(@PathVariable String id, @RequestBody @Valid CustomFormFillModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
    }

    @ApiOperation(value = "附件批量下载", notes = "附件批量下载接口", position = 6)
    @ApiVersion(1)
    @GetMapping(value = "/getWordZip",produces = "application/octet-stream")
    public void getWord(CustomFormFillModelSearch search) {
        service.batchDownloadFiles(search);
    }
}
