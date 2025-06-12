package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsImportHikFaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 海康人脸照片数据数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-08-30
 */
@Repository
public interface GsImportHikFaceInfoRepository extends JpaRepository<GsImportHikFaceInfo, String>,
        JpaSpecificationExecutor<GsImportHikFaceInfo>,
        QuerydslPredicateExecutor<GsImportHikFaceInfo> {

}
