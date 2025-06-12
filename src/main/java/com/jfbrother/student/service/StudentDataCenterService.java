package com.jfbrother.student.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.student.model.GsImportHikFaceInfoModel;
import com.jfbrother.student.model.extend.*;
import com.jfbrother.student.model.search.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StudentDataCenterService extends BaseService {

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<GsGxxsAllStuBaseInfoModelExtend> getAllStuBaseInfo(GsGxxsAllStuBaseInfoModelSearch search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<GsGxxsAllStuBaseInfoModelExtend> getAllStuBaseInfo(GsGxxsAllStuBaseInfoModelSearch search, Sort sort);

    PageOb<GsGxxsStuBaseEntireModelExtend> getStuBaseEntire(GsGxxsStuBaseEntireModelSearch search, Pageable pageable);

    List<GsGxxsStuBaseEntireModelExtend> getStuBaseEntire(GsGxxsStuBaseEntireModelSearch search, Sort sort);

    PageOb<GsGxxsStuContactModelExtend> getStuContact(GsGxxsStuContactModelSearch search, Pageable pageable);

    List<GsGxxsStuContactModelExtend> getStuContact(GsGxxsStuContactModelSearch search, Sort sort);

    PageOb<GsGxxsStuFamilyContactModelExtend> getStuFamilyContact(GsGxxsStuFamilyContactModelSearch search, Pageable pageable);

    List<GsGxxsStuFamilyContactModelExtend> getStuFamilyContact(GsGxxsStuFamilyContactModelSearch search, Sort sort);

    PageOb<GsGxxsScxfApplyDetailModelExtend> getScxfApplyDetail(GsGxxsScxfApplyDetailModelSearch search, Pageable pageable);

    List<GsGxxsScxfApplyDetailModelExtend> getScxfApplyDetail(GsGxxsScxfApplyDetailModelSearch search, Sort sort);

    PageOb<GsGxxsStuCourseScoreModelExtend> getStuCourseScore(GsGxxsStuCourseScoreModelSearch search, Pageable pageable);

    List<GsGxxsStuCourseScoreModelExtend> getStuCourseScore(GsGxxsStuCourseScoreModelSearch search, Sort sort);

    PageOb<GsGxxsStuDormModelExtend> getStuDorm(GsGxxsStuDormModelSearch search, Pageable pageable);

    List<GsGxxsStuDormModelExtend> getStuDorm(GsGxxsStuDormModelSearch search, Sort sort);

    PageOb<GsGxxsStuLevelEaxmInfoModelExtend> getStuLevelEaxmInfo(GsGxxsStuLevelEaxmInfoModelSearch search, Pageable pageable);

    List<GsGxxsStuLevelEaxmInfoModelExtend> getStuLevelEaxmInfo(GsGxxsStuLevelEaxmInfoModelSearch search, Sort sort);

    PageOb<GsGxxsStuXjChangeModelExtend> getStuXjChange(GsGxxsStuXjChangeModelSearch search, Pageable pageable);

    List<GsGxxsStuXjChangeModelExtend> getStuXjChange(GsGxxsStuXjChangeModelSearch search, Sort sort);

    PageOb<GsYktxtPersonConsumeModelExtend> getPersonConsume(GsYktxtPersonConsumeModelSearch search, Pageable pageable);

    List<GsYktxtPersonConsumeModelExtend> getPersonConsume(GsYktxtPersonConsumeModelSearch search, Sort sort);

    PageOb<GsGxbgApplyInfoModelExtend> getApplyInfo(GsGxbgApplyInfoModelSearch search, Pageable pageable);

    List<GsGxbgApplyInfoModelExtend> getApplyInfo(GsGxbgApplyInfoModelSearch search, Sort sort);

    PageOb<GsGxxsStuSocialPracModelExtend> getStuSocialPrac(GsGxxsStuSocialPracModelSearch search, Pageable pageable);

    List<GsGxxsStuSocialPracModelExtend> getStuSocialPrac(GsGxxsStuSocialPracModelSearch search, Sort sort);

    PageOb<GsLibraryBorrowingModelExtend> getLibraryBorrowing(GsLibraryBorrowingModelSearch search, Pageable pageable);

    List<GsLibraryBorrowingModelExtend> getLibraryBorrowing(GsLibraryBorrowingModelSearch search, Sort sort);

    PageOb<GsGxxsStuRegistrationDataModelExtend> getRegistrationData(GsGxxsStuRegistrationDataModelSearch search, Pageable pageable);

    List<GsGxxsStuRegistrationDataModelExtend> getRegistrationData(GsGxxsStuRegistrationDataModelSearch search, Sort sort);

    PageOb<GsGxxsStuPhysiqueResultDataModelExtend> getPhysiqueResultData(GsGxxsStuPhysiqueResultDataModelSearch search, Pageable pageable);

    List<GsGxxsStuPhysiqueResultDataModelExtend> getPhysiqueResultData(GsGxxsStuPhysiqueResultDataModelSearch search, Sort sort);

    PageOb<GsGxxsStuLeaveDataModelExtend> getLeaveData(GsGxxsStuLeaveDataModelSearch search, Pageable pageable);

    List<GsGxxsStuLeaveDataModelExtend> getLeaveData(GsGxxsStuLeaveDataModelSearch search, Sort sort);

    PageOb<GsGxxsStuDormitoryTransferLogDataModelExtend> getDormitoryTransferLogData(GsGxxsStuDormitoryTransferLogDataModelSearch search, Pageable pageable);

    List<GsGxxsStuDormitoryTransferLogDataModelExtend> getDormitoryTransferLogData(GsGxxsStuDormitoryTransferLogDataModelSearch search, Sort sort);

    /**
     * 获取海康人脸数据
     *
     * @param userId
     * @return
     */
    GsImportHikFaceInfoModel getHikFace(String userId);
}
