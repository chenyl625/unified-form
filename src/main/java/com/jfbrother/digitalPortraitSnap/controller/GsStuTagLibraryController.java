package com.jfbrother.digitalPortraitSnap.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.digitalPortraitSnap.model.extend.GsStuTagMajorItemModelExtend;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuTagLibraryModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GsStuTagLibraryModelExtend;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuTagMajorItemModelParam;
import com.jfbrother.digitalPortraitSnap.model.search.GsStuTagLibraryModelSearch;
import com.jfbrother.digitalPortraitSnap.model.search.GsStuTagMajorItemModelSearch;
import com.jfbrother.digitalPortraitSnap.service.GsStuTagLibraryService;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.digitalPortraitSnap.service.GsStuTagMajorItemService;
import com.jfbrother.digitalPortraitSnap.service.GsStuTagRelationService;
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
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 学生标签库MVC控制器类
 * @author xinz 2023-03-27
 */
@ApiSort(10)
@Api(tags={"学生标签库接口-1.0.0-20230327"})
@Validated
@RestController("GsStuTagLibrary")
@RequestMapping("/api/v1/gsStuTagLibrary")
public class GsStuTagLibraryController {
    @Autowired
    private GsStuTagLibraryService gsStuTagLibraryService;
    @Autowired
    private GsStuTagMajorItemService gsStuTagMajorItemService;
    @Autowired
    private GsStuTagRelationService gsStuTagRelationService;

    @ApiOperationSupport(author = "xinz", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生标签库列表获取", notes = "学生标签库列表获取接口")
    @ApiVersion(1)
    @GetMapping
    public Result<PageOb<GsStuTagLibraryModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, GsStuTagLibraryModelSearch search) {
        PageOb<GsStuTagLibraryModelExtend> pageGsStuTagLibraryModel = gsStuTagLibraryService.get(search,pageable);
        return ResultGenerator.genSuccessResult(pageGsStuTagLibraryModel);
    }

    @ApiOperationSupport(author = "xinz", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生标签库列表获取，不分页，默认上限100", notes = "学生标签库列表获取接口")
    @ApiVersion(1)
    @GetMapping("all")
    public Result<List<GsStuTagLibraryModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, GsStuTagLibraryModelSearch search) {
        List<GsStuTagLibraryModelExtend> listGsStuTagLibraryModel = gsStuTagLibraryService.get(search, sort);
        return ResultGenerator.genSuccessResult(listGsStuTagLibraryModel);
    }

    @ApiOperationSupport(author = "xinz", order = 3)
    @ApiOperation(value = "学生标签库获取", notes = "学生标签库获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学生标签库id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Result<GsStuTagLibraryModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(gsStuTagLibraryService.get(id));
    }

    @ApiOperationSupport(author = "xinz", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createId", "model.updateTime", "model.updateId", "model.deleteFlag"})
    @ApiOperation(value = "学生标签库添加", notes = "学生标签库添加接口")
    @ApiVersion(1)
    @PostMapping
    public Result<GsStuTagLibraryModelExtend> post(@RequestBody @Valid GsStuTagLibraryModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, gsStuTagLibraryService.post(model));
    }

    @ApiOperationSupport(author = "xinz", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createId", "model.deleteFlag"})
    @ApiOperation(value = "学生标签库全更新", notes = "学生标签库全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的学生标签库id", required = true, dataType = "string")
    })
    @PutMapping(value = "/{id}")
    public Result<GsStuTagLibraryModelExtend> put(@PathVariable String id, @RequestBody @Valid GsStuTagLibraryModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, gsStuTagLibraryService.put(id, model));
    }

    @ApiOperationSupport(author = "xinz", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createId", "model.deleteFlag"})
    @ApiOperation(value = "学生标签库部分更新", notes = "学生标签库部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的学生标签库id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    public Result<GsStuTagLibraryModelExtend> patch(@PathVariable String id, @RequestBody GsStuTagLibraryModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, gsStuTagLibraryService.patch(id, model));
    }

    @ApiOperationSupport(author = "xinz", order = 7)
    @ApiOperation(value = "学生标签库删除", notes = "学生标签库删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public Result delete(@RequestBody @NotNull(message = "学生标签库id列表不能为空") @Size(min = 1, message = "学生标签库id至少存在一个") List<String> ids) {
        gsStuTagLibraryService.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "xinz", order = 8)
    @ApiOperation(value = "学生标签库逻辑删除", notes = "学生标签库逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "学生标签库id列表不能为空") @Size(min = 1, message = "学生标签库id至少存在一个") List<String> ids) {
        gsStuTagLibraryService.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }


    @ApiOperationSupport(author = "xinz", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生标签大类列表获取", notes = "学生标签大类列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuTagMajorItem")
    public Result<PageOb<GsStuTagMajorItemModelExtend>> getStuTagMajorItem(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.DESC)
                    Pageable pageable, GsStuTagMajorItemModelSearch search) {
        PageOb<GsStuTagMajorItemModelExtend> pageGsStuTagMajorItemModel = gsStuTagMajorItemService.get(search,pageable);
        return ResultGenerator.genSuccessResult(pageGsStuTagMajorItemModel);
    }

    @ApiOperationSupport(author = "xinz", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生标签大类列表获取，不分页，默认上限100", notes = "学生标签大类列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuTagMajorItemAll")
    public Result<List<GsStuTagMajorItemModelExtend>> getStuTagMajorItemAll(
            @SortDefault(sort = "createTime", direction = Direction.DESC)
                    Sort sort, GsStuTagMajorItemModelSearch search) {
        List<GsStuTagMajorItemModelExtend> listGsStuTagMajorItemModel = gsStuTagMajorItemService.get(search, sort);
        return ResultGenerator.genSuccessResult(listGsStuTagMajorItemModel);
    }

    @ApiOperationSupport(author = "xinz", order = 3)
    @ApiOperation(value = "学生标签大类获取", notes = "学生标签大类获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学生标签大类id", required = true, dataType = "string")
    })
    @GetMapping(value = "/stuTagMajorItem/{id}")
    public Result<GsStuTagMajorItemModelExtend> getStuTagMajorItemById(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(gsStuTagMajorItemService.get(id));
    }

    @ApiOperationSupport(author = "xinz", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createId", "model.updateTime", "model.updateId", "model.deleteFlag"})
    @ApiOperation(value = "学生标签大类添加", notes = "学生标签大类添加接口")
    @ApiVersion(1)
    @PostMapping("addStuTagMajorItem")
    public Result<GsStuTagMajorItemModelExtend> post(@RequestBody @Valid GsStuTagMajorItemModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, gsStuTagMajorItemService.post(model));
    }

    @ApiOperationSupport(author = "xinz", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createId", "model.deleteFlag"})
    @ApiOperation(value = "学生标签大类全更新", notes = "学生标签大类全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的学生标签大类id", required = true, dataType = "string")
    })
    @PutMapping(value = "/stuTagMajorItem/{id}")
    public Result<GsStuTagMajorItemModelExtend> put(@PathVariable String id, @RequestBody @Valid GsStuTagMajorItemModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, gsStuTagMajorItemService.put(id, model));
    }

    @ApiOperationSupport(author = "xinz", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createId", "model.deleteFlag"})
    @ApiOperation(value = "学生标签大类部分更新", notes = "学生标签大类部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的学生标签大类id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/stuTagMajorItem/{id}")
    public Result<GsStuTagMajorItemModelExtend> patch(@PathVariable String id, @RequestBody GsStuTagMajorItemModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, gsStuTagMajorItemService.patch(id, model));
    }

    @ApiOperationSupport(author = "xinz", order = 7)
    @ApiOperation(value = "学生标签大类删除", notes = "学生标签大类删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("deleteStuTagMajorItem")
    public Result deleteStuTagMajorItem(@RequestBody @NotNull(message = "学生标签大类id列表不能为空") @Size(min = 1, message = "学生标签大类id至少存在一个") List<String> ids) {
        gsStuTagMajorItemService.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "xinz", order = 8)
    @ApiOperation(value = "学生标签大类逻辑删除", notes = "学生标签大类逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("deleteLogicalStuTagMajorItem")
    public Result deleteLogicalStuTagMajorItem(@RequestBody @NotNull(message = "学生标签大类id列表不能为空") @Size(min = 1, message = "学生标签大类id至少存在一个") List<String> ids) {
        gsStuTagMajorItemService.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "xinz")
    @ApiOperation(value = "查询符合标签学生列表,分页", notes = "查询符合标签学生列表,分页接口")
    @ApiVersion(1)
    @PostMapping("conformToTagStuListPage")
    public Result<Map<String,Object>> getConformToTagStuListPage(@RequestBody Map<String,Object> body) {
        Map<String,Object> map = gsStuTagLibraryService.getConformToTagStuListPage(body);
        return ResultGenerator.genSuccessResult(map);
    }

    @ApiOperationSupport(author = "xinz")
    @ApiOperation(value = "给学生标记标签", notes = "给学生标记标签接口")
    @ApiVersion(1)
    @PostMapping("markTagToStu")
    public Result<Void> markTagToStu(@RequestBody Map<String,Object> body) {
        gsStuTagLibraryService.markTagToStu(body);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperationSupport(author = "xinz")
    @ApiOperation(value = "删除学生已有标签", notes = "删除学生已有标签接口")
    @ApiVersion(1)
    @DeleteMapping("deleteStuTag")
    public Result<Void> deleteStuTag(@RequestBody Map<String,Object> body) {
        gsStuTagRelationService.deleteStuTag(body);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperationSupport(author = "xinz", ignoreParameters = {"id"})
    @ApiOperation(value = "学生标签列表,分页获取", notes = "学生标签列表,分页获取接口")
    @ApiVersion(1)
    @PostMapping("stuTagListPage")
    public Result<Map<String,Object>> getStuTagListPage(@RequestBody @NotEmpty Map<String,Object> params) {
        Map<String,Object> map = gsStuTagLibraryService.getStuTagListPage(params);
        return ResultGenerator.genSuccessResult(map);
    }

    @ApiOperationSupport(author = "xinz")
    @ApiOperation(value = "标签统计", notes = "标签统计接口")
    @ApiVersion(1)
    @GetMapping("labelStatistics")
    public Result<Map> getLabelStatistics() {
        return ResultGenerator.genSuccessResult(gsStuTagLibraryService.getLabelStatistics());
    }

}
