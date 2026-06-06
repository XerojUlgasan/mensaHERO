package com.mensahero.mensahero.DTO.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserCreateRequest {

    @NotNull
    @Email
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
