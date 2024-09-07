package com.service;

import com.entity.StudentStatus;
import com.mapper.StudentStatusMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentStatusService {
    private final StudentStatusMapper studentStatusMapper;

    public StudentStatusService(StudentStatusMapper studentStatusMapper) {
        this.studentStatusMapper = studentStatusMapper;
    }

    public StudentStatus getStudentStatusByStudentId(String userId) {
        return this.studentStatusMapper.getStudentStatusByStudentId(userId);
    }

    public void addStudentStatus(StudentStatus studentStatus) {
        this.studentStatusMapper.insertStudentStatus(studentStatus);
    }

    public void updateStudentStatus(StudentStatus studentStatus) {
        this.studentStatusMapper.updateStudentStatus(studentStatus);
    }
}
