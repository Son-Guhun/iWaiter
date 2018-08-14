package com.example.songuhun.objects;

public class User {
    private String userName;

    public User(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
