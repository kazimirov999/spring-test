package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(exclude = {"subComments", "user", "product", "parent"})
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All details about the comment")
public class CommentDto extends AuditableDto {
    @ApiModelProperty(notes = "The database generated comment id")
    private Integer id;
    @ApiModelProperty(notes = "Comment text")
    private String text;
    @ApiModelProperty(notes = "Comment subComment")
    @JsonBackReference(value = "comment-comment")
    private CommentDto parent;
    @JsonManagedReference(value = "comment-comment")
    private List<CommentDto> subComments;
    @JsonBackReference(value = "product-comment")
    private ProductDto product;
    @JsonBackReference(value = "user-comment")
    private UserDto user;
}
