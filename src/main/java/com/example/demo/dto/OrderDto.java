package com.example.demo.dto;

import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"client", "products", "orderItems"})
@ApiModel(description = "All details about the order")
public class OrderDto extends AuditableDto {

    @ApiModelProperty(notes = "Order id in table Orders")
    private Integer id;
    @ApiModelProperty(notes = "User id in table Orders")
    @JsonBackReference(value = "client-order")
    private UserDto client;
    @ApiModelProperty(notes = "List products in table orders")
    private Set<ProductDto> products = new HashSet<>();
    @JsonManagedReference(value = "order-orderItems")
    private Set<OrderItemDto> orderItems = new HashSet<>();

}
