package com.example.simplyread_backend.repository;

import com.example.simplyread_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
