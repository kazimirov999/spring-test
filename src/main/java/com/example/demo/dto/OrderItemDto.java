package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto extends AuditableDto {
    private Integer id;
    private ProductDto product;
    @JsonBackReference(value = "order-orderItems")
    private OrderDto order;
    private Integer quantity;
}
