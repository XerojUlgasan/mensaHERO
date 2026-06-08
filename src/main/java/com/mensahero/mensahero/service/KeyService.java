package com.mensahero.mensahero.service;

import com.mensahero.mensahero.DTO.keys.KeyDetails;
import com.mensahero.mensahero.Enums.KeyStatus;
import com.mensahero.mensahero.model.Key;

import java.util.UUID;

public interface KeyService {
    // RETURNS ARRAY OF {apiId, name}
    public KeyDetails getKeysOverview(UUID ownerId);

    // UPDATES STATUS
    public Boolean updateKeyStatus(UUID ownerId, UUID key, KeyStatus status);

    // DELETES KEY
    public Boolean deleteKey(UUID ownerId, UUID apiId);

    // CREATES KEY
    public Key createKey(UUID ownerId);
}
