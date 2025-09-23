package com.tharun.employee.crud.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;   // who performed the action
    private String action;     // CREATE / UPDATE
    private String entity;     // e.g., Employee
    private LocalDateTime timestamp; // when

    public AuditLog() {}

    public AuditLog(String username, String action, String entity, LocalDateTime timestamp) {
        this.username = username;
        this.action = action;
        this.entity = entity;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getEntity() { return entity; }
    public void setEntity(String entity) { this.entity = entity; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}

