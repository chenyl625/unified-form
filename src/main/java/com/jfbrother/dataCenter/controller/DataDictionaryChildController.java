package com.jfbrother.dataCenter.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.dataCenter.model.extend.DataDictionaryChildModelExtend;
import com.jfbrother.dataCenter.model.param.DataDictionaryChildModelParam;
import com.jfbrother.dataCenter.model.search.DataDictionaryChildModelSearch;
import com.jfbrother.dataCenter.service.DataDictionaryChildService;
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
*  数据字典子表MVC控制器类
* @author chenyl@jfbrother.com 2022-09-01
*/
@ApiSort(10)
@Api(tags={"数据字典子表接口-1.0.0-20220901"})
@Validated
@RestController("DataDictionaryChild")
@RequestMapping("/api/v1/dataDictionaryChild")
public class DataDictionaryChildController {
	@Autowired
	private DataDictionaryChildService service;

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
	@ApiOperation(value = "数据字典子表列表获取", notes = "数据字典子表列表获取接口")
	@ApiVersion(1)
	@GetMapping
	//@RolesAllowed({"ADMIN"})
	public Result<PageOb<DataDictionaryChildModelExtend>> get(
		@PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
		Pageable pageable, DataDictionaryChildModelSearch search) {
		PageOb<DataDictionaryChildModelExtend> pageDataDictionaryChildModel = service.get(search,pageable);
		return ResultGenerator.genSuccessResult(pageDataDictionaryChildModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
	@ApiOperation(value = "数据字典子表列表获取，不分页，默认上限100", notes = "数据字典子表列表获取接口")
	@ApiVersion(1)
	@GetMapping("all")
	//@RolesAllowed({"ADMIN"})
	public Result<List<DataDictionaryChildModelExtend>> get(
		@SortDefault(sort = "createTime", direction = Direction.DESC)
		Sort sort, DataDictionaryChildModelSearch search) {
		List<DataDictionaryChildModelExtend> listDataDictionaryChildModel = service.get(search, sort);
		return ResultGenerator.genSuccessResult(listDataDictionaryChildModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
	@ApiOperation(value = "数据字典子表获取", notes = "数据字典子表获取接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "数据字典子表id", required = true, dataType = "string")
	})
	@GetMapping(value = "/{id}")
	public Result<DataDictionaryChildModelExtend> get(@PathVariable String id) {
		return ResultGenerator.genSuccessResult(service.get(id));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "数据字典子表添加", notes = "数据字典子表添加接口")
	@ApiVersion(1)
	@PostMapping
	public Result<DataDictionaryChildModelExtend> post(@RequestBody @Valid DataDictionaryChildModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "数据字典子表全更新", notes = "数据字典子表全更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "需要更新的数据字典子表id", required = true, dataType = "string")
	})
	@PutMapping(value = "/{id}")
	public Result<DataDictionaryChildModelExtend> put(@PathVariable String id, @RequestBody @Valid DataDictionaryChildModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "数据字典子表部分更新", notes = "数据字典子表部分更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "id", value = "需要更新的数据字典子表id", required = true, dataType = "string")
	})
	@PatchMapping(value = "/{id}")
	public Result<DataDictionaryChildModelExtend> patch(@PathVariable String id, @RequestBody DataDictionaryChildModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
	@ApiOperation(value = "数据字典子表删除", notes = "数据字典子表删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping
	public Result delete(@RequestBody @NotNull(message = "数据字典子表id列表不能为空") @Size(min = 1, message = "数据字典子表id至少存在一个") List<String> ids) {
		service.delete(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
	@ApiOperation(value = "数据字典子表逻辑删除", notes = "数据字典子表逻辑删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping("logical")
	public Result deleteLogical(@RequestBody @NotNull(message = "数据字典子表id列表不能为空") @Size(min = 1, message = "数据字典子表id至少存在一个") List<String> ids) {
		service.deleteLogical(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}
}
