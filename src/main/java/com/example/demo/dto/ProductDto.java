package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"comments"})
@AllArgsConstructor
@ApiModel(description = "All details about the product")
public class ProductDto extends AuditableDto {
    @ApiModelProperty(notes = "The database generated product id")
    private Integer id;
    @ApiModelProperty(notes = "Product name")
    private String name;
    @ApiModelProperty(notes = "Product price")
    private Integer price;
    @ApiModelProperty(notes = "Comments for product")
    @JsonManagedReference(value = "product-comment")
    private Set<CommentDto> comments = new HashSet<>();

}
