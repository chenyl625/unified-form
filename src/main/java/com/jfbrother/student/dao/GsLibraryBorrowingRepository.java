package com.jfbrother.student.dao;


import com.jfbrother.student.jpa.GsLibraryBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
*  在借记录数据库操作对象
* @author chenyl@jfbrother.com 2022-09-02
*/
@Repository
public interface GsLibraryBorrowingRepository extends JpaRepository<GsLibraryBorrowing, String>,
        JpaSpecificationExecutor<GsLibraryBorrowing>,
        QuerydslPredicateExecutor<GsLibraryBorrowing> {

}
