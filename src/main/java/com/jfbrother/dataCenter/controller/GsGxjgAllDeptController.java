package com.jfbrother.dataCenter.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.dataCenter.model.extend.GsGxjgAllDeptModelExtend;
import com.jfbrother.dataCenter.model.search.GsGxjgAllDeptModelSearch;
import com.jfbrother.dataCenter.service.GsGxjgAllDeptService;
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

import java.util.List;


/**
 * 人事中历史所有部门信息MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-09-01
 */
@ApiSort(10)
@Api(tags = {"人事中历史所有部门信息接口-1.0.0-20220901"})
@Validated
@RestController("GsGxjgAllDept")
@RequestMapping("/api/v1/gsGxjgAllDept")
public class GsGxjgAllDeptController {
    @Autowired
    private GsGxjgAllDeptService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "人事中历史所有部门信息列表获取", notes = "人事中历史所有部门信息列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<GsGxjgAllDeptModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, GsGxjgAllDeptModelSearch search) {
        PageOb<GsGxjgAllDeptModelExtend> pageGsGxjgAllDeptModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxjgAllDeptModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "人事中历史所有部门信息列表获取，不分页，默认上限100", notes = "人事中历史所有部门信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<GsGxjgAllDeptModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, GsGxjgAllDeptModelSearch search) {
        List<GsGxjgAllDeptModelExtend> listGsGxjgAllDeptModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxjgAllDeptModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "人事中历史所有部门信息获取", notes = "人事中历史所有部门信息获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "人事中历史所有部门信息id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<GsGxjgAllDeptModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "部门同步", notes = "部门同步接口")
    @ApiVersion(1)
    @PutMapping("syncDept")
    public Result syncDept() {
        service.syncDept();
        return ResultGenerator.genSuccessResult(ResultCode.OK);
    }
}
