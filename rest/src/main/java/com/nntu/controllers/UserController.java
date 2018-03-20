package com.nntu.controllers;

import com.nntu.containers.info.UserInfo;
import com.nntu.containers.info.UserRole;
import com.nntu.containers.responses.AuthorizationResponse;
import com.nntu.containers.responses.RequestStatus;
import com.nntu.containers.responses.UserResponse;
import com.nntu.dao.UserDAO;
import com.nntu.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserDAO userDAO;

    @CrossOrigin
    @RequestMapping(value = "/new-user", method = RequestMethod.POST)
    public UserResponse addNewUser(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "lastName") String lastName,
                                   @RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "role") UserRole role) {
        if (Optional.ofNullable(userDAO.getUserByEmail(email)).isPresent()) {
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
            response.setUserInfo(new UserInfo(newUser));
        } catch (Exception se) {
            response = new UserResponse(RequestStatus.FAILURE);
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping(value = "/authorize-user", method = RequestMethod.GET)
    public AuthorizationResponse authorizeUser(@RequestParam(value = "email") String email,
                                               @RequestParam(value = "password") String password) {
        Optional<User> result = Optional.ofNullable(userDAO.getUserByEmail(email));
        AuthorizationResponse response = new AuthorizationResponse(RequestStatus.SUCCESS);
        result.ifPresentOrElse(x -> {
                    if (UUID.fromString(x.getPasswordHash())
                            .equals(UUID.nameUUIDFromBytes(password.getBytes()))) {
                        response.setUserInfo(new UserInfo(x));
                    } else {
                        response.setStatus(RequestStatus.FAILURE);
                    }
                },
                () -> response.setStatus(RequestStatus.FAILURE));

        return response;
    }

    @CrossOrigin
    @RequestMapping(value = "/user-by-id", method = RequestMethod.GET)
    public UserResponse getUserById(@RequestParam(value = "id") Long userId) {
        Optional<User> result = userDAO.findById(userId);
        UserResponse response = new UserResponse(RequestStatus.SUCCESS);
        result.ifPresentOrElse(x -> response.setUserInfo(new UserInfo(x)),
                () -> response.setStatus(RequestStatus.FAILURE));

        return response;
    }

    @Autowired
    public void setUserDao(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
