package com.tournament.bgmi.controller;

import com.tournament.bgmi.entity.Users;
import com.tournament.bgmi.service.JwtService;
import com.tournament.bgmi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users users){
        Boolean status = userService.register(users);
        if (status){
            return new ResponseEntity<>("User Registered successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User Registered Failed", HttpStatus.BAD_REQUEST);
    }





    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users users) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword());

        try {
            Authentication authentication = authManager.authenticate(token);
            Boolean status = authentication.isAuthenticated();

            if (status) {
                String jwtToken = jwtService.generateToken(
                        users.getEmail(),
                        users.getRole()
                );
                return new ResponseEntity<>(jwtToken, HttpStatus.OK);
            }
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("Login Failed", HttpStatus.BAD_REQUEST);
    }


}
