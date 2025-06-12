package com.jfbrother.customForm.service.impl;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.customForm.dao.CustomFormRuleRepository;
import com.jfbrother.customForm.jpa.CustomFormRule;
import com.jfbrother.customForm.jpa.QCustomFormRule;
import com.jfbrother.customForm.model.extend.CustomFormRuleModelExtend;
import com.jfbrother.customForm.model.param.CustomFormRuleModelParam;
import com.jfbrother.customForm.model.search.CustomFormRuleModelSearch;
import com.jfbrother.customForm.service.CustomFormRuleService;
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
 * 自定义表单规则service实现
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Service
public class CustomFormRuleServiceImpl extends BaseServiceImpl implements CustomFormRuleService {
    @Autowired
    private CustomFormRuleRepository repository;

    QCustomFormRule qCustomFormRule = QCustomFormRule.customFormRule;

    @Override
    @Transactional
    public CustomFormRuleModelExtend post(CustomFormRuleModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        CustomFormRule entity = CopyUtils.copyBean(model, CustomFormRule.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormRuleModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public CustomFormRuleModelExtend put(String id, CustomFormRuleModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormRule entity = findCustomFormRuleById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormRuleModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public CustomFormRuleModelExtend patch(String id, CustomFormRuleModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormRule entity = findCustomFormRuleById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormRuleModelExtend.class);
    }

    @Override
    public CustomFormRuleModelExtend get(String id) {
        CustomFormRule entity = findCustomFormRuleById(id);
        return CopyUtils.copyBean(entity, CustomFormRuleModelExtend.class);
    }

    @Override
    public PageOb<CustomFormRuleModelExtend> get(CustomFormRuleModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<CustomFormRule> pageCustomFormRule = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageCustomFormRule, CustomFormRuleModelExtend.class);
    }

    @Override
    public List<CustomFormRuleModelExtend> get(CustomFormRuleModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<CustomFormRule> pageCustomFormRule = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageCustomFormRule.getContent(), CustomFormRuleModelExtend.class);
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

    @Override
    public CustomFormRuleModelExtend findCustomFormRuleByFormId(String formId) {
        if (StringUtils.isEmpty(formId)) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "formId is required!");
        }
        CustomFormRule entity = repository.findByFormId(formId);
        if (entity != null) {
            return CopyUtils.copyBean(entity, CustomFormRuleModelExtend.class);
        }

        return null;
    }

    /**
     * 通过id查询自定义表单规则
     *
     * @param id
     * @return
     */
    private CustomFormRule findCustomFormRuleById(String id) {
        CustomFormRule entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No CustomFormRule found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(CustomFormRuleModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getFormId())) {
            pre = ExpressionUtils.and(pre, qCustomFormRule.formId.like("%" + search.getFormId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getText())) {
            pre = ExpressionUtils.and(pre, qCustomFormRule.text.like("%" + search.getText() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormRule.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormRule.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qCustomFormRule.isDelete.eq(0), qCustomFormRule.isDelete.isNull()));

        return pre;
    }
}
