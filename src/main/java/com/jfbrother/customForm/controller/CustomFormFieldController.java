package com.jfbrother.customForm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.customForm.model.extend.CustomFormFieldModelExtend;
import com.jfbrother.customForm.model.param.CustomFormFieldModelParam;
import com.jfbrother.customForm.model.search.CustomFormFieldModelSearch;
import com.jfbrother.customForm.service.CustomFormFieldService;
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
 * 自定义表单字段MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@ApiSort(10)
@Api(tags = {"自定义表单字段接口-1.0.0-20220610"})
@Validated
@RestController("CustomFormField")
@RequestMapping("/api/v1/customFormField")
public class CustomFormFieldController {
    @Autowired
    private CustomFormFieldService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单字段列表获取", notes = "自定义表单字段列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<CustomFormFieldModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, CustomFormFieldModelSearch search) {
        PageOb<CustomFormFieldModelExtend> pageCustomFormFieldModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageCustomFormFieldModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单字段列表获取，不分页，默认上限100", notes = "自定义表单字段列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<CustomFormFieldModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, CustomFormFieldModelSearch search) {
        List<CustomFormFieldModelExtend> listCustomFormFieldModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listCustomFormFieldModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "自定义表单字段获取", notes = "自定义表单字段获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自定义表单字段id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<CustomFormFieldModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单字段添加", notes = "自定义表单字段添加接口")
    @ApiVersion(1)
    @PostMapping
    public Result<CustomFormFieldModelExtend> post(@RequestBody @Valid CustomFormFieldModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单字段全更新", notes = "自定义表单字段全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单字段id", required = true, dataType = "string")
    })
    @PutMapping(value = "/{id}")
    public Result<CustomFormFieldModelExtend> put(@PathVariable String id, @RequestBody @Valid CustomFormFieldModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单字段部分更新", notes = "自定义表单字段部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单字段id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    public Result<CustomFormFieldModelExtend> patch(@PathVariable String id, @RequestBody CustomFormFieldModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
    @ApiOperation(value = "自定义表单字段删除", notes = "自定义表单字段删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public Result delete(@RequestBody @NotNull(message = "自定义表单字段id列表不能为空") @Size(min = 1, message = "自定义表单字段id至少存在一个") List<String> ids) {
        service.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
    @ApiOperation(value = "自定义表单字段逻辑删除", notes = "自定义表单字段逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "自定义表单字段id列表不能为空") @Size(min = 1, message = "自定义表单字段id至少存在一个") List<String> ids) {
        service.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "自定义表单字段根据父id获取", notes = "自定义表单字段根据父id获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "父id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{pid}/field")
    public List<Map<String, Object>> getField(@PathVariable String pid) {
        return service.getField(pid);
    }
}
