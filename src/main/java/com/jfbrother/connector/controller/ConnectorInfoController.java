package com.jfbrother.connector.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.connector.model.extend.ConnectorInfoModelExtend;
import com.jfbrother.connector.model.param.ConnectorInfoModelParam;
import com.jfbrother.connector.model.search.ConnectorInfoModelSearch;
import com.jfbrother.connector.service.ConnectorInfoService;
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
 * 接口管理MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
@ApiSort(10)
@Api(tags = {"接口管理接口-1.0.0-20220630"})
@Validated
@RestController("ConnectorInfo")
@RequestMapping("/api/v1/connectorInfo")
public class ConnectorInfoController {
    @Autowired
    private ConnectorInfoService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "接口管理列表获取", notes = "接口管理列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<ConnectorInfoModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, ConnectorInfoModelSearch search) {
        PageOb<ConnectorInfoModelExtend> pageConnectorInfoModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageConnectorInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "接口管理列表获取，不分页，默认上限100", notes = "接口管理列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<ConnectorInfoModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, ConnectorInfoModelSearch search) {
        List<ConnectorInfoModelExtend> listConnectorInfoModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listConnectorInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "接口管理获取", notes = "接口管理获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "接口管理id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<ConnectorInfoModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "接口管理添加", notes = "接口管理添加接口")
    @ApiVersion(1)
    @PostMapping
    public Result<ConnectorInfoModelExtend> post(@RequestBody @Valid ConnectorInfoModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "接口管理全更新", notes = "接口管理全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的接口管理id", required = true, dataType = "string")
    })
    @PutMapping(value = "/{id}")
    public Result<ConnectorInfoModelExtend> put(@PathVariable String id, @RequestBody @Valid ConnectorInfoModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "接口管理部分更新", notes = "接口管理部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的接口管理id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    public Result<ConnectorInfoModelExtend> patch(@PathVariable String id, @RequestBody ConnectorInfoModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
    @ApiOperation(value = "接口管理删除", notes = "接口管理删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public Result delete(@RequestBody @NotNull(message = "接口管理id列表不能为空") @Size(min = 1, message = "接口管理id至少存在一个") List<String> ids) {
        service.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
    @ApiOperation(value = "接口管理逻辑删除", notes = "接口管理逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "接口管理id列表不能为空") @Size(min = 1, message = "接口管理id至少存在一个") List<String> ids) {
        service.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "接口管理字段获取", notes = "接口管理字段获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "接口管理id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}/field")
    public Result<JSONObject> getField(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.getField(id));
    }
}
