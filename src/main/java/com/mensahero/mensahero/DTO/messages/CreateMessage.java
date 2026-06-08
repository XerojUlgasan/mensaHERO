package com.mensahero.mensahero.DTO.messages;

import java.util.UUID;

public class CreateMessage {
    private UUID apiKey;
    private String from;
    private String to;
    private String message;

    public UUID getApiKey() {
        return apiKey;
    }

    public void setApiKey(UUID apiKey) {
        this.apiKey = apiKey;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
