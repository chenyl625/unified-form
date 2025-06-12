package com.jfbrother.customForm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.*;
import com.jfbrother.baseserver.enums.ConnTypeEnum;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.customForm.model.extend.CustomFormFieldModelExtend;
import com.jfbrother.customForm.model.extend.CustomFormReleaseModelExtend;
import com.jfbrother.customForm.model.param.CustomFormReleaseModelParam;
import com.jfbrother.customForm.model.search.CustomFormReleaseModelSearch;
import com.jfbrother.customForm.service.CustomFormReleaseService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;


/**
 * 自定义表单发布MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
@ApiSort(10)
@Api(tags = {"自定义表单发布接口-1.0.0-20220630"})
@Validated
@RestController("CustomFormRelease")
@RequestMapping("/api/v1/customFormRelease")
public class CustomFormReleaseController {
    @Autowired
    private CustomFormReleaseService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单发布列表获取", notes = "自定义表单发布列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<CustomFormReleaseModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, CustomFormReleaseModelSearch search) {
        PageOb<CustomFormReleaseModelExtend> pageCustomFormReleaseModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageCustomFormReleaseModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单发布列表获取，不分页，默认上限100", notes = "自定义表单发布列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<CustomFormReleaseModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, CustomFormReleaseModelSearch search) {
        List<CustomFormReleaseModelExtend> listCustomFormReleaseModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listCustomFormReleaseModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "自定义表单发布获取", notes = "自定义表单发布获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "自定义表单发布id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<CustomFormReleaseModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单发布添加", notes = "自定义表单发布添加接口")
    @ApiVersion(1)
    @PostMapping
    public Result<CustomFormReleaseModelExtend> post(@RequestBody @Valid CustomFormReleaseModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单发布全更新", notes = "自定义表单发布全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单发布id", required = true, dataType = "string")
    })
    @PutMapping(value = "/{id}")
    public Result<CustomFormReleaseModelExtend> put(@PathVariable String id, @RequestBody @Valid CustomFormReleaseModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单发布部分更新", notes = "自定义表单发布部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的自定义表单发布id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    public Result<CustomFormReleaseModelExtend> patch(@PathVariable String id, @RequestBody CustomFormReleaseModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
    @ApiOperation(value = "自定义表单发布删除", notes = "自定义表单发布删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public Result delete(@RequestBody @NotNull(message = "自定义表单发布id列表不能为空") @Size(min = 1, message = "自定义表单发布id至少存在一个") List<String> ids) {
        service.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
    @ApiOperation(value = "自定义表单发布逻辑删除", notes = "自定义表单发布逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "自定义表单发布id列表不能为空") @Size(min = 1, message = "自定义表单发布id至少存在一个") List<String> ids) {
        service.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单发布填报列表获取", notes = "自定义表单发布填报列表获取接口")
    @ApiVersion(1)
    @GetMapping("fill")
    public Result<PageOb<CustomFormReleaseModelExtend>> getFill(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, CustomFormReleaseModelSearch search) {
        PageOb<CustomFormReleaseModelExtend> pageCustomFormReleaseModel = service.getFill(search, pageable);
        return ResultGenerator.genSuccessResult(pageCustomFormReleaseModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单字段列表获取", notes = "自定义表单字段列表获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @GetMapping("/{id}/getField")
    public Result<List<CustomFormFieldModelExtend>> getField(@PathVariable String id) {
        List<CustomFormFieldModelExtend> listCustomFormFieldModel = service.getField(id);
        return ResultGenerator.genSuccessResult(listCustomFormFieldModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "自定义表单发布保存字段", notes = "自定义表单发布保存字段接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @PutMapping("/{id}/saveField")
    public Result saveField(@PathVariable String id, @RequestBody @Valid List<CustomFormFieldModelExtend> listModel) {
        service.saveField(id, listModel);
        return ResultGenerator.genSuccessResult(ResultCode.CREATED);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 15, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "接口数据获取", notes = "接口数据获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "发布表单id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "connType", value = "接口类型", required = true, dataType = "String"),
    })
    @GetMapping(value = "/{id}/connData/{connType}")
    public Result<Map<String, Object>> getConnData(@PathVariable String id, @PathVariable ConnTypeEnum connType, @RequestParam Map<String, Object> param) {
        return ResultGenerator.genSuccessResult(service.getConnDataMap(id, connType, param));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "自定义表单的填报汇总数据", notes = "自定义表单的填报汇总数据接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "发布表单id", required = true, dataType = "String"),
    })
    @GetMapping("dataAll")
    public Result<List<Map<String, Object>>> getData(CustomFormReleaseModelSearch search) {
        if (StringUtils.isEmpty(search.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "表单id不能为空.");
        }
        List<Map<String, Object>> listCustomFormReleaseModel = service.getData(search.getId());
        return ResultGenerator.genSuccessResult(listCustomFormReleaseModel);
    }

    @ApiOperation(value = "下载导入模板", notes = "下载导入模板接口", position = 10)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "发布表单id", required = true, dataType = "String"),
    })
    @GetMapping(value = "/{id}/downloadTemplate", produces = "application/octet-stream")
    public void downloadTemplate(@PathVariable String id) {
        service.downloadTemplate(id);
    }

    @ApiOperation(value = "信息导入", notes = "信息导入接口", position = 9)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "excel文件", dataType = "__File")
    })
    @PostMapping("import")
    public Result<List<String>> importInfo(@RequestParam(value = "id", required = true) String id,
                                           @RequestParam(value = "file", required = true) MultipartFile file
    ) {
        List<String> noCodeList = service.importFile(id, file);
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, noCodeList);
    }
}
