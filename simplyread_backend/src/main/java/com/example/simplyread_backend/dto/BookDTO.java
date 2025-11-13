package com.example.simplyread_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDTO {

    private Long bookId;
    private String name;
    private String author;
    private LocalDateTime releaseDate;
    private String category;
    private double price;

}
