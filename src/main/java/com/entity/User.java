package com.entity;

public class User {
    private int ID;
    private String username;
    private String address;

    public User(int ID, String username, String address) {
        this.ID = ID;
        this.username = username;
        this.address = address;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
