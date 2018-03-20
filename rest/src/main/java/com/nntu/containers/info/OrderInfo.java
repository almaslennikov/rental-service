package com.nntu.containers.info;

import com.nntu.models.Order;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderInfo {
    private Long id;
    private UserInfo customerInfo;
    private VehicleInfo vehicleInfo;

    public OrderInfo(Order order) {
        this.id = order.getId();
        this.customerInfo = new UserInfo(order.getCustomer());
        this.vehicleInfo = new VehicleInfo(order.getVehicle());
    }

    public OrderInfo(Long id, UserInfo customerInfo, VehicleInfo vehicleInfo) {
        this.id = id;
        this.customerInfo = customerInfo;
        this.vehicleInfo = vehicleInfo;
    }
}
