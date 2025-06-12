package com.jfbrother.digitalPortraitSnap.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.jpa.QSysUsers;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.connector.service.ConnectorInfoService;
import com.jfbrother.dataCenter.jpa.QGsTeacherBaseInfo;
import com.jfbrother.digitalPortraitSnap.dao.*;
import com.jfbrother.digitalPortraitSnap.jpa.*;
import com.jfbrother.digitalPortraitSnap.model.extend.*;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuLeaveMsgModelParam;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuParentsBindHistoryModelParam;
import com.jfbrother.digitalPortraitSnap.model.search.*;
import com.jfbrother.digitalPortraitSnap.service.DigitalPortraitSnapService;
import com.jfbrother.student.jpa.*;
import com.jfbrother.student.model.GsGxxsChargeTeacherModel;
import com.jfbrother.student.model.GsImportHikFaceInfoModel;
import com.jfbrother.student.model.extend.*;
import com.jfbrother.student.model.search.*;
import com.jfbrother.student.service.StudentSearchService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DigitalPortraitSnapServiceImpl extends BaseServiceImpl implements DigitalPortraitSnapService,StudentSearchService {
    @Autowired
    private ConnectorInfoService connectorInfoService;
    @Autowired
    private GsGxxsStuBaseEntireRepository1 gsGxxsStuBaseEntireRepository1;
    @Autowired
    private GsGxxsStuCourseScoreRepository1 gsGxxsStuCourseScoreRepository1;
    @Autowired
    private GsStuLeaveMsgRepository gsStuLeaveMsgRepository;
    @Autowired
    private GsStuParentsBindHistoryRepository gsStuParentsBindHistoryRepository;
    @Autowired
    private GsGxxsChargeTeacherRepository1 gsGxxsChargeTeacherRepository1;
    @Autowired
    private GsGxxsClassInfoRepository gsGxxsClassInfoRepository;


    QSysUsers qSysUsers = QSysUsers.sysUsers;
    QGsImportHikFaceInfo qGsImportHikFaceInfo = QGsImportHikFaceInfo.gsImportHikFaceInfo;
    QGsGxxsStuBaseEntire qGsGxxsStuBaseEntire = QGsGxxsStuBaseEntire.gsGxxsStuBaseEntire;
    QGsGxxsChargeTeacher qGsGxxsChargeTeacher = QGsGxxsChargeTeacher.gsGxxsChargeTeacher;
    QGsGxxsInstitutionBaseInfo qGsGxxsInstitutionBaseInfo = QGsGxxsInstitutionBaseInfo.gsGxxsInstitutionBaseInfo;
    QGxGxxsMajorBaseInfp qGxGxxsMajorBaseInfp = QGxGxxsMajorBaseInfp.gxGxxsMajorBaseInfp;
    QGsTeacherBaseInfo qGsTeacherBaseInfo = QGsTeacherBaseInfo.gsTeacherBaseInfo;
    QGsTeacherBaseInfo qGsTeacherBaseInfo2 = new QGsTeacherBaseInfo("qGsTeacherBaseInfo2");
    QGsGxxsStuLevelEaxmInfo qGsGxxsStuLevelEaxmInfo = QGsGxxsStuLevelEaxmInfo.gsGxxsStuLevelEaxmInfo;
    QGsStuDormNightSign qGsStuDormNightSign = QGsStuDormNightSign.gsStuDormNightSign;
    QGsYktxtPersonConsume qGsYktxtPersonConsume = QGsYktxtPersonConsume.gsYktxtPersonConsume;
    QGsStuDormHealthCheck qGsStuDormHealthCheck = QGsStuDormHealthCheck.gsStuDormHealthCheck;
    QGsGxxsStuDorm qGsGxxsStuDorm = QGsGxxsStuDorm.gsGxxsStuDorm;
    QGsGxxsStuCourseScore qGsGxxsStuCourseScore = QGsGxxsStuCourseScore.gsGxxsStuCourseScore;
    QGsGxbgApplyInfo qGsGxbgApplyInfo = QGsGxbgApplyInfo.gsGxbgApplyInfo;
    QGsGxxsScxfApplyDetail qGsGxxsScxfApplyDetail = QGsGxxsScxfApplyDetail.gsGxxsScxfApplyDetail;
    QGsStuLeaveMsg qGsStuLeaveMsg = QGsStuLeaveMsg.gsStuLeaveMsg;
    QGsStuParentsBindHistory qGsStuParentsBindHistory = QGsStuParentsBindHistory.gsStuParentsBindHistory;
    QGsGxxsStuFamilyContact qGsGxxsStuFamilyContact = QGsGxxsStuFamilyContact.gsGxxsStuFamilyContact;
    QGsGxxsStuLeaveData qGsGxxsStuLeaveData = QGsGxxsStuLeaveData.gsGxxsStuLeaveData;
    QGsGxxsClassInfo qGsGxxsClassInfo = QGsGxxsClassInfo.gsGxxsClassInfo;

    @Override
    public GsImportHikFaceInfoModel getHikFace2(String userId) {
        GsImportHikFaceInfo entity = queryFactory.selectFrom(qGsImportHikFaceInfo)
                .leftJoin(qSysUsers).on(qGsImportHikFaceInfo.code.eq(qSysUsers.username))
                .where(qSysUsers.username.eq(userId))
                .fetchFirst();
        if (entity == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "No GsImportHikFaceInfo found.");
        }
        return CopyUtils.copyBean(entity, GsImportHikFaceInfoModel.class);
    }

    @Override
    public List<GsGxxsStuBaseEntireModelExtend> getStuBaseEntire(GsGxxsStuBaseEntireModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStudentTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuBaseEntire))
                .fetch().stream()
                .map(getTupleGsGxxsStuBaseEntireModelExtendFunction(dictMap)).collect(Collectors.toList());
    }


    private JPAQuery<Tuple> getStudentTupleJPAQuery(GsGxxsStuBaseEntireModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuBaseEntire, qSysUsers.id,qGsGxxsChargeTeacher,qGsGxxsInstitutionBaseInfo.yxmc,qGxGxxsMajorBaseInfp.zymc,qGsTeacherBaseInfo.sj,qGsTeacherBaseInfo2.sj)
                .from(qGsGxxsStuBaseEntire)
                .leftJoin(qSysUsers)
                .on(qGsGxxsStuBaseEntire.xh.eq(qSysUsers.username))
                .leftJoin(qGxGxxsMajorBaseInfp)
                .on(qGsGxxsStuBaseEntire.zyId.eq(qGxGxxsMajorBaseInfp.id));
        joinStudentInfo(jpaQuery);
        jpaQuery.innerJoin(qGsTeacherBaseInfo)
                .on(qGsGxxsChargeTeacher.bzrgh.eq(qGsTeacherBaseInfo.gh))
                .innerJoin(qGsTeacherBaseInfo2)
                .on(qGsGxxsChargeTeacher.fdyh.eq(qGsTeacherBaseInfo2.gh));

        Predicate pre = fillStuBaseEntirePredicate(search);
        //if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
        //    //根据角色拼接查询条件
        //    pre = getPredicateByRole(queryFactory, pre);
        //}
        jpaQuery.where(pre);
        return jpaQuery;
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

    @NotNull
    private Function<Tuple, GsGxxsStuBaseEntireModelExtend> getTupleGsGxxsStuBaseEntireModelExtendFunction(Map<String, Map<String, String>> dictMap) {
        return tuple -> {
            GsGxxsStuBaseEntireModelExtend model = CopyUtils.copyBean(tuple.get(qGsGxxsStuBaseEntire), GsGxxsStuBaseEntireModelExtend.class);
            model.setUserId(tuple.get(qSysUsers.id));
            model.setTeacherInfo(CopyUtils.copyBean(tuple.get(qGsGxxsChargeTeacher),GsGxxsChargeTeacherModel.class));
            model.setYxmc(tuple.get(qGsGxxsInstitutionBaseInfo.yxmc));
            model.setZymc(tuple.get(qGxGxxsMajorBaseInfp.zymc));
            model.setBzrsj(tuple.get(qGsTeacherBaseInfo.sj));
            model.setFdysj(tuple.get(qGsTeacherBaseInfo2.sj));
            GsGxxsStuBaseEntireModelExtend dto = CopyUtils.copyDictList(dictMap, model, GsGxxsStuBaseEntireModelExtend.class);
            return dto;
        };
    }

    @Override
    public List<GsGxxsStuLevelEaxmInfoModelExtend> getStuLevelEaxmInfo(GsGxxsStuLevelEaxmInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStuLevelEaxmInfoTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuLevelEaxmInfo))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuLevelEaxmInfo), GsGxxsStuLevelEaxmInfoModelExtend.class)).collect(Collectors.toList());
    }

    private JPAQuery<Tuple> getStuLevelEaxmInfoTupleJPAQuery(GsGxxsStuLevelEaxmInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuLevelEaxmInfo, qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuLevelEaxmInfo)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuLevelEaxmInfo.userXh.eq(qGsGxxsStuBaseEntire.xh));
        //joinStudentInfo(jpaQuery);

        Predicate pre = fillStuLevelEaxmInfoPredicate(search);
        //if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
        //    //根据角色拼接查询条件
        //    pre = getPredicateByRole(queryFactory, pre);
        //}
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private Predicate fillStuLevelEaxmInfoPredicate(GsGxxsStuLevelEaxmInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();
        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuLevelEaxmInfo.userXh.eq(search.getXh()));
        }
        if (!StringUtils.isEmpty(search.getCompreScore())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuLevelEaxmInfo.compreScore.eq(search.getCompreScore()));
        }
        return pre;
    }

    @Override
    public List<GsStuDormNightSignModelExtend> getStuDormNightSignAll(GsStuDormNightSignModelSearch search, Sort sort) {
        JPAQuery<GsStuDormNightSign> jpaQuery = getStuDormNightSignTupleJPAQuery(search);

        return CopyUtils.copyList(jpaQuery.orderBy(getOrderSpecifiers(sort, qGsStuDormNightSign))
                .fetch(),GsStuDormNightSignModelExtend.class);
    }

    private JPAQuery<GsStuDormNightSign> getStuDormNightSignTupleJPAQuery(GsStuDormNightSignModelSearch search) {
        JPAQuery<GsStuDormNightSign> jpaQuery = queryFactory.select(qGsStuDormNightSign)
                .from(qGsStuDormNightSign);

        Predicate pre = fillStuDormNightSignPredicate(search);
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private Predicate fillStuDormNightSignPredicate(GsStuDormNightSignModelSearch search) {
        Predicate pre = new BooleanBuilder();
        StringTemplate stringTemplate = Expressions.stringTemplate("substring({0},1,4)",qGsStuDormNightSign.checkTime);
        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsStuDormNightSign.xh.eq(search.getXh()));
        }
        if (!StringUtils.isEmpty(search.getCheckYear())) {
            pre = ExpressionUtils.and(pre, stringTemplate.eq(search.getCheckYear()));
        }
        return pre;
    }

    @Override
    public List<GsYktxtPersonConsumeModelExtend> getPersonConsume(GsYktxtPersonConsumeModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getPersonConsumeTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsYktxtPersonConsume))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsYktxtPersonConsume), GsYktxtPersonConsumeModelExtend.class)).collect(Collectors.toList());
    }

    private JPAQuery<Tuple> getPersonConsumeTupleJPAQuery(GsYktxtPersonConsumeModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsYktxtPersonConsume, qGsGxxsStuBaseEntire.xh)
                .from(qGsYktxtPersonConsume)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsYktxtPersonConsume.code.eq(qGsGxxsStuBaseEntire.xh));
        //joinStudentInfo(jpaQuery);

        Predicate pre = fillPersonConsumePredicate(search);
        //if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
        //    //根据角色拼接查询条件
        //    pre = getPredicateByRole(queryFactory, pre);
        //}
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private Predicate fillPersonConsumePredicate(GsYktxtPersonConsumeModelSearch search) {
        Predicate pre = new BooleanBuilder();
        StringTemplate stringTemplate = Expressions.stringTemplate("substring({0},1,4)",qGsYktxtPersonConsume.dealtime);
        if (!StringUtils.isEmpty(search.getCode())) {
            pre = ExpressionUtils.and(pre, qGsYktxtPersonConsume.code.eq(search.getCode()));
        }
        if (!StringUtils.isEmpty(search.getQueryYear())) {
            pre = ExpressionUtils.and(pre, stringTemplate.eq(search.getQueryYear()));
        }
        //过滤 0.00消费记录
        pre = ExpressionUtils.and(pre,qGsYktxtPersonConsume.mondeal.ne(search.getMondeal()));
        return pre;
    }

    @Override
    public JSONObject getConsumMoreThanHowManyStudentsThisYear(GsYktxtPersonConsumeModelSearch search) {
        JSONObject jsonObject = new JSONObject();
        //查在校生人数
        Long allSchoolPers = queryFactory.select(qGsGxxsStuBaseEntire.count())
                .from(qGsGxxsStuBaseEntire).fetchFirst();
        List<Map<String,Object>> list = gsGxxsStuBaseEntireRepository1.getConsumMoreThanHowManyStudentsThisYear(search.getQueryYear(),search.getCode());
        //超越人数,含自身
        int moreThanPers = Integer.parseInt(list.get(0).get("pers").toString());
        //0,表示该学生要么没消费记录，要么合计消费记录为 0
        if(moreThanPers != 0){
            int allPers = allSchoolPers.intValue();
            Long result = Math.round((double)(allPers-moreThanPers)/(double)allPers * 100);
            jsonObject.put("percent",result);
        }else{
            jsonObject.put("percent",0);
        }
        return jsonObject;
    }

    @Override
    public List<GsStuDormHealthCheckModelExtend> getStuDormHealthCheckAll(GsStuDormHealthCheckModelSearch search, Sort sort) {
        JPAQuery<GsStuDormHealthCheck> jpaQuery = getStuDormHealthCheckTupleJPAQuery(search);

        return CopyUtils.copyList(jpaQuery.orderBy(getOrderSpecifiers(sort, qGsStuDormHealthCheck))
                .fetch(),GsStuDormHealthCheckModelExtend.class);
    }

    private JPAQuery<GsStuDormHealthCheck> getStuDormHealthCheckTupleJPAQuery(GsStuDormHealthCheckModelSearch search) {
        JPAQuery<GsStuDormHealthCheck> jpaQuery = queryFactory.select(qGsStuDormHealthCheck)
                .from(qGsStuDormHealthCheck)
                .rightJoin(qGsGxxsStuDorm)
                .on(qGsStuDormHealthCheck.bedroomName.eq(qGsGxxsStuDorm.bedroomName));

        Predicate pre = fillStuDormHealthCheckPredicate(search);
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private Predicate fillStuDormHealthCheckPredicate(GsStuDormHealthCheckModelSearch search) {
        Predicate pre = new BooleanBuilder();
        StringTemplate stringTemplate = Expressions.stringTemplate("substring({0},1,4)",qGsStuDormHealthCheck.checkDate);
        if (!StringUtils.isEmpty(search.getStudentCode())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuDorm.studentCode.eq(search.getStudentCode()));
        }
        if (!StringUtils.isEmpty(search.getCheckYear())) {
            pre = ExpressionUtils.and(pre, stringTemplate.eq(search.getCheckYear()));
        }
        return pre;
    }

    @Override
    public JSONObject getIntraClassRanking(GsGxxsStuCourseScoreModelSearch search) {
        JSONObject jsonObject = new JSONObject();
        //查所在班人数
        Long allClassPers = queryFactory.select(qGsGxxsStuBaseEntire.count())
                .from(qGsGxxsStuBaseEntire)
                .where(qGsGxxsStuBaseEntire.ssbjId.eq(search.getSsbjId())).fetchFirst();
        //查该学生绩点，班内排名
        List<Map<String,Object>> list = gsGxxsStuCourseScoreRepository1.getIntraClassRanking(search.getSsbjId(),search.getXn(),search.getXq(),search.getXh());
        jsonObject.put("ranking",list.size());
        jsonObject.put("allClassPers",allClassPers);
        return jsonObject;
    }

    @Override
    public List<GsGxxsStuCourseScoreModelExtend> getStuCourseScore(GsGxxsStuCourseScoreModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getStuCourseScoreTupleJPAQuery(search);
        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        List<GsGxxsStuCourseScoreModelExtend> list = new ArrayList<>();
        List<Tuple> tuples = jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuCourseScore))
                .fetch();
        if(tuples.size() > 0){
            //求班内排名 List
            List<Map<String,Object>> intraClassRankingList = gsGxxsStuCourseScoreRepository1.getIntraClassRankingBySubject(
                    tuples.get(0).get(qGsGxxsStuCourseScore).getXh(),
                    tuples.get(0).get(qGsGxxsStuCourseScore).getBjdm(),
                    tuples.get(0).get(qGsGxxsStuCourseScore).getXn(),
                    tuples.get(0).get(qGsGxxsStuCourseScore).getXq());

            for(Tuple tuple : tuples){
                for(Map<String,Object> map : intraClassRankingList){
                    if(tuple.get(qGsGxxsStuCourseScore).getKch().equals(map.get("kch"))){
                        String result = map.get("ran").toString() + " / " + map.get("total").toString();
                        GsGxxsStuCourseScoreModelExtend gsGxxsStuCourseScoreModelExtend = CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuCourseScore), GsGxxsStuCourseScoreModelExtend.class);
                        gsGxxsStuCourseScoreModelExtend.setIntraClassRanking(result);
                        list.add(gsGxxsStuCourseScoreModelExtend);
                        break;
                    }
                }
            }
        }
        return list;
    }

    private JPAQuery<Tuple> getStuCourseScoreTupleJPAQuery(GsGxxsStuCourseScoreModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuCourseScore, qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuCourseScore)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuCourseScore.xh.eq(qGsGxxsStuBaseEntire.xh));
        //joinStudentInfo(jpaQuery);
        Predicate pre = fillStuCourseScorePredicate(search);
        //if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
        //    //根据角色拼接查询条件
        //    pre = getPredicateByRole(queryFactory, pre);
        //}
        jpaQuery.where(pre);
        return jpaQuery;
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

    @Override
    public List<GsGxbgApplyInfoModelExtend> getApplyInfo(GsGxbgApplyInfoModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getApplyTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxbgApplyInfo))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxbgApplyInfo), GsGxbgApplyInfoModelExtend.class)).collect(Collectors.toList());
    }

    private JPAQuery<Tuple> getApplyTupleJPAQuery(GsGxbgApplyInfoModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxbgApplyInfo, qGsGxxsStuBaseEntire.xh)
                .from(qGsGxbgApplyInfo)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxbgApplyInfo.userCode.eq(qGsGxxsStuBaseEntire.xh));
        //joinStudentInfo(jpaQuery);

        Predicate pre = fillApplyInfoPredicate(search);
        //根据角色拼接查询条件
        //pre = getPredicateByRole(queryFactory, pre);
        jpaQuery.where(pre);

        return jpaQuery;
    }

    private Predicate fillApplyInfoPredicate(GsGxbgApplyInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();
        if (!StringUtils.isEmpty(search.getUserCode())) {
            pre = ExpressionUtils.and(pre, qGsGxbgApplyInfo.userCode.eq(search.getUserCode()));
        }
        if (!StringUtils.isEmpty(search.getSchoolYear())) {
            pre = ExpressionUtils.and(pre, qGsGxbgApplyInfo.schoolYear.eq(search.getSchoolYear()));
        }
        if (!StringUtils.isEmpty(search.getStatus())) {
            pre = ExpressionUtils.and(pre, qGsGxbgApplyInfo.status.eq(search.getStatus()));
        }
        return pre;
    }

    @Override
    public List<GsGxxsScxfApplyDetailModelExtend> getScxfApplyDetail(GsGxxsScxfApplyDetailModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getScxfApplyTupleJPAQuery(search);

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsScxfApplyDetail))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsScxfApplyDetail), GsGxxsScxfApplyDetailModelExtend.class)).collect(Collectors.toList());
    }

    private JPAQuery<Tuple> getScxfApplyTupleJPAQuery(GsGxxsScxfApplyDetailModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsScxfApplyDetail, qGsGxxsStuBaseEntire.xm)
                .from(qGsGxxsScxfApplyDetail)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsScxfApplyDetail.applyUserXgh.eq(qGsGxxsStuBaseEntire.xh));
        //joinStudentInfo(jpaQuery);

        Predicate pre = fillScxfApplyDetailPredicate(search);
        //if (!StringUtils.isEmpty(search.getIzEnableRole()) && search.getIzEnableRole().equals(BooleanEnum.YES.getStatus())) {
        //    //根据角色拼接查询条件
        //    pre = getPredicateByRole(queryFactory, pre);
        //}
        jpaQuery.where(pre);

        return jpaQuery;
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

    @Override
    public PageOb<GsYktxtPersonConsumeModelExtend> getPersonConsume(GsYktxtPersonConsumeModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = getPersonConsumeTupleJPAQuery(search);

        setPageParams(jpaQuery, pageable, qGsYktxtPersonConsume);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsYktxtPersonConsumeModelExtend.class, tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsYktxtPersonConsume), GsYktxtPersonConsumeModelExtend.class));
    }

    @Override
    public PageOb<GsStuLeaveMsgModelExtend> getLeaveMsg(GsStuLeaveMsgModelSearch search, Pageable pageable) {
        Predicate pre = fillLeaveMsgPredicate(search);
        Page<GsStuLeaveMsg> pageGsStuLeaveMsg = gsStuLeaveMsgRepository.findAll(pre, pageable);
        return CopyUtils.copyPageOb(pageGsStuLeaveMsg, GsStuLeaveMsgModelExtend.class);
    }

    @Override
    @Transactional
    public GsStuLeaveMsgModelExtend addLeaveMsg(GsStuLeaveMsgModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }
        GsStuLeaveMsg entity = CopyUtils.copyBean(model, GsStuLeaveMsg.class);
        entity = gsStuLeaveMsgRepository.save(entity);
        return CopyUtils.copyBean(entity, GsStuLeaveMsgModelExtend.class);
    }

    @Override
    @Transactional
    public void deleteLogical(List<String> ids) {
        if(ids.size() == 0){
            throw new ServiceException(ResultCode.BAD_REQUEST,"ids is required!");
        }
        gsStuLeaveMsgRepository.deleteBatchLogical(ids);
    }

    private Predicate fillLeaveMsgPredicate(GsStuLeaveMsgModelSearch search) {
        Predicate pre = new BooleanBuilder();
        pre = ExpressionUtils.and(pre, qGsStuLeaveMsg.deleteFlag.eq("0"));
        if (!StringUtils.isEmpty(search.getAboutStuCode())) {
            pre = ExpressionUtils.and(pre, qGsStuLeaveMsg.aboutStuCode.eq(search.getAboutStuCode()));
        }
        return pre;
    }

    @Override
    @Transactional
    public GsStuParentsBindHistoryModelExtend addStuParentsBindHistory(GsStuParentsBindHistoryModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }
        GsStuParentsBindHistory entity = CopyUtils.copyBean(model, GsStuParentsBindHistory.class);
        entity = gsStuParentsBindHistoryRepository.save(entity);
        return CopyUtils.copyBean(entity, GsStuParentsBindHistoryModelExtend.class);
    }

    @Override
    public List<GsStuParentsBindHistoryModelExtend> getStuParentsBindHistoryAll(GsStuParentsBindHistoryModelSearch search, Sort sort) {
        Predicate pre = fillStuParentsBindHistoryPredicate(search);
        Pageable pageable = PageRequest.of(0, max, sort);
        Page<GsStuParentsBindHistory> pageGsStuParentsBindHistory = gsStuParentsBindHistoryRepository.findAll(pre, pageable);
        return CopyUtils.copyList(pageGsStuParentsBindHistory.getContent(), GsStuParentsBindHistoryModelExtend.class);
    }

    private Predicate fillStuParentsBindHistoryPredicate(GsStuParentsBindHistoryModelSearch search) {
        Predicate pre = new BooleanBuilder();
        if (!StringUtils.isEmpty(search.getStuCode())) {
            pre = ExpressionUtils.and(pre, qGsStuParentsBindHistory.stuCode.eq(search.getStuCode()));
        }
        return pre;
    }

    @Override
    public Map<String, Object> verifyParentIdentity(String openId,String parentPhone) {
        Map<String,Object> m = new HashMap<>();

        if(openId != null){
            GsStuParentsBindHistory gsStuParentsBindHistory = queryFactory.select(qGsStuParentsBindHistory)
                    .from(qGsStuParentsBindHistory)
                    .where(qGsStuParentsBindHistory.openId.eq(openId)).fetchFirst();
            if(gsStuParentsBindHistory != null){
                m.put("code","0");
                return m;
            }else{
                m.put("code","1");
                return m;
            }
        }
        if(parentPhone != null){
            GsStuParentsBindHistory gsStuParentsBindHistory = queryFactory.select(qGsStuParentsBindHistory)
                    .from(qGsStuParentsBindHistory)
                    .where(qGsStuParentsBindHistory.parentPhone.eq(parentPhone)).fetchFirst();
            if(gsStuParentsBindHistory != null){
                m.put("code","4");
                return m;
            }
            Tuple tuple = queryFactory.select(qGsGxxsStuFamilyContact.studentCode,qGsGxxsStuFamilyContact.relation,qGsGxxsStuFamilyContact.parentName,qGsGxxsStuFamilyContact.phone)
                    .from(qGsGxxsStuFamilyContact)
                    .where(qGsGxxsStuFamilyContact.phone.eq(parentPhone)).fetchFirst();
            if(tuple != null){
                m.put("code","2");
                m.put("data",tuple);
                return m;
            }else{
                m.put("code","3");
                return m;
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> getTeacherInfoByCode(String code) {
        List<Map<String,Object>> list = gsGxxsChargeTeacherRepository1.getTeacherInfoByCode(code);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int getNotReadLeaveMsgCount(String gh,int role) {
        List<Map<String,Object>> list = gsGxxsChargeTeacherRepository1.getNotReadLeaveMsgCount(gh,role);
        if(list.size() > 0){
            return Integer.parseInt(list.get(0).get("total").toString());
        }
        return 0;
    }

    @Override
    public Map<String,Object> getStuMsgPageList(String gh, int role, int page,int size) {
        List<Map<String,Object>> list = gsGxxsChargeTeacherRepository1.getStuMsgPageList(gh,role);

        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("size",size);
        map.put("totalCount",list.size());
        List<Map<String,Object>> subList = list.stream().skip(page * size).limit(size).
                collect(Collectors.toList());
        map.put("result",subList);
        return map;
    }

    @Transactional
    @Override
    public void updateReadOrNot(Map<String, Object> map) {
        if(map.get("role").equals("0")){
            queryFactory.update(qGsStuLeaveMsg).set(qGsStuLeaveMsg.bzrReadOrNot,"1")
                    .where(qGsStuLeaveMsg.aboutStuCode.eq(map.get("aboutStuCode").toString())
                            .and(qGsStuLeaveMsg.bzrReadOrNot.eq("0"))
                            .and(qGsStuLeaveMsg.leaveTime.loe(Long.parseLong(map.get("timeStamp").toString())))).execute();
        }else if(map.get("role").equals("1")){
            queryFactory.update(qGsStuLeaveMsg).set(qGsStuLeaveMsg.fdyReadOrNot,"1")
                    .where(qGsStuLeaveMsg.aboutStuCode.eq(map.get("aboutStuCode").toString())
                            .and(qGsStuLeaveMsg.fdyReadOrNot.eq("0"))
                            .and(qGsStuLeaveMsg.leaveTime.loe(Long.parseLong(map.get("timeStamp").toString())))).execute();
        }else{
            queryFactory.update(qGsStuLeaveMsg).set(qGsStuLeaveMsg.parentReadOrNot,"1")
                    .where(qGsStuLeaveMsg.aboutStuCode.eq(map.get("aboutStuCode").toString())
                            .and(qGsStuLeaveMsg.parentReadOrNot.eq("0"))
                            .and(qGsStuLeaveMsg.leaveTime.loe(Long.parseLong(map.get("timeStamp").toString())))).execute();
        }
    }

    @Override
    public PageOb<Map> getStuSnapShot(String gh, GsGxxsStuBaseEntireModelSearch search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuBaseEntire.xh,qGsGxxsStuBaseEntire.xm,qGsGxxsStuBaseEntire.bjmc,qGsGxxsInstitutionBaseInfo.yxmc)
                .from(qGsGxxsStuBaseEntire);
        joinStudentInfo(jpaQuery);
        Predicate pre = fillStuSnapShotPredicate(gh,search);
        jpaQuery.where(pre);

        setPageParams(jpaQuery,pageable,qGsGxxsStuBaseEntire);
        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();
        return CopyUtils.copyPageObByQueryResults(fetchResults,Map.class,getTupleGsGxxsStuBaseEntireModelExtendFunction());
    }

    private Predicate fillStuSnapShotPredicate(String gh,GsGxxsStuBaseEntireModelSearch search) {
        Predicate pre = new BooleanBuilder();
        if (!StringUtils.isEmpty(gh)) {
            pre = ExpressionUtils.and(pre, ExpressionUtils.or(qGsGxxsChargeTeacher.bzrgh.eq(gh),qGsGxxsChargeTeacher.fdyh.eq(gh)));
        }
        if (!StringUtils.isEmpty(search.getKeyWords())) {
            pre = ExpressionUtils.and(pre, ExpressionUtils.or(qGsGxxsStuBaseEntire.xh.like("%" + search.getKeyWords() + "%"),qGsGxxsStuBaseEntire.xm.like("%" + search.getKeyWords() + "%")));
        }
        return pre;
    }

    @NotNull
    private Function<Tuple, Map> getTupleGsGxxsStuBaseEntireModelExtendFunction() {
        return tuple -> {
            Map map = new HashMap<>();
            map.put("xh",tuple.get(qGsGxxsStuBaseEntire.xh));
            map.put("xm",tuple.get(qGsGxxsStuBaseEntire.xm));
            map.put("bjmc",tuple.get(qGsGxxsStuBaseEntire.bjmc));
            map.put("yxmc",tuple.get(qGsGxxsInstitutionBaseInfo.yxmc));
            return map;
        };
    }

    @Override
    public int getParentNotReadLeaveMsgCount(String aboutStuCode) {
        List<Map<String,Object>> list = gsGxxsChargeTeacherRepository1.getParentNotReadLeaveMsgCount(aboutStuCode);
        if(list.size() > 0){
            return Integer.parseInt(list.get(0).get("total").toString());
        }
        return 0;
    }

    @Override
    public List<GsGxxsStuLeaveDataModelExtend> getLeaveData(GsGxxsStuLeaveDataModelSearch search, Sort sort) {
        JPAQuery<Tuple> jpaQuery = getLeaveDataTupleJPAQuery(search);
        Map<String, Map<String, String>> dictMap = connectorInfoService.getCommonDictMap(search.getApiUrl());
        return jpaQuery.orderBy(getOrderSpecifiers(sort, qGsGxxsStuLeaveData))
                .fetch().stream()
                .map(tuple -> CopyUtils.copyDictList(dictMap, tuple.get(qGsGxxsStuLeaveData), GsGxxsStuLeaveDataModelExtend.class)).collect(Collectors.toList());
    }

    private JPAQuery<Tuple> getLeaveDataTupleJPAQuery(GsGxxsStuLeaveDataModelSearch search) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsGxxsStuLeaveData,qGsGxxsStuBaseEntire.xh)
                .from(qGsGxxsStuLeaveData)
                .leftJoin(qGsGxxsStuBaseEntire)
                .on(qGsGxxsStuBaseEntire.xh.eq(qGsGxxsStuLeaveData.xh));
        joinStudentInfo(jpaQuery);
        Predicate pre = fillLeaveDataredicate(search);
        //根据角色拼接查询条件
        //pre = getPredicateByRole(queryFactory, pre);
        jpaQuery.where(pre);
        return jpaQuery;
    }

    private Predicate fillLeaveDataredicate(GsGxxsStuLeaveDataModelSearch search) {
        Predicate pre = new BooleanBuilder();
        if (!StringUtils.isEmpty(search.getXh())) {
            pre = ExpressionUtils.and(pre, qGsGxxsStuLeaveData.xh.eq(search.getXh()));
        }
        return pre;
    }

    @Override
    public PageOb<GsGxxsClassInfoModelExtend> getClassInfo(GsGxxsClassInfoModelSearch search, Pageable pageable) {
        Predicate pre = fillClassInfoPredicate(search);
        Page<GsGxxsClassInfo> pageGsGxxsClassInfo = gsGxxsClassInfoRepository.findAll(pre, pageable);
        return CopyUtils.copyPageOb(pageGsGxxsClassInfo, GsGxxsClassInfoModelExtend.class);
    }

    private Predicate fillClassInfoPredicate(GsGxxsClassInfoModelSearch search) {
        Predicate pre = new BooleanBuilder();
        if (!StringUtils.isEmpty(search.getNj())) {
            pre = ExpressionUtils.and(pre, qGsGxxsClassInfo.nj.like("%" + search.getNj() + "%"));
        }
        if (!StringUtils.isEmpty(search.getBjmc())) {
            pre = ExpressionUtils.and(pre, qGsGxxsClassInfo.bjmc.like("%" + search.getBjmc() + "%"));
        }
        return pre;
    }
}
