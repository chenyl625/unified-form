package com.jfbrother.digitalPortraitSnap.dao;


import com.jfbrother.digitalPortraitSnap.jpa.GsStuTagLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  学生标签库数据库操作对象
 * @author xinz 2023-03-27
 */
@Repository
public interface GsStuTagLibraryRepository extends JpaRepository<GsStuTagLibrary, String>,
        JpaSpecificationExecutor<GsStuTagLibrary>,
        QuerydslPredicateExecutor<GsStuTagLibrary> {
    /**
     * @param ids
     * @function 根据主键id列表删除数据
     */
    @Modifying
    @Query("delete from #{#entityName} s where s.id in (?1)")
    void deleteBatch(List<String> ids);

    /**
     * @param ids
     * @function 根据主键id列表逻辑删除数据
     */
    @Modifying
    @Query("update #{#entityName} s set s.deleteFlag = '1' where s.id in (?1)")
    void deleteBatchLogical(List<String> ids);

    /**
     * 学生标签,分页获取
     * @param keyWords
     * @param tagId
     * @return
     */
    @Query(value = "select e.xh,e.xm,e.bjmc,b.yxmc,GROUP_CONCAT(l.tag_name SEPARATOR ',') bqmc " +
            "from gs_stu_tag_relation r " +
            "inner join (select * from gs_stu_tag_library where delete_flag = '0') l " +
            "on r.tag_id = l.id " +
            "inner join (select * from gs_stu_tag_major_item where delete_flag = '0') i " +
            "on l.tag_major_item_code = i.id " +
            "left join gs_gxxs_stu_base_entire e " +
            "on r.stu_code = e.xh " +
            "left join gs_gxxs_institution_base_info b " +
            "on e.yx_id = b.id " +
            "where 1=1 and " +
            "case ?1 when 'null' then 1=1 else (e.xh like ?1 or e.xm like ?1) end " +
            "GROUP BY e.xh " +
            "HAVING e.xh is not null and " +
            "case ?2 when 'null' then 1=1 else FIND_IN_SET(?2,GROUP_CONCAT(l.id SEPARATOR ',')) end " +
            "order by r.create_time DESC",nativeQuery = true)
    List<Map<String,Object>> getStuTagListPage(String keyWords,String tagId);

    /**
     * 大类统计
     * @return
     */
    @Query(value = "select l.tag_major_item_code id,l.tag_major_item name,count(1) as value " +
            "from gs_stu_tag_library l " +
            "inner join gs_stu_tag_relation r " +
            "on l.id = r.tag_id " +
            "where r.delete_flag = '0' and l.delete_flag = '0' " +
            "group by l.tag_major_item_code " +
            "order by l.tag_major_item_code",nativeQuery = true)
    List<Map<String,Object>> getTagMajorItemStatistics();

    /**
     * 标签男女统计
     * @return
     */
    @Query(value = "select l.id,l.tag_name name,l.tag_major_item_code dlcode,case e.xb when '1' then '男' when '2' then '女' end sex," +
            "count(1) value " +
            "from gs_stu_tag_library l " +
            "inner join gs_stu_tag_relation r on l.id = r.tag_id " +
            "inner join gs_gxxs_stu_base_entire e on r.stu_code = e.xh " +
            "where l.delete_flag = '0' and r.delete_flag = '0' " +
            "group by l.id,e.xb " +
            "order by l.id",nativeQuery = true)
    List<Map<String,Object>> getTagStatisticsBySex();

}
