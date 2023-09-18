package com.example.cafemanager.user;

public class User {
    private String userName;
    private String password;

    //생성자

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //getter,setter

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
