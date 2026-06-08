package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.service.impl.UserServiceImp;
import org.springframework.web.bind.annotation.*;

//TODO:
//

@RestController
@RequestMapping("/api/users")
public class UserController {
    UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
