package com.mensahero.mensahero.service.impl;

import com.mensahero.mensahero.DTO.keys.KeyDetails;
import com.mensahero.mensahero.Enums.KeyStatus;
import com.mensahero.mensahero.model.Key;
import com.mensahero.mensahero.repository.KeyRepository;
import com.mensahero.mensahero.service.KeyService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KeyServiceImp implements KeyService {
    KeyRepository keyRepository;

    public KeyServiceImp(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    @Override
    public KeyDetails getKeysOverview(UUID ownerId) {

        return null;
    }

    @Override
    public Boolean updateKeyStatus(UUID ownerId, UUID key, KeyStatus status) {

        return null;
    }

    @Override
    public Boolean deleteKey(UUID ownerId, UUID apiId) {

        return null;
    }

    @Override
    public Key createKey(UUID ownerId) {

        return null;
    }
}
