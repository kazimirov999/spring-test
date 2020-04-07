package com.example.demo.service;

import com.example.demo.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto saveOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Integer id);

    void updateOrder(Integer id, OrderDto orderDto);

    void deleteOrderById(Integer id);

    void deleteOrderAll();
}
