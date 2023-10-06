package com.sansa.jumpdigitalhackathon.controller;

import com.sansa.jumpdigitalhackathon.exception.SkinNotFoundException;
import com.sansa.jumpdigitalhackathon.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SkinNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(SkinNotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponse("Skin not found"));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handle(ExpiredJwtException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse("Token expired"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }
}
