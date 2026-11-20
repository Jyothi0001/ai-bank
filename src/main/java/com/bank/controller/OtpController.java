package com.bank.controller;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.bank.dto.OtpRequest;
import com.bank.services.OtpService;

import java.util.Map;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    private final OtpService otpService;

    public OtpController(
            OtpService otpService) {

        this.otpService =
                otpService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generate(
            @RequestBody
            Map<String, Object> request) {

        try {

            Long userId =
                    Long.valueOf(
                        request
                            .get("userId")
                            .toString()
                    );

            return ResponseEntity.ok(
                    otpService.generateOtp(
                            userId
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

    @PostMapping("/verify")
    public ResponseEntity<?> verify(
            @RequestBody
            OtpRequest request) {

        try {

            boolean verified =
                    otpService.verifyOtp(
                            request.getUserId(),
                            request.getOtp()
                    );

            if (verified) {

                return ResponseEntity.ok(
                        Map.of(
                            "verified",
                            true,
                            "message",
                            "OTP verified successfully"
                        )
                );
            }

            return ResponseEntity.ok(
                    Map.of(
                        "verified",
                        false,
                        "message",
                        "Invalid OTP"
                    )
            );

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                        Map.of(
                            "verified",
                            false,
                            "message",
                            e.getMessage()
                        )
                    );
        }
    }
}