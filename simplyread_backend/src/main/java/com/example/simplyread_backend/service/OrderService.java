package com.example.simplyread_backend.service;

import com.example.simplyread_backend.dto.BookDTO;
import com.example.simplyread_backend.dto.OrderDTO;
import com.example.simplyread_backend.dto.OrderRequestDTO;
import com.example.simplyread_backend.entity.Book;
import com.example.simplyread_backend.entity.Order;
import com.example.simplyread_backend.entity.User;
import com.example.simplyread_backend.repository.BookRepository;
import com.example.simplyread_backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final EmailService emailService;

    public OrderDTO placeOrder(User user, OrderRequestDTO orderRequestDTO) {
        List<Book> books = bookRepository.findAllById(orderRequestDTO.getBookIds());
        double total = books.stream().mapToDouble(Book::getPrice).sum();

        Order order = new Order();
        order.setUser(user);
        order.setBooks(books);
        order.setTotalPrice(total);
        order.setOrderDate(LocalDate.now());

        Order savedOrder = orderRepository.save(order);

        StringBuilder body = new StringBuilder("Hi" + user.getUsername() + ",\n\nYour Order: \n");
        books.forEach(book -> body.append(book.getName()).append(" - $").append(book.getPrice()).append("\n"));
        body.append("\nTotal: $").append(total);
        emailService.sendOrderConfirmationEmail(user.getEmail(), "Order Confirmation", body.toString());

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(savedOrder.getOrderId());
        orderDTO.setUsername(user.getUsername());
        orderDTO.setTotalPrice(savedOrder.getTotalPrice());
        orderDTO.setOrderDate(savedOrder.getOrderDate().atStartOfDay());
        orderDTO.setBooks(books.stream().map(book -> {
            BookDTO bdto = new BookDTO();
            BeanUtils.copyProperties(book, bdto);
            return bdto;
        }).collect(Collectors.toList()));
        return orderDTO;
    }



}
