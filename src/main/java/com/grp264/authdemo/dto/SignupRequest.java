package com.grp264.authdemo.dto;

public class SignupRequest {
    private String username;
    private String email;
    private String password;

    public SignupRequest() {}

    public SignupRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

//POST → http://localhost:8080/api/auth/signup
//{
//        "username": "senpai",
//        "email": "senpai@example.com",
//        "password": "mypassword123"
//        }   -> send this as a request json body pookie

