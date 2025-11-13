package com.example.simplyread_backend.service;

import com.example.simplyread_backend.dto.UserDTO;
import com.example.simplyread_backend.entity.Role;
import com.example.simplyread_backend.entity.User;
import com.example.simplyread_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User registerUser (UserDTO userDTO, String rawPassword) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.CUSTOMER);
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }
}
