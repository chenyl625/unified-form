package com.jfbrother.dataCenter.service.impl;

import com.jfbrother.baseserver.constant.RoleConstant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.enums.BooleanEnum;
import com.jfbrother.baseserver.jpa.QSysConnUserRole;
import com.jfbrother.baseserver.jpa.QSysUsers;
import com.jfbrother.baseserver.jpa.SysConnUserRole;
import com.jfbrother.baseserver.jwt.JwtUser;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.LoginUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.connector.service.ConnectorInfoService;
import com.jfbrother.dataCenter.jpa.*;
import com.jfbrother.dataCenter.model.extend.*;
import com.jfbrother.dataCenter.model.response.TeacherInfoResponseModel;
import com.jfbrother.dataCenter.model.response.TeacherPersonnelResponseModel;
import com.jfbrother.dataCenter.model.search.*;
import com.jfbrother.dataCenter.service.DataCenterService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataCenterServiceImpl extends BaseServiceImpl implements DataCenterService {

    @Autowired
    private ConnectorInfoService connectorInfoService;

    QGsTeacherBaseInfo qGsTeacherBaseInfo = QGsTeacherBaseInfo.gsTeacherBaseInfo;
    QGsTeacherDetailInfo qGsTeacherDetailInfo = QGsTeacherDetailInfo.gsTeacherDetailInfo;
    QGsTeacherAllInfo qGsTeacherAllInfo = QGsTeacherAllInfo.gsTeacherAllInfo;
    QGsTeacherYearKh qGsTeacherYearKh = QGsTeacherYearKh.gsTeacherYearKh;
    QGsTeacherFamilyMem qGsTeacherFamilyMem = QGsTeacherFamilyMem.gsTeacherFamilyMem;
    QGsTeacherPosition qGsTeacherPosition = QGsTeacherPosition.gsTeacherPosition;
    QGsStuEvaluateTeacher qGsStuEvaluateTeacher = QGsStuEvaluateTeacher.gsStuEvaluateTeacher;
    QGsTeacherTeachQualityEvalInfo qGsTeacherTeachQualityEvalInfo = QGsTeacherTeachQualityEvalInfo.gsTeacherTeachQualityEvalInfo;
    QGsKbBasicInfo qGsKbBasicInfo = QGsKbBasicInfo.gsKbBasicInfo;
    QGsTeacherZzmm qGsTeacherZzmm = QGsTeacherZzmm.gsTeacherZzmm;
    QGsTeacherPersonComm qGsTeacherPersonComm = QGsTeacherPersonComm.gsTeacherPersonComm;
    QGsProjectInfo qGsProjectInfo = QGsProjectInfo.gsProjectInfo;
    QGsKyPatentInfo qGsKyPatentInfo = QGsKyPatentInfo.gsKyPatentInfo;
    QGsKyPaperInfo qGsKyPaperInfo = QGsKyPaperInfo.gsKyPaperInfo;
    QGsTeacherEduDegree qGsTeacherEduDegree = QGsTeacherEduDegree.gsTeacherEduDegree;
    QGsCwGzPersonDetail qGsCwGzPersonDetail = QGsCwGzPersonDetail.gsCwGzPersonDetail;
    QGsCwJjPersonDetail qGsCwJjPersonDetail = QGsCwJjPersonDetail.gsCwJjPersonDetail;
    QGsCwProjectTeacherDetail qGsCwProjectTeacherDetail = QGsCwProjectTeacherDetail.gsCwProjectTeacherDetail;
    QSysUsers qSysUsers = QSysUsers.sysUsers;
    QGsGxjgAllDept qGsGxjgAllDept = QGsGxjgAllDept.gsGxjgAllDept;
    QSysConnUserRole qSysConnUserRole = QSysConnUserRole.sysConnUserRole;
    QGsTeacherBaseInfo qGsTeacherBaseInfoForCompare = new QGsTeacherBaseInfo("qGsTeacherBaseInfoForCompare");

    @Override
    public PageOb<GsTeacherBaseInfoModelExtend> getTeacherBase(GsTeacherBaseInfoModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherBaseInfo, qGsGxjgAllDept.bmmc)
                .from(qGsTeacherBaseInfo)
                .leftJoin(qGsGxjgAllDept)
                .on(qGsTeacherBaseInfo.bmh.eq(qGsGxjgAllDept.bmdm))
                .leftJoin(qGsTeacherBaseInfoForCompare).on(qGsTeacherBaseInfo.gh.eq(qGsTeacherBaseInfoForCompare.gh).and(qGsTeacherBaseInfo.etlCheckDate.lt(qGsTeacherBaseInfoForCompare.etlCheckDate)));

        Predicate pre = fillTeacherBasePredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        pre = ExpressionUtils.and(pre, qGsTeacherBaseInfoForCompare.etlJfuuid.isNull());
        jpaQuery.where(pre);

        setPageParams(jpaQuery, pageable, qGsTeacherBaseInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsTeacherBaseInfoModelExtend.class, getTupleGsTeacherBaseInfoModelExtendFunction(dictMap));
    }

    @Override
    public List<GsTeacherBaseInfoModelExtend> getTeacherBase(GsTeacherBaseInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getTeacherBaseTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsTeacherBaseInfo))
                .fetch().stream()
                .map(getTupleGsTeacherBaseInfoModelExtendFunction(dictMap)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsTeacherDetailInfoModelExtend> getTeacherDetail(GsTeacherDetailInfoModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getTeacherDetailTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsTeacherDetailInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsTeacherDetailInfoModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherDetailInfo), GsTeacherDetailInfoModelExtend.class));
    }

    @Override
    public List<GsTeacherDetailInfoModelExtend> getTeacherDetail(GsTeacherDetailInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getTeacherDetailTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsTeacherDetailInfo))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherDetailInfo), GsTeacherDetailInfoModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsTeacherAllInfoModelExtend> getTeacherAll(GsTeacherAllInfoModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getTeacherAllTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsTeacherBaseInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsTeacherAllInfoModelExtend.class, getTupleGsTeacherAllInfoModelExtendFunction(dictMap));
    }

    @Override
    public List<GsTeacherAllInfoModelExtend> getTeacherAll(GsTeacherAllInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getTeacherAllTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsTeacherAllInfo))
                .fetch().stream()
                .map(getTupleGsTeacherAllInfoModelExtendFunction(dictMap)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsTeacherFamilyMemModelExtend> getTeacherFamilyMem(GsTeacherFamilyMemModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getFamilyMemTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsTeacherFamilyMem);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsTeacherFamilyMemModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherFamilyMem), GsTeacherFamilyMemModelExtend.class));
    }

    @Override
    public List<GsTeacherFamilyMemModelExtend> getTeacherFamilyMem(GsTeacherFamilyMemModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getFamilyMemTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsTeacherFamilyMem))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherFamilyMem), GsTeacherFamilyMemModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsTeacherYearKhModelExtend> getTeacherYearKh(GsTeacherYearKhModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getYearKhTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsTeacherYearKh);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsTeacherYearKhModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherYearKh), GsTeacherYearKhModelExtend.class));
    }

    @Override
    public List<GsTeacherYearKhModelExtend> getTeacherYearKh(GsTeacherYearKhModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getYearKhTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsTeacherYearKh))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherYearKh), GsTeacherYearKhModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsTeacherPositionModelExtend> getTeacherPosition(GsTeacherPositionModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getTeacherPositionTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsTeacherPosition);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsTeacherPositionModelExtend.class, getTupleTeacherPositionResponseModelFunction(dictMap));
    }

    @Override
    public List<GsTeacherPositionModelExtend> getTeacherPosition(GsTeacherPositionModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getTeacherPositionTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsTeacherPosition))
                .fetch().stream()
                .map(getTupleTeacherPositionResponseModelFunction(dictMap)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsStuEvaluateTeacherModelExtend> getStuEvaluateTeacher(GsStuEvaluateTeacherModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getStuEvaluateTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsStuEvaluateTeacher);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsStuEvaluateTeacherModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsStuEvaluateTeacher), GsStuEvaluateTeacherModelExtend.class));
    }

    @Override
    public List<GsStuEvaluateTeacherModelExtend> getStuEvaluateTeacher(GsStuEvaluateTeacherModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStuEvaluateTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsStuEvaluateTeacher))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsStuEvaluateTeacher), GsStuEvaluateTeacherModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsKbBasicInfoModelExtend> getKbBasicInfo(GsKbBasicInfoModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getKbBasicTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsKbBasicInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsKbBasicInfoModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsKbBasicInfo), GsKbBasicInfoModelExtend.class));
    }

    @Override
    public List<GsKbBasicInfoModelExtend> getKbBasicInfo(GsKbBasicInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getKbBasicTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsKbBasicInfo))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsKbBasicInfo), GsKbBasicInfoModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsTeacherTeachQualityEvalInfoModelExtend> getTeacherTeachQualityEval(GsTeacherTeachQualityEvalInfoModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getTeachQualityTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsTeacherTeachQualityEvalInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsTeacherTeachQualityEvalInfoModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherTeachQualityEvalInfo), GsTeacherTeachQualityEvalInfoModelExtend.class));
    }

    @Override
    public List<GsTeacherTeachQualityEvalInfoModelExtend> getTeacherTeachQualityEval(GsTeacherTeachQualityEvalInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getTeachQualityTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsTeacherTeachQualityEvalInfo))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherTeachQualityEvalInfo), GsTeacherTeachQualityEvalInfoModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsTeacherZzmmModelExtend> getTeacherZzmm(GsTeacherZzmmModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getTeacherZzmmTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsTeacherZzmm);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsTeacherZzmmModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherZzmm), GsTeacherZzmmModelExtend.class));
    }

    @Override
    public List<GsTeacherZzmmModelExtend> getTeacherZzmm(GsTeacherZzmmModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getTeacherZzmmTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsTeacherZzmm))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherZzmm), GsTeacherZzmmModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsTeacherPersonCommModelExtend> getTeacherPersonComm(GsTeacherPersonCommModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getPersonCommTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsTeacherPersonComm);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsTeacherPersonCommModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherPersonComm), GsTeacherPersonCommModelExtend.class));
    }

    @Override
    public List<GsTeacherPersonCommModelExtend> getTeacherPersonComm(GsTeacherPersonCommModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getPersonCommTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsTeacherPersonComm))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherPersonComm), GsTeacherPersonCommModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsProjectInfoModelExtend> getProjectInfo(GsProjectInfoModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getProjectTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsProjectInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsProjectInfoModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsProjectInfo), GsProjectInfoModelExtend.class));
    }

    @Override
    public List<GsProjectInfoModelExtend> getProjectInfo(GsProjectInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getProjectTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsProjectInfo))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsProjectInfo), GsProjectInfoModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsKyPatentInfoModelExtend> getKyPatent(GsKyPatentInfoModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getPatentTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsKyPatentInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsKyPatentInfoModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsKyPatentInfo), GsKyPatentInfoModelExtend.class));
    }

    @Override
    public List<GsKyPatentInfoModelExtend> getKyPatent(GsKyPatentInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getPatentTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsKyPatentInfo))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsKyPatentInfo), GsKyPatentInfoModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsKyPaperInfoModelExtend> getKyPaper(GsKyPaperInfoModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getPaperTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsKyPaperInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsKyPaperInfoModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsKyPaperInfo), GsKyPaperInfoModelExtend.class));
    }

    @Override
    public List<GsKyPaperInfoModelExtend> getKyPaper(GsKyPaperInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getPaperTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsKyPaperInfo))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsKyPaperInfo), GsKyPaperInfoModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<TeacherPersonnelResponseModel> getTeacherPersonnel(GsTeacherAllInfoModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getTeacherPersonnelTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsTeacherAllInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, TeacherPersonnelResponseModel.class, getTupleTeacherPersonnelResponseModelFunction(dictMap));
    }

    @Override
    public List<TeacherPersonnelResponseModel> getTeacherPersonnel(GsTeacherAllInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getTeacherPersonnelTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsTeacherAllInfo))
                .fetch().stream()
                .map(getTupleTeacherPersonnelResponseModelFunction(dictMap)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsTeacherEduDegreeModelExtend> getTeacherEduDegree(GsTeacherEduDegreeModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getEduDegreeTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsTeacherEduDegree);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsTeacherEduDegreeModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherEduDegree), GsTeacherEduDegreeModelExtend.class));
    }

    @Override
    public List<GsTeacherEduDegreeModelExtend> getTeacherEduDegree(GsTeacherEduDegreeModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getEduDegreeTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsTeacherEduDegree))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherEduDegree), GsTeacherEduDegreeModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsCwGzPersonDetailModelExtend> getGzPersonDetail(GsCwGzPersonDetailModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getGzPersonDetailTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsCwGzPersonDetail);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsCwGzPersonDetailModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsCwGzPersonDetail), GsCwGzPersonDetailModelExtend.class));
    }

    @Override
    public List<GsCwGzPersonDetailModelExtend> getGzPersonDetail(GsCwGzPersonDetailModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getGzPersonDetailTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsCwGzPersonDetail))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsCwGzPersonDetail), GsCwGzPersonDetailModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsCwJjPersonDetailModelExtend> getJjPersonDetail(GsCwJjPersonDetailModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getJjPersonDetailTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsCwJjPersonDetail);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsCwJjPersonDetailModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsCwJjPersonDetail), GsCwJjPersonDetailModelExtend.class));
    }

    @Override
    public List<GsCwJjPersonDetailModelExtend> getJjPersonDetail(GsCwJjPersonDetailModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getJjPersonDetailTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsCwJjPersonDetail))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsCwJjPersonDetail), GsCwJjPersonDetailModelExtend.class)).collect(Collectors.toList());
    }

    @Override
    public PageOb<GsCwProjectTeacherDetailModelExtend> getProjectTeacherDetail(GsCwProjectTeacherDetailModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getProjectTeacherDetailTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsCwProjectTeacherDetail);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsCwProjectTeacherDetailModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsCwProjectTeacherDetail), GsCwProjectTeacherDetailModelExtend.class));
    }

    @Override
    public List<GsCwProjectTeacherDetailModelExtend> getProjectTeacherDetail(GsCwProjectTeacherDetailModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getProjectTeacherDetailTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsCwProjectTeacherDetail))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsCwProjectTeacherDetail), GsCwProjectTeacherDetailModelExtend.class)).collect(Collectors.toList());
    }

    private JPAQuery<Tuple> getTeacherTupleJPAQuery() {
        return queryFactory.select(qGsTeacherBaseInfo, qGsTeacherAllInfo.pinyin, qGsTeacherAllInfo.sex
                , qGsTeacherAllInfo.birthPlace, qGsTeacherAllInfo.nationName, qGsTeacherAllInfo.gj
                , qGsTeacherAllInfo.sfzType, qGsTeacherAllInfo.hyzk
                , qGsTeacherAllInfo.topEdu, qGsTeacherAllInfo.deptName, qGsTeacherDetailInfo.nowPosition
        )
                .from(qGsTeacherBaseInfo)
                .leftJoin(qGsTeacherAllInfo)
                .on(qGsTeacherAllInfo.gh.eq(qGsTeacherBaseInfo.gh))
                .leftJoin(qGsTeacherDetailInfo)
                .on(qGsTeacherDetailInfo.gh.eq(qGsTeacherBaseInfo.gh));
    }

    private JPAQuery<Tuple> getTeacherBaseTupleJPAQuery(GsTeacherBaseInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherBaseInfo, qGsGxjgAllDept.bmmc)
                .from(qGsTeacherBaseInfo)
                .leftJoin(qGsGxjgAllDept)
                .on(qGsTeacherBaseInfo.bmh.eq(qGsGxjgAllDept.bmdm));

        Predicate pre = fillTeacherBasePredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getTeacherDetailTupleJPAQuery(GsTeacherDetailInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherDetailInfo, qGsTeacherBaseInfo.gh)
                .from(qGsTeacherDetailInfo)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsTeacherDetailInfo.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillTeacherDetailPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getTeacherAllTupleJPAQuery(GsTeacherAllInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherAllInfo, qSysUsers.id)
                .from(qGsTeacherBaseInfo)
                .leftJoin(qSysUsers).on(qGsTeacherBaseInfo.gh.eq(qSysUsers.username))
                .leftJoin(qSysConnUserRole).on(qSysUsers.id.eq(qSysConnUserRole.userId))
                .leftJoin(qGsTeacherAllInfo).on(qGsTeacherAllInfo.gh.eq(qGsTeacherBaseInfo.gh));

        Predicate pre = fillTeacherAllPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre).groupBy(qGsTeacherBaseInfo);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getFamilyMemTupleJPAQuery(GsTeacherFamilyMemModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherFamilyMem, qGsTeacherBaseInfo.gh)
                .from(qGsTeacherFamilyMem)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsTeacherFamilyMem.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillTeacherFamilyMemPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getYearKhTupleJPAQuery(GsTeacherYearKhModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherYearKh, qGsTeacherBaseInfo.gh)
                .from(qGsTeacherYearKh)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsTeacherYearKh.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillTeacherYearKhPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getTeacherPositionTupleJPAQuery(GsTeacherPositionModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherPosition, qGsTeacherPosition.etlJfuuid)
                .from(qGsTeacherPosition)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsTeacherPosition.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillTeacherPositionPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private JPAQuery<Tuple> getStuEvaluateTupleJPAQuery(GsStuEvaluateTeacherModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsStuEvaluateTeacher, qGsTeacherBaseInfo.gh)
                .from(qGsStuEvaluateTeacher)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsStuEvaluateTeacher.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillStuEvaluateTeacherPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getKbBasicTupleJPAQuery(GsKbBasicInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsKbBasicInfo, qGsTeacherBaseInfo.gh)
                .from(qGsKbBasicInfo)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsKbBasicInfo.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillKbBasicInfoPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getTeachQualityTupleJPAQuery(GsTeacherTeachQualityEvalInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherTeachQualityEvalInfo, qGsTeacherBaseInfo.gh)
                .from(qGsTeacherTeachQualityEvalInfo)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsTeacherTeachQualityEvalInfo.pjjs.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillTeacherTeachQualityEvalPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getTeacherZzmmTupleJPAQuery(GsTeacherZzmmModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherZzmm, qGsTeacherBaseInfo.gh)
                .from(qGsTeacherZzmm)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsTeacherZzmm.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillTeacherZzmmPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getPersonCommTupleJPAQuery(GsTeacherPersonCommModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherPersonComm, qGsTeacherBaseInfo.gh)
                .from(qGsTeacherPersonComm)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsTeacherPersonComm.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillTeacherPersonCommPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getProjectTupleJPAQuery(GsProjectInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsProjectInfo, qGsTeacherBaseInfo.gh)
                .from(qGsProjectInfo)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsProjectInfo.chargerCode.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillProjectInfoPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getPatentTupleJPAQuery(GsKyPatentInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsKyPatentInfo, qGsTeacherBaseInfo.gh)
                .from(qGsKyPatentInfo)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsKyPatentInfo.firstAuthorCode.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillKyPatentPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getPaperTupleJPAQuery(GsKyPaperInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsKyPaperInfo, qGsTeacherBaseInfo.gh)
                .from(qGsKyPaperInfo)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsKyPaperInfo.firstAuthorCode.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillKyPaperPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getTeacherPersonnelTupleJPAQuery(GsTeacherAllInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherAllInfo.gh
                , qGsTeacherAllInfo.isDouble
                , qGsTeacherAllInfo.isQuali
        )
                .from(qGsTeacherAllInfo)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsTeacherAllInfo.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillTeacherAllPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getEduDegreeTupleJPAQuery(GsTeacherEduDegreeModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherEduDegree, qGsTeacherBaseInfo.gh)
                .from(qGsTeacherEduDegree)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsTeacherEduDegree.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillTeacherEduDegreePredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getGzPersonDetailTupleJPAQuery(GsCwGzPersonDetailModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsCwGzPersonDetail, qGsTeacherBaseInfo.gh)
                .from(qGsCwGzPersonDetail)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsCwGzPersonDetail.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillGzPersonDetailPredicate(search);
        //根据角色拼接查询条件
        pre = getPredicateByRole(queryFactory, pre);
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getJjPersonDetailTupleJPAQuery(GsCwJjPersonDetailModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsCwJjPersonDetail, qGsTeacherBaseInfo.gh)
                .from(qGsCwJjPersonDetail)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsCwJjPersonDetail.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillJjPersonDetailPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private JPAQuery<Tuple> getProjectTeacherDetailTupleJPAQuery(GsCwProjectTeacherDetailModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsCwProjectTeacherDetail, qGsTeacherBaseInfo.gh)
                .from(qGsCwProjectTeacherDetail)
                .leftJoin(qGsTeacherBaseInfo)
                .on(qGsCwProjectTeacherDetail.gh.eq(qGsTeacherBaseInfo.gh));

        //添加查询条件
        Predicate pre = fillProjectTeacherDetailPredicate(search);
        if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
            //根据角色拼接查询条件
            pre = getPredicateByRole(queryFactory, pre);
        }
        jpaQuery.where(pre);
        return jpaQuery;
    }

    @NotNull
    private Function<Tuple, TeacherInfoResponseModel> getTupleTeacherInfoResponseModelFunction(Map<String, Map<String, String>> dictMap) {
        return tuple -> {
            TeacherInfoResponseModel model = CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherBaseInfo), TeacherInfoResponseModel.class);
            model.setPinyin(tuple.get(qGsTeacherAllInfo.pinyin));
            model.setSex(tuple.get(qGsTeacherAllInfo.sex));
            model.setBirthPlace(tuple.get(qGsTeacherAllInfo.birthPlace));
            model.setNationName(tuple.get(qGsTeacherAllInfo.nationName));
            model.setGj(tuple.get(qGsTeacherAllInfo.gj));
            model.setSfzType(tuple.get(qGsTeacherAllInfo.sfzType));
            model.setHyzk(tuple.get(qGsTeacherAllInfo.hyzk));
            model.setTopEdu(tuple.get(qGsTeacherAllInfo.topEdu));
            model.setZzmmName(tuple.get(qGsTeacherAllInfo.zzmmName));
            model.setDeptName(tuple.get(qGsTeacherAllInfo.deptName));
            //拼接所在学院及现任职务
            String str = null;
            if (!StringUtils.isEmpty(tuple.get(qGsTeacherAllInfo.deptName))) {
                str = tuple.get(qGsTeacherAllInfo.deptName) + "、";
            }
            if (!StringUtils.isEmpty(tuple.get(qGsTeacherDetailInfo.nowPosition))) {
                str += tuple.get(qGsTeacherDetailInfo.nowPosition) + "、";
            }
            if (!StringUtils.isEmpty(str)) {
                str = str.substring(0, str.lastIndexOf("、"));
            }
            model.setDeptPosition(str);

            return model;
        };
    }

    @NotNull
    private Function<Tuple, GsTeacherBaseInfoModelExtend> getTupleGsTeacherBaseInfoModelExtendFunction(Map<String, Map<String, String>> dictMap) {
        return tuple -> {
            GsTeacherBaseInfoModelExtend model = CopyUtils.copyBean(tuple.get(qGsTeacherBaseInfo), GsTeacherBaseInfoModelExtend.class);
            model.setBmmc(tuple.get(qGsGxjgAllDept.bmmc));

            GsTeacherBaseInfoModelExtend dto = CopyUtils.copyDictList(dictMap, model, GsTeacherBaseInfoModelExtend.class);
            return dto;
        };
    }

    private Function<Tuple, GsTeacherAllInfoModelExtend> getTupleGsTeacherAllInfoModelExtendFunction(Map<String, Map<String, String>> dictMap) {
        return tuple -> {
            GsTeacherAllInfoModelExtend model = CopyUtils.copyDictList(dictMap, tuple.get(qGsTeacherAllInfo), GsTeacherAllInfoModelExtend.class);
            model.setUserId(tuple.get(qSysUsers.id));

            return model;
        };
    }

    private Function<Tuple, GsTeacherPositionModelExtend> getTupleTeacherPositionResponseModelFunction(Map<String, Map<String, String>> dictMap) {
        return tuple -> {
            GsTeacherPositionModelExtend model = CopyUtils.copyBean(tuple.get(qGsTeacherPosition), GsTeacherPositionModelExtend.class);
            //拼接现专业技术等级及聘任时间
            String str = null;
            if (!StringUtils.isEmpty(model.getPositionName())) {
                str = model.getPositionName() + "、";
            }
            if (!StringUtils.isEmpty(model.getAcquireDate())) {
                str += model.getAcquireDate() + "、";
            }
            if (!StringUtils.isEmpty(str)) {
                str = str.substring(0, str.lastIndexOf("、"));
            }
            model.setPositionNameDate(str);

            GsTeacherPositionModelExtend dto = CopyUtils.copyDictList(dictMap, model, GsTeacherPositionModelExtend.class);
            return dto;
        };
    }

    private Function<Tuple, TeacherPersonnelResponseModel> getTupleTeacherPersonnelResponseModelFunction(Map<String, Map<String, String>> dictMap) {
        return tuple -> {
            TeacherPersonnelResponseModel model = new TeacherPersonnelResponseModel();
            model.setGh(tuple.get(qGsTeacherAllInfo.gh));
            model.setIsDouble(tuple.get(qGsTeacherAllInfo.isDouble));
            model.setIsQuali(tuple.get(qGsTeacherAllInfo.isQuali));

            return CopyUtils.copyDictList(dictMap, model, TeacherPersonnelResponseModel.class);
        };
    }

    private Predicate fillTeacherBasePredicate(GsTeacherBaseInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.gh.eq(search.getGh()));
        }
        if (!StringUtils.isEmpty(search.getKeyWords())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.gh.like("%" + search.getKeyWords() + "%").or(qGsTeacherBaseInfo.xm.like("%" + search.getKeyWords() + "%")));
        }
        pre = ExpressionUtils.and(pre,qGsTeacherBaseInfo.peopleSort.in("03","04","05"));
        return pre;
    }

    private Predicate fillTeacherDetailPredicate(GsTeacherDetailInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherDetailInfo.gh.eq(search.getGh()));
        }

        return pre;
    }

    private Predicate fillTeacherAllPredicate(GsTeacherAllInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherAllInfo.gh.eq(search.getGh()));
        }
        if (!StringUtils.isEmpty(search.getCurPosition())) {
            pre = ExpressionUtils.and(pre, qGsTeacherAllInfo.curPosition.eq(search.getCurPosition()));
        }
        if (!StringUtils.isEmpty(search.getKeyWords())) {
            pre = ExpressionUtils.and(pre, qGsTeacherAllInfo.gh.like("%" + search.getKeyWords() + "%").or(qGsTeacherAllInfo.name.like("%" + search.getKeyWords() + "%")));
        }
        if (!StringUtils.isEmpty(search.getRoleId())) {
            pre = ExpressionUtils.and(pre, qSysConnUserRole.roleId.eq(search.getRoleId()));
        }
        if (!StringUtils.isEmpty(search.getDeptName())) {
            pre = ExpressionUtils.and(pre, qGsTeacherAllInfo.deptName.eq(search.getDeptName()));
        }
        return pre;
    }

    private Predicate fillTeacherFamilyMemPredicate(GsTeacherFamilyMemModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.gh.eq(search.getGh()));
        }

        return pre;
    }

    private Predicate fillTeacherYearKhPredicate(GsTeacherYearKhModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherYearKh.gh.eq(search.getGh()));
        }
        if (!StringUtils.isEmpty(search.getXn())) {
            pre = ExpressionUtils.and(pre, qGsTeacherYearKh.xn.eq(search.getXn()));
        }

        return pre;
    }

    private Predicate fillTeacherPositionPredicate(GsTeacherPositionModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherPosition.gh.eq(search.getGh()));
        }

        return pre;
    }

    private Predicate fillStuEvaluateTeacherPredicate(GsStuEvaluateTeacherModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsStuEvaluateTeacher.gh.eq(search.getGh()));
        }

        return pre;
    }

    private Predicate fillKbBasicInfoPredicate(GsKbBasicInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsKbBasicInfo.gh.eq(search.getGh()));
        }

        return pre;
    }

    private Predicate fillTeacherTeachQualityEvalPredicate(GsTeacherTeachQualityEvalInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getPjjs())) {
            pre = ExpressionUtils.and(pre, qGsTeacherTeachQualityEvalInfo.pjjs.eq(search.getPjjs()));
        }

        return pre;
    }

    private Predicate fillTeacherZzmmPredicate(GsTeacherZzmmModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherZzmm.gh.eq(search.getGh()));
        }

        return pre;
    }

    private Predicate fillTeacherPersonCommPredicate(GsTeacherPersonCommModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherPersonComm.gh.eq(search.getGh()));
        }

        return pre;
    }

    private Predicate fillProjectInfoPredicate(GsProjectInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getChargerCode())) {
            pre = ExpressionUtils.and(pre, qGsProjectInfo.chargerCode.eq(search.getChargerCode()));
        }
        if (!StringUtils.isEmpty(search.getProjectClassId())) {
            pre = ExpressionUtils.and(pre, qGsProjectInfo.projectClassId.eq(search.getProjectClassId()));
        }
        if (!StringUtils.isEmpty(search.getCheckStatusId())) {
            pre = ExpressionUtils.and(pre, qGsProjectInfo.checkStatusId.eq(search.getCheckStatusId()));
        }

        return pre;
    }

    private Predicate fillKyPatentPredicate(GsKyPatentInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getFirstAuthorCode())) {
            pre = ExpressionUtils.and(pre, qGsKyPatentInfo.firstAuthorCode.eq(search.getFirstAuthorCode()));
        }
        if (!StringUtils.isEmpty(search.getCheckStatusId())) {
            pre = ExpressionUtils.and(pre, qGsKyPatentInfo.checkStatusId.eq(search.getCheckStatusId()));
        }

        return pre;
    }

    private Predicate fillKyPaperPredicate(GsKyPaperInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getFirstAuthorCode())) {
            pre = ExpressionUtils.and(pre, qGsKyPaperInfo.firstAuthorCode.eq(search.getFirstAuthorCode()));
        }
        if (!StringUtils.isEmpty(search.getCheckStatusId())) {
            pre = ExpressionUtils.and(pre, qGsKyPaperInfo.checkStatusId.eq(search.getCheckStatusId()));
        }

        return pre;
    }

    private Predicate fillTeacherEduDegreePredicate(GsTeacherEduDegreeModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherEduDegree.gh.eq(search.getGh()));
        }

        return pre;
    }

    private Predicate fillGzPersonDetailPredicate(GsCwGzPersonDetailModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsCwGzPersonDetail.gh.eq(search.getGh()));
        }

        return pre;
    }

    private Predicate fillJjPersonDetailPredicate(GsCwJjPersonDetailModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsCwJjPersonDetail.gh.eq(search.getGh()));
        }

        return pre;
    }

    private Predicate fillProjectTeacherDetailPredicate(GsCwProjectTeacherDetailModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsCwProjectTeacherDetail.gh.eq(search.getGh()));
        }

        return pre;
    }

    /**
     * 根据角色生成查询条件
     *
     * @return
     */
    private Predicate getPredicateByRole(JPAQueryFactory queryFactory, Predicate pre) {
        //判断角色
        JwtUser user = LoginUtils.getUserInfo();
        if (user == null) {
            return pre;
        }

        List<SysConnUserRole> listSysConnUserRole = queryFactory.select(qSysConnUserRole)
                .from(qSysConnUserRole)
                .where(qSysConnUserRole.userId.eq(user.getId()))
                .fetch();
        List<String> roleIds = new ArrayList<>();
        for (SysConnUserRole sysConnUserRole : listSysConnUserRole) {
            roleIds.add(sysConnUserRole.getRoleId());
        }

        //如果不是超级管理员或校级领导，则需要进行角色验权
        if ("admin".equals(user.getId()) || roleIds.contains(RoleConstant.SUPER_ADMIN) || roleIds.contains(RoleConstant.SCHOOL_LEADER)) {
            return pre;
        }

        //普通教师
        pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.gh.eq(user.getUsername()));

        return pre;
    }
}
