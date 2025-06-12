package com.jfbrother.dataCenter.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.dataCenter.jpa.DataErrorCorrection;
import org.springframework.stereotype.Repository;

/**
*  数据纠错补录表数据库操作对象
* @author chenyl@jfbrother.com 2022-08-05
*/
@Repository
public interface DataErrorCorrectionRepository extends BaseRepository<DataErrorCorrection> {

}
