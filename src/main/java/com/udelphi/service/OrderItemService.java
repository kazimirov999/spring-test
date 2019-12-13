package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.OrderItemDto;

public interface OrderItemService {
    OrderItemDto saveOrderItem(OrderItemDto orderItemDto);

    OrderItemDto getOrderItem(int id);

    List<OrderItemDto> getAllOrderItems();

    void deleteById(int id);

    void updateOrderItem(int id, OrderItemDto orderItemDto);
}
