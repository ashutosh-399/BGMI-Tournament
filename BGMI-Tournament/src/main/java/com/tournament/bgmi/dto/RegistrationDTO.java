package com.tournament.bgmi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RegistrationDTO {

    private UUID userId;
    private UUID tournamentId;
    private String utrNumber;
}
