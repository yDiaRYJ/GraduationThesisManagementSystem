package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*") // 设置CORS头部，解决跨域资源共享（CORS）问题
public class AdminController {
    //注入用户 Service
    @Autowired
    private UserService userService;
    @PostMapping("/studentRegister")
    public String registerStudent(@RequestParam String userId, @RequestParam String username, @RequestParam String password){
        User user = new User(userId, username, password, 2);
        userService.addUser(user);
        return "register successful!";
    }
}
