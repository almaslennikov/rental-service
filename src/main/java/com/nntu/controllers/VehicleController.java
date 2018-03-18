package com.nntu.controllers;

import com.nntu.containers.info.ModelInfo;
import com.nntu.containers.info.VehicleInfo;
import com.nntu.containers.responses.RequestStatus;
import com.nntu.containers.responses.Response;
import com.nntu.containers.responses.VehicleListResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class VehicleController {
    @RequestMapping("/addVehicleModel")
    public Response addVehicleModel(@RequestParam(value = "modelName") String modelName,
                                    @RequestParam(value = "modelVersion") String modelVersion) {

        return new Response(RequestStatus.SUCCESS);
    }

    @RequestMapping("/addNewVehicle")
    public Response addNewVehicle(@RequestParam(value = "ownerId") Long ownerId,
                                  @RequestParam(value = "modelId") Long modelId) {
        return new Response(RequestStatus.SUCCESS);
    }

    @RequestMapping("/getAvailableVehicles")
    public VehicleListResponse getAvailableVehicles() {
        List<VehicleInfo> test = new LinkedList<>();
        test.add(new VehicleInfo(1L, new ModelInfo(2L, "UAZ", 1)));
        return new VehicleListResponse(RequestStatus.SUCCESS, test);
    }

    @RequestMapping("/getVehicleById")
    public VehicleListResponse getVehicleById(@RequestParam(value = "id") Long id) {
        List<VehicleInfo> test = new LinkedList<>();
        test.add(new VehicleInfo(1L, new ModelInfo(2L, "UAZ", 1)));
        return new VehicleListResponse(RequestStatus.SUCCESS, test);
    }
}
