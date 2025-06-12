package com.jfbrother.digitalPortraitSnap.dao;


import com.jfbrother.student.jpa.GsGxxsStuCourseScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
*  学生课程成绩表数据库操作对象
* @author chenyl@jfbrother.com 2022-08-29
*/
@Repository
public interface GsGxxsStuCourseScoreRepository1 extends JpaRepository<GsGxxsStuCourseScore, String>,
        JpaSpecificationExecutor<GsGxxsStuCourseScore>,
        QuerydslPredicateExecutor<GsGxxsStuCourseScore> {

    @Query(value = "select t1.xh,sum(t2.jd) jd from gs_gxxs_stu_base_entire t1 " +
            "left join gs_gxxs_stu_course_score t2 on t1.xh = t2.xh " +
            "where t1.ssbj_id = ?1 and t2.xn = ?2 and t2.xq = ?3 " +
            "group by t1.xh " +
            "HAVING jd >= (select sum(jd) from gs_gxxs_stu_course_score " +
            "where xh = ?4 and xn = ?2 and xq = ?3) " +
            "order by jd DESC ",nativeQuery = true)
    List<Map<String,Object>> getIntraClassRanking(String ssbjId,String xn,String xq,String xh);

    /**
     * 获取单科在班内排名
     * @param xh
     * @param bjdm
     * @param xn
     * @param xq
     * @return
     */
    @Query(value = "select t1.kch,t1.ran,t2.total " +
            "from (select ran,kch from (select xh,kch,RANK() over (partition by kch ORDER BY kscj_num DESC ) AS ran from gs_gxxs_stu_course_score " +
            "where xn = ?3 and xq = ?4 and bjdm = ?2) v " +
            "where xh = ?1) t1 " +
            "left join (select kch,count(1) as total from gs_gxxs_stu_course_score " +
            "where xn = ?3 and xq = ?4 and bjdm = ?2 GROUP BY kch) t2 " +
            "on t1.kch = t2.kch",nativeQuery = true)
    List<Map<String,Object>> getIntraClassRankingBySubject(String xh,String bjdm,String xn,String xq);

}
