package com.upc.hydroti.security.application.dto;

public class AddUserRequest {

    private String email;

    private String password;

    private String role;

    private String name;

    private String lastname;

    public AddUserRequest() {
    }

    public AddUserRequest(String email, String password, String role, String name, String lastname) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
