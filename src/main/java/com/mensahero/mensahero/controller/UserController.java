package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.DTO.UserDTO.UserCreateRequest;
import com.mensahero.mensahero.model.User;
import com.mensahero.mensahero.service.UserService;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/create-user")
    public User createUser(
            @Valid @RequestBody @NonNull
            UserCreateRequest userCreateRequest) {

        User user = new User();

        UUID uuid = UUID.randomUUID();

        user.setEmail(userCreateRequest.getEmail());
        user.setId(uuid);

        return userService.createUser(user);
    }
}
