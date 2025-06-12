package com.jfbrother.digitalPortraitSnap.service;

import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.service.BaseService;
import com.jfbrother.digitalPortraitSnap.model.extend.*;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuLeaveMsgModelParam;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuParentsBindHistoryModelParam;
import com.jfbrother.digitalPortraitSnap.model.search.*;
import com.jfbrother.student.model.GsImportHikFaceInfoModel;
import com.jfbrother.student.model.extend.*;
import com.jfbrother.student.model.search.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface DigitalPortraitSnapService extends BaseService {
    /**
     * 学生数字快照,获取海康人脸数据
     * @param userId
     * @return
     */
    GsImportHikFaceInfoModel getHikFace2(String userId);

    /**
     * 快照系统-在校生列表获取,不分页
     * @param search,sort
     * @return
     */
    List<GsGxxsStuBaseEntireModelExtend> getStuBaseEntire(GsGxxsStuBaseEntireModelSearch search, Sort sort);

    /**
     * 快照系统-等级考试列表获取,不分页
     * @param search,sort
     * @return
     */
    List<GsGxxsStuLevelEaxmInfoModelExtend> getStuLevelEaxmInfo(GsGxxsStuLevelEaxmInfoModelSearch search, Sort sort);

    /**
     * 学生寝室晚归签到列表获取，不分页，默认上限100
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<GsStuDormNightSignModelExtend> getStuDormNightSignAll(GsStuDormNightSignModelSearch search, Sort sort);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询个人消费数据
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<GsYktxtPersonConsumeModelExtend> getPersonConsume(GsYktxtPersonConsumeModelSearch search, Pageable pageable);

    /**
     * 个人消费列表获取，不分页，默认上限100
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<GsYktxtPersonConsumeModelExtend> getPersonConsume(GsYktxtPersonConsumeModelSearch search, Sort sort);

    /**
     * 本年度消费超过多少学生
     */
    JSONObject getConsumMoreThanHowManyStudentsThisYear(GsYktxtPersonConsumeModelSearch search);

    /**
     * 学生寝室卫生检查列表获取，不分页，默认上限100
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<GsStuDormHealthCheckModelExtend> getStuDormHealthCheckAll(GsStuDormHealthCheckModelSearch search, Sort sort);

    /**
     * 绩点，班内排名
     */
    JSONObject getIntraClassRanking(GsGxxsStuCourseScoreModelSearch search);

    /**
     * 学生课程成绩表列表获取，不分页，默认上限100
     * @param search
     * @param sort
     * @return
     */
    List<GsGxxsStuCourseScoreModelExtend> getStuCourseScore(GsGxxsStuCourseScoreModelSearch search, Sort sort);

    /**
     * 办事申请列表获取，不分页，默认上限100
     * @param search
     * @param sort
     * @return
     */
    List<GsGxbgApplyInfoModelExtend> getApplyInfo(GsGxbgApplyInfoModelSearch search, Sort sort);

    /**
     * 三创学分申请详情列表获取，不分页，默认上限100
     * @param search
     * @param sort
     * @return
     */
    List<GsGxxsScxfApplyDetailModelExtend> getScxfApplyDetail(GsGxxsScxfApplyDetailModelSearch search, Sort sort);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<GsStuLeaveMsgModelExtend> getLeaveMsg(GsStuLeaveMsgModelSearch search, Pageable pageable);

    /**
     * 学生家长/教师留言添加
     *
     * @param model 数据
     * @return
     */
    GsStuLeaveMsgModelExtend addLeaveMsg(GsStuLeaveMsgModelParam model);

    /**
     * 根据ids逻辑删除数据,留言
     *
     * @param ids id列表
     */
    void deleteLogical(List<String> ids);

    /**
     * 学生-家长绑定历史信息添加
     * @param model 数据
     * @return
     */
    GsStuParentsBindHistoryModelExtend addStuParentsBindHistory(GsStuParentsBindHistoryModelParam model);

    /**
     * 学生,家长绑定信息不分页
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<GsStuParentsBindHistoryModelExtend> getStuParentsBindHistoryAll(GsStuParentsBindHistoryModelSearch search, Sort sort);

    /**
     * 验证登录家长身份
     * @param openId openId
     * @param parentPhone 家长手机号
     * @return
     */
    Map<String,Object> verifyParentIdentity(String openId,String parentPhone);

    /**
     * 根据教师工号，获得教师信息
     * @param code
     * @return
     */
    Map<String,Object> getTeacherInfoByCode(String code);

    /**
     * 获取教师未读留言数
     * @param gh
     * @param role
     * @return
     */
    int getNotReadLeaveMsgCount(String gh,int role);

    /**
     * 教师，获取各学生留言分页列表
     * @param gh
     * @param role
     * @param page
     * @param size
     * @return
     */
    Map<String,Object> getStuMsgPageList(String gh, int role, int page,int size);

    /**
     * 教师已读留言
     * @param map
     */
    void updateReadOrNot(Map<String,Object> map);

    /**
     * 教师获取学生快照分页
     * @param gh
     * @param search
     * @param pageable
     * @return
     */
    PageOb<Map> getStuSnapShot(String gh,GsGxxsStuBaseEntireModelSearch search,Pageable pageable);

    /**
     * 获取家长未读留言数
     * @param aboutStuCode
     * @return
     */
    int getParentNotReadLeaveMsgCount(String aboutStuCode);

    /**
     * 学生请假列表获取
     * @param search
     * @param sort
     * @return
     */
    List<GsGxxsStuLeaveDataModelExtend> getLeaveData(GsGxxsStuLeaveDataModelSearch search, Sort sort);

    /**
     * 班级信息,分页
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<GsGxxsClassInfoModelExtend> getClassInfo(GsGxxsClassInfoModelSearch search, Pageable pageable);
}
