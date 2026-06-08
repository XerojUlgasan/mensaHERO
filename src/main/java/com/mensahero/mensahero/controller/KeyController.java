package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.DTO.keys.KeyDetails;
import com.mensahero.mensahero.service.impl.KeyServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//TODO:
//

@RestController
@RequestMapping("/api/keys")
public class KeyController {
    KeyServiceImp keyServiceImp;

    public KeyController(KeyServiceImp keyServiceImp) {this.keyServiceImp = keyServiceImp;}

    @GetMapping("/retrieve")
    public void getOwnedKeys(@RequestParam UUID ownerId){
        return;
    }

    @PostMapping("/create")
    public void createNewKey(@RequestBody KeyDetails keyDetails){
        return;
    }

    @PatchMapping("/update")
    public void updateKey(@RequestBody KeyDetails keyDetails){
        return;
    }

    @DeleteMapping("/delete")
    public void deleteKey(@RequestParam UUID ownerId, @RequestParam UUID apiId){
        return;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
