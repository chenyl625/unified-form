package com.jfbrother.customForm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.customForm.model.extend.CustomFormReleaseTaskModelExtend;
import com.jfbrother.customForm.model.param.CustomFormReleaseTaskModelParam;
import com.jfbrother.customForm.model.search.CustomFormReleaseTaskModelSearch;
import com.jfbrother.customForm.service.CustomFormReleaseTaskService;
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
*  自定义表单发布环节MVC控制器类
* @author chenyl@jfbrother.com 2022-07-04
*/
@ApiSort(10)
@Api(tags={"自定义表单发布环节接口-1.0.0-20220704"})
@Validated
@RestController("CustomFormReleaseTask")
@RequestMapping("/api/v1/customFormReleaseTask")
public class CustomFormReleaseTaskController {
	@Autowired
	private CustomFormReleaseTaskService service;

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
	@ApiOperation(value = "自定义表单发布环节列表获取", notes = "自定义表单发布环节列表获取接口")
	@ApiVersion(1)
	@GetMapping
	//@RolesAllowed({"ADMIN"})
	public Result<PageOb<CustomFormReleaseTaskModelExtend>> get(
		@PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
		Pageable pageable, CustomFormReleaseTaskModelSearch search) {
		PageOb<CustomFormReleaseTaskModelExtend> pageCustomFormReleaseTaskModel = service.get(search,pageable);
		return ResultGenerator.genSuccessResult(pageCustomFormReleaseTaskModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
	@ApiOperation(value = "自定义表单发布环节列表获取，不分页，默认上限100", notes = "自定义表单发布环节列表获取接口")
	@ApiVersion(1)
	@GetMapping("all")
	//@RolesAllowed({"ADMIN"})
	public Result<List<CustomFormReleaseTaskModelExtend>> get(
		@SortDefault(sort = "createTime", direction = Direction.DESC)
		Sort sort, CustomFormReleaseTaskModelSearch search) {
		List<CustomFormReleaseTaskModelExtend> listCustomFormReleaseTaskModel = service.get(search, sort);
		return ResultGenerator.genSuccessResult(listCustomFormReleaseTaskModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
	@ApiOperation(value = "自定义表单发布环节获取", notes = "自定义表单发布环节获取接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "自定义表单发布环节id", required = true, dataType = "string")
	})
	@GetMapping(value = "/{id}")
	public Result<CustomFormReleaseTaskModelExtend> get(@PathVariable String id) {
		return ResultGenerator.genSuccessResult(service.get(id));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "自定义表单发布环节添加", notes = "自定义表单发布环节添加接口")
	@ApiVersion(1)
	@PostMapping
	public Result<CustomFormReleaseTaskModelExtend> post(@RequestBody @Valid CustomFormReleaseTaskModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "自定义表单发布环节全更新", notes = "自定义表单发布环节全更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "需要更新的自定义表单发布环节id", required = true, dataType = "string")
	})
	@PutMapping(value = "/{id}")
	public Result<CustomFormReleaseTaskModelExtend> put(@PathVariable String id, @RequestBody @Valid CustomFormReleaseTaskModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "自定义表单发布环节部分更新", notes = "自定义表单发布环节部分更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "id", value = "需要更新的自定义表单发布环节id", required = true, dataType = "string")
	})
	@PatchMapping(value = "/{id}")
	public Result<CustomFormReleaseTaskModelExtend> patch(@PathVariable String id, @RequestBody CustomFormReleaseTaskModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
	@ApiOperation(value = "自定义表单发布环节删除", notes = "自定义表单发布环节删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping
	public Result delete(@RequestBody @NotNull(message = "自定义表单发布环节id列表不能为空") @Size(min = 1, message = "自定义表单发布环节id至少存在一个") List<String> ids) {
		service.delete(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
	@ApiOperation(value = "自定义表单发布环节逻辑删除", notes = "自定义表单发布环节逻辑删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping("logical")
	public Result deleteLogical(@RequestBody @NotNull(message = "自定义表单发布环节id列表不能为空") @Size(min = 1, message = "自定义表单发布环节id至少存在一个") List<String> ids) {
		service.deleteLogical(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}
}
