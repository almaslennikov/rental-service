package com.nntu.containers.info;

import lombok.Data;

@Data
public class ModelInfo {
    private Long modelId;
    private String modelName;
    private Integer modelVersion;

    public ModelInfo(Long modelId, String modelName, Integer modelVersion) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelVersion = modelVersion;
    }
}
