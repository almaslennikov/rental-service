package com.nntu.containers.info;

import lombok.Data;

@Data
public class VehicleInfo {
    private Long id;
    private ModelInfo modelInfo;

    public VehicleInfo(Long id, ModelInfo modelInfo) {
        this.id = id;
        this.modelInfo = modelInfo;
    }
}
