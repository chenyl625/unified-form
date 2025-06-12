package com.jfbrother.customForm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.customForm.model.extend.CustomFormRuleModelExtend;
import com.jfbrother.customForm.model.param.CustomFormRuleModelParam;
import com.jfbrother.customForm.model.search.CustomFormRuleModelSearch;
import com.jfbrother.customForm.service.CustomFormRuleService;
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
*  自定义表单规则MVC控制器类
* @author chenyl@jfbrother.com 2022-06-10
*/
@ApiSort(10)
@Api(tags={"自定义表单规则接口-1.0.0-20220610"})
@Validated
@RestController("CustomFormRule")
@RequestMapping("/api/v1/customFormRule")
public class CustomFormRuleController {
	@Autowired
	private CustomFormRuleService service;

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
	@ApiOperation(value = "自定义表单规则列表获取", notes = "自定义表单规则列表获取接口")
	@ApiVersion(1)
	@GetMapping
	//@RolesAllowed({"ADMIN"})
	public Result<PageOb<CustomFormRuleModelExtend>> get(
		@PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
		Pageable pageable, CustomFormRuleModelSearch search) {
		PageOb<CustomFormRuleModelExtend> pageCustomFormRuleModel = service.get(search,pageable);
		return ResultGenerator.genSuccessResult(pageCustomFormRuleModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
	@ApiOperation(value = "自定义表单规则列表获取，不分页，默认上限100", notes = "自定义表单规则列表获取接口")
	@ApiVersion(1)
	@GetMapping("all")
	//@RolesAllowed({"ADMIN"})
	public Result<List<CustomFormRuleModelExtend>> get(
		@SortDefault(sort = "createTime", direction = Direction.DESC)
		Sort sort, CustomFormRuleModelSearch search) {
		List<CustomFormRuleModelExtend> listCustomFormRuleModel = service.get(search, sort);
		return ResultGenerator.genSuccessResult(listCustomFormRuleModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
	@ApiOperation(value = "自定义表单规则获取", notes = "自定义表单规则获取接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "自定义表单规则id", required = true, dataType = "string")
	})
	@GetMapping(value = "/{id}")
	public Result<CustomFormRuleModelExtend> get(@PathVariable String id) {
		return ResultGenerator.genSuccessResult(service.get(id));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "自定义表单规则添加", notes = "自定义表单规则添加接口")
	@ApiVersion(1)
	@PostMapping
	public Result<CustomFormRuleModelExtend> post(@RequestBody @Valid CustomFormRuleModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "自定义表单规则全更新", notes = "自定义表单规则全更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "需要更新的自定义表单规则id", required = true, dataType = "string")
	})
	@PutMapping(value = "/{id}")
	public Result<CustomFormRuleModelExtend> put(@PathVariable String id, @RequestBody @Valid CustomFormRuleModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "自定义表单规则部分更新", notes = "自定义表单规则部分更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "id", value = "需要更新的自定义表单规则id", required = true, dataType = "string")
	})
	@PatchMapping(value = "/{id}")
	public Result<CustomFormRuleModelExtend> patch(@PathVariable String id, @RequestBody CustomFormRuleModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
	@ApiOperation(value = "自定义表单规则删除", notes = "自定义表单规则删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping
	public Result delete(@RequestBody @NotNull(message = "自定义表单规则id列表不能为空") @Size(min = 1, message = "自定义表单规则id至少存在一个") List<String> ids) {
		service.delete(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
	@ApiOperation(value = "自定义表单规则逻辑删除", notes = "自定义表单规则逻辑删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping("logical")
	public Result deleteLogical(@RequestBody @NotNull(message = "自定义表单规则id列表不能为空") @Size(min = 1, message = "自定义表单规则id至少存在一个") List<String> ids) {
		service.deleteLogical(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}
}
