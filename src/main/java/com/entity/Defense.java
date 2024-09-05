package com.entity;

import java.util.Date;

public class Defense {
    private int defenseId;
    private Date defenseStartTime;
    private Date defenseEndTime;
    private int defenseScore;
    private String studentId;
    private int defenseStatus;

    // 默认构造函数
    public Defense() {}

    // 带参数的构造函数
    public Defense(String studentId, int defenseStatus) {
        this.studentId = studentId;
        this.defenseStatus = defenseStatus;
    }

    // Getter 和 Setter 方法
    public int getDefenseId() {
        return defenseId;
    }

    public void setDefenseId(int defenseId) {
        this.defenseId = defenseId;
    }

    public Date getDefenseStartTime() {
        return defenseStartTime;
    }

    public void setDefenseStartTime(Date defenseStartTime) {
        this.defenseStartTime = defenseStartTime;
    }

    public Date getDefenseEndTime() {
        return defenseEndTime;
    }

    public void setDefenseEndTime(Date defenseEndTime) {
        this.defenseEndTime = defenseEndTime;
    }

    public int getDefenseScore() {
        return defenseScore;
    }

    public void setDefenseScore(int defenseScore) {
        this.defenseScore = defenseScore;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getDefenseStatus() {
        return defenseStatus;
    }

    public void setDefenseStatus(int defenseStatus) {
        this.defenseStatus = defenseStatus;
    }
}
