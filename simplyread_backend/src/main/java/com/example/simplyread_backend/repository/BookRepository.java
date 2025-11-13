package com.example.simplyread_backend.repository;

import com.example.simplyread_backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
