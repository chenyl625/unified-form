package com.jfbrother.standard.service.impl;

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
import com.jfbrother.standard.dao.StandardDataThemeRepository;
import com.jfbrother.standard.jpa.StandardDataTheme;
import com.jfbrother.standard.jpa.QStandardDataTheme;
import com.jfbrother.standard.model.StandardDataThemeModel;
import com.jfbrother.standard.model.param.StandardDataThemeModelParam;
import com.jfbrother.standard.model.extend.StandardDataThemeModelExtend;
import com.jfbrother.standard.model.search.StandardDataThemeModelSearch;
import com.jfbrother.standard.service.StandardDataThemeService;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.querydsl.core.BooleanBuilder;


import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;


/**
*  数据开放主题管理service实现
* @author chenyl@jfbrother.com 2022-09-16
*/
@Service
public class StandardDataThemeServiceImpl extends BaseServiceImpl implements StandardDataThemeService {
	@Autowired
	private StandardDataThemeRepository repository;

	QStandardDataTheme qStandardDataTheme = QStandardDataTheme.standardDataTheme;

	@Override
	@Transactional
	public StandardDataThemeModelExtend post(StandardDataThemeModelParam model) {
		if (!StringUtils.isEmpty(model.getId())) {
			throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
		}

		StandardDataTheme entity = CopyUtils.copyBean(model, StandardDataTheme.class);

		entity = repository.save(entity);
		return CopyUtils.copyBean(entity, StandardDataThemeModelExtend.class);
	}

	@SuppressWarnings("Duplicates")
	@Override
	@Transactional
	public StandardDataThemeModelExtend put(String id, StandardDataThemeModelParam model) {
		if (StringUtils.isEmpty(id)) {
			throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
		}

		StandardDataTheme entity = findStandardDataThemeById(id);
		CopyUtils.copyBean(model, entity, Constant.UPDATE_IGNORE_FIELDS);
		entity = repository.save(entity);
		return CopyUtils.copyBean(entity, StandardDataThemeModelExtend.class);
	}

	@SuppressWarnings("Duplicates")
	@Override
	@Transactional
	public StandardDataThemeModelExtend patch(String id, StandardDataThemeModelParam model) {
		if (StringUtils.isEmpty(id)) {
		throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
		}

		StandardDataTheme entity = findStandardDataThemeById(id);
		CopyUtils.copyBean(model, entity, ListUtils.concat(CopyUtils.getNullPropertyNames(model), Constant.UPDATE_IGNORE_FIELDS));
		entity = repository.save(entity);
		return CopyUtils.copyBean(entity, StandardDataThemeModelExtend.class);
	}

	@Override
	public StandardDataThemeModelExtend get(String id) {
		StandardDataTheme entity = findStandardDataThemeById(id);
		return CopyUtils.copyBean(entity, StandardDataThemeModelExtend.class);
	}

	@Override
	public PageOb<StandardDataThemeModelExtend> get(StandardDataThemeModelSearch search, Pageable pageable) {
		Predicate pre = fillPredicate(search);

		Page<StandardDataTheme> pageStandardDataTheme = repository.findAll(pre, pageable);

		return CopyUtils.copyPageOb(pageStandardDataTheme, StandardDataThemeModelExtend.class);
	}

	@Override
	public List<StandardDataThemeModelExtend> get(StandardDataThemeModelSearch search, Sort sort) {
		Predicate pre = fillPredicate(search);

		Pageable pageable = PageRequest.of(0, max, sort);
		Page<StandardDataTheme> pageStandardDataTheme = repository.findAll(pre, pageable);

		return CopyUtils.copyList(pageStandardDataTheme.getContent(), StandardDataThemeModelExtend.class);
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
	 * 通过id查询数据开放主题管理
	 * 
	 * @param id
	 * @return
	 */
	private StandardDataTheme findStandardDataThemeById(String id) {
		StandardDataTheme entity = null;
		try {
			entity = repository.findById(id).get();
		} catch (NoSuchElementException e) {
			// throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
			throw new ServiceException(ResultCode.NOT_FOUND, "No StandardDataTheme found.");
		}
		return entity;
	}


	/**
	* 将查询条件转化为领域模型的查询对象
	*
	* @param search
	* @return
	*/
	private Predicate fillPredicate(StandardDataThemeModelSearch search) {
		Predicate pre = new BooleanBuilder();

				if (!StringUtils.isEmpty(search.getThemeName())) {
					pre = ExpressionUtils.and(pre, qStandardDataTheme.themeName.like("%" + search.getThemeName() + "%"));
				}
				if (!StringUtils.isEmpty(search.getSortNum())) {
					pre = ExpressionUtils.and(pre, qStandardDataTheme.sortNum.eq(search.getSortNum()));
				}
				if (!StringUtils.isEmpty(search.getStatusNum())) {
					pre = ExpressionUtils.and(pre, qStandardDataTheme.statusNum.eq(search.getStatusNum()));
				}
				pre = ExpressionUtils.and(pre, ExpressionUtils.or(qStandardDataTheme.isDelete.eq(0), qStandardDataTheme.isDelete.isNull()));

		return pre;
	}
}
