package com.jfbrother.digitalPortraitSnap.dao;


import com.jfbrother.student.jpa.GsGxxsStuBaseEntire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 教务系统里的在校生数据库操作对象
 *
 * @author chenyl@jfbrother.com 2022-08-26
 */
@Repository
public interface GsGxxsStuBaseEntireRepository1 extends JpaRepository<GsGxxsStuBaseEntire, String>,
        JpaSpecificationExecutor<GsGxxsStuBaseEntire>,
        QuerydslPredicateExecutor<GsGxxsStuBaseEntire> {

    //统计个人消费超越全校多少%学生
    @Query(value = "select count(*) as pers from (select t1.xh,ABS(SUM(t2.mondeal)) as money " +
            "from gs_gxxs_stu_base_entire t1 " +
            "LEFT JOIN gs_yktxt_person_consume t2 " +
            "ON t1.xh = t2.code " +
            "WHERE SUBSTRING( t2.dealtime, 1, 4 ) = ?1 " +
            "GROUP BY t1.xh " +
            "HAVING money != 0.00 and money >= (select ABS(SUM(mondeal)) from gs_yktxt_person_consume " +
            "where code = ?2 and SUBSTRING( dealtime, 1, 4 ) = ?1 " +
            "GROUP BY code) ORDER BY money DESC) as tab ",nativeQuery = true)
    List<Map<String,Object>> getConsumMoreThanHowManyStudentsThisYear(String queryYear,String xh);

}
