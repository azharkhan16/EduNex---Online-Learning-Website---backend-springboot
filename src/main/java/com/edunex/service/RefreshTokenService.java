package com.edunex.service;

import com.edunex.model.RefreshToken;

public interface RefreshTokenService {
    RefreshToken createToken(String email);
    RefreshToken verifyToken(String token);
}
