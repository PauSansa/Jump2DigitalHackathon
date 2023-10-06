package com.sansa.jumpdigitalhackathon.response;

import lombok.Builder;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
