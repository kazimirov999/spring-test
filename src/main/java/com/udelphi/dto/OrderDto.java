package com.udelphi.dto;

import java.util.HashSet;
import java.util.Set;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

@ApiModel(description = "All details about the order.")
public class OrderDto {
    @ApiModelProperty(notes = "The database generated order ID")
    private Integer id;
    @ApiModelProperty(notes = "Date when created order")
    private Date orderDate;
    @ApiModelProperty(notes = "List order items")
    private Set<OrderItemDto> orderItems = new HashSet<>();
    @ApiModelProperty(notes = "Id client who make order")
    private Integer clientId;

    public OrderDto() {
    }

    public Integer getClientId() {
        return clientId;
    }

    public OrderDto setClientId(Integer clientId) {
        this.clientId = clientId;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public OrderDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public OrderDto setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public Set<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public OrderDto setOrderItems(Set<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
        return this;
    }
}
