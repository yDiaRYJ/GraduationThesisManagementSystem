package com.controller;

import com.entity.User;
import com.entity.UserAssociation;
import com.service.TopicService;
import com.service.UserAssociationService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*") // 设置CORS头部，解决跨域资源共享（CORS）问题
public class StudentController {
    //注入用户 Service
    @Autowired
    private UserService userService;
    @Autowired
    private UserAssociationService userAssociationService;
    @Autowired
    private TopicService topicService;

    @PostMapping("/studentLogin")
    public ResponseEntity<Map<String, Object>> loginStudent(
            @RequestParam String userId,
            @RequestParam String password) {

        Map<String, Object> response = new HashMap<>();

        User queryUser = userService.getUserById(userId);
        if (queryUser == null) {
            response.put("success", false);
            response.put("message", "user not exists!");
        } else {
            if (queryUser.getUserType() != 2) {
                response.put("success", false);
                response.put("message", "user is not a student!");
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

    @PostMapping("/getTeacher")
    public ResponseEntity<Map<String, Object>> getTeacher(@RequestParam String userId) {
        Map<String, Object> response = new HashMap<>();

        UserAssociation userAssociation = this.userAssociationService.getUserAssociationByStudentId(userId);
        if (userAssociation == null) {
            response.put("success", false);
            response.put("message", null);
        } else {
            User user = this.userService.getUserById(userAssociation.getTeacherId());

            if (user != null) {
                response.put("success", true);
                response.put("message", user);
            } else {
                response.put("success", false);
                response.put("message", null);
            }
        }

        return ResponseEntity.ok(response);
    }
}
