package com.jfbrother.work.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.work.model.MessageSendingDataModel;
import com.jfbrother.work.model.param.MessageSendingDataModelParam;
import com.jfbrother.work.model.extend.MessageSendingDataModelExtend;
import com.jfbrother.work.model.search.MessageSendingDataModelSearch;
import com.jfbrother.work.service.MessageSendingDataService;
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
*  消息发送数据管理表MVC控制器类
* @author chenyl@jfbrother.com 2022-12-15
*/
@ApiSort(10)
@Api(tags={"消息发送数据管理表接口-1.0.0-20221215"})
@Validated
@RestController("MessageSendingData")
@RequestMapping("/api/v1/messageSendingData")
public class MessageSendingDataController {
	@Autowired
	private MessageSendingDataService service;

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
	@ApiOperation(value = "消息发送数据管理表列表获取", notes = "消息发送数据管理表列表获取接口")
	@ApiVersion(1)
	@GetMapping
	//@RolesAllowed({"ADMIN"})
	public Result<PageOb<MessageSendingDataModelExtend>> get(
		@PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
		Pageable pageable, MessageSendingDataModelSearch search) {
		PageOb<MessageSendingDataModelExtend> pageMessageSendingDataModel = service.get(search,pageable);
		return ResultGenerator.genSuccessResult(pageMessageSendingDataModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
	@ApiOperation(value = "消息发送数据管理表列表获取，不分页，默认上限100", notes = "消息发送数据管理表列表获取接口")
	@ApiVersion(1)
	@GetMapping("all")
	//@RolesAllowed({"ADMIN"})
	public Result<List<MessageSendingDataModelExtend>> get(
		@SortDefault(sort = "createTime", direction = Direction.DESC)
		Sort sort, MessageSendingDataModelSearch search) {
		List<MessageSendingDataModelExtend> listMessageSendingDataModel = service.get(search, sort);
		return ResultGenerator.genSuccessResult(listMessageSendingDataModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
	@ApiOperation(value = "消息发送数据管理表获取", notes = "消息发送数据管理表获取接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "消息发送数据管理表id", required = true, dataType = "string")
	})
	@GetMapping(value = "/{id}")
	public Result<MessageSendingDataModelExtend> get(@PathVariable String id) {
		return ResultGenerator.genSuccessResult(service.get(id));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "消息发送数据管理表添加", notes = "消息发送数据管理表添加接口")
	@ApiVersion(1)
	@PostMapping
	public Result<MessageSendingDataModelExtend> post(@RequestBody @Valid MessageSendingDataModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "消息发送数据管理表全更新", notes = "消息发送数据管理表全更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "需要更新的消息发送数据管理表id", required = true, dataType = "string")
	})
	@PutMapping(value = "/{id}")
	public Result<MessageSendingDataModelExtend> put(@PathVariable String id, @RequestBody @Valid MessageSendingDataModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "消息发送数据管理表部分更新", notes = "消息发送数据管理表部分更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "id", value = "需要更新的消息发送数据管理表id", required = true, dataType = "string")
	})
	@PatchMapping(value = "/{id}")
	public Result<MessageSendingDataModelExtend> patch(@PathVariable String id, @RequestBody MessageSendingDataModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
	@ApiOperation(value = "消息发送数据管理表删除", notes = "消息发送数据管理表删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping
	public Result delete(@RequestBody @NotNull(message = "消息发送数据管理表id列表不能为空") @Size(min = 1, message = "消息发送数据管理表id至少存在一个") List<String> ids) {
		service.delete(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
	@ApiOperation(value = "消息发送数据管理表逻辑删除", notes = "消息发送数据管理表逻辑删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping("logical")
	public Result deleteLogical(@RequestBody @NotNull(message = "消息发送数据管理表id列表不能为空") @Size(min = 1, message = "消息发送数据管理表id至少存在一个") List<String> ids) {
		service.deleteLogical(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}
}
