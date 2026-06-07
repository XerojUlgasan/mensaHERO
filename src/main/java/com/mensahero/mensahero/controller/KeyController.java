package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.DTO.keys.CreateKeys;
import com.mensahero.mensahero.model.Key;
import com.mensahero.mensahero.repository.KeyRepository;
import com.mensahero.mensahero.service.KeyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//TODO:
//

@RestController
@RequestMapping("/api/keys")
public class KeyController {
    KeyService keyService;

    public KeyController(KeyService keyService) {this.keyService = keyService;}

    @GetMapping("/retrieve")
    public List<Key> getOwnedKeys(@RequestParam UUID ownerId){
        System.out.println("RETRIEVING OWNED KEYS BY: " + ownerId);
        return keyService.getKeys(ownerId);
    }

    @PostMapping("/create")
    public Key createNewKey(@RequestBody CreateKeys createKeys){
        System.out.println("CREATE NEW KEY OWNED BY: " + createKeys.getOwnerId());

        Key key = new Key();

        key.setId(UUID.randomUUID());
        key.setKey(Key.generateApiKey());
        key.setOwner_id(createKeys.getOwnerId());
        key.setName(createKeys.getName());

        return keyService.createKey(key);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
