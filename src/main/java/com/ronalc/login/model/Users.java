package com.ronalc.login.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Users {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(name = "usuario")
    private String username;
    @Column(name = "senha")
    private String password;
}
