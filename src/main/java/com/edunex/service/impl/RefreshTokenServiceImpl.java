package com.edunex.service.impl;

import com.edunex.model.RefreshToken;
import com.edunex.repository.RefreshTokenRepository;
import com.edunex.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository repo;

    @Override
    public RefreshToken createToken(String email) {
        return repo.save(
                RefreshToken.builder()
                        .token(UUID.randomUUID().toString())
                        .email(email)
                        .expiry(Instant.now().plus(Duration.ofDays(30))) // 7 days expiry
                        .build()
        );
    }

    @Override
    public RefreshToken verifyToken(String token) {
        RefreshToken rt = repo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (rt.getExpiry().isBefore(Instant.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        return rt;
    }
}
