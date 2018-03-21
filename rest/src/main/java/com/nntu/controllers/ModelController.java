package com.nntu.controllers;

import com.nntu.containers.info.ModelInfo;
import com.nntu.containers.responses.ModelListResponse;
import com.nntu.containers.responses.RequestStatus;
import com.nntu.containers.responses.Response;
import com.nntu.dao.ModelDAO;
import com.nntu.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/models")
public class ModelController {
    private ModelDAO modelDAO;

    @CrossOrigin
    @RequestMapping(value = "/new-model", method = RequestMethod.POST)
    public Response addVehicleModel(@RequestParam(value = "brand") String brand,
                                    @RequestParam(value = "model") String modelName) {
        Optional<Model> findResult = Optional.ofNullable(modelDAO.getModelByBrand(brand));
        if (findResult.isPresent() &&
                findResult.get().getModelName().equals(modelName)) {
            return new Response(RequestStatus.FAILURE);
        }

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
    @RequestMapping(value = "/available-models", method = RequestMethod.GET)
    public ModelListResponse getAvailableModels() {
        return new ModelListResponse(RequestStatus.SUCCESS,
                StreamSupport.stream(modelDAO.findAll().spliterator(), false)
                        .map(ModelInfo::new)
                        .collect(Collectors.toList()));
    }

    @Autowired
    public void setModelDAO(ModelDAO modelDAO) {
        this.modelDAO = modelDAO;
    }
}
