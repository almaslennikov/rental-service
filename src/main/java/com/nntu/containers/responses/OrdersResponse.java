package com.nntu.containers.responses;

import com.nntu.containers.info.OrderInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrdersResponse extends Response {
    private List<OrderInfo> orderInfoList;

    public OrdersResponse(RequestStatus status, List<OrderInfo> orderInfoList) {
        super(status);
        this.orderInfoList = orderInfoList;
    }
}
