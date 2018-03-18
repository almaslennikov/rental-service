package com.nntu.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "model_id")
    @ManyToOne
    private Model model;

    @NotNull
    @Column(name = "is_busy")
    private Boolean isBusy;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "landlord_id")
    private User landlord;

    public Vehicle() {
        // empty constructor
    }

    public Vehicle(@NotNull Model model, @NotNull Boolean isBusy, @NotNull User landlord) {
        this.model = model;
        this.isBusy = isBusy;
        this.landlord = landlord;
    }
}
