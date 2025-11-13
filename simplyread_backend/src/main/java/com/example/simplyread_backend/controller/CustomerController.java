package com.example.simplyread_backend.controller;

import com.example.simplyread_backend.dto.BookDTO;
import com.example.simplyread_backend.dto.OrderDTO;
import com.example.simplyread_backend.dto.OrderRequestDTO;
import com.example.simplyread_backend.entity.User;
import com.example.simplyread_backend.service.BookService;
import com.example.simplyread_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final BookService bookService;
    private final OrderService orderService;

    @GetMapping("/getAllBooks")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/order")
    public OrderDTO placeOrder(@RequestBody OrderRequestDTO orderRequestDTO, @AuthenticationPrincipal User user) {
        return orderService.placeOrder(user, orderRequestDTO);
    }
}
