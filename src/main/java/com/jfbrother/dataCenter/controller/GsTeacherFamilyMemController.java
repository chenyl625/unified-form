package com.jfbrother.dataCenter.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.dataCenter.model.GsTeacherFamilyMemModel;
import com.jfbrother.dataCenter.service.GsTeacherFamilyMemService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 教师家庭成员MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-07-19
 */
@ApiSort(10)
@Api(tags = {"教师家庭成员接口-1.0.0-20220719"})
@Validated
@RestController("GsTeacherFamilyMem")
@RequestMapping("/api/v1/gsTeacherFamilyMem")
public class GsTeacherFamilyMemController {
    @Autowired
    private GsTeacherFamilyMemService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教师家庭成员列表获取", notes = "教师家庭成员列表获取接口")
    @ApiVersion(1)
    @GetMapping
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<GsTeacherFamilyMemModel>> get(
            @PageableDefault(page = 0, size = 10, sort = "idSort", direction = Direction.ASC)
                    Pageable pageable, GsTeacherFamilyMemModel search) {
        PageOb<GsTeacherFamilyMemModel> pageGsTeacherFamilyMemModel = service.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsTeacherFamilyMemModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教师家庭成员列表获取，不分页，默认上限100", notes = "教师家庭成员列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    //@RolesAllowed({"ADMIN"})
    public Result<List<GsTeacherFamilyMemModel>> get(
            @SortDefault(sort = "idSort", direction = Direction.ASC)
                    Sort sort, GsTeacherFamilyMemModel search) {
        List<GsTeacherFamilyMemModel> listGsTeacherFamilyMemModel = service.get(search, sort);
        return ResultGenerator.genSuccessResult(listGsTeacherFamilyMemModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "教师家庭成员获取", notes = "教师家庭成员获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "教师家庭成员id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<GsTeacherFamilyMemModel> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(service.get(id));
    }
}
