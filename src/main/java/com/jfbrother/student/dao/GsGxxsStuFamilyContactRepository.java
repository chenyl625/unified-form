package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsGxxsStuFamilyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 学生家庭联系方式数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-08-26
 */
@Repository
public interface GsGxxsStuFamilyContactRepository extends JpaRepository<GsGxxsStuFamilyContact, String>,
        JpaSpecificationExecutor<GsGxxsStuFamilyContact>,
        QuerydslPredicateExecutor<GsGxxsStuFamilyContact> {

}
