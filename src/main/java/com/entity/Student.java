package com.entity;

public class Student extends User{
    private int studentStatus;

    public Student(User user, int studentStatus) {
        super(user.getUserId(), user.getUserName(), user.getUserPassword(), user.getUserType());
        this.studentStatus = studentStatus;
    }


    public int getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(int studentStatus) {
        this.studentStatus = studentStatus;
    }
}
