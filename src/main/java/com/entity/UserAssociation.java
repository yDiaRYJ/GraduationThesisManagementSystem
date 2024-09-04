package com.entity;

public class UserAssociation {
    private int associationId;
    private String studentId;
    private String teacherId;

    // 默认构造函数
    public UserAssociation() {}

    public UserAssociation(String studentId, String teacherId) {
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    // 带参数的构造函数
    public UserAssociation(int associationId, String studentId, String teacherId) {
        this.associationId = associationId;
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    // Getter 和 Setter 方法
    public int getAssociationId() {
        return associationId;
    }

    public void setAssociationId(int associationId) {
        this.associationId = associationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "UserAssociation{" +
                "associationId=" + associationId +
                ", studentId='" + studentId + '\'' +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}
