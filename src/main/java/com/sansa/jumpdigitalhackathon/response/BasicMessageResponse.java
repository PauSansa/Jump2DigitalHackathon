package com.sansa.jumpdigitalhackathon.response;

import lombok.Data;

@Data
public class BasicMessageResponse {
    private String message;

    public BasicMessageResponse(String message) {
        this.message = message;
    }
}
