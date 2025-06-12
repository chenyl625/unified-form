package com.jfbrother.customForm.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.customForm.jpa.CustomFormField;
import org.springframework.stereotype.Repository;

/**
*  自定义表单字段数据库操作对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Repository
public interface CustomFormFieldRepository extends BaseRepository<CustomFormField> {

}
