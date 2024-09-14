package com.ronalc.login.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "usuarios")
public class Users {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(name = "usuario")
    private String username;
    @Column(name = "senha")
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Users(String username, String encryptedPassword) {
    }
}
