package com.bank.controller;



import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.bank.dto.LoginRequest;
import com.bank.dto.RegisterRequest;
import com.bank.model.User;
import com.bank.services.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {

        this.authService =
                authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody
            RegisterRequest request) {

        try {

            User user =
                    authService.register(
                            request
                    );

            return ResponseEntity.ok(user);

        } catch (Exception e) {

            Map<String, String> response =
                    new HashMap<>();

            response.put(
                    "message",
                    e.getMessage()
            );

            return ResponseEntity
                    .badRequest()
                    .body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody
            LoginRequest request) {

        try {

            return ResponseEntity.ok(
                    authService.login(
                            request
                    )
            );

        } catch (Exception e) {

            Map<String, String> response =
                    new HashMap<>();

            response.put(
                    "message",
                    e.getMessage()
            );

            return ResponseEntity
                    .status(401)
                    .body(response);
        }
    }
}