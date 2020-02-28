package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        Order persist = orderRepository.save(modelMapper.map(orderDto, Order.class));
        return modelMapper.map(persist, OrderDto.class);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(toList());
    }

    public OrderDto getOrderById(Integer id) {
        return orderRepository.findById(id)
                .map(order -> modelMapper.map(order, OrderDto.class))
                .orElseThrow(()-> new EntityNotFoundException("Order not found"));
    }

    public void updateOrder(Integer id, OrderDto orderDto) {
        orderRepository.findById(id)
                .map(order -> modelMapper.map(orderDto, Order.class))
                .ifPresentOrElse(orderRepository::save,
                        () -> {
                            throw new EntityNotFoundException("Order_table not found by id " + id);
                        });
    }

    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);

    }

    public void deleteOrderAll() {
        orderRepository.deleteAll();
    }

}
