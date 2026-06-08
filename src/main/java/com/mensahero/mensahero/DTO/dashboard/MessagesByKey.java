package com.mensahero.mensahero.DTO.dashboard;

import java.util.UUID;

public class MessagesByKey{
    private UUID apiId;
    private String name;
    private long messageCount;

    public MessagesByKey() {
    }

    public MessagesByKey(UUID apiId, String name, long messageCount) {
        this.apiId = apiId;
        this.name = name;
        this.messageCount = messageCount;
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

    public long getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(long messageCount) {
        this.messageCount = messageCount;
    }
}
