package com.salon.User.service.service.impl;

import com.salon.User.service.model.User;
import com.salon.User.service.payload.response.UserException;
import com.salon.User.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements com.salon.User.service.service.UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> res = userRepository.findById(id);

        if(res.isPresent()) {
            return res.get();
        }
        throw  new UserException("User not found");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        Optional<User>res = userRepository.findById(id);

        if(res.isEmpty()) {
            throw  new UserException("User not found with id " + id);
        }

        userRepository.deleteById(res.get().getId());
    }

    @Override
    public User updateUser(Long id, User user) throws UserException {
        Optional<User>res = userRepository.findById(id);

        if(res.isEmpty()) {
            throw  new UserException("User not found");
        }

        User existingUser = res.get();

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }
}
