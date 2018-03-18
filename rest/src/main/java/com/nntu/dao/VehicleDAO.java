package com.nntu.dao;

import com.nntu.models.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDAO extends CrudRepository<Vehicle, Long> {
    public List<Vehicle> findAllByIsBusy(Boolean value);

    public List<Vehicle> findAllById(Long id);
}
