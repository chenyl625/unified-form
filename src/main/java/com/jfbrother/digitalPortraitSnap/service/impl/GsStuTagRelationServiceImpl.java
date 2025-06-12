package com.jfbrother.digitalPortraitSnap.service.impl;


import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.digitalPortraitSnap.dao.GsStuTagRelationRepository;
import com.jfbrother.digitalPortraitSnap.jpa.GsStuTagRelation;
import com.jfbrother.digitalPortraitSnap.jpa.QGsStuTagRelation;
import com.jfbrother.digitalPortraitSnap.model.param.GsStuTagRelationModelParam;
import com.jfbrother.digitalPortraitSnap.model.extend.GsStuTagRelationModelExtend;
import com.jfbrother.digitalPortraitSnap.service.GsStuTagRelationService;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.StringUtils;

import java.util.Map;


/**
 *  学生标签关系表service实现
 * @author xinz 2023-03-28
 */
@Service
public class GsStuTagRelationServiceImpl extends BaseServiceImpl implements GsStuTagRelationService {
    @Autowired
    private GsStuTagRelationRepository gsStuTagRelationRepository;

    QGsStuTagRelation qGsStuTagRelation = QGsStuTagRelation.gsStuTagRelation;

    @Override
    @Transactional
    public GsStuTagRelationModelExtend post(GsStuTagRelationModelParam model) {
        if (!StringUtils.isEmpty(model.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }
        if (StringUtils.isEmpty(model.getDeleteFlag())) {
            model.setDeleteFlag("0");
        }
        GsStuTagRelation entity = CopyUtils.copyBean(model, GsStuTagRelation.class);
        entity = gsStuTagRelationRepository.save(entity);
        return CopyUtils.copyBean(entity, GsStuTagRelationModelExtend.class);
    }

    @Override
    @Transactional
    public void deleteStuTag(Map<String, Object> body) {
        if (StringUtils.isEmpty(body.get("xh"))) {
            throw new ServiceException(ResultCode.FORBIDDEN, "学号参数缺失.");
        }
        if (StringUtils.isEmpty(body.get("tagId"))) {
            throw new ServiceException(ResultCode.FORBIDDEN, "标签id参数缺失.");
        }
        queryFactory.delete(qGsStuTagRelation)
                .where(qGsStuTagRelation.stuCode.eq(body.get("xh").toString())
                        .and(qGsStuTagRelation.tagId.eq(body.get("tagId").toString()))
                ).execute();
    }

}
