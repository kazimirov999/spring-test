package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.OrderDto;
import com.udelphi.dto.OrderItemDto;

public interface OrderService {
    OrderDto saveOrder(OrderDto orderDto);

    OrderDto getOrder(int id);

    List<OrderDto> getAllOrders();

    void deleteById(int id);

    void updateOrder(int id, OrderDto orderDto);

    List<OrderItemDto> getAllOrderItems(int id);
}
