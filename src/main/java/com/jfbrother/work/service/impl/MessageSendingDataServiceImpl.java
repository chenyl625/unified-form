package com.jfbrother.work.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfbrother.baseserver.constant.RedisKeyConstant;
import com.jfbrother.baseserver.service.RedisService;
import com.jfbrother.baseserver.service.impl.BaseServiceImpl;
import com.jfbrother.customForm.jpa.QCustomFormRelease;
import com.jfbrother.dataCenter.jpa.GsTeacherBaseInfo;
import com.jfbrother.dataCenter.jpa.QGsTeacherBaseInfo;
import com.jfbrother.dataCenter.model.GsTeacherBaseInfoModel;
import com.jfbrother.work.jpa.MessageSendingTemplate;
import com.jfbrother.work.jpa.QMessageSendingTemplate;
import com.jfbrother.work.model.extend.MessageSendingTemplateModelExtend;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.text.StringSubstitutor;
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
import com.jfbrother.work.dao.MessageSendingDataRepository;
import com.jfbrother.work.jpa.MessageSendingData;
import com.jfbrother.work.jpa.QMessageSendingData;
import com.jfbrother.work.model.MessageSendingDataModel;
import com.jfbrother.work.model.param.MessageSendingDataModelParam;
import com.jfbrother.work.model.extend.MessageSendingDataModelExtend;
import com.jfbrother.work.model.search.MessageSendingDataModelSearch;
import com.jfbrother.work.service.MessageSendingDataService;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.querydsl.core.BooleanBuilder;


import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;


/**
*  消息发送数据管理表service实现
* @author chenyl@jfbrother.com 2022-12-15
*/
@Service
public class MessageSendingDataServiceImpl extends BaseServiceImpl implements MessageSendingDataService {
	@Autowired
	private MessageSendingDataRepository repository;
	@Autowired
	private RedisService redisService;

	QMessageSendingData qMessageSendingData = QMessageSendingData.messageSendingData;
	QMessageSendingTemplate qMessageSendingTemplate = QMessageSendingTemplate.messageSendingTemplate;
	QGsTeacherBaseInfo qGsTeacherBaseInfo = QGsTeacherBaseInfo.gsTeacherBaseInfo;
	QGsTeacherBaseInfo qReciverTeacherBaseInfo = new QGsTeacherBaseInfo("qReciverTeacherBaseInfo");
	@Override
	@Transactional
	public MessageSendingDataModelExtend post(MessageSendingDataModelParam model) {
		if (!StringUtils.isEmpty(model.getId())) {
			throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
		}

		MessageSendingData entity = CopyUtils.copyBean(model, MessageSendingData.class);

		entity = repository.save(entity);
		return CopyUtils.copyBean(entity, MessageSendingDataModelExtend.class);
	}

	@SuppressWarnings("Duplicates")
	@Override
	@Transactional
	public MessageSendingDataModelExtend put(String id, MessageSendingDataModelParam model) {
		if (StringUtils.isEmpty(id)) {
			throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
		}

		MessageSendingData entity = findMessageSendingDataById(id);
		CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
		entity = repository.save(entity);
		return CopyUtils.copyBean(entity, MessageSendingDataModelExtend.class);
	}

	@SuppressWarnings("Duplicates")
	@Override
	@Transactional
	public MessageSendingDataModelExtend patch(String id, MessageSendingDataModelParam model) {
		if (StringUtils.isEmpty(id)) {
		throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
		}

		MessageSendingData entity = findMessageSendingDataById(id);
		CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
		entity = repository.save(entity);
		return CopyUtils.copyBean(entity, MessageSendingDataModelExtend.class);
	}

	@Override
	public MessageSendingDataModelExtend get(String id) {
		MessageSendingData entity = findMessageSendingDataById(id);
		return CopyUtils.copyBean(entity, MessageSendingDataModelExtend.class);
	}

	@Override
	public PageOb<MessageSendingDataModelExtend> get(MessageSendingDataModelSearch search, Pageable pageable) {
		Predicate pre = fillPredicate(search);
		QCustomFormRelease qCustomFormRelease = QCustomFormRelease.customFormRelease;
		JPAQuery<Tuple> jpaQuery = queryFactory.select(qMessageSendingData, qMessageSendingTemplate.templateName, qGsTeacherBaseInfo, qReciverTeacherBaseInfo, qCustomFormRelease.releaseName).from(qMessageSendingData)
				.leftJoin(qMessageSendingTemplate).on(qMessageSendingTemplate.id.eq(qMessageSendingData.belongTemplate))
				.leftJoin(qGsTeacherBaseInfo).on(qGsTeacherBaseInfo.gh.eq(qMessageSendingData.sendUser))
				.leftJoin(qReciverTeacherBaseInfo).on(qReciverTeacherBaseInfo.gh.eq(qMessageSendingData.receiveGh))
				.leftJoin(qCustomFormRelease).on(qCustomFormRelease.id.eq(qMessageSendingData.belongReleaseId));
		QueryResults<Tuple> results = jpaQuery.where(pre).fetchResults();

		return CopyUtils.copyPageObByQueryResults(results,MessageSendingDataModelExtend.class,tuple -> {
			MessageSendingDataModelExtend extend = CopyUtils.copyBean(tuple.get(qMessageSendingData), MessageSendingDataModelExtend.class);
			if(tuple.get(qGsTeacherBaseInfo)!=null){
				extend.setSendTeacher(CopyUtils.copyBean(tuple.get(qGsTeacherBaseInfo), GsTeacherBaseInfoModel.class));
			}
			if(tuple.get(qReciverTeacherBaseInfo)!=null){
				extend.setReciverTeacher(CopyUtils.copyBean(tuple.get(qReciverTeacherBaseInfo), GsTeacherBaseInfoModel.class));
			}
			extend.setFormReleaseName(tuple.get(qCustomFormRelease.releaseName));
			extend.setTemplateName(tuple.get(qMessageSendingTemplate.templateName));
			return extend;
		});
	}

	@Override
	public List<MessageSendingDataModelExtend> get(MessageSendingDataModelSearch search, Sort sort) {
		Predicate pre = fillPredicate(search);

		Pageable pageable = PageRequest.of(0, max, sort);
		Page<MessageSendingData> pageMessageSendingData = repository.findAll(pre, pageable);

		return CopyUtils.copyList(pageMessageSendingData.getContent(), MessageSendingDataModelExtend.class);
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

	@Override
	public void sendMsgByGh(String gh, String matchingKey, Map<String, Object> paramMap) {
		List<MessageSendingTemplate> templateList = queryFactory.select(qMessageSendingTemplate).from(qMessageSendingTemplate)
				.where(qMessageSendingTemplate.isOpen.eq(1).and(qMessageSendingTemplate.matchingKey.eq(matchingKey))).fetch();
		List<GsTeacherBaseInfo> fetch = queryFactory.selectFrom(qGsTeacherBaseInfo).where(qGsTeacherBaseInfo.gh.eq(gh)).fetch();
		if(fetch.size()==0||(fetch.size()>0&&StringUtils.isEmpty(fetch.get(0).getSj()))){
			return;
		}
		for (MessageSendingTemplate template : templateList) {
			MessageSendingDataModelExtend extend = new MessageSendingDataModelExtend();
			String templateContent = template.getTemplateContent();
			StringSubstitutor str = new StringSubstitutor(paramMap);
			String content = str.replace(templateContent);
			extend.setMessageContent(content);
			extend.setReceiveGh(gh);
			extend.setReceivePhone(fetch.get(0).getSj());
			extend.setBelongReleaseId("");
			extend.setBelongTemplate(template.getId());
			//发送短信消息
			extend.setSendUser("admin");
			extend.setSendDateTime(1L);
			extend.setType(template.getType());
			redisService.lPush(RedisKeyConstant.SMS_MESSAGE_TASK, JSONObject.toJSONString(extend));

		}
	}

	/**
	 * 通过id查询消息发送数据管理表
	 * 
	 * @param id
	 * @return
	 */
	private MessageSendingData findMessageSendingDataById(String id) {
		MessageSendingData entity = null;
		try {
			entity = repository.findById(id).get();
		} catch (NoSuchElementException e) {
			// throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
			throw new ServiceException(ResultCode.NOT_FOUND, "No MessageSendingData found.");
		}
		return entity;
	}


	/**
	* 将查询条件转化为领域模型的查询对象
	*
	* @param search
	* @return
	*/
	private Predicate fillPredicate(MessageSendingDataModelSearch search) {
		Predicate pre = new BooleanBuilder();

				if (!StringUtils.isEmpty(search.getSendUser())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.sendUser.like("%" + search.getSendUser() + "%"));
				}
				if (!StringUtils.isEmpty(search.getReceiveGh())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.receiveGh.like("%" + search.getReceiveGh() + "%"));
				}
				if (!StringUtils.isEmpty(search.getReceivePhone())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.receivePhone.like("%" + search.getReceivePhone() + "%"));
				}
				if (!StringUtils.isEmpty(search.getMessageContent())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.messageContent.like("%" + search.getMessageContent() + "%"));
				}
				if (!StringUtils.isEmpty(search.getBelongTemplate())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.belongTemplate.like("%" + search.getBelongTemplate() + "%"));
				}
				if (!StringUtils.isEmpty(search.getBelongReleaseId())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.belongReleaseId.like("%" + search.getBelongReleaseId() + "%"));
				}
				if (!StringUtils.isEmpty(search.getSendStatus())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.sendStatus.eq(search.getSendStatus()));
				}
				if (!StringUtils.isEmpty(search.getSendDateTime())) {
						pre = ExpressionUtils.and(pre, qMessageSendingData.sendDateTime.eq(search.getSendDateTime()));
				}
				if (!StringUtils.isEmpty(search.getWechatUserId())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.wechatUserId.like("%" + search.getWechatUserId() + "%"));
				}
				if (!StringUtils.isEmpty(search.getContent())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.content.like("%" + search.getContent() + "%"));
				}
				if (!StringUtils.isEmpty(search.getType())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.type.eq(search.getType()));
				}
				if (!StringUtils.isEmpty(search.getSortNum())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.sortNum.eq(search.getSortNum()));
				}
				if (!StringUtils.isEmpty(search.getStatusNum())) {
					pre = ExpressionUtils.and(pre, qMessageSendingData.statusNum.eq(search.getStatusNum()));
				}
				pre = ExpressionUtils.and(pre, ExpressionUtils.or(qMessageSendingData.isDelete.eq(0), qMessageSendingData.isDelete.isNull()));

		return pre;
	}
}
