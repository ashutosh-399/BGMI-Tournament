package com.tournament.bgmi.service;

import com.tournament.bgmi.dto.AdminVerifyRequest;
import com.tournament.bgmi.entity.Registration;
import com.tournament.bgmi.entity.Tournament;
import com.tournament.bgmi.entity.Users;
import com.tournament.bgmi.enums.PaymentStatus;
import com.tournament.bgmi.enums.RegistrationStatus;
import com.tournament.bgmi.repository.RegistrationRepo;
import com.tournament.bgmi.repository.TournamentRepo;
import com.tournament.bgmi.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RegistrationService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TournamentRepo tournamentRepo;
    @Autowired
    private RegistrationRepo registrationRepo;


    @Transactional
    public String register(UUID tournamentId, String utrNumber){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Fetch user
        Users user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch tournament
        Tournament tournament = tournamentRepo.findById(tournamentId).orElseThrow(() ->
                new RuntimeException("Tournament not found"));


        // Check duplicate Registration
        if (registrationRepo.existsByUserIdAndTournamentId(user.getId(), tournament.getId())){
            return "Already registered";
        }

        //Check Duplicate Utr Number
        if (registrationRepo.existsByUtrNumber(utrNumber)){
            return "Utr Number Already present";
        }

        // Check for slot availability
        Integer registeredCount = registrationRepo.countByTournamentId(tournamentId);
        if (registeredCount >= tournament.getMaxSlots()){
            return "Slot Full";
        }


        //Slot alloting
        List<Integer> usedSlot = registrationRepo.findUsedSlots(tournamentId);

        Integer maxSlot = tournament.getMaxSlots();
        Integer slotNumber = -1;

        for (int i=1; i<=maxSlot; i++){
            if (!usedSlot.contains(i)){
                slotNumber=i;
                break;
            }
        }

        if (slotNumber == -1){
            return "No slots available";
        }




        //Save Registration
        Registration regd = new Registration();
        regd.setUser(user);
        regd.setTournament(tournament);
        regd.setUtrNumber(utrNumber);
        regd.setRegistrationStatus(RegistrationStatus.PENDING);
        regd.setPaymentStatus(PaymentStatus.PENDING);
        regd.setRegisteredAt(LocalDateTime.now());
        regd.setSlotNumber(slotNumber);

        try {
            Registration registration = registrationRepo.save(regd);
            tournamentRepo.incrementFilledSlots(tournamentId);
            return "Registration submitted. Awaiting payment verification.";
        } catch (DataIntegrityViolationException e) {
            return "Utr already used";
        }
    }



    // Admin Get Pending Payments
    public List<Registration> getPendingPayments(){
        return registrationRepo.findByPaymentStatus(PaymentStatus.PENDING);
    }



    //Admin Verify Payments
    public String verifyPayment(UUID id, AdminVerifyRequest request){
          Registration reg = registrationRepo.findById(id).orElseThrow(() ->
                  new RuntimeException("User not found"));

          if (reg.getPaymentStatus() != PaymentStatus.PENDING){
              throw new RuntimeException("Already verifies");
          }

          PaymentStatus status = PaymentStatus.valueOf(request.getPaymentStatus().toUpperCase());
          if (status == PaymentStatus.SUCCESS){
              reg.setPaymentStatus(PaymentStatus.SUCCESS);
              reg.setRegistrationStatus(RegistrationStatus.CONFIRMED);
          }else {
              reg.setPaymentStatus(PaymentStatus.FAILED);
              reg.setRegistrationStatus(RegistrationStatus.REJECTED);
          }
          registrationRepo.save(reg);
          return "Verification Complete";
    }





    public List<Registration> getMyRegistration(){
        //Extract user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));

        List<Registration> registrationsList = registrationRepo.findByUserId(user.getId());
        return registrationsList;
    }
}



