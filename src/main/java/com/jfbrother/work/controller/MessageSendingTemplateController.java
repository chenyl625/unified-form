package com.jfbrother.work.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.work.model.MessageSendingTemplateModel;
import com.jfbrother.work.model.param.MessageSendingTemplateModelParam;
import com.jfbrother.work.model.extend.MessageSendingTemplateModelExtend;
import com.jfbrother.work.model.search.MessageSendingTemplateModelSearch;
import com.jfbrother.work.service.MessageSendingTemplateService;
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
*  消息发送模版表MVC控制器类
* @author chenyl@jfbrother.com 2022-12-15
*/
@ApiSort(10)
@Api(tags={"消息发送模版表接口-1.0.0-20221215"})
@Validated
@RestController("MessageSendingTemplate")
@RequestMapping("/api/v1/messageSendingTemplate")
public class MessageSendingTemplateController {
	@Autowired
	private MessageSendingTemplateService service;

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
	@ApiOperation(value = "消息发送模版表列表获取", notes = "消息发送模版表列表获取接口")
	@ApiVersion(1)
	@GetMapping
	//@RolesAllowed({"ADMIN"})
	public Result<PageOb<MessageSendingTemplateModelExtend>> get(
		@PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
		Pageable pageable, MessageSendingTemplateModelSearch search) {
		PageOb<MessageSendingTemplateModelExtend> pageMessageSendingTemplateModel = service.get(search,pageable);
		return ResultGenerator.genSuccessResult(pageMessageSendingTemplateModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
	@ApiOperation(value = "消息发送模版表列表获取，不分页，默认上限100", notes = "消息发送模版表列表获取接口")
	@ApiVersion(1)
	@GetMapping("all")
	//@RolesAllowed({"ADMIN"})
	public Result<List<MessageSendingTemplateModelExtend>> get(
		@SortDefault(sort = "createTime", direction = Direction.DESC)
		Sort sort, MessageSendingTemplateModelSearch search) {
		List<MessageSendingTemplateModelExtend> listMessageSendingTemplateModel = service.get(search, sort);
		return ResultGenerator.genSuccessResult(listMessageSendingTemplateModel);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
	@ApiOperation(value = "消息发送模版表获取", notes = "消息发送模版表获取接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "消息发送模版表id", required = true, dataType = "string")
	})
	@GetMapping(value = "/{id}")
	public Result<MessageSendingTemplateModelExtend> get(@PathVariable String id) {
		return ResultGenerator.genSuccessResult(service.get(id));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "消息发送模版表添加", notes = "消息发送模版表添加接口")
	@ApiVersion(1)
	@PostMapping
	public Result<MessageSendingTemplateModelExtend> post(@RequestBody @Valid MessageSendingTemplateModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.post(model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "消息发送模版表全更新", notes = "消息发送模版表全更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "需要更新的消息发送模版表id", required = true, dataType = "string")
	})
	@PutMapping(value = "/{id}")
	public Result<MessageSendingTemplateModelExtend> put(@PathVariable String id, @RequestBody @Valid MessageSendingTemplateModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.put(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
	@ApiOperation(value = "消息发送模版表部分更新", notes = "消息发送模版表部分更新接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "id", value = "需要更新的消息发送模版表id", required = true, dataType = "string")
	})
	@PatchMapping(value = "/{id}")
	public Result<MessageSendingTemplateModelExtend> patch(@PathVariable String id, @RequestBody MessageSendingTemplateModelParam model) {
		return ResultGenerator.genSuccessResult(ResultCode.OK, service.patch(id, model));
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7)
	@ApiOperation(value = "消息发送模版表删除", notes = "消息发送模版表删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping
	public Result delete(@RequestBody @NotNull(message = "消息发送模版表id列表不能为空") @Size(min = 1, message = "消息发送模版表id至少存在一个") List<String> ids) {
		service.delete(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}

	@ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8)
	@ApiOperation(value = "消息发送模版表逻辑删除", notes = "消息发送模版表逻辑删除接口")
	@ApiVersion(1)
	@ApiImplicitParams({
	@ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
	})
	@DeleteMapping("logical")
	public Result deleteLogical(@RequestBody @NotNull(message = "消息发送模版表id列表不能为空") @Size(min = 1, message = "消息发送模版表id至少存在一个") List<String> ids) {
		service.deleteLogical(ids);
		return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
	}
}
