package com.controller;

import com.entity.User;
import com.entity.UserAssociation;
import com.service.UserAssociationService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*") // 设置CORS头部，解决跨域资源共享（CORS）问题
public class TeacherController {
    //注入用户 Service
    @Autowired
    private UserService userService;
    @Autowired
    private UserAssociationService userAssociationService;

    @PostMapping("/teacherLogin")
    public ResponseEntity<Map<String, Object>> loginTeacher(
            @RequestParam String userId,
            @RequestParam String password) {

        Map<String, Object> response = new HashMap<>();

        User queryUser = userService.getUserById(userId);
        if (queryUser == null) {
            response.put("success", false);
            response.put("message", "user not exists!");
        } else {
            if (queryUser.getUserType() != 1) {
                response.put("success", false);
                response.put("message", "user is not a teacher!");
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

    @PostMapping("/getStudents")
    public ResponseEntity<Map<String, Object>> getStudents(@RequestParam String userId) {
        Map<String, Object> response = new HashMap<>();

        List<UserAssociation> userAssociationList = this.userAssociationService.getUserAssociationListByTeacherId(userId);
        List<User> userList = new ArrayList<>();

        for (UserAssociation userAssociation: userAssociationList) {
            userList.add(this.userService.getUserById(userAssociation.getStudentId()));
        }


        if (!userList.isEmpty()) {
            response.put("success", true);
            response.put("message", userList);
        } else {
            response.put("success", false);
            response.put("message", null);
        }

        return ResponseEntity.ok(response);
    }
}
