package com.udelphi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the role.")
public class RoleDto {
    @ApiModelProperty(notes = "The database generated role ID")
    private Integer id;
    @ApiModelProperty(notes = "Role name")
    private String name;

    public RoleDto() {
    }

    public Integer getId() {
        return id;
    }

    public RoleDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleDto setName(String name) {
        this.name = name;
        return this;
    }
}
