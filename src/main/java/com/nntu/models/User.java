package com.nntu.models;

import com.nntu.containers.info.UserRole;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String passwordHash;

    @NotNull
    private UserRole role;

    public User(@NotNull String name, @NotNull String lastName, @NotNull String email, @NotNull String passwordHash, @NotNull UserRole role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }
}
