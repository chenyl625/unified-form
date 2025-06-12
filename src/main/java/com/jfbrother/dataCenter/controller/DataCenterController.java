package com.jfbrother.dataCenter.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.version.ApiVersion;
import com.jfbrother.dataCenter.model.extend.*;
import com.jfbrother.dataCenter.model.response.TeacherPersonnelResponseModel;
import com.jfbrother.dataCenter.model.search.*;
import com.jfbrother.dataCenter.service.DataCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 数据中心信息MVC控制器类
 *
 * @author chenyl@jfbrother.com 2022-07-18
 */
@ApiSort(10)
@Api(tags = {"数据中心接口-1.0.0-20220718"})
@Validated
@RestController("DataCenter")
@RequestMapping("/api/v1/dataCenter")
public class DataCenterController {
    @Autowired
    private DataCenterService service;

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教师基本信息列表获取", notes = "教师基本信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherBase")
    public Result<PageOb<GsTeacherBaseInfoModelExtend>> getTeacherBase(
            @PageableDefault(page = 0, size = 10, sort = {"sortNum","bmh"}, direction = Direction.ASC)
                    Pageable pageable, GsTeacherBaseInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsTeacherBaseInfoModelExtend> pageModel = service.getTeacherBase(search, pageable);
        return ResultGenerator.genSuccessResult(pageModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教师基本信息列表获取，不分页，默认上限100", notes = "教师基本信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherBaseAll")
    public Result<List<GsTeacherBaseInfoModelExtend>> getTeacherBase(
            @SortDefault(sort = "sortNum", direction = Direction.ASC)
                    Sort sort, GsTeacherBaseInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsTeacherBaseInfoModelExtend> listModel = service.getTeacherBase(search, sort);
        return ResultGenerator.genSuccessResult(listModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教师详细信息列表获取", notes = "教师详细信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherDetail")
    public Result<PageOb<GsTeacherDetailInfoModelExtend>> getTeacherDetail(
            @PageableDefault(page = 0, size = 10, sort = "gh", direction = Direction.ASC)
                    Pageable pageable, GsTeacherDetailInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsTeacherDetailInfoModelExtend> pageGsTeacherDetailInfoModel = service.getTeacherDetail(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsTeacherDetailInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教师详细信息列表获取，不分页，默认上限100", notes = "教师详细信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherDetailAll")
    public Result<List<GsTeacherDetailInfoModelExtend>> getTeacherDetail(
            @SortDefault(sort = "gh", direction = Direction.ASC)
                    Sort sort, GsTeacherDetailInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsTeacherDetailInfoModelExtend> listGsTeacherDetailInfoModel = service.getTeacherDetail(search, sort);
        return ResultGenerator.genSuccessResult(listGsTeacherDetailInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教师全部信息列表获取", notes = "教师全部信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherAll")
    public Result<PageOb<GsTeacherAllInfoModelExtend>> getTeacherAll(
            @PageableDefault(page = 0, size = 10, sort = "sortNum", direction = Direction.ASC)
                    Pageable pageable, GsTeacherAllInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsTeacherAllInfoModelExtend> pageGsTeacherAllInfoModel = service.getTeacherAll(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsTeacherAllInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教师全部信息列表获取，不分页，默认上限100", notes = "教师全部信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherAllAll")
    public Result<List<GsTeacherAllInfoModelExtend>> getTeacherAll(
            @SortDefault(sort = {"sortNum","bmh"}, direction = Direction.ASC)
                    Sort sort, GsTeacherAllInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsTeacherAllInfoModelExtend> listGsTeacherAllInfoModel = service.getTeacherAll(search, sort);
        return ResultGenerator.genSuccessResult(listGsTeacherAllInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 3, ignoreParameters = {"id"})
    @ApiOperation(value = "教师家庭信息列表获取", notes = "教师家庭信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherFamilyMem")
    public Result<PageOb<GsTeacherFamilyMemModelExtend>> getTeacherFamilyMem(
            @PageableDefault(page = 0, size = 10, sort = "relationCode", direction = Direction.ASC)
                    Pageable pageable, GsTeacherFamilyMemModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsTeacherFamilyMemModelExtend> pageModel = service.getTeacherFamilyMem(search, pageable);
        return ResultGenerator.genSuccessResult(pageModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 4, ignoreParameters = {"id"})
    @ApiOperation(value = "教师家庭信息列表获取，不分页，默认上限100", notes = "教师家庭信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherFamilyMemAll")
    public Result<List<GsTeacherFamilyMemModelExtend>> getTeacherFamilyMem(
            @SortDefault(sort = "relationCode", direction = Direction.ASC)
                    Sort sort, GsTeacherFamilyMemModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsTeacherFamilyMemModelExtend> listModel = service.getTeacherFamilyMem(search, sort);
        return ResultGenerator.genSuccessResult(listModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 5, ignoreParameters = {"id"})
    @ApiOperation(value = "教师年度考核信息列表获取", notes = "教师年度考核信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherYearKh")
    public Result<PageOb<GsTeacherYearKhModelExtend>> getTeacherYearKh(
            @PageableDefault(page = 0, size = 10, sort = "xn", direction = Direction.DESC)
                    Pageable pageable, GsTeacherYearKhModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsTeacherYearKhModelExtend> pageModel = service.getTeacherYearKh(search, pageable);
        return ResultGenerator.genSuccessResult(pageModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 6, ignoreParameters = {"id"})
    @ApiOperation(value = "教师年度考核信息列表获取，不分页，默认上限100", notes = "教师年度考核信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherYearKhAll")
    public Result<List<GsTeacherYearKhModelExtend>> getTeacherYearKh(
            @SortDefault(sort = "xn", direction = Direction.DESC)
                    Sort sort, GsTeacherYearKhModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsTeacherYearKhModelExtend> listModel = service.getTeacherYearKh(search, sort);
        return ResultGenerator.genSuccessResult(listModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 7, ignoreParameters = {"id"})
    @ApiOperation(value = "教师专业技术职务列表获取", notes = "教师专业技术职务列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherPosition")
    public Result<PageOb<GsTeacherPositionModelExtend>> getTeacherPosition(
            @PageableDefault(page = 0, size = 10, sort = "rankPositionCode", direction = Direction.DESC)
                    Pageable pageable, GsTeacherPositionModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsTeacherPositionModelExtend> pageModel = service.getTeacherPosition(search, pageable);
        return ResultGenerator.genSuccessResult(pageModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 8, ignoreParameters = {"id"})
    @ApiOperation(value = "教师专业技术职务列表获取，不分页，默认上限100", notes = "教师专业技术职务列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherPositionAll")
    public Result<List<GsTeacherPositionModelExtend>> getTeacherPosition(
            @SortDefault(sort = "rankPositionCode", direction = Direction.DESC)
                    Sort sort, GsTeacherPositionModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsTeacherPositionModelExtend> listModel = service.getTeacherPosition(search, sort);
        return ResultGenerator.genSuccessResult(listModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 9, ignoreParameters = {"id"})
    @ApiOperation(value = "学评教-学生评价老师汇总表列表获取", notes = "学评教-学生评价老师汇总表列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuEvaluateTeacher")
    public Result<PageOb<GsStuEvaluateTeacherModelExtend>> getStuEvaluateTeacher(
            @PageableDefault(page = 0, size = 10, sort = "xn", direction = Direction.DESC)
                    Pageable pageable, GsStuEvaluateTeacherModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsStuEvaluateTeacherModelExtend> pageGsStuEvaluateTeacherModel = service.getStuEvaluateTeacher(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsStuEvaluateTeacherModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 10, ignoreParameters = {"id"})
    @ApiOperation(value = "学评教-学生评价老师汇总表列表获取，不分页，默认上限100", notes = "学评教-学生评价老师汇总表列表获取接口")
    @ApiVersion(1)
    @GetMapping("stuEvaluateTeacherAll")
    public Result<List<GsStuEvaluateTeacherModelExtend>> getStuEvaluateTeacher(
            @SortDefault(sort = "xn", direction = Direction.DESC)
                    Sort sort, GsStuEvaluateTeacherModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsStuEvaluateTeacherModelExtend> listGsStuEvaluateTeacherModel = service.getStuEvaluateTeacher(search, sort);
        return ResultGenerator.genSuccessResult(listGsStuEvaluateTeacherModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教师排课基本信息列表获取", notes = "教师排课基本信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("kbBasicInfo")
    public Result<PageOb<GsKbBasicInfoModelExtend>> getKbBasicInfo(
            @PageableDefault(page = 0, size = 10, sort = "kkxn", direction = Direction.DESC)
                    Pageable pageable, GsKbBasicInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsKbBasicInfoModelExtend> pageGsKbBasicInfoModel = service.getKbBasicInfo(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsKbBasicInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教师排课基本信息列表获取，不分页，默认上限100", notes = "教师排课基本信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("kbBasicInfoAll")
    public Result<List<GsKbBasicInfoModelExtend>> getKbBasicInfo(
            @SortDefault(sort = "kkxn", direction = Direction.DESC)
                    Sort sort, GsKbBasicInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsKbBasicInfoModelExtend> listGsKbBasicInfoModel = service.getKbBasicInfo(search, sort);
        return ResultGenerator.genSuccessResult(listGsKbBasicInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教师维度的教学质量评价信息列表获取", notes = "教师维度的教学质量评价信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherTeachQualityEval")
    public Result<PageOb<GsTeacherTeachQualityEvalInfoModelExtend>> getTeacherTeachQualityEval(
            @PageableDefault(page = 0, size = 10, sort = "orderNo", direction = Direction.DESC)
                    Pageable pageable, GsTeacherTeachQualityEvalInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsTeacherTeachQualityEvalInfoModelExtend> pageGsTeacherTeachQualityEvalInfoModel = service.getTeacherTeachQualityEval(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsTeacherTeachQualityEvalInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教师维度的教学质量评价信息列表获取，不分页，默认上限100", notes = "教师维度的教学质量评价信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherTeachQualityEvalAll")
    public Result<List<GsTeacherTeachQualityEvalInfoModelExtend>> getTeacherTeachQualityEval(
            @SortDefault(sort = "orderNo", direction = Direction.DESC)
                    Sort sort, GsTeacherTeachQualityEvalInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsTeacherTeachQualityEvalInfoModelExtend> listGsTeacherTeachQualityEvalInfoModel = service.getTeacherTeachQualityEval(search, sort);
        return ResultGenerator.genSuccessResult(listGsTeacherTeachQualityEvalInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教师政治面貌列表获取", notes = "教师政治面貌列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherZzmm")
    public Result<PageOb<GsTeacherZzmmModelExtend>> getTeacherZzmm(
            @PageableDefault(page = 0, size = 10, sort = "idSort", direction = Direction.ASC)
                    Pageable pageable, GsTeacherZzmmModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsTeacherZzmmModelExtend> pageGsTeacherZzmmModel = service.getTeacherZzmm(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsTeacherZzmmModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教师政治面貌列表获取，不分页，默认上限100", notes = "教师政治面貌列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherZzmmAll")
    public Result<List<GsTeacherZzmmModelExtend>> getTeacherZzmm(
            @SortDefault(sort = "idSort", direction = Direction.ASC)
                    Sort sort, GsTeacherZzmmModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsTeacherZzmmModelExtend> listGsTeacherZzmmModel = service.getTeacherZzmm(search, sort);
        return ResultGenerator.genSuccessResult(listGsTeacherZzmmModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教师个人通讯方式列表获取", notes = "教师个人通讯方式列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherPersonComm")
    public Result<PageOb<GsTeacherPersonCommModelExtend>> getTeacherPersonComm(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.DESC)
                    Pageable pageable, GsTeacherPersonCommModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsTeacherPersonCommModelExtend> pageGsTeacherPersonCommModel = service.getTeacherPersonComm(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsTeacherPersonCommModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教师个人通讯方式列表获取，不分页，默认上限100", notes = "教师个人通讯方式列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherPersonCommAll")
    public Result<List<GsTeacherPersonCommModelExtend>> getTeacherPersonComm(
            @SortDefault(sort = "id", direction = Direction.DESC)
                    Sort sort, GsTeacherPersonCommModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsTeacherPersonCommModelExtend> listGsTeacherPersonCommModel = service.getTeacherPersonComm(search, sort);
        return ResultGenerator.genSuccessResult(listGsTeacherPersonCommModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "科研项目信息列表获取", notes = "科研项目信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("projectInfo")
    public Result<PageOb<GsProjectInfoModelExtend>> getProjectInfo(
            @PageableDefault(page = 0, size = 10, sort = "code", direction = Direction.DESC)
                    Pageable pageable, GsProjectInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsProjectInfoModelExtend> pageGsProjectInfoModel = service.getProjectInfo(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsProjectInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "科研项目信息列表获取，不分页，默认上限100", notes = "科研项目信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("projectInfoAll")
    public Result<List<GsProjectInfoModelExtend>> getProjectInfo(
            @SortDefault(sort = "code", direction = Direction.DESC)
                    Sort sort, GsProjectInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsProjectInfoModelExtend> listGsProjectInfoModel = service.getProjectInfo(search, sort);
        return ResultGenerator.genSuccessResult(listGsProjectInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "科研专利成果信息列表获取", notes = "科研专利成果信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("kyPatent")
    public Result<PageOb<GsKyPatentInfoModelExtend>> getKyPatent(
            @PageableDefault(page = 0, size = 10, sort = "applyDate", direction = Direction.DESC)
                    Pageable pageable, GsKyPatentInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsKyPatentInfoModelExtend> pageGsKyPatentInfoModel = service.getKyPatent(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsKyPatentInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "科研专利成果信息列表获取，不分页，默认上限100", notes = "科研专利成果信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("kyPatentAll")
    public Result<List<GsKyPatentInfoModelExtend>> getKyPatent(
            @SortDefault(sort = "applyDate", direction = Direction.DESC)
                    Sort sort, GsKyPatentInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsKyPatentInfoModelExtend> listGsKyPatentInfoModel = service.getKyPatent(search, sort);
        return ResultGenerator.genSuccessResult(listGsKyPatentInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "科研论文信息列表获取", notes = "科研论文信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("kyPaper")
    public Result<PageOb<GsKyPaperInfoModelExtend>> getKyPaper(
            @PageableDefault(page = 0, size = 10, sort = "publishDate", direction = Direction.DESC)
                    Pageable pageable, GsKyPaperInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsKyPaperInfoModelExtend> pageGsKyPaperInfoModel = service.getKyPaper(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsKyPaperInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "科研论文信息列表获取，不分页，默认上限100", notes = "科研论文信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("kyPaperAll")
    public Result<List<GsKyPaperInfoModelExtend>> getKyPaper(
            @SortDefault(sort = "publishDate", direction = Direction.DESC)
                    Sort sort, GsKyPaperInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsKyPaperInfoModelExtend> listGsKyPaperInfoModel = service.getKyPaper(search, sort);
        return ResultGenerator.genSuccessResult(listGsKyPaperInfoModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教师人事信息列表获取", notes = "教师人事信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherPersonnel")
    public Result<PageOb<TeacherPersonnelResponseModel>> getTeacherPersonnel(
            @PageableDefault(page = 0, size = 10, sort = "gh", direction = Direction.ASC)
                    Pageable pageable, GsTeacherAllInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<TeacherPersonnelResponseModel> pageModel = service.getTeacherPersonnel(search, pageable);
        return ResultGenerator.genSuccessResult(pageModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教师人事信息列表获取，不分页，默认上限100", notes = "教师人事信息列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherPersonnelAll")
    public Result<List<TeacherPersonnelResponseModel>> getTeacherPersonnel(
            @SortDefault(sort = "gh", direction = Direction.ASC)
                    Sort sort, GsTeacherAllInfoModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<TeacherPersonnelResponseModel> listModel = service.getTeacherPersonnel(search, sort);
        return ResultGenerator.genSuccessResult(listModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "教师学历学位列表获取", notes = "教师学历学位列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherEduDegree")
    public Result<PageOb<GsTeacherEduDegreeModelExtend>> getTeacherEduDegree(
            @PageableDefault(page = 0, size = 10, sort = "endTime", direction = Direction.ASC)
                    Pageable pageable, GsTeacherEduDegreeModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsTeacherEduDegreeModelExtend> pageGsTeacherEduDegreeModel = service.getTeacherEduDegree(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsTeacherEduDegreeModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "教师学历学位列表获取，不分页，默认上限100", notes = "教师学历学位列表获取接口")
    @ApiVersion(1)
    @GetMapping("teacherEduDegreeAll")
    public Result<List<GsTeacherEduDegreeModelExtend>> getTeacherEduDegree(
            @SortDefault(sort = "endTime", direction = Direction.ASC)
                    Sort sort, GsTeacherEduDegreeModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsTeacherEduDegreeModelExtend> listGsTeacherEduDegreeModel = service.getTeacherEduDegree(search, sort);
        return ResultGenerator.genSuccessResult(listGsTeacherEduDegreeModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "工资发放-个人收入明细列表获取", notes = "工资发放-个人收入明细列表获取接口")
    @ApiVersion(1)
    @GetMapping("gzPersonDetail")
    public Result<PageOb<GsCwGzPersonDetailModelExtend>> getGzPersonDetail(
            @PageableDefault(page = 0, size = 10, sort = "nybs", direction = Direction.DESC)
                    Pageable pageable, GsCwGzPersonDetailModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsCwGzPersonDetailModelExtend> pageGsCwGzPersonDetailModel = service.getGzPersonDetail(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsCwGzPersonDetailModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "工资发放-个人收入明细列表获取，不分页，默认上限100", notes = "工资发放-个人收入明细列表获取接口")
    @ApiVersion(1)
    @GetMapping("gzPersonDetailAll")
    public Result<List<GsCwGzPersonDetailModelExtend>> getGzPersonDetail(
            @SortDefault(sort = "nybs", direction = Direction.DESC)
                    Sort sort, GsCwGzPersonDetailModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsCwGzPersonDetailModelExtend> listGsCwGzPersonDetailModel = service.getGzPersonDetail(search, sort);
        return ResultGenerator.genSuccessResult(listGsCwGzPersonDetailModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "奖金发放-个人收入明细列表获取", notes = "奖金发放-个人收入明细列表获取接口")
    @ApiVersion(1)
    @GetMapping("jjPersonDetail")
    public Result<PageOb<GsCwJjPersonDetailModelExtend>> getJjPersonDetail(
            @PageableDefault(page = 0, size = 10, sort = "nybs", direction = Direction.DESC)
                    Pageable pageable, GsCwJjPersonDetailModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsCwJjPersonDetailModelExtend> pageGsCwJjPersonDetailModel = service.getJjPersonDetail(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsCwJjPersonDetailModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "奖金发放-个人收入明细列表获取，不分页，默认上限100", notes = "奖金发放-个人收入明细列表获取接口")
    @ApiVersion(1)
    @GetMapping("jjPersonDetailAll")
    public Result<List<GsCwJjPersonDetailModelExtend>> getJjPersonDetail(
            @SortDefault(sort = "nybs", direction = Direction.DESC)
                    Sort sort, GsCwJjPersonDetailModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsCwJjPersonDetailModelExtend> listGsCwJjPersonDetailModel = service.getJjPersonDetail(search, sort);
        return ResultGenerator.genSuccessResult(listGsCwJjPersonDetailModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 1, ignoreParameters = {"id"})
    @ApiOperation(value = "项目发放-教职工收入列表获取", notes = "项目发放-教职工收入列表获取接口")
    @ApiVersion(1)
    @GetMapping("projectTeacherDetail")
    public Result<PageOb<GsCwProjectTeacherDetailModelExtend>> getProjectTeacherDetail(
            @PageableDefault(page = 0, size = 10, sort = "nybs", direction = Direction.DESC)
                    Pageable pageable, GsCwProjectTeacherDetailModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        PageOb<GsCwProjectTeacherDetailModelExtend> pageGsCwProjectTeacherDetailModel = service.getProjectTeacherDetail(search, pageable);
        return ResultGenerator.genSuccessResult(pageGsCwProjectTeacherDetailModel);
    }

    @ApiOperationSupport(author = "chenyl@jfbrother.com", order = 2, ignoreParameters = {"id"})
    @ApiOperation(value = "项目发放-教职工收入列表获取，不分页，默认上限100", notes = "项目发放-教职工收入列表获取接口")
    @ApiVersion(1)
    @GetMapping("projectTeacherDetailAll")
    public Result<List<GsCwProjectTeacherDetailModelExtend>> getProjectTeacherDetail(
            @SortDefault(sort = "nybs", direction = Direction.DESC)
                    Sort sort, GsCwProjectTeacherDetailModelSearch search, HttpServletRequest request) {
        search.setApiUrl(request.getRequestURI());
        List<GsCwProjectTeacherDetailModelExtend> listGsCwProjectTeacherDetailModel = service.getProjectTeacherDetail(search, sort);
        return ResultGenerator.genSuccessResult(listGsCwProjectTeacherDetailModel);
    }
}
