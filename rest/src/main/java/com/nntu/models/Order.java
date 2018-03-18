package com.nntu.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User customer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User landlord;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    public Order() {
        // empty constructor
    }

    public Order(@NotNull User customer, @NotNull User landlord, @NotNull Vehicle vehicle) {
        this.customer = customer;
        this.landlord = landlord;
        this.vehicle = vehicle;
    }
}
