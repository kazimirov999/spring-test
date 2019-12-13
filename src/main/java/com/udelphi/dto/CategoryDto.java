package com.udelphi.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the category.")
public class CategoryDto {

    @ApiModelProperty(notes = "Database generated category ID")
    private Integer id;
    @ApiModelProperty(notes = "Category name")
    private String name;

    public CategoryDto() {
    }

    public Integer getId() {
        return id;
    }

    public CategoryDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }
}
