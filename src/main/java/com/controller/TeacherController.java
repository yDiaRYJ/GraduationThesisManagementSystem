package com.controller;

import com.entity.*;
import com.service.StudentStatusService;
import com.service.TopicService;
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
    @Autowired
    private TopicService topicService;
    @Autowired
    private StudentStatusService studentStatusService;

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
                    response.put("code", 200);
                    response.put("message", queryUser);
                } else {
                    response.put("success", false);
                    response.put("message", "wrong password");
                }
            }
        }
        return ResponseEntity.ok(response);
    }

    public List<User> getStudentsByTeacherId(String userId) {
        List<UserAssociation> userAssociationList = this.userAssociationService.getUserAssociationListByTeacherId(userId);
        List<User> userList = new ArrayList<>();

        for (UserAssociation userAssociation: userAssociationList) {
            User user = this.userService.getUserById(userAssociation.getStudentId());
            int studentStatus = studentStatusService.getStudentStatusByStudentId(user.getUserId()).getStudentStatus();
            Student student = new Student(user, studentStatus);
            userList.add(student);
        }
        return userList;
    }

    @PostMapping("/getStudents")
    public ResponseEntity<Map<String, Object>> getStudents(@RequestParam String userId) {
        Map<String, Object> response = new HashMap<>();

        List<User> userList = getStudentsByTeacherId(userId);

        if (!userList.isEmpty()) {
            response.put("success", true);
            response.put("code", 200);
            response.put("message", userList);
        } else {
            response.put("success", false);
            response.put("message", null);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/getTopics")
    public ResponseEntity<Map<String, Object>> getTopics(@RequestParam String userId) {
        Map<String, Object> response = new HashMap<>();

        List<User> studentList = getStudentsByTeacherId(userId);

        List<Topic> topicList = new ArrayList<>();

        for (User student: studentList) {
            Topic topic = topicService.getTopicByStudentId(student.getUserId());
            if (topic != null) {
                topicList.add(topic);
            }
        }
        if (topicList.isEmpty()) {
            response.put("success", false);
            response.put("message", null);
        } else {
            response.put("success", true);
            response.put("code", 200);
            response.put("message", topicList);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/judgeTopic")
    public ResponseEntity<Map<String, Object>> judgeTopic(@RequestParam String topicId,
                                                          @RequestParam boolean isPass) {
        Map<String, Object> response = new HashMap<>();

        Topic topic = topicService.getTopicByTopicId(topicId);
        if (topic != null) {
            if (isPass) {
                // 设置选题状态
                topic.setTopicStatus(2);
                // 设置学生状态
                User user = userService.getUserById(topic.getStudentId());
                StudentStatus studentStatus = studentStatusService.getStudentStatusByStudentId(user.getUserId());
                studentStatus.setStudentStatus(1);
                studentStatusService.updateStudentStatus(studentStatus);
            } else {
                topic.setTopicStatus(3);
                // 设置学生状态
                User user = userService.getUserById(topic.getStudentId());
                StudentStatus studentStatus = studentStatusService.getStudentStatusByStudentId(user.getUserId());
                studentStatus.setStudentStatus(0);
                studentStatusService.updateStudentStatus(studentStatus);
            }
            topicService.updateTopic(topic);
            response.put("success", true);
            response.put("code", 200);
            response.put("message", topic);
        } else {
            response.put("success", false);
            response.put("message", "wrong topic id!");
        }

        return ResponseEntity.ok(response);
    }
}
