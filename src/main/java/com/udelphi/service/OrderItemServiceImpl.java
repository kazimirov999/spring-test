package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.OrderItemDto;
import com.udelphi.exception.EntityNotFoundException;
import com.udelphi.model.OrderItem;
import com.udelphi.repository.OrderItemRepository;
import static java.util.stream.Collectors.toList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ModelMapper modelMapper) {
        this.orderItemRepository = orderItemRepository;
        this.modelMapper = modelMapper;
    }


    public OrderItemDto saveOrderItem(OrderItemDto orderItemDto) {

        OrderItem saveOrderItem = orderItemRepository.save(modelMapper.map(orderItemDto, OrderItem.class));
        return modelMapper.map(saveOrderItem, OrderItemDto.class);
    }

    public OrderItemDto getOrderItem(int id) {

        return orderItemRepository.findById(id)
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<OrderItemDto> getAllOrderItems() {
        return orderItemRepository.findAll()
                .stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class))
                .collect(toList());
    }

    public void deleteById(int id) {
        orderItemRepository.deleteById(id);
    }

    public void updateOrderItem(int id, OrderItemDto orderItemDto) {
        orderItemRepository.findById(id)
                .map(orderItem -> modelMapper.map(orderItemDto, OrderItem.class))
                .ifPresentOrElse(orderItemRepository::save,
                        () -> {
                            throw new EntityNotFoundException("Entity not found with id: " + id);
                        });
    }

}