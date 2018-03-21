package com.nntu.controllers;

import com.nntu.containers.info.UserRole;
import com.nntu.containers.info.VehicleInfo;
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

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private VehicleDAO vehicleDAO;
    private ModelDAO modelDAO;
    private UserDAO userDAO;

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
    @RequestMapping(value = "/vehicle-by-id", method = RequestMethod.GET)
    public VehicleListResponse getVehicleById(@RequestParam(value = "id") Long id) {
        return new VehicleListResponse(RequestStatus.SUCCESS,
                vehicleDAO.findAllById(id)
                        .stream()
                        .map(VehicleInfo::new)
                        .collect(Collectors.toList()));
    }

    @CrossOrigin
    @RequestMapping(value = "/vehicles-by-landlord-id", method = RequestMethod.GET)
    public VehicleListResponse getVehiclesByLandlordId(@RequestParam(value = "id") Long id) {
        Optional<User> landlord = userDAO.findById(id);
        VehicleListResponse response = new VehicleListResponse(RequestStatus.SUCCESS);
        landlord.ifPresentOrElse(x -> {
                    if (x.getRole() == UserRole.LANDLORD) {
                        response.setVehicleInfoList(
                                vehicleDAO.findAllByLandlord(x)
                                        .stream()
                                        .map(VehicleInfo::new)
                                        .collect(Collectors.toList())
                        );
                    } else {
                        response.setStatus(RequestStatus.FAILURE);
                    }
                },
                () -> response.setStatus(RequestStatus.FAILURE));
        return response;
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
