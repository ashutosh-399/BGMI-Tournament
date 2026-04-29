package com.tournament.bgmi.service;

import com.tournament.bgmi.configuration.PwdEncoderConfig;
import com.tournament.bgmi.entity.Users;
import com.tournament.bgmi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PwdEncoderConfig pwdEncoder;



    public Boolean register(Users user){
        String encodedPwd = pwdEncoder.passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPwd);

        Users saveduser = userRepo.save(user);
        return saveduser.getId() != null;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with this email"));
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public List<Users> getAllUser(){
        List<Users> allUser = userRepo.findAll();
        return allUser;
    }
}
