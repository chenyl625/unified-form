package com.jfbrother.standard.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.standard.jpa.StandardDataSource;
import org.springframework.stereotype.Repository;

/**
*  数据来源标准库数据库操作对象
* @author chenyl@jfbrother.com 2022-08-04
*/
@Repository
public interface StandardDataSourceRepository extends BaseRepository<StandardDataSource> {

}
