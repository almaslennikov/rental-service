package com.nntu.controllers;

import com.nntu.containers.info.OrderInfo;
import com.nntu.containers.info.UserInfo;
import com.nntu.containers.info.UserRole;
import com.nntu.containers.responses.OrdersResponse;
import com.nntu.containers.responses.RequestStatus;
import com.nntu.containers.responses.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class OrderController {
    @RequestMapping("/orderVehicle")
    public Response orderVehicle(@RequestParam(value = "customerId") Long customerId,
                                 @RequestParam(value = "vehicleOwnerId") Long vehicleOwnerId,
                                 @RequestParam(value = "vehicleId") Long vehicleId) {

        return new Response(RequestStatus.SUCCESS);
    }

    @RequestMapping("/getOrdersByCustomerId")
    public OrdersResponse getOrdersByCustomerId(@RequestParam(value = "id") Long id) {
        List<OrderInfo> test = new LinkedList<>();
        test.add(new OrderInfo(1L,
                new UserInfo(1L, "Mark", "Lemeshevskij", "gigelbig@gmail.com", UserRole.CUSTOMER),
                new UserInfo(2L, "Alexander", "Maslennikov", "combat0705@yandex.ru", UserRole.LANDLORD)));

        return new OrdersResponse(RequestStatus.SUCCESS, test);
    }

    @RequestMapping("/getOrdersByLandlordId")
    public OrdersResponse getOrdersByLandlordId(@RequestParam(value = "id") Long id) {
        List<OrderInfo> test = new LinkedList<>();
        test.add(new OrderInfo(1L,
                new UserInfo(1L, "Mark", "Lemeshevskij", "gigelbig@gmail.com", UserRole.CUSTOMER),
                new UserInfo(2L, "Alexander", "Maslennikov", "combat0705@yandex.ru", UserRole.LANDLORD)));
        
        return new OrdersResponse(RequestStatus.SUCCESS, test);
    }
}
