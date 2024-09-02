package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from t_user")
    List<User> getAllUsers();
    @Delete("DELETE FROM t_user WHERE user_id = #{id}")
    void delete(Integer id);
    @Insert("INSERT INTO t_user (id, username, address) VALUES (#{id}, #{username}, #{address})")
    void insertUser(User user);
}
