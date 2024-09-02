package com.entity;

public class Doc {
    private int docId;
    private String userId;
    private String docPath;
    private int docType;
    private int docStatus;

    // 默认构造函数
    public Doc() {}

    // 带参数的构造函数
    public Doc(int docId, String userId, String docPath, int docType, int docStatus) {
        this.docId = docId;
        this.userId = userId;
        this.docPath = docPath;
        this.docType = docType;
        this.docStatus = docStatus;
    }

    // Getter 和 Setter 方法
    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public int getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(int docStatus) {
        this.docStatus = docStatus;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "docId=" + docId +
                ", userId='" + userId + '\'' +
                ", docPath='" + docPath + '\'' +
                ", docType=" + docType +
                ", docStatus=" + docStatus +
                '}';
    }
}