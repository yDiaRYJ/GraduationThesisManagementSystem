package com.entity;

import java.util.Date;

public class Defense {
    private int defenseId;
    private Date defenseStartTime;
    private Date defenseEndTime;
    private int defenseScore;
    private String studentId;

    // 默认构造函数
    public Defense() {}

    // 带参数的构造函数
    public Defense(int defenseId, Date defenseStartTime, Date defenseEndTime, int defenseScore, String studentId) {
        this.defenseId = defenseId;
        this.defenseStartTime = defenseStartTime;
        this.defenseEndTime = defenseEndTime;
        this.defenseScore = defenseScore;
        this.studentId = studentId;
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

    @Override
    public String toString() {
        return "Defense{" +
                "defenseId=" + defenseId +
                ", defenseStartTime=" + defenseStartTime +
                ", defenseEndTime=" + defenseEndTime +
                ", defenseScore=" + defenseScore +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
