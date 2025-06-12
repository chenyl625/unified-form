package com.jfbrother.student.service;

import com.jfbrother.baseserver.constant.RoleConstant;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.jpa.QSysConnUserRole;
import com.jfbrother.baseserver.jpa.QSysDepartment;
import com.jfbrother.baseserver.jpa.QSysUsers;
import com.jfbrother.baseserver.jpa.SysConnUserRole;
import com.jfbrother.baseserver.jwt.JwtUser;
import com.jfbrother.baseserver.utils.LoginUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.student.jpa.QGsGxxsChargeTeacher;
import com.jfbrother.student.jpa.QGsGxxsInstitutionBaseInfo;
import com.jfbrother.student.jpa.QGsGxxsStuBaseEntire;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 处理学生条件查询的接口类
 */
@SuppressWarnings("Duplicates")
public interface StudentSearchService {

    QGsGxxsStuBaseEntire qGsGxxsStuBaseEntire = QGsGxxsStuBaseEntire.gsGxxsStuBaseEntire;
    QGsGxxsChargeTeacher qGsGxxsChargeTeacher = QGsGxxsChargeTeacher.gsGxxsChargeTeacher;
    QGsGxxsInstitutionBaseInfo qGsGxxsInstitutionBaseInfo = QGsGxxsInstitutionBaseInfo.gsGxxsInstitutionBaseInfo;
    QSysUsers qSysUsers = QSysUsers.sysUsers;
    QSysDepartment qSysDepartment = QSysDepartment.sysDepartment;

    /**
     * 关联查询学生其他信息的逻辑封装
     *
     * @param jpaQuery
     */
    default void joinStudentInfo(JPAQuery jpaQuery) {
        //学生信息关联
        jpaQuery
                //在校班级的班主任
                .leftJoin(qGsGxxsChargeTeacher)
                .on(qGsGxxsStuBaseEntire.ssbjId.eq(qGsGxxsChargeTeacher.bjdm))
                .leftJoin(qGsGxxsInstitutionBaseInfo)
                .on(qGsGxxsStuBaseEntire.yxId.eq(qGsGxxsInstitutionBaseInfo.id));
    }

    /**
     * 根据角色生成查询条件
     *
     * @return
     */
    default Predicate getPredicateByRole(JPAQueryFactory queryFactory, Predicate pre) {
        return getPredicateByRole(queryFactory, pre, null);
    }

    default Predicate getPredicateByRole(JPAQueryFactory queryFactory, Predicate pre, BooleanExpression booleanExpression) {
        //过滤班级为空的学生
        pre = ExpressionUtils.and(pre, qGsGxxsStuBaseEntire.ssbjId.isNotNull());

        return connPredicateByRole(queryFactory, pre, booleanExpression);
    }

    default Predicate connPredicateByRole(JPAQueryFactory queryFactory, Predicate pre, BooleanExpression booleanExpression) {
        //判断角色
        JwtUser user = LoginUtils.getUserInfo();
        if (user == null) {
            return pre;
        }
        QSysConnUserRole qSysConnUserRole = QSysConnUserRole.sysConnUserRole;
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

        //当前登录人的部门编号
        String deptCode = queryFactory.select(qSysDepartment.deptCode)
                .from(qSysUsers)
                .leftJoin(qSysDepartment).on(qSysUsers.deptId.eq(qSysDepartment.id))
                .where(qSysUsers.id.eq(user.getId()))
                .fetchFirst();
        if (StringUtils.isEmpty(deptCode)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "未获取到当前登陆人的部门信息.");
        }
        List<String> deptCodeList = new ArrayList<>();
        deptCodeList.add(deptCode);

        //条件集合(使用set去重)，使用or相关联
        Set<BooleanExpression> expressions = new HashSet<>();
        //学生属于教师学院的条件
        BooleanExpression deptExpression = qGsGxxsInstitutionBaseInfo.yxdm.in(deptCodeList);
        //班主任是当前登录人
        BooleanExpression chargeTeacherExpression = qGsGxxsChargeTeacher.bzrgh.eq(user.getUsername());
        //辅导员是当前登录人
        BooleanExpression assistantTeacherExpression = qGsGxxsChargeTeacher.fdyh.eq(user.getUsername());

        //分院领导
        if (roleIds.contains(RoleConstant.BRANCH_LEADER)) {
            expressions.add(deptExpression);
        }
        //班主任
        if (roleIds.contains(RoleConstant.CHARGE_ID)) {
            expressions.add(chargeTeacherExpression);
        }
        //辅导员
        if (roleIds.contains(RoleConstant.ASSISTANT_ID)) {
            expressions.add(assistantTeacherExpression);
        }

        if (expressions.size() == 0) {
            //不属于以上任何一种角色
            pre = ExpressionUtils.and(pre, qGsGxxsStuBaseEntire.xh.eq(user.getUsername()));

            return pre;
        }
        //特定的表达式
        if (booleanExpression != null) {
            expressions.add(booleanExpression);
        }
        //先使用or拼接
        Predicate listOr = null;
        for (BooleanExpression expression : expressions) {
            listOr = ExpressionUtils.or(listOr, expression);
        }
        //然后使用and拼接到总条件
        pre = ExpressionUtils.and(pre, listOr);

        return pre;
    }
}
