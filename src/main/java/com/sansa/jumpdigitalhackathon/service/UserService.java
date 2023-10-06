package com.sansa.jumpdigitalhackathon.service;

import com.sansa.jumpdigitalhackathon.model.Role;
import com.sansa.jumpdigitalhackathon.model.User;
import com.sansa.jumpdigitalhackathon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository data;
    private final PasswordEncoder encoder;

    public Optional<User> createUser(String username, String password){
        User user = User.builder()
                .id(UUID.randomUUID())
                .username(username)
                .role(Role.USER)
                .password(encoder.encode(password))
                .createdAt(new Date())
                .build();

        return saveUser(user);
    }

    public Optional<User> saveUser(User user){
        if(data.existsByUsername(user.getUsername())){
            return Optional.empty();
        }
        return Optional.of(data.save(user));
    }

    public Optional<User> getUser(String username, String password) {
        Optional<User> user = data.findByUsername(username);
        if(user.isEmpty()){
            return Optional.empty();
        }

        if(!encoder.matches(password, user.get().getPassword())){
            return Optional.empty();
        }

        return user;
    }
}
