package com.tournament.bgmi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String phone;
    private String password;

    @Column(unique = true)
    private String email;
    private String bgmiId;
    private String bgmiUsername;

    private String role = "PLAYER";
}
