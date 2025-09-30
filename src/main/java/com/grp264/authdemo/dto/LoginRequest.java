package com.grp264.authdemo.dto;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

//POST → http://localhost:8080/api/auth/login
//
//        {
//        "username": "senpai",
//        "password": "mypassword123"
//        } --> test seperately

