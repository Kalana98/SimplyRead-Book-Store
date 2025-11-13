package com.example.simplyread_backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private List<Long> bookIds;
}
