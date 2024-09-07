package com.service;

import com.entity.Defense;
import com.mapper.DefenseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefenseService {
    private final DefenseMapper defenseMapper;

    public DefenseService(DefenseMapper defenseMapper) {
        this.defenseMapper = defenseMapper;
    }

    public Defense getDefenseByStudentId(@Param("studentId") String studentId) {
        return this.defenseMapper.getDefenseByStudentId(studentId);
    }

    public void insertDefense(Defense defense) {
        this.defenseMapper.insertDefense(defense);
    }

    public void updateDefenseStatus(Defense defense) {
        this.defenseMapper.updateDefenseStatus(defense);
    }
    public void arrangeDefense(Defense defense) {
        this.defenseMapper.arrangeDefense(defense);
    }

    public void scoreDefense(Defense defense) {
        this.defenseMapper.scoreDefense(defense);
    }
}
