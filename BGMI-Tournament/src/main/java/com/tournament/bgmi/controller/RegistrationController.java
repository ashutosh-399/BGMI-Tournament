package com.tournament.bgmi.controller;

import com.tournament.bgmi.dto.RegistrationDTO;
import com.tournament.bgmi.entity.Registration;
import com.tournament.bgmi.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/registerForMatch")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegistrationDTO registrationDTO){
        String response = registrationService.register(
                registrationDTO.getTournamentId(), registrationDTO.getUtrNumber());

        if (response.equals("Already registered") || response.equals("Slot Full")
                || response.equals("Utr Number Already present") || response.equals("Utr already used") ||
                response.equals("No slots available")){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", response));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", response));
    }


    @GetMapping("getMyRegistration")
    public ResponseEntity<List<Registration>> getMyRegistrations(){
        List<Registration> rList = registrationService.getMyRegistration();
        return new ResponseEntity<>(rList, HttpStatus.OK);
    }
}
