package com.service.impl;

import com.entity.User;
import com.mapper.UserMapper;
import com.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    //注入用户 Mapper
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers(){
        return this.userMapper.getAllUsers();
    }
    @Override
    public void deleteUser(Integer id){
        System.out.println("删除了id为 "+id+" 的用户");
        this.userMapper.delete(id);
    }
    @Override
    public void createUser(User user) {
        userMapper.insertUser(user);
    }
}
