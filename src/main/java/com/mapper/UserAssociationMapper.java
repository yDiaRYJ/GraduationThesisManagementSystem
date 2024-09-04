package com.mapper;

import com.entity.User;
import com.entity.UserAssociation;
import com.sun.nio.sctp.Association;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserAssociationMapper {
    @Select("select * from t_user_association")
    List<UserAssociation> getAllUserAssociation();

    @Select("select * from t_user_association where student_id = #{studentId}")
    UserAssociation getUserAssociationByStudentId(String studentId);
    @Select("select * from t_user_association where teacher_id = #{teacherId}")
    List<UserAssociation> getUserAssociationListByTeacherId(String teacherId);
    @Insert("INSERT INTO t_user_association (student_id, teacher_id) VALUES (#{studentId}, #{teacherId})")
    void insertUserAssociation(UserAssociation userAssociation);
}
