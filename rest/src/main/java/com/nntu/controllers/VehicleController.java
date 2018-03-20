package com.nntu.controllers;

import com.nntu.containers.info.ModelInfo;
import com.nntu.containers.info.VehicleInfo;
import com.nntu.containers.responses.ModelListResponse;
import com.nntu.containers.responses.RequestStatus;
import com.nntu.containers.responses.Response;
import com.nntu.containers.responses.VehicleListResponse;
import com.nntu.dao.ModelDAO;
import com.nntu.dao.UserDAO;
import com.nntu.dao.VehicleDAO;
import com.nntu.models.Model;
import com.nntu.models.User;
import com.nntu.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class VehicleController {
    private VehicleDAO vehicleDAO;
    private ModelDAO modelDAO;
    private UserDAO userDAO;

    @CrossOrigin
    @RequestMapping("/addVehicleModel")
    public Response addVehicleModel(@RequestParam(value = "modelName") String modelName,
                                    @RequestParam(value = "modelVersion") Integer modelVersion) {
        Model newModel = new Model(modelName, modelVersion);
        Response response = new Response(RequestStatus.SUCCESS);
        try {
            modelDAO.save(newModel);
        } catch (Exception se) {
            response.setStatus(RequestStatus.FAILURE);
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping("/addNewVehicle")
    public Response addNewVehicle(@RequestParam(value = "ownerId") Long ownerId,
                                  @RequestParam(value = "modelId") Long modelId) {
        Optional<Model> model = modelDAO.findById(modelId);
        Optional<User> user = userDAO.findById(ownerId);

        if (!model.isPresent() || !user.isPresent()) {
            return new Response(RequestStatus.FAILURE);
        }

        Vehicle newVehicle = new Vehicle(model.get(), Boolean.FALSE, user.get());
        Response response = new Response(RequestStatus.SUCCESS);
        try {
            vehicleDAO.save(newVehicle);
        } catch (Exception se) {
            response.setStatus(RequestStatus.FAILURE);
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping("/getAvailableVehicles")
    public VehicleListResponse getAvailableVehicles() {
        return new VehicleListResponse(RequestStatus.SUCCESS,
                vehicleDAO.findAllByIsBusy(Boolean.FALSE)
                        .stream()
                        .map(VehicleInfo::new)
                        .collect(Collectors.toList()));
    }

    @CrossOrigin
    @RequestMapping("/getVehicleById")
    public VehicleListResponse getVehicleById(@RequestParam(value = "id") Long id) {
        return new VehicleListResponse(RequestStatus.SUCCESS,
                vehicleDAO.findAllById(id)
                        .stream()
                        .map(VehicleInfo::new)
                        .collect(Collectors.toList()));
    }

    @CrossOrigin
    @RequestMapping("/getAvailableModels")
    public ModelListResponse getAvailableModels() {
        return new ModelListResponse(RequestStatus.SUCCESS,
                StreamSupport.stream(modelDAO.findAll().spliterator(), false)
                        .map(ModelInfo::new)
                        .collect(Collectors.toList()));
    }

    @Autowired
    public void setVehicleDAO(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    @Autowired
    public void setModelDAO(ModelDAO modelDAO) {
        this.modelDAO = modelDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
