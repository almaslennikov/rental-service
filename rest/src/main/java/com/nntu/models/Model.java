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
    @Column(name = "model_name")
    private String modelName;

    @NotNull
    @Column(name = "model_version")
    private Integer modelVersion;

    public Model() {
        // empty constructor
    }

    public Model(@NotNull String modelName, @NotNull Integer modelVersion) {
        this.modelName = modelName;
        this.modelVersion = modelVersion;
    }
}
