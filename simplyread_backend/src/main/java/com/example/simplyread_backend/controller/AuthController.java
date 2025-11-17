package com.example.simplyread_backend.controller;

import com.example.simplyread_backend.dto.AuthRequestDTO;
import com.example.simplyread_backend.dto.AuthRespondDTO;
import com.example.simplyread_backend.dto.UserDTO;
import com.example.simplyread_backend.entity.Role;
import com.example.simplyread_backend.entity.User;
import com.example.simplyread_backend.repository.UserRepository;
import com.example.simplyread_backend.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthRespondDTO> login(@RequestBody AuthRequestDTO dto) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthRespondDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername()))
            return ResponseEntity.badRequest().body("Username already exists");
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode("default123")); // or receive from DTO
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}

