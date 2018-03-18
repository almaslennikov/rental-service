package com.nntu.controllers;

import com.nntu.containers.info.UserInfo;
import com.nntu.containers.info.UserRole;
import com.nntu.containers.responses.AuthorizationResponse;
import com.nntu.containers.responses.RequestStatus;
import com.nntu.containers.responses.UserResponse;
import com.nntu.dao.UserDAO;
import com.nntu.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    private UserDAO userDAO;

    @CrossOrigin
    @RequestMapping("/addNewUser")
    public UserResponse addNewUser(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "lastName") String lastName,
                                   @RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "role") UserRole role) {
        if (authorizeUser(email, password).getStatus().equals(RequestStatus.SUCCESS)) {
            return new UserResponse(RequestStatus.FAILURE);
        }

        User newUser = new User(name,
                lastName,
                email,
                UUID.nameUUIDFromBytes(password.getBytes()).toString(),
                role);
        UserResponse response = new UserResponse(RequestStatus.SUCCESS);
        try {
            userDAO.save(newUser);
            response.setUserInfo(new UserInfo(newUser.getId(),
                    newUser.getName(),
                    newUser.getLastName(),
                    newUser.getEmail(),
                    newUser.getRole()));
        } catch (Exception se) {
            response = new UserResponse(RequestStatus.FAILURE);
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping("/authorizeUser")
    public AuthorizationResponse authorizeUser(@RequestParam(value = "email") String email,
                                               @RequestParam(value = "password") String password) {
        Optional<User> result = Optional.ofNullable(userDAO.getUserByEmail(email));
        AuthorizationResponse response = new AuthorizationResponse(RequestStatus.SUCCESS);
        result.ifPresentOrElse(x -> {
                    if (UUID.fromString(x.getPasswordHash())
                            .equals(UUID.nameUUIDFromBytes(password.getBytes()))) {
                        response.setUserInfo(UserInfo.builder()
                                .id(x.getId())
                                .name(x.getName())
                                .lastName(x.getLastName())
                                .email(x.getEmail())
                                .role(x.getRole())
                                .build()
                        );
                    } else {
                        response.setStatus(RequestStatus.FAILURE);
                    }
                },
                () -> response.setStatus(RequestStatus.FAILURE));

        return response;
    }

    @CrossOrigin
    @RequestMapping("/getUserById")
    public UserResponse getUserById(@RequestParam(value = "id") Long userId) {
        Optional<User> result = userDAO.findById(userId);
        UserResponse response = new UserResponse(RequestStatus.SUCCESS);
        result.ifPresentOrElse(x -> response.setUserInfo(UserInfo.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .lastName(x.getLastName())
                        .email(x.getEmail())
                        .role(x.getRole())
                        .build()),
                () -> response.setStatus(RequestStatus.FAILURE));

        return response;
    }

    @Autowired
    public void setUserDao(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
