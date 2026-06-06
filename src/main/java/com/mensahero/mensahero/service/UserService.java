package com.mensahero.mensahero.service;

import com.mensahero.mensahero.model.User;
import com.mensahero.mensahero.repository.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}