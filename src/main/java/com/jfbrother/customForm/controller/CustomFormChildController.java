package com.jfbrother.customForm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.customForm.model.extend.CustomFormChildModelExtend;
import com.jfbrother.customForm.model.param.CustomFormChildModelParam;
import com.jfbrother.customForm.model.search.CustomFormChildModelSearch;
import com.jfbrother.customForm.service.CustomFormChildService;
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
 * 自定义表单子表MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@ApiSort(10)
@Api(tags = {"自定义表单子表接口-1.0.0-20220610"})
@Validated
@RestController("CustomFormChild")
@RequestMapping("/api/v1/customFormChild")
public class CustomFormChildController {
    @Autowired
    private CustomFormChildService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单子表列表获取", notes = "自定义表单子表列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<CustomFormChildModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, CustomFormChildModelSearch search) {
        PageOb<CustomFormChildModelExtend> pageCustomFormChildModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageCustomFormChildModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单子表列表获取，不分页，默认上限100", notes = "自定义表单子表列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<CustomFormChildModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, CustomFormChildModelSearch search) {
        List<CustomFormChildModelExtend> listCustomFormChildModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listCustomFormChildModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "自定义表单子表获取", notes = "自定义表单子表获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自定义表单子表id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<CustomFormChildModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单子表添加", notes = "自定义表单子表添加接口")
    @ApiVersion(1)
    @PostMapping
    public Result<CustomFormChildModelExtend> post(@RequestBody @Valid CustomFormChildModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单子表全更新", notes = "自定义表单子表全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单子表id", required = true, dataType = "string")
    })
    @PutMapping(value = "/{id}")
    public Result<CustomFormChildModelExtend> put(@PathVariable String id, @RequestBody @Valid CustomFormChildModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单子表部分更新", notes = "自定义表单子表部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单子表id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    public Result<CustomFormChildModelExtend> patch(@PathVariable String id, @RequestBody CustomFormChildModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
    @ApiOperation(value = "自定义表单子表删除", notes = "自定义表单子表删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public Result delete(@RequestBody @NotNull(message = "自定义表单子表id列表不能为空") @Size(min = 1, message = "自定义表单子表id至少存在一个") List<String> ids) {
        service.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
    @ApiOperation(value = "自定义表单子表逻辑删除", notes = "自定义表单子表逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "自定义表单子表id列表不能为空") @Size(min = 1, message = "自定义表单子表id至少存在一个") List<String> ids) {
        service.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }
}
