package com.controller;

import com.entity.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    @Autowired
    private StudentStatusService studentStatusService;
    @Autowired
    private FileService fileService;
    @Autowired
    private DocService docService;


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
                    response.put("code", 200);
                    // 获取学生当前状态
                    int studentStatus = studentStatusService.getStudentStatusByStudentId(userId).getStudentStatus();
                    // 构造返回值
                    Student student = new Student(queryUser, studentStatus);
                    response.put("message", student);
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
            response.put("code", 200);
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
            response.put("code", 200);
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
            // 已经提交过选题
            if (queryTopic.getTopicStatus() == 2) {
                // 选题已经被评审
                response.put("success", false);
                response.put("message", "the topic had already been judged");
            } else {
                // 选题未被评审，更新选题
                queryTopic.setTopicName(topicName);
                queryTopic.setTopicDescription(topicDescription);
                queryTopic.setTopicStatus(1);
                topicService.updateTopic(queryTopic);
                response.put("success", true);
                response.put("code", 200);
                response.put("message", queryTopic);
            }
        } else {
            // 未提交过选题
            User teacher = getTeacherByStudentId(userId);
            if (teacher == null){
                // 无指导老师
                response.put("success", false);
                response.put("message", "the student dont have a teacher!");
            } else {
                // 有指导老师
                Topic topic = new Topic(topicName, topicDescription, userId, teacher.getUserId(), 1);
                topicService.addTopic(topic);
                response.put("success", true);
                response.put("code", 200);
                response.put("message", topic);
            }
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam MultipartFile file,
                             @RequestParam String userId,
                             @RequestParam int docType) {
        Map<String, Object> response = new HashMap<>();

        Doc queryDoc = docService.getDocByUserIdAndDocType(userId, docType);
        if (queryDoc != null && queryDoc.getDocStatus() == 2) {
            // 文档已经被评审
            response.put("success", false);
            response.put("message", "the doc had been judged!");
        } else {
            // 文档未被评审
            String filePath = fileService.saveFile(file, userId, docType);
            if (filePath != null) {
                // 文档保存成功
                if (queryDoc != null) {
                    // 已经提交过，更新文档状态
                    queryDoc.setDocStatus(1);
                    docService.updateDoc(queryDoc);
                    response.put("success", true);
                    response.put("code", 200);
                    response.put("message", queryDoc);
                } else {
                    // 未提交过，新增记录
                    Doc doc = new Doc(userId, filePath, docType, 1);
                    docService.insertDoc(doc);
                    response.put("success", true);
                    response.put("code", 200);
                    response.put("message", doc);
                }
            } else {
                // 文档保存失败
                response.put("success", false);
                response.put("message", "save error!");
            }
        }

        return ResponseEntity.ok(response);
    }
}
