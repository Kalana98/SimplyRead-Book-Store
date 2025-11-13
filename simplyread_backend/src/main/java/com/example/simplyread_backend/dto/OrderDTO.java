package com.example.simplyread_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private Long orderId;
    private String username;
    private List<BookDTO> books;
    private double totalPrice;
    private LocalDateTime orderDate;

}
