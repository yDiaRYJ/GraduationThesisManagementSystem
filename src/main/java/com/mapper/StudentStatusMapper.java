package com.mapper;

import com.entity.StudentStatus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentStatusMapper {
    @Select("select * from t_student_status WHERE student_id = #{userId}")
    StudentStatus getStudentStatusByStudentId(String userId);
    @Insert("INSERT INTO t_student_status (student_id, student_status) " +
            "VALUES (#{studentId}, #{studentStatus})")
    void insertStudentStatus(StudentStatus studentStatus);
    @Update("UPDATE t_student_status SET student_status = #{studentStatus} WHERE student_id = #{studentId}")
    void updateStudentStatus(StudentStatus studentStatus);
}
