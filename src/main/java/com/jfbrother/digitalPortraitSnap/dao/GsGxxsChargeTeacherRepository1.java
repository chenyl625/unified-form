package com.jfbrother.digitalPortraitSnap.dao;


import com.jfbrother.student.jpa.GsGxxsChargeTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
*  在校班级的班主任数据库操作对象
* @author xinz 2023-03-22
*/
@Repository
public interface GsGxxsChargeTeacherRepository1 extends JpaRepository<GsGxxsChargeTeacher, String>,
        JpaSpecificationExecutor<GsGxxsChargeTeacher>,
        QuerydslPredicateExecutor<GsGxxsChargeTeacher> {

    /**
     * 根据教师工号，获取教师信息
     * @param code
     * @return
     */
    @Query(value = "select GROUP_CONCAT(t1.bjmc SEPARATOR ',') bjmc,t1.gh,t1.xm,t1.role,sys_users.portrait,sys_department.dept_name deptName,sys_users.sex from (select bjmc,bzrgh gh,bzrxm xm,'班主任' as role from gs_gxxs_charge_teacher union " +
            "select bjmc,fdyh,fdyxm,'辅导员' from gs_gxxs_charge_teacher) t1 left join sys_users " +
            "on t1.gh = sys_users.username " +
            "left join sys_department " +
            "on sys_users.dept_id = sys_department.id " +
            "where t1.gh = ?1 " +
            "group by t1.gh ",nativeQuery = true)
    List<Map<String,Object>> getTeacherInfoByCode(String code);

    /**
     * 获取教师未读留言数
     * @param gh
     * @param role
     * @return
     */
    @Query(value = "select count(1) total " +
            "from gs_stu_leave_msg inner join " +
            "(select xh from gs_gxxs_stu_base_entire " +
            "where FIND_IN_SET(ssbj_id,(" +
            "select GROUP_CONCAT(t1.bjdm SEPARATOR ',') " +
            "from (select bjdm from gs_gxxs_charge_teacher " +
            "where bzrgh = ?1 or fdyh = ?1) t1 ))) t2 " +
            "on gs_stu_leave_msg.about_stu_code = t2.xh " +
            "where gs_stu_leave_msg.delete_flag = '0' " +
            "and (case ?2 when 0 then gs_stu_leave_msg.bzr_read_or_not = '0' when 1 then gs_stu_leave_msg.fdy_read_or_not = '0' end)",nativeQuery = true)
    List<Map<String,Object>> getNotReadLeaveMsgCount(String gh,int role);

    /**
     * 教师，获取各学生留言分页列表
     * @param gh
     * @param role
     * @return
     */
    @Query(value = "select u.portrait,t2.xm,t2.bjmc,t2.xh,m.msg,m.leave_time,m.total " +
            "from (select m1.*,sum(if((case ?2 when 0 then m1.bzr_read_or_not = '0' when 1 then m1.fdy_read_or_not = '0' end),1,0)) as total " +
            "from gs_stu_leave_msg m1 " +
            "inner join (select distinct(id) id from gs_stu_leave_msg where delete_flag = '0' order by leave_time DESC) m2 " +
            "on m2.id = m1.id where m1.delete_flag = '0' group by m1.about_stu_code) m " +
            "inner join (select xh,xm,bjmc " +
            "from gs_gxxs_stu_base_entire " +
            "where FIND_IN_SET(ssbj_id,(select GROUP_CONCAT(t1.bjdm SEPARATOR ',') " +
            "from (select bjdm from gs_gxxs_charge_teacher " +
            "where bzrgh = ?1 or fdyh = ?1) t1 ))) t2 " +
            "on m.about_stu_code = t2.xh " +
            "left join sys_users u " +
            "on m.code = u.username " +
            "order by m.leave_time DESC",nativeQuery = true)
    List<Map<String,Object>> getStuMsgPageList(String gh, int role);

    /**
     * 获取家长未读留言数
     * @param aboutStuCode
     * @return
     */
    @Query(value = "select count(1) total " +
            "from gs_stu_leave_msg " +
            "where delete_flag = '0' " +
            "and parent_read_or_not = '0' " +
            "and about_stu_code = ?1",nativeQuery = true)
    List<Map<String,Object>> getParentNotReadLeaveMsgCount(String aboutStuCode);

}
