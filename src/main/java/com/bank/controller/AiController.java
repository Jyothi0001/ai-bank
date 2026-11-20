package com.bank.controller;



import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.bank.dto.ChatRequest;
import com.bank.services.AiService;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(
            AiService aiService) {

        this.aiService =
                aiService;
    }

    @PostMapping("/fraud")
    public ResponseEntity<?> fraud(
            @RequestBody
            Map<String, Object> request) {

        try {

            Double amount =
                    request.get("amount") == null
                        ? 0.0
                        : Double.valueOf(
                            request
                                .get("amount")
                                .toString()
                        );

            String location =
                    request.get("location") == null
                        ? "Unknown"
                        : request
                            .get("location")
                            .toString();

            String account =
                    request.get("account") == null
                        ? ""
                        : request
                            .get("account")
                            .toString();

            return ResponseEntity.ok(
                    aiService.fraudDetection(
                            amount,
                            location,
                            account
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

    @PostMapping("/loan-prediction")
    public ResponseEntity<?> loanPrediction(
            @RequestBody
            Map<String, Object> request) {

        try {

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

            Double amount =
                    request.get("amount") == null
                        ? null
                        : Double.valueOf(
                            request
                                .get("amount")
                                .toString()
                        );

            return ResponseEntity.ok(
                    aiService.loanPrediction(
                            income,
                            creditScore,
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

    @PostMapping("/chat")
    public ResponseEntity<?> chat(
            @RequestBody
            ChatRequest request) {

        return ResponseEntity.ok(
                aiService.chat(
                        request.getMessage()
                )
        );
    }

    @PostMapping("/face-detect")
    public ResponseEntity<?> faceDetect(
            @RequestBody
            Map<String, String> request) {

        return ResponseEntity.ok(
                aiService.faceDetection(
                        request.get("image")
                )
        );
    }
}