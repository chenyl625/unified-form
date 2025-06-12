package com.jfbrother.digitalPortraitSnap.dao;


import com.jfbrother.digitalPortraitSnap.jpa.GsDataUseReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 *  数据使用参照,快照系统取数依据数据库操作对象
 * @author xinz 2023-03-30
 */
@Repository
public interface GsDataUseReferenceRepository extends JpaRepository<GsDataUseReference, String>,
        JpaSpecificationExecutor<GsDataUseReference>,
        QuerydslPredicateExecutor<GsDataUseReference> {
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
     * 获取数据使用模板
     * @param stuCode
     * @return
     */
    @Query(value = "select * from gs_data_use_reference " +
            "where delete_flag = '0' " +
            "and status = '1' " +
            "and bjdm like concat('%',(select ssbj_id from gs_gxxs_stu_base_entire " +
            "where xh = ?1),'%')",nativeQuery = true)
    List<Map<String,Object>> getDataUseTemplate(String stuCode);
}
