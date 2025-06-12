package com.jfbrother.customForm.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.customForm.jpa.CustomFormFill;
import org.springframework.stereotype.Repository;

/**
 * 自定义表单填报数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-06-21
 */
@Repository
public interface CustomFormFillRepository extends BaseRepository<CustomFormFill> {

    CustomFormFill findByFlowId(Integer flowId);

}
