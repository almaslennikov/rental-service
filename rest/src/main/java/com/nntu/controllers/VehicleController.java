package com.nntu.controllers;

import com.nntu.containers.info.VehicleInfo;
import com.nntu.containers.responses.RequestStatus;
import com.nntu.containers.responses.Response;
import com.nntu.containers.responses.VehicleListResponse;
import com.nntu.dao.VehicleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class VehicleController {
    private VehicleDAO vehicleDAO;

    @CrossOrigin
    @RequestMapping("/addVehicleModel")
    public Response addVehicleModel(@RequestParam(value = "modelName") String modelName,
                                    @RequestParam(value = "modelVersion") String modelVersion) {

        return new Response(RequestStatus.SUCCESS);
    }

    @CrossOrigin
    @RequestMapping("/addNewVehicle")
    public Response addNewVehicle(@RequestParam(value = "ownerId") Long ownerId,
                                  @RequestParam(value = "id") Long modelId) {
        return new Response(RequestStatus.SUCCESS);
    }

    @CrossOrigin
    @RequestMapping("/getAvailableVehicles")
    public VehicleListResponse getAvailableVehicles() {
        List<VehicleInfo> test = new LinkedList<>();

        return new VehicleListResponse(RequestStatus.SUCCESS, test);
    }

    @CrossOrigin
    @RequestMapping("/getVehicleById")
    public VehicleListResponse getVehicleById(@RequestParam(value = "id") Long id) {
        List<VehicleInfo> test = new LinkedList<>();

        return new VehicleListResponse(RequestStatus.SUCCESS, test);
    }

    @Autowired
    public void setVehicleDAO(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }
}
