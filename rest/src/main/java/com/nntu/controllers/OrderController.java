package com.nntu.controllers;

import com.nntu.containers.info.OrderInfo;
import com.nntu.containers.responses.OrdersResponse;
import com.nntu.containers.responses.RequestStatus;
import com.nntu.containers.responses.Response;
import com.nntu.dao.OrderDAO;
import com.nntu.dao.UserDAO;
import com.nntu.dao.VehicleDAO;
import com.nntu.models.Order;
import com.nntu.models.User;
import com.nntu.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderDAO orderDAO;
    private UserDAO userDAO;
    private VehicleDAO vehicleDAO;

    @CrossOrigin
    @RequestMapping(value = "/order-vehicle", method = RequestMethod.POST)
    public Response orderVehicle(@RequestParam(value = "customerId") Long customerId,
                                 @RequestParam(value = "vehicleOwnerId") Long vehicleOwnerId,
                                 @RequestParam(value = "vehicleId") Long vehicleId) {
        Optional<User> customer = userDAO.findById(customerId);
        Optional<User> landlord = userDAO.findById(vehicleOwnerId);
        Optional<Vehicle> vehicle = vehicleDAO.findById(vehicleId);

        if (!customer.isPresent() || !landlord.isPresent() || !vehicle.isPresent()) {
            return new Response(RequestStatus.FAILURE);
        }

        Response response = new Response(RequestStatus.SUCCESS);
        Order newOrder = new Order(customer.get(), landlord.get(), vehicle.get());
        vehicle.get().setIsBusy(Boolean.TRUE);
        try {
            orderDAO.save(newOrder);
            vehicleDAO.save(vehicle.get());
        } catch (Exception se) {
            response.setStatus(RequestStatus.FAILURE);
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping(value = "/orders-by-customer-id", method = RequestMethod.GET)
    public OrdersResponse getOrdersByCustomerId(@RequestParam(value = "id") Long id) {
        final OrdersResponse response = new OrdersResponse(RequestStatus.SUCCESS);
        userDAO.findById(id)
                .ifPresentOrElse(x -> response.getOrderInfoList().addAll(x.getCustomerOrders().stream()
                                .map(OrderInfo::new)
                                .collect(Collectors.toList())),
                        () -> response.setStatus(RequestStatus.FAILURE));

        return response;
    }

    @CrossOrigin
    @RequestMapping(value = "/orders-by-landlord-id", method = RequestMethod.GET)
    public OrdersResponse getOrdersByLandlordId(@RequestParam(value = "id") Long id) {
        final OrdersResponse response = new OrdersResponse(RequestStatus.SUCCESS);
        userDAO.findById(id)
                .ifPresentOrElse(x -> response.getOrderInfoList().addAll(x.getLandlordOrders().stream()
                                .map(OrderInfo::new)
                                .collect(Collectors.toList())),
                        () -> response.setStatus(RequestStatus.FAILURE));

        return response;
    }

    @CrossOrigin
    @RequestMapping(value = "/order", method = RequestMethod.DELETE)
    public Response cancelExistingOrder(@RequestParam(value = "id") Long id) {
        Response response = new Response(RequestStatus.SUCCESS);
        orderDAO.findById(id)
                .ifPresentOrElse(order -> {
                            try {
                                Vehicle vehicle = order.getVehicle();
                                vehicle.setIsBusy(Boolean.FALSE);
                                vehicleDAO.save(vehicle);
                                orderDAO.delete(order);
                            } catch (Exception se) {
                                response.setStatus(RequestStatus.FAILURE);
                            }
                        },
                        () -> {
                            response.setStatus(RequestStatus.FAILURE);
                        });
        return response;
    }

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setVehicleDAO(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }
}
