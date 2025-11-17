package com.example.simplyread_backend.dto;

import lombok.Data;

@Data
public class AuthRespondDTO {

    private String token;


    public AuthRespondDTO(String token) {
    }
}
