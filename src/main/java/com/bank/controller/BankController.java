package com.bank.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.model.Account;
import com.bank.model.BankTransaction;
import com.bank.services.BankService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/account/{uid}")
    public ResponseEntity<?> getAccount(
            @PathVariable Long uid) {

        try {

            return ResponseEntity.ok(
                    bankService.getAccount(uid)
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

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(
            @RequestBody Map<String, Object> request) {

        try {

            Long userId =
                    Long.valueOf(
                            request.get("userId").toString()
                    );

            Double amount =
                    Double.valueOf(
                            request.get("amount").toString()
                    );

            return ResponseEntity.ok(
                    bankService.deposit(
                            userId,
                            amount
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

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(
            @RequestBody Map<String, Object> request) {

        try {

            Long userId =
                    Long.valueOf(
                            request.get("userId").toString()
                    );

            Double amount =
                    Double.valueOf(
                            request.get("amount").toString()
                    );

            return ResponseEntity.ok(
                    bankService.withdraw(
                            userId,
                            amount
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

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(
            @RequestBody Map<String, Object> request) {

        try {

            Long userId =
                    Long.valueOf(
                            request.get("userId").toString()
                    );

            String receiverAccount =
                    request.get("receiverAccount")
                            .toString();

            Double amount =
                    Double.valueOf(
                            request.get("amount").toString()
                    );

            String description =
                    request.get("description") == null
                            ? "Money transfer"
                            : request.get("description")
                                    .toString();

            Boolean otpVerified =
                    request.get("otpVerified") != null
                            && Boolean.parseBoolean(
                                    request.get("otpVerified")
                                            .toString()
                            );

            String result =
                    bankService.transfer(
                            userId,
                            receiverAccount,
                            amount,
                            description,
                            otpVerified
                    );

            if ("OTP_REQUIRED".equals(result)) {

                Map<String, Object> response =
                        new HashMap<>();

                response.put(
                        "requiresOtp",
                        true
                );

                response.put(
                        "message",
                        "OTP verification required"
                );

                return ResponseEntity.ok(response);
            }

            return ResponseEntity.ok(
                    Map.of(
                            "requiresOtp",
                            false,
                            "message",
                            result
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

    @GetMapping("/transactions/{uid}")
    public ResponseEntity<List<BankTransaction>>
    getTransactions(
            @PathVariable Long uid) {

        return ResponseEntity.ok(
                bankService.getTransactions(uid)
        );
    }

    @GetMapping("/dashboard/{uid}")
    public ResponseEntity<?> dashboard(
            @PathVariable Long uid) {

        Account account =
                bankService.getAccount(uid);

        List<BankTransaction> transactions =
                bankService.getTransactions(uid);

        Map<String, Object> dashboard =
                new HashMap<>();

        dashboard.put("account", account);
        dashboard.put("transactions", transactions);
        dashboard.put(
                "transactionCount",
                transactions.size()
        );

        return ResponseEntity.ok(dashboard);
    }
}