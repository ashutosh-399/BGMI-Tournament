package com.tournament.bgmi.repository;

import com.tournament.bgmi.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, UUID> {

    @Modifying
    @Query("UPDATE Tournament t SET t.filledSlots = t.filledSlots + 1 WHERE t.id = :tournamentId")
    public void incrementFilledSlots(@Param("tournamentId") UUID tournamentId);

    @Modifying
    @Query("UPDATE Tournament t SET t.filledSlots = t.filledSlots - 1 WHERE t.id = :id")
    void decrementFilledSlots(UUID id);
}
