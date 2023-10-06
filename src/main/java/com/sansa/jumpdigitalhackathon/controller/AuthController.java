package com.sansa.jumpdigitalhackathon.controller;

import com.sansa.jumpdigitalhackathon.request.AuthRequest;
import com.sansa.jumpdigitalhackathon.response.AuthResponse;
import com.sansa.jumpdigitalhackathon.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @RequestMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest ar){
        if(!isRequestValid(ar)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }

        Optional<String> token = authService.register(ar);
        if(token.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already registered");
        }

        return ResponseEntity.ok(new AuthResponse(token.get()));
    }

    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest ar){
        if(!isRequestValid(ar)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }

        Optional<String> token = authService.login(ar);
        if(token.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return ResponseEntity.ok(new AuthResponse(token.get()));
    }

    public boolean isRequestValid(AuthRequest ar){
        if(ar.getUsername() == null || ar.getUsername().isEmpty()){
            return false;
        }

        if(ar.getPassword() == null || ar.getPassword().isEmpty()){
            return false;
        }

        return true;
    }
}
