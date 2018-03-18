package com.nntu.containers.info;

import lombok.Data;

@Data
public class UserInfo {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private UserRole role;

    public UserInfo(Long id, String name, String lastName, String email, UserRole role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
