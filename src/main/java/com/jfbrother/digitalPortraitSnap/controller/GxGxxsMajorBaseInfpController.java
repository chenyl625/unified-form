package com.jfbrother.digitalPortraitSnap.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.digitalPortraitSnap.model.GxGxxsMajorBaseInfpModel;
import com.jfbrother.digitalPortraitSnap.model.param.GxGxxsMajorBaseInfpModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GxGxxsMajorBaseInfpModelExtend;
import com.jfbrother.digitalPortraitSnap.model.search.GxGxxsMajorBaseInfpModelSearch;
import com.jfbrother.digitalPortraitSnap.service.GxGxxsMajorBaseInfpService;
import com.jfbrother.baseserver.version.ApiVersion;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *  全部专业基本信息MVC控制器类
 * @author hjy@jfbrother.com 2023-03-08
 */
@ApiSort(10)
@Api(tags={"全部专业基本信息接口-1.0.0-20230308"})
@Validated
@RestController("GxGxxsMajorBaseInfp")
@RequestMapping("/api/v1/gxGxxsMajorBaseInfp")
public class GxGxxsMajorBaseInfpController {
    @Autowired
    private GxGxxsMajorBaseInfpService service;

    @ApiOperationSupport(author = "hjy@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "全部专业基本信息列表获取", notes = "全部专业基本信息列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<GxGxxsMajorBaseInfpModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, GxGxxsMajorBaseInfpModelSearch search) {
        PageOb<GxGxxsMajorBaseInfpModelExtend> pageGxGxxsMajorBaseInfpModel = service.get(search,pageable);
        return ResultGenerator.genSuccessResult(pageGxGxxsMajorBaseInfpModel);
    }

    @ApiOperationSupport(author = "hjy@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "全部专业基本信息列表获取，不分页，默认上限100", notes = "全部专业基本信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<GxGxxsMajorBaseInfpModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, GxGxxsMajorBaseInfpModelSearch search) {
        List<GxGxxsMajorBaseInfpModelExtend> listGxGxxsMajorBaseInfpModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listGxGxxsMajorBaseInfpModel);
    }

    @ApiOperationSupport(author = "hjy@jfbrother.com", order = 3)
    @ApiOperation(value = "全部专业基本信息获取", notes = "全部专业基本信息获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "全部专业基本信息id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<GxGxxsMajorBaseInfpModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "hjy@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "全部专业基本信息添加", notes = "全部专业基本信息添加接口")
    @ApiVersion(1)
    @PostMapping
    public Result<GxGxxsMajorBaseInfpModelExtend> post(@RequestBody @Valid GxGxxsMajorBaseInfpModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
    }

    @ApiOperationSupport(author = "hjy@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "全部专业基本信息全更新", notes = "全部专业基本信息全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的全部专业基本信息id", required = true, dataType = "string")
    })
    @PutMapping(value = "/{id}")
    public Result<GxGxxsMajorBaseInfpModelExtend> put(@PathVariable String id, @RequestBody @Valid GxGxxsMajorBaseInfpModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
    }

    @ApiOperationSupport(author = "hjy@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "全部专业基本信息部分更新", notes = "全部专业基本信息部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的全部专业基本信息id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    public Result<GxGxxsMajorBaseInfpModelExtend> patch(@PathVariable String id, @RequestBody GxGxxsMajorBaseInfpModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
    }

    @ApiOperationSupport(author = "hjy@jfbrother.com", order = 7)
    @ApiOperation(value = "全部专业基本信息删除", notes = "全部专业基本信息删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public Result delete(@RequestBody @NotNull(message = "全部专业基本信息id列表不能为空") @Size(min = 1, message = "全部专业基本信息id至少存在一个") List<String> ids) {
        service.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "hjy@jfbrother.com", order = 8)
    @ApiOperation(value = "全部专业基本信息逻辑删除", notes = "全部专业基本信息逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "全部专业基本信息id列表不能为空") @Size(min = 1, message = "全部专业基本信息id至少存在一个") List<String> ids) {
        service.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }
}
