package com.jfbrother.customForm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.customForm.model.extend.CustomFormReleaseConnModelExtend;
import com.jfbrother.customForm.model.param.CustomFormReleaseConnModelParam;
import com.jfbrother.customForm.model.search.CustomFormReleaseConnModelSearch;
import com.jfbrother.customForm.service.CustomFormReleaseConnService;
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
 * 自定义表单发布对应接口MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
@ApiSort(10)
@Api(tags = {"自定义表单发布对应接口接口-1.0.0-20220630"})
@Validated
@RestController("CustomFormReleaseConn")
@RequestMapping("/api/v1/customFormReleaseConn")
public class CustomFormReleaseConnController {
    @Autowired
    private CustomFormReleaseConnService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单发布对应接口列表获取", notes = "自定义表单发布对应接口列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<CustomFormReleaseConnModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, CustomFormReleaseConnModelSearch search) {
        PageOb<CustomFormReleaseConnModelExtend> pageCustomFormReleaseConnModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageCustomFormReleaseConnModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单发布对应接口列表获取，不分页，默认上限100", notes = "自定义表单发布对应接口列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<CustomFormReleaseConnModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, CustomFormReleaseConnModelSearch search) {
        List<CustomFormReleaseConnModelExtend> listCustomFormReleaseConnModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listCustomFormReleaseConnModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "自定义表单发布对应接口获取", notes = "自定义表单发布对应接口获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自定义表单发布对应接口id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<CustomFormReleaseConnModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单发布对应接口添加", notes = "自定义表单发布对应接口添加接口")
    @ApiVersion(1)
    @PostMapping
    public Result<CustomFormReleaseConnModelExtend> post(@RequestBody @Valid CustomFormReleaseConnModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单发布对应接口全更新", notes = "自定义表单发布对应接口全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单发布对应接口id", required = true, dataType = "string")
    })
    @PutMapping(value = "/{id}")
    public Result<CustomFormReleaseConnModelExtend> put(@PathVariable String id, @RequestBody @Valid CustomFormReleaseConnModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单发布对应接口部分更新", notes = "自定义表单发布对应接口部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单发布对应接口id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    public Result<CustomFormReleaseConnModelExtend> patch(@PathVariable String id, @RequestBody CustomFormReleaseConnModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
    @ApiOperation(value = "自定义表单发布对应接口删除", notes = "自定义表单发布对应接口删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public Result delete(@RequestBody @NotNull(message = "自定义表单发布对应接口id列表不能为空") @Size(min = 1, message = "自定义表单发布对应接口id至少存在一个") List<String> ids) {
        service.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
    @ApiOperation(value = "自定义表单发布对应接口逻辑删除", notes = "自定义表单发布对应接口逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "自定义表单发布对应接口id列表不能为空") @Size(min = 1, message = "自定义表单发布对应接口id至少存在一个") List<String> ids) {
        service.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

}
