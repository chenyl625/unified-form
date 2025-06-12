package com.jfbrother.customForm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.customForm.model.JimuReportModel;
import com.jfbrother.customForm.model.extend.CustomFormModelExtend;
import com.jfbrother.customForm.model.param.CustomFormModelParam;
import com.jfbrother.customForm.model.response.CustomFormSelfYearResponseModel;
import com.jfbrother.customForm.model.search.CustomFormModelSearch;
import com.jfbrother.customForm.service.CustomFormService;
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
 * 自定义表单MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@ApiSort(10)
@Api(tags = {"自定义表单接口-1.0.0-20220610"})
@Validated
@RestController("CustomForm")
@RequestMapping("/api/v1/customForm")
public class CustomFormController {
    @Autowired
    private CustomFormService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单列表获取", notes = "自定义表单列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<CustomFormModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, CustomFormModelSearch search) {
        PageOb<CustomFormModelExtend> pageCustomFormModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageCustomFormModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单列表获取，不分页，默认上限100", notes = "自定义表单列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<CustomFormModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, CustomFormModelSearch search) {
        List<CustomFormModelExtend> listCustomFormModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listCustomFormModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "自定义表单获取", notes = "自定义表单获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自定义表单id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<CustomFormModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单添加", notes = "自定义表单添加接口")
    @ApiVersion(1)
    @PostMapping
    public Result<CustomFormModelExtend> post(@RequestBody @Valid CustomFormModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单全更新", notes = "自定义表单全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单id", required = true, dataType = "string")
    })
    @PutMapping(value = "/{id}")
    public Result<CustomFormModelExtend> put(@PathVariable String id, @RequestBody @Valid CustomFormModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单部分更新", notes = "自定义表单部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    public Result<CustomFormModelExtend> patch(@PathVariable String id, @RequestBody CustomFormModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
    @ApiOperation(value = "自定义表单删除", notes = "自定义表单删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public Result delete(@RequestBody @NotNull(message = "自定义表单id列表不能为空") @Size(min = 1, message = "自定义表单id至少存在一个") List<String> ids) {
        service.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
    @ApiOperation(value = "自定义表单逻辑删除", notes = "自定义表单逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "自定义表单id列表不能为空") @Size(min = 1, message = "自定义表单id至少存在一个") List<String> ids) {
        service.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 9, ignoreParameters = {"id"})
    @ApiOperation(value = "我的填报列表获取", notes = "我的填报列表获取接口")
    @ApiVersion(1)
    @GetMapping("selfYear")
    public Result<List<CustomFormSelfYearResponseModel>> getSelfYear(CustomFormModelSearch search) {
        List<CustomFormSelfYearResponseModel> listCustomFormModel = service.getSelfYear(search);
        return ResultGenerator.genSuccessResult(listCustomFormModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单生成积木报表", notes = "自定义表单生成积木报表接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自定义表单id", required = true, dataType = "string")
    })
    @PostMapping("/{id}/jimuReport")
    public Result saveJimuReport(@PathVariable String id) {
        service.saveJimuReport(id);
        return ResultGenerator.genSuccessResult(ResultCode.CREATED);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "积木报表列表获取，不分页，默认上限100", notes = "积木报表列表获取接口")
    @ApiVersion(1)
    @GetMapping("jimuReportAll")
    public Result<List<JimuReportModel>> getJimuReport() {
        List<JimuReportModel> listModel = service.getJimuReport();
        return ResultGenerator.genSuccessResult(listModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单复制积木报表", notes = "自定义表单复制积木报表接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自定义表单id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "jimuReportId", value = "积木报表id", required = true, dataType = "string")
    })
    @PostMapping("/{id}/copyJimuReport/{jimuReportId}")
    public Result copyJimuReport(@PathVariable String id, @PathVariable String jimuReportId) {
        service.copyJimuReport(id, jimuReportId);
        return ResultGenerator.genSuccessResult(ResultCode.CREATED);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
    @ApiOperation(value = "自定义表单移除积木报表", notes = "自定义表单移除积木报表接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @PutMapping("removeJimuReport")
    public Result removeJimuReport(@RequestBody @NotNull(message = "自定义表单id列表不能为空") @Size(min = 1, message = "自定义表单id至少存在一个") List<String> ids) {
        service.removeJimuReport(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }
}
