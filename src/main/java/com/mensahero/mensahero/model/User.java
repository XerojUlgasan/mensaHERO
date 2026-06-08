package com.mensahero.mensahero.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(schema = "public", name = "\"Users\"")
public class User {

    @Id
    private UUID id;

    @Column(insertable = false, updatable = false)
    private OffsetDateTime created_at;
    private String email;

    ///////////////////////

    public void setId(UUID id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public OffsetDateTime getCreated_at() {
        return created_at;
    }
}
