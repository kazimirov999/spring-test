package com.example.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All details about the role")
public class RoleDto extends AuditableDto {
    @ApiModelProperty(notes = "The database generated role id")
    private Integer id;
    @ApiModelProperty(notes = "Role name")
    private String name;

}
