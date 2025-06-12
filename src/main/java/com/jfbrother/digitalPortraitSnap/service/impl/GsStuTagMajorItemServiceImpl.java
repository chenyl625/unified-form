package com.jfbrother.digitalPortraitSnap.service.impl;

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
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.digitalPortraitSnap.dao.GsStuTagMajorItemRepository;
import com.jfbrother.digitalPortraitSnap.jpa.GsStuTagMajorItem;
import com.jfbrother.digitalPortraitSnap.jpa.QGsStuTagMajorItem;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuTagMajorItemModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GsStuTagMajorItemModelExtend;
import com.jfbrother.digitalPortraitSnap.model.search.GsStuTagMajorItemModelSearch;
import com.jfbrother.digitalPortraitSnap.service.GsStuTagMajorItemService;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;


/**
 *  学生标签大类service实现
 * @author xinz 2023-03-27
 */
@Service
public class GsStuTagMajorItemServiceImpl extends BaseServiceImpl implements GsStuTagMajorItemService {
    @Autowired
    private GsStuTagMajorItemRepository gsStuTagMajorItemRepository;

    QGsStuTagMajorItem qGsStuTagMajorItem = QGsStuTagMajorItem.gsStuTagMajorItem;

    @Override
    @Transactional
    public GsStuTagMajorItemModelExtend post(GsStuTagMajorItemModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }
        if (StringUtils.isEmpty(model.getDeleteFlag())) {
            model.setDeleteFlag("0");
        }
        GsStuTagMajorItem entity = CopyUtils.copyBean(model, GsStuTagMajorItem.class);
        entity = gsStuTagMajorItemRepository.save(entity);
        return CopyUtils.copyBean(entity, GsStuTagMajorItemModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public GsStuTagMajorItemModelExtend put(String id, GsStuTagMajorItemModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }
        GsStuTagMajorItem entity = findGsStuTagMajorItemById(id);
        //自定义忽略字段
        String[] strings = {"id","deleteFlag","createId","createTime"};
        CopyUtils.copyBean(model, entity, strings);
        entity = gsStuTagMajorItemRepository.save(entity);
        return CopyUtils.copyBean(entity, GsStuTagMajorItemModelExtend.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public GsStuTagMajorItemModelExtend patch(String id, GsStuTagMajorItemModelParam model) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }
        GsStuTagMajorItem entity = findGsStuTagMajorItemById(id);
        //自定义忽略字段,防止前端未传入updateTime,导致 updateTime不更新
        if(model.getUpdateId() == null){
            model.setUpdateId("");
        }
        if(model.getUpdateTime() == null){
            model.setUpdateTime("");
        }
        CopyUtils.copyBean(model, entity, CopyUtils.getNullPropertyNames(model));
        entity = gsStuTagMajorItemRepository.save(entity);
        return CopyUtils.copyBean(entity, GsStuTagMajorItemModelExtend.class);
    }

    @Override
    public GsStuTagMajorItemModelExtend get(String id) {
        GsStuTagMajorItem entity = findGsStuTagMajorItemById(id);
        return CopyUtils.copyBean(entity, GsStuTagMajorItemModelExtend.class);
    }

    @Override
    public PageOb<GsStuTagMajorItemModelExtend> get(GsStuTagMajorItemModelSearch search, Pageable pageable) {
        Predicate pre = fillPredicate(search);
        Page<GsStuTagMajorItem> pageGsStuTagMajorItem = gsStuTagMajorItemRepository.findAll(pre, pageable);
        return CopyUtils.copyPageOb(pageGsStuTagMajorItem, GsStuTagMajorItemModelExtend.class);
    }

    @Override
    public List<GsStuTagMajorItemModelExtend> get(GsStuTagMajorItemModelSearch search, Sort sort) {
        Predicate pre = fillPredicate(search);
        Pageable pageable = PageRequest.of(0, max, sort);
        Page<GsStuTagMajorItem> pageGsStuTagMajorItem = gsStuTagMajorItemRepository.findAll(pre, pageable);
        return CopyUtils.copyList(pageGsStuTagMajorItem.getContent(), GsStuTagMajorItemModelExtend.class);
    }

    @Override
    @Transactional
    public void delete(List<String> ids) {
        if(ids.size() == 0){
            throw new ServiceException(ResultCode.BAD_REQUEST,"ids is required!");
        }
        gsStuTagMajorItemRepository.deleteBatch(ids);
    }
    @Override
    @Transactional
    public void deleteLogical(List<String> ids) {
        if(ids.size() == 0){
            throw new ServiceException(ResultCode.BAD_REQUEST,"ids is required!");
        }
        gsStuTagMajorItemRepository.deleteBatchLogical(ids);
    }

    /**
     * 通过id查询学生标签大类
     * @param id
     * @return
     */
    private GsStuTagMajorItem findGsStuTagMajorItemById(String id) {
        GsStuTagMajorItem entity = null;
        try {
            entity = gsStuTagMajorItemRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No GsStuTagMajorItem found.");
        }
        return entity;
    }

    /**
     * 将查询条件转化为领域模型的查询对象
     * @param search
     * @return
     */
    private Predicate fillPredicate(GsStuTagMajorItemModelSearch search) {
        Predicate pre = new BooleanBuilder();
        if (StringUtils.isEmpty(search.getDeleteFlag())) {
            pre = ExpressionUtils.and(pre, qGsStuTagMajorItem.deleteFlag.eq("0"));
        }
        if (!StringUtils.isEmpty(search.getId())) {
            pre = ExpressionUtils.and(pre, qGsStuTagMajorItem.id.eq(search.getId()));
        }
        if (!StringUtils.isEmpty(search.getTagMajorItem())) {
            pre = ExpressionUtils.and(pre, qGsStuTagMajorItem.tagMajorItem.like("%" + search.getTagMajorItem() + "%"));
        }
        if (!StringUtils.isEmpty(search.getIds())) {
            pre = ExpressionUtils.and(pre, qGsStuTagMajorItem.id.in(spilt(search.getIds())));
        }
        return pre;
    }

    //字符串切割加单引号
    private static String spilt(String str) {
        StringBuffer sb = new StringBuffer();
        String[] temp = str.split(",");
        for (int i = 0; i < temp.length; i++) {
            if (!"".equals(temp[i]) && temp[i] != null)
                sb.append("'" + temp[i] + "',");
        }
        String result = sb.toString();
        String tp = result.substring(result.length() - 1);
        if (",".equals(tp))
            return result.substring(0, result.length() - 1);
        else
            return result;
    }
}
