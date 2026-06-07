package com.mensahero.mensahero.service;

import com.mensahero.mensahero.model.Key;
import com.mensahero.mensahero.repository.KeyRepository;
import com.mensahero.mensahero.repository.MessageRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KeyService {
    KeyRepository keyRepository;

    public KeyService(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    public List<Key> getKeys(UUID ownerId){
        return keyRepository.findByOwnerId(ownerId);
    }

    public Key createKey(Key key){
        return keyRepository.save(key);
    }

    public Key checkKeyExistence(@NotNull String key) {
        return keyRepository.findByKey(key).orElse(null);
    }
}
