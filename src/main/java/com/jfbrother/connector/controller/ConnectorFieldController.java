package com.jfbrother.connector.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.connector.model.extend.ConnectorFieldModelExtend;
import com.jfbrother.connector.model.param.ConnectorFieldModelParam;
import com.jfbrother.connector.model.search.ConnectorFieldModelSearch;
import com.jfbrother.connector.service.ConnectorFieldService;
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
import java.util.Map;


/**
 * 接口字段MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-07-20
 */
@ApiSort(10)
@Api(tags = {"接口字段接口-1.0.0-20220720"})
@Validated
@RestController("ConnectorField")
@RequestMapping("/api/v1/connectorField")
public class ConnectorFieldController {
    @Autowired
    private ConnectorFieldService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "接口字段列表获取", notes = "接口字段列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<ConnectorFieldModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, ConnectorFieldModelSearch search) {
        PageOb<ConnectorFieldModelExtend> pageConnectorFieldModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageConnectorFieldModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "接口字段列表获取，不分页，默认上限100", notes = "接口字段列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<ConnectorFieldModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, ConnectorFieldModelSearch search) {
        List<ConnectorFieldModelExtend> listConnectorFieldModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listConnectorFieldModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "接口字段获取", notes = "接口字段获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "接口字段id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<ConnectorFieldModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "接口字段添加", notes = "接口字段添加接口")
    @ApiVersion(1)
    @PostMapping
    public Result<ConnectorFieldModelExtend> post(@RequestBody @Valid ConnectorFieldModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "接口字段全更新", notes = "接口字段全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的接口字段id", required = true, dataType = "string")
    })
    @PutMapping(value = "/{id}")
    public Result<ConnectorFieldModelExtend> put(@PathVariable String id, @RequestBody @Valid ConnectorFieldModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "接口字段部分更新", notes = "接口字段部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的接口字段id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    public Result<ConnectorFieldModelExtend> patch(@PathVariable String id, @RequestBody ConnectorFieldModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
    @ApiOperation(value = "接口字段删除", notes = "接口字段删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public Result delete(@RequestBody @NotNull(message = "接口字段id列表不能为空") @Size(min = 1, message = "接口字段id至少存在一个") List<String> ids) {
        service.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
    @ApiOperation(value = "接口字段逻辑删除", notes = "接口字段逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "接口字段id列表不能为空") @Size(min = 1, message = "接口字段id至少存在一个") List<String> ids) {
        service.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 9, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "接口字段批量添加", notes = "接口字段批量添加接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "connId", value = "接口id", required = true, dataType = "string")
    })
    @PostMapping("/{connId}/saveAll")
    public Result saveAll(@PathVariable String connId, @RequestBody List<ConnectorFieldModelParam> list) {
        service.saveAll(connId, list);
        return ResultGenerator.genSuccessResult(ResultCode.CREATED);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "根据接口id获取字段", notes = "根据接口id获取字段")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "connId", value = "接口id", required = true, dataType = "string")
    })
    @GetMapping("/{connId}/get")
    public Result<Map<String, Map<String, Object>>> getByConnId(@PathVariable String connId) {
        return ResultGenerator.genSuccessResult(service.getByConnId(connId));
    }
}
