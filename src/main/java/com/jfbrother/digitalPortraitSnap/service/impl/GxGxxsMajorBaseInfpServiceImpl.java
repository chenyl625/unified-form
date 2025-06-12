package com.jfbrother.digitalPortraitSnap.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.digitalPortraitSnap.dao.GxGxxsMajorBaseInfpRepository;
import com.jfbrother.digitalPortraitSnap.jpa.GxGxxsMajorBaseInfp;
import com.jfbrother.digitalPortraitSnap.jpa.QGxGxxsMajorBaseInfp;
import com.jfbrother.digitalPortraitSnap.model.GxGxxsMajorBaseInfpModel;
import com.jfbrother.digitalPortraitSnap.model.param.GxGxxsMajorBaseInfpModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GxGxxsMajorBaseInfpModelExtend;
import com.jfbrother.digitalPortraitSnap.model.search.GxGxxsMajorBaseInfpModelSearch;
import com.jfbrother.digitalPortraitSnap.service.GxGxxsMajorBaseInfpService;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.querydsl.core.BooleanBuilder;


import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;


/**
 *  全部专业基本信息service实现
 * @author hjy@jfbrother.com 2023-03-08
 */
@Service
public class GxGxxsMajorBaseInfpServiceImpl extends BaseServiceImpl implements GxGxxsMajorBaseInfpService {
    @Autowired
    private GxGxxsMajorBaseInfpRepository repository;

    QGxGxxsMajorBaseInfp qGxGxxsMajorBaseInfp = QGxGxxsMajorBaseInfp.gxGxxsMajorBaseInfp;

    @Override
    @Transactional
    public GxGxxsMajorBaseInfpModelExtend post(GxGxxsMajorBaseInfpModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        GxGxxsMajorBaseInfp entity = CopyUtils.copyBean(model, GxGxxsMajorBaseInfp.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, GxGxxsMajorBaseInfpModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public GxGxxsMajorBaseInfpModelExtend put(String id, GxGxxsMajorBaseInfpModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        GxGxxsMajorBaseInfp entity = findGxGxxsMajorBaseInfpById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, GxGxxsMajorBaseInfpModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public GxGxxsMajorBaseInfpModelExtend patch(String id, GxGxxsMajorBaseInfpModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        GxGxxsMajorBaseInfp entity = findGxGxxsMajorBaseInfpById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, GxGxxsMajorBaseInfpModelExtend.class);
    }

    @Override
    public GxGxxsMajorBaseInfpModelExtend get(String id) {
        GxGxxsMajorBaseInfp entity = findGxGxxsMajorBaseInfpById(id);
        return CopyUtils.copyBean(entity, GxGxxsMajorBaseInfpModelExtend.class);
    }

    @Override
    public PageOb<GxGxxsMajorBaseInfpModelExtend> get(GxGxxsMajorBaseInfpModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<GxGxxsMajorBaseInfp> pageGxGxxsMajorBaseInfp = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageGxGxxsMajorBaseInfp, GxGxxsMajorBaseInfpModelExtend.class);
    }

    @Override
    public List<GxGxxsMajorBaseInfpModelExtend> get(GxGxxsMajorBaseInfpModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<GxGxxsMajorBaseInfp> pageGxGxxsMajorBaseInfp = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageGxGxxsMajorBaseInfp.getContent(), GxGxxsMajorBaseInfpModelExtend.class);
    }

    @Override
    @Transactional
    public void delete(List<String> ids) {
        if(ids.size() == 0){
            throw new ServiceException(ResultCode.BAD_REQUEST,"ids is required!");
        }
        repository.deleteBatch(ids);
    }
    @Override
    @Transactional
    public void deleteLogical(List<String> ids) {
        if(ids.size() == 0){
            throw new ServiceException(ResultCode.BAD_REQUEST,"ids is required!");
        }

        repository.deleteBatchLogical(ids);
    }

    /**
     * 通过id查询全部专业基本信息
     *
     * @param id
     * @return
     */
    private GxGxxsMajorBaseInfp findGxGxxsMajorBaseInfpById(String id) {
        GxGxxsMajorBaseInfp entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No GxGxxsMajorBaseInfp found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(GxGxxsMajorBaseInfpModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getYxId())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.yxId.like("%" + search.getYxId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getYxmc())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.yxmc.like("%" + search.getYxmc() + "%"));
        }
        if (!StringUtils.isEmpty(search.getYxdm())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.yxdm.like("%" + search.getYxdm() + "%"));
        }
        if (!StringUtils.isEmpty(search.getZymc())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.zymc.like("%" + search.getZymc() + "%"));
        }
        if (!StringUtils.isEmpty(search.getZyDm())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.zyDm.like("%" + search.getZyDm() + "%"));
        }
        if (!StringUtils.isEmpty(search.getGbzydm())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.gbzydm.like("%" + search.getGbzydm() + "%"));
        }
        if (!StringUtils.isEmpty(search.getXz())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.xz.like("%" + search.getXz() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlPri())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.etlPri.like("%" + search.getEtlPri() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlMd5())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.etlMd5.like("%" + search.getEtlMd5() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlKeyMd5())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.etlKeyMd5.like("%" + search.getEtlKeyMd5() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlCheckType())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.etlCheckType.like("%" + search.getEtlCheckType() + "%"));
        }
        if (!StringUtils.isEmpty(search.getEtlCheckDate())) {
            pre = ExpressionUtils.and(pre, qGxGxxsMajorBaseInfp.etlCheckDate.like("%" + search.getEtlCheckDate() + "%"));
        }

        return pre;
    }
}
