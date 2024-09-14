package com.ronalc.login.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "usuarios")
public class Users {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "usuario", nullable = false, unique = true)
    private String username;

    @Column(name = "senha", nullable = false)
    private String password;

    @Column(name = "createdAt", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Users() {}

    public Users(String username, String encryptedPassword) {
        this.username = username;
        this.password = encryptedPassword;
    }
}
