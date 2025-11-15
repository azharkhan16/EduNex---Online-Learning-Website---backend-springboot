package com.edunex.controller;

import com.edunex.dto.LoginRequest;
import com.edunex.exception.InvalidCredentialsException;
import com.edunex.exception.UserNotFoundException;
import com.edunex.security.JwtUtil;
import com.edunex.security.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

        // STEP 1 - email check
        UserDetails userDetails = null;
        try {
            userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        } catch (Exception e) {
            throw new UserNotFoundException("Invalid email or password");
        }

        // STEP 2 - password check
        if (!new BCryptPasswordEncoder().matches(request.getPassword(), userDetails.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // STEP 3 - role extract
        String role = userDetails.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replace("ROLE_", "");

        // STEP 4 → generate token
        String token = jwtUtil.generateToken(userDetails.getUsername(), role);

        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "role", role
                )
        );
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//
//        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
//        String role = user.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
//        String token = jwtUtil.generateToken(user.getUsername(), role);
//
//        return ResponseEntity.ok(Map.of("token", token, "role", role));
//    }
}
