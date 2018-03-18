package com.nntu.dao;

import com.nntu.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {
    public User getUserByEmail(String email);
}
