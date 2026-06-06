package com.mensahero.mensahero.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.UUID;

@Entity
@Table(schema = "public", name = "\"Keys\"")
public class Key {

    @Id
    private UUID id;

    @Column(insertable = false, updatable = false)
    private OffsetDateTime created_at;

    @Column(insertable = false)
    private OffsetDateTime expires_at;

    private String key;

    @Column(insertable = false)
    private String status;

    @Column(name = "owner_id")
    private UUID ownerId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String generateApiKey() {

        final SecureRandom secureRandom = new SecureRandom();

        byte[] bytes = new byte[15]; // ~20 chars after encoding
        secureRandom.nextBytes(bytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes)
                .substring(0, 20);
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getCreated_at() {
        return created_at;
    }

    public OffsetDateTime getExpires_at() {
        return expires_at;
    }

    public String getKey() {
        return key;
    }

    public UUID getOwner_id() {
        return ownerId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCreated_at(OffsetDateTime created_at) {
        this.created_at = created_at;
    }

    public void setExpires_at(OffsetDateTime expires_at) {
        this.expires_at = expires_at;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setOwner_id(UUID ownerId) {
        this.ownerId = ownerId;
    }
}

