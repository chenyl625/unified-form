package com.jfbrother.customForm.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.customForm.jpa.CustomFormRelease;
import org.springframework.stereotype.Repository;

/**
*  自定义表单发布数据库操作对象
* @author chenyl@jfbrother.com 2022-06-30
*/
@Repository
public interface CustomFormReleaseRepository extends BaseRepository<CustomFormRelease> {

}
