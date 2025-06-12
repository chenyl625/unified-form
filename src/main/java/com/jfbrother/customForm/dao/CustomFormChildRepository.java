package com.jfbrother.customForm.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.customForm.jpa.CustomFormChild;
import org.springframework.stereotype.Repository;

/**
*  自定义表单子表数据库操作对象
* @author chenyl@jfbrother.com 2022-06-10
*/
@Repository
public interface CustomFormChildRepository extends BaseRepository<CustomFormChild> {

}
