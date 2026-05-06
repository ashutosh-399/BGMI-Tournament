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

import java.util.Map;

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
    public ResponseEntity<Map<String, String>> register(@RequestBody Users users){
        Boolean status = userService.register(users);
        if (status){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of("message", "User registered success"));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "User Registered Failed"));
    }





    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Users users) {
        System.out.println("LOGIN HIT");
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword());

        try {
            Authentication authentication = authManager.authenticate(token);
            Boolean status = authentication.isAuthenticated();

            if (status) {
                String role = authentication.getAuthorities().iterator().next().getAuthority();
                String jwtToken = jwtService.generateToken(
                        authentication.getName(),
                        role
                );
                return new ResponseEntity<>(Map.of("token", jwtToken), HttpStatus.OK);
            }
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid Cridential"));
        }
        return
                ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("message", "Login failed"));
    }


}
