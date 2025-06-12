package com.jfbrother.dataCenter.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.model.other.UserIdsAndRoleIdsModelOther;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.dataCenter.model.GsTeacherBaseInfoModel;
import com.jfbrother.dataCenter.model.extend.GsTeacherBaseInfoModelExtend;
import com.jfbrother.dataCenter.service.GsTeacherBaseInfoService;
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
import java.util.Map;


/**
 * 教师基本信息MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-07-18
 */
@ApiSort(10)
@Api(tags = {"教师基本信息接口-1.0.0-20220718"})
@Validated
@RestController("GsTeacherBaseInfo")
@RequestMapping("/api/v1/gsTeacherBaseInfo")
public class GsTeacherBaseInfoController {
    @Autowired
    private GsTeacherBaseInfoService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教师基本信息列表获取", notes = "教师基本信息列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<GsTeacherBaseInfoModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "sortNum", direction = Direction.DESC)
                    Pageable pageable, GsTeacherBaseInfoModel search) {
        PageOb<GsTeacherBaseInfoModelExtend> pageGsTeacherBaseInfoModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsTeacherBaseInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教师基本信息列表获取，不分页，默认上限100", notes = "教师基本信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<GsTeacherBaseInfoModel>> get(
            @SortDefault(sort = "sortNum", direction = Direction.DESC)
                    Sort sort, GsTeacherBaseInfoModel search) {
        List<GsTeacherBaseInfoModel> listGsTeacherBaseInfoModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listGsTeacherBaseInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "教师基本信息获取", notes = "教师基本信息获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "教师基本信息id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<GsTeacherBaseInfoModel> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "教师和学生账号自动生成", notes = "教师和学生账号自动生成接口")
    @ApiVersion(1)
    @PostMapping("autoCreate")
    public Result autoCreate() {
        service.autoCreate();
        return ResultGenerator.genSuccessResult(ResultCode.CREATED);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "现任职务列表获取", notes = "现任职务列表获取接口")
    @ApiVersion(1)
    @GetMapping("curPosition")
    public Result<List<Map<String, String>>> getCurPosition() {
        List<Map<String, String>> list = service.getCurPosition();
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "教师批量赋权", notes = "教师批量赋权接口")
    @ApiVersion(1)
    @PutMapping("setAuth")
    public Result setAuth(UserIdsAndRoleIdsModelOther model) {
        service.setAuth(model);
        return ResultGenerator.genSuccessResult(ResultCode.OK);
    }
}
