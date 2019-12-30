package com.udelphi.repository;

import java.util.Set;
import com.udelphi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Set<Order> findAllByClientId(int id);
}
