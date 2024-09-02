package com.entity;

public class Topic {
    private int topicId;
    private String topicDescription;
    private String studentId;
    private String teacherId;
    private int topicStatus;

    // 默认构造函数
    public Topic() {}

    // 带参数的构造函数
    public Topic(int topicId, String topicDescription, String studentId, String teacherId, int topicStatus) {
        this.topicId = topicId;
        this.topicDescription = topicDescription;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.topicStatus = topicStatus;
    }

    // Getter 和 Setter 方法
    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
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

    public int getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(int topicStatus) {
        this.topicStatus = topicStatus;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", topicDescription='" + topicDescription + '\'' +
                ", studentId='" + studentId + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", topicStatus=" + topicStatus +
                '}';
    }
}
