package com.tournament.bgmi.service;

import com.tournament.bgmi.dto.RegistrationDtoForAdminOutPut;
import com.tournament.bgmi.entity.Registration;
import com.tournament.bgmi.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private RegistrationRepo registrationRepo;


    public List<RegistrationDtoForAdminOutPut> getRegistrationByTournament(UUID tournamentId){
        List<Registration> allRegistration =  registrationRepo.findByTournamentId(tournamentId);
        return allRegistration.stream().map(reg -> new RegistrationDtoForAdminOutPut(
                reg.getId(),
                reg.getUser().getEmail(),
                reg.getTournament().getTitle(),
                reg.getPaymentStatus().toString()
        )).toList();
    }
}
