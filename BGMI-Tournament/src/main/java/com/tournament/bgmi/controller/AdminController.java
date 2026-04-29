package com.tournament.bgmi.controller;

import com.tournament.bgmi.entity.Tournament;
import com.tournament.bgmi.entity.Users;
import com.tournament.bgmi.service.TournamentService;
import com.tournament.bgmi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private UserService userService;



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

}
