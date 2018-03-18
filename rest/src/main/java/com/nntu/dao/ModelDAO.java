package com.nntu.dao;

import com.nntu.models.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelDAO extends CrudRepository<Model, Long> {

}
