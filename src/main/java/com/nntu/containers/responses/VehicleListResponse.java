package com.nntu.containers.responses;

import com.nntu.containers.info.VehicleInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class VehicleListResponse extends Response {
    private List<VehicleInfo> vehicleInfoList;

    public VehicleListResponse(RequestStatus status, List<VehicleInfo> vehicleInfoList) {
        super(status);
        this.vehicleInfoList = vehicleInfoList;
    }
}
