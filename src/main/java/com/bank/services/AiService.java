package com.bank.services;



import org.springframework.stereotype.Service;

import com.bank.repo.FraudAlertRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

    private final FraudAlertRepository
            fraudAlertRepository;

    public AiService(
            FraudAlertRepository fraudAlertRepository) {

        this.fraudAlertRepository =
                fraudAlertRepository;
    }

    public Map<String, Object>
    fraudDetection(
            Double amount,
            String location,
            String account) {

        double riskScore = 0.0;

        String reason =
                "Normal transaction";

        boolean fraud = false;

        if (amount != null &&
            amount > 100000) {

            riskScore += 0.60;

            reason =
                    "High transaction amount";
        }

        if (location != null &&
            location.equalsIgnoreCase(
                    "Unknown")) {

            riskScore += 0.25;

            reason =
                    "Unknown transaction location";
        }

        if (riskScore >= 0.70) {

            fraud = true;
        }

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "riskScore",
                riskScore
        );

        response.put(
                "fraud",
                fraud
        );

        response.put(
                "flag",
                fraud
        );

        response.put(
                "reason",
                reason
        );

        return response;
    }

    public Map<String, Object>
    loanPrediction(
            Double income,
            Integer creditScore,
            Double amount) {

        boolean eligible = false;

        double interestRate = 12.5;

        String reason;

        if (income != null &&
            creditScore != null &&
            amount != null) {

            if (creditScore >= 700 &&
                income >= 25000 &&
                amount <= income * 10) {

                eligible = true;

                interestRate = 9.5;

                reason =
                        "Applicant meets basic eligibility criteria";

            } else if (
                    creditScore >= 600 &&
                    income >= 15000) {

                eligible = true;

                interestRate = 11.5;

                reason =
                        "Applicant has moderate eligibility";

            } else {

                reason =
                        "Credit score or income is below the demo threshold";
            }

        } else {

            reason =
                    "Income, credit score and amount are required";
        }

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "eligible",
                eligible
        );

        response.put(
                "eligibility",
                eligible
        );

        response.put(
                "interestRate",
                interestRate
        );

        response.put(
                "reason",
                reason
        );

        return response;
    }

    public Map<String, Object>
    chat(String message) {

        String reply;

        if (message == null ||
            message.isBlank()) {

            reply =
                    "Hello! How can I help you with your banking account?";
        }

        else if (
                message
                    .toLowerCase()
                    .contains("balance")) {

            reply =
                    "You can check your current balance from the Dashboard or Account page.";
        }

        else if (
                message
                    .toLowerCase()
                    .contains("transfer")) {

            reply =
                    "To transfer money, open Transfer, enter the receiver account and amount, then complete OTP verification.";
        }

        else if (
                message
                    .toLowerCase()
                    .contains("loan")) {

            reply =
                    "You can use the Loan section to check demo loan eligibility based on income and credit score.";
        }

        else if (
                message
                    .toLowerCase()
                    .contains("otp")) {

            reply =
                    "OTP is required to verify sensitive banking operations such as transfers.";
        }

        else if (
                message
                    .toLowerCase()
                    .contains("fraud")) {

            reply =
                    "The AI fraud module calculates a demo risk score and flags high-risk transactions.";
        }

        else {

            reply =
                    "I can help you with balance, transfers, OTP, loans and fraud detection.";
        }

        return Map.of(
                "message",
                message == null ? "" : message,

                "response",
                reply
        );
    }

    public Map<String, Object>
    faceDetection(String image) {

        boolean detected =
                image != null &&
                !image.isBlank();

        return Map.of(
                "faceDetected",
                detected,

                "message",
                detected
                    ? "Face detected successfully"
                    : "No face image received"
        );
    }
}