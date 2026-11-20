package com.bank.controller;



import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.bank.services.LoanService;

import java.util.Map;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(
            LoanService loanService) {

        this.loanService =
                loanService;
    }

    @GetMapping("/{uid}")
    public ResponseEntity<?> getLoans(
            @PathVariable Long uid) {

        return ResponseEntity.ok(
                loanService.getLoans(uid)
        );
    }

    @PostMapping("/predict")
    public ResponseEntity<?> predict(
            @RequestBody
            Map<String, Object> request) {

        try {

            Long userId =
                    request.get("userId") == null
                        ? null
                        : Long.valueOf(
                            request
                                .get("userId")
                                .toString()
                        );

            Double amount =
                    request.get("amount") == null
                        ? null
                        : Double.valueOf(
                            request
                                .get("amount")
                                .toString()
                        );

            Integer tenure =
                    request.get("tenure") == null
                        ? null
                        : Integer.valueOf(
                            request
                                .get("tenure")
                                .toString()
                        );

            Double income =
                    request.get("income") == null
                        ? null
                        : Double.valueOf(
                            request
                                .get("income")
                                .toString()
                        );

            Integer creditScore =
                    request.get("creditScore") == null
                        ? null
                        : Integer.valueOf(
                            request
                                .get("creditScore")
                                .toString()
                        );

            return ResponseEntity.ok(
                    loanService.predictLoan(
                            userId,
                            amount,
                            tenure,
                            income,
                            creditScore
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