package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/changePassword")
    public ResponseEntity<Map<String, Object>> changePassword(
            @RequestParam String userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        Map<String, Object> response = new HashMap<>();

        User queryUser = userService.getUserById(userId);
        if (oldPassword.equals(queryUser.getUserPassword())) {
            queryUser.setUserPassword(newPassword);
            userService.updateUser(queryUser);
            response.put("success", true);
            response.put("message", queryUser);
        } else {
            response.put("success", false);
            response.put("message", "wrong password!");
        }

        return ResponseEntity.ok(response);
    }
}
