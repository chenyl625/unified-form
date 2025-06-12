package com.jfbrother.dataCenter.service.impl;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.jpa.QSysDepartment;
import com.jfbrother.baseserver.jpa.QSysUsers;
import com.jfbrother.baseserver.jpa.SysUsers;
import com.jfbrother.baseserver.model.SysDepartmentModel;
import com.jfbrother.baseserver.model.SysUsersModel;
import com.jfbrother.baseserver.service.SysDepartmentService;
import com.jfbrother.baseserver.service.SysUsersService;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.dataCenter.dao.GsGxjgAllDeptRepository;
import com.jfbrother.dataCenter.jpa.GsGxjgAllDept;
import com.jfbrother.dataCenter.jpa.QGsGxjgAllDept;
import com.jfbrother.dataCenter.jpa.QGsTeacherBaseInfo;
import com.jfbrother.dataCenter.model.extend.GsGxjgAllDeptModelExtend;
import com.jfbrother.dataCenter.model.search.GsGxjgAllDeptModelSearch;
import com.jfbrother.dataCenter.service.GsGxjgAllDeptService;
import com.jfbrother.student.jpa.QGsGxxsInstitutionBaseInfo;
import com.jfbrother.student.jpa.QGsGxxsStuBaseEntire;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


/**
 * 人事中历史所有部门信息service实现
 *
 * @author chenyl@jfbrother.com 2022-09-01
 */
@Service
public class GsGxjgAllDeptServiceImpl extends BaseServiceImpl implements GsGxjgAllDeptService {
    @Autowired
    private GsGxjgAllDeptRepository repository;
    @Autowired
    private SysDepartmentService sysDepartmentService;
    @Autowired
    private SysUsersService sysUsersService;

    QGsGxjgAllDept qGsGxjgAllDept = QGsGxjgAllDept.gsGxjgAllDept;
    QSysDepartment qSysDepartment = QSysDepartment.sysDepartment;
    QSysUsers qSysUsers = QSysUsers.sysUsers;
    QGsTeacherBaseInfo qGsTeacherBaseInfo = QGsTeacherBaseInfo.gsTeacherBaseInfo;
    QGsGxxsStuBaseEntire qGsGxxsStuBaseEntire = QGsGxxsStuBaseEntire.gsGxxsStuBaseEntire;
    QGsGxxsInstitutionBaseInfo qGsGxxsInstitutionBaseInfo = QGsGxxsInstitutionBaseInfo.gsGxxsInstitutionBaseInfo;

    @Override
    public GsGxjgAllDeptModelExtend get(String id) {
        GsGxjgAllDept entity = findGsGxjgAllDeptById(id);
        return CopyUtils.copyBean(entity, GsGxjgAllDeptModelExtend.class);
    }

    @Override
    public PageOb<GsGxjgAllDeptModelExtend> get(GsGxjgAllDeptModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<GsGxjgAllDept> pageGsGxjgAllDept = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageGsGxjgAllDept, GsGxjgAllDeptModelExtend.class);
    }

    @Override
    public List<GsGxjgAllDeptModelExtend> get(GsGxjgAllDeptModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<GsGxjgAllDept> pageGsGxjgAllDept = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageGsGxjgAllDept.getContent(), GsGxjgAllDeptModelExtend.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncDept() {
        //更新部门数据
        Map<String, SysDepartmentModel> deptMap = sysDepartmentService.get(new SysDepartmentModel(), Sort.by(Sort.Direction.ASC, "orderNum")).stream().collect(Collectors.toMap(SysDepartmentModel::getDeptCode, item -> item));
        String pid = StringUtils.isEmpty(deptMap.get("00")) ? null : deptMap.get("00").getId();

        this.get(new GsGxjgAllDeptModelSearch(), Sort.by(Sort.Direction.ASC, "sortNum")).stream().forEach(item -> {
            SysDepartmentModel deptModel = deptMap.get(item.getBmdm());
            if (deptModel == null) {
                //新增
                deptModel = new SysDepartmentModel();
                deptModel.setDeptCode(item.getBmdm());
                deptModel.setDeptName(item.getBmmc());
                deptModel.setOrderNum(Long.valueOf(item.getSortNum()));
                deptModel.setPid(pid);
                sysDepartmentService.post(deptModel);
            } else {
                //更新
                deptModel.setDeptName(item.getBmmc());
                deptModel.setOrderNum(Long.valueOf(item.getSortNum()));
                sysDepartmentService.put(deptModel.getId(), deptModel);
            }
        });

        //同步教师部门
        queryFactory.select(qSysUsers, qSysDepartment.id)
                .from(qGsTeacherBaseInfo)
                .leftJoin(qSysUsers).on(qGsTeacherBaseInfo.gh.eq(qSysUsers.username))
                .leftJoin(qSysDepartment).on(qGsTeacherBaseInfo.bmh.eq(qSysDepartment.deptCode))
                .where(qSysUsers.id.isNotNull()
                        .and(qSysUsers.deptId.isNull().or(qSysUsers.deptId.ne(qSysDepartment.id)))
                )
                .fetch().stream()
                .forEach(tuple -> {
                    SysUsers sysUsers = tuple.get(qSysUsers);
                    sysUsers.setDeptId(tuple.get(qSysDepartment.id));
                    sysUsersService.put(sysUsers.getId(), CopyUtils.copyBean(sysUsers, SysUsersModel.class));
                });
        //同步学生部门
        queryFactory.select(qSysUsers, qSysDepartment.id)
                .from(qGsGxxsStuBaseEntire)
                .leftJoin(qGsGxxsInstitutionBaseInfo).on(qGsGxxsStuBaseEntire.yxId.eq(qGsGxxsInstitutionBaseInfo.id))
                .leftJoin(qSysUsers).on(qGsGxxsStuBaseEntire.xh.eq(qSysUsers.username))
                .leftJoin(qSysDepartment).on(qGsGxxsInstitutionBaseInfo.yxdm.eq(qSysDepartment.deptCode))
                .where(qSysUsers.id.isNotNull()
                        .and(qSysUsers.deptId.isNull().or(qSysUsers.deptId.ne(qSysDepartment.id)))
                )
                .fetch().stream()
                .forEach(tuple -> {
                    SysUsers sysUsers = tuple.get(qSysUsers);
                    sysUsers.setDeptId(tuple.get(qSysDepartment.id));
                    sysUsersService.put(sysUsers.getId(), CopyUtils.copyBean(sysUsers, SysUsersModel.class));
                });
    }

    /**
     * 通过id查询人事中历史所有部门信息
     *
     * @param id
     * @return
     */
    private GsGxjgAllDept findGsGxjgAllDeptById(String id) {
        GsGxjgAllDept entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No GsGxjgAllDept found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(GsGxjgAllDeptModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getEtlJfuuid())) {
            pre = ExpressionUtils.and(pre, qGsGxjgAllDept.etlJfuuid.like("%" + search.getEtlJfuuid() + "%"));
        }
        if (!StringUtils.isEmpty(search.getBmdm())) {
            pre = ExpressionUtils.and(pre, qGsGxjgAllDept.bmdm.like("%" + search.getBmdm() + "%"));
        }
        if (!StringUtils.isEmpty(search.getBmmc())) {
            pre = ExpressionUtils.and(pre, qGsGxjgAllDept.bmmc.like("%" + search.getBmmc() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSjbmdm())) {
            pre = ExpressionUtils.and(pre, qGsGxjgAllDept.sjbmdm.like("%" + search.getSjbmdm() + "%"));
        }
        if (!StringUtils.isEmpty(search.getFzr())) {
            pre = ExpressionUtils.and(pre, qGsGxjgAllDept.fzr.like("%" + search.getFzr() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qGsGxjgAllDept.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getEtlPri())) {
            pre = ExpressionUtils.and(pre, qGsGxjgAllDept.etlPri.like("%" + search.getEtlPri() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlIsDelete())) {
        }
        if (!StringUtils.isEmpty(search.getEtlIsChecked())) {
        }
        if (!StringUtils.isEmpty(search.getEtlMd5())) {
            pre = ExpressionUtils.and(pre, qGsGxjgAllDept.etlMd5.like("%" + search.getEtlMd5() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlKeyMd5())) {
            pre = ExpressionUtils.and(pre, qGsGxjgAllDept.etlKeyMd5.like("%" + search.getEtlKeyMd5() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlCheckType())) {
            pre = ExpressionUtils.and(pre, qGsGxjgAllDept.etlCheckType.like("%" + search.getEtlCheckType() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlCheckDate())) {
            pre = ExpressionUtils.and(pre, qGsGxjgAllDept.etlCheckDate.like("%" + search.getEtlCheckDate() + "%"));
        }

        return pre;
    }
}
