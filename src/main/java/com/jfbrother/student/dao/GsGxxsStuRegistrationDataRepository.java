package com.jfbrother.student.dao;


import com.jfbrother.baseserver.dao.BaseRepository;
import com.jfbrother.student.jpa.GsGxxsStuRegistrationData;
import com.jfbrother.student.jpa.GsLibraryBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 *  学生注册数据数据库操作对象
 * @author hujy@jfbrother.com 2022-10-18
 */
@Repository
public interface GsGxxsStuRegistrationDataRepository extends JpaRepository<GsGxxsStuRegistrationData, String>,
        JpaSpecificationExecutor<GsGxxsStuRegistrationData>,
        QuerydslPredicateExecutor<GsGxxsStuRegistrationData>{

}
