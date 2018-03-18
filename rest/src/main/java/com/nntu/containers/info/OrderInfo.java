package com.nntu.containers.info;

import com.nntu.models.Order;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderInfo {
    private Long id;
    private UserInfo customerInfo;
    private UserInfo landlordInfo;
    private VehicleInfo vehicleInfo;

    public OrderInfo(Order order) {
        this.id = order.getId();
        this.customerInfo = new UserInfo(order.getCustomer());
        this.landlordInfo = new UserInfo(order.getLandlord());
        this.vehicleInfo = new VehicleInfo(order.getVehicle());
    }

    public OrderInfo(Long id, UserInfo customerInfo, UserInfo landlordInfo, VehicleInfo vehicleInfo) {
        this.id = id;
        this.customerInfo = customerInfo;
        this.landlordInfo = landlordInfo;
        this.vehicleInfo = vehicleInfo;
    }
}
