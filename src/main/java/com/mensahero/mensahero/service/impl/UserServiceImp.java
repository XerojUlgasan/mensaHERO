package com.mensahero.mensahero.service.impl;

import com.mensahero.mensahero.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp {
    private final UserRepository userRepository;
    private final KeyServiceImp keyServiceImp;

    public UserServiceImp(UserRepository userRepository, KeyServiceImp keyServiceImp) {

        this.userRepository = userRepository;
        this.keyServiceImp = keyServiceImp;
    }
}