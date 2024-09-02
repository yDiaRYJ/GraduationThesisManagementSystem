package com.entity;

public class StudentStatus {
    private int statusId;
    private String studentId;
    private int studentStatus;

    // 默认构造函数
    public StudentStatus() {}

    // 带参数的构造函数
    public StudentStatus(int statusId, String studentId, int studentStatus) {
        this.statusId = statusId;
        this.studentId = studentId;
        this.studentStatus = studentStatus;
    }

    // Getter 和 Setter 方法
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(int studentStatus) {
        this.studentStatus = studentStatus;
    }

    @Override
    public String toString() {
        return "StudentStatus{" +
                "statusId=" + statusId +
                ", studentId='" + studentId + '\'' +
                ", studentStatus=" + studentStatus +
                '}';
    }
}
