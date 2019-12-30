package com.udelphi.controller;

import java.util.List;
import com.udelphi.dto.OrderDto;
import com.udelphi.dto.OrderItemDto;
import com.udelphi.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add an order")
    public OrderDto createOrder(
            @ApiParam(value = "Order object store in database table", required = true)
            @RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an order by Id")
    public OrderDto getOrder(
            @ApiParam(value = "Order id from which order object will retrieve", required = true)
            @PathVariable int id) {
        return orderService.getOrder(id);
    }

    @GetMapping
    @ApiOperation(value = "View a list of available orders", response = List.class)
    public List<OrderDto> getOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an order")
    public void deleteOrder(
            @ApiParam(value = "Order Id from which order object will delete from database table", required = true)
            @PathVariable int id) {
        orderService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update an order")
    public void updateOrder(
            @ApiParam(value = "Order Id to update order object", required = true)
            @PathVariable int id,
            @ApiParam(value = "Update order object", required = true)
            @RequestBody OrderDto orderDto) {
        orderService.updateOrder(id, orderDto);
    }


    @GetMapping("/{id}/orderItems")
    @ApiOperation(value = "View a list of available order items", response = List.class)
    public List<OrderItemDto> getAllOrderItems(
            @ApiParam(value = "Order Id from which order items objects will retrieve", required = true)
            @PathVariable int id) {
        return orderService.getAllOrderItems(id);
    }
}