package com.tournament.bgmi.repository;

import com.tournament.bgmi.entity.Registration;
import com.tournament.bgmi.entity.Tournament;
import com.tournament.bgmi.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegistrationRepo extends JpaRepository<Registration, UUID> {

    List<Registration> findByUserId(UUID id);

    Integer countByTournamentId(UUID tournamentId);

    Boolean existsByUserIdAndTournamentId(UUID userId, UUID tournamentId);

    Boolean existsByUtrNumber(String utrNumber);

    @Query("SELECT r.slotNumber FROM Registration r WHERE r.tournament.id = :tournamentId")
    List<Integer> findUsedSlots(UUID tournamentId);

    List<Registration> findByTournamentId(UUID tournamentId);

    List<Registration> findByPaymentStatus(PaymentStatus status);
}
