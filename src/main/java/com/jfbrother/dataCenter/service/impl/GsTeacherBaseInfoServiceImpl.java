package com.jfbrother.dataCenter.service.impl;

import com.jfbrother.baseserver.constant.RoleConstant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.dao.SysConnUserRoleRepository;
import com.jfbrother.baseserver.dao.SysUsersRepository;
import com.jfbrother.baseserver.jpa.*;
import com.jfbrother.baseserver.model.SysUsersModel;
import com.jfbrother.baseserver.model.other.UserIdsAndRoleIdsModelOther;
import com.jfbrother.baseserver.service.SysUsersService;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.dataCenter.dao.GsTeacherBaseInfoRepository;
import com.jfbrother.dataCenter.jpa.GsTeacherBaseInfo;
import com.jfbrother.dataCenter.jpa.QGsTeacherAllInfo;
import com.jfbrother.dataCenter.jpa.QGsTeacherBaseInfo;
import com.jfbrother.dataCenter.model.GsTeacherBaseInfoModel;
import com.jfbrother.dataCenter.model.extend.GsTeacherBaseInfoModelExtend;
import com.jfbrother.dataCenter.service.GsTeacherBaseInfoService;
import com.jfbrother.student.jpa.QGsGxxsInstitutionBaseInfo;
import com.jfbrother.student.jpa.QGsGxxsStuBaseEntire;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 教师基本信息service实现
 *
 * @author chenyl@jfbrother.com 2022-07-18
 */
@Service
@Slf4j
public class GsTeacherBaseInfoServiceImpl extends BaseServiceImpl implements GsTeacherBaseInfoService {
    @Autowired
    private GsTeacherBaseInfoRepository repository;
    @Autowired
    private SysUsersRepository sysUsersRepository;
    @Autowired
    private SysConnUserRoleRepository sysConnUserRoleRepository;

    @Autowired
    private SysUsersService sysUsersService;

    QGsTeacherBaseInfo qGsTeacherBaseInfo = QGsTeacherBaseInfo.gsTeacherBaseInfo;
    QGsTeacherAllInfo qGsTeacherAllInfo = QGsTeacherAllInfo.gsTeacherAllInfo;
    QGsGxxsStuBaseEntire qGsGxxsStuBaseEntire = QGsGxxsStuBaseEntire.gsGxxsStuBaseEntire;
    QSysDepartment qSysDepartment = QSysDepartment.sysDepartment;
    QSysUsers qSysUsers = QSysUsers.sysUsers;
    QGsGxxsInstitutionBaseInfo qGsGxxsInstitutionBaseInfo = QGsGxxsInstitutionBaseInfo.gsGxxsInstitutionBaseInfo;

    @Override
    public GsTeacherBaseInfoModel get(String id) {
        GsTeacherBaseInfo entity = findGsTeacherBaseInfoById(id);
        return CopyUtils.copyBean(entity, GsTeacherBaseInfoModel.class);
    }

    @Override
    public PageOb<GsTeacherBaseInfoModelExtend> get(GsTeacherBaseInfoModel search, Pageable pageable) {
        JPAQuery<Tuple> jpaQuery = queryFactory.select(qGsTeacherBaseInfo, qSysUsers.id)
                .from(qGsTeacherBaseInfo)
                .leftJoin(qSysUsers)
                .on(qGsTeacherBaseInfo.gh.eq(qSysUsers.username));

        Predicate pre = fillPredicate(search);
        jpaQuery.where(pre);

        setPageParams(jpaQuery, pageable, qGsTeacherBaseInfo);

        QueryResults<Tuple> fetchResults = jpaQuery.fetchResults();

        return CopyUtils.copyPageObByQueryResults(fetchResults, GsTeacherBaseInfoModelExtend.class, tuple -> {
            GsTeacherBaseInfoModelExtend model = CopyUtils.copyBean(tuple.get(qGsTeacherBaseInfo), GsTeacherBaseInfoModelExtend.class);
            model.setUserId(tuple.get(qSysUsers.id));

            return model;
        });
    }

    @Override
    public List<GsTeacherBaseInfoModel> get(GsTeacherBaseInfoModel search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<GsTeacherBaseInfo> pageGsTeacherBaseInfo = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageGsTeacherBaseInfo.getContent(), GsTeacherBaseInfoModel.class);
    }

    @Override
    public void autoCreate() {
        //获取所有部门
        Map<String, String> deptMap = queryFactory.selectFrom(qSysDepartment).fetch().stream().collect(Collectors.toMap(SysDepartment::getDeptCode, SysDepartment::getId));

        //创建教师
        autoCreateTeacher(deptMap);
        //创建学生
        autoCreateStudent(deptMap);
    }

    @Override
    public List<Map<String, String>> getCurPosition() {
        return queryFactory.select(qGsTeacherAllInfo.curPosition)
                .from(qGsTeacherAllInfo)
                .where(qGsTeacherAllInfo.curPosition.isNotNull().and(qGsTeacherAllInfo.curPosition.ne("")))
                .groupBy(qGsTeacherAllInfo.curPosition).orderBy(qGsTeacherAllInfo.curPosition.asc())
                .fetch().stream().map(str -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("id", str);
                    map.put("name", str);

                    return map;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setAuth(UserIdsAndRoleIdsModelOther model) {
        List<String> userIds = model.getUserIds();
        List<String> roleIds = model.getRoleIds();

        //去重
        userIds = ListUtils.filter(userIds, t -> (String) t);

        for (String userId : userIds) {
            //判断用户是否存在
            if (!sysUsersRepository.findById(userId).isPresent()) {
                throw new ServiceException(ResultCode.NOT_FOUND, "No user!");
            }

            QSysConnUserRole qSysConnUserRole = QSysConnUserRole.sysConnUserRole;
            //获取该用户原先与角色的关联信息
            List<String> _roleIds = queryFactory.select(qSysConnUserRole.roleId).from(qSysConnUserRole).where(qSysConnUserRole.userId.eq(userId)).fetch();
            _roleIds.addAll(roleIds);

            //去重
            _roleIds = ListUtils.filter(_roleIds, t -> (String) t);

            //重新插入关联信息
            List<SysConnUserRole> listSysConnUserRole = new ArrayList<>();
            for (String roleId : _roleIds) {
                listSysConnUserRole.add(SysConnUserRole.of(userId, roleId));
            }
            sysConnUserRoleRepository.saveAll(listSysConnUserRole);
        }
    }

    private void autoCreateTeacher(Map<String, String> deptMap) {
        //给教师生成账号
        List<String> userIds = queryFactory.select(qGsTeacherBaseInfo.gh, qGsTeacherBaseInfo.xm, qGsTeacherBaseInfo.bmh)
                .from(qGsTeacherBaseInfo)
                .leftJoin(qSysUsers)
                .on(qGsTeacherBaseInfo.gh.eq(qSysUsers.username))
                .where(qSysUsers.id.isNull())
                .fetch().stream()
                .map(tuple -> {
                    SysUsersModel usersModel = new SysUsersModel();
                    usersModel.setName(tuple.get(qGsTeacherBaseInfo.xm));
                    usersModel.setUsername(tuple.get(qGsTeacherBaseInfo.gh));
                    usersModel.setDeptId(deptMap.get(tuple.get(qGsTeacherBaseInfo.bmh)));

                    usersModel = sysUsersService.post(usersModel);

                    return usersModel.getId();
                }).collect(Collectors.toList());

        //给教师账号批量赋权
        List<String> roleIds = new ArrayList<>();
        roleIds.add(RoleConstant.TEACHER_ID);

        sysUsersService.putPermit(new UserIdsAndRoleIdsModelOther() {
            {
                setUserIds(userIds);
                setRoleIds(roleIds);
            }
        });
    }

    private void autoCreateStudent(Map<String, String> deptMap) {
        List<String> roleIds = new ArrayList<>();
        roleIds.add(RoleConstant.STUDENT_ID);

        //给学生成账号
        queryFactory.select(qGsGxxsStuBaseEntire.xh, qGsGxxsStuBaseEntire.xm, qGsGxxsInstitutionBaseInfo.yxdm)
                .from(qGsGxxsStuBaseEntire)
                .leftJoin(qGsGxxsInstitutionBaseInfo).on(qGsGxxsStuBaseEntire.yxId.eq(qGsGxxsInstitutionBaseInfo.id))
                .leftJoin(qSysUsers).on(qGsGxxsStuBaseEntire.xh.eq(qSysUsers.username))
                .where(qSysUsers.id.isNull())
                .fetch().stream()
                .forEach(tuple -> {
                    SysUsersModel usersModel = new SysUsersModel();
                    usersModel.setName(tuple.get(qGsGxxsStuBaseEntire.xm));
                    usersModel.setUsername(tuple.get(qGsGxxsStuBaseEntire.xh));
                    usersModel.setDeptId(deptMap.get(tuple.get(qGsGxxsInstitutionBaseInfo.yxdm)));
                    usersModel = sysUsersService.post(usersModel);

                    //赋权
                    sysUsersService.putPermit(usersModel.getId(), roleIds);
                });
    }

    /**
     * 通过id查询教师基本信息
     *
     * @param id
     * @return
     */
    private GsTeacherBaseInfo findGsTeacherBaseInfoById(String id) {
        GsTeacherBaseInfo entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No GsTeacherBaseInfo found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(GsTeacherBaseInfoModel search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getEtlJfuuid())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.etlJfuuid.like("%" + search.getEtlJfuuid() + "%"));
        }
        if (!StringUtils.isEmpty(search.getXm())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.xm.like("%" + search.getXm() + "%"));
        }
        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.gh.eq(search.getGh()));
        }
        if (!StringUtils.isEmpty(search.getCym())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.cym.like("%" + search.getCym() + "%"));
        }
        if (!StringUtils.isEmpty(search.getXb())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.xb.like("%" + search.getXb() + "%"));
        }
        if (!StringUtils.isEmpty(search.getXrzwDm())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.xrzwDm.like("%" + search.getXrzwDm() + "%"));
        }
        if (!StringUtils.isEmpty(search.getCsrq())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.csrq.like("%" + search.getCsrq() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSfzh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.sfzh.like("%" + search.getSfzh() + "%"));
        }
        if (!StringUtils.isEmpty(search.getMzDm())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.mzDm.like("%" + search.getMzDm() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSj())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.sj.like("%" + search.getSj() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIsTeach())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.isTeach.like("%" + search.getIsTeach() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIsDoubleTeacher())) {
        }
        if (!StringUtils.isEmpty(search.getBmh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.bmh.like("%" + search.getBmh() + "%"));
        }
        if (!StringUtils.isEmpty(search.getType())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.type.like("%" + search.getType() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEmail())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.email.like("%" + search.getEmail() + "%"));
        }
        if (!StringUtils.isEmpty(search.getZzmmDm())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.zzmmDm.like("%" + search.getZzmmDm() + "%"));
        }
        if (!StringUtils.isEmpty(search.getPhoneNum())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.phoneNum.like("%" + search.getPhoneNum() + "%"));
        }
        if (!StringUtils.isEmpty(search.getOfficePhone())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.officePhone.like("%" + search.getOfficePhone() + "%"));
        }
        if (!StringUtils.isEmpty(search.getUnicomShortPhone())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.unicomShortPhone.like("%" + search.getUnicomShortPhone() + "%"));
        }
        if (!StringUtils.isEmpty(search.getUnicomPhone())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.unicomPhone.like("%" + search.getUnicomPhone() + "%"));
        }
        if (!StringUtils.isEmpty(search.getMobileShortPhone())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.mobileShortPhone.like("%" + search.getMobileShortPhone() + "%"));
        }
        if (!StringUtils.isEmpty(search.getMobilePhone())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.mobilePhone.like("%" + search.getMobilePhone() + "%"));
        }
        if (!StringUtils.isEmpty(search.getPeopleSort())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.peopleSort.like("%" + search.getPeopleSort() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
        }
        if (!StringUtils.isEmpty(search.getJg())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.jg.like("%" + search.getJg() + "%"));
        }
        if (!StringUtils.isEmpty(search.getXyzjm())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.xyzjm.like("%" + search.getXyzjm() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlPri())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.etlPri.like("%" + search.getEtlPri() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlIsDelete())) {
        }
        if (!StringUtils.isEmpty(search.getEtlIsChecked())) {
        }
        if (!StringUtils.isEmpty(search.getEtlMd5())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.etlMd5.like("%" + search.getEtlMd5() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlKeyMd5())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.etlKeyMd5.like("%" + search.getEtlKeyMd5() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlCheckType())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.etlCheckType.like("%" + search.getEtlCheckType() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlCheckDate())) {
            pre = ExpressionUtils.and(pre, qGsTeacherBaseInfo.etlCheckDate.like("%" + search.getEtlCheckDate() + "%"));
        }

        return pre;
    }
}
