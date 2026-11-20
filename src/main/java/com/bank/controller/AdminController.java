package com.bank.controller;



import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.bank.services.AdminService;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(
            AdminService adminService) {

        this.adminService =
                adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> users() {

        return ResponseEntity.ok(
                adminService.getUsers()
        );
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> transactions() {

        return ResponseEntity.ok(
                adminService.getTransactions()
        );
    }

    @GetMapping("/fraud-alerts")
    public ResponseEntity<?> fraudAlerts() {

        return ResponseEntity.ok(
                adminService.getFraudAlerts()
        );
    }

    @GetMapping("/loans")
    public ResponseEntity<?> loans() {

        return ResponseEntity.ok(
                adminService.getLoans()
        );
    }

    @PutMapping("/loans/{id}/{status}")
    public ResponseEntity<?> updateLoanStatus(
            @PathVariable Long id,
            @PathVariable String status) {

        try {

            return ResponseEntity.ok(
                    adminService
                        .updateLoanStatus(
                            id,
                            status
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