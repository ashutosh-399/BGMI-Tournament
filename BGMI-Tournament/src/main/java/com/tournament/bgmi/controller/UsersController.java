package com.tournament.bgmi.controller;

import com.tournament.bgmi.entity.Tournament;
import com.tournament.bgmi.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {


    @Autowired
    private TournamentService tournamentService;




    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome from usercontroller";
    }


    @GetMapping("/showAllTournament")
    public ResponseEntity<List<Tournament>> getAllTournament(){
        List<Tournament> tournaments = tournamentService.getAllTournament();
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }


}
