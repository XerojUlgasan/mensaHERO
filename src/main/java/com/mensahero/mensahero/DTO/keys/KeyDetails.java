package com.mensahero.mensahero.DTO.keys;

import com.mensahero.mensahero.Enums.KeyStatus;

import java.util.UUID;

public class KeyDetails {
    private UUID ownerId;
    private String name;
    private KeyStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public KeyStatus getStatus() {
        return status;
    }

    public void setStatus(KeyStatus status) {
        this.status = status;
    }
}
