package com.jfbrother.dataCenter.service.impl;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.dataCenter.dao.GsTeacherFamilyMemRepository;
import com.jfbrother.dataCenter.jpa.GsTeacherFamilyMem;
import com.jfbrother.dataCenter.jpa.QGsTeacherFamilyMem;
import com.jfbrother.dataCenter.model.GsTeacherFamilyMemModel;
import com.jfbrother.dataCenter.service.GsTeacherFamilyMemService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * 教师家庭成员service实现
 *
 * @author chenyl@jfbrother.com 2022-07-19
 */
@Service
public class GsTeacherFamilyMemServiceImpl extends BaseServiceImpl implements GsTeacherFamilyMemService {
    @Autowired
    private GsTeacherFamilyMemRepository repository;

    QGsTeacherFamilyMem qGsTeacherFamilyMem = QGsTeacherFamilyMem.gsTeacherFamilyMem;

    @Override
    public GsTeacherFamilyMemModel get(String id) {
        GsTeacherFamilyMem entity = findGsTeacherFamilyMemById(id);
        return CopyUtils.copyBean(entity, GsTeacherFamilyMemModel.class);
    }

    @Override
    public PageOb<GsTeacherFamilyMemModel> get(GsTeacherFamilyMemModel search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<GsTeacherFamilyMem> pageGsTeacherFamilyMem = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageGsTeacherFamilyMem, GsTeacherFamilyMemModel.class);
    }

    @Override
    public List<GsTeacherFamilyMemModel> get(GsTeacherFamilyMemModel search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<GsTeacherFamilyMem> pageGsTeacherFamilyMem = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageGsTeacherFamilyMem.getContent(), GsTeacherFamilyMemModel.class);
    }

    /**
     * 通过id查询教师家庭成员
     *
     * @param id
     * @return
     */
    private GsTeacherFamilyMem findGsTeacherFamilyMemById(String id) {
        GsTeacherFamilyMem entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No GsTeacherFamilyMem found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(GsTeacherFamilyMemModel search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getEtlJfuuid())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.etlJfuuid.like("%" + search.getEtlJfuuid() + "%"));
        }
        if (!StringUtils.isEmpty(search.getGh())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.gh.eq(search.getGh()));
        }
        if (!StringUtils.isEmpty(search.getRsType())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.rsType.like("%" + search.getRsType() + "%"));
        }
        if (!StringUtils.isEmpty(search.getRelationCode())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.relationCode.like("%" + search.getRelationCode() + "%"));
        }
        if (!StringUtils.isEmpty(search.getRelationName())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.relationName.like("%" + search.getRelationName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getMemberName())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.memberName.like("%" + search.getMemberName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getPhone())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.phone.like("%" + search.getPhone() + "%"));
        }
        if (!StringUtils.isEmpty(search.getMemberCompany())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.memberCompany.like("%" + search.getMemberCompany() + "%"));
        }
        if (!StringUtils.isEmpty(search.getAddress())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.address.like("%" + search.getAddress() + "%"));
        }
        if (!StringUtils.isEmpty(search.getZzmmCode())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.zzmmCode.like("%" + search.getZzmmCode() + "%"));
        }
        if (!StringUtils.isEmpty(search.getZzmmName())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.zzmmName.like("%" + search.getZzmmName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIdSort())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.idSort.eq(search.getIdSort()));
        }
        if (!StringUtils.isEmpty(search.getEtlPri())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.etlPri.like("%" + search.getEtlPri() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlIsDelete())) {
        }
        if (!StringUtils.isEmpty(search.getEtlIsChecked())) {
        }
        if (!StringUtils.isEmpty(search.getEtlMd5())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.etlMd5.like("%" + search.getEtlMd5() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlKeyMd5())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.etlKeyMd5.like("%" + search.getEtlKeyMd5() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlCheckType())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.etlCheckType.like("%" + search.getEtlCheckType() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlCheckDate())) {
            pre = ExpressionUtils.and(pre, qGsTeacherFamilyMem.etlCheckDate.like("%" + search.getEtlCheckDate() + "%"));
        }

        return pre;
    }
}
