package com.mensahero.mensahero.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(schema = "public", name = "\"Messages\"")
public class Message {

    @Id
    private UUID id;

    @Column(insertable = false, updatable = false, name = "created_at")
    private OffsetDateTime createdAt;

    @Column(insertable = false, name = "sent_at")
    private OffsetDateTime sentAt;

    private String sender;
    private String receiver;
    private String message;

    @Column(insertable = false)
    private String status;

    @Column(name = "api_id")
    private UUID apiId;

    public Message() {
    }

    public Message(String message, String receiver, String sender, UUID apiId) {
        this.id = UUID.randomUUID();
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
        this.apiId = apiId;
    }

    ///////////////////////


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getApi_id() {
        return apiId;
    }

    public void setApi_id(UUID api_key) {
        this.apiId = api_key;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCreated_at(OffsetDateTime created_at) {
        this.createdAt = created_at;
    }

    public void setSent_at(OffsetDateTime sent_at) {
        this.sentAt = sent_at;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getCreated_at() {
        return createdAt;
    }

    public OffsetDateTime getSent_at() {
        return sentAt;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", created_at=" + createdAt +
                ", sent_at=" + sentAt +
                ", api_key='" + apiId + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}