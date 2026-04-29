package com.tournament.bgmi.repository;

import com.tournament.bgmi.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, UUID> {

}
