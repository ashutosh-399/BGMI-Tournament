package com.tournament.bgmi.service;

import com.tournament.bgmi.entity.Tournament;
import com.tournament.bgmi.repository.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepo tournamentRepo;

    public Boolean createTournament(Tournament tournament){
        Tournament t = tournamentRepo.save(tournament);
        return t.getId() != null;
    }


    public List<Tournament> getAllTournament(){
        List<Tournament> t = tournamentRepo.findAll();
        return t;
    }
}
