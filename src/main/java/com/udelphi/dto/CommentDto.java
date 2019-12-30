package com.udelphi.dto;

import java.util.Set;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the comment.")
public class CommentDto extends AuditableDto{
    @ApiModelProperty(notes = "Database generated comment ID")
    private Integer id;
    @ApiModelProperty(notes = "Comment text")
    private String text;
    @ApiModelProperty(notes = "Id user who written comment")
    private Integer userId;
    @ApiModelProperty(notes = "Id product to which it was written comment")
    private Integer productId;
    @ApiModelProperty(notes = "List sub comments")
    private Set<CommentDto> comments;

    public CommentDto() {
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

    public CommentDto setComments(Set<CommentDto> comments) {
        this.comments = comments;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public CommentDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public CommentDto setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public CommentDto setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getProductId() {
        return productId;
    }

    public CommentDto setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }
}
