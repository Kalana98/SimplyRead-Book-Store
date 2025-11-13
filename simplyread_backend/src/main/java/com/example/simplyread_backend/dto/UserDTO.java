package com.example.simplyread_backend.dto;

import com.example.simplyread_backend.entity.Role;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private Role role;
}
