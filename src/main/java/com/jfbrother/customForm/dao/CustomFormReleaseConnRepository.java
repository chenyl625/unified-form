package com.jfbrother.customForm.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.customForm.jpa.CustomFormReleaseConn;
import org.springframework.stereotype.Repository;

/**
*  自定义表单发布对应接口数据库操作对象
* @author chenyl@jfbrother.com 2022-06-30
*/
@Repository
public interface CustomFormReleaseConnRepository extends BaseRepository<CustomFormReleaseConn> {

}
