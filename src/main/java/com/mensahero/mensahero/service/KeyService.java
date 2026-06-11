package com.mensahero.mensahero.service;

import com.mensahero.mensahero.Enums.KeyStatus;
import com.mensahero.mensahero.model.Key;

import java.util.List;
import java.util.UUID;

public interface KeyService {
    public List<UUID> getKeysId(UUID ownerId);

    public List<Key> getKeysOverview(UUID ownerId);

    // UPDATES STATUS
    public Key updateKey(UUID ownerId, UUID key, KeyStatus status, String name);

    // DELETES KEY
    public Key deleteKey(UUID ownerId, UUID apiId);

    // CREATES KEY
    public Key createKey(UUID ownerId, String name);
}
