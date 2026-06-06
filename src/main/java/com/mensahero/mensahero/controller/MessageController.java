package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.DTO.messages.CreateMessage;
import com.mensahero.mensahero.model.Key;
import com.mensahero.mensahero.model.Message;
import com.mensahero.mensahero.service.KeyService;
import com.mensahero.mensahero.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

//TODO:
//getByFrom
//getByTo
//getByKey
//getByOwner

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    MessageService messageService;
    KeyService keyService;

    public MessageController(MessageService messageService, KeyService keyService) {
        this.messageService = messageService;
        this.keyService = keyService;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @PostMapping("/create")
    public Message createMessage(@RequestBody CreateMessage createMessage){
        Key key = keyService.checkKeyExistence(createMessage.getApiKey());

        if(key == null){ // API KEY DOES NOT EXIST
            System.out.println("API DOES NOT EXIST: " + createMessage.getApiKey());
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid API key"
            );
        }

        Message message = new Message();

        message.setId(UUID.randomUUID());
        message.setApi_id(key.getId()); // SHOULD BE VALIDATED FIRST IF API KEY EXISTS
        message.setSender(createMessage.getFrom());
        message.setReceiver(createMessage.getTo());
        message.setMessage(createMessage.getMessage());

        return messageService.createMessage(message);
    }

    @GetMapping("/retrieve")
    public List<Message> getAllMessagesByKey(@RequestParam String apiKey){
        System.out.println("Retrieving all messages by key: " + apiKey);
        Key key = keyService.checkKeyExistence(apiKey);

        if(key == null){ // API KEY DOES NOT EXIST
            System.out.println("API DOES NOT EXIST: " + apiKey);
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid API key"
            );
        }

        return messageService.getMessagesByApiId(key.getId());
    }
}
