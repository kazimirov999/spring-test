package com.example.demo.repository;

import com.example.demo.model.Order;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
