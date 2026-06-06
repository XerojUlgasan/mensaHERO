package com.mensahero.mensahero.DTO.keys;

import java.util.UUID;

public class CreateKeys {
    private UUID ownerId;

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }
}
