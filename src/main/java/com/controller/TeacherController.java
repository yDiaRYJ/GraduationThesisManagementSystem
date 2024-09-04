package com.controller;

import com.entity.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
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
    @Autowired
    private DocService docService;
    @Autowired
    private FileService fileService;

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
    public ResponseEntity<Map<String, Object>> judgeTopic(@RequestParam String studentId,
                                                          @RequestParam boolean isPass) {
        Map<String, Object> response = new HashMap<>();

        Topic topic = topicService.getTopicByStudentId(studentId);
        if (topic != null) {
            // 找到选题
            if (isPass) {
                // 通过
                // 设置选题状态
                topic.setTopicStatus(2);
                // 设置学生状态
                User user = userService.getUserById(topic.getStudentId());
                StudentStatus studentStatus = studentStatusService.getStudentStatusByStudentId(user.getUserId());
                studentStatus.setStudentStatus(1);
                studentStatusService.updateStudentStatus(studentStatus);
            } else {
                // 未通过
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
            // 未找到选题
            response.put("success", false);
            response.put("message", "wrong student id!");
        }

        return ResponseEntity.ok(response);
    }

    /**
     * 根据文档类型返回学生状态
     * 学生状态
     * 0：未通过任何文档
     * 1：通过选题
     * 2：通过开题报告
     * 3：通过中期审核
     * 4：通过初稿
     * 5：通过检测版论文
     * 6：通过查重
     * 7：通过终稿
     * 8：通过答辩
     * <p>
     * 文档类别
     * 1：开题报告
     * 2：中期文档
     * 3：论文初稿
     * 4：检测版论文
     * 5：论文终稿
     *
     * @param docType 文档类别
     * @return 学生状态
     */
    public static int getStudentStatusByDocType(int docType) {
        switch (docType) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 7;
            default:
                return -1;
        }
    }

    @PostMapping("/getStudentDocs")
    public ResponseEntity<Map<String, Object>> getStudentDocs(@RequestParam String studentId) {
        Map<String, Object> response = new HashMap<>();

        List<Doc> docList = this.docService.getDocListByUserId(studentId);

        response.put("success", true);
        response.put("code", 200);
        response.put("message", docList);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/downloadDoc")
    public ResponseEntity<FileSystemResource> downloadDoc(@RequestParam String studentId,
                                                          @RequestParam int docType) {
        Doc doc = docService.getDocByUserIdAndDocType(studentId, docType);
        if (doc != null) {
            // 文件存在
            String filePath = fileService.getUploadDir() + File.separator + doc.getDocPath();
            return fileService.getFile(filePath);
        } else {
            // 文件不存在
            return null;
        }
    }

    @PostMapping("/judgeDoc")
    public ResponseEntity<Map<String, Object>> judgeDoc(@RequestParam String studentId,
                                                          @RequestParam int docType,
                                                          @RequestParam boolean isPass) {
        Map<String, Object> response = new HashMap<>();

        Doc doc = docService.getDocByUserIdAndDocType(studentId, docType);
        if (doc != null) {
            // 找到文档
            if (isPass) {
                // 通过
                // 设置文档状态
                doc.setDocStatus(2);
                // 设置学生状态
                User user = userService.getUserById(doc.getUserId());
                StudentStatus studentStatus = studentStatusService.getStudentStatusByStudentId(user.getUserId());
                studentStatus.setStudentStatus(getStudentStatusByDocType(docType));
                studentStatusService.updateStudentStatus(studentStatus);
            } else {
                // 未通过
                doc.setDocStatus(3);
                // 设置学生状态
                User user = userService.getUserById(doc.getUserId());
                StudentStatus studentStatus = studentStatusService.getStudentStatusByStudentId(user.getUserId());
                studentStatus.setStudentStatus(getStudentStatusByDocType(docType) - 1);
                studentStatusService.updateStudentStatus(studentStatus);
            }
            // 更新文档表
            docService.updateDoc(doc);
            // 构造返回消息
            response.put("success", true);
            response.put("code", 200);
            response.put("message", doc);
        } else {
            // 未找到文档
            response.put("success", false);
            response.put("message", "cant see the correct doc!");
        }

        return ResponseEntity.ok(response);
    }
}
