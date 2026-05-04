package com.tournament.bgmi.entity;

import com.tournament.bgmi.enums.PaymentStatus;
import com.tournament.bgmi.enums.RegistrationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(
        name = "registration",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tournament_id" , "slot_number"}))
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String utrNumber;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime registeredAt;

    @Column(nullable = false)
    private Integer slotNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
