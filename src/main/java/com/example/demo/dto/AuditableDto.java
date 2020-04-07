package com.example.demo.dto;

import lombok.*;

import java.util.Date;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AuditableDto {

    protected String createdBy;

    protected Date creationDate;

    protected String lastModifiedBy;

    protected Date lastModifiedDate;
}
