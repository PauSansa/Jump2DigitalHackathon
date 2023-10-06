package com.sansa.jumpdigitalhackathon.service;

import com.sansa.jumpdigitalhackathon.model.User;
import com.sansa.jumpdigitalhackathon.request.AuthRequest;
import com.sansa.jumpdigitalhackathon.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;

    public Optional<String> register(AuthRequest rr){
        Optional<User> user = userService.createUser(rr.getUsername(), rr.getPassword());

        return user.map(value -> jwtService.generateToken(value, new HashMap<>()));
    }

    public Optional<String> login(AuthRequest ar) {
        Optional<User> user = userService.getUser(ar.getUsername(), ar.getPassword());

        return user.map(value -> jwtService.generateToken(value, new HashMap<>()));
    }
}
