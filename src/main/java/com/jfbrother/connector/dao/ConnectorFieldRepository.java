package com.jfbrother.connector.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.connector.jpa.ConnectorField;
import org.springframework.stereotype.Repository;

/**
*  接口字段数据库操作对象
* @author chenyl@jfbrother.com 2022-07-20
*/
@Repository
public interface ConnectorFieldRepository extends BaseRepository<ConnectorField> {

}
