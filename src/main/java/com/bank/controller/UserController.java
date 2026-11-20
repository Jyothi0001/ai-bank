package com.bank.controller;



import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.bank.model.User;
import com.bank.services.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService) {

        this.userService =
                userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(
            @PathVariable Long id) {

        try {

            return ResponseEntity.ok(
                    userService.getUser(id)
            );

        } catch (Exception e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>>
    getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        try {

            return ResponseEntity.ok(
                    userService.updateUser(
                            id,
                            user
                    )
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                        Map.of(
                            "message",
                            e.getMessage()
                        )
                    );
        }
    }
}