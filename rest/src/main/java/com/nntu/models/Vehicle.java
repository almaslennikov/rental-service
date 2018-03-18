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
}
