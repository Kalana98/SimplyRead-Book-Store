package com.example.simplyread_backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDTO {

    private Long bookId;
    private String name;
    private String author;
    private LocalDate releaseDate;
    private String category;
    private double price;

}
