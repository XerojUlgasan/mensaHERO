package com.mensahero.mensahero.DTO.keys;

import com.mensahero.mensahero.model.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class KeyIds {
    private UUID apiId;
    private String name;

    public KeyIds(){};

    public KeyIds(UUID apiId, String name) {
        this.apiId = apiId;
        this.name = name;
    }

    public static List<KeyIds> getKeyDetails(List<Key> keys){
        List<KeyIds> keyIdsList = new ArrayList<>();

        keys.forEach(key ->
            keyIdsList.add(new KeyIds(key.getId(), key.getName()))
        );

        return keyIdsList;
    }

    public UUID getApiId() {
        return apiId;
    }

    public void setApiId(UUID apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
