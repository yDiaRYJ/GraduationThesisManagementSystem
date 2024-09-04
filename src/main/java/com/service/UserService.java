package com.service;

import com.entity.User;
import com.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    //注入用户 Mapper
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public List<User> getAllUsers() {
        return this.userMapper.getAllUsers();
    }

    public User getUserById(String userId) {
        return this.userMapper.getUserById(userId);
    }

    public void deleteUser(Integer id){
        System.out.println("删除了id为 "+id+" 的用户");
        this.userMapper.delete(id);
    }

    public void addUser(User user) {
        userMapper.insertUser(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
