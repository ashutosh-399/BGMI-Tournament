package com.tournament.bgmi.controller;

import com.tournament.bgmi.dto.AdminVerifyRequest;
import com.tournament.bgmi.dto.RegistrationDtoForAdminOutPut;
import com.tournament.bgmi.entity.Registration;
import com.tournament.bgmi.entity.Tournament;
import com.tournament.bgmi.entity.Users;
import com.tournament.bgmi.service.AdminService;
import com.tournament.bgmi.service.RegistrationService;
import com.tournament.bgmi.service.TournamentService;
import com.tournament.bgmi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RegistrationService registrationService;



    @PostMapping("/createTournament")
    public ResponseEntity<String> createTournament(@RequestBody Tournament tournament){
        Boolean status = tournamentService.createTournament(tournament);
        if (status){
            return new ResponseEntity<>("Tournament Created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/showAllUser")
    public ResponseEntity<List<Users>> showAllUsers(){
        List<Users> allUsers = userService.getAllUser();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }


    @PostMapping("/getAllRegisterForTournament/{tournamentId}")
    public ResponseEntity<List<RegistrationDtoForAdminOutPut>> getAllRegisterForTournament(
            @PathVariable UUID tournamentId) {
        List<RegistrationDtoForAdminOutPut> list = adminService.getRegistrationByTournament(tournamentId);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }



    @GetMapping("/pending")
    public List<Registration> getPending() {
        List<Registration> r = registrationService.getPendingPayments();
        return r;
    }


    // 👨‍💼 ADMIN: VERIFY
    @PutMapping("/verify/{id}")
    public String verify(@PathVariable UUID id, @RequestBody AdminVerifyRequest request) {
            return registrationService.verifyPayment(id, request);

    }
}
