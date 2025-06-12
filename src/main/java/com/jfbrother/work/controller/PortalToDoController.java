package com.jfbrother.work.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.work.model.PortalToDoModel;
import com.jfbrother.work.model.PortalToDoSynModel;
import com.jfbrother.work.model.request.WorkFlowTodoRequestModel;
import com.jfbrother.work.model.response.WorkFlowTodoResponseModel;
import com.jfbrother.work.service.PortalTodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ApiSort(20)
@Api(tags = {"门户待办推送-1.0.0-20221009"})
@Validated
@RestController("PortalTodo")
@RequestMapping("/api/{version}/portalTodo")
public class PortalToDoController {
    @Autowired
    private PortalTodoService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 11)
    @ApiOperation(value = "单个待办同步到校务服务", notes = "单个待办同步到校务服务推送接口")
    @ApiVersion(1)
    @PostMapping("syn")
    public Result synToPoral(@RequestBody @Valid PortalToDoSynModel model) {
        service.synToPoral(model);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 11)
    @ApiOperation(value = "推送待办到校务服务", notes = "推送待办到校务服务接口")
    @ApiVersion(1)
    @PostMapping("pushTodo")
    public Result toDoPoral(@RequestBody @Valid PortalToDoModel model) {
        service.pushToDoPoral(model);
        return ResultGenerator.genSuccessResult();
    }
}
