package com.edunex.controller;

import com.edunex.dto.LoginRequest;
import com.edunex.exception.InvalidCredentialsException;
import com.edunex.exception.UserNotFoundException;
import com.edunex.model.RefreshToken;
import com.edunex.security.JwtUtil;
import com.edunex.security.UserDetailsServiceImpl;
import com.edunex.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

        // STEP 1 - Email check
        UserDetails user;
        try {
            user = userDetailsService.loadUserByUsername(request.getEmail());
        } catch (Exception e) {
            throw new UserNotFoundException("Invalid email or password");
        }

        // STEP 2 - Password check
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // STEP 3 - Extract role
        String role = user.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replace("ROLE_", "");

        // STEP 4 - Access token
        String accessToken = jwtUtil.generateToken(user.getUsername(), role);

        // STEP 5 - Refresh token
        RefreshToken refreshToken = refreshTokenService.createToken(user.getUsername());

        return ResponseEntity.ok(Map.of(
                "token", accessToken,
                "refreshToken", refreshToken.getToken(),
                "role", role
        ));
    }

    // 🔹 REFRESH TOKEN
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {

        String refreshToken = body.get("refreshToken");

        RefreshToken rt = refreshTokenService.verifyToken(refreshToken);

        UserDetails user = userDetailsService.loadUserByUsername(rt.getEmail());

        String role = user.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replace("ROLE_","");

        String newAccessToken = jwtUtil.generateToken(user.getUsername(), role);

        return ResponseEntity.ok(Map.of("token", newAccessToken));
    }
}
