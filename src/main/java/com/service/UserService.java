package com.service;

import com.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void deleteUser(Integer id);
    public void createUser(User user);
}
