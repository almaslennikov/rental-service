package com.nntu.containers.responses;

import com.nntu.containers.info.ModelInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ModelListResponse extends Response {
    private List<ModelInfo> modelInfoList;

    public ModelListResponse(RequestStatus status, List<ModelInfo> modelInfoList) {
        super(status);
        this.modelInfoList = modelInfoList;
    }
}
