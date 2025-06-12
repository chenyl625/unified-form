package com.jfbrother.digitalPortraitSnap.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.digitalPortraitSnap.model.extend.*;
import com.jfbrother.digitalPortraitSnap.model.param.GsDataUseReferenceModelParam;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuLeaveMsgModelParam;
import com.jfbrother.digitalPortraitSnap.model.search.*;
import com.jfbrother.digitalPortraitSnap.service.DigitalPortraitSnapService;
import com.jfbrother.digitalPortraitSnap.service.GsDataUseReferenceService;
import com.jfbrother.student.model.GsImportHikFaceInfoModel;
import com.jfbrother.student.model.extend.*;
import com.jfbrother.student.model.search.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

/**
 * 学生数字快照
 *
 * @author xinz 2023-03-07
 */
@ApiSort(10)
@Api(tags = {"学生数字快照接口-1.0.0-2023-03-07"})
@Validated
@RestController("DigitalPortraitSnap")
@RequestMapping("/api/v1/digitalPortraitSnap")
public class DigitalPortraitSnapController {
    @Autowired
    private DigitalPortraitSnapService service;
    @Autowired
    private GsDataUseReferenceService gsDataUseReferenceService;

    @ApiOperationSupport(author = "xinz", order = 3)
    @ApiOperation(value = "海康人脸照片数据获取", notes = "海康人脸照片数据获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{userId}/hikFace2")
    public Result<GsImportHikFaceInfoModel> getHikFace2(@PathVariable String userId) {
        return ResultGenerator.genSuccessResult(service.getHikFace2(userId));
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "快照系统-在校生列表获取，不分页，默认上限100", notes = "快照系统-在校生列表获取")
    @ApiVersion(1)
    @GetMapping("stuBaseEntireAll")
    public Result<List<GsGxxsStuBaseEntireModelExtend>> getStuBaseEntire(
            @SortDefault(sort = "xh", direction = Sort.Direction.DESC)
                    Sort sort, GsGxxsStuBaseEntireModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuBaseEntireModelExtend> listGsGxxsStuBaseEntireModel = service.getStuBaseEntire(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuBaseEntireModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生等级考试情况列表获取，不分页，默认上限100", notes = "学生等级考试情况列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuLevelEaxmInfoAll")
    public Result<List<GsGxxsStuLevelEaxmInfoModelExtend>> getStuLevelEaxmInfo(
            @SortDefault(sort = "signTime", direction = Sort.Direction.DESC)
                    Sort sort, GsGxxsStuLevelEaxmInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuLevelEaxmInfoModelExtend> listGsGxxsStuLevelEaxmInfoModel = service.getStuLevelEaxmInfo(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuLevelEaxmInfoModel);
    }

    @ApiOperationSupport(author = "xinz", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生寝室晚归签到列表获取，不分页，默认上限100", notes = "学生寝室晚归签到列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuDormNightSignAll")
    public Result<List<GsStuDormNightSignModelExtend>> getStuDormNightSignAll(
            @SortDefault(sort = "createTime", direction = Sort.Direction.DESC)
                    Sort sort, GsStuDormNightSignModelSearch search) {
        List<GsStuDormNightSignModelExtend> listGsStuDormNightSignModel = service.getStuDormNightSignAll(search, sort);
        return ResultGenerator.genSuccessResult(listGsStuDormNightSignModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "个人消费记录列表获取", notes = "个人消费记录列表获取接口")
    @ApiVersion(1)
    @GetMapping("personConsume")
    public Result<PageOb<GsYktxtPersonConsumeModelExtend>> getPersonConsume(
            @PageableDefault(page = 0, size = 10, sort = "dealtime", direction = Sort.Direction.DESC)
                    Pageable pageable, GsYktxtPersonConsumeModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsYktxtPersonConsumeModelExtend> pageGsYktxtPersonConsumeModel = service.getPersonConsume(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsYktxtPersonConsumeModel);
    }

    @ApiOperationSupport(author = "xinz", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "个人消费记录列表获取，不分页，默认上限100", notes = "个人消费记录列表获取接口")
    @ApiVersion(1)
    @GetMapping("personConsumeAll")
    public Result<List<GsYktxtPersonConsumeModelExtend>> getPersonConsume(
            @SortDefault(sort = "dealtime", direction = Sort.Direction.DESC)
                    Sort sort, GsYktxtPersonConsumeModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsYktxtPersonConsumeModelExtend> listGsYktxtPersonConsumeModel = service.getPersonConsume(search, sort);
        return ResultGenerator.genSuccessResult(listGsYktxtPersonConsumeModel);
    }

    @ApiOperationSupport(author = "xinz", order = 2)
    @ApiOperation(value = "本年度消费超过多少学生(全校)", notes = "本年度消费超过多少学生(全校)")
    @ApiVersion(1)
    @GetMapping("consumMoreThanHowManyStudentsThisYear")
    public Result getConsumMoreThanHowManyStudentsThisYear(GsYktxtPersonConsumeModelSearch search) {
        JSONObject jsonObject = service.getConsumMoreThanHowManyStudentsThisYear(search);
        return ResultGenerator.genSuccessResult(jsonObject);
    }

    @ApiOperationSupport(author = "xinz", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生寝室卫生检查列表获取，不分页，默认上限100", notes = "学生寝室卫生检查列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuDormHealthCheckAll")
    public Result<List<GsStuDormHealthCheckModelExtend>> getStuDormHealthCheckAll(
            @SortDefault(sort = "checkDate", direction = Sort.Direction.DESC)
                    Sort sort, GsStuDormHealthCheckModelSearch search) {
        List<GsStuDormHealthCheckModelExtend> listGsStuDormHealthCheckModel = service.getStuDormHealthCheckAll(search, sort);
        return ResultGenerator.genSuccessResult(listGsStuDormHealthCheckModel);
    }

    @ApiOperationSupport(author = "xinz", order = 2)
    @ApiOperation(value = "绩点，班内排名", notes = "绩点，班内排名")
    @ApiVersion(1)
    @GetMapping("intraClassRanking")
    public Result intraClassRanking(GsGxxsStuCourseScoreModelSearch search) {
        JSONObject jsonObject = service.getIntraClassRanking(search);
        return ResultGenerator.genSuccessResult(jsonObject);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生课程成绩表列表获取，不分页，默认上限100", notes = "学生课程成绩表列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuCourseScoreAll")
    public Result<List<GsGxxsStuCourseScoreModelExtend>> getStuCourseScore(
            @SortDefault(sort = "xn", direction = Sort.Direction.DESC)
                    Sort sort, GsGxxsStuCourseScoreModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuCourseScoreModelExtend> listGsGxxsStuCourseScoreModel = service.getStuCourseScore(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuCourseScoreModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "办事申请表列表获取，不分页，默认上限100", notes = "办事申请表列表获取接口")
    @ApiVersion(1)
    @GetMapping("applyInfoAll")
    public Result<List<GsGxbgApplyInfoModelExtend>> getApplyInfo(
            @SortDefault(sort = "submitDate", direction = Sort.Direction.DESC)
                    Sort sort, GsGxbgApplyInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxbgApplyInfoModelExtend> listGsGxbgApplyInfoModel = service.getApplyInfo(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxbgApplyInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "三创学分申请详情列表获取，不分页，默认上限100", notes = "三创学分申请详情列表获取接口")
    @ApiVersion(1)
    @GetMapping("scxfApplyDetailAll")
    public Result<List<GsGxxsScxfApplyDetailModelExtend>> getScxfApplyDetail(
            @SortDefault(sort = "applyTime", direction = Sort.Direction.DESC)
                    Sort sort, GsGxxsScxfApplyDetailModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsScxfApplyDetailModelExtend> listGsGxxsScxfApplyDetailModel = service.getScxfApplyDetail(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsScxfApplyDetailModel);
    }

    @ApiOperationSupport(author = "xinz", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生家长/教师留言列表获取", notes = "学生家长/教师留言列表获取接口")
    @ApiVersion(1)
    @GetMapping("leaveMsg")
    public Result<PageOb<GsStuLeaveMsgModelExtend>> getLeaveMsg(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Sort.Direction.DESC)
                    Pageable pageable, GsStuLeaveMsgModelSearch search) {
        PageOb<GsStuLeaveMsgModelExtend> pageGsStuLeaveMsgModel = service.getLeaveMsg(search,pageable);
        return ResultGenerator.genSuccessResult(pageGsStuLeaveMsgModel);
    }

    @ApiOperationSupport(author = "xinz", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createUser", "model.updateTime", "model.updateUser"})
    @ApiOperation(value = "学生家长/教师留言添加", notes = "学生家长/教师留言添加接口")
    @ApiVersion(1)
    @PostMapping("addLeaveMsg")
    public Result<GsStuLeaveMsgModelExtend> addLeaveMsg(@RequestBody @Valid GsStuLeaveMsgModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, service.addLeaveMsg(model));
    }

    @ApiOperationSupport(author = "xinz", order = 8)
    @ApiOperation(value = "学生家长/教师留言逻辑删除", notes = "学生家长/教师留言逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("logical")
    public Result deleteLogical(@RequestBody @NotNull(message = "学生家长/教师留言id列表不能为空") @Size(min = 1, message = "学生家长/教师留言id至少存在一个") List<String> ids) {
        service.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "xinz", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生-家长绑定历史信息列表获取，不分页，默认上限100", notes = "学生-家长绑定历史信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuParentsBindHistoryAll")
    public Result<List<GsStuParentsBindHistoryModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Sort.Direction.DESC)
                    Sort sort, GsStuParentsBindHistoryModelSearch search) {
        List<GsStuParentsBindHistoryModelExtend> listGsStuParentsBindHistoryModel = service.getStuParentsBindHistoryAll(search, sort);
        return ResultGenerator.genSuccessResult(listGsStuParentsBindHistoryModel);
    }

    @ApiOperationSupport(author = "xinz")
    @ApiOperation(value = "通过工号获取在校班级班主任/辅导员信息", notes = "通过工号获取在校班级班主任/辅导员信息接口")
    @ApiVersion(1)
    @GetMapping("teacherInfoByCode")
    public Result<Map<String,Object>> getTeacherInfoByCode(@RequestParam String code) {
        Map<String,Object> map = service.getTeacherInfoByCode(code);
        return ResultGenerator.genSuccessResult(map);
    }

    @ApiOperationSupport(author = "xinz")
    @ApiOperation(value = "获取教师未读留言数", notes = "获取教师未读留言数接口")
    @ApiVersion(1)
    @GetMapping("notReadLeaveMsgCount")
    public Result<Integer> getNotReadLeaveMsgCount(@RequestParam String gh,
                                                   @RequestParam int role) {
        return ResultGenerator.genSuccessResult(service.getNotReadLeaveMsgCount(gh,role));
    }

    @ApiOperationSupport(author = "xinz")
    @ApiOperation(value = "教师获取各学生留言分页列表", notes = "教师获取各学生留言分页列表接口")
    @ApiVersion(1)
    @GetMapping("stuMsgPageList")
    public Result<Map<String,Object>> getStuMsgPageList(@RequestParam String gh,
                                                        @RequestParam int role,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        Map<String,Object> map = service.getStuMsgPageList(gh,role,page,size);
        return ResultGenerator.genSuccessResult(map);
    }

    @ApiOperationSupport(author = "xinz")
    @ApiOperation(value = "教师/家长已读留言", notes = "教师/家长已读留言接口")
    @ApiVersion(1)
    @PutMapping("updateReadOrNot")
    public Result<Void> updateReadOrNot(@RequestBody @NotEmpty Map<String,Object> params) {
        if(StringUtils.isEmpty(params.get("role"))
                || StringUtils.isEmpty(params.get("aboutStuCode"))
                || StringUtils.isEmpty(params.get("timeStamp"))){
            return ResultGenerator.genFailResult("入参参数缺失,角色、关于谁的留言(学号)、时间戳");
        }
        service.updateReadOrNot(params);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperationSupport(author = "xinz")
    @ApiOperation(value = "教师获取学生快照分页", notes = "教师获取学生快照分页接口")
    @ApiVersion(1)
    @GetMapping("stuSnapShot")
    public Result<PageOb<Map>> getStuSnapShot(
            @PageableDefault(page = 0, size = 10, sort = "xh", direction = Sort.Direction.ASC)
                    Pageable pageable, GsGxxsStuBaseEntireModelSearch search, @RequestParam @NotNull(message = "教师工号不能为空") String gh) {
        PageOb<Map> page = service.getStuSnapShot(gh,search, pageable);
        return ResultGenerator.genSuccessResult(page);
    }

    @ApiOperationSupport(author = "xinz")
    @ApiOperation(value = "获取家长未读留言数", notes = "获取家长未读留言数接口")
    @ApiVersion(1)
    @GetMapping("parentNotReadLeaveMsgCount")
    public Result<Integer> getParentNotReadLeaveMsgCount(@RequestParam String aboutStuCode) {
        return ResultGenerator.genSuccessResult(service.getParentNotReadLeaveMsgCount(aboutStuCode));
    }

    @ApiOperationSupport(author = "xinz", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "数据使用参照,快照系统取数依据列表获取", notes = "数据使用参照,快照系统取数依据列表获取接口")
    @ApiVersion(1)
    @GetMapping("dataUseReference")
    public Result<PageOb<GsDataUseReferenceModelExtend>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Sort.Direction.DESC)
                    Pageable pageable, GsDataUseReferenceModelSearch search) {
        PageOb<GsDataUseReferenceModelExtend> pageGsDataUseReferenceModel = gsDataUseReferenceService.get(search,pageable);
        return ResultGenerator.genSuccessResult(pageGsDataUseReferenceModel);
    }

    @ApiOperationSupport(author = "xinz", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "数据使用参照,快照系统取数依据列表获取，不分页，默认上限100", notes = "数据使用参照,快照系统取数依据列表获取接口")
    @ApiVersion(1)
    @GetMapping("dataUseReferenceAll")
    public Result<List<GsDataUseReferenceModelExtend>> get(
            @SortDefault(sort = "createTime", direction = Sort.Direction.DESC)
                    Sort sort, GsDataUseReferenceModelSearch search) {
        List<GsDataUseReferenceModelExtend> listGsDataUseReferenceModel = gsDataUseReferenceService.get(search, sort);
        return ResultGenerator.genSuccessResult(listGsDataUseReferenceModel);
    }

    @ApiOperationSupport(author = "xinz", order = 3)
    @ApiOperation(value = "数据使用参照,快照系统取数依据获取", notes = "数据使用参照,快照系统取数依据获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "数据使用参照,快照系统取数依据id", required = true, dataType = "string")
    })
    @GetMapping(value = "/dataUseReference/{id}")
    public Result<GsDataUseReferenceModelExtend> get(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(gsDataUseReferenceService.get(id));
    }

    @ApiOperationSupport(author = "xinz", order = 4, ignoreParameters = {"model.id", "model.createTime", "model.createId", "model.updateTime", "model.updateId", "model.deleteFlag"})
    @ApiOperation(value = "数据使用参照,快照系统取数依据添加", notes = "数据使用参照,快照系统取数依据添加接口")
    @ApiVersion(1)
    @PostMapping("addDataUseReference")
    public Result<GsDataUseReferenceModelExtend> post(@RequestBody @Valid GsDataUseReferenceModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, gsDataUseReferenceService.post(model));
    }

    @ApiOperationSupport(author = "xinz", order = 5, ignoreParameters = {"model.id", "model.createTime", "model.createId", "model.deleteFlag"})
    @ApiOperation(value = "数据使用参照,快照系统取数依据全更新", notes = "数据使用参照,快照系统取数依据全更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的数据使用参照,快照系统取数依据id", required = true, dataType = "string")
    })
    @PutMapping(value = "/dataUseReference/{id}")
    public Result<GsDataUseReferenceModelExtend> put(@PathVariable String id, @RequestBody @Valid GsDataUseReferenceModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, gsDataUseReferenceService.put(id, model));
    }

    @ApiOperationSupport(author = "xinz", order = 6, ignoreParameters = {"model.id", "model.createTime", "model.createId", "model.deleteFlag"})
    @ApiOperation(value = "数据使用参照,快照系统取数依据部分更新", notes = "数据使用参照,快照系统取数依据部分更新接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的数据使用参照,快照系统取数依据id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/dataUseReference/{id}")
    public Result<GsDataUseReferenceModelExtend> patch(@PathVariable String id, @RequestBody GsDataUseReferenceModelParam model) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, gsDataUseReferenceService.patch(id, model));
    }

    @ApiOperationSupport(author = "xinz", order = 7)
    @ApiOperation(value = "数据使用参照,快照系统取数依据删除", notes = "数据使用参照,快照系统取数依据删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("deleteDataUseReference")
    public Result delete(@RequestBody @NotNull(message = "数据使用参照,快照系统取数依据id列表不能为空") @Size(min = 1, message = "数据使用参照,快照系统取数依据id至少存在一个") List<String> ids) {
        gsDataUseReferenceService.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "xinz", order = 8)
    @ApiOperation(value = "数据使用参照,快照系统取数依据逻辑删除", notes = "数据使用参照,快照系统取数依据逻辑删除接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping("deleteLogicalDataUseReference")
    public Result deleteLogicalDataUseReference(@RequestBody @NotNull(message = "数据使用参照,快照系统取数依据id列表不能为空") @Size(min = 1, message = "数据使用参照,快照系统取数依据id至少存在一个") List<String> ids) {
        gsDataUseReferenceService.deleteLogical(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperationSupport(author = "xinz")
    @ApiOperation(value = "获取数据使用模板", notes = "获取数据使用模板接口")
    @ApiVersion(1)
    @GetMapping("dataUseTemplate")
    public Result<Map> getDataUseTemplate(@RequestParam @NotNull(message = "学号参数，不能为空") String stuCode) {
        return ResultGenerator.genSuccessResult(gsDataUseReferenceService.getDataUseTemplate(stuCode));
    }

    @ApiOperationSupport(author = "xinz", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生请假数据列表获取，不分页，默认上限100", notes = "学生请假数据列表获取接口")
    @ApiVersion(1)
    @GetMapping("getLeaveDataAll")
    public Result<List<GsGxxsStuLeaveDataModelExtend>> getLeaveData(
            @SortDefault(sort = "etlPri", direction = Sort.Direction.DESC)
                    Sort sort, GsGxxsStuLeaveDataModelSearch search,HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuLeaveDataModelExtend> listGsGxxsStuLeaveDataModel = service.getLeaveData(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuLeaveDataModel);
    }

    @ApiOperationSupport(author = "xinz", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "班级基本信息列表获取", notes = "班级基本信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("classInfo")
    public Result<PageOb<GsGxxsClassInfoModelExtend>> getClassInfo(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Sort.Direction.DESC)
                    Pageable pageable, GsGxxsClassInfoModelSearch search) {
        PageOb<GsGxxsClassInfoModelExtend> pageGsGxxsClassInfoModel = service.getClassInfo(search,pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsClassInfoModel);
    }

}
