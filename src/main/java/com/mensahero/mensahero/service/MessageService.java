package com.mensahero.mensahero.service;

import com.mensahero.mensahero.DTO.messages.CreateMessage;
import com.mensahero.mensahero.Enums.DateFilters;
import com.mensahero.mensahero.Enums.MessageStatus;
import com.mensahero.mensahero.model.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    public List<Message> getRecipientsHistory(UUID ownerId, UUID apiId, DateFilters dateFilters, int page, int pageSize);
    public List<Message> getMessagesByRecipients(UUID ownerId, UUID apiId, DateFilters dateFilters, String recipient, MessageStatus messageStatus, int page, int pageSize);
    public Message createMessage(UUID ownerId, CreateMessage createMessage);
}
