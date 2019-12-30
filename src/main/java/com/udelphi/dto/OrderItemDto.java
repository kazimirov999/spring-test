package com.udelphi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the order item.")
public class OrderItemDto  extends AuditableDto{
    @ApiModelProperty(notes = "The database generated order item ID")
    private Integer id;
    @ApiModelProperty(notes = "Product quantity")
    private Integer quantity;
    @ApiModelProperty(notes = "Id product")
    private Integer productId;
    @ApiModelProperty(notes = "Id order who have this order item")
    private Integer orderId;

    public OrderItemDto() {
    }

    public Integer getProductId() {
        return productId;
    }

    public OrderItemDto setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderItemDto setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public OrderItemDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItemDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
