package com.nntu.controllers;

import com.nntu.containers.info.UserInfo;
import com.nntu.containers.info.UserRole;
import com.nntu.containers.responses.AuthorizationResponse;
import com.nntu.containers.responses.RequestStatus;
import com.nntu.containers.responses.UserResponse;
import com.nntu.dao.UserDAO;
import com.nntu.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserDAO userDAO;

    @RequestMapping("/addNewUser")
    public UserResponse addNewUser(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "lastName") String lastName,
                                   @RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "role") UserRole role) {
        User newUser = new User(name, lastName, email, password, role);
        userDAO.save(newUser);

        return new UserResponse(RequestStatus.SUCCESS,
                new UserInfo(newUser.getId(),
                        newUser.getName(),
                        newUser.getLastName(),
                        newUser.getEmail(),
                        newUser.getRole()));
    }

    @RequestMapping("/authorizeUser")
    public AuthorizationResponse authorizeUser(@RequestParam(value = "email") String email,
                                               @RequestParam(value = "password") String password) {
        Long userId = 1L;
        return new AuthorizationResponse(RequestStatus.SUCCESS, userId);
    }

    @RequestMapping("/getUserById")
    public UserResponse getUserById(@RequestParam(value = "id") Long userId) {
        return new UserResponse(RequestStatus.SUCCESS,
                new UserInfo(1L, "Mark", "Lemeshevskij", "gigelbig@gmail.com", UserRole.LANDLORD));
    }

    @Autowired
    public void setUserDao(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
