package com.jfbrother.dataCenter.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.dataCenter.jpa.DataDictionary;
import org.springframework.stereotype.Repository;

/**
*  数据字典表数据库操作对象
* @author chenyl@jfbrother.com 2022-09-01
*/
@Repository
public interface DataDictionaryRepository extends BaseRepository<DataDictionary> {

}
