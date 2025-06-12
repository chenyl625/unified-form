package com.jfbrother.customForm.service.impl;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.customForm.dao.CustomFormReleaseTaskRepository;
import com.jfbrother.customForm.jpa.CustomFormReleaseTask;
import com.jfbrother.customForm.jpa.QCustomFormReleaseTask;
import com.jfbrother.customForm.model.extend.CustomFormReleaseTaskModelExtend;
import com.jfbrother.customForm.model.param.CustomFormReleaseTaskModelParam;
import com.jfbrother.customForm.model.search.CustomFormReleaseTaskModelSearch;
import com.jfbrother.customForm.service.CustomFormReleaseTaskService;
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
 * 自定义表单发布环节service实现
 *
 * @author chenyl@jfbrother.com 2022-07-04
 */
@Service
public class CustomFormReleaseTaskServiceImpl extends BaseServiceImpl implements CustomFormReleaseTaskService {
    @Autowired
    private CustomFormReleaseTaskRepository repository;

    QCustomFormReleaseTask qCustomFormReleaseTask = QCustomFormReleaseTask.customFormReleaseTask;

    @Override
    @Transactional
    public CustomFormReleaseTaskModelExtend post(CustomFormReleaseTaskModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        CustomFormReleaseTask entity = CopyUtils.copyBean(model, CustomFormReleaseTask.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormReleaseTaskModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public CustomFormReleaseTaskModelExtend put(String id, CustomFormReleaseTaskModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormReleaseTask entity = findCustomFormReleaseTaskById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormReleaseTaskModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public CustomFormReleaseTaskModelExtend patch(String id, CustomFormReleaseTaskModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        CustomFormReleaseTask entity = findCustomFormReleaseTaskById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, CustomFormReleaseTaskModelExtend.class);
    }

    @Override
    public CustomFormReleaseTaskModelExtend get(String id) {
        CustomFormReleaseTask entity = findCustomFormReleaseTaskById(id);
        return CopyUtils.copyBean(entity, CustomFormReleaseTaskModelExtend.class);
    }

    @Override
    public PageOb<CustomFormReleaseTaskModelExtend> get(CustomFormReleaseTaskModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<CustomFormReleaseTask> pageCustomFormReleaseTask = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageCustomFormReleaseTask, CustomFormReleaseTaskModelExtend.class);
    }

    @Override
    public List<CustomFormReleaseTaskModelExtend> get(CustomFormReleaseTaskModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<CustomFormReleaseTask> pageCustomFormReleaseTask = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageCustomFormReleaseTask.getContent(), CustomFormReleaseTaskModelExtend.class);
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
     * 通过id查询自定义表单发布环节
     *
     * @param id
     * @return
     */
    private CustomFormReleaseTask findCustomFormReleaseTaskById(String id) {
        CustomFormReleaseTask entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No CustomFormReleaseTask found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(CustomFormReleaseTaskModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getReleaseId())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseTask.releaseId.like("%" + search.getReleaseId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getTaskIdentify())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseTask.taskIdentify.like("%" + search.getTaskIdentify() + "%"));
        }
        if (!StringUtils.isEmpty(search.getTaskIdentifyName())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseTask.taskIdentifyName.like("%" + search.getTaskIdentifyName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseTask.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qCustomFormReleaseTask.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qCustomFormReleaseTask.isDelete.eq(0), qCustomFormReleaseTask.isDelete.isNull()));

        return pre;
    }
}
