package com.salon.User.service.service;

import com.salon.User.service.model.User;
import com.salon.User.service.payload.response.UserException;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User getUserById(Long id) throws UserException;
    List<User>getAllUsers();
    void deleteUser(Long id) throws UserException;
    User updateUser(Long id, User user) throws UserException;
}
