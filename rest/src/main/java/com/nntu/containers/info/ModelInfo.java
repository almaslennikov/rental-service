package com.nntu.containers.info;

import com.nntu.models.Model;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ModelInfo {
    private Long id;
    private String modelName;
    private Integer modelVersion;

    public ModelInfo(Model model) {
        this.id = model.getId();
        this.modelName = model.getModelName();
        this.modelVersion = model.getModelVersion();
    }

    public ModelInfo(Long modelId, String modelName, Integer modelVersion) {
        this.id = modelId;
        this.modelName = modelName;
        this.modelVersion = modelVersion;
    }
}
