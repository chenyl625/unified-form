package com.jfbrother.customForm.service.impl;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.customForm.dao.CustomFormReleaseConnRepository;
import com.jfbrother.customForm.jpa.CustomFormReleaseConn;
import com.jfbrother.customForm.jpa.QCustomFormReleaseConn;
import com.jfbrother.customForm.model.extend.CustomFormReleaseConnModelExtend;
import com.jfbrother.customForm.model.param.CustomFormReleaseConnModelParam;
import com.jfbrother.customForm.model.search.CustomFormReleaseConnModelSearch;
import com.jfbrother.customForm.service.CustomFormReleaseConnService;
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
import java.util.NoSuchElementException;


/**
 * 自定义表单发布对应接口service实现
 *
 * @author chenyl@jfbrother.com 2022-06-30
 */
@Service
public class CustomFormReleaseConnServiceImpl extends BaseServiceImpl implements CustomFormReleaseConnService {
    @Autowired
    private CustomFormReleaseConnRepository repository;

    QCustomFormReleaseConn qCustomFormReleaseConn = QCustomFormReleaseConn.customFormReleaseConn;

    @Override
    @Transactional
    public CustomFormReleaseConnModelExtend post(CustomFormReleaseConnModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        CustomFormReleaseConn entity = CopyUtils.copyBean(model, CustomFormReleaseConn.class);

        entity = repository.save(entity);

        return CopyUtils.copyBean(entity, CustomFormReleaseConnModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public CustomFormReleaseConnModelExtend put(String id, CustomFormReleaseConnModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormReleaseConn entity = findCustomFormReleaseConnById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);

        return CopyUtils.copyBean(entity, CustomFormReleaseConnModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public CustomFormReleaseConnModelExtend patch(String id, CustomFormReleaseConnModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormReleaseConn entity = findCustomFormReleaseConnById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormReleaseConnModelExtend.class);
    }

    @Override
    public CustomFormReleaseConnModelExtend get(String id) {
        CustomFormReleaseConn entity = findCustomFormReleaseConnById(id);
        return CopyUtils.copyBean(entity, CustomFormReleaseConnModelExtend.class);
    }

    @Override
    public PageOb<CustomFormReleaseConnModelExtend> get(CustomFormReleaseConnModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<CustomFormReleaseConn> pageCustomFormReleaseConn = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageCustomFormReleaseConn, CustomFormReleaseConnModelExtend.class);
    }

    @Override
    public List<CustomFormReleaseConnModelExtend> get(CustomFormReleaseConnModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<CustomFormReleaseConn> pageCustomFormReleaseConn = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageCustomFormReleaseConn.getContent(), CustomFormReleaseConnModelExtend.class);
    }

    @Override
    @Transactional
    public void delete(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }
        repository.deleteBatch(ids);
    }

    @Override
    @Transactional
    public void deleteLogical(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }

        repository.deleteBatchLogical(ids);
    }

    /**
     * 通过id查询自定义表单发布对应接口
     *
     * @param id
     * @return
     */
    private CustomFormReleaseConn findCustomFormReleaseConnById(String id) {
        CustomFormReleaseConn entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No CustomFormReleaseConn found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(CustomFormReleaseConnModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getReleaseId())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseConn.releaseId.like("%" + search.getReleaseId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getFormFieldId())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseConn.formFieldId.like("%" + search.getFormFieldId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIsChildField())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseConn.isChildField.eq(search.getIsChildField()));
        }
        if (!StringUtils.isEmpty(search.getChildId())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseConn.childId.like("%" + search.getChildId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIsField())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseConn.isField.eq(search.getIsField()));
        }
        if (!StringUtils.isEmpty(search.getConnId())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseConn.connId.like("%" + search.getConnId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getConnFieldName())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseConn.connFieldName.like("%" + search.getConnFieldName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIsLink())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseConn.isLink.eq(search.getIsLink()));
        }
        if (!StringUtils.isEmpty(search.getLinkName())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseConn.linkName.like("%" + search.getLinkName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseConn.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseConn.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qCustomFormReleaseConn.isDelete.eq(0), qCustomFormReleaseConn.isDelete.isNull()));

        return pre;
    }
}
