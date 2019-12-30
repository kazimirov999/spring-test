package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.OrderDto;
import com.udelphi.dto.OrderItemDto;
import com.udelphi.exception.EntityNotFoundException;
import com.udelphi.model.Order;
import com.udelphi.repository.OrderItemRepository;
import com.udelphi.repository.OrderRepository;
import static java.util.stream.Collectors.toList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.modelMapper = modelMapper;
    }


    public OrderDto saveOrder(OrderDto orderDto) {

        Order saveOrder = orderRepository.save(modelMapper.map(orderDto, Order.class));
        return modelMapper.map(saveOrder, OrderDto.class);
    }

    public OrderDto getOrder(int id) {

        return orderRepository.findById(id)
                .map(order -> modelMapper.map(order, OrderDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(toList());
    }

    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

    public void updateOrder(int id, OrderDto orderDto) {
        orderRepository.findById(id)
                .map(order -> modelMapper.map(orderDto, Order.class))
                .ifPresentOrElse(orderRepository::save,
                        () -> {
                            throw new EntityNotFoundException("Entity not found with id: " + id);
                        });
    }

    public List<OrderItemDto> getAllOrderItems(int id) {
        return orderItemRepository.findAllByOrderId(id)
                .stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class))
                .collect(toList());
    }
}