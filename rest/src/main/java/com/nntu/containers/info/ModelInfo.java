package com.nntu.containers.info;

import com.nntu.models.Model;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ModelInfo {
    private Long id;
    private String brand;
    private String model;

    public ModelInfo(Model model) {
        this.id = model.getId();
        this.brand = model.getBrand();
        this.model = model.getModelName();
    }

    public ModelInfo(Long modelId, String brand, String model) {
        this.id = modelId;
        this.brand = brand;
        this.model = model;
    }
}
