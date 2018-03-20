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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private VehicleDAO vehicleDAO;
    private ModelDAO modelDAO;
    private UserDAO userDAO;

    @CrossOrigin
    @RequestMapping(value = "/new-vehicle-model", method = RequestMethod.POST)
    public Response addVehicleModel(@RequestParam(value = "brand") String brand,
                                    @RequestParam(value = "model") String modelName) {
        Model newModel = new Model(brand, modelName);
        Response response = new Response(RequestStatus.SUCCESS);
        try {
            modelDAO.save(newModel);
        } catch (Exception se) {
            response.setStatus(RequestStatus.FAILURE);
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping(value = "/new-vehicle", method = RequestMethod.POST)
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
    @RequestMapping(value = "/available-vehicles", method = RequestMethod.GET)
    public VehicleListResponse getAvailableVehicles() {
        return new VehicleListResponse(RequestStatus.SUCCESS,
                vehicleDAO.findAllByIsBusy(Boolean.FALSE)
                        .stream()
                        .map(VehicleInfo::new)
                        .collect(Collectors.toList()));
    }

    @CrossOrigin
    @RequestMapping("/vehicle-by-id")
    public VehicleListResponse getVehicleById(@RequestParam(value = "id") Long id) {
        return new VehicleListResponse(RequestStatus.SUCCESS,
                vehicleDAO.findAllById(id)
                        .stream()
                        .map(VehicleInfo::new)
                        .collect(Collectors.toList()));
    }

    @CrossOrigin
    @RequestMapping(value = "/available-models", method = RequestMethod.GET)
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
