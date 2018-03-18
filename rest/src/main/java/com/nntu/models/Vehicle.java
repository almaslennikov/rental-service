package com.nntu.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Model model;

    @NotNull
    @Column(name = "is_busy")
    private Boolean isBusy;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User landlord;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
    private List<Order> orders;

    public Vehicle() {
        // empty constructor
    }

    public Vehicle(@NotNull Model model, @NotNull Boolean isBusy, @NotNull User landlord) {
        this.model = model;
        this.isBusy = isBusy;
        this.landlord = landlord;
    }
}
