package com.mensahero.mensahero.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;

@Entity
@Table(schema = "public", name = "\"Keys\"")
public class Key {

    @Id
    long id;

    @Column(insertable = false, updatable = false)
    OffsetDateTime created_at;

    @Column(updatable = false)
    OffsetDateTime expires_at;

    String key;
    String owner_id;
}

