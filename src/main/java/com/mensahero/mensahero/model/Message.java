package com.mensahero.mensahero.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;

@Entity
@Table(schema = "public", name = "\"Messages\"")
public class Message {

    @Id
    String id;

    @Column(insertable = false, updatable = false)
    OffsetDateTime created_at;

    @Column(updatable = false)
    OffsetDateTime sent_at;

    String owner_id;
    String from;
    String to;
    String message;
}