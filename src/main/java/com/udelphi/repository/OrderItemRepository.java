package com.udelphi.repository;

import java.util.Set;
import com.udelphi.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    Set<OrderItem> findAllByOrderId(int id);
    Set<OrderItem> findAllByProductId(int id);
}
