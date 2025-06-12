package com.jfbrother.student.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.student.model.GsImportHikFaceInfoModel;
import com.jfbrother.student.model.extend.*;
import com.jfbrother.student.model.search.*;
import com.jfbrother.student.service.StudentDataCenterService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 学生数据中心信息MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-07-18
 */
@ApiSort(10)
@Api(tags = {"学生数据中心接口-1.0.0-20220718"})
@Validated
@RestController("StudentDataCenter")
@RequestMapping("/api/v1/studentDataCenter")
public class StudentDataCenterController {
    @Autowired
    private StudentDataCenterService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "所有学生基本数据子类列表获取", notes = "所有学生基本数据子类列表获取接口")
    @ApiVersion(1)
    @GetMapping("allStuBaseInfo")
    public Result<PageOb<GsGxxsAllStuBaseInfoModelExtend>> getAllStuBaseInfo(
            @PageableDefault(page = 0, size = 10, sort = "xh", direction = Direction.DESC)
                    Pageable pageable, GsGxxsAllStuBaseInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsAllStuBaseInfoModelExtend> pageGsGxxsAllStuBaseInfoModel = service.getAllStuBaseInfo(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsAllStuBaseInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "所有学生基本数据子类列表获取，不分页，默认上限100", notes = "所有学生基本数据子类列表获取接口")
    @ApiVersion(1)
    @GetMapping("allStuBaseInfoAll")
    public Result<List<GsGxxsAllStuBaseInfoModelExtend>> getAllStuBaseInfo(
            @SortDefault(sort = "xh", direction = Direction.DESC)
                    Sort sort, GsGxxsAllStuBaseInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsAllStuBaseInfoModelExtend> listGsGxxsAllStuBaseInfoModel = service.getAllStuBaseInfo(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsAllStuBaseInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教务系统里的在校生列表获取", notes = "教务系统里的在校生列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuBaseEntire")
    public Result<PageOb<GsGxxsStuBaseEntireModelExtend>> getStuBaseEntire(
            @PageableDefault(page = 0, size = 10, sort = "xh", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuBaseEntireModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuBaseEntireModelExtend> pageGsGxxsStuBaseEntireModel = service.getStuBaseEntire(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuBaseEntireModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教务系统里的在校生列表获取，不分页，默认上限100", notes = "教务系统里的在校生列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuBaseEntireAll")
    public Result<List<GsGxxsStuBaseEntireModelExtend>> getStuBaseEntire(
            @SortDefault(sort = "xh", direction = Direction.DESC)
                    Sort sort, GsGxxsStuBaseEntireModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuBaseEntireModelExtend> listGsGxxsStuBaseEntireModel = service.getStuBaseEntire(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuBaseEntireModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生联系方式列表获取", notes = "学生联系方式列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuContact")
    public Result<PageOb<GsGxxsStuContactModelExtend>> getStuContact(
            @PageableDefault(page = 0, size = 10, sort = "studentCode", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuContactModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuContactModelExtend> pageGsGxxsStuContactModel = service.getStuContact(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuContactModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生联系方式列表获取，不分页，默认上限100", notes = "学生联系方式列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuContactAll")
    public Result<List<GsGxxsStuContactModelExtend>> getStuContact(
            @SortDefault(sort = "studentCode", direction = Direction.DESC)
                    Sort sort, GsGxxsStuContactModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuContactModelExtend> listGsGxxsStuContactModel = service.getStuContact(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuContactModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生家庭联系方式列表获取", notes = "学生家庭联系方式列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuFamilyContact")
    public Result<PageOb<GsGxxsStuFamilyContactModelExtend>> getStuFamilyContact(
            @PageableDefault(page = 0, size = 10, sort = "studentCode", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuFamilyContactModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuFamilyContactModelExtend> pageGsGxxsStuFamilyContactModel = service.getStuFamilyContact(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuFamilyContactModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生家庭联系方式列表获取，不分页，默认上限100", notes = "学生家庭联系方式列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuFamilyContactAll")
    public Result<List<GsGxxsStuFamilyContactModelExtend>> getStuFamilyContact(
            @SortDefault(sort = "studentCode", direction = Direction.DESC)
                    Sort sort, GsGxxsStuFamilyContactModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuFamilyContactModelExtend> listGsGxxsStuFamilyContactModel = service.getStuFamilyContact(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuFamilyContactModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "三创学分申请详情列表获取", notes = "三创学分申请详情列表获取接口")
    @ApiVersion(1)
    @GetMapping("scxfApplyDetail")
    public Result<PageOb<GsGxxsScxfApplyDetailModelExtend>> getScxfApplyDetail(
            @PageableDefault(page = 0, size = 10, sort = "applyTime", direction = Direction.DESC)
                    Pageable pageable, GsGxxsScxfApplyDetailModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsScxfApplyDetailModelExtend> pageGsGxxsScxfApplyDetailModel = service.getScxfApplyDetail(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsScxfApplyDetailModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "三创学分申请详情列表获取，不分页，默认上限100", notes = "三创学分申请详情列表获取接口")
    @ApiVersion(1)
    @GetMapping("scxfApplyDetailAll")
    public Result<List<GsGxxsScxfApplyDetailModelExtend>> getScxfApplyDetail(
            @SortDefault(sort = "applyTime", direction = Direction.DESC)
                    Sort sort, GsGxxsScxfApplyDetailModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsScxfApplyDetailModelExtend> listGsGxxsScxfApplyDetailModel = service.getScxfApplyDetail(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsScxfApplyDetailModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生课程成绩表列表获取", notes = "学生课程成绩表列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuCourseScore")
    public Result<PageOb<GsGxxsStuCourseScoreModelExtend>> getStuCourseScore(
            @PageableDefault(page = 0, size = 10, sort = "xn", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuCourseScoreModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuCourseScoreModelExtend> pageGsGxxsStuCourseScoreModel = service.getStuCourseScore(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuCourseScoreModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生课程成绩表列表获取，不分页，默认上限100", notes = "学生课程成绩表列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuCourseScoreAll")
    public Result<List<GsGxxsStuCourseScoreModelExtend>> getStuCourseScore(
            @SortDefault(sort = "xn", direction = Direction.DESC)
                    Sort sort, GsGxxsStuCourseScoreModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuCourseScoreModelExtend> listGsGxxsStuCourseScoreModel = service.getStuCourseScore(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuCourseScoreModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生住宿信息列表获取", notes = "学生住宿信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuDorm")
    public Result<PageOb<GsGxxsStuDormModelExtend>> getStuDorm(
            @PageableDefault(page = 0, size = 10, sort = "studentCode", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuDormModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuDormModelExtend> pageGsGxxsStuDormModel = service.getStuDorm(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuDormModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生住宿信息列表获取，不分页，默认上限100", notes = "学生住宿信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuDormAll")
    public Result<List<GsGxxsStuDormModelExtend>> getStuDorm(
            @SortDefault(sort = "studentCode", direction = Direction.DESC)
                    Sort sort, GsGxxsStuDormModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuDormModelExtend> listGsGxxsStuDormModel = service.getStuDorm(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuDormModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生等级考试情况列表获取", notes = "学生等级考试情况列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuLevelEaxmInfo")
    public Result<PageOb<GsGxxsStuLevelEaxmInfoModelExtend>> getStuLevelEaxmInfo(
            @PageableDefault(page = 0, size = 10, sort = "signTime", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuLevelEaxmInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuLevelEaxmInfoModelExtend> pageGsGxxsStuLevelEaxmInfoModel = service.getStuLevelEaxmInfo(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuLevelEaxmInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生等级考试情况列表获取，不分页，默认上限100", notes = "学生等级考试情况列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuLevelEaxmInfoAll")
    public Result<List<GsGxxsStuLevelEaxmInfoModelExtend>> getStuLevelEaxmInfo(
            @SortDefault(sort = "signTime", direction = Direction.DESC)
                    Sort sort, GsGxxsStuLevelEaxmInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuLevelEaxmInfoModelExtend> listGsGxxsStuLevelEaxmInfoModel = service.getStuLevelEaxmInfo(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuLevelEaxmInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生学籍异动最新记录列表获取", notes = "学生学籍异动最新记录列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuXjChange")
    public Result<PageOb<GsGxxsStuXjChangeModelExtend>> getStuXjChange(
            @PageableDefault(page = 0, size = 10, sort = "changeTime", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuXjChangeModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuXjChangeModelExtend> pageGsGxxsStuXjChangeModel = service.getStuXjChange(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuXjChangeModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生学籍异动最新记录列表获取，不分页，默认上限100", notes = "学生学籍异动最新记录列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuXjChangeAll")
    public Result<List<GsGxxsStuXjChangeModelExtend>> getStuXjChange(
            @SortDefault(sort = "changeTime", direction = Direction.DESC)
                    Sort sort, GsGxxsStuXjChangeModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuXjChangeModelExtend> listGsGxxsStuXjChangeModel = service.getStuXjChange(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuXjChangeModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "个人消费记录列表获取", notes = "个人消费记录列表获取接口")
    @ApiVersion(1)
    @GetMapping("personConsume")
    public Result<PageOb<GsYktxtPersonConsumeModelExtend>> getPersonConsume(
            @PageableDefault(page = 0, size = 10, sort = "dealtime", direction = Direction.DESC)
                    Pageable pageable, GsYktxtPersonConsumeModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsYktxtPersonConsumeModelExtend> pageGsYktxtPersonConsumeModel = service.getPersonConsume(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsYktxtPersonConsumeModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "个人消费记录列表获取，不分页，默认上限100", notes = "个人消费记录列表获取接口")
    @ApiVersion(1)
    @GetMapping("personConsumeAll")
    public Result<List<GsYktxtPersonConsumeModelExtend>> getPersonConsume(
            @SortDefault(sort = "dealtime", direction = Direction.DESC)
                    Sort sort, GsYktxtPersonConsumeModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsYktxtPersonConsumeModelExtend> listGsYktxtPersonConsumeModel = service.getPersonConsume(search, sort);
        return ResultGenerator.genSuccessResult(listGsYktxtPersonConsumeModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "办事申请表列表获取", notes = "办事申请表列表获取接口")
    @ApiVersion(1)
    @GetMapping("applyInfo")
    public Result<PageOb<GsGxbgApplyInfoModelExtend>> getApplyInfo(
            @PageableDefault(page = 0, size = 10, sort = "submitDate", direction = Direction.DESC)
                    Pageable pageable, GsGxbgApplyInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxbgApplyInfoModelExtend> pageGsGxbgApplyInfoModel = service.getApplyInfo(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxbgApplyInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "办事申请表列表获取，不分页，默认上限100", notes = "办事申请表列表获取接口")
    @ApiVersion(1)
    @GetMapping("applyInfoAll")
    public Result<List<GsGxbgApplyInfoModelExtend>> getApplyInfo(
            @SortDefault(sort = "submitDate", direction = Direction.DESC)
                    Sort sort, GsGxbgApplyInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxbgApplyInfoModelExtend> listGsGxbgApplyInfoModel = service.getApplyInfo(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxbgApplyInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生社会实践列表获取", notes = "学生社会实践列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuSocialPrac")
    public Result<PageOb<GsGxxsStuSocialPracModelExtend>> getStuSocialPrac(
            @PageableDefault(page = 0, size = 10, sort = "acquireDate", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuSocialPracModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuSocialPracModelExtend> pageGsGxxsStuSocialPracModel = service.getStuSocialPrac(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuSocialPracModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生社会实践列表获取，不分页，默认上限100", notes = "学生社会实践列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuSocialPracAll")
    public Result<List<GsGxxsStuSocialPracModelExtend>> getStuSocialPrac(
            @SortDefault(sort = "acquireDate", direction = Direction.DESC)
                    Sort sort, GsGxxsStuSocialPracModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuSocialPracModelExtend> listGsGxxsStuSocialPracModel = service.getStuSocialPrac(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuSocialPracModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "在借记录列表获取", notes = "在借记录列表获取接口")
    @ApiVersion(1)
    @GetMapping("libraryBorrowing")
    public Result<PageOb<GsLibraryBorrowingModelExtend>> getLibraryBorrowing(
            @PageableDefault(page = 0, size = 10, sort = "lendDate", direction = Direction.DESC)
                    Pageable pageable, GsLibraryBorrowingModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsLibraryBorrowingModelExtend> pageGsLibraryBorrowingModel = service.getLibraryBorrowing(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsLibraryBorrowingModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "在借记录列表获取，不分页，默认上限100", notes = "在借记录列表获取接口")
    @ApiVersion(1)
    @GetMapping("libraryBorrowingAll")
    public Result<List<GsLibraryBorrowingModelExtend>> getLibraryBorrowing(
            @SortDefault(sort = "lendDate", direction = Direction.DESC)
                    Sort sort, GsLibraryBorrowingModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsLibraryBorrowingModelExtend> listGsLibraryBorrowingModel = service.getLibraryBorrowing(search, sort);
        return ResultGenerator.genSuccessResult(listGsLibraryBorrowingModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3)
    @ApiOperation(value = "海康人脸照片数据获取", notes = "海康人脸照片数据获取接口")
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{userId}/hikFace")
    public Result<GsImportHikFaceInfoModel> getHikFace(@PathVariable String userId) {
        return ResultGenerator.genSuccessResult(service.getHikFace(userId));
    }

    @ApiOperationSupport(author = "hujy@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生注册数据列表获取", notes = "学生注册数据列表获取接口")
    @ApiVersion(1)
    @GetMapping("getRegistrationData")
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<GsGxxsStuRegistrationDataModelExtend>> getRegistrationData(
            @PageableDefault(page = 0, size = 10, sort = "etlPri", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuRegistrationDataModelSearch search,HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuRegistrationDataModelExtend> pageGsGxxsStuRegistrationDataModel = service.getRegistrationData(search,pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuRegistrationDataModel);
    }

    @ApiOperationSupport(author = "hujy@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生注册数据列表获取，不分页，默认上限100", notes = "学生注册数据列表获取接口")
    @ApiVersion(1)
    @GetMapping("getRegistrationDataAll")
    //@RolesAllowed({"ADMIN"})
    public Result<List<GsGxxsStuRegistrationDataModelExtend>> getRegistrationData(
            @SortDefault(sort = "etlPri", direction = Direction.DESC)
                    Sort sort, GsGxxsStuRegistrationDataModelSearch search,HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuRegistrationDataModelExtend> listGsGxxsStuRegistrationDataModel = service.getRegistrationData(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuRegistrationDataModel);
    }

    @ApiOperationSupport(author = "hujy@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生体质测试成绩数据列表获取", notes = "学生体质测试成绩数据列表获取接口")
    @ApiVersion(1)
    @GetMapping("physiqueResultData")
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<GsGxxsStuPhysiqueResultDataModelExtend>> getPhysiqueResultData(
            @PageableDefault(page = 0, size = 10, sort = "etlPri", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuPhysiqueResultDataModelSearch search,HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuPhysiqueResultDataModelExtend> pageGsGxxsStuPhysiqueResultDataModel = service.getPhysiqueResultData(search,pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuPhysiqueResultDataModel);
    }

    @ApiOperationSupport(author = "hujy@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生体质测试成绩数据列表获取，不分页，默认上限100", notes = "学生体质测试成绩数据列表获取接口")
    @ApiVersion(1)
    @GetMapping("physiqueResultDataAll")
    //@RolesAllowed({"ADMIN"})
    public Result<List<GsGxxsStuPhysiqueResultDataModelExtend>> getPhysiqueResultData(
            @SortDefault(sort = "etlPri", direction = Direction.DESC)
                    Sort sort, GsGxxsStuPhysiqueResultDataModelSearch search,HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuPhysiqueResultDataModelExtend> listGsGxxsStuPhysiqueResultDataModel = service.getPhysiqueResultData(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuPhysiqueResultDataModel);
    }

    @ApiOperationSupport(author = "hujy@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生请假数据列表获取", notes = "学生请假数据列表获取接口")
    @ApiVersion(1)
    @GetMapping("getLeaveData")
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<GsGxxsStuLeaveDataModelExtend>> getLeaveData(
            @PageableDefault(page = 0, size = 10, sort = "etlPri", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuLeaveDataModelSearch search,HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuLeaveDataModelExtend> pageGsGxxsStuLeaveDataModel = service.getLeaveData(search,pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuLeaveDataModel);
    }

    @ApiOperationSupport(author = "hujy@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生请假数据列表获取，不分页，默认上限100", notes = "学生请假数据列表获取接口")
    @ApiVersion(1)
    @GetMapping("getLeaveDataAll")
    //@RolesAllowed({"ADMIN"})
    public Result<List<GsGxxsStuLeaveDataModelExtend>> getLeaveData(
            @SortDefault(sort = "etlPri", direction = Direction.DESC)
                    Sort sort, GsGxxsStuLeaveDataModelSearch search,HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuLeaveDataModelExtend> listGsGxxsStuLeaveDataModel = service.getLeaveData(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuLeaveDataModel);
    }

    @ApiOperationSupport(author = "hujy@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "学生寝室调动日志数据列表获取", notes = "学生寝室调动日志数据列表获取接口")
    @ApiVersion(1)
    @GetMapping("getDormitoryTransferLogData")
    //@RolesAllowed({"ADMIN"})
    public Result<PageOb<GsGxxsStuDormitoryTransferLogDataModelExtend>> getDormitoryTransferLogData(
            @PageableDefault(page = 0, size = 10, sort = "etlPri", direction = Direction.DESC)
                    Pageable pageable, GsGxxsStuDormitoryTransferLogDataModelSearch search,HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsGxxsStuDormitoryTransferLogDataModelExtend> pageGsGxxsStuDormitoryTransferLogDataModel = service.getDormitoryTransferLogData(search,pageable);
        return ResultGenerator.genSuccessResult(pageGsGxxsStuDormitoryTransferLogDataModel);
    }

    @ApiOperationSupport(author = "hujy@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "学生寝室调动日志数据列表获取，不分页，默认上限100", notes = "学生寝室调动日志数据列表获取接口")
    @ApiVersion(1)
    @GetMapping("getDormitoryTransferLogDataAll")
    //@RolesAllowed({"ADMIN"})
    public Result<List<GsGxxsStuDormitoryTransferLogDataModelExtend>> getDormitoryTransferLogData(
            @SortDefault(sort = "etlPri", direction = Direction.DESC)
                    Sort sort, GsGxxsStuDormitoryTransferLogDataModelSearch search,HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsGxxsStuDormitoryTransferLogDataModelExtend> listGsGxxsStuDormitoryTransferLogDataModel = service.getDormitoryTransferLogData(search, sort);
        return ResultGenerator.genSuccessResult(listGsGxxsStuDormitoryTransferLogDataModel);
    }
}
