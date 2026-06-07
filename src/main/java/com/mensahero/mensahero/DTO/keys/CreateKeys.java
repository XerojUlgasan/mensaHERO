package com.mensahero.mensahero.DTO.keys;

import java.util.UUID;

public class CreateKeys {
    private UUID ownerId;
    private String name;

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
}
