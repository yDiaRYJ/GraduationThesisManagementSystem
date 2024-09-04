package com.service;

import com.entity.UserAssociation;
import com.mapper.UserAssociationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserAssociationService {
    //注入用户 Mapper
    private final UserAssociationMapper userAssociationMapper;

    public UserAssociationService(UserAssociationMapper userAssociationMapper) {
        this.userAssociationMapper = userAssociationMapper;
    }

    public UserAssociation getUserAssociationByStudentId(String studentId) {
        return this.userAssociationMapper.getUserAssociationByStudentId(studentId);
    }

    public List<UserAssociation> getUserAssociationListByTeacherId(String teacherId) {
        return this.userAssociationMapper.getUserAssociationListByTeacherId(teacherId);
    }
    public void addUserAssociation(UserAssociation userAssociation) {
        this.userAssociationMapper.insertUserAssociation(userAssociation);
    }
}
