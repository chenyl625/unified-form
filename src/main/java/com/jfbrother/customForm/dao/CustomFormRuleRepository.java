package com.jfbrother.customForm.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.customForm.jpa.CustomFormRule;
import org.springframework.stereotype.Repository;

/**
 * 自定义表单规则数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-06-10
 */
@Repository
public interface CustomFormRuleRepository extends BaseRepository<CustomFormRule> {

    CustomFormRule findByFormId(String formId);

}
