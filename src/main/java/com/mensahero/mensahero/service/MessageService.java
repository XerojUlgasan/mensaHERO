package com.mensahero.mensahero.service;

import com.mensahero.mensahero.model.Message;
import com.mensahero.mensahero.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByApiId(UUID apiId){
        return messageRepository.findByApiId(apiId);
    }
}
