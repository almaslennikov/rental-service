package com.nntu.containers.info;

import lombok.Data;

@Data
public class OrderInfo {
    private Long id;
    private UserInfo customerInfo;
    private UserInfo landlordInfo;

    public OrderInfo(Long id, UserInfo customerInfo, UserInfo landlordInfo) {
        this.id = id;
        this.customerInfo = customerInfo;
        this.landlordInfo = landlordInfo;
    }
}
