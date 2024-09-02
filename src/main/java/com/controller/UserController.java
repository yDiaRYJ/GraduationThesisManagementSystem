package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*") // 设置CORS头部，解决跨域资源共享（CORS）问题
public class UserController {
    //注入用户 Service
    @Autowired
    private UserService userService;
    @RequestMapping("/userList")
    public List<User> getAllUsers(){
        List<User> list = this.userService.getAllUsers();
        System.out.println("OK" + list);
        return list;
    }
    @GetMapping("/delete")
    public void delete(@RequestParam Integer id){
        this.userService.deleteUser(id);
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "test ok!";
    }
}
