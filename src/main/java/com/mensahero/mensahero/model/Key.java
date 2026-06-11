package com.mensahero.mensahero.model;

import com.mensahero.mensahero.Enums.KeyStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", columnDefinition = "api_key_status")
    private KeyStatus status;

    @Column(name = "owner_id")
    private UUID ownerId;

    @Column(insertable = false)
    private OffsetDateTime last_used;

    private String name;

    ///////////////////////


    public static String generateApiKey() {

        final SecureRandom secureRandom = new SecureRandom();

        byte[] bytes = new byte[15]; // ~20 chars after encoding
        secureRandom.nextBytes(bytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes)
                .substring(0, 20);
    }

    public static Key createKey(UUID ownerId, String name){
        Key  key = new Key();

        key.setId(UUID.randomUUID());
        key.setOwner_id(ownerId);
        key.setName(name);
        key.setStatus(KeyStatus.ACTIVE);
        key.setKey(Key.generateApiKey());

        return key;
    }

    ///

    public OffsetDateTime getLast_used() {
        return last_used;
    }

    public void setLast_used(OffsetDateTime last_used) {
        this.last_used = last_used;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KeyStatus getStatus() {
        return status;
    }

    public void setStatus(KeyStatus status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", expires_at=" + expires_at +
                ", key='" + key + '\'' +
                ", status=" + status +
                ", ownerId=" + ownerId +
                ", last_used=" + last_used +
                ", name='" + name + '\'' +
                '}';
    }
}

