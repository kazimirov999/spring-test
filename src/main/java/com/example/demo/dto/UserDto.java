package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@EqualsAndHashCode(exclude = {"comments", "orders", "roles"})
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All details about the user")
public class UserDto extends AuditableDto {
    @ApiModelProperty(notes = "The database generated user id")
    private Integer id;
    @ApiModelProperty(notes = "User name")
    private String name;
    @ApiModelProperty(notes = "User email")
    private String email;
    @ApiModelProperty(notes = "User roles")
    private Set<RoleDto> roles = new HashSet<>();
    @ApiModelProperty(notes = "User orders")
    @JsonManagedReference(value = "client-order")
    private Set<OrderDto> orders = new HashSet<>();
    @ApiModelProperty("User comments")
    @JsonManagedReference(value = "user-comment")
    private Set<CommentDto> comments = new HashSet<>();

}
