package com.upc.hydroti.security.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.core.sym.Name;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "name", nullable = false)
    private String name;

    public UserEntity(String Id, String Email, String Password, String Role, String LastName, String Name) {
        id = Id;
        email = Email;
        password = Password;
        role = Role;
        lastname = LastName;
        name = Name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
