package com.jfbrother.echarts.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.echarts.service.EchartsDataService;
import com.jfbrother.work.model.extend.WorkFlowTaskModelExtend;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@ApiSort(10)
@Api(tags = {"图表数据接口-1.0.0-20191205"})
@Validated
@RestController("EchartsData")
@RequestMapping("/api/v1/echartsData")
public class EchartsDataController {
    @Autowired
    private EchartsDataService service;

    @ApiOperation(value = "填报数据分析", notes = "填报数据分析接口", position = 1)
    @ApiVersion(1)
    @GetMapping("getFillDataAnalysis")
    public Result<Map<String, Object>> getFillDataAnalysis() {
        return ResultGenerator.genSuccessResult(service.getFillDataAnalysis());
    }

    @ApiOperation(value = "填报效率分析", notes = "填报效率分析", position = 2)
    @ApiVersion(1)
    @GetMapping("getFillEfficiencyAnalysis")
    public Result<List<WorkFlowTaskModelExtend>> getFillEfficiencyAnalysis() {
        return ResultGenerator.genSuccessResult(service.getFillEfficiencyAnalysis());
    }

    @ApiOperation(value = "数据纠错系统分布", notes = "数据纠错系统分布接口", position = 7)
    @ApiVersion(1)
    @GetMapping("getDataErrorSystemCount")
    public Result<List<Map<String,Object>>> getDataErrorSystemCount() {
        return ResultGenerator.genSuccessResult(service.getDataErrorSystemCount());
    }
}
