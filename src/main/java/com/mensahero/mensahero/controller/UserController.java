package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.service.UserService;
import org.springframework.web.bind.annotation.*;

//TODO:
//

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

}
