package com.udelphi.controller;

import java.util.List;
import com.udelphi.dto.OrderItemDto;
import com.udelphi.service.OrderItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {

    private final OrderItemService orderItemService;


    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add an order item")
    public OrderItemDto createOrderItem(
            @ApiParam(value = "Order item object store in database table", required = true)
            @RequestBody OrderItemDto orderItemDto) {
        return orderItemService.saveOrderItem(orderItemDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an order item by Id")
    public OrderItemDto getOrderItem(
            @ApiParam(value = "Order item id from which order item object will retrieve", required = true)
            @PathVariable int id) {
        return orderItemService.getOrderItem(id);
    }

    @GetMapping
    @ApiOperation(value = "View a list of available order items", response = List.class)
    public List<OrderItemDto> getOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete an order item")
    public void deleteOrderItem(
            @ApiParam(value = "Order item Id from which order item object will delete from database table", required = true)
            @PathVariable int id) {
        orderItemService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update an order item")
    public void updateOrderItem(
            @ApiParam(value = "Order item Id to update order item object", required = true)
            @PathVariable int id,
            @ApiParam(value = "Update order item object", required = true)
            @RequestBody OrderItemDto orderItemDto) {
        orderItemService.updateOrderItem(id, orderItemDto);
    }

}
