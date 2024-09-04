package com.controller;

import com.entity.Topic;
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

    public User getTeacherByStudentId(String userId) {
        User queryUser = userService.getUserById(userId);
        if (queryUser != null) {
            UserAssociation userAssociation = this.userAssociationService.getUserAssociationByStudentId(userId);
            if (userAssociation != null) {
                User teacher = userService.getUserById(userAssociation.getTeacherId());
                if (teacher != null) {
                    return teacher;
                }
            }
        }
        return null;
    }

    @PostMapping("/getTeacher")
    public ResponseEntity<Map<String, Object>> getTeacher(@RequestParam String userId) {
        Map<String, Object> response = new HashMap<>();

        User teacher = getTeacherByStudentId(userId);
        if (teacher != null) {
            response.put("success", true);
            response.put("message", teacher);
        } else {
            response.put("success", false);
            response.put("message", teacher);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/getTopic")
    public ResponseEntity<Map<String, Object>> getTopic(@RequestParam String userId) {
        Topic topic = topicService.getTopicByStudentId(userId);

        Map<String, Object> response = new HashMap<>();

        if (topic == null) {
            response.put("success", false);
            response.put("message", topic);
        } else {
            response.put("success", true);
            response.put("message", topic);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addTopic")
    public ResponseEntity<Map<String, Object>> addTopic(@RequestParam String userId,
                                                        @RequestParam String topicName,
                                                        @RequestParam String topicDescription) {
        Map<String, Object> response = new HashMap<>();

        Topic queryTopic = topicService.getTopicByStudentId(userId);

        if (queryTopic != null) {
            if (queryTopic.getTopicStatus() == 2) {
                response.put("success", false);
                response.put("message", "the topic had already been judged");
            } else {
                queryTopic.setTopicName(topicName);
                queryTopic.setTopicDescription(topicDescription);
                queryTopic.setTopicStatus(1);
                topicService.updateTopic(queryTopic);
                response.put("success", true);
                response.put("message", queryTopic);
            }
        } else {
            Topic topic = new Topic(topicName, topicDescription, userId, getTeacherByStudentId(userId).getUserId(), 1);
            topicService.addTopic(topic);
            response.put("success", true);
            response.put("message", topic);
        }

        return ResponseEntity.ok(response);
    }
}
