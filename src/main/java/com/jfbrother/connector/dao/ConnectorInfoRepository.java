package com.jfbrother.connector.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.connector.jpa.ConnectorInfo;
import org.springframework.stereotype.Repository;

/**
*  接口管理数据库操作对象
* @author chenyl@jfbrother.com 2022-06-30
*/
@Repository
public interface ConnectorInfoRepository extends BaseRepository<ConnectorInfo> {

}
