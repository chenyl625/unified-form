package com.jfbrother.standard.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.standard.model.StandardDataSourceModel;
import com.jfbrother.standard.model.param.StandardDataSourceModelParam;
import com.jfbrother.standard.model.extend.StandardDataSourceModelExtend;
import com.jfbrother.standard.model.search.StandardDataSourceModelSearch;
import com.jfbrother.standard.service.StandardDataSourceService;
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
*  数据来源标准库MVC控制器类
* @author chenyl@jfbrother.com 2022-08-04
*/
@ApiSort(10)
@Api(tags={"数据来源标准库接口-1.0.0-20220804"})
@Validated
@RestController("StandardDataSource")
@RequestMapping("/api/v1/standardDataSource")
public class StandardDataSourceController {
	@Autowired
	private StandardDataSourceService service;

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
	@ApiOperation(value = "数据来源标准库列表获取", notes = "数据来源标准库列表获取接口")
	@ApiVersion(1)
	@GetMapping
	//@RolesAllowed({"ADMIN"})
	public Result<PageOb<StandardDataSourceModelExtend>> get(
		@PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
		Pageable pageable, StandardDataSourceModelSearch search) {
		PageOb<StandardDataSourceModelExtend> pageStandardDataSourceModel = service.get(search,pageable);
		return ResultGenerator.genSuccessResult(pageStandardDataSourceModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
	@ApiOperation(value = "数据来源标准库列表获取，不分页，默认上限100", notes = "数据来源标准库列表获取接口")
	@ApiVersion(1)
	@GetMapping("all")
	//@RolesAllowed({"ADMIN"})
	public Result<List<StandardDataSourceModelExtend>> get(
		@SortDefault(sort = "createTime", direction = Direction.DESC)
		Sort sort, StandardDataSourceModelSearch search) {
		List<StandardDataSourceModelExtend> listStandardDataSourceModel = service.get(search, sort);
		return ResultGenerator.genSuccessResult(listStandardDataSourceModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
	@ApiOperation(value = "数据来源标准库获取", notes = "数据来源标准库获取接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "数据来源标准库id", required = true, dataType = "string")
	})
	@GetMapping(value = "/{id}")
	public Result<StandardDataSourceModelExtend> get(@PathVariable String id) {
		return ResultGenerator.genSuccessResult(service.get(id));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "数据来源标准库添加", notes = "数据来源标准库添加接口")
	@ApiVersion(1)
	@PostMapping
	public Result<StandardDataSourceModelExtend> post(@RequestBody @Valid StandardDataSourceModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "数据来源标准库全更新", notes = "数据来源标准库全更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "需要更新的数据来源标准库id", required = true, dataType = "string")
	})
	@PutMapping(value = "/{id}")
	public Result<StandardDataSourceModelExtend> put(@PathVariable String id, @RequestBody @Valid StandardDataSourceModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "数据来源标准库部分更新", notes = "数据来源标准库部分更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "id", value = "需要更新的数据来源标准库id", required = true, dataType = "string")
	})
	@PatchMapping(value = "/{id}")
	public Result<StandardDataSourceModelExtend> patch(@PathVariable String id, @RequestBody StandardDataSourceModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
	@ApiOperation(value = "数据来源标准库删除", notes = "数据来源标准库删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping
	public Result delete(@RequestBody @NotNull(message = "数据来源标准库id列表不能为空") @Size(min = 1, message = "数据来源标准库id至少存在一个") List<String> ids) {
		service.delete(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
	@ApiOperation(value = "数据来源标准库逻辑删除", notes = "数据来源标准库逻辑删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping("logical")
	public Result deleteLogical(@RequestBody @NotNull(message = "数据来源标准库id列表不能为空") @Size(min = 1, message = "数据来源标准库id至少存在一个") List<String> ids) {
		service.deleteLogical(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}
}
