package com.jfbrother.work.service.impl;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.jfbrother.work.dao.WorkFlowTaskRepository;
import com.jfbrother.work.jpa.QWorkFlowTask;
import com.jfbrother.work.jpa.WorkFlowTask;
import com.jfbrother.work.model.extend.WorkFlowTaskModelExtend;
import com.jfbrother.work.model.param.WorkFlowTaskModelParam;
import com.jfbrother.work.model.search.WorkFlowTaskModelSearch;
import com.jfbrother.work.service.WorkFlowTaskService;
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
import java.util.stream.Collectors;


/**
 * 流程环节实例service实现
 *
 * @author chenyl@jfbrother.com 2022-06-27
 */
@Service
public class WorkFlowTaskServiceImpl extends BaseServiceImpl implements WorkFlowTaskService {
    @Autowired
    private WorkFlowTaskRepository repository;

    QWorkFlowTask qWorkFlowTask = QWorkFlowTask.workFlowTask;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WorkFlowTaskModelExtend post(WorkFlowTaskModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }

        WorkFlowTask entity = CopyUtils.copyBean(model, WorkFlowTask.class);

        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, WorkFlowTaskModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public WorkFlowTaskModelExtend put(String id, WorkFlowTaskModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        WorkFlowTask entity = findWorkFlowTaskById(id);
        CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, WorkFlowTaskModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public WorkFlowTaskModelExtend patch(String id, WorkFlowTaskModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }

        WorkFlowTask entity = findWorkFlowTaskById(id);
        CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
        entity = repository.save(entity);
        return CopyUtils.copyBean(entity, WorkFlowTaskModelExtend.class);
    }

    @Override
    public WorkFlowTaskModelExtend get(String id) {
        WorkFlowTask entity = findWorkFlowTaskById(id);
        return CopyUtils.copyBean(entity, WorkFlowTaskModelExtend.class);
    }

    @Override
    public PageOb<WorkFlowTaskModelExtend> get(WorkFlowTaskModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);

        Page<WorkFlowTask> pageWorkFlowTask = repository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageWorkFlowTask, WorkFlowTaskModelExtend.class);
    }

    @Override
    public List<WorkFlowTaskModelExtend> get(WorkFlowTaskModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<WorkFlowTask> pageWorkFlowTask = repository.findAll(pre, pageable);

        return CopyUtils.copyList(pageWorkFlowTask.getContent(), WorkFlowTaskModelExtend.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }
        repository.deleteBatch(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLogical(List<String> ids) {
        if (ids.size() == 0) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }

        repository.deleteBatchLogical(ids);
    }

    @Override
    public List<WorkFlowTaskModelExtend> getByFillId(String fillId) {
        List<WorkFlowTaskModelExtend> taskList = queryFactory.selectFrom(qWorkFlowTask)
                .where(qWorkFlowTask.fillId.eq(fillId))
                .orderBy(qWorkFlowTask.assignDate.asc().nullsLast(), qWorkFlowTask.orderId.asc()).fetch()
                .stream().map(item -> CopyUtils.copyBean(item, WorkFlowTaskModelExtend.class)).collect(Collectors.toList());

        return taskList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByFillId(String fillId) {
        if(StringUtils.isEmpty(fillId)){
            throw new ServiceException(ResultCode.BAD_REQUEST, "fillIds is required!");
        }
        queryFactory.delete(qWorkFlowTask).where(qWorkFlowTask.fillId.eq(fillId)).execute();
    }

    @Override
    public WorkFlowTaskModelExtend updateStatusByTaskId(int taskId,String status) {
        WorkFlowTask workFlowTask = queryFactory.select(qWorkFlowTask).where(qWorkFlowTask.taskId.eq(taskId))
                .fetchFirst();
        if(!StringUtils.isEmpty(status)){
            workFlowTask.setStatus(status);
        }
        repository.save(workFlowTask);
        return CopyUtils.copyBean(workFlowTask,WorkFlowTaskModelExtend.class);
    }

    /**
     * 通过id查询流程环节实例
     *
     * @param id
     * @return
     */
    private WorkFlowTask findWorkFlowTaskById(String id) {
        WorkFlowTask entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No WorkFlowTask found.");
        }
        return entity;
    }


    /**
     * 将查询条件转化为领域模型的查询对象
     *
     * @param search
     * @return
     */
    private Predicate fillPredicate(WorkFlowTaskModelSearch search) {
        Predicate pre = new BooleanBuilder();

        if (!StringUtils.isEmpty(search.getFillId())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.fillId.like("%" + search.getFillId() + "%"));
        }
        if (!StringUtils.isEmpty(search.getTaskId())) {
        }
        if (!StringUtils.isEmpty(search.getOrderId())) {
        }
        if (!StringUtils.isEmpty(search.getName())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.name.like("%" + search.getName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getTimeLimit())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.timeLimit.like("%" + search.getTimeLimit() + "%"));
        }
        if (!StringUtils.isEmpty(search.getDiffHours())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.diffHours.like("%" + search.getDiffHours() + "%"));
        }
        if (!StringUtils.isEmpty(search.getAssignDate())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.assignDate.eq(search.getAssignDate()));
        }
        if (!StringUtils.isEmpty(search.getEndDate())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.endDate.eq(search.getEndDate()));
        }
        if (!StringUtils.isEmpty(search.getStatus())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.status.like("%" + search.getStatus() + "%"));
        }
        if (!StringUtils.isEmpty(search.getAssigeeName())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.assigeeName.like("%" + search.getAssigeeName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getAssigneeAccount())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.assigneeAccount.eq(search.getAssigneeAccount()));
        }
        if (!StringUtils.isEmpty(search.getIsCurrTask())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.isCurrTask.eq(search.getIsCurrTask()));
        }
        if (!StringUtils.isEmpty(search.getSortNum())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.sortNum.eq(search.getSortNum()));
        }
        if (!StringUtils.isEmpty(search.getStatusNum())) {
            pre = ExpressionUtils.and(pre, qWorkFlowTask.statusNum.eq(search.getStatusNum()));
        }
        pre = ExpressionUtils.and(pre, ExpressionUtils.or(qWorkFlowTask.isDelete.eq(0), qWorkFlowTask.isDelete.isNull()));

        return pre;
    }
}
