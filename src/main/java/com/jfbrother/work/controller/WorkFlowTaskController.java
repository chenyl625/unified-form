package com.jfbrother.work.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.work.model.extend.WorkFlowTaskModelExtend;
import com.jfbrother.work.model.param.WorkFlowTaskModelParam;
import com.jfbrother.work.model.search.WorkFlowTaskModelSearch;
import com.jfbrother.work.service.WorkFlowTaskService;
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
 * 流程环节实例MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-06-27
 */
@ApiSort(10)
@Api(tags = {"流程环节实例接口-1.0.0-20220627"})
@Validated
@RestController("WorkFlowTask")
@RequestMapping("/api/v1/workFlowTask")
public class WorkFlowTaskController {
    @Autowired
    private WorkFlowTaskService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "流程环节实例列表获取", notes = "流程环节实例列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<WorkFlowTaskModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, WorkFlowTaskModelSearch search) {
        PageOb<WorkFlowTaskModelExtend> pageWorkFlowTaskModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageWorkFlowTaskModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "流程环节实例列表获取，不分页，默认上限100", notes = "流程环节实例列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<WorkFlowTaskModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, WorkFlowTaskModelSearch search) {
        List<WorkFlowTaskModelExtend> listWorkFlowTaskModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listWorkFlowTaskModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "流程环节实例获取", notes = "流程环节实例获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "流程环节实例id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<WorkFlowTaskModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "流程环节实例添加", notes = "流程环节实例添加接口")
    @ApiVersion(1)
    @PostMapping
    public Result<WorkFlowTaskModelExtend> post(@RequestBody @Valid WorkFlowTaskModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "流程环节实例全更新", notes = "流程环节实例全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的流程环节实例id", required = true, dataType = "string")
    })
    @PutMapping(value = "/{id}")
    public Result<WorkFlowTaskModelExtend> put(@PathVariable String id, @RequestBody @Valid WorkFlowTaskModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "流程环节实例部分更新", notes = "流程环节实例部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的流程环节实例id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    public Result<WorkFlowTaskModelExtend> patch(@PathVariable String id, @RequestBody WorkFlowTaskModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
    @ApiOperation(value = "流程环节实例删除", notes = "流程环节实例删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public Result delete(@RequestBody @NotNull(message = "流程环节实例id列表不能为空") @Size(min = 1, message = "流程环节实例id至少存在一个") List<String> ids) {
        service.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
    @ApiOperation(value = "流程环节实例逻辑删除", notes = "流程环节实例逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "流程环节实例id列表不能为空") @Size(min = 1, message = "流程环节实例id至少存在一个") List<String> ids) {
        service.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 9)
    @ApiOperation(value = "根据填报id流程环节实例列表获取", notes = "根据填报id流程环节实例列表获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fillId", value = "填报id", required = true, dataType = "string")
    })
    @GetMapping("/{fillId}/get")
    public Result<List<WorkFlowTaskModelExtend>> getByFillId(@PathVariable String fillId) {
        List<WorkFlowTaskModelExtend> listWorkFlowTaskModel = service.getByFillId(fillId);
        return ResultGenerator.genSuccessResult(listWorkFlowTaskModel);
    }

}
