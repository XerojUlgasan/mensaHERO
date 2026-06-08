package com.mensahero.mensahero.service;

import java.util.UUID;

public interface UserService {
    public void changeEmail(UUID userId, String email);
    public void changePassword(UUID userId, String password);
}
