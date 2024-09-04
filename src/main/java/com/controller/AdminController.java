package com.controller;

import com.entity.User;
import com.entity.UserAssociation;
import com.service.UserAssociationService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*") // 设置CORS头部，解决跨域资源共享（CORS）问题
public class AdminController {
    //注入用户 Service
    @Autowired
    private UserService userService;
    @Autowired
    private UserAssociationService userAssociationService;

    @PostMapping("/adminLogin")
    public ResponseEntity<Map<String, Object>> loginAdmin(
            @RequestParam String userId,
            @RequestParam String password) {

        Map<String, Object> response = new HashMap<>();

        User queryUser = userService.getUserById(userId);
        if (queryUser == null) {
            response.put("success", false);
            response.put("message", "user not exists!");
        } else {
            if (queryUser.getUserType() != 0) {
                response.put("success", false);
                response.put("message", "user is not a admin!");
            } else {
                if (queryUser.getUserPassword().equals(password)) {
                    response.put("success", true);
                    response.put("message", queryUser);
                } else {
                    response.put("success", false);
                    response.put("message", "wrong password");
                }
            }
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/adminRegister")
    public ResponseEntity<Map<String, Object>> registerAdmin(
            @RequestParam String userId,
            @RequestParam String username,
            @RequestParam String password) {

        Map<String, Object> response = new HashMap<>();

        User queryUser = userService.getUserById(userId);
        if (queryUser == null) {
            User user = new User(userId, username, password, 0);
            userService.addUser(user);
            response.put("success", true);
            response.put("message", user);
        } else {
            response.put("success", false);
            response.put("message", "user already exists!");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/teacherRegister")
    public ResponseEntity<Map<String, Object>> registerTeacher(
            @RequestParam String userId,
            @RequestParam String username,
            @RequestParam String password) {

        Map<String, Object> response = new HashMap<>();

        User queryUser = userService.getUserById(userId);
        if (queryUser == null) {
            User user = new User(userId, username, password, 1);
            userService.addUser(user);
            response.put("success", true);
            response.put("message", user);
        } else {
            response.put("success", false);
            response.put("message", "user already exists!");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/studentRegister")
    public ResponseEntity<Map<String, Object>> registerStudent(
            @RequestParam String userId,
            @RequestParam String username,
            @RequestParam String password) {

        Map<String, Object> response = new HashMap<>();

        User queryUser = userService.getUserById(userId);
        if (queryUser == null) {
            User user = new User(userId, username, password, 2);
            userService.addUser(user);
            response.put("success", true);
            response.put("message", user);
        } else {
            response.put("success", false);
            response.put("message", "user already exists!");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/addUserAssociation")
    public ResponseEntity<Map<String, Object>> addUserAssociation (@RequestParam String studentId,
                                                                  @RequestParam String teacherId) {
        Map<String, Object> response = new HashMap<>();

        UserAssociation queryUserAssociation = userAssociationService.getUserAssociationByStudentId(studentId);
        if (queryUserAssociation == null) {
            UserAssociation userAssociation = new UserAssociation(studentId, teacherId);
            userAssociationService.addUserAssociation(userAssociation);
            response.put("success", true);
            response.put("message", "add userAssociation successful!");
        } else {
            response.put("success", false);
            response.put("message", "student already has a teacher!");
        }

        return ResponseEntity.ok(response);
    }
}
