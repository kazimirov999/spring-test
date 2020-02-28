package com.example.demo.controllers;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a order")
    public OrderDto addOrder(
            @ApiParam(value = "Order object store in database table", required = true)
            @RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

    @GetMapping
    @ApiOperation(value = "Get all orders")
    public List<OrderDto> getOrdersAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get order by id")
    public OrderDto getOrderById(
            @ApiParam(value = "The order ID that will return the order from the database table")
            @PathVariable Integer id) {
        return orderService.getOrderById(id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete comment by id")
    public void deleteOrderById(
            @ApiParam(value = "Order ID to delete the order from the database table")
            @PathVariable Integer id) {
        orderService.deleteOrderById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all comments")
    public void deleteOrdersAll() {
        orderService.deleteOrderAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update order by id")
    public void updateOrderById(
            @ApiParam(value = "Order ID to be updated in the database table")
            @PathVariable Integer id,
            @ApiParam(value = "Update order object")
            @RequestBody OrderDto orderDto) {
        orderService.updateOrder(id, orderDto);
    }
}
