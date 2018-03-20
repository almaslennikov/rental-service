package com.nntu.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "models")
@Data
public class Model {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "model")
    List<Vehicle> vehicles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "model_brand")
    private String brand;

    @NotNull
    @Column(name = "model_name")
    private String modelName;

    public Model() {
        // empty constructor
    }

    public Model(@NotNull String brand, @NotNull String modelName) {
        this.brand = brand;
        this.modelName = modelName;
    }
}
