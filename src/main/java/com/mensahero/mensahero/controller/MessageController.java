package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.DTO.dashboard.DashboardCount;
import com.mensahero.mensahero.DTO.dashboard.MessagesByKey;
import com.mensahero.mensahero.DTO.dashboard.MessagesOverTime;
import com.mensahero.mensahero.DTO.messages.CreateMessage;
import com.mensahero.mensahero.Enums.DateFilters;
import com.mensahero.mensahero.exception.ApiException;
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
    public String test(){
        throw new ApiException("TEST", HttpStatus.NOT_FOUND);
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
