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

    @Column(insertable = false, updatable = false)
    private OffsetDateTime created_at;

    @Column(insertable = false)
    private OffsetDateTime sent_at;

    @Column(name = "api_id")
    private UUID apiId;
    private String sender;
    private String receiver;
    private String message;

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
        this.created_at = created_at;
    }

    public void setSent_at(OffsetDateTime sent_at) {
        this.sent_at = sent_at;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getCreated_at() {
        return created_at;
    }

    public OffsetDateTime getSent_at() {
        return sent_at;
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
                ", created_at=" + created_at +
                ", sent_at=" + sent_at +
                ", api_key='" + apiId + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}