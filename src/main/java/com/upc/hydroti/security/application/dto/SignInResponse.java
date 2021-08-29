package com.upc.hydroti.security.application.dto;

public class SignInResponse {

    private String username;
    private String token;

    public SignInResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public SignInResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
