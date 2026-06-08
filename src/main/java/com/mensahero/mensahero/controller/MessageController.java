package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.DTO.dashboard.MessagesByKey;
import com.mensahero.mensahero.DTO.messages.CreateMessage;
import com.mensahero.mensahero.model.Key;
import com.mensahero.mensahero.model.Message;
import com.mensahero.mensahero.service.impl.KeyServiceImp;
import com.mensahero.mensahero.service.impl.MessageServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    MessageServiceImp messageServiceImp;
    KeyServiceImp keyServiceImp;

    public MessageController(MessageServiceImp messageServiceImp, KeyServiceImp keyServiceImp) {
        this.messageServiceImp = messageServiceImp;
        this.keyServiceImp = keyServiceImp;
    }

    @GetMapping("/test")
    public List<MessagesByKey> test(){
        List<UUID> apiIds = new ArrayList<>();
        apiIds.add(UUID.fromString("62429b98-c27e-4be6-81d5-7dccd52c33f5"));
        apiIds.add(UUID.fromString("6566fbff-a6d8-4fad-b92b-4a3c83a69a04"));

        return messageServiceImp.getMessagesByKey(apiIds);
    }

    @PostMapping("/create")
    public Message createMessage(@RequestBody CreateMessage createMessage){
        return messageServiceImp.createMessage(null, createMessage);
    }

    @GetMapping("/retrieveRecipients")
    public List<Message> getAllRecipients(@RequestParam UUID apiId){
        return messageServiceImp.getRecipientsHistory(null, apiId, null, 1, 10);
    }

    @GetMapping("/retrieveMessagesByRecipient")
    public List<Message> getAllMessages(@RequestParam UUID apiId,
                                        @RequestParam String recipient,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int pageSize){

        return messageServiceImp.getMessagesByRecipients(null, apiId, null, recipient, null, page, pageSize);
    }
}
