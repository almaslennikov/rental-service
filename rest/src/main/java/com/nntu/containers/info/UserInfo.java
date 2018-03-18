package com.nntu.containers.info;

import com.nntu.models.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfo {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private UserRole role;

    public UserInfo(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public UserInfo(Long id, String name, String lastName, String email, UserRole role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
