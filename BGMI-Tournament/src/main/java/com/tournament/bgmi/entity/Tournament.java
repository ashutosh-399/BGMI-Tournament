package com.tournament.bgmi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tournament")
@Data
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;
    private Double entryFees;

    private Double firstPrize;
    private Double secondPrize;
    private Double thirdPrize;

    private Integer maxSlots;
    private Integer filledSlots = 0;

    private LocalDateTime matchTime;

    private String roomId;
    private String roomPassword;

    private String status = "UPCOMING";
}
