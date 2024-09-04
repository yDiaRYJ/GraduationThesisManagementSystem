package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from t_user")
    List<User> getAllUsers();
    @Select("select * from t_user WHERE user_id = #{userId}")
    User getUserById(String userId);
    @Delete("DELETE FROM t_user WHERE user_id = #{id}")
    void delete(Integer id);
    @Insert("INSERT INTO t_user (user_id, user_name, user_password, user_type) VALUES (#{userId}, #{userName}, #{userPassword}, #{userType})")
    void insertUser(User user);
    @Update("UPDATE t_user SET user_password = #{userPassword} WHERE user_id = #{userId}")
    void updateUser(User user);
}
