package com.jfbrother.work.service.impl;

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
import com.jfbrother.work.dao.MessageSendingTemplateRepository;
import com.jfbrother.work.jpa.MessageSendingTemplate;
import com.jfbrother.work.jpa.QMessageSendingTemplate;
import com.jfbrother.work.model.MessageSendingTemplateModel;
import com.jfbrother.work.model.param.MessageSendingTemplateModelParam;
import com.jfbrother.work.model.extend.MessageSendingTemplateModelExtend;
import com.jfbrother.work.model.search.MessageSendingTemplateModelSearch;
import com.jfbrother.work.service.MessageSendingTemplateService;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.querydsl.core.BooleanBuilder;


import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;


/**
*  消息发送模版表service实现
* @author chenyl@jfbrother.com 2022-12-15
*/
@Service
public class MessageSendingTemplateServiceImpl extends BaseServiceImpl implements MessageSendingTemplateService {
	@Autowired
	private MessageSendingTemplateRepository repository;

	QMessageSendingTemplate qMessageSendingTemplate = QMessageSendingTemplate.messageSendingTemplate;

	@Override
	@Transactional
	public MessageSendingTemplateModelExtend post(MessageSendingTemplateModelParam model) {
		if (!StringUtils.isEmpty(model.getId())) {
			throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
		}

		MessageSendingTemplate entity = CopyUtils.copyBean(model, MessageSendingTemplate.class);

		entity = repository.save(entity);
		return CopyUtils.copyBean(entity, MessageSendingTemplateModelExtend.class);
	}

	@SuppressWarnings("Duplicates")
	@Override
	@Transactional
	public MessageSendingTemplateModelExtend put(String id, MessageSendingTemplateModelParam model) {
		if (StringUtils.isEmpty(id)) {
			throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
		}

		MessageSendingTemplate entity = findMessageSendingTemplateById(id);
		CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
		entity = repository.save(entity);
		return CopyUtils.copyBean(entity, MessageSendingTemplateModelExtend.class);
	}

	@SuppressWarnings("Duplicates")
	@Override
	@Transactional
	public MessageSendingTemplateModelExtend patch(String id, MessageSendingTemplateModelParam model) {
		if (StringUtils.isEmpty(id)) {
		throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
		}

		MessageSendingTemplate entity = findMessageSendingTemplateById(id);
		CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
		entity = repository.save(entity);
		return CopyUtils.copyBean(entity, MessageSendingTemplateModelExtend.class);
	}

	@Override
	public MessageSendingTemplateModelExtend get(String id) {
		MessageSendingTemplate entity = findMessageSendingTemplateById(id);
		return CopyUtils.copyBean(entity, MessageSendingTemplateModelExtend.class);
	}

	@Override
	public PageOb<MessageSendingTemplateModelExtend> get(MessageSendingTemplateModelSearch search, Pageable pageable) {
		Predicate pre = fillPredicate(search);

		Page<MessageSendingTemplate> pageMessageSendingTemplate = repository.findAll(pre, pageable);

		return CopyUtils.copyPageOb(pageMessageSendingTemplate, MessageSendingTemplateModelExtend.class);
	}

	@Override
	public List<MessageSendingTemplateModelExtend> get(MessageSendingTemplateModelSearch search, Sort sort) {
		Predicate pre = fillPredicate(search);

		Pageable pageable = PageRequest.of(0, max, sort);
		Page<MessageSendingTemplate> pageMessageSendingTemplate = repository.findAll(pre, pageable);

		return CopyUtils.copyList(pageMessageSendingTemplate.getContent(), MessageSendingTemplateModelExtend.class);
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
	 * 通过id查询消息发送模版表
	 * 
	 * @param id
	 * @return
	 */
	private MessageSendingTemplate findMessageSendingTemplateById(String id) {
		MessageSendingTemplate entity = null;
		try {
			entity = repository.findById(id).get();
		} catch (NoSuchElementException e) {
			// throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
			throw new ServiceException(ResultCode.NOT_FOUND, "No MessageSendingTemplate found.");
		}
		return entity;
	}


	/**
	* 将查询条件转化为领域模型的查询对象
	*
	* @param search
	* @return
	*/
	private Predicate fillPredicate(MessageSendingTemplateModelSearch search) {
		Predicate pre = new BooleanBuilder();

				if (!StringUtils.isEmpty(search.getTemplateName())) {
					pre = ExpressionUtils.and(pre, qMessageSendingTemplate.templateName.like("%" + search.getTemplateName() + "%"));
				}
				if (!StringUtils.isEmpty(search.getTemplateContent())) {
					pre = ExpressionUtils.and(pre, qMessageSendingTemplate.templateContent.like("%" + search.getTemplateContent() + "%"));
				}
				if (!StringUtils.isEmpty(search.getMatchingKey())) {
					pre = ExpressionUtils.and(pre, qMessageSendingTemplate.matchingKey.like("%" + search.getMatchingKey() + "%"));
				}
				if (!StringUtils.isEmpty(search.getSendObj())) {
					pre = ExpressionUtils.and(pre, qMessageSendingTemplate.sendObj.like("%" + search.getSendObj() + "%"));
				}
				if (!StringUtils.isEmpty(search.getType())) {
					pre = ExpressionUtils.and(pre, qMessageSendingTemplate.type.eq(search.getType()));
				}
				if (!StringUtils.isEmpty(search.getIsOpen())) {
					pre = ExpressionUtils.and(pre, qMessageSendingTemplate.isOpen.eq(search.getIsOpen()));
				}
				if (!StringUtils.isEmpty(search.getSortNum())) {
					pre = ExpressionUtils.and(pre, qMessageSendingTemplate.sortNum.eq(search.getSortNum()));
				}
				if (!StringUtils.isEmpty(search.getStatusNum())) {
					pre = ExpressionUtils.and(pre, qMessageSendingTemplate.statusNum.eq(search.getStatusNum()));
				}
				pre = ExpressionUtils.and(pre, ExpressionUtils.or(qMessageSendingTemplate.isDelete.eq(0), qMessageSendingTemplate.isDelete.isNull()));

		return pre;
	}
}
