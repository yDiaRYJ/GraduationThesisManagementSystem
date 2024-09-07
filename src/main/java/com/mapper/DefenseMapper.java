package com.mapper;

import com.entity.Defense;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DefenseMapper {
    @Select("select * from t_defense WHERE student_id = #{studentId}")
    Defense getDefenseByStudentId(@Param("studentId") String studentId);
    @Insert("INSERT INTO t_defense (student_id, defense_status) " +
            "VALUES (#{studentId}, #{defenseStatus})")
    void insertDefense(Defense defense);
    @Update("UPDATE t_defense SET defense_status = #{defenseStatus} " +
            "WHERE student_id = #{studentId}")
    void updateDefenseStatus(Defense defense);
    @Update("UPDATE t_defense SET defense_startTime = #{defenseStartTime}, defense_endTime = #{defenseEndTime}, " +
            "defense_status = #{defenseStatus} " +
            "WHERE student_id = #{studentId}")
    void arrangeDefense(Defense defense);

    @Update("UPDATE t_defense SET defense_score = #{defenseScore} " +
            "WHERE student_id = #{studentId}")
    void scoreDefense(Defense defense);
}
