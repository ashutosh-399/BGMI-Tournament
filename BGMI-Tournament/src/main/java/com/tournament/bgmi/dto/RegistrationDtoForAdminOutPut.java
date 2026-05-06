package com.tournament.bgmi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDtoForAdminOutPut {

    private UUID id;
    private String username;
    private String tournamentName;
    private String status;
}
