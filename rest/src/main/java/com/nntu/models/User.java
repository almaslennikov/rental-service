package com.nntu.models;

import com.nntu.containers.info.UserRole;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password_hash")
    private String passwordHash;

    @NotNull
    @Column(name = "role")
    private UserRole role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Order> customerOrders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "landlord")
    private List<Order> landlordOrders;

    public User() {
        // empty constructor
    }

    public User(@NotNull String name, @NotNull String lastName, @NotNull String email, @NotNull String passwordHash, @NotNull UserRole role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }
}
