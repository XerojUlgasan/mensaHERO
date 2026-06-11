package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.DTO.keys.KeyDetails;
import com.mensahero.mensahero.exception.ApiException;
import com.mensahero.mensahero.model.Key;
import com.mensahero.mensahero.service.impl.KeyServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//TODO:
//

@RestController
@RequestMapping("/api/keys")
public class KeyController {
    KeyServiceImp keyServiceImp;

    public KeyController(KeyServiceImp keyServiceImp) {this.keyServiceImp = keyServiceImp;}

    @GetMapping("/retrieve")
    public List<Key> getOwnedKeys(Authentication auth){
        return keyServiceImp.getKeysOverview((UUID)auth.getPrincipal());
    }

    @PostMapping("/create")
    public Key createNewKey(@RequestBody KeyDetails keyDetails, Authentication auth){
        return keyServiceImp.createKey((UUID)auth.getPrincipal(), keyDetails.getName());
    }

    @PatchMapping("/update")
    public Key updateKey(@RequestBody KeyDetails keyDetails, Authentication auth){
        System.out.println(keyDetails.toString());
        return keyServiceImp.updateKey(
                (UUID)auth.getPrincipal(),
                keyDetails.getKeyId(),
                keyDetails.getStatus(),
                keyDetails.getName()
        );
    }

    @DeleteMapping("/delete/{apiId}")
    public Key deleteKey(Authentication auth, @PathVariable UUID apiId){
        return keyServiceImp.deleteKey((UUID)auth.getPrincipal(), apiId);
    }

    @GetMapping("/test")
    public String test(Authentication auth){
        throw new ApiException("TRSTING", HttpStatus.NOT_ACCEPTABLE);
//        return "test";
    }
}
