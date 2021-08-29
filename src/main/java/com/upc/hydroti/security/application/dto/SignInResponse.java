package com.upc.hydroti.security.application.dto;

public class SignInResponse {

    private final String username;
    private final String token;

    public SignInResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() { return username; }

}
