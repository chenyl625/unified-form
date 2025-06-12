package com.jfbrother.student.service.impl;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.enums.BooleanEnum;
import com.jfbrother.baseserver.jpa.QSysUsers;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.connector.service.ConnectorInfoService;
import com.jfbrother.student.dao.*;
import com.jfbrother.student.jpa.*;
import com.jfbrother.student.model.GsImportHikFaceInfoModel;
import com.jfbrother.student.model.extend.*;
import com.jfbrother.student.model.search.*;
import com.jfbrother.student.service.StudentDataCenterService;
import com.jfbrother.student.service.StudentSearchService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentDataCenterServiceImpl extends BaseServiceImpl implements StudentDataCenterService, StudentSearchService {

    @Autowired
    private GsGxxsAllStuBaseInfoRepository gsGxxsAllStuBaseInfoRepository;
    @Autowired
    private ConnectorInfoService connectorInfoService;

    QSysUsers qSysUsers = QSysUsers.sysUsers;
    QGsGxxsAllStuBaseInfo qGsGxxsAllStuBaseInfo = QGsGxxsAllStuBaseInfo.gsGxxsAllStuBaseInfo;
    QGsGxxsStuBaseEntire qGsGxxsStuBaseEntire = QGsGxxsStuBaseEntire.gsGxxsStuBaseEntire;
    QGsGxxsStuContact qGsGxxsStuContact = QGsGxxsStuContact.gsGxxsStuContact;
    QGsGxxsStuFamilyContact qGsGxxsStuFamilyContact = QGsGxxsStuFamilyContact.gsGxxsStuFamilyContact;
    QGsGxxsScxfApplyDetail qGsGxxsScxfApplyDetail = QGsGxxsScxfApplyDetail.gsGxxsScxfApplyDetail;
    QGsGxxsStuCourseScore qGsGxxsStuCourseScore = QGsGxxsStuCourseScore.gsGxxsStuCourseScore;
    QGsGxxsStuDorm qGsGxxsStuDorm = QGsGxxsStuDorm.gsGxxsStuDorm;
    QGsGxxsStuLevelEaxmInfo qGsGxxsStuLevelEaxmInfo = QGsGxxsStuLevelEaxmInfo.gsGxxsStuLevelEaxmInfo;
    QGsGxxsStuXjChange qGsGxxsStuXjChange = QGsGxxsStuXjChange.gsGxxsStuXjChange;
    QGsYktxtPersonConsume qGsYktxtPersonConsume = QGsYktxtPersonConsume.gsYktxtPersonConsume;
    QGsGxbgApplyInfo qGsGxbgApplyInfo = QGsGxbgApplyInfo.gsGxbgApplyInfo;
    QGsGxxsStuSocialPrac qGsGxxsStuSocialPrac = QGsGxxsStuSocialPrac.gsGxxsStuSocialPrac;
    QGsImportHikFaceInfo qGsImportHikFaceInfo = QGsImportHikFaceInfo.gsImportHikFaceInfo;
    QGsLibraryBorrowing qGsLibraryBorrowing = QGsLibraryBorrowing.gsLibraryBorrowing;
    QGsGxxsStuRegistrationData qGsGxxsStuRegistrationData = QGsGxxsStuRegistrationData.gsGxxsStuRegistrationData;
    QGsGxxsStuPhysiqueResultData qGsGxxsStuPhysiqueResultData = QGsGxxsStuPhysiqueResultData.gsGxxsStuPhysiqueResultData;
    QGsGxxsStuLeaveData qGsGxxsStuLeaveData = QGsGxxsStuLeaveData.gsGxxsStuLeaveData;
    QGsGxxsStuDormitoryTransferLogData qGsGxxsStuDormitoryTransferLogData = QGsGxxsStuDormitoryTransferLogData.gsGxxsStuDormitoryTransferLogData;


    @Override
    public PageOb<GsGxxsAllStuBaseInfoModelExtend> getAllStuBaseInfo(GsGxxsAllStuBaseInfoModelSearch search, Pageable pageable) {
        Predicate pre = fillAllStuBaseInfoPredicate(search);

        Page<GsGxxsAllStuBaseInfo> pageGsGxxsAllStuBaseInfo = gsGxxsAllStuBaseInfoRepository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageGsGxxsAllStuBaseInfo, GsGxxsAllStuBaseInfoModelExtend.class);
    }

    @Override
    public List<GsGxxsAllStuBaseInfoModelExtend> getAllStuBaseInfo(GsGxxsAllStuBaseInfoModelSearch search, Sort sort) {
        Predicate pre = fillAllStuBaseInfoPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<GsGxxsAllStuBaseInfo> pageGsGxxsAllStuBaseInfo = gsGxxsAllStuBaseInfoRepository.findAll(pre, pageable);

        return CopyUtils.copyList(pageGsGxxsAllStuBaseInfo.getContent(), GsGxxsAllStuBaseInfoModelExtend.class);
    }

    @Override
    public PageOb<GsGxxsStuBaseEntireModelExtend> getStuBaseEntire(GsGxxsStuBaseEntireModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getStudentTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuBaseEntire);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuBaseEntireModelExtend.class, getTupleGsGxxsStuBaseEntireModelExtendFunction(dictMap));
    }

    @Override
    public List<GsGxxsStuBaseEntireModelExtend> getStuBaseEntire(GsGxxsStuBaseEntireModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStudentTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuBaseEntire))
                .fetch().stream()
                .map(getTupleGsGxxsStuBaseEntireModelExtendFunction(dictMap)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsStuContactModelExtend> getStuContact(GsGxxsStuContactModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getStuContactTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuContact);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuContactModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuContact), GsGxxsStuContactModelExtend.class));
    }

    @Override
    public List<GsGxxsStuContactModelExtend> getStuContact(GsGxxsStuContactModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStuContactTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuContact))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuContact), GsGxxsStuContactModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsStuFamilyContactModelExtend> getStuFamilyContact(GsGxxsStuFamilyContactModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getStuFamilyContactTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuFamilyContact);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuFamilyContactModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuFamilyContact), GsGxxsStuFamilyContactModelExtend.class));
    }

    @Override
    public List<GsGxxsStuFamilyContactModelExtend> getStuFamilyContact(GsGxxsStuFamilyContactModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStuFamilyContactTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuFamilyContact))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuFamilyContact), GsGxxsStuFamilyContactModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsScxfApplyDetailModelExtend> getScxfApplyDetail(GsGxxsScxfApplyDetailModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getScxfApplyTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsScxfApplyDetail);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsScxfApplyDetailModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsScxfApplyDetail), GsGxxsScxfApplyDetailModelExtend.class));
    }

    @Override
    public List<GsGxxsScxfApplyDetailModelExtend> getScxfApplyDetail(GsGxxsScxfApplyDetailModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getScxfApplyTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsScxfApplyDetail))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsScxfApplyDetail), GsGxxsScxfApplyDetailModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsStuCourseScoreModelExtend> getStuCourseScore(GsGxxsStuCourseScoreModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getStuCourseScoreTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuCourseScore);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuCourseScoreModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuCourseScore), GsGxxsStuCourseScoreModelExtend.class));
    }

    @Override
    public List<GsGxxsStuCourseScoreModelExtend> getStuCourseScore(GsGxxsStuCourseScoreModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStuCourseScoreTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuCourseScore))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuCourseScore), GsGxxsStuCourseScoreModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsStuDormModelExtend> getStuDorm(GsGxxsStuDormModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getStuDormTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuDorm);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuDormModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuDorm), GsGxxsStuDormModelExtend.class));
    }

    @Override
    public List<GsGxxsStuDormModelExtend> getStuDorm(GsGxxsStuDormModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStuDormTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuDorm))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuDorm), GsGxxsStuDormModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsStuLevelEaxmInfoModelExtend> getStuLevelEaxmInfo(GsGxxsStuLevelEaxmInfoModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getStuLevelEaxmInfoTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuLevelEaxmInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuLevelEaxmInfoModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuLevelEaxmInfo), GsGxxsStuLevelEaxmInfoModelExtend.class));
    }

    @Override
    public List<GsGxxsStuLevelEaxmInfoModelExtend> getStuLevelEaxmInfo(GsGxxsStuLevelEaxmInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStuLevelEaxmInfoTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuLevelEaxmInfo))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuLevelEaxmInfo), GsGxxsStuLevelEaxmInfoModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsStuXjChangeModelExtend> getStuXjChange(GsGxxsStuXjChangeModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getStuXjChangeTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuXjChange);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuXjChangeModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuXjChange), GsGxxsStuXjChangeModelExtend.class));
    }

    @Override
    public List<GsGxxsStuXjChangeModelExtend> getStuXjChange(GsGxxsStuXjChangeModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStuXjChangeTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuXjChange))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuXjChange), GsGxxsStuXjChangeModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsYktxtPersonConsumeModelExtend> getPersonConsume(GsYktxtPersonConsumeModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getPersonConsumeTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsYktxtPersonConsume);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsYktxtPersonConsumeModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsYktxtPersonConsume), GsYktxtPersonConsumeModelExtend.class));
    }

    @Override
    public List<GsYktxtPersonConsumeModelExtend> getPersonConsume(GsYktxtPersonConsumeModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getPersonConsumeTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsYktxtPersonConsume))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsYktxtPersonConsume), GsYktxtPersonConsumeModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxbgApplyInfoModelExtend> getApplyInfo(GsGxbgApplyInfoModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getApplyTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxbgApplyInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxbgApplyInfoModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxbgApplyInfo), GsGxbgApplyInfoModelExtend.class));
    }

    @Override
    public List<GsGxbgApplyInfoModelExtend> getApplyInfo(GsGxbgApplyInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getApplyTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxbgApplyInfo))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxbgApplyInfo), GsGxbgApplyInfoModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsStuSocialPracModelExtend> getStuSocialPrac(GsGxxsStuSocialPracModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getStuSocialPracTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuSocialPrac);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuSocialPracModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuSocialPrac), GsGxxsStuSocialPracModelExtend.class));
    }

    @Override
    public List<GsGxxsStuSocialPracModelExtend> getStuSocialPrac(GsGxxsStuSocialPracModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStuSocialPracTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuSocialPrac))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuSocialPrac), GsGxxsStuSocialPracModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsLibraryBorrowingModelExtend> getLibraryBorrowing(GsLibraryBorrowingModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getLibraryBorrowingTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsLibraryBorrowing);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsLibraryBorrowingModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsLibraryBorrowing), GsLibraryBorrowingModelExtend.class));
    }

    @Override
    public List<GsLibraryBorrowingModelExtend> getLibraryBorrowing(GsLibraryBorrowingModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getLibraryBorrowingTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsLibraryBorrowing))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsLibraryBorrowing), GsLibraryBorrowingModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsStuRegistrationDataModelExtend> getRegistrationData(GsGxxsStuRegistrationDataModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getRegistrationDataTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuRegistrationData);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuRegistrationDataModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuRegistrationData), GsGxxsStuRegistrationDataModelExtend.class));
    }

    @Override
    public List<GsGxxsStuRegistrationDataModelExtend> getRegistrationData(GsGxxsStuRegistrationDataModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getRegistrationDataTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuRegistrationData))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuRegistrationData), GsGxxsStuRegistrationDataModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsStuPhysiqueResultDataModelExtend> getPhysiqueResultData(GsGxxsStuPhysiqueResultDataModelSearch search, Pageable pageable) {

        JPAQuery<Tuple> jpaQuery = getPhysiqueResultDataTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuPhysiqueResultData);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuPhysiqueResultDataModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuPhysiqueResultData), GsGxxsStuPhysiqueResultDataModelExtend.class));
    }

    @Override
    public List<GsGxxsStuPhysiqueResultDataModelExtend> getPhysiqueResultData(GsGxxsStuPhysiqueResultDataModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getPhysiqueResultDataTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuPhysiqueResultData))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuPhysiqueResultData), GsGxxsStuPhysiqueResultDataModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsStuLeaveDataModelExtend> getLeaveData(GsGxxsStuLeaveDataModelSearch search, Pageable pageable) {

        JPAQuery<Tuple> jpaQuery = getLeaveDataTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuLeaveData);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuLeaveDataModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuLeaveData), GsGxxsStuLeaveDataModelExtend.class));
    }

    @Override
    public List<GsGxxsStuLeaveDataModelExtend> getLeaveData(GsGxxsStuLeaveDataModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getLeaveDataTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuLeaveData))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuLeaveData), GsGxxsStuLeaveDataModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsGxxsStuDormitoryTransferLogDataModelExtend> getDormitoryTransferLogData(GsGxxsStuDormitoryTransferLogDataModelSearch search, Pageable pageable) {

        JPAQuery<Tuple> jpaQuery = getDormitoryTransferLogDataTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsGxxsStuDormitoryTransferLogData);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsGxxsStuDormitoryTransferLogDataModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuDormitoryTransferLogData), GsGxxsStuDormitoryTransferLogDataModelExtend.class));
    }

    @Override
    public List<GsGxxsStuDormitoryTransferLogDataModelExtend> getDormitoryTransferLogData(GsGxxsStuDormitoryTransferLogDataModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getDormitoryTransferLogDataTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuDormitoryTransferLogData))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuDormitoryTransferLogData), GsGxxsStuDormitoryTransferLogDataModelExtend.class)).collect(Collectors.toList());
    }


    @Override
    public GsImportHikFaceInfoModel getHikFace(String userId) {
        GsImportHikFaceInfo entity = queryFactory.selectFrom(qGsImportHikFaceInfo)
                .leftJoin(qSysUsers).on(qGsImportHikFaceInfo.code.eq(qSysUsers.username))
                .where(qSysUsers.id.eq(userId))
                .fetchFirst();
        if (entity == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "No GsImportHikFaceInfo found.");
        }
        return CopyUtils.copyBean(entity, GsImportHikFaceInfoModel.class);
    }

    private JPAQuery<Tuple> getStudentTupleJPAQuery(GsGxxsStuBaseEntireModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuBaseEntire, qSysUsers.id)
                .from(qGsGxxsStuBaseEntire)
                .leftJoin(qSysUsers)
                .on(qGsGxxsStuBaseEntire.xh.eq(qSysUsers.username));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillStuBaseEntirePredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getStuContactTupleJPAQuery(GsGxxsStuContactModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuContact, qGsGxxsStuBaseEntire.xm)
                .from(qGsGxxsStuContact)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuContact.studentCode.eq(qGsGxxsStuBaseEntire.xh));
        joinStudentInfo(jpaQuery);

        //添加查询条件
        Predicate pre = fillStuContactPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getStuFamilyContactTupleJPAQuery(GsGxxsStuFamilyContactModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuFamilyContact, qGsGxxsStuBaseEntire.xm)
                .from(qGsGxxsStuFamilyContact)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuFamilyContact.studentCode.eq(qGsGxxsStuBaseEntire.xh));
        joinStudentInfo(jpaQuery);

        //添加查询条件
        Predicate pre = fillStuFamilyContactPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getScxfApplyTupleJPAQuery(GsGxxsScxfApplyDetailModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsScxfApplyDetail, qGsGxxsStuBaseEntire.xm)
                .from(qGsGxxsScxfApplyDetail)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsScxfApplyDetail.applyUserXgh.eq(qGsGxxsStuBaseEntire.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillScxfApplyDetailPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getStuCourseScoreTupleJPAQuery(GsGxxsStuCourseScoreModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuCourseScore, qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuCourseScore)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuCourseScore.xh.eq(qGsGxxsStuBaseEntire.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillStuCourseScorePredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getStuDormTupleJPAQuery(GsGxxsStuDormModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuDorm, qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuDorm)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuDorm.studentCode.eq(qGsGxxsStuBaseEntire.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillStuDormPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getStuLevelEaxmInfoTupleJPAQuery(GsGxxsStuLevelEaxmInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuLevelEaxmInfo, qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuLevelEaxmInfo)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuLevelEaxmInfo.userXh.eq(qGsGxxsStuBaseEntire.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillStuLevelEaxmInfoPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getStuXjChangeTupleJPAQuery(GsGxxsStuXjChangeModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuXjChange, qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuXjChange)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuXjChange.xh.eq(qGsGxxsStuBaseEntire.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillStuXjChangePredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getPersonConsumeTupleJPAQuery(GsYktxtPersonConsumeModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsYktxtPersonConsume, qGsGxxsStuBaseEntire.xh)
                .from(qGsYktxtPersonConsume)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsYktxtPersonConsume.code.eq(qGsGxxsStuBaseEntire.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillPersonConsumePredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getApplyTupleJPAQuery(GsGxbgApplyInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxbgApplyInfo, qGsGxxsStuBaseEntire.xh)
                .from(qGsGxbgApplyInfo)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxbgApplyInfo.userCode.eq(qGsGxxsStuBaseEntire.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillApplyInfoPredicate(search);
        //根据角色拼接查询条件
        pre = getPredicateByRole(queryFactory, pre);
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getStuSocialPracTupleJPAQuery(GsGxxsStuSocialPracModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuSocialPrac, qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuSocialPrac)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuSocialPrac.xh.eq(qGsGxxsStuBaseEntire.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillStuSocialPracPredicate(search);
        //根据角色拼接查询条件
        pre = getPredicateByRole(queryFactory, pre);
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getLibraryBorrowingTupleJPAQuery(GsLibraryBorrowingModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsLibraryBorrowing, qGsGxxsStuBaseEntire.xh)
                .from(qGsLibraryBorrowing)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsLibraryBorrowing.userBarcode.eq(qGsGxxsStuBaseEntire.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillLibraryBorrowingPredicate(search);
        //根据角色拼接查询条件
        pre = getPredicateByRole(queryFactory, pre);
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getRegistrationDataTupleJPAQuery(GsGxxsStuRegistrationDataModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuRegistrationData,qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuRegistrationData)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuBaseEntire.xh.eq(qGsGxxsStuRegistrationData.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillRegistrationDataPredicate(search);
        //根据角色拼接查询条件
        pre = getPredicateByRole(queryFactory, pre);
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getPhysiqueResultDataTupleJPAQuery(GsGxxsStuPhysiqueResultDataModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuPhysiqueResultData,qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuPhysiqueResultData)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuBaseEntire.xh.eq(qGsGxxsStuPhysiqueResultData.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillPhysiqueResultDataPredicate(search);
        //根据角色拼接查询条件
        pre = getPredicateByRole(queryFactory, pre);
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getLeaveDataTupleJPAQuery(GsGxxsStuLeaveDataModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuLeaveData,qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuLeaveData)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuBaseEntire.xh.eq(qGsGxxsStuLeaveData.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillLeaveDataredicate(search);
        //根据角色拼接查询条件
        pre = getPredicateByRole(queryFactory, pre);
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getDormitoryTransferLogDataTupleJPAQuery(GsGxxsStuDormitoryTransferLogDataModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuDormitoryTransferLogData,qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuDormitoryTransferLogData)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuBaseEntire.xh.eq(qGsGxxsStuDormitoryTransferLogData.xh));
        joinStudentInfo(jpaQuery);

        Predicate pre = fillDormitoryTransferLogDataredicate(search);
        //根据角色拼接查询条件
        pre = getPredicateByRole(queryFactory, pre);
        jpaQuery.where(pre);

        return jpaQuery;
    }

    @NotNull
    private Function<Tuple, GsGxxsStuBaseEntireModelExtend> getTupleGsGxxsStuBaseEntireModelExtendFunction(Map<String, Map<String, String>> dictMap) {
        return tuple -> {
            GsGxxsStuBaseEntireModelExtend model = CopyUtils.copyBean(tuple.get(qGsGxxsStuBaseEntire), GsGxxsStuBaseEntireModelExtend.class);
            model.setUserId(tuple.get(qSysUsers.id));

            GsGxxsStuBaseEntireModelExtend dto = CopyUtils.copyDictList(dictMap, model, GsGxxsStuBaseEntireModelExtend.class);
            return dto;
        };
    }

    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillAllStuBaseInfoPredicate(GsGxxsAllStuBaseInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsAllStuBaseInfo.xh.like("%" + search.getXh() + "%"));
        }
        if (!StringUtils.isEmpty(search.getXm())) {
            pre = ExpressionUtils.and(pre, qGsGxxsAllStuBaseInfo.xm.like("%" + search.getXm() + "%"));
        }

        return pre;
    }

    private Predicate fillStuBaseEntirePredicate(GsGxxsStuBaseEntireModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuBaseEntire.xh.eq(search.getXh()));
        }
        if (!StringUtils.isEmpty(search.getXm())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuBaseEntire.xm.like("%" + search.getXm() + "%"));
        }

        return pre;
    }

    private Predicate fillStuContactPredicate(GsGxxsStuContactModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getStudentCode())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuContact.studentCode.eq(search.getStudentCode()));
        }

        return pre;
    }

    private Predicate fillStuFamilyContactPredicate(GsGxxsStuFamilyContactModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getStudentCode())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuFamilyContact.studentCode.eq(search.getStudentCode()));
        }

        return pre;
    }

    private Predicate fillScxfApplyDetailPredicate(GsGxxsScxfApplyDetailModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getApplyUserXgh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsScxfApplyDetail.applyUserXgh.eq(search.getApplyUserXgh()));
        }
        if (!StringUtils.isEmpty(search.getXn())) {
            pre = ExpressionUtils.and(pre, qGsGxxsScxfApplyDetail.xn.eq(search.getXn()));
        }
        if (!StringUtils.isEmpty(search.getApplyStatus())) {
            pre = ExpressionUtils.and(pre, qGsGxxsScxfApplyDetail.applyStatus.eq(search.getApplyStatus()));
        }

        return pre;
    }

    private Predicate fillStuCourseScorePredicate(GsGxxsStuCourseScoreModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuCourseScore.xh.like(search.getXh()));
        }
        if (!StringUtils.isEmpty(search.getXn())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuCourseScore.xn.eq(search.getXn()));
        }
        if (!StringUtils.isEmpty(search.getXq())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuCourseScore.xq.eq(search.getXq()));
        }

        return pre;
    }

    private Predicate fillStuDormPredicate(GsGxxsStuDormModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getStudentCode())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuDorm.studentCode.eq(search.getStudentCode()));
        }

        return pre;
    }

    private Predicate fillStuLevelEaxmInfoPredicate(GsGxxsStuLevelEaxmInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuLevelEaxmInfo.userXh.eq(search.getXh()));
        }

        return pre;
    }

    private Predicate fillStuXjChangePredicate(GsGxxsStuXjChangeModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuXjChange.xh.eq(search.getXh()));
        }

        return pre;
    }

    private Predicate fillPersonConsumePredicate(GsYktxtPersonConsumeModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getCode())) {
            pre = ExpressionUtils.and(pre, qGsYktxtPersonConsume.code.eq(search.getCode()));
        }

        return pre;
    }

    private Predicate fillApplyInfoPredicate(GsGxbgApplyInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getUserCode())) {
            pre = ExpressionUtils.and(pre, qGsGxbgApplyInfo.userCode.eq(search.getUserCode()));
        }

        return pre;
    }

    private Predicate fillStuSocialPracPredicate(GsGxxsStuSocialPracModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuSocialPrac.xh.eq(search.getXh()));
        }

        return pre;
    }

    private Predicate fillLibraryBorrowingPredicate(GsLibraryBorrowingModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getUserBarcode())) {
            pre = ExpressionUtils.and(pre, qGsLibraryBorrowing.userBarcode.eq(search.getUserBarcode()));
        }

        return pre;
    }

    private Predicate fillRegistrationDataPredicate(GsGxxsStuRegistrationDataModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuRegistrationData.xh.eq(search.getXh()));
        }
        if (!StringUtils.isEmpty(search.getXn())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuRegistrationData.xn.eq(search.getXn()));
        }
        if (!StringUtils.isEmpty(search.getXq())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuRegistrationData.xq.eq(search.getXq()));
        }
        if (!StringUtils.isEmpty(search.getZczt())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuRegistrationData.zczt.eq(search.getZczt()));
        }

        return pre;
    }

    private Predicate fillPhysiqueResultDataPredicate(GsGxxsStuPhysiqueResultDataModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuPhysiqueResultData.xh.eq(search.getXh()));
        }
        if (!StringUtils.isEmpty(search.getXm())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuPhysiqueResultData.xm.eq(search.getXm()));
        }

        return pre;
    }

    private Predicate fillLeaveDataredicate(GsGxxsStuLeaveDataModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuLeaveData.xh.eq(search.getXh()));
        }
        return pre;
    }

    private Predicate fillDormitoryTransferLogDataredicate(GsGxxsStuDormitoryTransferLogDataModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuDormitoryTransferLogData.xh.eq(search.getXh()));
        }
        return pre;
    }
}
