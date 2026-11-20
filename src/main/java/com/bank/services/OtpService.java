package com.bank.services;



import org.springframework.stereotype.Service;

import com.bank.model.Otp;
import com.bank.repo.OtpRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private final OtpRepository otpRepository;

    public OtpService(
            OtpRepository otpRepository) {

        this.otpRepository =
                otpRepository;
    }

    public Map<String, Object>
    generateOtp(Long userId) {

        String otp =
                String.format(
                        "%06d",
                        new Random()
                            .nextInt(1000000)
                );

        Otp otpEntity =
                new Otp();

        otpEntity.setUserId(userId);

        otpEntity.setOtp(otp);

        otpEntity.setCreatedAt(
                LocalDateTime.now()
        );

        otpEntity.setVerified(false);

        otpRepository.save(
                otpEntity
        );

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "message",
                "OTP generated successfully"
        );

        response.put(
                "otp",
                otp
        );

        response.put(
                "expiresIn",
                300
        );

        return response;
    }

    public boolean verifyOtp(
            Long userId,
            String otp) {

        Otp savedOtp =
                otpRepository
                    .findTopByUserIdOrderByCreatedAtDesc(
                            userId
                    )
                    .orElseThrow(() ->
                        new RuntimeException(
                            "OTP not found"
                        )
                    );

        if (savedOtp.isVerified()) {

            return false;
        }

        if (savedOtp.getCreatedAt()
                .plusMinutes(5)
                .isBefore(
                    LocalDateTime.now()
                )) {

            throw new RuntimeException(
                    "OTP expired"
            );
        }

        if (!savedOtp.getOtp()
                .equals(otp)) {

            return false;
        }

        savedOtp.setVerified(true);

        otpRepository.save(
                savedOtp
        );

        return true;
    }
}