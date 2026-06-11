package com.mensahero.mensahero.service.impl;

import com.mensahero.mensahero.DTO.keys.KeyDetails;
import com.mensahero.mensahero.Enums.KeyStatus;
import com.mensahero.mensahero.exception.ApiException;
import com.mensahero.mensahero.model.Key;
import com.mensahero.mensahero.repository.KeyRepository;
import com.mensahero.mensahero.service.KeyService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KeyServiceImp implements KeyService {
    KeyRepository keyRepository;

    public KeyServiceImp(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    @Override
    public List<UUID> getKeysId(UUID ownerId) {
        return keyRepository.getKeysId((UUID) ownerId);
    }

    @Override
    public List<Key> getKeysOverview(UUID ownerId) {
        return keyRepository.findByOwnerId(ownerId);
    }

    @Transactional
    @Override
    public Key updateKey(UUID ownerId, UUID apiId, KeyStatus status, String name) {
        Optional<Key> optKey = keyRepository.findByIdAndOwnerId(apiId, ownerId);
        Key key;

        key = optKey.orElse(null);

        if(key == null){
            throw new ApiException("No API key found using the provided details.", HttpStatus.NOT_FOUND);
        }

        if((status == null || key.getStatus().equals(status)) && (name == null || key.getName().equals(name))) {
            throw new ApiException("No Change Detected", HttpStatus.NOT_ACCEPTABLE);
        }

        if(status != null) {key.setStatus(status);}
        if(name != null && !name.isEmpty()) key.setName(name);

        return keyRepository.save(key);
    }

    @Transactional
    @Override
    public Key deleteKey(UUID ownerId, UUID apiId) {
        Optional<Key> optKey = keyRepository.findByOwnerIdAndId(ownerId, apiId);
        Key key = optKey.orElse(null);

        if(key == null) {
            throw new ApiException("No API key found using the provided details.", HttpStatus.NOT_FOUND);
        }
        keyRepository.delete(key);

        return key;
    }

    @Override
    public Key createKey(UUID ownerId, String name) {
        return keyRepository.save(Key.createKey(ownerId, name));
    }
}
