package com.jfbrother.dataCenter.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.dataCenter.model.extend.*;
import com.jfbrother.dataCenter.model.response.TeacherPersonnelResponseModel;
import com.jfbrother.dataCenter.model.search.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DataCenterService extends BaseService {

    /**
     * 教师信息分页获取
     *
     * @param search
     * @param pageable
     * @return
     */
    PageOb<GsTeacherBaseInfoModelExtend> getTeacherBase(GsTeacherBaseInfoModelSearch search, Pageable pageable);

    /**
     * 教师信息全部获取
     *
     * @param search
     * @param sort
     * @return
     */
    List<GsTeacherBaseInfoModelExtend> getTeacherBase(GsTeacherBaseInfoModelSearch search, Sort sort);

    PageOb<GsTeacherDetailInfoModelExtend> getTeacherDetail(GsTeacherDetailInfoModelSearch search, Pageable pageable);

    List<GsTeacherDetailInfoModelExtend> getTeacherDetail(GsTeacherDetailInfoModelSearch search, Sort sort);

    PageOb<GsTeacherAllInfoModelExtend> getTeacherAll(GsTeacherAllInfoModelSearch search, Pageable pageable);

    List<GsTeacherAllInfoModelExtend> getTeacherAll(GsTeacherAllInfoModelSearch search, Sort sort);

    PageOb<GsTeacherFamilyMemModelExtend> getTeacherFamilyMem(GsTeacherFamilyMemModelSearch search, Pageable pageable);

    List<GsTeacherFamilyMemModelExtend> getTeacherFamilyMem(GsTeacherFamilyMemModelSearch search, Sort sort);

    PageOb<GsTeacherYearKhModelExtend> getTeacherYearKh(GsTeacherYearKhModelSearch search, Pageable pageable);

    List<GsTeacherYearKhModelExtend> getTeacherYearKh(GsTeacherYearKhModelSearch search, Sort sort);

    PageOb<GsTeacherPositionModelExtend> getTeacherPosition(GsTeacherPositionModelSearch search, Pageable pageable);

    List<GsTeacherPositionModelExtend> getTeacherPosition(GsTeacherPositionModelSearch search, Sort sort);

    PageOb<GsStuEvaluateTeacherModelExtend> getStuEvaluateTeacher(GsStuEvaluateTeacherModelSearch search, Pageable pageable);

    List<GsStuEvaluateTeacherModelExtend> getStuEvaluateTeacher(GsStuEvaluateTeacherModelSearch search, Sort sort);

    PageOb<GsKbBasicInfoModelExtend> getKbBasicInfo(GsKbBasicInfoModelSearch search, Pageable pageable);

    List<GsKbBasicInfoModelExtend> getKbBasicInfo(GsKbBasicInfoModelSearch search, Sort sort);

    PageOb<GsTeacherTeachQualityEvalInfoModelExtend> getTeacherTeachQualityEval(GsTeacherTeachQualityEvalInfoModelSearch search, Pageable pageable);

    List<GsTeacherTeachQualityEvalInfoModelExtend> getTeacherTeachQualityEval(GsTeacherTeachQualityEvalInfoModelSearch search, Sort sort);

    PageOb<GsTeacherZzmmModelExtend> getTeacherZzmm(GsTeacherZzmmModelSearch search, Pageable pageable);

    List<GsTeacherZzmmModelExtend> getTeacherZzmm(GsTeacherZzmmModelSearch search, Sort sort);

    PageOb<GsTeacherPersonCommModelExtend> getTeacherPersonComm(GsTeacherPersonCommModelSearch search, Pageable pageable);

    List<GsTeacherPersonCommModelExtend> getTeacherPersonComm(GsTeacherPersonCommModelSearch search, Sort sort);

    PageOb<GsProjectInfoModelExtend> getProjectInfo(GsProjectInfoModelSearch search, Pageable pageable);

    List<GsProjectInfoModelExtend> getProjectInfo(GsProjectInfoModelSearch search, Sort sort);

    PageOb<GsKyPatentInfoModelExtend> getKyPatent(GsKyPatentInfoModelSearch search, Pageable pageable);

    List<GsKyPatentInfoModelExtend> getKyPatent(GsKyPatentInfoModelSearch search, Sort sort);

    PageOb<GsKyPaperInfoModelExtend> getKyPaper(GsKyPaperInfoModelSearch search, Pageable pageable);

    List<GsKyPaperInfoModelExtend> getKyPaper(GsKyPaperInfoModelSearch search, Sort sort);

    PageOb<TeacherPersonnelResponseModel> getTeacherPersonnel(GsTeacherAllInfoModelSearch search, Pageable pageable);

    List<TeacherPersonnelResponseModel> getTeacherPersonnel(GsTeacherAllInfoModelSearch search, Sort sort);

    PageOb<GsTeacherEduDegreeModelExtend> getTeacherEduDegree(GsTeacherEduDegreeModelSearch search, Pageable pageable);

    List<GsTeacherEduDegreeModelExtend> getTeacherEduDegree(GsTeacherEduDegreeModelSearch search, Sort sort);

    PageOb<GsCwGzPersonDetailModelExtend> getGzPersonDetail(GsCwGzPersonDetailModelSearch search, Pageable pageable);

    List<GsCwGzPersonDetailModelExtend> getGzPersonDetail(GsCwGzPersonDetailModelSearch search, Sort sort);

    PageOb<GsCwJjPersonDetailModelExtend> getJjPersonDetail(GsCwJjPersonDetailModelSearch search, Pageable pageable);

    List<GsCwJjPersonDetailModelExtend> getJjPersonDetail(GsCwJjPersonDetailModelSearch search, Sort sort);

    PageOb<GsCwProjectTeacherDetailModelExtend> getProjectTeacherDetail(GsCwProjectTeacherDetailModelSearch search, Pageable pageable);

    List<GsCwProjectTeacherDetailModelExtend> getProjectTeacherDetail(GsCwProjectTeacherDetailModelSearch search, Sort sort);
}
