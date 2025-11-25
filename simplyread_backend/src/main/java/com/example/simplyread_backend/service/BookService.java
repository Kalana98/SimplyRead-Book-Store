package com.example.simplyread_backend.service;

import com.example.simplyread_backend.dto.BookDTO;
import com.example.simplyread_backend.entity.Book;
import com.example.simplyread_backend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookDTO addBook(BookDTO bookDTO){

        Book book = new Book();
        BeanUtils.copyProperties(bookDTO,book);
        bookRepository.save(book);

        BookDTO bDTO = new BookDTO();
        BeanUtils.copyProperties(book,bDTO);
        return bDTO;
    }

    public BookDTO updateBook(Long bookId, BookDTO bookDTO){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        BeanUtils.copyProperties(bookDTO,book);
        bookRepository.save(book);

        BookDTO bDTO = new BookDTO();
        BeanUtils.copyProperties(book,bDTO);
        return bDTO;
    }

    public void deleteBook(Long bookId){
        bookRepository.deleteById(bookId);
    }

    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream()
                .map(book -> {
                    BookDTO bookDTO = new BookDTO();
                    BeanUtils.copyProperties(book,bookDTO);
                    return bookDTO;
                }).collect(Collectors.toList());
    }
}
